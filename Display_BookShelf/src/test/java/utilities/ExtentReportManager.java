package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
import java.net.URL;

// Extent report 5.x version
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;   // UI of the report
    public ExtentReports extent;                // Populate common info of the report
    public ExtentTest test;  // Create test case entries in the report and update status of test methods

    String repName;

    // This method is invoked when the test starts
    public void onStart(ITestContext testContext) {
        // Generate a timestamp for the report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        
        // Initialize the ExtentSparkReporter with the report file path
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Configure the report
        sparkReporter.config().setDocumentTitle("UrbanLadder Automation Report"); // Title of the report
        sparkReporter.config().setReportName("UrbanLadder Functional Testing"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK); // Theme of the report

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set system information for the report
        extent.setSystemInfo("Application", "UrbanLadder");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Get OS and browser information from test context parameters
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // Include groups information if available
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    // This method is invoked when a test method succeeds
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // Display groups in the report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    // This method is invoked when a test method fails
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            // Capture screenshot on failure
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // This method is invoked when a test method is skipped
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // This method is invoked when all tests are finished
    public void onFinish(ITestContext testContext) {
        extent.flush(); // Write everything to the report

        // Automatically open the report in the default browser
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
