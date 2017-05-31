package com.Util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by caoxiao on 2017/5/25.
 */
public class PageUtil {
    private static final String PAGE_NAME = "/page.json";
    private static PageUtil ourInstance = new PageUtil();
    private static PageList pageList;

    public static PageUtil getInstance() {
        return ourInstance;
    }

    private PageUtil() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        try {
            InputStream inputFile = PageUtil.class.getResourceAsStream(PAGE_NAME);
            String jsonData = IOUtils.toString(inputFile, "UTF-8");
            pageList = mapper.readValue(jsonData,PageList.class);
            System.out.print(ourInstance.pageList.getPageList().get(0).getPageName());
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PageList getPageList() {
        return pageList;
    }

    public void setPageList(PageList pageList) {
        this.pageList = pageList;
    }
}
