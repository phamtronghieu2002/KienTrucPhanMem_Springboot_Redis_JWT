package fit.iuh.edu;

import fit.iuh.edu.configs.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication

public class ThucHanhRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThucHanhRedisApplication.class, args);
    }

}
