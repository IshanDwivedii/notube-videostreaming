package com.notube.notube.service;

import com.notube.notube.dto.LoginRequest;
import com.notube.notube.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import com.notube.notube.repository.UserRepository;
import com.notube.notube.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Map<String, String> signup(User request){
        //check user exist
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("You Already Have an Account!");
        }
        //encode pass
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        //generate jwt token
        String token = jwtService.generateToken(user);

        return Map.of(
                "token", token,
                "messages", "Successful SignUp"
        );


    }

    //for login verify creds and return jwt

    public Map<String, String> login(String username, String password){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // fetch user details
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid Creds"));

        // generate new token
        String token = jwtService.generateToken(user);

        return Map.of(
                "token", token,
                "message", "Successful Login"
        );
    }


}
