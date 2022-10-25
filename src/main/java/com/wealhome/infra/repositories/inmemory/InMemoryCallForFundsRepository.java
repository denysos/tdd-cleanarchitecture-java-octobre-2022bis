package com.wealhome.infra.repositories.inmemory;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCallForFundsRepository implements CallForFundsRepository {

    private List<CallForFunds> callsForFunds = new ArrayList<>();
    private boolean existingPastCallForFunds;

    @Override
    public void save(CallForFunds callForFunds) {
        callsForFunds.add(callForFunds);
    }

    @Override
    public boolean doSomePastCallForFundsExist() {
        return existingPastCallForFunds;
    }

    public List<CallForFunds> getCallsForFunds() {
        return callsForFunds;
    }

    public void feedWith(List<CallForFunds> callForFunds) {
        callsForFunds.addAll(callForFunds);
    }

    public void clear() {
        callsForFunds = new ArrayList<>();
    }

    public void setExistingPastCallForFunds() {
        this.existingPastCallForFunds = true;
    }
}
