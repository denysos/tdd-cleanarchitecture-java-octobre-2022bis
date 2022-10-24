package com.wealhome.infra.repositories.jpa;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;

public record JpaCallForFundsRepository(
        SpringCallForFundsRepository springCallForFundsRepository) implements CallForFundsRepository {

    @Override
    public void save(CallForFunds callForFunds) {
        springCallForFundsRepository.save(callForFunds);
    }
}
