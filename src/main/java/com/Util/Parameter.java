package com.Util;

import java.util.List;

/**
 * Created by caoxiao on 2017/4/16.
 */
public final class Parameter {

    /**
     * 使用说明：
     * 1. 只填写name, 运算符: dbType
     * 2. 填写name, value和value2， 运算符： between
     * 3. 只填写 parameterList， 对其中所有规则进行或运算
     * 4. 填写parameterList和parameterList2， 对两个list进行或运算
     * 5. 只填写name, value并且value为list， 运算符： in
     * 6. 填写name, value, dtdbType, 根据dtdbType运算value的值
     */
    private String name;
    private Object value;
    private Object value2;
    private DBType dtdbType;
    private List<Parameter> parameterList;
    private List<Parameter> parameterList2;

    public Parameter(String name, Object value, Object value2, DBType dtdbType) {
        this.name = name;
        this.value = value;
        this.value2 = value2;
        this.dtdbType = dtdbType;
    }

    public Parameter(String name, Object value, Object value2) {
        this(name, value, value2, null);
    }

    public Parameter(String name, Object value) {
        this(name, value, null, null);
    }

    public Parameter(String name, Object value, DBType dtdbType) {
        this(name, value, null, dtdbType);
    }

    public Parameter(String name) {
        this(name, null, null);
    }

    public Parameter(List<Parameter> parameterList, List<Parameter> parameterList2) {
        this.parameterList = parameterList;
        this.parameterList2 = parameterList2;
    }

    public Parameter(List<Parameter> parameterList, DBType dtdbType) {
        this.parameterList = parameterList;
        this.dtdbType = dtdbType;
    }

    public Parameter(List<Parameter> parameterList) {
        this.parameterList = parameterList;
        this.parameterList2 = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    public DBType getDtdbType() {
        return dtdbType;
    }

    public void setDtdbTypee(DBType dtdbType) {
        this.dtdbType = dtdbType;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }


    public List<Parameter> getParameterList2() {
        return parameterList2;
    }

    public void setParameterList2(List<Parameter> parameterList2) {
        this.parameterList2 = parameterList2;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isValue2Null() {
        return value2 == null;
    }

    public boolean isOrType() {
        return parameterList != null;
    }

    public boolean is2OrType() {
        return parameterList != null && parameterList2 != null;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", value2=" + value2 +
                ", dtdbType=" + dtdbType +
                ", orParameterList=" + parameterList +
                '}';
    }
}