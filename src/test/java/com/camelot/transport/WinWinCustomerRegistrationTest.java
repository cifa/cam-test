package com.camelot.transport;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.camelot.exception.RegistrationException;
import com.google.common.collect.Sets;

public class WinWinCustomerRegistrationTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void processesValidInput() {
		LocalDate expectedEndDate = LocalDate.of(2014, 12, 5);
		LocalDate expectedStartDate = expectedEndDate.minusMonths(6);
		Set<Integer> expectedGuessNumbers = Sets.newHashSet(1, 6, 13, 21, 34, 60);
		
		WinWinLotteryCustomerRegistration reg = 
				new WinWinLotteryCustomerRegistration(new String[] {"05/12/2014", "1", "6", "13", "21", "34", "60"});
		
		assertEquals("Start Date", expectedStartDate, reg.getStartDate());
		assertEquals("End Date", expectedEndDate, reg.getEndDate());
		assertEquals("Guess", expectedGuessNumbers, reg.getGuessNumbers());
	}
	
	@Test
	public void throwsIfDateFormatIncorrect() {
		expectedException.expect(RegistrationException.class);
		
		new WinWinLotteryCustomerRegistration(new String[] {"05-12-2014", "5", "6", "13", "21", "34", "59"});
	}
	
	@Test
	public void throwsIfNumbersOutOfBounds() {
		expectedException.expect(RegistrationException.class);
		
		new WinWinLotteryCustomerRegistration(new String[] {"05/12/2014", "0", "6", "13", "21", "34", "59"});
	}

	@Test
	public void throwsIfNumbersNotUnique() {
		expectedException.expect(RegistrationException.class);
		
		new WinWinLotteryCustomerRegistration(new String[] {"05/12/2014", "1", "1", "13", "21", "34", "59"});
	}
	
	@Test
	public void throwsIfTooFewRegDetails() {
		expectedException.expect(RegistrationException.class);
		
		new WinWinLotteryCustomerRegistration(new String[] {"05/12/2014", "6", "13", "21", "34", "59"});
	}
	
	@Test
	public void throwsIfTooManyRegDetails() {
		expectedException.expect(RegistrationException.class);
		
		new WinWinLotteryCustomerRegistration(new String[] {"05/12/2014", "1", "6", "8", "13", "21", "34", "59"});
	}
}
