package com.site.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper = new UserMapper();

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        ERole role = ERole.ROLE_USER;
        user.setRoles(role);
        //user.setRoles(Collections.singletonList(role)); //ATRIBUIR ROLE_USER AO SET DE ROLES DO USUARIO NA CRIACAO


        userRepository.save(user);

        return userMapper.convertToDto(user);

    }

    @Override
    public User findByEmail(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()){
            return existingUser.get();
        } else {
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()){
            return existingUser.get();
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
