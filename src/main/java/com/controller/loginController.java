package com.controller;

import com.Util.Credentials;
import com.Util.JsonUtil;
import com.Util.ResponseInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.model.EmployeeModel;
import com.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by caoxiao on 2017/5/21.
 */
@Controller
public class loginController {
    @Resource
    public LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login (@RequestBody String requestStr) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode objectNode = mapper.createObjectNode();
        Credentials credentials = null;
        try {
            credentials = mapper.readValue(requestStr,Credentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseInfo responseInfo = new ResponseInfo();
        if (null != credentials) {
            responseInfo = loginService.login(credentials.getUsername(),credentials.getPassword());
        }
        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode objectNode = mapper.createObjectNode();
        String username;
        String password;
        String email;
        String mobile;
        String nickname;
        try {
            ObjectNode root = mapper.readValue(requestStr, ObjectNode.class);
            JsonNode params = root.path("params");
            username = params.path("username").asText();
            password = params.path("password").asText();
            email = params.path("email").asText();
            mobile = params.path("mobile").asText();
            nickname = params.path("nickname").asText();
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }
        responseInfo = loginService.register(username, password, email, mobile, nickname);

        return JsonUtil.getJsonStr(responseInfo);
    }

}
