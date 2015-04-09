package com.camelot.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.camelot.service.LotteryService;
import com.camelot.transport.DrawResult;

public class WinWinLotteryService implements LotteryService {
	
	public static final int LOWER_BOUND = 1;
	public static final int UPPER_BOUND = 60; 
	public static final int NUMBERS_PER_DRAW = 6;
	
	private static final Random rnd = new Random();

	@Override
	public List<DrawResult> getDrawResultsForPeriod(LocalDate from, LocalDate to) {
		return generateDrawDates(from, to).stream()
				.map((date) -> new DrawResult(date, drawRandomNumbers()))
				.collect(
					Collectors.toList()
				);
	}
	
	List<LocalDate> generateDrawDates(LocalDate from, LocalDate to) {
		List<LocalDate> drawDates = new ArrayList<>();
		from = getFirstMonday(from);
		while(!from.isAfter(to)) {
			drawDates.add(from);
			from = from.plusWeeks(1);
		}
		return drawDates;
	}
	
	private Set<Integer> drawRandomNumbers() {
		return rnd.ints(LOWER_BOUND, UPPER_BOUND + 1)
				.distinct()
				.limit(NUMBERS_PER_DRAW)
				.boxed()
				.collect(
					Collectors.toSet()
				);
	}

	private LocalDate getFirstMonday(LocalDate from) {
		while(from.getDayOfWeek() != DayOfWeek.MONDAY) {
			from = from.plusDays(1);
		}
		return from;
	}

}
