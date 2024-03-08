package fit.iuh.edu.controllers;

import fit.iuh.edu.entities.Car;
import fit.iuh.edu.entities.User;
import fit.iuh.edu.repositories.UserRepository;
import fit.iuh.edu.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class userController {


    RestTemplate restTemplate = new RestTemplate();


    @Autowired
    private UserDao userService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {


        String apiUrl = "http://localhost:8082/car?userid="+id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token YourAccessTokenHere");


        HttpEntity<String> entity = new HttpEntity<>(headers);


        ParameterizedTypeReference<List<Car>> responseType = new ParameterizedTypeReference<List<Car>>() {};
        ResponseEntity<List<Car>> response = restTemplate.exchange(apiUrl + "1", HttpMethod.GET, entity, responseType);


        List<Car> carList = response.getBody();

        User user=userService.findUserById(id);
        user.setCars(carList);

        return  user;

    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @PostMapping("/users")
    public User listAll(@RequestBody User user) {

        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User User) {

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
             userService.deleteUser(id);
    }
}
