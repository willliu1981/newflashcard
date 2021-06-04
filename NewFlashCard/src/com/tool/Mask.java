package com.tool;

public class Mask {
	private int mask;

	public Mask(int mask) {
		this.mask = mask;
	}

	public int getMask() {
		return mask;
	}

	public boolean has(Mask mask) {
		return (this.mask & mask.getMask()) == mask.getMask();
	}

	
	public Mask add(Mask m) {
		return new Mask(this.mask|m.getMask());
	}
}
