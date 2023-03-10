package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Member;
import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.form.ItemSearchForm;
import market.eshop.repository.MemberRepository;
import market.eshop.service.CatalogService;
import market.eshop.service.CategoryService;
import market.eshop.service.ItemService;
import market.eshop.web.session.SessionConst;
import market.eshop.web.session.SessionManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CatalogController {

    private final CategoryService categoryService;
    private final CatalogService catalogService;
    private final ItemService itemService;
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping("/catalog")
    public String getMainPage(@RequestParam(value = "id", required = false) Long category,
                              @ModelAttribute ItemSearchForm searchForm,
                              HttpServletRequest request,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, // μΈμ μμ± x
                              Model model) {
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

        searchForm.setCategoryId(category);

        PageRequest pageRequest = PageRequest.of(page, 6);

        Page<ItemDto> items = itemService.findAllItem(pageRequest, searchForm);
        int pageNumber = items.getPageable().getPageNumber();
        model.addAttribute("items", items);
        model.addAttribute("pages", items);
        model.addAttribute("maxPage", 5);
        model.addAttribute("nowPage", (pageNumber+1));

        /** λ‘κ·ΈμΈ 1 λ°©μ */
        //  μΈμ κ΄λ¦¬μμ μ μ₯λ νμ μ λ³΄ μ‘°ν
        //  Object session = sessionManager.getSession(request);
        //  μΈμ μμΌλ©΄ λ°λ‘ λ©μΈνλ©΄
        //  if(session == null) return "index";

        /** λ‘κ·ΈμΈ 2 λ°©μ */
        //  HttpSession session = request.getSession(false);
        //  if(session == null) return "index";
        //  Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        //  if(loginMember == null)
        //  return "index";

        model.addAttribute("member", loginMember);

        return "index";
    }

}
