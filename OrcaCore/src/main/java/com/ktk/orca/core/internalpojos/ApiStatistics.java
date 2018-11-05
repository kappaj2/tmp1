package com.ktk.orca.core.internalpojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiStatistics implements Serializable {

    private Instant apiRequestTime;
    private String apiCalled;
    private String apiMethodCalled;
    private Long callDuration;
    private Integer responseCode;
    private String responseMessage;
    private String requestPayload;
    private String responsePayload;
}
