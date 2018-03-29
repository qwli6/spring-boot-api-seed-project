package com.company.project.core;


/**
 * 描述:
 *
 * @author liqiwen
 * @date 2018-03-28 17:05
 */
public abstract class BaseController {



    /**
     * 分页信息
     * @return
     */
    protected PageBean getPageBean(){
        return new PageBean();
    }



}
