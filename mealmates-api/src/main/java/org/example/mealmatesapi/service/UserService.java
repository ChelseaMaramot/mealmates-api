package org.example.mealmatesapi.service;

import org.example.mealmatesapi.dto.UserDTO;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserDTO mapToDTO(User user){
        return new UserDTO(user.getUsername(), user.getPassword(),user.getEmail());
    }

    private User mapToEntity(UserDTO userDTO){
        return new User(userDTO.getUsername(),  userDTO.getPassword(), userDTO.getEmail());
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public UserDTO getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return mapToDTO(user.get());
        }else{
            throw new RuntimeException("User not found with id: " + id);
        }

    }

    public UserDTO createUser(UserDTO userDTO)
    {
        System.out.println("----------CREATING USER IN SERVICE--------");
        return mapToDTO(userRepository.save(mapToEntity(userDTO)));
    }

    public void deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new RuntimeException("user does not exist with id: "+id);
        }

    }


}
