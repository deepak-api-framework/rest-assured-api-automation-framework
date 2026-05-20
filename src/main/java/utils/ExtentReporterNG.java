package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {

        String reportPath =
                System.getProperty("user.dir")
                + "/reports/index.html";

        ExtentSparkReporter reporter =
                new ExtentSparkReporter(reportPath);

        reporter.config().setReportName("API Automation Results");

        reporter.config().setDocumentTitle("Rest Assured Framework Report");

        ExtentReports extent = new ExtentReports();

        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Deepak");

        return extent;
    }
}