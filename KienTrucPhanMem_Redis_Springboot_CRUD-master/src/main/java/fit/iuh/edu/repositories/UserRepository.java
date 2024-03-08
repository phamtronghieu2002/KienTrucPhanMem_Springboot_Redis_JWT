package fit.iuh.edu.repositories;

import fit.iuh.edu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}