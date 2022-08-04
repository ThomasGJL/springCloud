package com.example.springbootlog.util;

import lombok.extern.slf4j.Slf4j;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;

@Slf4j
public class DocxUtil {

    private static boolean createDocx(String fileSavePath, String fileSaveName) {

        try {
            if (!fileSaveName.substring(fileSaveName.indexOf(".")).equalsIgnoreCase(".docx"))
                fileSaveName += ".docx";

            if (fileSavePath.endsWith("/") || fileSavePath.endsWith("\\")) {
                fileSavePath += fileSaveName;
            } else {
                fileSavePath += File.separator + fileSaveName;
            }

            WordprocessingMLPackage wordPackage = wordPackage = WordprocessingMLPackage.createPackage();
            wordPackage.save(new File(fileSavePath));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("create docx fail:" + e.getMessage());
            return false;
        }
    }


    public static void main(String[] args) {

        DocxUtil.createDocx("C:\\Payment\\", "test.docx");
    }

}
