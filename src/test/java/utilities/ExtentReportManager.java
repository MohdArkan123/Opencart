package utilities;

import java.io.*;
import java.awt.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener 
{
	
	

	public ExtentSparkReporter sparkRepoter;//UI of the Report
	public ExtentReports extent;//Popular common Information on the report
	public ExtentTest test;//Creating test case entries in the report and update status of the test methods.
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentDateTimeStamp=df.format(dt);
		*/
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//TimeStamp
		
		repName="Test-Report-"+timeStamp+".html";
		sparkRepoter=new ExtentSparkReporter(".\\reports\\"+repName);//specific location of the report where report will save.
		
		sparkRepoter.config().setDocumentTitle("OpenCart Automation Report"); //Title of Report
		sparkRepoter.config().setReportName("OpenCart Functional Testing");  //Name of Report
		sparkRepoter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(sparkRepoter);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) 
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());//Create a new Entry in the Report
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS, "Test Case Passed is: "+result.getName());// Update Status p/f/s
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "Test Case Failed is:"+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try 
		{
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		
		catch(Exception e) {
			e.printStackTrace(); 
		}
		
		
	  }
	
	
	public void onTestSkipped(ITestResult result) {
	    test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    
	    test.log(Status.SKIP, result.getName()+"got skipped");
	    test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	
	 public void onFinish(ITestContext context) {
		    extent.flush();
		    
		    String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		    File extentReport=new File(pathOfExtentReport);
		    
		    try
		    {
		    	Desktop.getDesktop().browse(extentReport.toURI());
		    }
		    catch(Exception e) 
		    {
		    	e.printStackTrace();
		    }
		    
		    
		  }

}
