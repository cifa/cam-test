package com.camelot.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

public interface LotteryService {

	List<LocalDate> getDrawDates(LocalDate endDate, Period period);
	Set<Integer> drawNumbers(int amount, int low, int max);
}
