package com.camelot.transport;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.camelot.exception.RegistrationException;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.google.common.base.Preconditions.*;

public class WinWinLotteryCustomerRegistration implements CustomerRegistration {

	public static final Period REG_PERIOD = Period.ofMonths(6);
	public static final int GUESS_LOWER_BOUND = 1;
	public static final int GUESS_UPPER_BOUND = 60;
	public static final int GUESS_NUMBERS_COUNT = 6;

	private final Set<Integer> guessNumbers;
	private final LocalDate startDate;
	private final LocalDate endDate;

	@Inject
	public WinWinLotteryCustomerRegistration(
			@Named("REGISTRATION_DETAILS") String[] regDetails) {
		try {
			checkNotNull(regDetails);
			checkArgument(regDetails.length == GUESS_NUMBERS_COUNT + 1);
			
			this.endDate = extractDate(regDetails[0]);
			this.startDate = endDate.minus(REG_PERIOD);
			this.guessNumbers = extractGuessNumbers(Arrays.copyOfRange(
					regDetails, 1, 7));
			
			checkState(guessNumbers.size() == GUESS_NUMBERS_COUNT);
		} catch (Exception ex) {
			throw new RegistrationException("Invalid registration details", ex);
		}
	}

	private Set<Integer> extractGuessNumbers(String[] copyOfRange) {
		return Arrays
				.stream(copyOfRange)
				.map(Integer::parseInt)
				.filter((num) -> num >= GUESS_LOWER_BOUND
						&& num <= GUESS_UPPER_BOUND)
				.collect(Collectors.toSet());
	}

	private LocalDate extractDate(String string) {
		try {
			return LocalDate
					.parse(string, DateTimeFormatter.ofPattern("d/M/u"));
		} catch (DateTimeParseException ex) {
			throw new RegistrationException(
					"Expected date in format 'dd/mm/yyyy'", ex);
		}
	}

	WinWinLotteryCustomerRegistration(LocalDate startDate, LocalDate endDate,
			Set<Integer> guessNumbers) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.guessNumbers = guessNumbers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.camelot.transport.CustomerRegistration#getGuessNumbers()
	 */
	@Override
	public Set<Integer> getGuessNumbers() {
		return new HashSet<Integer>(guessNumbers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.camelot.transport.CustomerRegistration#getStartDate()
	 */
	@Override
	public LocalDate getStartDate() {
		return startDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.camelot.transport.CustomerRegistration#getEndDate()
	 */
	@Override
	public LocalDate getEndDate() {
		return endDate;
	}
}
