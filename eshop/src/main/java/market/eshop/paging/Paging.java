package market.eshop.paging;

import lombok.RequiredArgsConstructor;
import market.eshop.repository.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class Paging {

    private final ItemRepository itemRepository;

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String chart(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("items", itemRepository.findAllItemInfo(pageable));
        return "/index";
    }

}
