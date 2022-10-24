package com.wealhome.infra.client.springboot.controllers;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;
import com.wealhome.infra.repositories.jpa.JpaCallForFundsRepository;
import com.wealhome.businesslogic.usecases.LaunchCallForFundsCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CallForFundsController {

    private final LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler;
    private final CallForFundsRepository callForFundsRepository;

    public CallForFundsController(CallForFundsRepository callForFundsRepository,
                                  LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler) {
        this.launchCallForFundsCommandHandler = launchCallForFundsCommandHandler;
        this.callForFundsRepository = callForFundsRepository;
    }

    @PostMapping(path = "/condominiums/{condominiumId}/callsforfunds/{callForFundsId}", consumes = "application/json",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<CallForFunds> launchCallForFunds(
            @PathVariable UUID condominiumId,
            @PathVariable UUID callForFundsId
    ) {
        launchCallForFundsCommandHandler.handle(callForFundsId, condominiumId);
        return new ResponseEntity<>(((JpaCallForFundsRepository)callForFundsRepository)
                .springCallForFundsRepository().findById(callForFundsId).get(), HttpStatus.CREATED);
    }

}
