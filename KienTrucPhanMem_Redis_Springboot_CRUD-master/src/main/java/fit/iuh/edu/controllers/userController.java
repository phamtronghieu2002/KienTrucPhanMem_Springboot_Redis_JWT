package fit.iuh.edu.controllers;

import fit.iuh.edu.entities.Car;
import fit.iuh.edu.entities.User;
import fit.iuh.edu.repositories.UserRepository;
import fit.iuh.edu.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class userController {


    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserDao userService;


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

      if(token.isEmpty()){
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
        try {
            String apiUrl = "http://localhost:8081/car";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            System.out.println(token);

            HttpEntity<String> entity = new HttpEntity<>(headers);


            ParameterizedTypeReference<ArrayList<Car>> responseType = new ParameterizedTypeReference<ArrayList<Car>>() {
            };
            ResponseEntity<ArrayList<Car>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, responseType);
            ArrayList<Car> carList = response.getBody();
            System.out.println("carlist:"+carList);

            User user = userService.findUserById(id);

//            System.out.println("user:"+user);
            user.setCars(carList);
            userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e){
            System.out.println(e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }
}

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/abc")
    public User getdamn() {
        return userService.findUserById("1");
    }
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {

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
