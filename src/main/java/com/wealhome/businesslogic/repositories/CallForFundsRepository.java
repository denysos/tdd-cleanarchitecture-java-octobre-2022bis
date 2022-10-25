package com.wealhome.businesslogic.repositories;

import com.wealhome.businesslogic.models.CallForFunds;

import java.util.Optional;

public interface CallForFundsRepository {

    void save(CallForFunds callForFunds);

    boolean doSomePastCallForFundsExist();
}
