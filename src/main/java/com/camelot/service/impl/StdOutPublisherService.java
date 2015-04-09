package com.camelot.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.camelot.service.PublisherService;
import com.camelot.transport.DrawResult;
import com.camelot.transport.Prize;

public class StdOutPublisherService implements PublisherService {

	@Override
	public void publishLotteryResults(DrawResult result, Prize customerPrize) {
		String drawDate = result.getDrawDate().format(DateTimeFormatter.ofPattern("dd/MM/u"));
		String drawnNumbers = drawnNumbersToString(result);
		System.out.println(String.format("%s; %s; %s", drawDate, drawnNumbers, customerPrize));
	}
	
	private String drawnNumbersToString(DrawResult result) {
		return result.getNumbersDrawn()
				.stream()
				.map((num) -> num.toString())
				.collect(Collectors.joining(","));			
	}

}
