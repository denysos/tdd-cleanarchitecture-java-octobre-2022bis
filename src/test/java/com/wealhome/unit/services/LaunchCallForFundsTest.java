package com.wealhome.unit.services;

import com.wealhome.models.CallForFunds;
import com.wealhome.models.Condominium;
import com.wealhome.models.DeterministicDateProvider;
import com.wealhome.repositories.InMemoryCallForFundsRepository;
import com.wealhome.repositories.InMemoryCondominiumRepository;
import com.wealhome.services.LaunchCallForFundsCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class LaunchCallForFundsTest {

    private final InMemoryCondominiumRepository condominiumRepository = new InMemoryCondominiumRepository();
    private final InMemoryCallForFundsRepository callForFundsRepository = new InMemoryCallForFundsRepository();

    private final DeterministicDateProvider dateProvider = new DeterministicDateProvider();

    private final UUID condominiumId = UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2");
    private final UUID callForFundsId = UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5");

    @Nested
    public class ExistingCondominium {

        @BeforeEach
        void setup() {
            declareExistingCondominium(10000);
        }

        @Test
        void shouldLaunchTheFirstCallOfTheFiscalYear() {
            assertCallForFundsLaunched(2020, 1, 2500, 1);
            assertCallForFundsLaunched(2020, 3, 2500, 1);
            assertCallForFundsLaunched(2020, 4, 2500, 2);
            assertCallForFundsLaunched(2021, 7, 2500, 3);
        }

    }

    private void assertCallForFundsLaunched(int fiscalYear,
                                            int currentMonth,
                                            int expectedAmount,
                                            int expectedQuarter) {
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
                BigDecimal.valueOf(yearlyBudget)
        ));
    }

    private void launchCallForFunds(UUID callForFundsId, UUID condominiumId) {
        new LaunchCallForFundsCommandHandler(condominiumRepository, callForFundsRepository, dateProvider)
                .handle(callForFundsId, condominiumId);
    }

    private void _assertCallForFundsLaunched(int amount, int quarter) {
        assertThat(callForFundsRepository.getCallsForFunds()).containsExactly(
                new CallForFunds(
                        callForFundsId,
                        condominiumId,
                        BigDecimal.valueOf(amount),
                        quarter,
                        dateProvider.timeNow()
                )
        );
    }
}
