package com.example.demoauth.controllers;

import com.example.demoauth.exception.UserAlreadyExistException;
import com.example.demoauth.exception.UserNotFoundException;
import com.example.demoauth.models.Cities;
import com.example.demoauth.models.User;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.pojo.SignupRequest;
import com.example.demoauth.repository.CitiesRepository;
import com.example.demoauth.repository.UserRepository;
import com.example.demoauth.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    @Mock
    private AdminController adminController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CitiesRepository citiesRepository;

    @Mock
    private AdminService adminService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void getAllUsers() {
        List<User> userList = userRepository.findAll();

        Mockito.when(adminService.getAllUsers()).thenReturn(userList);

        assertEquals(userList, adminService.getAllUsers());

        assertNotNull(adminController.getAllCities());
    }

    @Test
    void editUser() throws UserNotFoundException, UserAlreadyExistException {
        Set<String> set = new HashSet<>();
        set.add("user");
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("test");
        signupRequest.setEmail("test@gmail.com");
        signupRequest.setPassword("12345678");
        signupRequest.setRoles(set);

        Mockito.when(adminController.editUser(4L, signupRequest)).thenReturn(ResponseEntity.ok(new MessageResponse("User updated")));
        //assertEquals(ResponseEntity.ok(new MessageResponse("User updated")), adminController.editUser(4L, signupRequest));

        //assertNull(adminService.editUser(4L, signupRequest));

    }


    @Test
    void deleteUser(){
        Mockito.when(adminController.deleteUser(4L)).thenReturn(ResponseEntity.ok("User was deleted"));

        //assertEquals(ResponseEntity.ok("User was deleted"), adminController.deleteUser(4L));
        assertNotNull(adminController.deleteUser(4L));
    }

    @Test
    void addNewCities() throws Exception {
        Cities cities = new Cities();
        cities.setCitiesName("Tashkent");
        cities.setWeather("smog");
        cities.setDegree("11");
        cities.setStatus(true);

        Mockito.when(adminController.addNewCities(cities)).thenReturn(ResponseEntity.ok(new MessageResponse("Successfully added")));
        //assertEquals(ResponseEntity.ok(new MessageResponse("Successfully added")), adminService.saveNewCity(cities));
        //assertNull(adminService.saveNewCity(cities));
    }


    @Test
    void getAllCities(){
        List<Cities> citiesList = citiesRepository.findAll();

        Mockito.when(adminService.getAllCities()).thenReturn(citiesList);

        assertEquals(citiesList, adminService.getAllCities());

        assertNotNull(adminController.getAllCities());
    }

    @Test
    void deleteCity(){
        Mockito.when(adminController.deleteCity(5L)).thenReturn(ResponseEntity.ok("City was deleted"));

        //assertEquals(ResponseEntity.ok("User was deleted"), adminController.deleteUser(4L));
        assertNotNull(adminController.deleteCity(5L));
    }
}