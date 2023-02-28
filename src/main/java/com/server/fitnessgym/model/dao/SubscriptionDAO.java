package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Subscription;

public interface SubscriptionDAO extends JpaRepository<Subscription, Long>{

}
