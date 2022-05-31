package com.pv41.rentalproperty.controller;

import com.pv41.rentalproperty.dto.AuthenticationRequestDto;
import com.pv41.rentalproperty.dto.AuthenticationResponseDto;
import com.pv41.rentalproperty.exceptions.LoginUnavailableException;
import com.pv41.rentalproperty.model.User;
import com.pv41.rentalproperty.security.jwt.JwtTokenProvider;
import com.pv41.rentalproperty.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;

@RestController
@RequestMapping(value = "api/v1/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            if (!loginDataIsCorrect(requestDto)) {
                throw new IllegalArgumentException();
            }

            String login = requestDto.getLogin();
            User user = userService.findByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("User with login " + login + " not found");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login, requestDto.getPassword()
            ));
            String accessToken = jwtTokenProvider.createAccessToken(login, user.getRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(login, user.getRoles());

            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Incorrect input");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Incorrect password");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            if (!registerDataIsCorrect(requestDto)) {
                throw new IllegalArgumentException();
            }
            User user = new User();
            user.setLogin(requestDto.getLogin());
            user.setPassword(requestDto.getPassword());
            user.setFirstName(requestDto.getFirstName());
            user.setLastName(requestDto.getLastName());
            userService.register(user);
            return ResponseEntity.ok("Success registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Incorrect input");
        } catch (AddressException e) {
            return ResponseEntity.badRequest().body("Incorrect mail");
        } catch (LoginUnavailableException e) {
            return ResponseEntity.badRequest().body("Login not available");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }

    private Boolean registerDataIsCorrect(AuthenticationRequestDto request) {
        return request.getLogin() != null
                && request.getPassword() != null
                && request.getFirstName() != null
                && request.getLastName() != null;
    }

    private Boolean loginDataIsCorrect(AuthenticationRequestDto request) {
        return request.getLogin() != null
                && request.getPassword() != null;
    }
}
