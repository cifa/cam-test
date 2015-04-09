package com.camelot.transport;

import java.time.LocalDate;
import java.util.Set;

public class DrawResult {

	private final LocalDate drawDate;
	private final Set<Integer> numbersDrawn;
	
	public DrawResult(LocalDate drawDate, Set<Integer> numbersDrawn) {
		this.drawDate = drawDate;
		this.numbersDrawn = numbersDrawn;	
	}

	public LocalDate getDrawDate() {
		return drawDate;
	}
	
	public Set<Integer> getNumbersDrawn() {
		return numbersDrawn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((drawDate == null) ? 0 : drawDate.hashCode());
		result = prime * result
				+ ((numbersDrawn == null) ? 0 : numbersDrawn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrawResult other = (DrawResult) obj;
		if (drawDate == null) {
			if (other.drawDate != null)
				return false;
		} else if (!drawDate.equals(other.drawDate))
			return false;
		if (numbersDrawn == null) {
			if (other.numbersDrawn != null)
				return false;
		} else if (!numbersDrawn.equals(other.numbersDrawn))
			return false;
		return true;
	}
}
