package com.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by caoxi on 2017/4/28.
 */
@Entity(name = "privilege")
public class PrivilegeMapEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "map_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mapId;

    @Column(name = "page_num")
    private String pageNum;

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
