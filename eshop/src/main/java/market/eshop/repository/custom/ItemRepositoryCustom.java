package market.eshop.repository.custom;

import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.form.ItemSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<ItemDto> findAllItemInfo(Pageable pageable, ItemSearchForm searchForm);

    ItemDto findByitem(Long itemId);
}
