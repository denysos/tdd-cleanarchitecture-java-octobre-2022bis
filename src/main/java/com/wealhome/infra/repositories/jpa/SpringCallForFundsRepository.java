package com.wealhome.infra.repositories.jpa;

import com.wealhome.businesslogic.models.CallForFunds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCallForFundsRepository extends JpaRepository<CallForFunds, UUID> {
}
