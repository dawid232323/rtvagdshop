package com.amadon.rtvagdshop.order.service;

import com.amadon.rtvagdshop.order.entity.OrderBuyerInformation;
import com.amadon.rtvagdshop.order.service.dto.OrderBuyerInformationDto;
import com.amadon.rtvagdshop.order.service.mapper.OrderBuyerInformationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class OrderBuyerInformationService
{
    private final OrderBuyerInformationMapper orderBuyerInformationMapper;

    /**
     * Updated buyer information. Partial update mechanism ignores null values.
     *
     * @param aBuyerInformation persisted buyer information
     *
     * @param aInformationDto dto with data to perform partial update
     */
    public void updateOrderBuyerInformation( final OrderBuyerInformation aBuyerInformation,
                                             final OrderBuyerInformationDto aInformationDto )
    {
        log.debug( "Partial updating order buyer info for values related with buyer information id {}",
                aBuyerInformation.getId() );
        orderBuyerInformationMapper.partialUpdate( aInformationDto, aBuyerInformation );
    }
}
