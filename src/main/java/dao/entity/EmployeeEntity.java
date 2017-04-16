package dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by caoxiao on 2017/4/16.
 */
@Entity
public class EmployeeEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_sex")
    private boolean employeeSex;

    @Column(name = "employee_birthday")
    private Date employeeBirthday;

    @Column(name = "employee_work_num")
    private String employeeWorkNum;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_tel")
    private String employeeTel;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_qq")
    private String employeeQQ;

    @Column(name = "employee_national")
    private String employeeNational;

    @Column(name = "employee_marriage")
    private String employeeMarriage;

    @Column(name = "employee_political")
    private String employeePolitical;

    @Column(name = "employee_edu")
    private String employeeEdu;

    @Column(name = "employee_degree")
    private String employeeDegree;

    @Column(name = "employee_school")
    private String employeeSchool;

    @Column(name = "employee_graduation_date")
    private String employeeGraduationDate;

    @Column(name = "employee_speciality")
    private String employeeSpeciality;

    @Column(name = "employee_state")
    private String employeeState;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "company_id")
    private String companyId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
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

    public String getEmployeeWorkNum() {
        return employeeWorkNum;
    }

    public void setEmployeeWorkNum(String employeeWorkNum) {
        this.employeeWorkNum = employeeWorkNum;
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

    public String getEmployeeQQ() {
        return employeeQQ;
    }

    public void setEmployeeQQ(String employeeQQ) {
        this.employeeQQ = employeeQQ;
    }

    public String getEmployeeNational() {
        return employeeNational;
    }

    public void setEmployeeNational(String employeeNational) {
        this.employeeNational = employeeNational;
    }

    public String getEmployeeMarriage() {
        return employeeMarriage;
    }

    public void setEmployeeMarriage(String employeeMarriage) {
        this.employeeMarriage = employeeMarriage;
    }

    public String getEmployeePolitical() {
        return employeePolitical;
    }

    public void setEmployeePolitical(String employeePolitical) {
        this.employeePolitical = employeePolitical;
    }

    public String getEmployeeEdu() {
        return employeeEdu;
    }

    public void setEmployeeEdu(String employeeEdu) {
        this.employeeEdu = employeeEdu;
    }

    public String getEmployeeDegree() {
        return employeeDegree;
    }

    public void setEmployeeDegree(String employeeDegree) {
        this.employeeDegree = employeeDegree;
    }

    public String getEmployeeSchool() {
        return employeeSchool;
    }

    public void setEmployeeSchool(String employeeSchool) {
        this.employeeSchool = employeeSchool;
    }

    public String getEmployeeGraduationDate() {
        return employeeGraduationDate;
    }

    public void setEmployeeGraduationDate(String employeeGraduationDate) {
        this.employeeGraduationDate = employeeGraduationDate;
    }

    public String getEmployeeSpeciality() {
        return employeeSpeciality;
    }

    public void setEmployeeSpeciality(String employeeSpeciality) {
        this.employeeSpeciality = employeeSpeciality;
    }

    public String getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
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
