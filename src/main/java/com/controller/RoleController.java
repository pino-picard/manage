package com.controller;

import com.Util.JsonUtil;
import com.Util.ResponseInfo;
import com.dao.entity.UserEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by caoxiao on 2017/5/29.
 */
@Controller
public class RoleController {

    @Resource
    RoleService roleService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public String getUserList (@RequestParam(required = false) String userName,
                               @RequestParam(required = false) String roleName) {
        ResponseInfo responseInfo = roleService.getUserList(userName,roleName);

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public String createUser (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        String userName;
        String password;
        String email;
        String mobile;
        String displayName;
        String roleId;
        try {
            ObjectNode root = mapper.readValue(requestStr, ObjectNode.class);
            JsonNode params = root.path("params");
            userName = params.path("userName").asText();
            password = params.path("password").asText();
            email = params.path("email").asText();
            mobile = params.path("mobile").asText();
            displayName = params.path("displayName").asText();
            roleId = params.path("roleId").asText();
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }
        boolean result = roleService.createUser(userName, displayName,email,mobile,password,roleId);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","用户创建失败");
        }

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        String userName;
        try {
            ObjectNode root = mapper.readValue(requestStr, ObjectNode.class);
            JsonNode params = root.path("params");
            userName = params.path("userName").asText();
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }
        boolean result = roleService.deleteUser(userName);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","用户删除失败");
        }

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public String modifyUser () {
        ResponseInfo responseInfo = new ResponseInfo();

        return JsonUtil.getJsonStr(responseInfo);
    }


    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public String getRoleList (@RequestParam(required = false) String roleName) {
        ResponseInfo responseInfo = roleService.getRoleList();

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    @ResponseBody
    public String createRole (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        String roleName;
        String privilege;
        try {
            ObjectNode root = mapper.readValue(requestStr, ObjectNode.class);
            JsonNode params = root.path("params");
            roleName = params.path("roleName").asText();
            privilege = params.path("privilege").asText();
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }
        boolean result = roleService.createRole(roleName,privilege);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","角色删除失败");
        }
        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole (@RequestParam String roleId) {
        ResponseInfo responseInfo = new ResponseInfo();
        boolean result = roleService.deleteRole(roleId);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","角色删除失败");
        }
        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/modifyRole", method = RequestMethod.POST)
    @ResponseBody
    public String modifyRole (@RequestParam String privilege,
                              @RequestParam String roleId) {
        ResponseInfo responseInfo = new ResponseInfo();
        boolean result = roleService.modifyRole(roleId, privilege);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","角色修改失败");
        }

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/getPrivilegeInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getPrivilegeInfo (@RequestParam String privileges) {
        ResponseInfo responseInfo = roleService.getPrivilegeInfo(privileges);

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/createPrivilege", method = RequestMethod.POST)
    @ResponseBody
    public String createPrivilege () {
        ResponseInfo responseInfo = new ResponseInfo();

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/deletePrivilege", method = RequestMethod.POST)
    @ResponseBody
    public String deletePrivilege () {
        ResponseInfo responseInfo = new ResponseInfo();

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/modifyPrivilege", method = RequestMethod.POST)
    @ResponseBody
    public String modifyPrivilege () {
        ResponseInfo responseInfo = new ResponseInfo();

        return JsonUtil.getJsonStr(responseInfo);
    }
}
