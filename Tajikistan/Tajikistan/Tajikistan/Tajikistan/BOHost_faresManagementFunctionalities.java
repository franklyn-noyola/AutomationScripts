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


public class BOHost_faresManagementFunctionalities{		
		private static boolean noEditeddeleted = false;
		private static String optionselected;
		private static boolean NotCopy=false;
		private static boolean duplicate= false;
		private static String faresActivation;		
		private static boolean notable=false;
		private static String Plaza;
		private static String Plaza2;
		private static int opSel;
		private static int opSel2;					
		public static String faresType1;
		public static String faresType2;
		private static String [] optionSelect = {"Create","Modify","Delete"};
		private static String [] mainSelect = {"Create Copy","Edit","Delete"};
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
		public void faresManagementFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(10,0,2,3);
			Thread.sleep(1000);
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			faresManagementFunctionalities();
			Thread.sleep(1000);
			if (notable) {
				actualResults.set(5, "Fares Activation date "+faresActivation+" has been created or cloned, but not able to modify or delelte, because there is not record, only it is able to create");
				for (int i=6;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");					
				}
				driver.close();
				testLink();
				System.out.println("Fares Activation date "+faresActivation+" has been created or cloned, but not able to modify or delelte, because there is not record, only it is able to create");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;								
			}
			
