package market.eshop.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import market.eshop.domain.dto.CatalogSummary;
import market.eshop.domain.dto.QCatalogSummary;
import market.eshop.domain.form.ItemSearchForm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static market.eshop.domain.QItem.item;

@Repository
public class CatalogDao {

    private JPAQueryFactory queryFactory;

    public CatalogDao(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<CatalogSummary> searchItem(ItemSearchForm searchForm) {
        return queryFactory
                .select(new QCatalogSummary(item.id, item.imagePath, item.name, item.price))
                .from(item)
                .where(nameLike(searchForm.getName()), categoryEq(searchForm.getCategoryId()))
                .fetch();

    }

    private Predicate nameLike(String name) {
        if (name != null && name.length() > 0)
            return item.name.like("%" + name + "%");
        return null;
    }

    private Predicate categoryEq(Long categoryId) {
        if(categoryId != null)
            return item.categoryId.eq(categoryId);
        return null;
    }

}
