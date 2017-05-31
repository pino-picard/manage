package com.Util;

/**
 * Created by caoxiao on 2017/5/31.
 */
public enum RecruitStatus {
    PENDING_AUDIT("待审批",1),
    IN_AUDIT("审批中",2),
    AUDIT_FAILD("审批未通过",3),
    AUDIT_SUCCESS("审批通过",4),
    PUBLISHED("已发布",5);

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private RecruitStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (RecruitStatus c : RecruitStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
