package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;


public class BOHost_settlementsFunctionalities{		
		private static boolean nodeleted = false;
		private static boolean duplicateSettlement = false;
		private static String settlement2;		
		private static String settlementC;
		private static int opSel;					
		public static int selOper;
		public static String settlementType1;
		public static String settlementType2;
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
		public void settlementsFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			Thread.sleep(1000);
			testPlanReading(16,0,2,3);
			accountOnly=false;
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			settlementsFunctionalities();
			Thread.sleep(1000);			
			if (duplicateSettlement){
				actualResults.set(8, "Unable to Create/Modify Settlement because there is another Settlement exists with this name : "+ settlementC);
				driver.close();
				testLink();
				System.out.println("Unable to Create/Modify Settlement because there is another Settlement exists with this name : "+ settlementC);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));												
				return;	
			}
			if (nodeleted){
				actualResults.set(8, "Unable to delete "+settlementC+" due to: "+errorText);
				driver.close();
				testLink();
				System.out.println("Unable to delete "+settlementC+" due to: "+errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));												
				return;	
			}
			
			//Step 9.- After clicked action is performed
			if (optionSelect[opSel].equals("Create")){
				actualResults.set(8, "A Settlement: "+ SettlementName+ " has been created correctly and confirmed in the creation of account: "+accountNumbr);
				System.out.println("A Settlement: "+ SettlementName+ " has been created correctly and confirmed in the creation of account: "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Modify")){
				actualResults.set(8, "Settlement "+ settlementC+ " with fare Type "+settlementType1+" has been Modified to another settlement "+ settlement2 +" with Fares type "+settlementType2+" correctly");
				System.out.println("Settlement "+ settlementC+ " with fare Type "+settlementType1+" has been Modified to another settlement "+ settlement2 +" with Fares type "+settlementType2+" correctly");
				
			}
			if (optionSelect[opSel].equals("Delete")){
				actualResults.set(8, "Settlement "+ settlementC+ "with fare type "+ settlementType1+" has been deleted correctly");
				System.out.println("Settlement "+ settlementC+ "with fare type "+ settlementType1+" has been deleted correctly");
			}
			driver.close();
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));

		}
		public static void settlementsFunctionalities() throws Exception{
			action = new Actions(driver);	
			try{
				//Step 1.- Enter into Tajikistan Login Page
				driver.get(BoHostUrl);
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
				takeScreenShot("E:\\Selenium\\","homeBOTajPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(2000);			
				
				//Step 3.- Click on System settings, then Fares & Currency and then click on Settlements link
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Fares & currency"))).build().perform();
				pageSelected = "Settlements";
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
				
				takeScreenShot("E:\\Selenium\\","settlementsMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","settlementsMainPage.jpeg");
				
				//Step 4.- Click on any Button: Create (Create a new Settlement), Modify (after selecting any existig Settlement, in order to modify the Settlement), Delete (after selecting any existing Settlement to delete the Settlement).
				opSel = ranNumbr(0,2);
				switch (optionSelect[opSel]){
					case "Create":					Thread.sleep(1000);													
													createSettlement();
													Thread.sleep(1000);													
													break;
					case "Modify":					Thread.sleep(1000);
													modify_deleteSettlement();
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);
													modify_deleteSettlement();
													Thread.sleep(1000);
													break;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}						
				
		}
		public static void createSettlement() throws Exception{
			settlementFunc = true;
			accountMode = "New";
			action = new Actions(driver);
			try{
				
				//Step 5.- if Create/Modified button was clicked, fill or modify all fields accordingly and click on Submit button; if Delete was clicked click on Accept button in the Popup				
				Thread.sleep(500);
				elementClick("ctl00_ContentZone_BtnCreate");
				actualResults.set(3, "Create button has been clicked and Settlement creation page is displayed");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","settlementCreatePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","settlementCreatePage.jpeg");
				SettlementName = "Settlement_"+ranNumbr(100,5000);				
				SendKeys("ctl00_ContentZone_txt_description_box_data",SettlementName);
				Thread.sleep(500);
				selectDropDown("ctl00_ContentZone_cmb_type_cmb_dropdown");
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				Thread.sleep(500);
				if (MPSTDFlow) {
					if (driver.getPageSource().contains("The name entered already exists")){
						String duplicateSett = getText("ctl00_LblError");
						while (!duplicateSett.contains("The name entered already exists")) {
							SettlementName = "Settlement_"+ranNumbr(100,5000);
							SendKeys("ctl00_ContentZone_txt_description_box_data",SettlementName);
							elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
							Thread.sleep(1000);
							duplicateSett = getText("ctl00_LblError");							
						}
					}
				}
				
				Thread.sleep(1000);
				if (!MPSTDFlow) {
					
					//Step from 6 to 8
					action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
					Thread.sleep(500);
					action.moveToElement(driver.findElement(By.linkText("Create account"))).build().perform();
					driver.findElement(By.linkText("Individual")).click();
					Thread.sleep(500);
					individualAccount();
				}
				Thread.sleep(1000);
			}catch (Exception e){
				e.printStackTrace();				
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}

		}
		
		public static void modify_deleteSettlement() throws Exception{
			try{
				//Step 5.- if Create/Modified button was clicked, fill or modify all fields accordingly and click on Submit button; if Delete was clicked click on Accept button in the Popup
				for (int i=5;i<8;i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
				}				
				Thread.sleep(1000);
				itemSearchedandSelected();
				settlementC=optionSelectedId;
				settlementType1=getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[4]");
				if (optionSelect[opSel].equals("Modify")){				
					elementClick("ctl00_ContentZone_BtnModify");
					actualResults.set(3, "Modify button has been clicked and Settlement Edit page is displayed");
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","settlementModifyPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","settlementModifyPage.jpeg");
					settlement2 = "Settlement_"+ranNumbr(100,9999);
					SendKeys("ctl00_ContentZone_txt_description_box_data", settlement2);
					Thread.sleep(500);
					selectDropDownV("ctl00_ContentZone_cmb_type_cmb_dropdown");
					Thread.sleep(500);
					WebElement typeFare = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).getFirstSelectedOption();
					settlementType2 = typeFare.getText();				
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","DataModified"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataModified.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");			
					Thread.sleep(3000);
					if (driver.getPageSource().contains("already exists")){
						duplicateSettlement = true;
						return;
					}					
				}
				if (optionSelect[opSel].equals("Delete")){
					elementClick("ctl00_ContentZone_BtnDelete");
					actualResults.set(3, "Delete button has been clicked and Deletion Settlement popup is displayed");
					Thread.sleep(1000);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					if (driver.getPageSource().contains("It can not be deleted")) {
						errorText = getText("ctl00_LblError");
						nodeleted = true;
						return;
					}
					Thread.sleep(2000);
					return;
				}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
			}
		}

}
