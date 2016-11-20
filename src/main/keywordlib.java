package main;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import generics.Excel;
import generics.Property;

public class keywordlib implements AutomationConstants 
{
	
//stores the value in excel
public static void storeInExcel(WebDriver driver, String input1, String action, String scenarioSheet, int i,ExtentTest testReport) 
{
	String dataval = Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
	Excel xlib0 = new Excel();
	xlib0.writeExcelData(scenariosPath1, "Sheet1", i, 1, dataval);
}

//clicks on button
public static void clickOnButton(WebDriver driver, String input1, String action, int i, ExtentTest testReport) 
{
try 
{
    driver.findElement(locator.getLocator(input1)).click();
	testReport.log(LogStatus.PASS, "Successfully clicked on the button");	
}
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Failed to click on the button");
}
}

// click can be used to click on checkbox,buttons,radiobuttons
public static void click(WebDriver driver, String input1, String action, int i, ExtentTest testReport) 
{
try 
{
	driver.findElement(locator.getLocator(input1)).click();
	testReport.log(LogStatus.PASS, "Clicked successfully");
	Excel xlib = new Excel();
	String compare = input1.substring(0, 2);
	
	if (compare.equalsIgnoreCase("ID"))
    {
	boolean b = driver.findElement(locator.getLocator(input1)).isSelected();
	String gotres = String.valueOf(b);
	xlib.writeExcelData(scenariosPath1, "Sheet1", i, 1, gotres);
	}
	
	if (compare.equalsIgnoreCase("Xp")) 
	{
	boolean b = driver.findElement(locator.getLocator(input1 + "/../input")).isSelected();
	String gotres1 = String.valueOf(b);
	xlib.writeExcelData(scenariosPath1, "Sheet1", i, 1, gotres1);
	}
} 
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Failed to Click");
}

}

// selects a value from dropdown
public static void Selectfromdropdown(WebDriver driver, String input1, String input2, String action, int i,ExtentTest testReport)
{
try 
{
	WebElement ele = driver.findElement(locator.getLocator(input1));
	Excel xlib1 = new Excel();
	Select sel = new Select(ele);
	sel.selectByVisibleText(input2);
	String data = driver.findElement(locator.getLocator(input1)).getAttribute("value");
	testReport.log(LogStatus.PASS, "Value from dropdown Selected");
    xlib1.writeExcelData(scenariosPath1, "Sheet1", i, 1, data);
} 
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Not able to select Value from dropdown");
}
}

// enters a value in lookup and selects a value from lookup
public static void Selectfromlookup(WebDriver driver, String input1, String input2, String action, int i,ExtentTest testReport) throws InterruptedException {
try
{
	Excel xlib2 = new Excel();
	WebElement ele = driver.findElement(locator.getLocator(input1));
	ele.sendKeys(input2);
	Thread.sleep(2000);
	ele.sendKeys(Keys.ARROW_DOWN);
	ele.sendKeys(Keys.ENTER);
	testReport.log(LogStatus.PASS, "Value from lookup got Selected");
	String data = driver.findElement(locator.getLocator(input1)).getAttribute("value");
	xlib2.writeExcelData(scenariosPath1, "Sheet1", i, 1, data);
}
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Value from lookup not Selected");
}
}

// navigates to "xml generator" page,selects "sdc user" and clicks on form and clicks on generate xml and clicks on click "here" link
public static void SelectForm(WebDriver driver, String input1, String input2, String action,ExtentTest testReport) throws InterruptedException 
{
try
{
	String appURL = Property.getPropertyValue(configPptPath, "URL");
	driver.get(appURL + "xml_generator/");
try
{
	driver.switchTo().alert().accept();
	testReport.log(LogStatus.WARNING, "Alert Popup triggered");
}
catch(Exception e)
{
}
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	WebElement sdcemail = driver.findElement(By.id("id_sdc_users"));
	String constant = "//label[contains(text(),'";
	String Variab = input1;
	String constant1 = "')]";
	Select sel = new Select(sdcemail);
	sel.selectByValue(input2);
	
	String value = constant + Variab + constant1;
	driver.findElement(By.xpath(value)).click();
	driver.findElement(By.xpath("//button[@name='import']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("html/body/div[1]/center/h3/a")).click();
	testReport.log(LogStatus.PASS, "Successfully imported");
}
catch(Exception e)
{
	testReport.log(LogStatus.FAIL, "Failed to import");
}
}

// opens chrome browser and enter username and pwd and clicks on login button
public static void login(WebDriver driver, String input1, String input2, String action, ExtentTest testReport)
{
	driver.findElement(By.id("inputEmail")).sendKeys(input1);
	driver.findElement(By.id("inputPassword")).sendKeys(input2);
	driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	String logstatus = driver.findElement(By.xpath("//strong[contains(text(),'Your active request(s)')]")).getText();
	if (logstatus.equalsIgnoreCase("Your active request(s)")) 
	{
	testReport.log(LogStatus.PASS, "Login Successful");
	}
	else
	{
	testReport.log(LogStatus.FAIL, "Login Failed");
	}
}

// using this i can go to the esf form page wher i fill the form
public static void NavigateToFormPage(WebDriver driver, String action, ExtentTest testReport)
{

	driver.findElement(By.xpath("//span[@class='ir-status-button-text']")).click();
	testReport.log(LogStatus.PASS, "Navigated to Form Page");

}

// enter text in the text field
public static void enter(WebDriver driver, String input1, String input2, String action, int i,ExtentTest testReport) 
{
try 
{
	Excel xlib3 = new Excel();
	driver.findElement(locator.getLocator(input1)).clear();
	driver.findElement(locator.getLocator(input1)).sendKeys(input2);
	String data = driver.findElement(locator.getLocator(input1)).getAttribute("value");
	testReport.log(LogStatus.PASS, "Executed enter");
    xlib3.writeExcelData(scenariosPath1, "Sheet1", i, 1, data);
}
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Not able to enter");
}

}

// gets the save status
public static void Verifysavestatus(WebDriver driver, String action, String input1, String input2,ExtentTest testReport) 
{
try
{
	String status = driver.findElement(locator.getLocator(input1)).getText();
	if (status.equals("Form successfully saved. All mandatory fields have been filled. The product status has been set to COMPLETED.")) 
	{
	testReport.log(LogStatus.PASS, "Saved successfully.The product status has been set to COMPLETED.");
	} 
	else 
	{
	testReport.log(LogStatus.FAIL, "Saved successfully.The product status has been set to ACTIVE-IN-PROGRESS.");
	}
}
catch(Exception e)
{
	testReport.log(LogStatus.FAIL, "Not able to find save status");	
}
}

// used to sign out from the application
public static void signout(WebDriver driver, String action, String input1, ExtentTest testReport) {
	driver.findElement(locator.getLocator(input1)).click();
	testReport.log(LogStatus.PASS, "Signedout successfull");
}

// used to uploading a file
public static void uploadFile(WebDriver driver, String action, String input1, String input2,
	ExtentTest testReport) {
try {
	driver.findElement(locator.getLocator(input1)).sendKeys(input2);
	testReport.log(LogStatus.PASS, "Uploaded successfully");
	} 
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Upload fail");
}
}

