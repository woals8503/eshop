package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.dao.CatalogDao;
import market.eshop.domain.dto.CatalogSummary;
import market.eshop.domain.form.ItemSearchForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogDao catalogDao;

    public List<CatalogSummary> getCatalog(ItemSearchForm searchForm) {
        return catalogDao.searchItem(searchForm);   // 아이템 정보를 조회
    }
}
