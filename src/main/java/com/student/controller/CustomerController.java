package com.student.controller;

import com.student.pojo.Customer;
import com.student.service.CustomerService;
import com.student.vo.QueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Created by liqiwen on 2017/7/10.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //保存用户
    @RequestMapping("/save")
    public String saveCustomer(Customer customer) throws Exception{
        customer.setCid(UUID.randomUUID().toString());
        String cid = UUID.randomUUID().toString();
        customer.setCid(cid.replace("-",""));
        customerService.saveCustomer(customer);

        return "forward:list";
    }


    //查询全部用户
    @RequestMapping("/list")
    public String customerList(Model model) throws Exception{
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        return "list";
    }


    /**
     * 通过 PathVariable 注解接受 url 中传递过来的参数
     * @param cid
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/find/{cid}")
    public String findCustomerById(@PathVariable("cid") String cid, Model model) throws Exception{
//        String cid = request.getParameter("cid");
        Customer customer = customerService.findCustomerById(cid);
        model.addAttribute("customer", customer);
        return "edit";
    }


    @RequestMapping("/update")
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
        return "forward:list";
    }

    @RequestMapping("/delete/{cid}")
    public String deleteCustomer(@PathVariable("cid") String cid) throws Exception{
//        String cid = request.getParameter("cid");
        Customer customer = customerService.findCustomerById(cid);
        if(customer != null){
            customerService.deleteCustomer(cid);
        }
        //重定向： 这里的路径就是绝对路径，重定向是两次请求
        return "redirect:/customer/list";
    }


    //根据输入内容进行模糊查询
    @RequestMapping(value = "/search")
    public String search(QueryVo queryVo, Model model) throws Exception{
        System.out.println(queryVo);
        List<Customer> customerList = customerService.search(queryVo);
        if(customerList != null && customerList.size() > 0) {
            model.addAttribute("customerList", customerList);
            return "list";
        }else{
            model.addAttribute("msg", "你输入的查询条件太苛刻啦！本服务器已经很努力了，但是找不到你需要的东西！");
            return "msg";

        }
    }

}
