package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Item;
import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.form.AddItemForm;
import market.eshop.service.CategoryService;
import market.eshop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @GetMapping("/itemDetail")
    public String itemDetail(@RequestParam(value = "id") Long itemId,
                             Model model) {
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());
        ItemDto findItem = itemService.findByItem(itemId);
        model.addAttribute("item", findItem);
        return "itemDetail";
    }

}
