package com.wealhome.infra.client.springboot.controllers;

import com.wealhome.businesslogic.repositories.CallForFundsRepository;
import com.wealhome.businesslogic.usecases.LaunchCallForFundsCommandHandler;
import com.wealhome.infra.client.springboot.viewmodels.CallForFundsVM;
import com.wealhome.businesslogic.usecases.CallForFundsVMPresenter;
import com.wealhome.infra.client.springboot.viewmodels.VerboseCallForFundsVMPresenter;
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
    public ResponseEntity<CallForFundsVM> launchCallForFunds(
            @PathVariable UUID condominiumId,
            @PathVariable UUID callForFundsId
    ) {
        VerboseCallForFundsVMPresenter presenter = new VerboseCallForFundsVMPresenter();
        launchCallForFundsCommandHandler.handle(callForFundsId, condominiumId, presenter);
        return new ResponseEntity<>(presenter.getCallForFundsVM(), HttpStatus.CREATED);
    }

}
