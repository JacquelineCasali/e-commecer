package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequestDTO;
import com.app.ecom.dto.UserResponseDTO;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }


    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        updateUserFromRequest(user, userRequestDTO);
        return mapToUserResponse(userRepository.save(user));


    }


    public UserResponseDTO updateUser(Long id, UserRequestDTO updateUserRequestdto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateUserFromRequest(existingUser, updateUserRequestdto);
        return mapToUserResponse(userRepository.save(existingUser));

    }


    public Optional<UserResponseDTO> fetchUser(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);

    }

    private UserResponseDTO mapToUserResponse(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(String.valueOf(user.getId()));
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhone(user.getPhone());
        responseDTO.setRole(user.getRole());
        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            responseDTO.setAddress(addressDTO);
        }
        return responseDTO;
    }

    private void updateUserFromRequest(User user, UserRequestDTO userRequestDTO) {
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhone(userRequestDTO.getPhone());
        if (userRequestDTO.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequestDTO.getAddress().getStreet());
            address.setCity(userRequestDTO.getAddress().getCity());
            address.setState(userRequestDTO.getAddress().getState());
            address.setCountry(userRequestDTO.getAddress().getCountry());
            address.setZipcode(userRequestDTO.getAddress().getZipcode());
            user.setAddress(address);
        }

    }
}
