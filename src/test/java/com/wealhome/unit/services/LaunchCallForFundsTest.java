package com.wealhome.unit.services;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.models.Condominium;
import com.wealhome.businesslogic.models.DeterministicDateProvider;
import com.wealhome.infra.client.springboot.viewmodels.CallForFundsVM;
import com.wealhome.businesslogic.usecases.CallForFundsVMPresenter;
import com.wealhome.infra.repositories.inmemory.InMemoryCallForFundsRepository;
import com.wealhome.infra.repositories.inmemory.InMemoryCondominiumRepository;
import com.wealhome.businesslogic.usecases.LaunchCallForFundsCommandHandler;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class LaunchCallForFundsTest {

    private final InMemoryCondominiumRepository condominiumRepository = new InMemoryCondominiumRepository();
    private final InMemoryCallForFundsRepository callForFundsRepository = new InMemoryCallForFundsRepository();

    private final DeterministicDateProvider dateProvider = new DeterministicDateProvider();

    private final PassiveCallForFundsVMPresenter callForFundsVMPresenter = new PassiveCallForFundsVMPresenter();

    private final UUID condominiumId = UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2");
    private final UUID callForFundsId = UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5");

    @Nested
    public class ExistingCondominium {

        @Test
        void shouldLaunchTheCallOfTheCurrentQuarter() {
            declareExistingCondominium(10000);
            assertCallForFundsLaunched(2020, 1, 2500, 1);
            assertCallForFundsLaunched(2020, 3, 2500, 1);
            assertCallForFundsLaunched(2020, 4, 2500, 2);
            assertCallForFundsLaunched(2021, 7, 2500, 3);
            assertCallForFundsLaunched(2021, 11, 2500, 4);
        }

        @Test
        void shouldLaunchACallExpectingTheFourthOfTheYearlyBudget() {
            declareExistingCondominium(10403);
            assertCallForFundsLaunched(2020, 1, 2601, 1);
        }

        @Test
        void shouldCumulateTheBudgetOfMissedCallForFunds() {
            declareExistingCondominium(10000);
            simulateCurrentDate(2020, 4);
            launchCallForFunds(callForFundsId, condominiumId);
            _assertCallForFundsLaunched(5000, 2);
        }

        @Test
        void shouldPresentTheLaunchedCallForFunds() {
            declareExistingCondominium(10000);
            simulateCurrentDate(2020, 1);
            launchCallForFunds(callForFundsId, condominiumId);
            assertThat(callForFundsVMPresenter.getId()).isEqualTo(callForFundsId);
            assertThat(callForFundsVMPresenter.getCondominiumId()).isEqualTo(condominiumId);
            assertThat(callForFundsVMPresenter.getAmount()).isEqualTo( valueOf(2500));
            assertThat(callForFundsVMPresenter.getQuarter()).isEqualTo(1);
            assertThat(callForFundsVMPresenter.getQuarter()).isEqualTo(1);
        }

    }

    private void assertCallForFundsLaunched(int fiscalYear,
                                            int currentMonth,
                                            int expectedAmount,
                                            int expectedQuarter) {
        callForFundsRepository.clear();
        callForFundsRepository.setExistingPastCallForFunds();
        simulateCurrentDate(fiscalYear, currentMonth);
        launchCallForFunds(callForFundsId, condominiumId);
        _assertCallForFundsLaunched(expectedAmount, expectedQuarter);
    }

    private void simulateCurrentDate(int year, int month) {
        dateProvider.setTime(LocalDateTime.of(year, month, 3, 14, 15, 3));
    }

    private void declareExistingCondominium(int yearlyBudget) {
        condominiumRepository.feedWith(new Condominium(
                condominiumId,
                valueOf(yearlyBudget)
        ));
    }

    private void launchCallForFunds(UUID callForFundsId, UUID condominiumId) {
        new LaunchCallForFundsCommandHandler(condominiumRepository, callForFundsRepository, dateProvider)
                .handle(callForFundsId, condominiumId, callForFundsVMPresenter);
    }

    private void _assertCallForFundsLaunched(int amount, int quarter) {
        assertThat(callForFundsRepository.getCallsForFunds().stream().map(CallForFunds::takeSnapshot)
                .collect(Collectors.toList())).containsExactly(
                new CallForFunds.CallForFundsStateSnapshot(
                        callForFundsId,
                        condominiumId,
                        valueOf(amount),
                        quarter,
                        dateProvider.timeNow()
                ));
    }

    private static class PassiveCallForFundsVMPresenter implements CallForFundsVMPresenter {

        private UUID id;
        private UUID condominiumId;
        private BigDecimal amount;
        private int quarter;
        private LocalDateTime occurredOn;

        @Override
        public void setCallForFunds(UUID id, UUID condominiumId, BigDecimal amount, int quarter, LocalDateTime occurredOn) {
            this.id = id;
            this.condominiumId = condominiumId;
            this.amount = amount;
            this.quarter = quarter;
            this.occurredOn = occurredOn;
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
    }

}

