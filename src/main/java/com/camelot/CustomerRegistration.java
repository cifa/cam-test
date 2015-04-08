package com.camelot;

import java.time.LocalDate;
import java.util.Set;

public class CustomerRegistration {
	
	private Set<Integer> numbers;
	private LocalDate endDate;

	public CustomerRegistration(String[] fromArray) {
		
	}
	
	public CustomerRegistration(LocalDate endDate, Set<Integer> numbers) {
		this.endDate = endDate;
		this.numbers = numbers;	
	}

}
