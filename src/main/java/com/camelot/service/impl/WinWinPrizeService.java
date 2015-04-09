package com.camelot.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import com.camelot.service.PrizeService;
import com.camelot.transport.DrawResult;
import com.camelot.transport.Prize;
import com.google.common.collect.Sets;

public class WinWinPrizeService implements PrizeService {
	
	private static final int JACKPOT_BASE = 10000;
	private static final int PRIZE_PER_NUMBER = 1000;
	private static final int PRIZE_THRESHOLD = 3;

	@Override
	public Prize calculatePrizeFor(DrawResult res, Set<Integer> guessNumbers) {
		int basePrize;
		Set<Integer> correctGuesses = getCorrectGuesses(res.getNumbersDrawn(), guessNumbers);
		if (correctGuesses.equals(res.getNumbersDrawn())) {
			basePrize = calculateJackpot(correctGuesses);
		} else if (correctGuesses.size() >= PRIZE_THRESHOLD) {
			basePrize = calculateRegularPrize(correctGuesses, res.getNumbersDrawn());
		} else {
			basePrize = calculateConsolationPrize(res.getNumbersDrawn());
		}
		return new Prize(withBonus(res.getDrawDate(),basePrize));
	}

	private Set<Integer> getCorrectGuesses(Set<Integer> numbersDrawn, Set<Integer> guessNumbers) {
		return Sets.intersection(numbersDrawn, guessNumbers);
	}

	private int calculateRegularPrize(Set<Integer> correctGuesses,
			Set<Integer> numbersDrawn) {
		int basePrize = PRIZE_PER_NUMBER * correctGuesses.size();
		int bonus = Sets.difference(numbersDrawn, correctGuesses).stream()
				.mapToInt(Integer::intValue)
				.reduce((left, right) -> left * right)
				.orElse(0);
		return basePrize + bonus;
	}
	
	private int calculateJackpot(Set<Integer> correctGuesses) {
		return correctGuesses.stream()
				.mapToInt((num) -> num * JACKPOT_BASE)
				.sum();
	}

	private int calculateConsolationPrize(Set<Integer> drawnNumbers) {
		return drawnNumbers.stream()
				.mapToInt(Integer::intValue)
				.sum();	
	}
	
	private int withBonus(LocalDate drawDate, int basePrize) {
		int coef = 1;
		if (drawDate.isLeapYear() && drawDate.getMonth() == Month.FEBRUARY) {
			if (drawDate.getDayOfMonth() == 29) {
				coef++;
			}
			coef++;
		}
		return basePrize * coef;
	}

}
