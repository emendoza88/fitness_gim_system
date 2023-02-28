package com.server.fitnessgym.model.dto;

public class PaymentTotalValueByConcept {
	
	private long totalValueGym;
	
	private long totalValueStore;
	
	public Long getTotalValueGym() {
		return totalValueGym;
	}

	public void setTotalValueGym(long totalValueGym) {
		this.totalValueGym = totalValueGym;
	}

	public Long getTotalValueStore() {
		return totalValueStore;
	}

	public void setTotalValueStore(long totalValueStore) {
		this.totalValueStore = totalValueStore;
	}
	
}