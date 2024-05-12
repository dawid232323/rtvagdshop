package com.amadon.rtvagdshop.product.features.description.service.mapper;

import com.amadon.rtvagdshop.product.features.description.entity.ProductDescription;
import com.amadon.rtvagdshop.product.features.description.service.mapper.contentConverter.ProductDescriptionContentConverter;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDescriptionMapperImpl implements ProductDescriptionMapper
{

    final List< ProductDescriptionContentConverter > contentConverters;

    @Override
    public String mapToHtml( final ProductDescription aProductDescription )
    {
        final Document parsedHtml = Jsoup.parse( aProductDescription.getDescription() );
        contentConverters.forEach( converter -> converter.convertContent( parsedHtml ) );
        return parsedHtml.html();
    }
}
