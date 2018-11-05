package com.ktk.orca.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "orca_api_statistics")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@NoArgsConstructor
public class OrcaApiStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "api_request_time", columnDefinition = "DATETIME")
    @Basic
    private Instant apiRequestTime;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "api_called")
    private String apiCalled;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "api_method_called")
    private String apiMethodCalled;

    @Column(name = "call_duration")
    private Long callDuration;

    @Column(name = "response_code")
    private Integer responseCode;

    @Size(min = 2, max = 255)
    @Column(name = "response_message")
    private String responseMessage;

    @Column(name = "request_payload", columnDefinition = "LONGTEXT")
    private String requestPayload;

    @Column(name = "response_payload", columnDefinition = "LONGTEXT")
    private String responsePayload;

}
