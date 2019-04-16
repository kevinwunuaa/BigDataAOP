package com.bsoft.common.pagination;

import java.util.List;

/**
 * easyui 分页对象.
 * total:数据总条数.
 * rows:本页数据.
 * @author Wuyong
 *
 */
public class PageObject {
	int total;
	List rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
