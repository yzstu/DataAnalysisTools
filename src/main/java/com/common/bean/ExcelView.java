package com.common.bean;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

public class ExcelView extends AbstractView implements Constants {
    public static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";

    public ExcelView() {
        setContentType(EXCEL_CONTENT_TYPE);
    }

    public String getContentType() {
        return EXCEL_CONTENT_TYPE;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String fileName = (String) model.get(EXCEL_FILE_NAME);
        String sheetName = (String) model.get(EXCEL_SHEET_NAME);
        String[][] content = (String[][]) model.get(EXCEL_CONTENT); // content[行][列]
        String contentTitle = (String) model.get("EXCEL_CONTENT_TITLE");

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "iso8859-1") + ".xls");
        response.setContentType(getContentType());
        OutputStream os = response.getOutputStream();

        WritableWorkbook book = Workbook.createWorkbook(os); // 创建工作薄
        WritableSheet sheet = book.createSheet(sheetName, 0); // 创建工作表
        // ；目前只支持一个工作表
        if (EXCEL_CONTENT_TITLE_COLUMN.equals(contentTitle)) {
            for (int i = 0; i < content.length; i++) {
                String[] col = content[i];
                for (int j = 0; j < col.length; j++) { // 将每列的标题名称放在第一位，content[n][0]
                    Label label = new Label(i, j, col[j]); // 列， 行
                    sheet.addCell(label);
                }
            }
        } else {
            for (int i = 0; i < content.length; i++) {
                String[] col = content[i];
                for (int j = 0; j < col.length; j++) { // 将每列的标题名称放在第一位，content[n][0]
                    Label label = new Label(j, i, col[j]); // 列， 行
                    sheet.addCell(label);
                }
            }
        }
        book.write();
        book.close();

        os.flush();
        os.close();
    }
}
