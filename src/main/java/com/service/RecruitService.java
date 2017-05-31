package com.service;

import com.Util.DataConvert;
import com.Util.RecruitStatus;
import com.Util.ResponseInfo;
import com.dao.RecruitDao;
import com.dao.entity.RecruitEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by caoxiao on 2017/5/31.
 */
@Component(value = "recruitService")
public class RecruitService {

    @Resource
    RecruitDao recruitDao;

    @Resource
    DataConvert dateConvert;

    public boolean createRecruit (String title,String reason,String description, String approver, String deadline, String applyPerson) {
        try {
            RecruitEntity recruitEntity = new RecruitEntity();
            recruitEntity.setRecruitTitle(title);
            recruitEntity.setDescription(description);
            recruitEntity.setApplyReason(reason);
            recruitEntity.setApprover(approver);
            recruitEntity.setApplyPerson("pino");
            recruitEntity.setApplyDate(new Date());
            recruitEntity.setDeadline(dateConvert.convertStringToDate(deadline));
            recruitEntity.setState(String.valueOf(RecruitStatus.PENDING_AUDIT.getIndex()));
            recruitDao.create(recruitEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteRecruit (String recruitId) {
        try {
            recruitDao.deleteById(Long.valueOf(recruitId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResponseInfo getRecruitList (String title, String applyPerson, String status) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        try {
            List<RecruitEntity> recruitEntities = recruitDao.getRecruitList(title, applyPerson, status);

            for (RecruitEntity entity : recruitEntities) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("id", entity.getRecruitId());
                objectNode.put("title", entity.getRecruitTitle());
                objectNode.put("applyPerson", entity.getApplyPerson());
                objectNode.put("applyReason", entity.getApplyReason());
                objectNode.put("require", entity.getDescription());
                objectNode.put("applyDate", dateConvert.convertDateToString(entity.getApplyDate()));
                objectNode.put("approveDate", dateConvert.convertDateToString(entity.getApproveDate()));
                objectNode.put("approver", entity.getApprover());
                objectNode.put("status", RecruitStatus.getName(Integer.valueOf(entity.getState())));
                arrayNode.add(objectNode);
            }
        } catch (Exception e) {
            responseInfo.createFailedResponse("", e.getMessage());
        }

        responseInfo.createSuccessResponse(arrayNode);
        return  responseInfo;
    }

    public boolean setRecruitStatus (String recruitId,String status) {
        try {
            RecruitEntity recruitEntity = recruitDao.get(Long.valueOf(recruitId));
            recruitEntity.setState(status);
            recruitDao.update(recruitEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean approvalRecruit (String recruitId, String approvalStatus, String approvalResult) {
        try {
            RecruitEntity recruitEntity = recruitDao.get(Long.valueOf(recruitId));
            recruitEntity.setState(approvalStatus);
            recruitEntity.setApproveResult(approvalResult);
            recruitEntity.setApproveDate(new Date());
            recruitDao.update(recruitEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
