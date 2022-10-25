package com.wealhome.businesslogic.usecases;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.models.DateProvider;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;
import com.wealhome.businesslogic.repositories.CondominiumRepository;

import java.util.UUID;

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
            CallForFunds callForFunds = condominium.determineNextCallForFunds(callForFundsId, dateProvider.timeNow());
            callForFundsRepository.save(callForFunds);
        });

    }

}
