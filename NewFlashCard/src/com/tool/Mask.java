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

	public boolean hasOr(Mask... masks) {
		for (Mask m : masks) {
			if (this.has(m)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasAnd(Mask... masks) {
		for (Mask m : masks) {
			if (!this.has(m)) {
				return false;
			}
		}
		return true;
	}

	public Mask add(Mask m) {
		this.mask = new Mask(this.mask | m.getMask()).getMask();
		return this;
	}
}
