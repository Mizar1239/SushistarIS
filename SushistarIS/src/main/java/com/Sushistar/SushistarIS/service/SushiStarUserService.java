package com.Sushistar.SushistarIS.service;


import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.repo.SushistarUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SushiStarUserService {
    private final SushistarUserRepo userRepo;

    @Autowired
    public SushiStarUserService(SushistarUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<SushistarUser> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<SushistarUser> login(String email, String password) {
        Optional<SushistarUser> user = userRepo.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
