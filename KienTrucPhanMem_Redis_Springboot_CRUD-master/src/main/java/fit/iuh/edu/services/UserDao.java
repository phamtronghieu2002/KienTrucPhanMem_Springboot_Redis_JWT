package  fit.iuh.edu.services;
import fit.iuh.edu.entities.User;
import fit.iuh.edu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDao {
    public static final String HASH_KEY = "User";
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RedisTemplate template;

    public User save(User user){
        template.opsForHash().put(HASH_KEY,user.getId(),user);
        userRepository.save(user);
        return user;
    }

    public List<User> findAll() {
       return  template.opsForHash().values(HASH_KEY);



    }

    public User findUserById(String id){
        return (User) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteUser(String id){
        template.opsForHash().delete(HASH_KEY,id);
        userRepository.deleteById(id);
        return "User removed !!";
    }
}