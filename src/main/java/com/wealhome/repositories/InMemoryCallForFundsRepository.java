package com.wealhome.repositories;

import com.wealhome.models.CallForFunds;

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
}
