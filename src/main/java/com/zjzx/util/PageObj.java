package com.zjzx.util;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class PageObj<T> {

	private static final long serialVersionUID = -5395997221963176643L;

	private List<T> list; // list result of this page
	private int pageNumber; // page number
	private int pageSize; // result amount of this page
	private int totalPage; // total page
	private int totalRow; // total row

	/**
	 * Constructor.
	 * 
	 * @param list
	 *            the list of paginate result
	 * @param pageNumber
	 *            the page number
	 * @param pageSize
	 *            the page size
	 * @param totalPage
	 *            the total page of paginate
	 * @param totalRow
	 *            the total row of paginate
	 */
	public PageObj(List<T> list, int pageNumber, int pageSize, int totalPage,
			int totalRow) {
		this.list = list;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalRow = totalRow;
	}

	public PageObj(Page<T> page) {
		this.list = page.getList();
		this.pageNumber = page.getPageNumber();
		this.pageSize = page.getPageSize();
		this.totalPage = page.getTotalPage();
		this.totalRow = page.getTotalRow();
	}

	public void appendPage(List<T> videoList) {
		// TODO Auto-generated method stub
		if (videoList == null || videoList.size() <= 0) {
			return;
		}
		if (this.list == null || this.list.size() <= 0) {
			this.list = videoList;
			return;
		}
		if (this.list.size() < 2) {
			this.list.addAll(videoList);
			return;
		}

		int insertindex = 2;

		for (T video : videoList) {
			if (insertindex > this.list.size()) {
				this.list.add(video);
				insertindex++;
				continue;
			}
			this.list.add(insertindex, video);
			insertindex = insertindex + 3;
		}

	}

//	public static void main(String[] args) {
//		List<Record> articleList = new ArrayList<Record>();
//		for (int i = 0; i < 8; i++) {
//			articleList.add(createRecord(i, "文章"));
//		}
//
//		List<Record> videoList = new ArrayList<Record>();
//		for (int i = 0; i < 5; i++) {
//			videoList.add(createRecord(i, "视频"));
//		}
//		Page<Record> page = new Page<>(articleList, 1, 15, 50, 10);
//		PageObj<Record> pageObj = new PageObj<>(page);
//		pageObj.appendPage(videoList);
//		System.out.println(pageObj.getList());
//	}

	public static Record createRecord(int index, String title) {
		Record record = new Record();
		record.set("title", title + index);
		return record;
	}

	public void appendPage(int index, T record) {
		if (record == null) {
			return;
		}
		if (this.list == null || this.list.size() <= 0) {
			return;
		}
		if (this.list.size() < index) {
			this.list.add(record);
		} else {
			this.list.add(index, record);
		}
	}

	/**
	 * Return list of this page.
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Return page number.
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * Return page size.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Return total page.
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * Return total row.
	 */
	public int getTotalRow() {
		return totalRow;
	}

	public boolean isFirstPage() {
		return pageNumber == 1;
	}

	public boolean isLastPage() {
		return pageNumber >= totalPage;
	}

	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("pageNumber : ").append(pageNumber);
		msg.append("\npageSize : ").append(pageSize);
		msg.append("\ntotalPage : ").append(totalPage);
		msg.append("\ntotalRow : ").append(totalRow);
		return msg.toString();
	}

}
