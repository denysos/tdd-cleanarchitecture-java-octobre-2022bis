package com.wealhome.businesslogic.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static java.math.BigDecimal.valueOf;

public class Condominium {

    private UUID id;
    private BigDecimal yearlyBudget;

    public Condominium(UUID id, BigDecimal yearlyBudget) {
        this.id = id;
        this.yearlyBudget = yearlyBudget;
    }

    public static Condominium fromSnapshot(UUID id, BigDecimal yearlyBudget) {
        return new Condominium(id, yearlyBudget);
    }

    public CondominiumStateSnapshot takeSnapshot() {
        return new CondominiumStateSnapshot(
                this.id,
                this.yearlyBudget
        );
    }

    public CallForFunds determineNextCallForFunds(UUID callForFundsId, boolean doSomePastCallForFundsExist, LocalDateTime currentDateTime) {
        int currentQuarter = determineCurrentQuarter(currentDateTime);
        return new CallForFunds(
                callForFundsId,
                this.id,
                computeCallForFundAmount(doSomePastCallForFundsExist, currentQuarter),
                currentQuarter,
                currentDateTime);
    }

    public UUID getId() {
        return id;
    }

    private int determineCurrentQuarter(LocalDateTime currentDateTime) {
        int currentMonth = currentDateTime.toLocalDate().getMonth().getValue();
        return (currentMonth - 1) / 3 + 1;
    }

    private BigDecimal computeCallForFundAmount(boolean doSomePastCallForFundsExist, int currentQuarter) {
        BigDecimal classicalAmount = yearlyBudget.divide(valueOf(4), RoundingMode.CEILING);
        if (doSomePastCallForFundsExist || currentQuarter == 1)
            return classicalAmount;
        return classicalAmount.multiply(valueOf(2));
    }

    public static class CondominiumStateSnapshot {

        public UUID id;
        public BigDecimal yearlyBudget;

        public CondominiumStateSnapshot(UUID id, BigDecimal yearlyBudget) {
            this.id = id;
            this.yearlyBudget = yearlyBudget;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CondominiumStateSnapshot that = (CondominiumStateSnapshot) o;
            return Objects.equals(id, that.id) && Objects.equals(yearlyBudget, that.yearlyBudget);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, yearlyBudget);
        }
    }

}
