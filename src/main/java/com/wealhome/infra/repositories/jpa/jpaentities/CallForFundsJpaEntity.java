package com.wealhome.infra.repositories.jpa.jpaentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "calls_for_funds")
public class CallForFundsJpaEntity {

    @Id
    private UUID id;
    private UUID condominiumId;
    private BigDecimal amount;
    private int quarter;
    private LocalDateTime occurredOn;

    public CallForFundsJpaEntity(UUID id, UUID condominiumId, BigDecimal amount, int quarter, LocalDateTime occurredOn) {
        this.id = id;
        this.condominiumId = condominiumId;
        this.amount = amount;
        this.quarter = quarter;
        this.occurredOn = occurredOn;
    }

    public CallForFundsJpaEntity() {
    }
}
