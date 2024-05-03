package com.amadon.rtvagdshop.exception.service.dto;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse
{
    private String uuid;
    private DomainArea area;
    private ReasonCode reasonCode;
    private int requestStatus;
    private String message;
}
