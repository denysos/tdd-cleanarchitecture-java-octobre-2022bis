package com.wealhome.businesslogic.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private LocalDateTime occurredOn;

    public CallForFunds(UUID id, UUID condominiumId, BigDecimal amount, int quarter, LocalDateTime occurredOn) {
        this.id = id;
        this.condominiumId = condominiumId;
        this.amount = amount;
        this.quarter = quarter;
        this.occurredOn = occurredOn;
    }

    public CallForFunds() {
    }

    public CallForFundsStateSnapshot takeSnapshot() {
        return new CallForFundsStateSnapshot(
                id,
                condominiumId,
                amount,
                quarter,
                occurredOn
        );
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

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public static class CallForFundsStateSnapshot {

        public UUID id;
        public UUID condominiumId;
        public BigDecimal amount;
        public int quarter;
        public LocalDateTime occurredOn;

        public CallForFundsStateSnapshot(UUID id, UUID condominiumId, BigDecimal amount, int quarter, LocalDateTime occurredOn) {
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
            CallForFundsStateSnapshot that = (CallForFundsStateSnapshot) o;
            return quarter == that.quarter && Objects.equals(id, that.id) && Objects.equals(condominiumId, that.condominiumId) && Objects.equals(amount, that.amount) && Objects.equals(occurredOn, that.occurredOn);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, condominiumId, amount, quarter, occurredOn);
        }

        @Override
        public String toString() {
            return "CallForFundsStateSnapshot{" +
                    "id=" + id +
                    ", condominiumId=" + condominiumId +
                    ", amount=" + amount +
                    ", quarter=" + quarter +
                    ", occurredOn=" + occurredOn +
                    '}';
        }
    }
}
