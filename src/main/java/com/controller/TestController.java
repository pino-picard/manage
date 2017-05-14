package com.controller;

import com.Util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caoxiao on 2017/4/5.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test (HttpServletRequest request, HttpServletResponse response,@RequestBody String requestBody) {
        System.out.println(requestBody);
        JSONObject object = new JSONObject();
        object.put("data","Hello Pino!");
        return JsonUtil.toJson(object);
    }
}
