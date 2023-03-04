package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Member;
import market.eshop.domain.dto.CatalogSummary;
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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CategoryController {

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
    public String getMainPage(@RequestParam(value = "category", required = false) Long category,
                              @ModelAttribute ItemSearchForm itemForm,
                              @CookieValue(name = "memberId", required = false) Long memberId,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              Model model) {
        /** category */
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

        PageRequest pageRequest = PageRequest.of(page, 6);

        Page<ItemDto> items = itemService.findAllItem(pageRequest);
        model.addAttribute("items", items);
        model.addAttribute("pages", items);
        model.addAttribute("maxPage", 5);
        int pageNumber = items.getPageable().getPageNumber();
        model.addAttribute("nowPage", (pageNumber+1));

        /** category */

        /** member */
        if(memberId == null) return "index";

        Optional<Member> loginMember = memberRepository.findById(memberId);
        if(loginMember == null) return "index";

        model.addAttribute("member", loginMember);
        /** member */
        return "index";
    }
}
