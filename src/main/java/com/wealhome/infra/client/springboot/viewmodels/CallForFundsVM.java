package com.wealhome.infra.client.springboot.viewmodels;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CallForFundsVM {

    public UUID id;
    public UUID condominiumId;
    public BigDecimal amount;
    public String quarter;
    public LocalDateTime occurredOn;

    public CallForFundsVM(UUID id, UUID condominiumId, BigDecimal amount, String quarter, LocalDateTime occurredOn) {
        this.id = id;
        this.condominiumId = condominiumId;
        this.amount = amount;
        this.quarter = quarter;
        this.occurredOn = occurredOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallForFundsVM that = (CallForFundsVM) o;
        return Objects.equals(id, that.id) && Objects.equals(condominiumId, that.condominiumId) && Objects.equals(amount, that.amount) && Objects.equals(quarter, that.quarter) && Objects.equals(occurredOn, that.occurredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, condominiumId, amount, quarter, occurredOn);
    }
}
