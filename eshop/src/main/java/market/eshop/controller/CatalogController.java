package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Category;
import market.eshop.domain.Member;
import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.form.ItemSearchForm;
import market.eshop.repository.MemberRepository;
import market.eshop.service.CatalogService;
import market.eshop.service.CategoryService;
import market.eshop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CatalogController {

    private final CategoryService categoryService;
    private final CatalogService catalogService;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    /**
     * @param category
     * @param itemForm
     * @param memberId
     * @param model
     * @return
     */
    @GetMapping("/catalog")
    public String getMainPage(@RequestParam(value = "id", required = false) Long category,
                              @ModelAttribute ItemSearchForm searchForm,
                              @CookieValue(name = "memberId", required = false) Long memberId,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              Model model) {
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

        if(searchForm == null)
            model.addAttribute("itemSearchForm", new ItemSearchForm());
        else
            model.addAttribute("itemSearchForm", searchForm);

        searchForm.setCategoryId(category);

        PageRequest pageRequest = PageRequest.of(page, 6);

        Page<ItemDto> items = itemService.findAllItem(pageRequest, searchForm);
        int pageNumber = items.getPageable().getPageNumber();
        model.addAttribute("items", items);
        model.addAttribute("pages", items);
        model.addAttribute("maxPage", 5);
        model.addAttribute("nowPage", (pageNumber+1));

        if(memberId == null) return "index";

        Optional<Member> loginMember = memberRepository.findById(memberId);
        if(loginMember == null) return "index";

        model.addAttribute("member", loginMember);

        return "index";
    }
}
