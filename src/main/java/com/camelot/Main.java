package com.camelot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camelot.guice.CamelotModule;
import com.google.inject.Guice;
import com.google.inject.Injector;



public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		CustomerRegistration custReg = new CustomerRegistration(args);
		Injector injector = Guice.createInjector(new CamelotModule());
		injector.getInstance(LotteryRunner.class).runLotteryFor(custReg);
	}
}
