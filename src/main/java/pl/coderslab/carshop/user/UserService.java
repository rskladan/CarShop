package pl.coderslab.carshop.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.carshop.exception.UserAlreadyExistException;
import pl.coderslab.carshop.exception.UserWrongValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {


    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User userToUpdate = userRepository.getById(user.getId());
        userToUpdate.setId(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        user.setEnabled(true);
        return userRepository.save(userToUpdate);
    }

    private boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null ? true : false;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {return userRepository.findByEmail(email); }

    public User findByUserName(String username) {
        if(userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();
        } else {
            return null;
        }
    }

    public boolean validateUser(User user) throws UserWrongValidationException {

        if(findUserByEmail(user.getEmail()) != null && passwordEncoder.matches(user.getPassword(), findUserByEmail(user.getEmail()).getPassword())){
            return true;
        } else {
            throw new UserWrongValidationException("Wrong login or password");
        }

    }

}
