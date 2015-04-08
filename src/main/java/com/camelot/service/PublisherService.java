package com.camelot.service;

import java.time.LocalDate;
import java.util.Set;

public interface PublisherService {

	void publishLotteryResults(LocalDate drawDate, Set<Integer> drawnNumbers, int prize);
}
