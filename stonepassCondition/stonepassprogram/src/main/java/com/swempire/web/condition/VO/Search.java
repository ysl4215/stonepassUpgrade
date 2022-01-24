package com.swempire.web.condition.VO;

public class Search extends ConditionPaginationVO {

	private String searchType;
	private String keyword;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Search [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
}
