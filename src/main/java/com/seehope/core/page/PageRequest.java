package com.seehope.core.page;

public class PageRequest implements Pageable {
	private int pageno;
	private int pagesize = 10;
	private Sort sort;

	public PageRequest() {
		this.pageno = 1;
		this.pagesize = 10;
	}

	public PageRequest(int pageno, int pagesize) {
		if (pageno <= 0) {
			pageno = 1;
		}
		if (pagesize <= 0) {
			pagesize = 10;
		}
		this.pageno = pageno;
		this.pagesize = pagesize;
	}

	public int getOffset() {
		return (this.pageno - 1) * this.pagesize;
	}

	public int getPageno() {
		return this.pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public Sort getSort() {
		return this.sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
}
