package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extentReports;

    public static ExtentReports getiInstanceOfExtentReports(String suitename){
        if(extentReports==null)
            createInstanceOfReport(suitename);
        return extentReports;
    }

    public static ExtentReports createInstanceOfReport(String suiteName){
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy_HHmmss");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                suiteName+"-"+simpleDateFormat.format(currentDate)+".html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setReportName("Report for UI tests");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("UI tests");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("UI tests report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        return extentReports;
    }

}
