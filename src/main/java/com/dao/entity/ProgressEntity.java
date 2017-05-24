package com.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by caoxiao on 2017/5/22.
 */
@Entity(name = "progress")
public class ProgressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "progress_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long progressId;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "progress_status")
    private String status;

    @Column(name = "feedback_info", columnDefinition="TEXT")
    private String feedback;

    @Column(name = "secret_id")
    private String secret_id;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSecret_id() {
        return secret_id;
    }

    public void setSecret_id(String secret_id) {
        this.secret_id = secret_id;
    }
}
