package com.service;

import com.Util.DataConvert;
import com.dao.CompanyDao;
import com.dao.DepartmentDao;
import com.dao.EmployeeDao;
import com.dao.entity.CompanyEntity;
import com.dao.entity.DepartmentEntity;
import com.dao.entity.EmployeeEntity;
import com.model.EmployeeModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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


}
