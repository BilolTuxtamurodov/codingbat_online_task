package com.company.codingbatapp.repository;

import com.company.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(@Email(message = "Email valid emas!")
                                  @NotNull(message = "bo'sh bo'lmasin")
                                  @NotEmpty(message = "Empty bo'lmasin")
                                          String email, Integer id);
}
