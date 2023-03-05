package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Item;
import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.form.AddItemForm;
import market.eshop.domain.form.ItemSearchForm;
import market.eshop.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(AddItemForm form) {
        Item item = Item.builder()
                .name(form.getName())
                .imagePath(form.getImagePath())
                .price(form.getPrice())
                .categoryId(form.getId())
                .stockQuantity(form.getStockQuantity())
                .build();
        Item newItem = itemRepository.save(item); //아이템 저장
        return newItem.getId();
    }

    public Page<ItemDto> findAllItem(Pageable pageable, ItemSearchForm searchForm) {
        //Dto 변환
        Page<ItemDto> result = itemRepository.findAllItemInfo(pageable, searchForm);

        return result;
    }

}
