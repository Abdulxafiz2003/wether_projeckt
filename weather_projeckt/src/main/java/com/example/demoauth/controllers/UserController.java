package com.example.demoauth.controllers;

import com.example.demoauth.models.Cities;
import com.example.demoauth.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method returns the names of the cities that are available to you.
     *
     * @return String list with cities names
     * @author Abdulxafiz Mirjamalov
     */
    @GetMapping("/cities-name")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<String> getCitiesName() {
		return userService.getCitiesName();
	}


    /**
     * This method returns the cities and weather that are available to you.
     *
     * @return List Cities
     * @author Abdulxafiz Mirjamalov
     */
    @GetMapping("/cities")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Cities> getCities() {
        return userService.getCities();
    }


}
