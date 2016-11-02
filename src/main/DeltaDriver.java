package main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;
import generics.Property;

public class DeltaDriver extends BaseDriver
{

@BeforeClass
public void open()
{    
	
	String appURL=Property.getPropertyValue(configPptPath,"URL");
	String timeout=Property.getPropertyValue(configPptPath,"TimeOut");
	 System.setProperty("webdriver.chrome.driver",chromeDriverPath);
//	 System.setProperty("webdriver.ie.driver","D://seleniumbrowserdriver//IEDriverServer.exe");
	 driver = new ChromeDriver();
//	 driver=new InternetExplorerDriver();
	 driver.manage().window().maximize();
	 driver.get(appURL);
	 driver.manage().timeouts().implicitlyWait(Long.parseLong(timeout),TimeUnit.SECONDS);

}


@Test(dataProvider="getScenarios")
public void testScenarios(String scenarioSheet, String executionStatus,String formName) throws InterruptedException
{      
	 testReport=eReport.startTest(scenarioSheet+"-"+formName);
//   int rc=Excel.getRowCount(scenariosPath, scenarioSheet);
//   testReport = eReport.startTest(browser+"_"+scenarioSheet);
    if(executionStatus.equalsIgnoreCase("yes")){
    
    int stepCount = Excel.getRowCount(scenariosPath,scenarioSheet);
    
    for(int i=1;i<=stepCount;i++)
    {
    String description = Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
   
    String action = Excel.getCellValue(scenariosPath, scenarioSheet, i, 1);
    
    String input1 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
    
    String input2 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
   
    
     String msg = "DESCRIPTION: "+description+ ", ACTION: "+action+ ", INPUT1: "+input1+ ", INPUT2: "+input2;
     testReport.log(LogStatus.INFO,msg);
     KeyWord.executekeyword(driver, action, input1, input2,testReport); 
     
}
    }
    else
    {
        testReport.log(LogStatus.SKIP,"Execution status is 'NO'");
        throw new SkipException("Skipping this scenario");
    }
    
    }


}

