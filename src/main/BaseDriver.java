package main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


import generics.Excel;

public class BaseDriver implements AutomationConstants {
	
	
	public WebDriver driver;
	public  ExtentReports eReport;
	public  ExtentTest testReport;
	

	
	@DataProvider
	public String[][] getScenarios()
	{
		int scenarioCount=Excel.getRowCount(controllerPath,suiteSheet);
		String[][] data =new String[scenarioCount][3];
		for(int i=1;i<=scenarioCount;i++)
		{
		String scenarioName=Excel.getCellValue(controllerPath,suiteSheet,i,0);
		String executionStatus=Excel.getCellValue(controllerPath,suiteSheet,i,1);
		String formName=Excel.getCellValue(controllerPath,suiteSheet,i,2);
		data[i-1][0]=scenarioName;
		data[i-1][1]=executionStatus;
		data[i-1][2]=formName;
		}
	  
		return data;
		
	}
	
	@BeforeSuite
	public void initFrameWork()
	{
		 eReport=new ExtentReports("C:\\Reports\\BMOESF.html");
		
		
//		try {
//			FileUtils.deleteDirectory(new File("./test-output"));
//		} catch (IOException e) {
//		}
	}
	
	
	@AfterSuite
	public void endFrameWork()
	{
		eReport.flush();
//		try {
//			FileUtils.cleanDirectory(new File(AutomationConstants.CsvFolderpath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	}



