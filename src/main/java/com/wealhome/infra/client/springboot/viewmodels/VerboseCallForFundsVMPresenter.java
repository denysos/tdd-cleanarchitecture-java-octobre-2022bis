package com.wealhome.infra.client.springboot.viewmodels;

import com.wealhome.businesslogic.usecases.CallForFundsVMPresenter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class VerboseCallForFundsVMPresenter implements CallForFundsVMPresenter {

    private CallForFundsVM callForFundsVM;

    @Override
    public void setCallForFunds(UUID id, UUID condominiumId, BigDecimal amount, int quarter, LocalDateTime occurredOn) {
        callForFundsVM = new CallForFundsVM(
                id,
                condominiumId,
                amount,
                "T" + quarter,
                occurredOn
        );
    }

    public CallForFundsVM getCallForFundsVM() {
        return callForFundsVM;
    }
}
