package com.camelot;

import com.camelot.service.LotteryService;
import com.camelot.service.PriceService;
import com.camelot.service.PublisherService;
import com.google.inject.Inject;

public class LotteryRunner {
	
	private LotteryService lotteryService;
	private PriceService priceService;
	private PublisherService publisherService;

	@Inject
	public LotteryRunner(LotteryService lotteryService, PriceService priceService, 
			PublisherService publisherService) {
				this.lotteryService = lotteryService;
				this.priceService = priceService;
				this.publisherService = publisherService;
	}
	
	public void runLotteryFor(CustomerRegistration custReg) {
		
	}
}
