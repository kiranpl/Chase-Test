package com.chase.framework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lakshmi Pannala
 * Helper class
 */
public class Helper {
    public static void takeScreenshot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot)Browser.getDriver()).getScreenshotAs(OutputType.FILE);

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName + "_" + timeStamp + ".png"));
    }
}