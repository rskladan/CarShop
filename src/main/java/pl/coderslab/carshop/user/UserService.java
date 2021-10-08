package pl.coderslab.carshop.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.carshop.exception.UserAlreadyExistException;
import pl.coderslab.carshop.exception.UserWrongValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final Validator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public UserService(UserRepository userRepository, Validator validator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) throws UserAlreadyExistException {

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (checkIfUserExist(user.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        } else if (violations.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    private boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null ? true : false;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public boolean validateUser(User user) throws UserWrongValidationException {


        if(findUserByEmail(user.getEmail()) != null && passwordEncoder.matches(user.getPassword(), findUserByEmail(user.getEmail()).getPassword())){
            return true;
        } else {
            throw new UserWrongValidationException("Wrong login or password");
        }

    }

}
