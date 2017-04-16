package Util;

/**
 * Created by caoxiao on 2017/4/16.
 */
public enum DBType {

    /**
     * 精确匹配，默认值
     */
    EXACT,

    /**
     * 模糊匹配
     */
    ANYWHERE,

    /**
     * 大于
     */
    GREATER,

    /**
     * 大于等于
     */
    EQUAL_GREATER,

    /**
     * 小于
     */
    LESS,

    /**
     * 小于等于
     */
    EQUAL_LESS,

    /**
     * 查询@ElementCollection
     */
    COLLECTION_ELEMENT,

    COLLECTION_ELEMENT_EQUAL,

    /**
     * value为null或者相等
     */
    EQUAL_NULL,

    /**
     * 不等于
     */
    NE,

    /*
    * 不等于null
    */
    NEORISNOTNULL,
    /**
     * 查询过滤关联表字段
     */
    RELATION_TABLE,

    /**
     * list里面是or的关系
     */
    LIST_OR,

    IN_LIST
}

