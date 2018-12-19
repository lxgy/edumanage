package com.seehope.core.page;

public abstract interface Pageable {
	public static final int START_INDEX = 1;
	public static final int PAGE_SIZE = 10;

	public abstract int getOffset();

	public abstract int getPageno();

	public abstract int getPagesize();

	public abstract Sort getSort();
}
