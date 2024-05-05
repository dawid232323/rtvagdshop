package com.amadon.rtvagdshop.exception.service.dto;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse
{
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();
    @Builder.Default
    private Instant timestamp = Instant.now();
    private DomainArea area;
    private ReasonCode reasonCode;
    private int requestStatus;
    private String message;
    private Map<String, String> errorDetails;
}
