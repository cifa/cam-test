package com.camelot.service.impl;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Test;

import com.camelot.transport.DrawResult;
import com.camelot.transport.Prize;
import com.google.common.collect.Sets;

public class WinWinPrizeServiceTest {
	
	LocalDate nonLeapYearDate = LocalDate.of(2015, 2, 20);
	LocalDate LeapYearNotFebDate = LocalDate.of(2016, 4, 20);
	LocalDate LeapYearInFebDate = LocalDate.of(2016, 2, 22);
	LocalDate LeapYear29thFebDate = LocalDate.of(2016, 2, 29);
	
	Set<Integer> drawnNums = Sets.newHashSet(1, 2, 3, 4, 5, 10);
	
	WinWinPrizeService service = new WinWinPrizeService();

	@Test
	public void noCorrectGuessesGetConsolationPrize() {
		Set<Integer> guesses = Sets.newHashSet(11, 21, 31, 41, 51, 60);
		DrawResult drawResult = new DrawResult(nonLeapYearDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 25, prize.getValue());
	}
	
	@Test
	public void twoCorrectGuessesGetConsolationPrize() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 31, 41, 51, 60);
		DrawResult drawResult = new DrawResult(nonLeapYearDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 25, prize.getValue());
	}

	@Test
	public void threeCorrectGuessesGetRegularPrize() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 41, 51, 60);
		DrawResult drawResult = new DrawResult(nonLeapYearDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 3200, prize.getValue());
	}
	
	@Test
	public void fiveCorrectGuessesGetRegularPrize() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 4, 5, 60);
		DrawResult drawResult = new DrawResult(nonLeapYearDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 5010, prize.getValue());
	}
	
	@Test
	public void sixCorrectGuessesGetJackpot() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 4, 5, 10);
		DrawResult drawResult = new DrawResult(nonLeapYearDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 250000, prize.getValue());
	}
	
	@Test
	public void leapYear_noBonusOutsideFeb() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 4, 5, 10);
		DrawResult drawResult = new DrawResult(LeapYearNotFebDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 250000, prize.getValue());
	}
	
	@Test
	public void leapYear_doubleBonusInFeb() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 4, 5, 10);
		DrawResult drawResult = new DrawResult(LeapYearInFebDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 500000, prize.getValue());
	}
	
	@Test
	public void leapYear_tripleBonusOnFeb29th() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 4, 5, 10);
		DrawResult drawResult = new DrawResult(LeapYear29thFebDate, drawnNums);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 750000, prize.getValue());
	}
	
	@Test
	public void randomThreeCorrectGuessesGetRegularPrize() {
		Set<Integer> guesses = Sets.newHashSet(1, 2, 3, 4, 5, 6);
		Set<Integer> drawn = Sets.newHashSet(2, 3, 4, 59, 60, 58);
		DrawResult drawResult = new DrawResult(nonLeapYearDate, drawn);
		
		Prize prize = service.calculatePrizeFor(drawResult, guesses);
		
		assertEquals("No correct Guesses prize", 208320, prize.getValue());
	}
}
