package com.example.footballteammanagementsystem.service;

import com.example.footballteammanagementsystem.domain.dto.RegisterUserRequest;
import com.example.footballteammanagementsystem.domain.dto.UsernameExistsException;
import com.example.footballteammanagementsystem.domain.exception.EmailExistsException;
import com.example.footballteammanagementsystem.domain.exception.TeamNameExistsException;
import com.example.footballteammanagementsystem.domain.model.Team;
import com.example.footballteammanagementsystem.domain.model.User;
import com.example.footballteammanagementsystem.domain.model.VerificationToken;
import com.example.footballteammanagementsystem.repository.TeamRepository;
import com.example.footballteammanagementsystem.repository.UserRepository;
import com.example.footballteammanagementsystem.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    public static final String TOKEN_BASE_PATH = "http://localhost:8080/api/auth/verification?token=";

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(RegisterUserRequest request) {
        validateRegistrationRequest(request);

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(LocalDate.parse(request.getDateOfBirth(), DateTimeFormatter.ISO_LOCAL_DATE));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        user = userRepository.save(user);
        createAndSaveTeamForUser(request, user);

        String token = generateVerificationToken(user);
        String activationLink = TOKEN_BASE_PATH + token;
        String message = "Thank you for registration, " +
                "please confirm your account using activation link: " + activationLink;
        mailService.sendMail(user.getEmail(), "Activate your account", message);
    }

    private void createAndSaveTeamForUser(RegisterUserRequest request, User saved) {
        Team team = new Team();
        team.setName(request.getTeamName());
        team.setCreatedDate(Instant.now());
        team.setTrainer(saved);
        teamRepository.save(team);
    }

    private void validateRegistrationRequest(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException("Email: " + request.getEmail() + " already exists");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameExistsException("Username: " + request.getUsername() + " already exists");
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new ValidationException("Passwords doesn't match");
        }
        if (teamRepository.existsByName(request.getTeamName())) {
            throw new TeamNameExistsException("Team with name " + request.getTeamName() + " already exists");
        }
    }

    private String generateVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return verificationToken.getToken();
    }
}