			if (duplicate){
				actualResults.set(3, "Unable to create fares activation date "+faresActivation+" because of "+errorText);
				for (int i=4;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");					
				}
				driver.close();
				testLink();
				System.out.println("Unable to create fares activation date "+faresActivation+" because of "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;	
				
			}
			if (NotCopy){
				actualResults.set(3, "No able to create copy because fares activation date "+faresActivation+" is before current date or invalid date");
				for (int i=4;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");					
				}
				driver.close();
				testLink();
				System.out.println("No able to create copy because fares activation date "+faresActivation+" is before current date or invalid date");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;					
			}
			
			if (noEditeddeleted){
				actualResults.set(3, "Unable to "+mainSelect[opSel]+ " Fare Activation date: "+faresActivation+ " because of: "+ errorText);
				for (int i=4;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+mainSelect[opSel]+ " Fare Activation date: "+faresActivation+ " because of: "+ errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;								
			}
			
			if (mainSelect[opSel].equals("Create Copy") || mainSelect[opSel].equals("Edit")){
				if (optionselected.equals("Delete")){
					actualResults.set(6, "Fares Activation date "+faresActivation+" has been created/cloned or edited, and a record has been deleted as well of Toll Company "+ faresType1+" and Plaza "+ Plaza +" correctly");
					System.out.println("Fares Activation date "+faresActivation+" has been created/cloned or edited, and a record has been deleted as well of Toll Company "+ faresType1+" and Plaza "+ Plaza +" correctly");
				}
				if (optionselected.equals("Modify")){
					actualResults.set(6, "Fares Activation date "+faresActivation+" has been created/cloned or edited, and a record has been modified from Toll Company"+ faresType1+" and Plaza "+ Plaza +" to Toll Company " +faresType2+ " and Plaza "+Plaza2 + " correctly");
					System.out.println("Fares Activation date "+faresActivation+" has been created/cloned or edited, and a record has been modified from Toll Company"+ faresType1+" and Plaza "+ Plaza +" to Toll Company " +faresType2+ " and Plaza "+Plaza2 + " correctly");
				}
				if (optionselected.equals("Create")){
					actualResults.set(6, "Fares Activation date "+faresActivation+" has been created/cloned or edited, and a new record has been Created with Toll Company "+ faresType1+" and Plaza "+ Plaza +" correctly");
					System.out.println("Fares Activation date "+faresActivation+" has been created/cloned or edited, and a new record has been Created with Toll Company "+ faresType1+" and Plaza "+ Plaza +" correctly");
				}
			}

			if (mainSelect[opSel].equals("Delete")){
				actualResults.set(5, "N/A");
				executionResults.set(5, "");
				actualResults.set(6, "Fare Activation Date "+ faresActivation+ " has been deleted correctly");
				System.out.println("Fare Activation Date "+ faresActivation+ " has been deleted correctly");
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));
			
		}
		public static void faresManagementFunctionalities() throws Exception{
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
				takeScreenShot("E:\\Workspace\\Tajikistan\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(2000);			
				//Step 3.- Click on System settings, then Fares & Currency and then click on Fares definition link
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Fares & currency"))).build().perform();				
				pageSelected = "Fares management";
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
				takeScreenShot("E:\\Selenium\\","faresMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Selenium\\"+projectNamePath+testClassName+"\\attachments\\","faresMainPage.jpeg");
				
				//Step 4.- Click on any Button: Create Copy (To create a Copy from existing Fares Management, selecting from Activation dropdown list any Fares Activation and introducing a Date and Time accordingly), Edit or Delete (selecting an existing Fares Activation from Activation dropdown list)
				opSel = ranNumbr(0,2);
				switch (mainSelect[opSel]){
					case "Create Copy":				Thread.sleep(1000);
													faresActivation = dateSel(2018,2020);													
													SendKeys("ctl00_ContentZone_dt_newTime_box_date", faresActivation);
													Thread.sleep(1000);
													SendKeys("ctl00_ContentZone_dt_newTime_box_hour",hourFormat(0,23,0,59));
													Thread.sleep(1000);
													elementClick("ctl00_ContentZone_ButtonClone");
													String noCopy="";
													if (!driver.getPageSource().contains("Activation time:")) {
														noCopy = driver.findElement(By.id("ctl00_ContentZone_dt_newTime_box_date")).getAttribute("class");														
													}
													if (noCopy.equals("generalBoxRed")) {
														NotCopy = true;
														return;
													}	
													if (driver.getPageSource().contains("A fare group already exists for given date")) {
														errorText = getText("ctl00_LblError");
														duplicate = true;
														return;
													}
													actualResults.set(3, "Create Copy button has been clicked");
													createCopyEdit();
													Thread.sleep(1000);													
													break;
					case "Edit":					Thread.sleep(1000);
													new Select(driver.findElement(By.id("ctl00_ContentZone_CmbDates"))).selectByIndex(0);
													//selectDropDown("ctl00_ContentZone_CmbDates");
													Thread.sleep(1000);
													WebElement faresSelected = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbDates"))).getFirstSelectedOption();
													faresActivation = faresSelected.getText();
													elementClick("ctl00_ContentZone_BtnEdit");
													Thread.sleep(1000);
													actualResults.set(3, "Edit button has been clicked");
													if (driver.getPageSource().contains("Only future fares can be edited")) {
														errorText = getText("ctl00_LblError");
														noEditeddeleted = true;
														return;
													}
													
													createCopyEdit();
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);													
													selectDropDown("ctl00_ContentZone_CmbDates");													
													Thread.sleep(1000);
													faresSelected = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbDates"))).getFirstSelectedOption();
													faresActivation = faresSelected.getText();
													elementClick("ctl00_ContentZone_BtnDelete");
													
													Thread.sleep(500);
													if (driver.getPageSource().contains("Only future fares can be deleted")) {
														errorText = getText("ctl00_LblError");
														noEditeddeleted = true;
														return;
													}
													actualResults.set(3, "Delete button has been clicked, Deletion confirmation popup is displayed");
													if (isAlertPresent()) {
														driver.switchTo().alert().accept();
													}
													actualResults.set(4, "Accept button was clicked in the Deletelion Confirmation popup");
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
		
		public static void createCopyEdit() throws Exception {
			action = new Actions(driver);
			try {		
				takeScreenShot("E:\\Selenium\\","faresClonePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","faresClonePage.jpeg");
				
				opSel2 =ranNumbr(0,2);
				optionselected = optionSelect[opSel2];
				faresActivation = driver.findElement(By.id("ctl00_ContentZone_txt_ActivationTime_box_data")).getAttribute("value");
				WebElement tableRes = driver.findElement(By.id("ctl00_ContentZone_TblResults"));	
				List <WebElement> accountRow = tableRes.findElements(By.tagName("tr"));
				if (optionselected.equals("Modify") || optionselected.equals("Delete")) {
					if (accountRow.size()<2) {
						notable = true;
						return;
					}			
					int selectRow = ranNumbr(2,accountRow.size());				
					elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectRow+"]/td[1]/input");
					faresType1 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectRow+"]/td[2]");
					Plaza = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectRow+"]/td[3]");
					if (optionselected.equals("Delete")){						
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_BtnDelete");
						Thread.sleep(1000);
						actualResults.set(4, "Delete button was clicked, Fare deletion confirmation popup is displayed");
						if (isAlertPresent()) {
							driver.switchTo().alert().accept();
						}
						actualResults.set(4, "Selected fare has been deleted correctly");
						Thread.sleep(1000);
						takeScreenShot("E:\\Selenium\\","deleteRecord"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","deletedRecord.jpeg");
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						Thread.sleep(1000);
					}
					if (optionselected.equals("Modify")) {						
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_BtnModify");
						actualResults.set(4, "Modify button was clicked, Fare Edit page is displayed with all corresponding fields");
						Thread.sleep(1000);
						takeScreenShot("E:\\Selenium\\","modifyPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","modifyPage.jpeg");
						if (driver.findElement(By.id("ctl00_ContentZone_ChkCompany")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkCompany");
							faresType2 = driver.findElement(By.id("ctl00_ContentZone_BoxNoCompany")).getAttribute("value"); 
						}else {
							elementClick("ctl00_ContentZone_ChkCompany");							
							if (driver.findElement(By.id("ctl00_ContentZone_ChkPlaza")).isSelected()) {
								elementClick("ctl00_ContentZone_ChkPlaza");
								Plaza2 = driver.findElement(By.id("ctl00_ContentZone_BoxNoPlaza")).getAttribute("value");
							}else {
								elementClick("ctl00_ContentZone_ChkPlaza");
								selectDropDown("ctl00_ContentZone_CmbPlaza");
								WebElement plaz = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbPlaza"))).getFirstSelectedOption();
								Plaza2= plaz.getText();
							}
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkLaneDir")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkLaneDir");
						}else {
							elementClick("ctl00_ContentZone_ChkLaneDir");
							selectDropDown("ctl00_ContentZone_CmbLaneDir");
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkLaneType")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkLaneType");
						}else {
							elementClick("ctl00_ContentZone_ChkLaneType");
							selectDropDown("ctl00_ContentZone_CmbLaneType");
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkWeekDay")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkWeekDay");
						}else {
							elementClick("ctl00_ContentZone_ChkWeekDay");
							selectDropDown("ctl00_ContentZone_CmbWeekDay");
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkDayType")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkDayType");
						}else {
							elementClick("ctl00_ContentZone_ChkDayType");
							selectDropDown("ctl00_ContentZone_CmbDayType");
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkInitialHour")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkInitialHour");
						}else {
							elementClick("ctl00_ContentZone_ChkInitialHour");
							SendKeys("ctl00_ContentZone_BoxInitialHour",hourFormat2(0,23,0,59,0,59));
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkEndHour")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkEndHour");
						}else {
							elementClick("ctl00_ContentZone_ChkEndHour");
							SendKeys("ctl00_ContentZone_BoxEndHour",hourFormat2(0,23,0,59,0,59));
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkClass")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkClass");
						}else {
							elementClick("ctl00_ContentZone_ChkClass");
							selectDropDown("ctl00_ContentZone_CmbClass");
						}
						Thread.sleep(1000);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkFareGroup")).isSelected()) {
							elementClick("ctl00_ContentZone_ChkFareGroup");
						}else {
							elementClick("ctl00_ContentZone_ChkFareGroup");
							selectDropDown("ctl00_ContentZone_CmbFareGroup");
						}
						Thread.sleep(1000);	
						action.click(driver.findElement(By.id("ctl00_ContentZone_BoxAmount_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(100,1000))).build().perform();
						takeScreenShot("E:\\Selenium\\","Datamodified"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataModified.jpeg");
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						actualResults.set(5,"Selected Fare has been modified correctly");
						Thread.sleep(1000);						
						takeScreenShot("E:\\Selenium\\","DatamodifiedMainPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataModifiedMainPage.jpeg");
						return;
					}										
				}
				if (optionselected.equals("Create")) {
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_BtnCreate");
					actualResults.set(4, "Create button has been clicked, Fare creation page is displayed with all corresponding fields");
					Thread.sleep(1000);	
					takeScreenShot("E:\\Selenium\\","CreateFares"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CreateFares.jpeg");
					if (ranNumbr(0,1)==1) {
						elementClick("ctl00_ContentZone_ChkCompany");							
						if (ranNumbr(0,1)==1) {
							elementClick("ctl00_ContentZone_ChkPlaza");
							selectDropDown("ctl00_ContentZone_CmbPlaza");
							WebElement plaz = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbPlaza"))).getFirstSelectedOption();
							Plaza= plaz.getText();
							
						}else {
							Plaza = driver.findElement(By.id("ctl00_ContentZone_BoxNoPlaza")).getAttribute("value");
						}
					}else {
						faresType1 = driver.findElement(By.id("ctl00_ContentZone_BoxNoCompany")).getAttribute("value");
						Plaza = driver.findElement(By.id("ctl00_ContentZone_BoxNoPlaza")).getAttribute("value");
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1) {						
						elementClick("ctl00_ContentZone_ChkLaneDir");
						selectDropDown("ctl00_ContentZone_CmbLaneDir");
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1) {						
						elementClick("ctl00_ContentZone_ChkLaneType");
						selectDropDown("ctl00_ContentZone_CmbLaneType");
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1) {
						elementClick("ctl00_ContentZone_ChkWeekDay");
						selectDropDown("ctl00_ContentZone_CmbWeekDay");
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1){						
						elementClick("ctl00_ContentZone_ChkDayType");
						selectDropDown("ctl00_ContentZone_CmbDayType");
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1){
						elementClick("ctl00_ContentZone_ChkInitialHour");
						SendKeys("ctl00_ContentZone_BoxInitialHour",hourFormat2(0,23,0,59,0,59));
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1){
						elementClick("ctl00_ContentZone_ChkEndHour");
						SendKeys("ctl00_ContentZone_BoxEndHour",hourFormat2(0,23,0,59,0,59));
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1){
						elementClick("ctl00_ContentZone_ChkClass");
						selectDropDown("ctl00_ContentZone_CmbClass");
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)==1){
						elementClick("ctl00_ContentZone_ChkFareGroup");
						selectDropDown("ctl00_ContentZone_CmbFareGroup");
					}
					Thread.sleep(1000);
					action.click(driver.findElement(By.id("ctl00_ContentZone_BoxAmount_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(100,1000))).build().perform();
					takeScreenShot("E:\\Selenium\\","DataCreated"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataCreated.jpeg");					
					Thread.sleep(1000);
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					actualResults.set(5, "A new Fare is created correctly");
					Thread.sleep(1000);						
					takeScreenShot("E:\\Selenium\\","DataCreatedMainPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataCreatedMainPage.jpeg");
					return;
					
					}																			
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());				
				fail(e.getMessage());
				throw(e);
			}				
			
		}
		
}