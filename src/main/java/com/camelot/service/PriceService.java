package com.camelot.service;

import java.time.LocalDate;
import java.util.Set;

public interface PriceService {

	int getBasePrize(Set<Integer> guessedNumbers, Set<Integer> drawnNumbers);
	int applyBonusFor(LocalDate drawDate, int basePrize);
}
