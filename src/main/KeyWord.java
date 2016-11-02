package main;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class KeyWord 
{
public static void executekeyword(WebDriver driver,String action,String input1,String input2,ExtentTest testReport) throws InterruptedException
{
	
	
	if(action.equalsIgnoreCase("click"))
	{
		keywordlib.click(driver, input1, action,testReport);
	}
	else if(action.equalsIgnoreCase("Selectfromdropdown"))
	{
		keywordlib.Selectfromdropdown(driver, input1, input2, action,testReport);
	}
	else if(action.equalsIgnoreCase("Selectfromlookup"))
	{
		keywordlib.Selectfromlookup(driver, input1, input2, action,testReport);
	}
	else if(action.equalsIgnoreCase("searchandclickfromxmlgenerator"))
	{
		keywordlib.searchandclickfromxmlgenerator(driver,input1,input2, action);
	}
	else if(action.equalsIgnoreCase("login"))
	{
		keywordlib.login(driver, input1, input2, action,testReport);
	}
	else if(action.equalsIgnoreCase("navigateToFormPage"))
	{
		keywordlib.navigateToFormPage(driver, action,testReport);
	}
	else if(action.equalsIgnoreCase("enter"))
	{
		keywordlib.enter(driver, input1, input2, action,testReport);
		}
	else if(action.equalsIgnoreCase("verifyTextContains"))
	{
		keywordlib.verifyTextContains(driver, action, input1, input2,testReport);
		}
	else if(action.equalsIgnoreCase("checkboxverify"))
	{
		keywordlib.checkboxverify(driver, action, input1, input2,testReport);
}
	else if(action.equalsIgnoreCase("savestatus"))
	{
		keywordlib.savestatus(driver, action, input1, input2,testReport);
	}
}
}