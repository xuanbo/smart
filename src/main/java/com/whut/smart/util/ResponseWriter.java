package com.whut.smart.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 不关闭HttpServletResponse
 *
 * Created by null on 2016/12/31.
 */
public class ResponseWriter {

    private HttpServletResponse response;

    public ResponseWriter(HttpServletResponse response) {
        this.response = response;
    }

    public void writerJson(String json) {
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
