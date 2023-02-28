package com.server.fitnessgym.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.server.fitnessgym.model.dto.ISubscriptionResultSet;
import com.server.fitnessgym.model.entity.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Long>{
	
	@Query(value = "SELECT c.id userId, c.name, c.surname, c.username, c.email, c.phone, s.state, s.start_date startDate, s.expiration_date expirationDate, m.name nameMembership "
			+ "FROM customer c "
			+ "LEFT JOIN subscription s ON c.id = s.customer_id "
			+ "LEFT JOIN membership m ON m.id = s.membership_id "
			+ "ORDER BY c.name", nativeQuery = true)
	public List<ISubscriptionResultSet> findSubscriptionClient();
	
	@Query(value = "SELECT c.id userId, c.name, c.surname, m.name nameMembership, s.price priceSubscription, s.id idSubscription "
			+ "FROM customer c "
			+ "INNER JOIN subscription s ON c.id = s.customer_id "
			+ "INNER JOIN membership m ON m.id = s.membership_id "
			+ "WHERE c.id = ?1", nativeQuery = true)
	public ISubscriptionResultSet getSubscriptionClient(Long user);

}