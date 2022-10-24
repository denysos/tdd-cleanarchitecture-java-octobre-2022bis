package com.wealhome.businesslogic.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static java.math.BigDecimal.valueOf;

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

    public BigDecimal computeCallForFundAmount() {
        return yearlyBudget.divide(valueOf(4), RoundingMode.CEILING);
    }

    public UUID getId() {
        return id;
    }

}
