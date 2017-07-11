package com.student.vo;

import com.student.pojo.Customer;

/**
 * Created by liqiwen on 2017/7/10.
 * 高级查询
 */
public class QueryVo {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "customer=" + customer +
                '}';
    }
}
