package com.example.javaProject1.controller;

import com.example.javaProject1.controller.VM.LoginVM;
import com.example.javaProject1.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
private final JwtTokenProvider jwtTokenProvider;
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authorize(@RequestBody LoginVM loginVm){
        UsernamePasswordAuthenticationToken authenticationToken
                =new UsernamePasswordAuthenticationToken(loginVm.getUsername() , loginVm.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(loginVm.getUsername() , authentication);

        HttpHeaders headers =new HttpHeaders();
        headers.add("Authorization" , "Bearer "+ jwt);
        return new ResponseEntity(new JJWT(jwt) , headers , HttpStatus.OK);
    }

    static class JJWT{
        private String token;

        public String getToken() {
            return token;
        }

        public JJWT(String token) {
            this.token = token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
