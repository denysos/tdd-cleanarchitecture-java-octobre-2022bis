package com.wealhome.businesslogic.repositories;

import com.wealhome.businesslogic.models.CallForFunds;

public interface CallForFundsRepository {

    void save(CallForFunds callForFunds);

    boolean doSomePastCallForFundsExist();
}
