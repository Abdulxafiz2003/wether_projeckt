package com.example.demoauth.service;

import com.example.demoauth.exception.UserAlreadyExistException;
import com.example.demoauth.exception.UserNotFoundException;
import com.example.demoauth.models.Cities;
import com.example.demoauth.models.ERole;
import com.example.demoauth.models.Role;
import com.example.demoauth.models.User;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.pojo.SignupRequest;
import com.example.demoauth.repository.CitiesRepository;
import com.example.demoauth.repository.RoleRepository;
import com.example.demoauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final CitiesRepository citiesRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<MessageResponse> editUser(Long id, SignupRequest signupRequest) throws UserAlreadyExistException, UserNotFoundException {
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже сущуствеут");
        }else if (userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User not found!");
        }

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                if ("admin".equals(r)) {
                    Role adminRole = roleRepository
                            .findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository
                            .findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User updated"));
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public ResponseEntity<MessageResponse> saveNewCity(Cities cities) throws UserAlreadyExistException {
        if (citiesRepository.findByCitiesName(cities.getCitiesName()) != null) {
            throw new UserAlreadyExistException("Такой город уже сушествует");
        }
        cities.setDegree(cities.getDegree() + "C°");
        citiesRepository.save(cities);
        return ResponseEntity.ok(new MessageResponse("Successfully added"));
    }

    public void updateCity(Cities cities) throws UserAlreadyExistException, UserNotFoundException {
        if (citiesRepository.findByCitiesName(cities.getCitiesName()) != null) {
            throw new UserAlreadyExistException("Такой город уже сущуствеут");
        }else if (citiesRepository.findById(cities.getId()).isEmpty()){
            throw new UserNotFoundException("City not found!");
        }
        citiesRepository.save(cities);
    }

    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }


    public void deleteCityById(Long id){
        citiesRepository.deleteById(id);
    }
}
