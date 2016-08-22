package com.wmg.dsp.tango.notices.util;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginationInfoWrapper<T> {

	@JsonProperty("total_items")
	private long totalItems;

	@JsonProperty("total_pages")
	private long totalPages;

	@JsonProperty("current_page")
	private int currentPage;

	private int limit;

	private Collection<T> data;

	public PaginationInfoWrapper() {
	}

	public PaginationInfoWrapper(Collection<T> data, int currentPage, int limit, long totalItems, long totalPages) {
		this.data = data;
		this.currentPage = currentPage;
		this.limit = limit;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
	}

	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
}
