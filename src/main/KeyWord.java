package main;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class KeyWord 
{
public static void executekeyword(WebDriver driver,String action,String input1,String input2,int i,String scenarioSheet,String description,ExtentTest testReport) throws InterruptedException
{
	
	
	if(action.equalsIgnoreCase("click"))
	{
		keywordlib.click(driver, input1, action,i,testReport);
	}
	if(action.equalsIgnoreCase("clickonbutton"))
	{
		keywordlib.clickOnButton(driver, input1, action, i, testReport);
	}
	else if(action.equalsIgnoreCase("Selectfromdropdown"))
	{
		keywordlib.Selectfromdropdown(driver, input1, input2, action,i, testReport);
	}
	else if(action.equalsIgnoreCase("Selectfromlookup"))
	{
		keywordlib.Selectfromlookup(driver, input1, input2, action,i, testReport);
	}
	else if(action.equalsIgnoreCase("SelectForm"))
	{
		keywordlib.SelectForm(driver,input1,input2, action, testReport);
	}
	else if(action.equalsIgnoreCase("login"))
	{
		keywordlib.login(driver, input1, input2, action,testReport);
	}
	else if(action.equalsIgnoreCase("NavigateToFormPage"))
	{
		keywordlib.NavigateToFormPage(driver, action,testReport);
	}
	else if(action.equalsIgnoreCase("enter"))
	{
		keywordlib.enter(driver, input1, input2, action,i, testReport);
		}
	
	else if(action.equalsIgnoreCase("Verifysavestatus"))
	{
		keywordlib.Verifysavestatus(driver, action, input1, input2,testReport);
	}
	else if(action.equalsIgnoreCase("signout")){
		keywordlib.signout(driver, action, input1,testReport);
	}
	else if(action.equalsIgnoreCase("verify")){
		keywordlib.verify(driver, action, input1,input2,scenarioSheet,description,testReport);
	}
	else if(action.equalsIgnoreCase("upload")){
		keywordlib.uploadFile(driver, action, input1,input2,testReport);
	}
	else if(action.equalsIgnoreCase("verifyupload")){
		keywordlib.verifyUpload(driver, action, input1,input2,testReport);
	}
	else if(action.equalsIgnoreCase("submit")){
		keywordlib.submit(driver, action, input1,testReport);
	}
	else if(action.equalsIgnoreCase("storeinexcel")){
		keywordlib.storeInExcel(driver, input1, action, scenarioSheet, i, testReport);
	}	
}
}