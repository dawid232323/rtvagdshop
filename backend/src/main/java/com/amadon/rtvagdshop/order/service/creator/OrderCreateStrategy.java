package com.amadon.rtvagdshop.order.service.creator;

import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;

public interface OrderCreateStrategy
{
    Order create( OrderCreateDto aCreateDto );

    boolean isApplicable();
}
