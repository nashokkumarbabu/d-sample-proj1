package com.test.model;

public class SearchEntity {
	
    private int pageNo;
    private int pageSize;
    private String sortBy;
    private String search;
    private String sortOrder;

    public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

    public String getSortBy() {
		return sortBy;
    }
    
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
    
    public SearchEntity(String sortBy, String search, int pageNo, int pageSize, String sortOrder) {
        this.sortBy = sortBy;
        this.search = search;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortOrder = sortOrder;
	}

}
