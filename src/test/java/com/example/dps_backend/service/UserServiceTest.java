package com.example.dps_backend.service;

import com.example.dps_backend.model.User;
import com.example.dps_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_callsRepositoryAndReturnsSaved() {
        User user = mock(User.class);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertSame(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserById_whenFound_returnsOptionalWithUser() {
        Long id = 1L;
        User user = mock(User.class);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(id);

        assertTrue(result.isPresent());
        assertSame(user, result.get());
        verify(userRepository).findById(id);
    }

    @Test
    void getUserById_whenNotFound_returnsEmptyOptional() {
        Long id = 2L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(id);

        assertFalse(result.isPresent());
        verify(userRepository).findById(id);
    }

    @Test
    void getUserByUsername_returnsUserFromRepository() {
        String username = "alice";
        User user = mock(User.class);
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.getUserByUsername(username);

        assertSame(user, result);
        verify(userRepository).findByUsername(username);
    }

    @Test
    void getAllUsers_returnsListFromRepository() {
        User u1 = mock(User.class);
        User u2 = mock(User.class);
        List<User> users = Arrays.asList(u1, u2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertSame(u1, result.get(0));
        assertSame(u2, result.get(1));
        verify(userRepository).findAll();
    }

    @Test
    void updateUser_whenExists_savesAndReturnsUpdated() {
        Long id = 10L;
        User existing = mock(User.class);
        User input = mock(User.class);
        User saved = mock(User.class);

        when(userRepository.findById(id)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenReturn(saved);

        User result = userService.updateUser(id, input);

        assertSame(saved, result);
        verify(userRepository).findById(id);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUser_whenNotExists_returnsNull() {
        Long id = 11L;
        User input = mock(User.class);
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        User result = userService.updateUser(id, input);

        assertNull(result);
        verify(userRepository).findById(id);
        verify(userRepository, never()).save(any());
    }

    @Test
    void deleteUser_whenExists_deletesAndReturnsTrue() {
        Long id = 5L;
        when(userRepository.existsById(id)).thenReturn(true);

        boolean result = userService.deleteUser(id);

        assertTrue(result);
        verify(userRepository).deleteById(id);
    }

    @Test
    void deleteUser_whenNotExists_returnsFalse() {
        Long id = 6L;
        when(userRepository.existsById(id)).thenReturn(false);

        boolean result = userService.deleteUser(id);

        assertFalse(result);
        verify(userRepository, never()).deleteById(anyLong());
    }
}

