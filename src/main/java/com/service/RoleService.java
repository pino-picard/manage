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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by caoxiao on 2017/5/29.
 */
@Component(value = "roleService")
public class RoleService {

    @Resource
    UserDao userDao;

    @Resource
    RoleDao roleDao;

    @Resource
    PrivilegeMapDao privilegeMapDao;

    @Resource
    DataConvert dataConvert;

    public ResponseInfo getUserList (String userName, String roleName) {
        ResponseInfo responseInfo = new ResponseInfo();
        HashSet<String> roleSet = new HashSet<>();
        List<RoleEntity> roleEntityList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        try {
            List<UserEntity> userEntities = userDao.getUserList(userName, roleName);

            for (UserEntity user : userEntities) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("user_name", user.getUserName());
                objectNode.put("email", user.getEmail());
                objectNode.put("mobile", user.getMobile());
                objectNode.put("display_name", user.getDisplayName());
                objectNode.put("role_name", roleDao.getRoleNameById(user.getRole_id()));
                objectNode.put("create_time", dataConvert.convertDateToString(user.getCreateTime()));
                arrayNode.add(objectNode);
            }
        } catch (Exception e) {
            responseInfo.createFailedResponse("", e.getMessage());
        }

        responseInfo.createSuccessResponse(arrayNode);
        return  responseInfo;
    }
    
    public boolean createUser (String userName, String displayName, String email, String mobile, String password, String roleId) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName);
            userEntity.setDisplayName(displayName);
            userEntity.setEmail(email);
            userEntity.setMobile(mobile);
            userEntity.setPassword(EnCode.encodePass(password.getBytes()));
            userEntity.setRole_id(roleId);
            userEntity.setCreateTime(new Date());
            userDao.create(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser (String userName) {
        try {
            UserEntity deleteEntity = userDao.findUserByName(userName);
            userDao.delete(deleteEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResponseInfo getRoleList () {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        try {
            List<RoleEntity> roleEntities = roleDao.get();

            for (RoleEntity roleEntity : roleEntities) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("roleName",roleEntity.getRoleName());
                objectNode.put("roleId",roleEntity.getRoleId());
                objectNode.put("privileges",roleEntity.getPrivilege_id());
                objectNode.put("createTime",dataConvert.convertDateToString(roleEntity.getCreateTime()));
                arrayNode.add(objectNode);
            }
        } catch (Exception e){
            e.printStackTrace();
            responseInfo.createFailedResponse("",e.getMessage());
        }
        responseInfo.createSuccessResponse(arrayNode);
        return  responseInfo;
    }

    public ResponseInfo getPrivilegeInfo (String privileges) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        String[] pages = null;
        try {
            PageUtil pageUtil = PageUtil.getInstance();
            ArrayList<Page> pageArrayList = pageUtil.getPageList().getPageList();
            HashMap<Integer,String> map = new HashMap<>();
            for (Page each : pageArrayList) {
                map.put(each.getPageNum(), each.getPageCn());
            }
            PrivilegeMapEntity mapEntity = privilegeMapDao.get(Long.valueOf(privileges));
            pages = mapEntity.getPageNum().split(",");
            for (String each : pages) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("pageName",map.get(Integer.valueOf(each)));
                objectNode.put("pageNum",each);
                arrayNode.add(objectNode);
            }
            responseInfo.createSuccessResponse(arrayNode);
        } catch (Exception e) {
            responseInfo.createFailedResponse("",e.getMessage());
        }

        return responseInfo;
    }

    public boolean modifyRole(String roleId, String privilege){
        try {
            RoleEntity roleEntity = roleDao.get(Long.valueOf(roleId));
            PrivilegeMapEntity mapEntity = privilegeMapDao.findMap(privilege);
            if (mapEntity == null) {
                mapEntity = new PrivilegeMapEntity();
                mapEntity.setPageNum(privilege);
                privilegeMapDao.create(mapEntity);
                mapEntity = privilegeMapDao.findMap(privilege);
                roleEntity.setPrivilege_id(mapEntity.getMapId().toString());
            } else {
                roleEntity.setPrivilege_id(mapEntity.getMapId().toString());
            }
            roleDao.update(roleEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteRole (String roleId) {
        try {
            roleDao.deleteById(Long.valueOf(roleId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createRole (String roleName,String privilege) {
        try {
            RoleEntity roleEntity = roleDao.getRoleEntityByName(roleName);
            if (roleEntity != null) {
                return false;
            }
            roleEntity = new RoleEntity();
            PrivilegeMapEntity mapEntity = privilegeMapDao.findMap(privilege);
            if (mapEntity == null) {
                mapEntity = getMapEntity(privilege);
                roleEntity.setPrivilege_id(mapEntity.getMapId().toString());
            } else {
                roleEntity.setPrivilege_id(mapEntity.getMapId().toString());
            }
            roleEntity.setRoleName(roleName);
            roleEntity.setCreateTime(new Date());
            roleDao.create(roleEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private PrivilegeMapEntity getMapEntity (String privilege) {
        PrivilegeMapEntity mapEntity = new PrivilegeMapEntity();
        mapEntity.setPageNum(privilege);
        privilegeMapDao.create(mapEntity);
        mapEntity = privilegeMapDao.findMap(privilege);
        return mapEntity;
    }
}
