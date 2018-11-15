package hu.trackthor.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.trackthor.server.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByName(String name);
    
}
