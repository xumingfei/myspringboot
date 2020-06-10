package com.myproject.springmybatis.page;
import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/9 17:20
 * 分页封装类
 */
public class PageBean <T> {
    private List<T> pageData;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalCount;

    public PageBean() {
    }

    public int getPageCount(){
        if(this.totalCount.intValue()%this.pageSize.intValue() ==0){
            return this.totalCount.intValue() / this.pageSize.intValue();
        }else {
            return this.totalCount.intValue() / this.pageSize.intValue() +1;
        }
    }

    public PageBean(List<T> pageData, Integer totalCount) {
        this.pageData = pageData;
        this.totalCount = totalCount;
    }

    public boolean isFirst(){
        return (this.currentPage==1 || this.totalCount==0);
    }

    public boolean isLast(){
        return (this.totalCount == 0 || this.currentPage >= this.getPageCount());
    }

    public boolean isHasNext(){
        return this.currentPage.intValue() < this.getPageCount();
    }

    public boolean isHasPrev(){
        return this.currentPage.intValue()>1;
    }

    public Integer getNextPage(){
        if(this.currentPage.intValue() >= getPageCount()){
            return Integer.valueOf(getPageCount());
        }else {
            return Integer.valueOf(this.currentPage.intValue() + 1);
        }

    }

    public Integer getPrevPage(){
        if(this.currentPage.intValue() <= 1){
            return Integer.valueOf(1);
        }else{
            return Integer.valueOf(this.currentPage - 1);
        }
    }


    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageData=" + pageData +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                '}';
    }
}

