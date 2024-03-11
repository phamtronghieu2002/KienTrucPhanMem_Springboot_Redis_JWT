package com.example.spring_boot_api_jwt_ad.controller;

import com.example.spring_boot_api_jwt_ad.authen.UserPrincipal;
import com.example.spring_boot_api_jwt_ad.entity.Car;
import com.example.spring_boot_api_jwt_ad.entity.Token;
import com.example.spring_boot_api_jwt_ad.entity.User;
import com.example.spring_boot_api_jwt_ad.repository.CarRepository;
import com.example.spring_boot_api_jwt_ad.service.TokenService;
import com.example.spring_boot_api_jwt_ad.service.UserService;
import com.example.spring_boot_api_jwt_ad.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());
        System.out.println(userPrincipal.getAuthorities());
        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/hello")

    public ResponseEntity hello(){
        return ResponseEntity.ok("hello");
    }


    @GetMapping("/car")
    public ResponseEntity<List<Car>> getCars(){

        return  ResponseEntity.status(HttpStatus.OK).body(carRepository.findAll());
    }



}
