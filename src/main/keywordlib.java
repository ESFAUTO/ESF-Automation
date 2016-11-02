package main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;



public class keywordlib{
	
	
	//click can be used to click on checkbox,buttons,radiobuttons
public static void click(WebDriver driver,String input1,String action,ExtentTest testReport){
	try{
	driver.findElement(locator.getLocator(input1)).click();
	testReport.log(LogStatus.PASS, "Clicked successfully");
	}
	catch(Exception e){
		testReport.log(LogStatus.FAIL, "Didnt clicked");
	}
	
	
}
//selects a value from dropdown
public static void Selectfromdropdown(WebDriver driver,String input1,String input2,String action,ExtentTest testReport){
	WebElement ele=driver.findElement(locator.getLocator(input1));
		Select sel=new Select(ele);
		try{
		sel.selectByVisibleText(input2);
		testReport.log(LogStatus.PASS, "Value from dropdown Selected");
		}
		catch(Exception e){
		testReport.log(LogStatus.FAIL, "Not able to select Value from dropdown");
		}
}

//enters a value in lookup and selects a value from lookup
public static void Selectfromlookup(WebDriver driver,String input1,String input2,String action,ExtentTest testReport) throws InterruptedException{
	WebElement ele=driver.findElement(locator.getLocator(input1));
	try{
	ele.sendKeys(input2);
	Thread.sleep(2000);
	ele.sendKeys(Keys.ARROW_DOWN);
	ele.sendKeys(Keys.ENTER);
	testReport.log(LogStatus.PASS, "Value from lookup got Selected");
	}
	catch(Exception e){
		testReport.log(LogStatus.FAIL, "Value from lookup not Selected");
	}
}

//navigates to xmlgenerator page,select sdc user and clicks on form and clicks on generate xml and clicks on click here link
public static void searchandclickfromxmlgenerator(WebDriver driver, String input1, String input2,String action) throws InterruptedException {
	driver.get("https://bmo-esf-qa.galepartners.com/xml_generator/");
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	WebElement sdcemail = driver.findElement(By.id("id_sdc_users"));
	String constant = "//label[contains(text(),'";
	String Variab = input1;
	String constant1 = "')]";		
	Select sel=new Select(sdcemail);
	sel.selectByValue(input2);
	// driver.findElement(By.xpath("//label[contains(text(),'Autoconnect')]")).click();
	String value = constant + Variab + constant1;
	driver.findElement(By.xpath(value)).click();
	//System.out.println(value);
	driver.findElement(By.xpath("//button[@name='import']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("html/body/div[1]/center/h3/a")).click();
	
}

//opens chrome browser and enter username and pwd and clicks on login button

public static void login(WebDriver driver,String input1,String input2,String action,ExtentTest testReport){
//	System.setProperty("webdriver.chrome.driver","D:\\seleniumbrowserdriver\\chromedriver.exe");
//	 driver = new ChromeDriver();
//	 driver.get("https://bmo-esf-qa.galepartners.com");
//	 driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	 driver.findElement(By.id("inputEmail")).sendKeys(input1);
		driver.findElement(By.id("inputPassword")).sendKeys(input2);// Qwerty123$
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	testReport.log(LogStatus.PASS, "Login Successful");
}

//using this i can go to the esf form page wher i fill the form
public static void navigateToFormPage(WebDriver driver,String action,ExtentTest testReport){
	driver.findElement(By.xpath("//span[@class='ir-status-button-text']")).click();
	testReport.log(LogStatus.PASS, "Navigated to Form Page");
}


public static void verifyTextContains(WebDriver driver,String action,String input1,String input2,ExtentTest testReport)
{
	
	String text_getValue=driver.findElement(locator.getLocator(input1)).getAttribute("value");
	testReport.log(LogStatus.INFO,"Actual text is: "+text_getValue+  ""+"Expected text is: "+input2);
//    String text_getText=driver.findElement(locator.getLocator(input1)).getText();
//    String text_innerHTML=driver.findElement(locator.getLocator(input1)).getAttribute("innerHTML");
//    String text_textContent=driver.findElement(locator.getLocator(input1)).getAttribute("textContent");
//    Reporter.log("Expected text is:"+input2);
//    Reporter.log("getText:"+text_getValue);
//    Reporter.log("innerHTML:"+text_innerHTML);
//    Reporter.log("textContent:"+text_textContent);
//    testReport.log(LogStatus.INFO, "Expected text is:"+input2);
//    testReport.log(LogStatus.INFO, "getText:"+text_getText);
//    testReport.log(LogStatus.INFO, "innerHTML:"+text_innerHTML);
//    testReport.log(LogStatus.INFO, "textContent:"+text_textContent);
//    if(text_getText.contains(input2))
//    {    
//        testReport.log(LogStatus.PASS,"Verified using getText; Actual text contains Expected text");
//    }
//    else if(text_innerHTML.contains(input2))
//    {    
//        testReport.log(LogStatus.PASS,"Verified using innerHTML; Actual text contains Expected text");
//    }
//    else if(text_textContent.contains(input2))
//    {    
//        testReport.log(LogStatus.PASS,"Verified using textContent; Actual text contains Expected text");
//    }
//    else
//    {
//        testReport.log(LogStatus.FAIL,"Actual text DO NOT contains Expected text");
//    }
    if(text_getValue.equals(input2))
    {
    	testReport.log(LogStatus.PASS, "Verified using getText; Actual text contains Expected text");
    	// Reporter.log("Verified using getText; Actual text contains Expected text",true);
    }
    else
    {
    	testReport.log(LogStatus.FAIL, "Actual text DO NOT contains Expected text");
    	//Reporter.log("Actual text DO NOT contains Expected text",true);
    }
}

//enter text in the text field
public static void enter(WebDriver driver,String input1,String input2,String action,ExtentTest testReport)
{
	
    driver.findElement(locator.getLocator(input1)).sendKeys(input2); 
    try{
    testReport.log(LogStatus.PASS,"executed enter");
    }
    catch(Exception e){
    	testReport.log(LogStatus.FAIL,"Not able to enter");
    }
   
}
public static void checkboxverify(WebDriver driver,String action,String input1,String input2,ExtentTest testReport){
	Boolean b=driver.findElement(locator.getLocator(input1)).isEnabled();
	if(b==true)
	{
	testReport.log(LogStatus.PASS,"Check box is selected");	
}
	else
	{
   testReport.log(LogStatus.FAIL,"Check box is not selected");
	}
	}
public static void savestatus(WebDriver driver,String action,String input1,String input2,ExtentTest testReport)
{
	String status=driver.findElement(locator.getLocator(input1)).getText();
	if(status.equals("Form successfully saved. All mandatory fields have been filled. The product status has been set to COMPLETED.")){
		testReport.log(LogStatus.PASS,"Saved successfully.The product status has been set to COMPLETED.");	
	}
	else{
		testReport.log(LogStatus.FAIL,"Saved successfully.The product status has been set to ACTIVE-IN-PROGRESS.");	
	}
}
}


