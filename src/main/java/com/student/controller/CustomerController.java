package com.student.controller;

import com.student.pojo.Customer;
import com.student.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liqiwen on 2017/7/10.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/save")
    public String saveCustomer(Customer customer) throws Exception{
        customer.setCid(UUID.randomUUID().toString());
        String cid = UUID.randomUUID().toString();
        customer.setCid(cid.replace("-",""));
        customerService.saveCustomer(customer);

        return "forward:query.action";
    }


    //查询全部用户
    @RequestMapping("/query")
    public String queryCustomer(Model model) throws Exception{
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        for (Customer customer :
                customerList) {
            System.out.println(customer);
        }
        return "list";
    }

    @RequestMapping("/findCustomerById")
    public String findCustomerById(HttpServletRequest request, Model model) throws Exception{
        String cid = request.getParameter("cid");
        Customer customer = customerService.findCustomerById(cid);
        model.addAttribute("customer", customer);
        return "edit";
    }


    @RequestMapping("/updateCustomer")
    public String updateCustomer(MultipartFile multipartFile, Customer customer) throws Exception{

        /**
        //获取图片完整名称
        String fileStr = multipartFile.getOriginalFilename();
        //使用随机生成的字符串 + 原图片的扩展名，防止重名
        String newfileName = UUID.randomUUID().toString()+ fileStr.substring(fileStr.lastIndexOf("."));//新的图片名称
        //将图片保存到硬盘
        multipartFile.transferTo(new File("E:\\picServer\\" + newfileName));

        //将图片名称保存到数据库
//        customer.setpic(newfileName);

         **/

        customerService.updateCustomer(customer);
        //请求转发，相对路径，不受 Controller 类上的RequestMapping 的影响
        //         绝对路径，受 Controller 类上的RequestMapping 的影响
        return "forward:query.action";
    }

    @RequestMapping("/delete")
    public String deleteCustomer(HttpServletRequest request) throws Exception{
        String cid = request.getParameter("cid");
        Customer customer = customerService.findCustomerById(cid);

        if(customer != null){
            customerService.deleteCustomer(cid);
        }
        //重定向： 这里的路径就是绝对路径，重定向是两次请求
        return "redirect:/customer/query.action";
    }
}
