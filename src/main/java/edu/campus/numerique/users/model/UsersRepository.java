package edu.campus.numerique.users.model;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    List<Users> findAll();
}
