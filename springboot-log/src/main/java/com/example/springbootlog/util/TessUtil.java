package com.example.springbootlog.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.math.BigDecimal;

public class TessUtil {

    public static void main(String[] args) {

        String path = "C:\\image.png";
        String lagnguagePath = "C:\\tessdata";

        File file = new File(path);
        ITesseract instance = new Tesseract();

        instance.setDatapath(lagnguagePath);

        instance.setLanguage("eng");
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result =  instance.doOCR(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        System.out.println("result: ");
        System.out.println(result);


        BigDecimal paymentAmount = new BigDecimal("3823212500");
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal rePaymentAmount = paymentAmount.divide(divisor);
        rePaymentAmount = rePaymentAmount.setScale(2);
        String newPaymentAmount = rePaymentAmount.toPlainString();

        System.out.println(newPaymentAmount);
    }
}
