package com.server.fitnessgym.model.dto;

import com.server.fitnessgym.model.enumerated.ETypeConcept;

public interface IPaymentValueTotalResult {
	
	ETypeConcept getTypeConcept();
	
	Long getTotalValue();

}
