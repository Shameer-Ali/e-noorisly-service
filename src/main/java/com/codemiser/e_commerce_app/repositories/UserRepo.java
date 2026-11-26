package com.codemiser.e_commerce_app.repositories;

import com.codemiser.e_commerce_app.modals.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findUserByUsername(String username);

    Optional<AppUser> findByGoogleSubject(String googleSubject);
}
