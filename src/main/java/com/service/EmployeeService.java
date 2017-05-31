package com.service;

import com.Util.DataConvert;
import com.Util.ResponseInfo;
import com.dao.CompanyDao;
import com.dao.DepartmentDao;
import com.dao.EmployeeDao;
import com.dao.entity.CompanyEntity;
import com.dao.entity.DepartmentEntity;
import com.dao.entity.EmployeeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.model.EmployeeModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoxiao on 2017/5/9.
 */
@Component(value = "employeeService")
public class EmployeeService {

    @Resource
    DataConvert dataConvert;

    @Resource
    EmployeeDao employeeDao;

    @Resource
    CompanyDao companyDao;

    @Resource
    DepartmentDao departmentDao;


    public void addEmployee (EmployeeModel newEmployee) {

        EmployeeEntity employeeEntity = transformModelToEntity(newEmployee);

        DepartmentEntity departmentEntity = departmentDao.findDepartmentByName(employeeEntity.getDepartmentName());
        CompanyEntity companyEntity = companyDao.findCompanyByName(employeeEntity.getCompanyName());

        if (companyEntity == null) {
            CompanyEntity newEntity = new CompanyEntity();
            newEntity.setCompanyName(employeeEntity.getCompanyName());
            companyDao.create(newEntity);
            companyEntity = companyDao.findCompanyByName(employeeEntity.getCompanyName());
        }
        if (departmentEntity == null) {
            DepartmentEntity newEntity = new DepartmentEntity();
            newEntity.setDepartmentName(employeeEntity.getDepartmentName());
            newEntity.setCompanyId(companyEntity.getCompanyId().toString());
            departmentDao.create(newEntity);
            departmentEntity = departmentDao.findDepartmentByName(employeeEntity.getDepartmentName());
        }

        employeeEntity.setDepartmentId(departmentEntity.getDepartmentId().toString());
        employeeEntity.setCompanyId(companyEntity.getCompanyId().toString());
        employeeDao.create(employeeEntity);
    }

    public List<EmployeeEntity> getEmployees (String employeeName,String company,String department,String telNum,String recruitId) {
        List<EmployeeEntity> targetList = employeeDao.describeEmployee(employeeName,company,department,telNum,recruitId);

        return targetList;
    }


    private EmployeeEntity transformModelToEntity (EmployeeModel employeeModel) {
        EmployeeEntity targetEntity = new EmployeeEntity();

        targetEntity.setEmployeeName(employeeModel.getName());
        targetEntity.setEmployeeSex(dataConvert.convertSex(employeeModel.getSex()));
        targetEntity.setEmployeeBirthday(dataConvert.convertStringToDate(employeeModel.getBirthday()));
        targetEntity.setEmployeeSchool(employeeModel.getSchool());
        targetEntity.setEmployeeEdu(employeeModel.getEducation());
        targetEntity.setEmployeeSpeciality(employeeModel.getSpecialty());
        targetEntity.setEmployeeTel(employeeModel.getTelNum());
        targetEntity.setEmployeeEmail(employeeModel.getEmail());
        targetEntity.setWorkStartTime(dataConvert.convertStringToDate(employeeModel.getWorkStartTime()));
        targetEntity.setWorkEndTime(dataConvert.convertStringToDate(employeeModel.getWorkEndTime()));
        targetEntity.setCompanyName(employeeModel.getCompanyName());
        targetEntity.setDepartmentName(employeeModel.getDepartmentName());
        targetEntity.setJobName(employeeModel.getJobName());
        targetEntity.setJobDescription(employeeModel.getJobDescription());
        targetEntity.setProjectStartTime(dataConvert.convertStringToDate(employeeModel.getProjectStartTime()));
        targetEntity.setProjectEndTime(dataConvert.convertStringToDate(employeeModel.getProjectEndTime()));
        targetEntity.setProjectName(employeeModel.getProjectName());
        targetEntity.setProjectDescription(employeeModel.getProjectDescription());
        targetEntity.setLanguageDescription(employeeModel.getLanguageDescription());
        targetEntity.setSkillDescription(employeeModel.getSkillDescription());

        return targetEntity;
    }


    public ResponseInfo getEmployeeTree () {
        ResponseInfo responseInfo = new ResponseInfo();
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        try {
            List<EmployeeEntity> EmployeeEntities = employeeDao.get();
            List<CompanyEntity> CompanyEntities = companyDao.get();
            List<DepartmentEntity> DepartmentEntities = departmentDao.get();

            for (CompanyEntity company : CompanyEntities) {
                ObjectNode companyNode = mapper.createObjectNode();
                ArrayNode comp = mapper.createArrayNode();

                for (DepartmentEntity department : DepartmentEntities) {
                    if (department.getCompanyId().equals(company.getCompanyId().toString())) {
                        ObjectNode departNode = mapper.createObjectNode();
                        ArrayNode depart = mapper.createArrayNode();

                        for (EmployeeEntity employee : EmployeeEntities) {
                            if (employee.getDepartmentId().equals(department.getDepartmentId().toString())) {
                                ObjectNode emp = mapper.createObjectNode();
                                emp.put("label", employee.getEmployeeName());
                                depart.add(emp);
                            }
                        }

                        departNode.put("label", department.getDepartmentName());
                        departNode.set("children", depart);
                        comp.add(departNode);

                    }
                }
                companyNode.put("label",company.getCompanyName());
                companyNode.set("children",comp);
                arrayNode.add(companyNode);

            }
        } catch (Exception e) {
            responseInfo.createFailedResponse("", e.getMessage());
        }

        responseInfo.createSuccessResponse(arrayNode);
        return  responseInfo;
    }
}
