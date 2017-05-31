package com.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by caoxiao on 2017/4/9.
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static String getJsonStr(Map<String, Object> map){
        String result = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getJsonStr(Object object){
        String result = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("getJsonStr error, e : " + e.getMessage());
        }
        return result;
    }

    public static final ObjectMapper mapper = new ObjectMapper();
    public static void sendResponse(HttpServletResponse response, String jsonContent) {
        if (null == response) {
            return;
        }

        PrintWriter pWriter = null; //��ȡд�����
        try {
            response.setCharacterEncoding("UTF-8"); //���ñ����ʽ
            response.setContentType("application/json");   //�������ݸ�ʽ

            pWriter = response.getWriter();
            pWriter.print(jsonContent); //��json����д������
            pWriter.flush();
        } catch (IOException e) {
            logger.debug("[JsonUtil][sendResponse]:Catch exception {}", e);
        } finally {
            if (null != pWriter) {
                pWriter.close();
            }
        }

    }

    public static void sendResponse(HttpServletResponse response, Object beanObject) {

        try {
            String jsonContent = mapper.writeValueAsString(beanObject);
            sendResponse(response, jsonContent);
        } catch (Exception e) {
            logger.debug("[JsonUtil][sendResponse]:Catch exception {}", e);
        }
    }

    public static String getJsonStr(ObjectMapper mapper, ObjectNode map){
        String resultStr = "";
        try {
            resultStr = mapper.writeValueAsString(map);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultStr;
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) throws Exception {

        return mapper.readValue(jsonStr, valueType);
    }

    public static <T> T readSafeValue(String jsonStr, Class<T> valueType) {
        T result = null;
        try {
            result = mapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            logger.error("readSafeValue error : " + e.getMessage());
        }

        return result;
    }
}
