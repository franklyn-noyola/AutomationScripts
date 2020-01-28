package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class BOHost_faresDefinition{		
		private static boolean nodeleted = false;
		private static boolean duplicateFares = false;
		private static String Fare2;		
		private static String faresC;
		private static int opSel;			
		public static String SettlementName;
		public static int selOp;
		public static String faresType1;
		public static String faresType2;
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
		public void faresDefinitionInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(9,0,2,3);
			Thread.sleep(1000);
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			faresDefinition();
			Thread.sleep(1000);
			
			if (duplicateFares ){
				actualResults.set(5, "Unable to Create/Modify Fare because there is another Fare exists with this name: "+ faresC);
				Thread.sleep(1000);
				driver.close();
				testLink();
				System.out.println("Unable to Create/Modify Fare because there is another Fare exists with this name: "+ faresC);
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				return;
			}
			
			if (nodeleted){
				actualResults.set(5, "Unable to delete "+faresC+" due to: "+errorText);
				Thread.sleep(1000);
				driver.close();
				testLink();
				System.out.println("Unable to delete "+faresC+" due to: "+errorText);
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				return;				
			}
						
			if (optionSelect[opSel].equals("Create")){
				actualResults.set(5, "A fare: "+ faresC+ " has been created correctly and confirmed in the creation of a Settlement: "+SettlementName);
				System.out.println("A fare: "+ faresC+ " has been created correctly and confirmed in the creation of a Settlement: "+SettlementName);
			}
			if (optionSelect[opSel].equals("Modify")){				
				actualResults.set(5, "Fare "+ faresC+ " with Type "+faresType1+" has been Modified to another fare "+ Fare2 +" with Type "+faresType2+" correctly");
				System.out.println("Fare "+ faresC+ " with Type "+faresType1+" has been Modified to another fare "+ Fare2 +" with Type "+faresType2+" correctly");
				
			}
			if (optionSelect[opSel].equals("Delete")){
				actualResults.set(5, "Fare "+ faresC+ " has been deleted correctly");
				System.out.println("Fare "+ faresC+ " has been deleted correctly");
			}
			Thread.sleep(500);
			driver.close();
			testLink();
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));

		}
		public static void faresDefinition() throws Exception{
			action = new Actions(driver);
			opSel = 2;ranNumbr(0,2);
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
				
				//Step 3.- Click on System settings, then Fares & Currency and then click on Fares definition link
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Fares & currency"))).build().perform();
				
				pageSelected = "Fares definition";
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
				takeScreenShot("E:\\Selenium\\","faresMainPage"+timet+".jpg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","faresMainPage.jpeg");
				//Step 4.- Click on any Button: Create (To create a new Fares Definition ), Modify or Delete (if there are existing Fares Definition in the table)
				switch (optionSelect[opSel]){
					case "Create":					Thread.sleep(1000);													
													createFares();
													Thread.sleep(1000);													
													break;
					case "Modify":					Thread.sleep(1000);
													modify_deleteFares();
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);
													modify_deleteFares();
													Thread.sleep(1000);
													break;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw (e);
			}
		}
		public static void createFares() throws Exception{
			action = new Actions(driver);
			elementClick("ctl00_ContentZone_BtnCreate");
			actualResults.set(3, "Create button has been clicked");
			takeScreenShot("E:\\Selenium\\","faresCreatePage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","faresCreatePage.jpeg");
			faresC = "Fare_"+ranNumbr(100,9999);
			SendKeys("ctl00_ContentZone_txt_description_box_data",faresC);
			Thread.sleep(500);
			selectDropDownV("ctl00_ContentZone_cmb_type_cmb_dropdown");
			Thread.sleep(500);
			List <WebElement> PlazaList = new Select(driver.findElement(By.id("ctl00_ContentZone_list_plaza"))).getOptions();
			int listSelect = ranNumbr(0,PlazaList.size()-1);
			new Select(driver.findElement(By.id("ctl00_ContentZone_list_plaza"))).selectByIndex(listSelect);
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","faresFillDataPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","faresFillDataPage.jpeg");
			
			actualResults.set(4, "All data is entered correctly in Fares Definition creation page");
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(1000);
			if (driver.getPageSource().contains("already exists")){
				duplicateFares = true;
				return;
			}
			
			action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
			Thread.sleep(1000);
			action.moveToElement(driver.findElement(By.linkText("Fares & currency"))).build().perform();
			
			pageSelected = "Settlements";
			driver.findElement(By.linkText(pageSelected)).click();
			Thread.sleep(500);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.out.println(errorText);
				fail(errorText);
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));
			}
			takeScreenShot("E:\\Selenium\\","SettlementMainPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","SettlementMainPage.jpeg");
			elementClick("ctl00_ContentZone_BtnCreate");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","SettlemeentCreationPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","SettlementCreationPage.jpeg");
			SettlementName = "Settlement_"+ranNumbr(100,9999);
			SendKeys("ctl00_ContentZone_txt_description_box_data",SettlementName);
			Thread.sleep(1000);
			elementClick("ctl00_ContentZone_cmb_type_cmb_dropdown");
			new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).selectByVisibleText(faresC);
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","farecreatedConfirmed"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","farecreatedConfirmed.jpeg");
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");			
			Thread.sleep(3000);
		}
		public static void modify_deleteFares() throws Exception{
			Thread.sleep(1000);
			itemSearchedandSelected();
			Thread.sleep(500);
			faresC=getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[3]");
			faresType1=getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[4]");
			if (optionSelect[opSel].equals("Modify")){				
				elementClick("ctl00_ContentZone_BtnModify");
				actualResults.set(3, "Modify Button has been clicked");
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","faresModifyPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","faresModifyPage.jpeg");
				Fare2 = "Fare_"+ranNumbr(100,9999);
				SendKeys("ctl00_ContentZone_txt_description_box_data", Fare2);
				Thread.sleep(500);
				selectDropDownV("ctl00_ContentZone_cmb_type_cmb_dropdown");
				Thread.sleep(500);
				WebElement typeFare = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).getFirstSelectedOption();
				faresType2 = typeFare.getText();
				List <WebElement> PlazaList = new Select(driver.findElement(By.id("ctl00_ContentZone_list_plaza"))).getOptions();
				int listSelect = ranNumbr(0,PlazaList.size()-1);
				new Select(driver.findElement(By.id("ctl00_ContentZone_list_plaza"))).selectByIndex(listSelect);
				Thread.sleep(100);
				takeScreenShot("E:\\Selenium\\","DataModified"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataModified.jpeg");				
				actualResults.set(4, "All data is modified correctly in Discount Table Edit page");
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");			
				Thread.sleep(3000);
				if (driver.getPageSource().contains("already exists")){
					duplicateFares = true;
					return;
				}
				
			}
			if (optionSelect[opSel].equals("Delete")){
				elementClick("ctl00_ContentZone_BtnDelete");
				actualResults.set(3, "Delete button has been clicked");
				Thread.sleep(1000);
				if (isAlertPresent()){
					driver.switchTo().alert().accept();
				}
				Thread.sleep(1000);
				actualResults.set(4, "Deletion Confirmation popup is displayed");
				if (driver.getPageSource().contains("can not be deleted")) {
					errorText = getText("ctl00_LblError");
					nodeleted = true;
					return;
				}
				Thread.sleep(2000);
				return;
			}
		}
		
}