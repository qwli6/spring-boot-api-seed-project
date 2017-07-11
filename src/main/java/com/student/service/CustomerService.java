package com.student.service;

import com.student.pojo.Customer;
import com.student.vo.QueryVo;

import java.util.List;

/**
 * Created by liqiwen on 2017/7/10.
 */
public interface CustomerService {


    //保存用户
    void saveCustomer(Customer customer);

    //查询全部用户
    List<Customer> findAll();

    //更新单个用户
    void updateCustomer(Customer customer);

    //根据主键查询用户
    Customer findCustomerById(String cid);

    //根据id 删除用户
    void deleteCustomer(String cid);

    //高级查询
    List<Customer> search(QueryVo queryVo);
}
