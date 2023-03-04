package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Item;
import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.form.AddItemForm;
import market.eshop.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Page<ItemDto> findAllItem(Pageable pageable) {
        //Dto 변환
        Page<ItemDto> result = itemRepository.findAllItemInfo(pageable);

        return result;
    }

    public Page<ItemDto> paging(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page,10);
        //Dto 변환
        Page<ItemDto> result = itemRepository.paging(pageable);

        return result;
    }
}
