package com.values.LibrarySystemSecurity.respository;

import com.values.LibrarySystemSecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findById(Long id);

    User findByUsername(String username);
}
