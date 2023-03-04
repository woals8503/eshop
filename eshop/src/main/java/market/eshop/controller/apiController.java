package market.eshop.controller;

import market.eshop.domain.dto.ItemDto;
import market.eshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@Transactional
public class apiController {

    @Autowired
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;


    @GetMapping("/test/page")
    public Page<ItemDto> pageTest() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<ItemDto> result = itemRepository.findAllItemInfo(pageRequest);
        for (ItemDto itemDto : result) {
            System.out.println("itemDto = " + itemDto);
        }
        return result;
    }
}
