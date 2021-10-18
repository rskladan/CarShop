package pl.coderslab.carshop.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loggedUser", "shoppingCart", "itemsList"})
public class UserController {

    private final UserService userService;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/home")
    public String home(){
        return "home";
    }

//    @GetMapping("/registration")
//    public ModelAndView registration(ModelAndView modelAndView){
//        modelAndView.addObject("user", new User());
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
//
//    @PostMapping("/registration")
//    public ModelAndView createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {
//        System.out.println(user);
//        User userExists = userService.findByUserName(user.getUsername());
//        User emailExists = userService.findUserByEmail(user.getEmail());
//        if (userExists != null) {
//            bindingResult.rejectValue("username", "error.user",
//                            "There is already a user registered with the user name provided");
//        }
//        if (emailExists != null) {
//            bindingResult.rejectValue("email", "error.user",
//                            "There is already a user registered with the email provided");
//        }
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.setViewName("redirect:/login");
//        }
//        return modelAndView;
//    }

    @GetMapping("/registration")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "/registration";
    }

    @PostMapping("/registration")
    public String add(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "/registration";
        }
        System.out.println(user);
        userService.saveUser(user);
        return "/login";
    }

    @GetMapping("/login")
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
        modelAndView.setViewName("/welcome");
        return modelAndView;
    }

    @GetMapping("/accountInfo")
    public ModelAndView getAccountInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accountInfo");
        return modelAndView;
    }

    @PostMapping("/accountInfo/{id}")
    public ModelAndView getAccountInfo(@Valid @ModelAttribute("loggedUser") User user, BindingResult bindingResult, @PathVariable(value="id") String id ) {
        ModelAndView modelAndView = new ModelAndView();
        User emailExists = userService.findUserByEmail(user.getEmail());

        if (emailExists != null && !emailExists.getId().equals(user.getId())) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("accountInfo");
        } else {
            userService.updateUser(user);
            modelAndView.setViewName("/welcome");
        }
        return modelAndView;
    }

    @ModelAttribute("userRoles")
    public List<String> userRoleList(){
        return Stream.of(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toList());
    }

}
