package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.form.AddItemForm;
import market.eshop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;

    @GetMapping("/addItem")
    public String createItem(Model model) {

        model.addAttribute("rootCategory", categoryService.createCategoryRoot());
        model.addAttribute("form", new AddItemForm());
        return "addItem";
    }

//    @PostMapping("/addItem")
//    public String updateItem() {
//
//        return null;
//    }
}
