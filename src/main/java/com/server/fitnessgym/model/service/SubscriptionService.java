package com.server.fitnessgym.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.SubscriptionDAO;
import com.server.fitnessgym.model.entity.Membership;
import com.server.fitnessgym.model.entity.Subscription;

@Service
public class SubscriptionService extends GenericService<Subscription, Long> {
	
	private SubscriptionDAO dao;
	private MembershipService membershipService; 
	private PeriodicityService periodicityService; 
	
	public SubscriptionService(SubscriptionDAO dao, MembershipService membershipService, PeriodicityService periodicityService) {
		this.dao = dao;
		this.membershipService = membershipService;
		this.periodicityService = periodicityService;
	}

	@Override
	public JpaRepository<Subscription, Long> getDao() {
		return dao;
	}
	
	@Override
	public Subscription create(Subscription entity) {
		Optional<Membership> membershiOptional = membershipService.findByID(entity.getMembership().getId());
		Membership membership = membershiOptional.get();
		Integer period = periodicityService.findByID(membership.getId()).get().getCountMonth();

		LocalDate localDate = entity.getStartDate().toLocalDate(); 
		LocalDate expirationDate = localDate.plusMonths(period);
		
		entity.setPrice(membership.getPrice());
		entity.setExpirationDate(Date.valueOf(expirationDate));
		entity.setState(1);
		
		return super.create(entity);
	}
	
	public void renew(Subscription subscription) {
		Optional<Membership> membershiOptional = membershipService.findByID(subscription.getMembership().getId());
		Membership membership = membershiOptional.get();
		Integer period = periodicityService.findByID(membership.getId()).get().getCountMonth();
		
		LocalDate startDate = subscription.getExpirationDate().toLocalDate(); 
		LocalDate expirationDate = startDate.plusMonths(period);
		
		subscription.setStartDate(Date.valueOf(startDate));
		subscription.setExpirationDate(Date.valueOf(expirationDate));
		subscription.setState(1);
		
		getDao().save(subscription);
	}

}