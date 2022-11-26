package com.example.demoauth.controllers;

import com.example.demoauth.exception.UserAlreadyExistException;
import com.example.demoauth.exception.UserNotFoundException;
import com.example.demoauth.models.Cities;
import com.example.demoauth.models.User;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.pojo.SignupRequest;
import com.example.demoauth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * This method returns user list
	 *
	 * @return All users
	 * @author Abdulxafiz Mirjamalov
	 */
	@GetMapping("/user-list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return adminService.getAllUsers();
	}

	/**
	 * Method for changing user data using id
	 *
	 * @return Response message
	 * @author Abdulxafiz Mirjamalov
	 */
	@PutMapping("/edit-user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> editUser(@PathVariable Long id, @RequestBody SignupRequest signupRequest) throws UserNotFoundException, UserAlreadyExistException {
		return adminService.editUser(id, signupRequest);
	}

	/**
	 * Delete user using id
	 *
	 * @return Response message
	 * @author Abdulxafiz Mirjamalov
	 */
	@DeleteMapping("/delete-user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		try {
			adminService.deleteUserById(id);
			return ResponseEntity.ok("User was deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
		}
	}

	/**
	 * Method for adding city and weather information
	 *
	 * @return Response message
	 * @author Abdulxafiz Mirjamalov
	 */
	@PostMapping("/add-city-weather")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> addNewCities(@RequestBody Cities cities) throws Exception {
		return adminService.saveNewCity(cities);
	}

	/**
	 * Method for changing city data using id
	 *
	 * @return Response message
	 * @author Abdulxafiz Mirjamalov
	 */
	@PutMapping("/edit-city")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateCity(@RequestBody Cities cities){
		try {
			adminService.updateCity(cities);
			return ResponseEntity.ok("City update successfully");
		}catch (UserNotFoundException | UserAlreadyExistException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * Method to get all cities and weather info
	 *
	 * @return All cities and weather info
	 * @author Abdulxafiz Mirjamalov
	 */
	@GetMapping("/cities-list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Cities> getAllCities() {
		return adminService.getAllCities();
	}

	/**
	 * Delete cities using id
	 *
	 * @return Response message
	 * @author Abdulxafiz Mirjamalov
	 */
	@DeleteMapping("/delete-city/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCity(@PathVariable Long id) {
		try {
			adminService.deleteCityById(id);
			return ResponseEntity.ok("City was deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
		}
	}
}

