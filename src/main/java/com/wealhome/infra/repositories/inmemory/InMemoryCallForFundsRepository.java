package com.wealhome.infra.repositories.inmemory;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCallForFundsRepository implements CallForFundsRepository {

    private List<CallForFunds> callsForFunds = new ArrayList<>();

    @Override
    public void save(CallForFunds callForFunds) {
        callsForFunds.add(callForFunds);
    }

    public List<CallForFunds> getCallsForFunds() {
        return callsForFunds;
    }

    public void clear() {
        callsForFunds = new ArrayList<>();
    }
}
