package ru.company.data;

import org.springframework.data.repository.CrudRepository;
import ru.company.classes.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
