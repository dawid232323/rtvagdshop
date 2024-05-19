package com.amadon.rtvagdshop.product.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductSearchResultDto
{
    private Long id;
    private String name;
    private Long basePrice;
    private String imageCoverPath;
    private List< CategoryPathDto > categoryPaths;
}
