package com.tony.test.page;

import java.io.Serializable;
import java.util.List;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> implements Cloneable, Serializable {

    private static final long serialVersionUID = 5774810159154559745L;

    private int               pageNo           = 1;                   //页码，默认是第一页

    private int               pageSize         = 20;                  //每页显示的记录数，默认是20

    private int               totalRecord;                            //总记录数

    private int               totalPage;                              //总页数

    private boolean           totalFlag        = true;                //是否查询总数和总页数 默认查询

    private List<T>           results;                                //对应的当前页记录

    private T                 params;                                 //其他的参数封装成一个Map对象

    public boolean getTotalFlag() {
        return totalFlag;
    }

    public void setTotalFlag(boolean totalFlag) {
        this.totalFlag = totalFlag;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        this.totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", totalFlag="
                + totalFlag + ", results=" + results + ", params=" + params + "]";
    }

}
