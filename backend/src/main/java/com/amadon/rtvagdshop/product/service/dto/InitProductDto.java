package com.amadon.rtvagdshop.product.service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitProductDto implements Serializable
{
    @NotNull
    @Size( min = 1, max = 500 )
    private String displayName;

    @NotNull
    @Min( 1 )
    @Max( 10000000 )
    private Long basePrice;

    @NotNull
    @Size( min = 1 )
    private List< Long > categoryIds;
}
