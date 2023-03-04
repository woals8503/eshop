package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.dto.CategoryDto;
import market.eshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createCategoryRoot() {
        //모든 카테고리 가져온후 Dto로 변환하고, 부모 아이디별로 grouping
        Map<Long, List<CategoryDto>> groupParentId = categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryDto(c.getId(), c.getName(), c.getParentId()))
                .collect(groupingBy(d -> d.getParentId()));
        //부모 생성
        CategoryDto rootCategoryDto = new CategoryDto(0l, "ROOT", null);
        addSubCategories(rootCategoryDto, groupParentId);
        return rootCategoryDto;
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupParentId) {
        List<CategoryDto> subCategories = groupParentId.get(parent.getId());

        // 종료 조건
        if(subCategories == null)
            return;
        
        //부모카테고리에 sub카테고리 셋팅
        parent.setSubCategories(subCategories);

        subCategories.stream()
                .forEach(s -> {
                   addSubCategories(s, groupParentId);
                });
    }
}
