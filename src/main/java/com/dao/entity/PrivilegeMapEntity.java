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
}
