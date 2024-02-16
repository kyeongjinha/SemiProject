package com.nf.not404found.admin.common;

public class AdminSelectCriteria {

    private String searchCondition;
    private String searchValue;

    public AdminSelectCriteria() {
    }

    public AdminSelectCriteria(String searchCondition, String searchValue) {
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public String toString() {
        return "SelectCriteria{" +
                "searchCondition='" + searchCondition + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
