package com.wealhome.springboot.controllers;

import com.wealhome.repositories.SpringCallForFundsRepository;
import com.wealhome.models.CallForFunds;
import com.wealhome.services.LaunchCallForFundsCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CallForFundsController {

    @Autowired
    private LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler;
    @Autowired
    private SpringCallForFundsRepository callForFundsRepository;

    @PostMapping(path = "/condominiums/{condominiumId}/callsforfunds/{callForFundsId}", consumes = "application/json",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<CallForFunds> launchCallForFunds(
            @PathVariable UUID condominiumId,
            @PathVariable UUID callForFundsId
    ) {
        launchCallForFundsCommandHandler.handle(callForFundsId, condominiumId);
        return new ResponseEntity<>(callForFundsRepository.findById(callForFundsId).get(), HttpStatus.CREATED);
    }

}
