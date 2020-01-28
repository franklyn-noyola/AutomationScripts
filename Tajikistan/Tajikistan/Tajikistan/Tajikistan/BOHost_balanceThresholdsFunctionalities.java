package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class BOHost_balanceThresholdsFunctionalities{		
		private static boolean nodeleted = false;
		private static boolean errorThres = false;				
		private static String thresC;
		public static int optSel;					
		private static int selOption;
		private static String [] thresSelect = {"Standard", "Negative"}; 
		private static String [] optionSelect = {"Create","Modify","Delete"};
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
		public void BalanceThresholdsFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);				
			testPlanReading(7,0,2,3);
			Thread.sleep(1000);
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			BalanceThresholdsFunctionalities();
			Thread.sleep(1000);
			if (errorThres){
				actualResults.set(6, "Unable to Create/Modify "+pageSelected+" because of: "+ errorText);
				Thread.sleep(1000);
				driver.close();
				testLink();				
				System.out.println("Unable to Create/Modify "+pageSelected+" because of: "+ errorText);
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				return;
			}
			
			if (nodeleted){
				actualResults.set(6, "Unable to delte "+pageSelected+": "+optionSelectedId+" because of: "+ errorText);
				Thread.sleep(1000);
				driver.close();
				testLink();				
				System.out.println("Unable to delte "+pageSelected+": "+optionSelectedId+" because of: "+ errorText);
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				return;
			}
						
			if (optionSelect[selOption].equals("Create")){
				actualResults.set(6, pageSelected+": "+ thresC+ " has been created correctly");				
				System.out.println(pageSelected+": "+ thresC+ " has been created correctly");
			}
			
			if (optionSelect[selOption].equals("Modify")){
				actualResults.set(6, pageSelected+": "+ optionSelectedId+ " has been Modified to another Balance Threshold "+ thresC +" correctly");								
				System.out.println(pageSelected+": "+ optionSelectedId+ " has been Modified to another Balance Threshold "+ thresC +" correctly");
				
			}
			if (optionSelect[selOption].equals("Delete")){
				actualResults.set(6, pageSelected+": "+ optionSelectedId+ " has been deleted correctly");
				System.out.println(pageSelected+": "+ optionSelectedId+ " has been deleted correctly");
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));

		}
		public static void BalanceThresholdsFunctionalities() throws Exception{
			action = new Actions(driver);	
			try{
				//Step 1.- Enter into Tajikistan Login Page
				driver.get(BoHostUrl);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.out.println("An error has ocurred in the Login Page");
					fail("An error has ocurred in the Login Page");
				}
				
				//Step 2.- Log with user 00001/00001
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOTajPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(2000);			
				
				//Step 3.- Click on System settings, then Account Parameters and then Balance thresholds link
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Balance thresholds"))).build().perform();
				
				optSel = ranNumbr(0,1);
				
				//Step 4.- Select either Standard or Negative link
				if (thresSelect[optSel].equals("Standard")) {					
					pageSelected = "Balance Threadholds Standard";
					elementClick("Standard");
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.out.println(errorText);
						fail(errorText);
						System.out.println("Tested in Version of: "+ getVersion(applevelTested));
					}
					balanceThresholdsStandard();
					return;
				}
				if (thresSelect[optSel].equals("Negative")) {
					pageSelected = "Balance Threadholds Negative";
					elementClick("Negative");
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.out.println(errorText);
						fail(errorText);
						System.out.println("Tested in Version of: "+ getVersion(applevelTested));
					}
					balanceThresholdsNegative();
					return;
				}	
				
				Thread.sleep(2000);						
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		public static void balanceThresholdsStandard() throws Exception{
			Thread.sleep(1000);
			action = new Actions(driver);
			actualResults.set(3, pageSelected+" page is displayed");
			try {
				takeScreenShot("E:\\Selenium\\","BalanceThresHoldsStandardPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","BalanceThresHoldsStandardPage.jpeg");
				selOption = ranNumbr(0,2);
				if (optionSelect[selOption].equals("Modify") || optionSelect[selOption].equals("Delete")) {
					itemSearchedandSelected();									
				}
			
				//	Step 5.- Click on any option: Create (To create a new selected threadholds), Modify or Delete (if there are existing selected threadholds in the table)
				if (optionSelect[selOption].equals("Create")||optionSelect[selOption].equals("Modify")) {
					takeScreenShot("E:\\Selenium\\","BalanceThresHoldsStandardEdit"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","BalanceThresHoldsStandardEditPage.jpeg");
					thresC="BHStandard_"+ranNumbr(100,1000);
					if (optionSelect[selOption].equals("Create")){
						actualResults.set(4, "Create button has been clicked, "+pageSelected+" creation page is displayed, a new "+pageSelected+" is going to be created");
						elementClick("ctl00_ContentZone_BtnCreate");
						
					}else {
						actualResults.set(4, "Modify button has been clicked, "+pageSelected+" Edit page is displayed, "+pageSelected+": "+optionSelectedId+" is going to be modified");
						elementClick("ctl00_ContentZone_BtnModify");
					}
					Thread.sleep(1000);
					SendKeys("ctl00_ContentZone_txt_name_box_data",thresC);
					Thread.sleep(500);
					//Filling Low Balance fields group
					for (int i=0;i <4;i++) {
						action.click(driver.findElement(By.id("ctl00_ContentZone_amount"+i+"_ctl00_ContentZone_tbl_roles_0_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(0,9999))).build().perform();						
						Thread.sleep(500);
						action.click(driver.findElement(By.id("ctl00_ContentZone_extraVehicle"+i+"_ctl00_ContentZone_tbl_roles_0_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(0,9999))).build().perform();
						Thread.sleep(500);						
					}
					//Filling Insufficient funds fields group
					for (int i=0;i <4;i++) {
						action.click(driver.findElement(By.id("ctl00_ContentZone_amount"+i+"_ctl00_ContentZone_tbl_roles_1_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(0,9999))).build().perform();						
						Thread.sleep(500);
						action.click(driver.findElement(By.id("ctl00_ContentZone_extraVehicle"+i+"_ctl00_ContentZone_tbl_roles_1_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(0,9999))).build().perform();
						Thread.sleep(500);						
					}
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","DataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilled.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					errorText = getText("ctl00_LblError");
					if (errorText.contains("successfully modified") || errorText.contains("successfully created")) {
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						Thread.sleep(3000);
					}else{
						errorThres = true;
						return;
					}
				}
				if (optionSelect[selOption].equals("Delete")) {
					actualResults.set(4, "Delete button has been clicked, a deletion confirmation popup is displayed, indicating that "+pageSelected+": "+optionSelectedId+" is going to be deleted");
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);
					
					//Step 6.- Click on Accept button
					if(isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					if (driver.getPageSource().contains("Cannot delete the item because it is being used")){
						errorText = getText("ctl00_LblError");
						nodeleted =true;
						return;
					}
					Thread.sleep(100);
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				System.err.println(e.getMessage());
				throw(e);				
			}
		}
		public static void balanceThresholdsNegative() throws Exception{
			Thread.sleep(1000);
			actualResults.set(3, pageSelected+" page is displayed");
			try {
				takeScreenShot("E:\\Selenium\\","BalanceThresHoldsNegativePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","BalanceThresHoldsNegativePage.jpeg");
				selOption = ranNumbr(0,2);
				if (optionSelect[selOption].equals("Modify")||optionSelect[selOption].equals("Delete")) {
					itemSearchedandSelected();							
				}
				//Step 5.- Click on any option: Create (To create a new selected threadholds), Modify or Delete (if there are existing selected threadholds in the table)
				if (optionSelect[selOption].equals("Create")||optionSelect[selOption].equals("Modify")) {
					takeScreenShot("E:\\Selenium\\","BalanceThresHoldsStandardEdit"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","BalanceThresHoldsStandardEditPage.jpeg");
					thresC="BHNegative_"+ranNumbr(100,1000);
					
					if (optionSelect[selOption].equals("Create")){
						actualResults.set(4, "Create button has been clicked, "+pageSelected+" creation page is displayed, a new "+pageSelected+" is going to be created");
						elementClick("ctl00_ContentZone_BtnCreate");
						
					}else {
						actualResults.set(4, "Modify button has been clicked, "+pageSelected+" Edit page is displayed, "+pageSelected+": "+optionSelectedId+" is going to be modified");
						elementClick("ctl00_ContentZone_BtnModify");
					}
					Thread.sleep(1000);
					SendKeys("ctl00_ContentZone_txt_name_box_data",thresC);
					Thread.sleep(500);
					action.click(driver.findElement(By.id("ctl00_ContentZone_txt_LowBalance_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(0,9999))).build().perform();
					Thread.sleep(500);
					action.click(driver.findElement(By.id("ctl00_ContentZone_txt_Insuficientbounds_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(0,9999))).build().perform();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","DataFilledNegative"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilledNegative.jpeg");
					
					//Step 6.- Click on Submit button
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					errorText = getText("ctl00_LblError");
					if (errorText.contains("successfully modified") || errorText.contains("successfully created")) {
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						Thread.sleep(3000);
					}else{
						errorThres = true;
						return;
					}
				}
				if (optionSelect[selOption].equals("Delete")) {
					actualResults.set(4, "Delete button has been clicked, a deletion confirmation popup is displayed, indicating that "+pageSelected+": "+optionSelectedId+" is going to be deleted");
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);
					
					//Step 6.- Click on Accept button
					if(isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					if (driver.getPageSource().contains("Cannot delete the item because it is being used")){
						errorText = getText("ctl00_LblError");
						nodeleted =true;
						return;
					}
					Thread.sleep(100);
					
				}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
}