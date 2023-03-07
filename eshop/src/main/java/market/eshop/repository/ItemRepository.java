package market.eshop.repository;

import market.eshop.domain.Item;
import market.eshop.repository.custom.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

}
