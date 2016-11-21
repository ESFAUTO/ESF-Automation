package main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;
import generics.Property;

public class DeltaDriver extends BaseDriver
{

@BeforeClass
public void open() throws MalformedURLException
{   

	String appURL=Property.getPropertyValue(configPptPath,"URL");
	String timeout=Property.getPropertyValue(configPptPath,"TimeOut");
	System.setProperty("webdriver.chrome.driver",chromeDriverPath);	 		 
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(appURL);
	driver.manage().timeouts().implicitlyWait(Long.parseLong(timeout),TimeUnit.SECONDS);
}


@Test(dataProvider="getScenarios")
public void testScenarios(String scenarioSheet, String executionStatus) throws InterruptedException
{    
	Excel lib=new Excel();
	testReport=eReport.startTest(scenarioSheet);
   
	if(executionStatus.equalsIgnoreCase("yes"))
    {  
    int stepCount = Excel.getRowCount(scenariosPath,scenarioSheet);
    int rowcount= Excel.getRowCount(scenariosPath1,"sheet1");
    for(int i=1;i<=rowcount;i++)
    {
    lib.writeExcelData(scenariosPath1,"sheet1",i,1,"");
    }
    for(int i=1;i<=stepCount;i++)
    {
    String description = Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
   
    String action = Excel.getCellValue(scenariosPath, scenarioSheet, i, 1);
    
    String input1 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
    
    String input2 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
   
    String msg = "DESCRIPTION: "+description+ ", ACTION: "+action+ ", INPUT1: "+input1+ ", INPUT2: "+input2;
    testReport.log(LogStatus.INFO,msg);
    KeyWord.executekeyword(driver, action, input1, input2,i,scenarioSheet,description,testReport); 
    }
    }
    else
    {
    testReport.log(LogStatus.SKIP,"Execution status is 'NO'");
    throw new SkipException("Skipping this scenario");
    }
    
}

}
