package market.eshop.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private Long parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

}
