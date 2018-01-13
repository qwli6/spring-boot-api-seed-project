package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.config.LogActions;
import com.itqiwen.blog.domain.Log;
import com.itqiwen.blog.domain.RestResponse;
import com.itqiwen.blog.domain.User;
import com.itqiwen.blog.service.LogService;
import com.itqiwen.blog.service.UserService;
import com.itqiwen.blog.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "admin/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse doLogin(User user, HttpServletRequest request,
                                HttpServletResponse response){
        System.out.println("登录用户名：" + user.getUsername() + "==" + user.getPassword());

        try {
            User loginUser = userService.login(user.getUsername(), user.getPassword());
            request.getSession().setAttribute(Config.LOGIN_SESSION_KEY, loginUser);

            //记录登录日志
            Log log = new Log();
            log.setAction(LogActions.LOGIN.getAction());
            log.setCreateDt(DateUtils.getUnixTimeByDate(new Date()));
            log.setIpAddress(request.getRemoteAddr());
            log.setNickname(loginUser.getNickname());
            log.setUsername(loginUser.getUsername());
            logService.insertLogs(log);
        }catch (Exception e){
            String msg = "登录失败";
            return RestResponse.fail(msg);
        }
       return RestResponse.ok();
    }

}
