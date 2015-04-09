package com.camelot.guice;

import java.util.Arrays;

import com.camelot.service.LotteryService;
import com.camelot.service.PrizeService;
import com.camelot.service.PublisherService;
import com.camelot.service.impl.StdOutPublisherService;
import com.camelot.service.impl.WinWinLotteryService;
import com.camelot.service.impl.WinWinPrizeService;
import com.camelot.transport.CustomerRegistration;
import com.camelot.transport.WinWinLotteryCustomerRegistration;
import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class CamelotModule implements Module {

	private final String[] regDetails;

	public CamelotModule(String[] regDetails) {
		this.regDetails = Arrays.copyOf(regDetails, regDetails.length);
	}

	@Override
	public void configure(Binder binder) {
		binder.bind(Key.get(new TypeLiteral<String[]>() {}, Names.named("REGISTRATION_DETAILS"))).toInstance(regDetails);
		binder.bind(LotteryService.class).to(WinWinLotteryService.class).in(Singleton.class);
		binder.bind(PrizeService.class).to(WinWinPrizeService.class).in(Singleton.class);
		binder.bind(PublisherService.class).to(StdOutPublisherService.class).in(Singleton.class);
		binder.bind(CustomerRegistration.class).to(WinWinLotteryCustomerRegistration.class).in(Singleton.class);
	}

}
