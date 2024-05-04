package com.amadon.rtvagdshop.order.service.creator;

import com.amadon.rtvagdshop.order.entity.ProductInfoSelectedVariant;
import com.amadon.rtvagdshop.product.entity.Product;

import java.util.List;

public interface OrderCodeCreateStrategy
{
    String getOrderCode( final Product aProduct, final List< ProductInfoSelectedVariant > aSelectedVariants );

    boolean isApplicable( final Product aProduct );
}
