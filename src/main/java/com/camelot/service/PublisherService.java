package com.camelot.service;

import com.camelot.transport.DrawResult;
import com.camelot.transport.Prize;

public interface PublisherService {

	void publishLotteryResults(DrawResult result, Prize customerPrize);
}
