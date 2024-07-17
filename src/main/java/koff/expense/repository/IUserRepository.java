package koff.expense.repository;

import koff.expense.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String>{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
