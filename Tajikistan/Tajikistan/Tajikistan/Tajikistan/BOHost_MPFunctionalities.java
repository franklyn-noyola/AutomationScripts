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


public class BOHost_MPFunctionalities{		
		private static boolean nodeleted = false;		
		private static String MPC;					
		public static int opSel;		
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
		public void MPFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(12,0,2,3);
			testLinkTestCase=testNameSelected;
			Thread.sleep(1000);
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			MPFunctionalities();
			Thread.sleep(1000);
			
			if (nodeleted){
				actualResults.set(5, "Unable to delete Monthly Pass with Settlement "+MPC+" due to: "+errorText);
				driver.close();
				testLink();
				System.out.println("Unable to delete Monthly Pass with Settlement "+MPC+" due to: "+errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}
						
			if (optionSelect[opSel].equals("Create")){
				actualResults.set(5, "Monthly Pass with Settlement: "+ SettlementName+ " has been created correctly");
				System.out.println("Monthly Pass with Settlement: "+ SettlementName+ " has been created correctly");
			}
			if (optionSelect[opSel].equals("Modify")){				
				actualResults.set(5, "Monthly Pass with Settlement: "+ MPC+ " has been Modified to another Montly Pass with Settlement "+ SettlementName +" correctly");
				System.out.println("Monthly Pass with Settlement: "+ MPC+ " has been Modified to another Montly Pass with Settlement "+ SettlementName +" correctly");
				
			}
			if (optionSelect[opSel].equals("Delete")){
				actualResults.set(5, "Monthly Pass with Settlement "+ MPC+ " has been deleted correctly");
				System.out.println("Monthly Pass with Settlement "+ MPC+ " has been deleted correctly");
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));

		}
		
		public static void MPFunctionalities() throws Exception{
			action = new Actions(driver);
			opSel = ranNumbr(0,2);
			try {
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
				
				//Step 3.- Click on System settings, then Account parameters and then click on Monthly Pass link
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();
				pageSelected = "Monthly pass";
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
				takeScreenShot("E:\\Selenium\\","MPMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPMainPage.jpeg");						
				switch (optionSelect[opSel]){
					case "Create":					Thread.sleep(1000);													
													createEditDeleteMP();
													actualResults.set(3, "Create button has been clicked, Monthly Pass creation page is displayed");
													Thread.sleep(1000);													
													break;
					case "Modify":					Thread.sleep(1000);
													createEditDeleteMP();
													actualResults.set(3, "Modified button has been clicked, Monthly Pass edit page is displayed");
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);
													createEditDeleteMP();
													actualResults.set(3, "Delete button has been clicked, a deletion pop up confirmation alert is displayed");
													Thread.sleep(1000);
													break;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		public static void createEditDeleteMP() throws Exception{
			Thread.sleep(1000);		
			action = new Actions(driver);
			try {
				if (optionSelect[opSel].equals("Modify")||optionSelect[opSel].equals("Delete")) {
					searchandSelect();
					Thread.sleep(1000);					
					MPC = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[3]");
				}
				if (optionSelect[opSel].equals("Create")||optionSelect[opSel].equals("Modify") || MPSTDFlow) {
					if (optionSelect[opSel].equals("Create") || MPSTDFlow) {
						elementClick("ctl00_ContentZone_BtnCreate");
					}else if (optionSelect[opSel].equals("Modify")){
						elementClick("ctl00_ContentZone_BtnModify");
					}
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","MPEditPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPEditPage.jpeg");					
					Thread.sleep(1000);
					if (!MPSTDFlow) {						
						selectDropDown("ctl00_ContentZone_cmb_settlements_cmb_dropdown");
						selectDropDown("ctl00_ContentZone_cmb_class_cmb_dropdown");
						WebElement newSelect = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_settlements_cmb_dropdown"))).getFirstSelectedOption();
						SettlementName = newSelect.getText();
					}else {					
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_settlements_cmb_dropdown"))).selectByVisibleText(SettlementName);
						Thread.sleep(1000);
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_class_cmb_dropdown"))).selectByIndex(catSelected);
						Thread.sleep(1000);
						WebElement catSele = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_class_cmb_dropdown"))).getFirstSelectedOption();
						catSelect = catSele.getText();						
					}
					
					action.click(driver.findElement(By.id("ctl00_ContentZone_mny_fixedprice_txt_formated"))).build().perform();
					action.sendKeys(ranNumbr(10,1000)+"").build().perform();;
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","DataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilled.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					actualResults.set(4, "All fields have been filled/modified accordingly and Submit button has been clicked");					
					Thread.sleep(1000);
				}
				if (optionSelect[opSel].equals("Delete")) {
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					actualResults.set(4, "Accept button has been clicked on the deletion pop up confirmation alert");
					Thread.sleep(1000);
					if (driver.getPageSource().contains("This monthly pass is currently in use")) {
						errorText=getText("ctl00_LblError");
						nodeleted=true;
						return;
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}		
}