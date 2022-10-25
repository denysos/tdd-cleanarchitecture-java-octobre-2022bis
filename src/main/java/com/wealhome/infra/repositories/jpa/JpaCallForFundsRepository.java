package com.wealhome.infra.repositories.jpa;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;

import java.util.Optional;

public record JpaCallForFundsRepository(
        SpringCallForFundsRepository springCallForFundsRepository) implements CallForFundsRepository {

    @Override
    public void save(CallForFunds callForFunds) {
        springCallForFundsRepository.save(callForFunds);
    }

    @Override
    public boolean doSomePastCallForFundsExist() {
        return false;
    }
}
