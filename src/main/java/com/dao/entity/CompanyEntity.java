package com.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by caoxiao on 2017/4/16.
 */
@Entity(name = "company")
public class CompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "compant_scale")
    private String companyScale;

    @Column(name = "industry")
    private String industry;

    @Column(name = "description")
    private String description;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
