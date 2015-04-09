package com.camelot.service.impl;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class WinWinLotteryServiceTest {
	
	WinWinLotteryService service = new WinWinLotteryService();

	@Test
	public void shouldReturnMondaysInApr2015() {
		List<LocalDate> expectedDates = Lists.newArrayList(
				LocalDate.of(2015, 4, 6),
				LocalDate.of(2015, 4, 13),
				LocalDate.of(2015, 4, 20),
				LocalDate.of(2015, 4, 27)
			);
		
		LocalDate from = LocalDate.of(2015, 4, 1);
		LocalDate to = LocalDate.of(2015, 4, 30);
		
		List<LocalDate> generateDrawDates = service.generateDrawDates(from, to);
		
		assertEquals(expectedDates, generateDrawDates);
	}
	
	@Test
	public void bothFromAndToDatesAreInclusive() {
		List<LocalDate> expectedDates = Lists.newArrayList(
				LocalDate.of(2015, 4, 6),
				LocalDate.of(2015, 4, 13),
				LocalDate.of(2015, 4, 20),
				LocalDate.of(2015, 4, 27)
			);
		
		LocalDate from = LocalDate.of(2015, 4, 6);
		LocalDate to = LocalDate.of(2015, 4, 27);
		
		List<LocalDate> generateDrawDates = service.generateDrawDates(from, to);
		
		assertEquals(expectedDates, generateDrawDates);
	}

}
