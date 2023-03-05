package market.eshop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import market.eshop.domain.QCategory;
import market.eshop.domain.dto.ItemDto;
import market.eshop.domain.dto.QItemDto;
import market.eshop.domain.form.ItemSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static market.eshop.domain.QCategory.*;
import static market.eshop.domain.QItem.*;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemDto> findAllItemInfo(Pageable pageable, ItemSearchForm searchForm) {
        QueryResults<ItemDto> results = queryFactory
                .select(new QItemDto(
                        item.name,
                        item.imagePath,
                        item.price,
                        item.categoryId
                ))
                .from(item)
                .where(nameLike(searchForm.getName()), categoryEq(searchForm.getCategoryId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private Predicate nameLike(String name) {
        if(name != null && name.length() > 0)
            return item.name.like("%" + name + "%");
        return null;
    }

    private Predicate categoryEq(Long categoryId) {
        if(categoryId != null)
            return item.categoryId.eq(categoryId);
        return null;
    }


}
