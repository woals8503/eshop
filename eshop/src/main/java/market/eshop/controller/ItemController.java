package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.form.AddItemForm;
import market.eshop.service.CategoryService;
import market.eshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @GetMapping("/addItem")
    public String createItem(Model model) {

        model.addAttribute("rootCategory", categoryService.createCategoryRoot());
        model.addAttribute("form", new AddItemForm());
        return "addItem";
    }

    @PostMapping("/addItem")
    public String updateItem(@ModelAttribute @Valid AddItemForm addItemForm) {
        Long item = itemService.saveItem(addItemForm);
        return "redirect:/catalog";
    }
}
