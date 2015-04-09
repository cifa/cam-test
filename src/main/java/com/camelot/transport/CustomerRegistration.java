package com.camelot.transport;

import java.time.LocalDate;
import java.util.Set;

public interface CustomerRegistration {

	Set<Integer> getGuessNumbers();

	LocalDate getStartDate();

	LocalDate getEndDate();

}