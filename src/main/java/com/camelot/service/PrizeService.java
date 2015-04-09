package com.camelot.service;

import java.util.Set;

import com.camelot.transport.DrawResult;
import com.camelot.transport.Prize;

public interface PrizeService {

	Prize calculatePrizeFor(DrawResult res, Set<Integer> guessNumbers);
}
