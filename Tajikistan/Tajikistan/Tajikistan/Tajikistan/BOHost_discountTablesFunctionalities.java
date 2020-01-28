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



public class BOHost_discountTablesFunctionalities{		
		private static boolean nodeleted = false;
		public static String deleted;
		private static String dTables1;
		private static int opSel;			
		private static boolean errorDTables=false;
		public static int selOp;		
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
		public void DiscountTablesFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(8,0,2,3);
			Thread.sleep(1000);		
			accountOnly=false;
			accountModeTaj = "New";
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			DiscountTablesFunctionalities();
			Thread.sleep(1000);
			if (nodeleted) {
				actualResults.set(5, "Unable to delete Discount Table: " + dTables1+" because of: "+deleted);				
				Thread.sleep(500);
				driver.close();
				testLink();
				System.out.println("Unable to delete Discount Table: " + dTables1+" because of: "+deleted);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}

			if (errorDTables){
				actualResults.set(5, "Unable to Create/Modify Discount table because of: "+ errorText);				
				Thread.sleep(500);
				driver.close();
				testLink();
				System.out.println("Unable to Create/Modify Discount table because of: "+ errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}
						
			if (optionSelect[opSel].equals("Create")){
				actualResults.set(5, "Discount Tables: "+ dTablesName+ " has been created correctly and confirmed in the creation of the Account: "+accountNumbr+" with Discount");
				System.out.println("Discount Tables: "+ dTablesName+ " has been created correctly and confirmed in the creation of the Account: "+accountNumbr+" with Discount");
			}
			
			if (optionSelect[opSel].equals("Modify")){				
				actualResults.set(5, "Discount Tables "+ dTables1+ " has been Modified to another Discount Tables"+ dTablesName +" correctly");
				System.out.println("Discount Tables "+ dTables1+ " has been Modified to another Discount Tables"+ dTablesName +" correctly");				
			}
			if (optionSelect[opSel].equals("Delete")){
				actualResults.set(5, "Discount Tables"+ dTables1+ " has been deleted correctly");
				System.out.println("Discount Tables"+ dTables1+ " has been deleted correctly");
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));

		}
		public static void DiscountTablesFunctionalities() throws Exception{
			action = new Actions (driver);
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
				
				//Step 3.- Click on System settings, then Account Parameters and then click on Discount Tables
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();
				
				pageSelected = "Discount tables";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				}
				takeScreenShot("E:\\Selenium\\","VolumeDiscountsMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","VolumeDiscountsMainPage.jpeg");
				
				//Step 4.- Click on any Button: Create (To create a new Discount Table), Modify or Delete (if there are existing Discount Table in the table)
				opSel = 0;ranNumbr(0,2);
				switch (optionSelect[opSel]){
					case "Create":					Thread.sleep(1000);													
													dTablesFunctions();
													Thread.sleep(1000);													
													break;
					case "Modify":					Thread.sleep(1000);
													dTablesFunctions();
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);
													dTablesFunctions();
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
		public static void dTablesFunctions() throws Exception{
			action = new Actions(driver);
			try {
				Thread.sleep(1000);			
				discountTable = true;
				if (optionSelect[opSel].equals("Modify")||optionSelect[opSel].equals("Delete")) {
					if (optionSelect[opSel].equals("Modify")||optionSelect[opSel].equals("Delete")) {
						itemSearchedandSelected();
						dTables1=getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[2]");
					}
				}
				if (optionSelect[opSel].equals("Create") || optionSelect[opSel].equals("Modify")) {
					dTablesName = "DiscountTables_"+ranNumbr(100,1000);
					if (optionSelect[opSel].equals("Create")) {						
						elementClick("ctl00_ContentZone_BtnCreate");
						actualResults.set(3, "Create button has been clicked");
						Thread.sleep(1000);
						takeScreenShot("E:\\Selenium\\","DiscountTablesWindowPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DiscountTablesEditWindow.jpeg");
						SendKeys("ctl00_ContentZone_txtPanelTDName_box_data", dTablesName);
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelTDStartDate_box_date",dateSel(2019,2020));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelTDStartDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelTDEndDate_box_date",dateSel(2020,2022));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelTDEndDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						int dPercentage = ranNumbr(0,100);
						if (dPercentage==100) {
							SendKeys("ctl00_ContentZone_txtPanelTDDiscount_box_data", dPercentage+"");
						}else {
						SendKeys("ctl00_ContentZone_txtPanelTDDiscount_box_data", dPercentage+"."+ranNumbr(0,99));
						}
						actualResults.set(4, "All data is entered correctly in Discount Table creation page");
						Thread.sleep(1000);
					}else {
						elementClick("ctl00_ContentZone_BtnModify");
						actualResults.set(3,  "Modify button has been clicked");
						Thread.sleep(1000);						
						takeScreenShot("E:\\Selenium\\","DiscountTablesWindowPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DiscountTablesEditWindow.jpeg");
						SendKeys("ctl00_ContentZone_txtPanelModifyTDName_box_data", dTablesName);
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyTDStartDate_box_date",dateSel(2019,2020));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyTDStartDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyTDEndDate_box_date",dateSel(2020,2022));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyTDEndDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						int dPercentage = ranNumbr(0,100);
						if (dPercentage==100) {
							SendKeys("ctl00_ContentZone_txtPanelModifyTDDiscount_box_data", dPercentage+"");
						}else {
							SendKeys("ctl00_ContentZone_txtPanelModifyTDDiscount_box_data", dPercentage+"."+ranNumbr(0,99));
						}
						actualResults.set(4, "All data is modified correctly in Discount Table Edit page");
						Thread.sleep(1000);			
					}
					takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilled.jpeg");
					if (optionSelect[opSel].equals("Create")){						
						elementClick("ctl00_ContentZone_BtnPanelAddTDSubmit");
					}else {
						elementClick("ctl00_ContentZone_BtnPanelModifyTDSubmit");
					}					
					Thread.sleep(1000);
					if (driver.getPageSource().contains("End time must be later begin time") || driver.getPageSource().contains("Name already exists. Please choose another one") || driver.getPageSource().contains("The start date and end date must be greater than the current date")) {
						errorText = getText("//*[@class='lblError']");						
						errorDTables= true;
						return;
					}
					if (optionSelect[opSel].equals("Create")){
						action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
						Thread.sleep(1000);
						action.moveToElement(driver.findElement(By.linkText("Create account"))).build().perform();
						Thread.sleep(1000);
						elementClick("Individual");
						Thread.sleep(1000);
						BOHost_accountCreationOnly.individualAccount();					
						Thread.sleep(1000);
					}
				}
				if (optionSelect[opSel].equals("Delete")) {					
					elementClick("ctl00_ContentZone_BtnDelete");
					actualResults.set(3, "Delete Button is clicked");
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					actualResults.set(4, "Deletion Confirmation popup is displayed");
					deleted = getText("ctl00_LblError");
					if (!deleted.contains("Discount table deleted" )) {
						nodeleted=true;
						return;
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw (e);
			}
				
		}		
		
}