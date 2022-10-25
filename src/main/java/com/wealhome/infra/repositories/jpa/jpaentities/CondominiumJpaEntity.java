package com.wealhome.infra.repositories.jpa.jpaentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "condominiums")
public class CondominiumJpaEntity {

    @Id
    private UUID id;
    private BigDecimal yearlyBudget;

    public CondominiumJpaEntity(UUID id, BigDecimal yearlyBudget) {
        this.id = id;
        this.yearlyBudget = yearlyBudget;
    }

    public CondominiumJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getYearlyBudget() {
        return yearlyBudget;
    }
}
