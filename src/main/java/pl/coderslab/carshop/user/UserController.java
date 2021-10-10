package pl.coderslab.carshop.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;

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

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUserName(user.getUsername());
        User emailExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (emailExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
        modelAndView.addObject("loggedUser", user);
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @GetMapping("/accountInfo")
    public ModelAndView getAccountInfo() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
        modelAndView.addObject("loggedUser", user);
        modelAndView.setViewName("accountInfo");
        return modelAndView;
    }

    @PostMapping("/accountInfo/{id}")
    public ModelAndView getAccountInfo(@Valid @ModelAttribute("loggedUser") User user, BindingResult result, @PathVariable(value="id") String id ) {
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.setViewName("accountInfo");
        } else {
            log.info("User details:" + user.toString());
            User loggedUser = userService.updateUser(user);
            modelAndView.addObject("loggedUser", loggedUser);
            modelAndView.setViewName("redirect:/welcome");
        }
        return modelAndView;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
        modelAndView.addObject("loggedUser", user);

        List<Item> allitems = itemRepository.findAll();
        modelAndView.addObject("allItems", allitems);
        modelAndView.setViewName("shoppingCart");


        return modelAndView;
    }
}
