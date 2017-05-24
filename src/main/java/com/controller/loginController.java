package com.controller;

import com.Util.JsonUtil;
import com.Util.ResponseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caoxiao on 2017/5/21.
 */
@Controller
public class loginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login (HttpServletRequest request, HttpServletResponse response, @RequestBody String requestBody) {
        ResponseInfo responseInfo = new ResponseInfo();

        return JsonUtil.toJson(responseInfo);
    }
}
