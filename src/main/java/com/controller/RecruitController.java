package com.controller;

import com.Util.JsonUtil;
import com.Util.ResponseInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.model.RecruitModel;
import com.service.RecruitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by caoxiao on 2017/5/29.
 */
@Controller
public class RecruitController {

    @Resource
    RecruitService recruitService;

    @RequestMapping(value = "/createRecruit", method = RequestMethod.POST)
    @ResponseBody
    public String createRecruit (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode objectNode = mapper.createObjectNode();

        RecruitModel recruitModel;
        try {
            recruitModel = mapper.readValue(requestStr, RecruitModel.class);
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }

        boolean result = false;
        if (recruitModel != null) {
            result = recruitService.createRecruit(recruitModel.getTitle(),recruitModel.getReason(),
                    recruitModel.getDescription(),recruitModel.getApprover(),recruitModel.getDeadline(),recruitModel.getApplyPerson());
        }
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","招聘信息创建失败");
        }
        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/deleteRecruit", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRecruit (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        String recruitId;
        try {
            ObjectNode root = mapper.readValue(requestStr, ObjectNode.class);
            JsonNode params = root.path("params");
            recruitId = params.path("recruitId").asText();
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }
        boolean result = recruitService.deleteRecruit(recruitId);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","招聘信息删除失败");
        }

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/getRecruitList", method = RequestMethod.GET)
    @ResponseBody
    public String getRecruitList (@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String applyPerson,
                                  @RequestParam(required = false) String status) {
        ResponseInfo responseInfo = recruitService.getRecruitList(title, applyPerson, status);

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/setRecruitStatus", method = RequestMethod.POST)
    @ResponseBody
    public String getRecruitInfo (@RequestParam String recruitId,
                                  @RequestParam String status) {
        ResponseInfo responseInfo = new ResponseInfo();
        boolean result = recruitService.setRecruitStatus(recruitId,status);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","变更招聘信息状态删除失败");
        }

        return JsonUtil.getJsonStr(responseInfo);
    }

    @RequestMapping(value = "/approvalRecruit", method = RequestMethod.POST)
    @ResponseBody
    public String getRecruitInfo (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        String recruitId;
        String approvalStatus;
        String approvalResult;
        try {
            ObjectNode root = mapper.readValue(requestStr, ObjectNode.class);
            JsonNode params = root.path("params");
            recruitId = params.path("recruitId").asText();
            approvalStatus = params.path("approvalStatus").asText();
            approvalResult = params.path("approvalResult").asText();
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.getJsonStr(responseInfo);
        }
        boolean result = recruitService.approvalRecruit(recruitId,approvalStatus,approvalResult);
        if (result) {
            responseInfo.createSuccessResponse("");
        } else {
            responseInfo.createFailedResponse("","审批提交失败");
        }

        return JsonUtil.getJsonStr(responseInfo);
    }
}
