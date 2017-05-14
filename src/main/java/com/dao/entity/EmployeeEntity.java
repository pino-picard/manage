package com.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by caoxiao on 2017/4/16.
 */
@Entity(name = "employee")
public class EmployeeEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_sex")
    private boolean employeeSex;

    @Column(name = "employee_birthday")
    private Date employeeBirthday;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_tel")
    private String employeeTel;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_edu")
    private String employeeEdu;

    @Column(name = "employee_school")
    private String employeeSchool;

    @Column(name = "employee_speciality")
    private String employeeSpeciality;

    @Column(name = "work_startTime")
    private Date workStartTime;

    @Column(name = "work_endTime")
    private Date workEndTime;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "project_startTime")
    private Date projectStartTime;

    @Column(name = "project_endTime")
    private Date projectEndTime;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "language_description")
    private String languageDescription;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "employee_state")
    private String employeeState;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "company_id")
    private String companyId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public boolean isEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(boolean employeeSex) {
        this.employeeSex = employeeSex;
    }

    public Date getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(Date employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeTel() {
        return employeeTel;
    }

    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeEdu() {
        return employeeEdu;
    }

    public void setEmployeeEdu(String employeeEdu) {
        this.employeeEdu = employeeEdu;
    }

    public String getEmployeeSchool() {
        return employeeSchool;
    }

    public void setEmployeeSchool(String employeeSchool) {
        this.employeeSchool = employeeSchool;
    }

    public String getEmployeeSpeciality() {
        return employeeSpeciality;
    }

    public void setEmployeeSpeciality(String employeeSpeciality) {
        this.employeeSpeciality = employeeSpeciality;
    }

    public Date getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Date getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(Date workEndTime) {
        this.workEndTime = workEndTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(Date projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public Date getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(Date projectEndTime) {
        this.projectEndTime = projectEndTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getLanguageDescription() {
        return languageDescription;
    }

    public void setLanguageDescription(String languageDescription) {
        this.languageDescription = languageDescription;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
