package com.common.bean;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Map;


public class ExcelFileView extends AbstractView implements Constants {
    public static final String CONTENT_TYPE = "application/msexcel";

    public ExcelFileView() {
        setContentType(CONTENT_TYPE);
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String fileName = (String) model.get(EXCEL_FILE_NAME);
        String filePath = (String) model.get(EXCEL_FILE_PATH);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "iso8859-1") + ".xls");
        response.setContentType(getContentType());
        FileInputStream in = new FileInputStream(filePath);
        byte b[] = new byte[1024];
        int i = 0;
        ServletOutputStream out = response.getOutputStream();
        while ((i = in.read(b)) != -1) {
            out.write(b, 0, i);
        }
        out.flush();
        out.close();
        in.close();
    }
}
