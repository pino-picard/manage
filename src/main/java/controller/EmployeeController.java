package controller;

import Util.JsonUtil;
import Util.ResponseInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.entity.EmployeeEntity;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by caoxiao on 2017/5/9.
 */
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
        node.add(test);
        responseInfo.createSuccessResponse(node);

        EmployeeEntity newEmployee = new EmployeeEntity();
        try {
            newEmployee = mapper.readValue(requestStr,EmployeeEntity.class);
        } catch (IOException e) {
            responseInfo.createFailedResponse("", e.getMessage());
            return JsonUtil.toJson(responseInfo);
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
