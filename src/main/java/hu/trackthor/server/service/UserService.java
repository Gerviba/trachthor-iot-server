package hu.trackthor.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.trackthor.server.model.User;
import hu.trackthor.server.repo.UserRepository;

@Service
@Transactional
public class UserService {
 
    @Autowired
    UserRepository users;

    @Autowired
    HashingService hashing;

    public void addUser(String username, String password, boolean admin) {
        users.save(new User(null, username, hashing.hash(password), admin));
    }

    public void removeUser(Long id) {
        users.deleteById(id);
    }
    
    public void changePassword(Long id, String password) {
        User user = users.findById(id).get();
        user.setPassword(hashing.hash(password));
        users.save(user);
    }

    public void setAdmin(Long id, boolean admin) {
        User user = users.findById(id).get();
        user.setAdmin(admin);
        users.save(user);
    }
}
