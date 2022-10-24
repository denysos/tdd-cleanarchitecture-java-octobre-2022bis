package com.wealhome.repositories;

import com.wealhome.models.CallForFunds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCallForFundsRepository extends JpaRepository<CallForFunds, UUID> {
}
