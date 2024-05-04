package com.amadon.rtvagdshop.order.service.creator.impl;

import com.amadon.rtvagdshop.order.service.creator.OrderCodeCreateStrategy;
import com.amadon.rtvagdshop.product.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuestClientOrderCreateStrategy extends AbstractOrderCreateStrategy
{
    public GuestClientOrderCreateStrategy( final ProductService productService,
                                           final List< OrderCodeCreateStrategy > codeCreateStrategies )
    {
        super( productService, codeCreateStrategies );
    }

    @Override
    public boolean isApplicable()
    {
        return true;
    }
}
