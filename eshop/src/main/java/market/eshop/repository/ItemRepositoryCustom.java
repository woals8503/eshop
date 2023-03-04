package market.eshop.repository;

import market.eshop.domain.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<ItemDto> findAllItemInfo(Pageable pageable);

    Page<ItemDto> paging(Pageable pageable);
}
