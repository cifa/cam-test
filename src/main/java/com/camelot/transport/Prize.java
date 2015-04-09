package com.camelot.transport;

public class Prize {

	private final int value;
	
	public Prize(int value) {
		this.value = value;	
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "£" + value;
	}
}
