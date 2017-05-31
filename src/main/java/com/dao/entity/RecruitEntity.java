package com.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by caoxiao on 2017/5/22.
 */
@Entity(name = "recruit")
public class RecruitEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recruitId;

    @Column(name = "recruit_title")
    private String recruitTitle;

    @Column(name = "apply_person")
    private String applyPerson;

    @Column(name = "apply_date")
    private Date applyDate;

    @Column(name = "apply_reason", columnDefinition="TEXT")
    private String applyReason;

    @Column(name = "state")
    private String state;

    @Column(name = "require_description", columnDefinition="TEXT")
    private String description;

    @Column(name = "approver")
    private String approver;

    @Column(name = "approve_date")
    private Date approveDate;

    @Column(name = "approve_result")
    private String approveResult;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "deadline")
    private Date deadline;

    public Long getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Long recruitId) {
        this.recruitId = recruitId;
    }

    public String getRecruitTitle() {
        return recruitTitle;
    }

    public void setRecruitTitle(String recruitTitle) {
        this.recruitTitle = recruitTitle;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


}
