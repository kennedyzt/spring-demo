package com.kennedy.springdemo.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author kennedy 分页查询返回对象
 * @param <T>
 */
public class PageModel<T> implements Serializable {
    private static final long serialVersionUID = 6010239761547229256L;
    private Integer totalCount; // 总条数
    private List<T> records; // 结果集list

    public PageModel() {
        super();
    }

    public PageModel(Integer totalCount, List<T> records) {
        super();
        this.totalCount = totalCount;
        this.records = records;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

}
