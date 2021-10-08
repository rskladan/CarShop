package pl.coderslab.carshop.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.carshop.exception.UserAlreadyExistException;
import pl.coderslab.carshop.exception.UserWrongValidationException;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ItemRepository itemRepository;
    private PasswordEncoder passwordEncoder;
    private HttpSession httpSession;
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());

        return "register-form";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult result){

            try {
                userService.createUser(user);
            } catch (UserAlreadyExistException e) {
                result.rejectValue("email", "user.email", e.getMessage());
            }

        if(result.hasErrors()) {
            return "register-form";
        } else {
            return "loginPage";
        }
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("user", new User());
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(User user, Model model) {


        try {
            userService.validateUser(user);

        } catch (UserWrongValidationException e) {
            log.error(e.getMessage());
            model.addAttribute("error", "Wrong login or password");
            return "loginPage";
        }

        model.addAttribute("user", user);
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcomeUser(Model model) {

        List<Item> allItems = itemRepository.findAll();
        model.addAttribute("allItems", allItems);
        model.addAttribute("user", new User());

        
        return "welcome";
    }



}
