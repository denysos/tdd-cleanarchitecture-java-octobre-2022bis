package com.wealhome.businesslogic.usecases;

import com.wealhome.infra.client.springboot.viewmodels.CallForFundsVM;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface CallForFundsVMPresenter {

    void setCallForFunds(UUID id, UUID condominiumId, BigDecimal amount, int quarter, LocalDateTime occurredOn);
}