// used to verify uploaded files
public static void verifyUpload(WebDriver driver, String action, String input1, String input2, ExtentTest testReport)
{
	String actualUpload = driver.findElement(locator.getLocator(input1)).getText();
	if (actualUpload.equalsIgnoreCase(input2))
	{
	testReport.log(LogStatus.PASS, "Uploaded file is present");
	} 
	else 
	{
	testReport.log(LogStatus.FAIL, "Uploaded file is not present");
	}
	}

//used to submit
public static void submit(WebDriver driver, String action, String input1, ExtentTest testReport) 
{
try 
{
    driver.findElement(locator.getLocator(input1)).sendKeys(Keys.TAB,Keys.ENTER);
    testReport.log(LogStatus.PASS, "Submitted successfully");
} 
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "Failed to Submit");
}
}

// used to verify values of radiobutton,checkbox,lookup,dropdown,text field
public static void verify(WebDriver driver, String action, String input1, String input2, String scenarioSheet,String description, ExtentTest testReport) 
{
	if (action.equalsIgnoreCase("clickonbutton")) 
	{

	} 
	else 
	{
	int firstline = Integer.parseInt(input1);
	int lastline = Integer.parseInt(input2);
    for (int i = firstline; i <= lastline; i++) 
    {
    String verifdescription = Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
    String verifaction = Excel.getCellValue(scenariosPath, scenarioSheet, i, 1);
    String verifinput1 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
    String verifinput2 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
    String msg = "DESCRIPTION: " + verifdescription + ", ACTION: " + verifaction + ", INPUT1: "+ verifinput1 + ", INPUT2: " + verifinput2;
	testReport.log(LogStatus.INFO, msg);

	String xpath = Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);

	String expres = Excel.getCellValue(scenariosPath1, "Sheet1", i, 1);
	String value;
try 
{
	value = driver.findElement(locator.getLocator(xpath)).getAttribute("type");
	if (value == null) 
	{
	value = driver.findElement(locator.getLocator(xpath + "/../input")).getAttribute("type");
	}
      
	if (value.equalsIgnoreCase("radio")) 
	{
		
try 
{
	boolean b = driver.findElement(locator.getLocator(xpath)).isSelected();
    String actual = String.valueOf(b);
	String rs = driver.findElement(locator.getLocator(xpath)).getAttribute("value");
	
	if (actual.equalsIgnoreCase(expres))
	{
	testReport.log(LogStatus.PASS, "Radio button " +"'"+ rs +"'" +" is selected");
	}
	else 
	{
	testReport.log(LogStatus.FAIL, "Radio button " +"'"+ rs +"'" +" is not selected");
	}
}
catch (Exception e) 
{
	
}	
}

	else if (value.equalsIgnoreCase("checkbox")) 
	{
					
try 
{
	boolean b = driver.findElement(locator.getLocator(xpath + "/../input")).isSelected();
	String actual1 = String.valueOf(b);					
	if (actual1.equalsIgnoreCase(expres))
	{
	testReport.log(LogStatus.PASS, "checkbox is selected");
	} 
	else
	{
	testReport.log(LogStatus.FAIL, "checkbox is not selected");
	}
	} 
catch (Exception e) 
{
	testReport.log(LogStatus.FAIL, "checkbox is not selected");
}
}
					

	else
	{
	String data = null;
try 
{
    data = driver.findElement(locator.getLocator(xpath)).getAttribute("value");
	if (data.equalsIgnoreCase(expres)) 
	{
	testReport.log(LogStatus.PASS," Actual value is: " + data + " Expected value is: " + expres);
	} 
	else 
	{
    testReport.log(LogStatus.FAIL," Actual value is: " + data + " Expected value is: " + expres);
	}
} 
catch (Exception e) 
{
	if (data.equalsIgnoreCase(expres)) 
	{
    testReport.log(LogStatus.PASS," Actual value is: " + data + " Expected value is: " + expres);
	} 
	else 
	{
	testReport.log(LogStatus.FAIL," Actual value is: " + data + " Expected value is: " + expres);
	}
}
    }
} 
catch (Exception e)
{

}
	}
	}
}
}
