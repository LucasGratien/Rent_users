package edu.campus.numerique.users.service;
import edu.campus.numerique.users.model.Users;
import edu.campus.numerique.users.model.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import edu.campus.numerique.users.model.UserNotFound;
import java.util.List;
import java.util.Optional;


@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> findById(int id) {

        return usersRepository.findById(id);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public Users update(int id, Users users) {
        if (!usersRepository.existsById(id)) {
            throw new UserNotFound();
        }
        users.setId(id);
        return usersRepository.save(users);
    }

    public void deleteById(int id) {
        usersRepository.deleteById(id);
    }
}

