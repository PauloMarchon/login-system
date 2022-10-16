package com.site.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

     User saveUser(UserDto userDto);

     User findByEmail(String email);

     User findByUsername(String username);

     List<UserDto> findAllUsers();

     void deleteUser(UUID id);

}
