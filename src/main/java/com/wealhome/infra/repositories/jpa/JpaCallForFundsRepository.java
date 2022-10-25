package com.wealhome.infra.repositories.jpa;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;
import com.wealhome.infra.repositories.jpa.jpaentities.CallForFundsJpaEntity;

public record JpaCallForFundsRepository(
        SpringCallForFundsRepository springCallForFundsRepository) implements CallForFundsRepository {

    @Override
    public void save(CallForFunds callForFunds) {
        CallForFunds.CallForFundsStateSnapshot callForFundsStateSnapshot = callForFunds.takeSnapshot();
        springCallForFundsRepository.save(
                new CallForFundsJpaEntity(
                        callForFundsStateSnapshot.id,
                        callForFundsStateSnapshot.condominiumId,
                        callForFundsStateSnapshot.amount,
                        callForFundsStateSnapshot.quarter,
                        callForFundsStateSnapshot.occurredOn
                ));
    }

    @Override
    public boolean doSomePastCallForFundsExist() {
        return false;
    }
}
