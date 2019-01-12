package com.yuanrong.admin.util;

import java.util.Vector;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class PagedResult {
    /**
     * 总数
     */
    private int recordCount = 0;

    /**
     * 每页显示的条数
     */
    private int pageSize = 25;

    private int defaultPageSize = 25;

    /**
     * 总页数
     */
    private int pageCount = 0;

    /**
     * 当前页数
     */
    private int currentPage = 1;

    private int start = 0;

    /**
     * 分页中的参数
     */
    private String parameter = "";

    /**
     * 返回的结果集
     */
    private Vector result = new Vector();

    /**
     *
     */
    public PagedResult() {
        super();
    }

    /**
     * 初始化分页类
     *
     * @param recordCount
     *            总条数
     * @param currentPage
     *            当前页
     * @param pageSize
     *            每页条数
     */
    public PagedResult(int recordCount, int currentPage, int pageSize) {
        if (recordCount < 0)
            recordCount = 0;
        this.recordCount = recordCount;

        if (pageSize < 1)
            pageSize = defaultPageSize;
        this.pageSize = pageSize;

        int pageCount = recordCount / pageSize;
        if ((recordCount - pageCount * pageSize) > 0)
            pageCount++;
        this.pageCount = pageCount;

        if (currentPage < 1)
            currentPage = 1;
        else if (currentPage > this.pageCount)
            currentPage = this.pageCount;
        this.currentPage = currentPage;
        if (this.currentPage < 1)
            this.currentPage = 1;
        this.start = (currentPage - 1) * pageSize;
    }

    public int getStart() {
        return this.start;
    }

    /**
     * 是否为尾页；
     */
    public boolean isEndPage() {
        return currentPage == pageCount || pageCount == 0;
    }

    /**
     * 是否为首页；
     */
    public boolean isStartPage() {
        return currentPage == 0;
    }

    /**
     * @return Returns the currentPage.
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            The currentPage to set.
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage < 1)
            currentPage = 1;
        this.currentPage = currentPage;
    }

    /**
     * @return Returns the pageCount.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount
     *            The pageCount to set.
     */
    public void setPageCount(int pageCount) {
        if (pageCount < 0)
            pageCount = 0;
        this.pageCount = pageCount;
    }

    /**
     * @return Returns the pageSize.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            The pageSize to set.
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 0)
            pageSize = defaultPageSize;
        this.pageSize = pageSize;
    }

    /**
     * @return Returns the recordCount.
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * @param recordCount
     *            The recordCount to set.
     */
    public void setRecordCount(int recordCount) {
        if (recordCount < 0)
            recordCount = 0;
        this.recordCount = recordCount;
    }

    /**
     * @return Returns the result.
     */
    public Vector getResult() {
        return result;
    }

    /**
     * @param result
     *            The result to set.
     */
    public void setResult(Vector result) {
        this.result = result;
    }


    public String getParameter() {
        return parameter;
    }

    public PagedResult setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }
}
