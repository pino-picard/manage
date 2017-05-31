package com.service;

import com.Util.*;
import com.dao.PrivilegeMapDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.dao.entity.PrivilegeMapEntity;
import com.dao.entity.RoleEntity;
import com.dao.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by caoxiao on 2017/5/24.
 */
@Component(value = "loginService")
public class LoginService {

    @Resource
    UserDao userDao;

    @Resource
    PrivilegeMapDao privilegeMapDao;

    @Resource
    RoleDao roleDao;

    @Resource
    DataConvert dataConvert;

    public ResponseInfo login (String username, String password) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode objectNode = mapper.createObjectNode();
        UserEntity currentUser = userDao.findUserByName(username);
        String[] pages = null;
        if (currentUser == null) {
            responseInfo.createFailedResponse("","用户名或密码错误");
            return responseInfo;
        }
        String temp = null;
        try {
            temp = EnCode.encodePass(password.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (temp == null || !temp.equals(currentUser.getPassword())) {
            responseInfo.createFailedResponse("","用户名或密码错误");
            return responseInfo;
        }

        objectNode.put("userId", currentUser.getUserId());
        objectNode.put("userName", currentUser.getUserName());

        RoleEntity roleEntity = roleDao.get(Long.valueOf(currentUser.getRole_id()));
        PrivilegeMapEntity privilegeMapEntity = null;
        if (roleEntity != null) {
            privilegeMapEntity = privilegeMapDao.get(Long.valueOf(roleEntity.getPrivilege_id()));
            objectNode.put("roleName", roleEntity.getRoleName());
        }
        if (privilegeMapEntity != null) {
            pages = privilegeMapEntity.getPageNum().split(",");
            for (String each : pages) {
                ObjectNode page = mapper.createObjectNode();
                page.put("pageNum",each);
                arrayNode.add(page);
            }
            objectNode.set("privileges", arrayNode);
        }
        responseInfo.createSuccessResponse(objectNode);
        return responseInfo;
    }
    
    public ResponseInfo register (String username, String password, String email, String mobile, String nickname) {
        ResponseInfo responseInfo = new ResponseInfo();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(username);
        userEntity.setDisplayName(nickname);
        try {
            userEntity.setPassword(EnCode.encodePass(password.getBytes()));
        } catch (Exception e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return responseInfo;
        }
        userEntity.setEmail(email);
        userEntity.setMobile(mobile);
        userEntity.setCreateTime(new Date());
        userEntity.setRole_id("5");
        userDao.create(userEntity);

        responseInfo.createSuccessResponse("");
        return responseInfo;
    }

    private String getPageString (PageList pages) {
        StringBuilder pageNum = new StringBuilder();
        boolean isFirst = true;
        for (Page each : pages.getPageList()) {
            if (isFirst) {
                pageNum.append(each.getPageNum());
                isFirst = false;
            } else {
                pageNum.append(",");
                pageNum.append(each.getPageNum());
            }
        }
        return pageNum.toString();
    }
}
