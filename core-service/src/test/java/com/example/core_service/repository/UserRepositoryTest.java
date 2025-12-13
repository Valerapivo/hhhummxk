package com.example.core_service.repository;

import com.example.core_service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("findme");
        user.setEmail("findme@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("findme");

        assertNotNull(foundUser);
        assertEquals("findme", foundUser.getUsername());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("todelete");
        user.setEmail("delete@example.com");
        user.setPassword("password123");
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());

        assertFalse(userRepository.existsById(savedUser.getId()));
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("toupdate");
        user.setEmail("update@example.com");
        user.setPassword("password123");
        User savedUser = userRepository.save(user);

        savedUser.setEmail("newemail@example.com");
        User updatedUser = userRepository.save(savedUser);

        assertEquals("newemail@example.com", updatedUser.getEmail());
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setUsername("emailtest");
        user.setEmail("specific@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("specific@example.com");

        assertNotNull(foundUser);
        assertEquals("emailtest", foundUser.getUsername());
    }
}
