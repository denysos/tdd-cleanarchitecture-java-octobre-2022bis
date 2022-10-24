package com.wealhome.services;

import com.wealhome.models.CallForFunds;
import com.wealhome.models.Condominium;
import com.wealhome.models.DateProvider;
import com.wealhome.repositories.SpringCallForFundsRepository;
import com.wealhome.repositories.SpringCondominiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class LaunchCallForFundsCommandHandler {

    @Autowired
    private SpringCallForFundsRepository callForFundsRepository;
    @Autowired
    private SpringCondominiumRepository condominiumRepository;

    @Autowired
    private DateProvider dateProvider;

    public void handle(UUID callForFundsId, UUID condominiumId) {
        Condominium condominium = condominiumRepository.findById(condominiumId).get();
        List<LocalDate> quarterLimits = List.of(
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 3, 31),
                LocalDate.of(2022, 6, 30),
                LocalDate.of(2022, 9, 30)
                );
        int currentQuarter = 0;
        for (LocalDate quarterLimit : quarterLimits)
            if (dateProvider.dateNow().isAfter(quarterLimit)) {
                currentQuarter++;
            }
        if(currentQuarter > 4)
            throw new IllegalStateException("Quarters are between 1 and 4");
        callForFundsRepository.save(new CallForFunds(
                callForFundsId,
                condominiumId,
                condominium.getYearlyBudget().divide(BigDecimal.valueOf(4), RoundingMode.CEILING),
                currentQuarter,
                dateProvider.timeNow()));

    }

}
