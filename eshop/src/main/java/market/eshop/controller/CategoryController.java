package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Member;
import market.eshop.domain.dto.CatalogSummary;
import market.eshop.domain.form.ItemSearchForm;
import market.eshop.repository.MemberRepository;
import market.eshop.service.CatalogService;
import market.eshop.service.CategoryService;
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
                              Model model) {
        /** category */
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

        if(itemForm == null)
            model.addAttribute("itemSearchForm", new ItemSearchForm());
        else
            model.addAttribute("itemSearchForm", itemForm);

        itemForm.setCategoryId(category);   // 아이템에 선택한 카테고리 번호를 넣어준다.
        List<CatalogSummary> items = catalogService.getCatalog(itemForm);   //그 아이템 정보를 전송
        model.addAttribute("items", items);


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
