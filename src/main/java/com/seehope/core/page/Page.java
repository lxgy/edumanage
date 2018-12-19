package com.seehope.core.page;

import java.util.List;

public class Page<T> {
	public List<T> results;
	public Integer pageno;
	public Integer pagesize;
	public Integer maxpage;
	public Integer total;

	public Page() {
	}

	public Page(List<T> resultList, Pageable pageable, int total) {
		this.results = resultList;
		this.pageno = Integer.valueOf(pageable.getPageno());
		this.pagesize = Integer.valueOf(pageable.getPagesize());
		this.maxpage = Integer.valueOf(total / pageable.getPagesize());
		if (total % pageable.getPagesize() != 0) {
			this.maxpage = Integer.valueOf(this.maxpage.intValue() + 1);
		}
		this.total = Integer.valueOf(total);
	}

	public List<T> getResults() {
		return this.results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Integer getPageno() {
		return this.pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public Integer getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getMaxpage() {
		return this.maxpage;
	}

	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
