package com.camelot;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.camelot.service.LotteryService;
import com.camelot.service.PrizeService;
import com.camelot.service.PublisherService;
import com.camelot.transport.CustomerRegistration;
import com.camelot.transport.DrawResult;
import com.camelot.transport.Prize;
import com.google.inject.Inject;

public class LotteryRunner implements Runnable {

	private final LotteryService lotteryService;
	private final PrizeService priceService;
	private final PublisherService publisherService;
	private final CustomerRegistration custReg;

	@Inject
	public LotteryRunner(LotteryService lotteryService,
			PrizeService priceService, PublisherService publisherService,
			CustomerRegistration custReg) {
		this.lotteryService = lotteryService;
		this.priceService = priceService;
		this.publisherService = publisherService;
		this.custReg = custReg;
	}

	public void run() {
		lotteryService
				.getDrawResultsForPeriod(custReg.getStartDate(),
						custReg.getEndDate())
				.stream()
				.collect(
						Collectors.toMap(Function.identity(),
								this::calculateCustomerPrize))
				.forEach(publisherService::publishLotteryResults);
	}

	private Prize calculateCustomerPrize(DrawResult res) {
		return priceService.calculatePrizeFor(res, custReg.getGuessNumbers());
	}
}
