package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.domain.RestResult;
import com.itqiwen.blog.domain.BlogSystem;
import com.itqiwen.blog.service.ILogService;
import com.itqiwen.blog.service.ISystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController{

    @Resource
    private ISystemService systemService;

    @Resource
    private ILogService logService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/login");
        if(request.getParameter("error") != null){
            modelAndView.addObject("errorMsg", request.getSession().getAttribute("exceptionMsg"));
        }
        return modelAndView;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestResult doLogin(BlogSystem user, HttpServletRequest request,
                              HttpServletResponse response){

//        try {
//            BlogSystem loginUser = userService.login(user.getUsername(), user.getPassword());
//            request.getSession().setAttribute(Config.LOGIN_SESSION_KEY, loginUser);
//
//            //记录登录日志
//            Logs log = new Logs();
//            log.setAction(LogActions.LOGIN.getAction());
//            log.setCreateDt(DateUtils.getUnixTimeByDate(new Date()));
//            log.setIpAddress(request.getRemoteAddr());
//            log.setNickname(loginUser.getNickname());
//            log.setUsername(loginUser.getUsername());
//            logService.insertLogs(log);
//        }catch (Exception e){
//            String msg = "登录失败";
//            return RestResult.fail(msg);
//        }
       return RestResult.ok();
    }

}
