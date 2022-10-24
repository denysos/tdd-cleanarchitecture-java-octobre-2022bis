package com.wealhome.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "calls_for_funds")
public class CallForFunds {

    @Id
    private UUID id;
    private UUID condominiumId;
    private BigDecimal amount;
    private int quarter;

    public CallForFunds(UUID id, UUID condominiumId, BigDecimal amount, int quarter) {
        this.id = id;
        this.condominiumId = condominiumId;
        this.amount = amount;
        this.quarter = quarter;
    }

    public CallForFunds() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallForFunds that = (CallForFunds) o;
        return quarter == that.quarter && Objects.equals(id, that.id) && Objects.equals(condominiumId, that.condominiumId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, condominiumId, amount, quarter);
    }

    public UUID getId() {
        return id;
    }

    public UUID getCondominiumId() {
        return condominiumId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getQuarter() {
        return quarter;
    }
}
