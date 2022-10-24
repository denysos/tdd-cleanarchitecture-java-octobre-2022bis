package com.wealhome.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "condominiums")
public class Condominium {

    @Id
    private UUID id;
    private BigDecimal yearlyBudget;

    public Condominium(UUID id, BigDecimal yearlyBudget) {
        this.id = id;
        this.yearlyBudget = yearlyBudget;
    }

    public Condominium() {
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getYearlyBudget() {
        return yearlyBudget;
    }
}
