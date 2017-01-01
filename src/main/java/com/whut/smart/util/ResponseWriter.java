package com.whut.smart.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 会关闭HttpServletResponse
 *
 * Created by null on 2016/12/31.
 */
public class ResponseWriter {

    private HttpServletResponse response;

    public ResponseWriter(HttpServletResponse response) {
        this.response = response;
    }

    public void writerJson(String json) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
