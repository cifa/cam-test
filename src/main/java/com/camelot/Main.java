package com.camelot;

import com.camelot.guice.CamelotModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new CamelotModule(args));
		injector.getInstance(LotteryRunner.class).run();
	}
}
