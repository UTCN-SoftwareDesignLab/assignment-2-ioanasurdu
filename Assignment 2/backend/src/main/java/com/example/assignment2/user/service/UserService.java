package com.example.assignment2.user.service;

import com.example.assignment2.user.dto.UserDTO;
import com.example.assignment2.user.dto.UserListDTO;
import com.example.assignment2.user.dto.UserMinimalDTO;
import com.example.assignment2.user.mapper.UserMapper;
import com.example.assignment2.user.model.User;
import com.example.assignment2.user.repository.RoleRepository;
import com.example.assignment2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::fromUser)
                .collect(toList());
    }

    public UserDTO create(UserDTO user) {
        return userMapper.fromUser(userRepository.save(
                userMapper.toUser(user)
        ));
    }

    public void edit(UserDTO user) {
        userRepository.save(userMapper.toUser(user));
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
    }

}
