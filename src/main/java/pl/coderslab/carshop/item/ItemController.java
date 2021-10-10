package pl.coderslab.carshop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/allItems")
    public String findAllItems(Model model) {
        List<Item> allItems = itemRepository.findAll();
        model.addAttribute("allItems", allItems);
        return "item-list";
    }

}
