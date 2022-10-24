package com.wealhome.services;

import com.wealhome.models.CallForFunds;
import com.wealhome.models.Condominium;
import com.wealhome.models.DateProvider;
import com.wealhome.models.DeterministicDateProvider;
import com.wealhome.repositories.CallForFundsRepository;
import com.wealhome.repositories.CondominiumRepository;
import com.wealhome.repositories.SpringCallForFundsRepository;
import com.wealhome.repositories.SpringCondominiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.*;

public class LaunchCallForFundsCommandHandler {

    private final CondominiumRepository condominiumRepository;
    private final CallForFundsRepository callForFundsRepository;

    private final DateProvider dateProvider;

    public LaunchCallForFundsCommandHandler(CondominiumRepository condominiumRepository,
                                            CallForFundsRepository callForFundsRepository,
                                            DateProvider dateProvider) {
        this.condominiumRepository = condominiumRepository;
        this.callForFundsRepository = callForFundsRepository;
        this.dateProvider = dateProvider;
    }

    public void handle(UUID callForFundsId, UUID condominiumId) {
        condominiumRepository.findById(condominiumId).ifPresent(condominium -> {
            callForFundsRepository.save(new CallForFunds(
                    callForFundsId,
                    condominiumId,
                    valueOf(2500),
                    determineCurrentQuarter(),
                    dateProvider.timeNow()));
        });

    }

    private int determineCurrentQuarter() {
        int currentMonth = dateProvider.dateNow().getMonth().getValue();
        if(currentMonth > 9) {
            return 4;
        }
        if(currentMonth > 6)
            return 3;
        if(currentMonth > 3)
            return 2;
        return 1;
    }

}
