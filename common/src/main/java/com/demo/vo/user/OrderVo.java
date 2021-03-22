package com.demo.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class OrderVo implements Serializable {

    private Integer id;
    private double prvice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public OrderVo(Integer id, double prvice, Date createTime) {
        this.id = id;
        this.prvice = prvice;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrvice() {
        return prvice;
    }

    public void setPrvice(double prvice) {
        this.prvice = prvice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
