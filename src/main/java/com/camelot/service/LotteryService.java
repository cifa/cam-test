package com.camelot.service;

import java.time.LocalDate;
import java.util.List;

import com.camelot.transport.DrawResult;

public interface LotteryService {

	List<DrawResult> getDrawResultsForPeriod(LocalDate from, LocalDate to);
}
