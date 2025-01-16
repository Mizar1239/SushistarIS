package com.Sushistar.SushistarIS.controller;

import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.service.SushiStarUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class SushiStarUserController {

    private final SushiStarUserService userService;

    public SushiStarUserController(SushiStarUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Optional<SushistarUser> user = userService.login(username, password);
        if (user.isPresent()) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<SushistarUser> getUserDetails(@RequestParam String email) {
        Optional<SushistarUser> user = userService.findByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
