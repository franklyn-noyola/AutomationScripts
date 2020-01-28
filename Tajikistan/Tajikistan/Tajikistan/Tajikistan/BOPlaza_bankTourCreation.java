package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOPlaza_bankTourCreation{	
	private static String bankValue;
	private static int bagSelected;
	public static List<String> windows;
	private static boolean noBagscreated=false;
	private String testNameSelected = this.getClass().getSimpleName();
	
	@Before
	public void setup() throws Exception{
			setUp();
	}
	
	@After
	public void teardown() throws Exception{
			tearDown();
	}
	
	
	@Test
	public void bankTourCreationInit() throws Exception{	
		configurationSection("Plaza",testNameSelected,testNameSelected);		
		testPlanReading(21,0,2,3);
		Thread.sleep(1000);	
		bankTourCreation();
		Thread.sleep(1000);
		if (noBagscreated==true) {
			actualResults.set(4, "Unable to create a Bank Tour because there is not bags of previous Shift Cash outs");					
			for (int i=5;i<actualResults.size()-1;i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();			
			System.out.println("Unable to create a Bank Tour because there is not bags of previous Shift Cash outs");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
		}

		actualResults.set(6, "Bank Tour has been created with number of bags: "+bagSelected+" of previous shift Cash outs with a Bank tour total amount: "+bankValue);			
		Thread.sleep(1000);
		driver.close();
		Thread.sleep(1000);
		windows = new ArrayList<String>();
		for (String wHandle : driver.getWindowHandles()) {
			windows.add(wHandle);												
		}
		if (windows.size()==1) {
			driver.switchTo().window(windows.get(0));
			driver.close();
		}
		testLink();			
		System.out.println("Bank Tour has been created with number of bags: "+bagSelected+" of previous shift Cash outs with a Bank tour total amount: "+bankValue);
		System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
		
	}
	
	public static void bankTourCreation() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Step 1.- Enter into Tajikistan Login Page
			driver.get(BoPlazaUrl);
			takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");
			loginPageErr();
			if (pageSelectedErr==true) {
				testLink();
				System.out.println("An error has ocurred in the Login Page");
				fail("An error has ocurred in the Login Page");
			}		
			// Step 2.- Log with user 00001/00001
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOTajiPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajiPage.jpeg");
			applicationVer = driver.findElement(By.id("ctl00_statusRight")).getText();
			Thread.sleep(500);	
			
			//Step 3.- Click on Collector Management then Bank Tour creation link
			action.moveToElement(driver.findElement(By.linkText("Collector management"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Bank tour creation";			
			elementClick(pageSelected);				
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.out.println(errorText);
				fail(errorText);
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));
			}
			takeScreenShot("E:\\Selenium\\","bankTourCreationSPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankTourCreation.jpeg");
			
			//Step 4.- In the Bank Tour main page, click on Submit button			
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","bankTourCreationDetail"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankTourCreationDetail.jpeg");
			WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			List <WebElement>tableResult = tableres.findElements(By.tagName("tr"));
			
			//Step 5.- In the Bank Tour creation: Details page, check if there are bags in the table and then click on Submit button
			bagSelected = tableResult.size();
			if (bagSelected<2) {
				bagSelected = 0;
			}else {
				bagSelected = bagSelected-1;
			}			
			if (bagSelected==0) {
				noBagscreated = true;
				return;
			}			
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				Thread.sleep(1000);
				
				//Step 6.- Fill all the fields accordingly and click on Submit button
				int matNum = ranNumbr(5000,9999);
				int matlet = ranNumbr(0,matletT.length()-1);
				int matlet1 = ranNumbr(0,matletT.length()-1);
				int matlet2 = ranNumbr(0,matletT.length()-1);
				int selOp = ranNumbr(0,8);
				matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
				SendKeys("ctl00_ContentZone_txt_plate_box_data",matriNu);
				SendKeys("ctl00_ContentZone_txt_officer_box_data",nameOp[selOp]+" "+lastNameOp[selOp]);
				SendKeys("ctl00_ContentZone_txt_comment_box_data","New Bank Tour has been created");
				
				String bankValue1 = driver.findElement(By.id("ctl00_ContentZone_txt_total_box_data")).getAttribute("value");
				bankValue = bankValue1.replace("TJS","");
				takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
				
				//Step 7.- Click on Accept button
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				if (isAlertPresent()) {
					driver.switchTo().alert().accept();				
				}			
				
				
				Thread.sleep(4000);										
				takeScreenShot("E:\\Selenium\\","expedicionCreada"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","expedicionCreada.jpeg");		
				
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	

}