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

    public void handle(UUID callForFundsId, UUID condominiumId, CallForFundsVMPresenter presenter) {
        condominiumRepository.findById(condominiumId).ifPresent(condominium -> {
            boolean doSomePastCallForFundsExist = callForFundsRepository.doSomePastCallForFundsExist();
            CallForFunds callForFunds = condominium.determineNextCallForFunds(
                    callForFundsId,
                    doSomePastCallForFundsExist,
                    dateProvider.timeNow());
            callForFundsRepository.save(callForFunds);
            CallForFunds.CallForFundsStateSnapshot callForFundsStateSnapshot = callForFunds.takeSnapshot();
            presenter.setCallForFunds(
                    callForFundsStateSnapshot.id,
                    callForFundsStateSnapshot.condominiumId,
                    callForFundsStateSnapshot.amount,
                    callForFundsStateSnapshot.quarter,
                    callForFundsStateSnapshot.occurredOn
            );
        });

    }

}
