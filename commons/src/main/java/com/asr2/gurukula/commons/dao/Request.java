package com.asr2.gurukula.commons.dao;

import java.util.Optional;

/**
 * Created by abhijith.nagarajan on 8/12/15.
 */
public class Request<T> {

	private int pageNum;

	private int maxRecords;

	private T searchObj;

	public Request(int pageNum, int maxRecords, T searchObj) {

		this.pageNum = pageNum < 0 ? 0 : pageNum;

		this.maxRecords = maxRecords < 10 ? 10 : maxRecords;

		if(Optional.ofNullable(searchObj).isPresent())
			this.searchObj = searchObj;
		else
			throw new NullPointerException("Search object cannot be null");

	}

	public int getPageNum() {
		return pageNum;
	}

	public int getMaxRecords() {
		return maxRecords;
	}

	public T getSearchObj() {
		return searchObj;
	}
}
