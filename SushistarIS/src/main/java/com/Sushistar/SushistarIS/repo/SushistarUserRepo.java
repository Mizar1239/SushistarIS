package com.Sushistar.SushistarIS.repo;

import com.Sushistar.SushistarIS.model.SushistarUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SushistarUserRepo extends JpaRepository<SushistarUser, Long> {
    Optional<SushistarUser> findByEmail(String username);
}
