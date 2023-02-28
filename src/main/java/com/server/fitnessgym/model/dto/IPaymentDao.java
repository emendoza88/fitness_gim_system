package com.server.fitnessgym.model.dto;

import java.sql.Timestamp;

import com.server.fitnessgym.model.enumerated.EPaymentMethod;
import com.server.fitnessgym.model.enumerated.ETypeConcept;

public interface IPaymentDao {

	Long getId();

	Timestamp getPaymentDate();

	ETypeConcept getTypeConcept();

	String getConcept();

	EPaymentMethod getPaymentMethod();

	Long getValue();

	String getName();

	String getSurname();

	String getEmail();

	String getPhone();

}