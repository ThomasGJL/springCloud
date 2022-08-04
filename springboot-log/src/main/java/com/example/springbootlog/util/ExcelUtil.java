package com.example.springbootlog.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static void fillReportWithEasyExcel(HttpServletResponse response, Map<String, List<?>> sheetAndDataMap,
                                               Map<String, String> map, String filename, InputStream inputStream){
        ExcelWriter excelWriter = null;
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
            for(Map.Entry<String, List<?>> entry : sheetAndDataMap.entrySet()){
                List<?> value = entry.getValue();
                WriteSheet writeSheet = EasyExcel.writerSheet(Integer.valueOf(entry.getKey())).build();
                FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
                excelWriter.fill(value, fillConfig, writeSheet);
                excelWriter.fill(map, writeSheet);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            excelWriter.finish();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {



    }
}
