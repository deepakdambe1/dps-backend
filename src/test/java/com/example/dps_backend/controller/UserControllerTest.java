package com.example.dps_backend.controller;

import com.example.dps_backend.model.User;
import com.example.dps_backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser_returnsCreatedWithBody() {
        User input = mock(User.class);
        User created = mock(User.class);
        when(userService.createUser(input)).thenReturn(created);

        ResponseEntity<User> resp = userController.createUser(input);

        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertSame(created, resp.getBody());
        verify(userService).createUser(input);
    }

    @Test
    void getUserById_found_returnsOkAndBody() {
        Long id = 1L;
        User user = mock(User.class);
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<User> resp = userController.getUserById(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(user, resp.getBody());
    }

    @Test
    void getUserById_notFound_returnsNotFound() {
        Long id = 2L;
        when(userService.getUserById(id)).thenReturn(Optional.empty());

        ResponseEntity<User> resp = userController.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void getUserByUsername_found_returnsOk() {
        String username = "bob";
        User user = mock(User.class);
        when(userService.getUserByUsername(username)).thenReturn(user);

        ResponseEntity<User> resp = userController.getUserByUsername(username);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(user, resp.getBody());
    }

    @Test
    void getUserByUsername_notFound_returnsNotFound() {
        String username = "charlie";
        when(userService.getUserByUsername(username)).thenReturn(null);

        ResponseEntity<User> resp = userController.getUserByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void getAllUsers_returnsOkWithList() {
        User u1 = mock(User.class);
        User u2 = mock(User.class);
        List<User> users = Arrays.asList(u1, u2);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> resp = userController.getAllUsers();

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(users, resp.getBody());
    }

    @Test
    void updateUser_whenSuccessful_returnsOk() {
        Long id = 7L;
        User input = mock(User.class);
        User updated = mock(User.class);
        when(userService.updateUser(id, input)).thenReturn(updated);

        ResponseEntity<User> resp = userController.updateUser(id, input);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(updated, resp.getBody());
    }

    @Test
    void updateUser_whenNotFound_returnsNotFound() {
        Long id = 8L;
        User input = mock(User.class);
        when(userService.updateUser(id, input)).thenReturn(null);

        ResponseEntity<User> resp = userController.updateUser(id, input);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void deleteUser_whenSuccessful_returnsNoContent() {
        Long id = 9L;
        when(userService.deleteUser(id)).thenReturn(true);

        ResponseEntity<Void> resp = userController.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
        assertNull(resp.getBody());
    }

    @Test
    void deleteUser_whenNotFound_returnsNotFound() {
        Long id = 10L;
        when(userService.deleteUser(id)).thenReturn(false);

        ResponseEntity<Void> resp = userController.deleteUser(id);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }
}

