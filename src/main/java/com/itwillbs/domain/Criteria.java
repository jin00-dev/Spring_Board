package com.itwillbs.domain;

/**
 * 페이징 처리를 계산하는 기준의 정보를 저장 
 */
public class Criteria {
	
	private int page;
	// private int pagestart; 시작인덱스
	private int pageSize;
	
	public Criteria() {
		this.page = 1;
		this.pageSize = 10;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize > 100 ) {
			this.pageSize = 10;
			return;
		}
		
		this.pageSize = pageSize;
	}
	
	// 변수를 저장하기위한 목적 아님
	// mapper에서 호출되는 메서드 # {pageStart}을 호출함
	public int getPageStart() {
		// 출력문 (필요에 따라)
		return (this.page - 1) * pageSize ;
	}
			
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}

	// alt shift s + s
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + "]";
	}


}
