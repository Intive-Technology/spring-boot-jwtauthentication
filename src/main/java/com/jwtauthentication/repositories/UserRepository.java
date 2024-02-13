package com.jwtauthentication.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jwtauthentication.models.User;

public interface UserRepository extends JpaRepository<User,String> {
    public Optional<User> findByEmail(String email);
}
