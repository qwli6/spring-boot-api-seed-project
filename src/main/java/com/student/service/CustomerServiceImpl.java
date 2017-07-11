package com.student.service;

import com.student.mapper.CustomerMapper;
import com.student.pojo.Customer;
import com.student.pojo.CustomerExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liqiwen on 2017/7/10.
 */
@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void saveCustomer(Customer customer) {
        customerMapper.insert(customer);
    }

    //查询全部用户
    @Override
    public List<Customer> findAll() {

        CustomerExample customerExample = new CustomerExample();
        return customerMapper.selectByExample(customerExample);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public Customer findCustomerById(String cid) {
        return customerMapper.selectByPrimaryKey(cid);
    }

    @Override
    public void deleteCustomer(String cid) {
        customerMapper.deleteByPrimaryKey(cid);
    }

}
