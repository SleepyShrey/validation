package br.com.darchanjo.examples.controller;

import br.com.darchanjo.examples.entity.UserDTO;
import br.com.darchanjo.examples.entity.UserEditDTO;
import br.com.darchanjo.examples.entity.Users;
import br.com.darchanjo.examples.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Slf4j
@RestController
public class UserValidation {

    private final RestTemplate restTemplate;

    private final UserServiceImpl userService;

    @Autowired
    public UserValidation(RestTemplate restTemplate, UserServiceImpl userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }


    @PostMapping("/user")
    public ResponseEntity<String> postNewUser(@Valid @RequestBody UserDTO users)
    {
        String name = users.getName();
        List<Users> username = userService.checkUser(name);
        log.info("Inside method postNewUser()");
        if(username.isEmpty()){
            HttpHeaders headers = new HttpHeaders();
//            String url = "http://user-app:8080/user/post";
            String url = "http://localhost:8080/user/post";
            HttpEntity<UserDTO> entity = new HttpEntity<>(users,headers);
            restTemplate.postForEntity(url,entity, UserDTO.class);
            log.info("User not found. Updated successfully");
            return new ResponseEntity<>("User not found. Updated successfully", HttpStatus.OK);
        }
        else {
            log.warn("User found with the same name in DB. Post method is skipped");
            return new ResponseEntity<>("User found with the same name in DB. Post method is skipped",HttpStatus.OK);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUserById(@Valid @RequestParam long id)
    {
        Users user = userService.checkUser(id);
//        String uri = "http://user-app:8080/user/delete/" + id;
        String uri = "http://localhost:8080/user/delete/" + id;


        log.info("Delete the user is requesting id, and the id provided is "+id);

        if (user != null)
        {
            // Send DELETE request to the external CRUD app
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                log.info("Deleted user successfully with id: {}", id);
                return new ResponseEntity<>("Deleted successfully ", HttpStatus.OK);
            }
            else {
                log.error("Failed to delete the user. In the else block of verification for success status");
                return new ResponseEntity<>("Failed to delete user in CRUD app", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            log.warn("User not found warning. Deleting the user not exist");
            return new ResponseEntity<>("User Not found",HttpStatus.OK);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> putUserById(@Valid @PathVariable long id, @RequestBody UserEditDTO users)
    {
        Users user = userService.checkUser(id);
//        String uri = "http://user-app:8080/user/edit/" + id;
        String uri = "http://localhost:8080/user/edit/" + id;
        if (user != null)
        {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<UserEditDTO> entity = new HttpEntity<>(users,headers);
            ResponseEntity<UserEditDTO> response = restTemplate.exchange(uri,HttpMethod.PUT,entity, UserEditDTO.class);

            if(response.getStatusCode().is2xxSuccessful()) {
                log.info("User with id: {} edited successfully.", id);
                return new ResponseEntity<>("Edited successfully", HttpStatus.OK);
            }
            else {
                log.error("Failed to edit user id: {}", id);
                return new ResponseEntity<>("Failed to edit user in CRUD app", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            log.warn("Failed to find the user with id: {}", id);
            return new ResponseEntity<>("User Not found",HttpStatus.OK);
        }
    }
}
