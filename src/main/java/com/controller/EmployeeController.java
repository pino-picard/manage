package com.controller;

import com.Util.JsonUtil;
import com.Util.ResponseInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.model.EmployeeModel;
import com.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by caoxiao on 2017/5/9.
 */
@Controller
public class EmployeeController {

    @Resource
    EmployeeService employeeService;

    @RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET)
    @ResponseBody
    public String getEmployeeList (@RequestParam String employeeName,
                                   @RequestParam String company,
                                   @RequestParam String department,
                                   @RequestParam String age) {
        ResponseInfo responseInfo = new ResponseInfo();



        return JsonUtil.toJson(responseInfo);
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String addEmployee (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode node = mapper.createArrayNode();
        ObjectNode test = mapper.createObjectNode();

        EmployeeModel newEmployee;
        try {
            newEmployee = mapper.readValue(requestStr, EmployeeModel.class);
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.toJson(responseInfo);
        }

        if (newEmployee != null) {
            employeeService.addEmployee(newEmployee);
        }

        return JsonUtil.toJson(responseInfo);
    }

    @RequestMapping(value = "/modifyEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String modifyEmployee (@RequestBody String requestStr) {
        ResponseInfo responseInfo = new ResponseInfo();



        return JsonUtil.toJson(responseInfo);
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteEmployee (@RequestParam String employeeId) {
        ResponseInfo responseInfo = new ResponseInfo();



        return JsonUtil.toJson(responseInfo);
    }

}
