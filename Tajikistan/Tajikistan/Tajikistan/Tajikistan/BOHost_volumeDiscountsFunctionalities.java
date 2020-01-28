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
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;



public class BOHost_volumeDiscountsFunctionalities {		
		private static boolean nodeleted = false;				
		private static String vDiscount1;
		private static int opSel;			
		private static boolean errorVDiscount=false;
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
		public void VolumeDiscountsFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);
			testPlanReading(20,0,2,3);
			accountModeTaj = "New";			
			Thread.sleep(1000);

			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			accountOnly = false;
			VolumeDiscountsFunctionalities();
			Thread.sleep(1000);						
			if(NoDocumentToSelect) {
				actualResults.set(3, "Unable to "+optionSelect[opSel]+" a Volume discount because of there is not Volume discount found in the table");
				for (int i=4;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+" a Volume discount because of there is not Volume discount found in the table");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;				
			}
			
			if (nodeleted) {
				actualResults.set(8, "Unable to delete Volume Discount : "+vDiscount1+" because of: "+errorText);				
				driver.close();
				testLink();
				System.out.println("Unable to delete Volume Discount : "+vDiscount1+" because of: "+errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}

			if (errorVDiscount){
				actualResults.set(8, "Unable to "+optionSelect[opSel]+" a Volume Discount because of: "+errorText);				
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+" a Volume Discount because of: "+errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}
						
			if (optionSelect[opSel].equals("Create")){
				actualResults.set(8, "Volume Discount: "+ vDiscountName+ " has been created correctly and confirmed in the creation of an Account with Discount: "+accountNumbr);
				System.out.println("Volume Discount: "+ vDiscountName+ " has been created correctly and confirmed in the creation of an Account with Discount: "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Modify")){				
				actualResults.set(8, "Volume Discount "+ vDiscount1+ " has been Modified to another Volume Discount "+ vDiscountName +" correctly");
				System.out.println("Volume Discount "+ vDiscount1+ " has been Modified to another Volume Discount "+ vDiscountName +" correctly");
				
			}
			if (optionSelect[opSel].equals("Delete")){
				actualResults.set(8, "Volume Discount "+ vDiscount1+ " has been deleted correctly");
				System.out.println("Volume Discount "+ vDiscount1+ " has been deleted correctly");
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));

		}
		
		public static void VolumeDiscountsFunctionalities() throws Exception{
			action = new Actions (driver);
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
				
				//Step 3.- Click on System settings, then Account parameters and then click on Volume discounts link
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();				
				pageSelected = "Volume discounts";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					fail(errorText);				
				}
				takeScreenShot("E:\\Selenium\\","VolumeDiscountsMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","VolumeDiscountsMainPage.jpeg");
				
				//Step 4.- Click on any Button: Create (Create a new Volume discount), Modify (after selecting any existig Volume discount, in order to modify the Volume discount), Delete (after selecting any existing Volume discount to delete the Volume discount).
				opSel = ranNumbr(0,2);
				switch (optionSelect[opSel]){
					case "Create":					Thread.sleep(1000);													
													vDiscountFunctions();
													Thread.sleep(1000);													
													break;
					case "Modify":					Thread.sleep(1000);
													vDiscountFunctions();
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);
													vDiscountFunctions();
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
		public static void vDiscountFunctions() throws Exception{
			action = new Actions(driver);
			try {
				Thread.sleep(1000);			
				vDiscountSel = true;
				
				//Step 5.- if Create/Modified button was clicked, fill or modify all fields accordingly and click on Submit button; if Delete was clicked click on Accept button in the Popup
				if (optionSelect[opSel].equals("Modify")||optionSelect[opSel].equals("Delete")) {
					for (int i=5;i<8;i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					searchandSelect();
					if(NoDocumentToSelect) {
						return;		
					}					
					vDiscount1=getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[2]");
				}			
				if (optionSelect[opSel].equals("Create") || optionSelect[opSel].equals("Modify")) {
					vDiscountName = "VoumeDiscount_"+ranNumbr(100,1000);
					if (optionSelect[opSel].equals("Create")) {						
						elementClick("ctl00_ContentZone_BtnCreate");
						actualResults.set(3, "Create button has been clicked and Volume discount Creation page is displayed");
						Thread.sleep(1000);
						takeScreenShot("E:\\Selenium\\","VolumeDiscountsWindowPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","VolumeDiscountsEditWindow.jpeg");
						SendKeys("ctl00_ContentZone_txtPanelVDName_box_data", vDiscountName);
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelVDStartDate_box_date",dateSel(2019,2020));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelVDStartDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelVDEndDate_box_date",dateSel(2020,2022));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelVDEndDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_txtPanelVDThreshold_box_data", String.valueOf(ranNumbr(10,999)));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_txtPanelVDBonus_box_data", String.valueOf(ranNumbr(10,999)));
						Thread.sleep(1000);
					}else {
						elementClick("ctl00_ContentZone_BtnModify");
						actualResults.set(3, "Modify button has been clicked and Volume discount Edit page is displayed");
						Thread.sleep(1000);
						takeScreenShot("E:\\Selenium\\","VolumeDiscountsWindowPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","VolumeDiscountsEditWindow.jpeg");
						SendKeys("ctl00_ContentZone_txtPanelModifyVDName_box_data", vDiscountName);
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyVDStartDate_box_date",dateSel(2019,2020));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyVDStartDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyVDEndDate_box_date",dateSel(2020,2022));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dtPanelModifyVDEndDate_box_hour",hourFormat(0,23,0,59));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_txtPanelModifyVDThreshold_box_data", String.valueOf(ranNumbr(10,999)));
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_txtPanelModifyVDBonus_box_data", String.valueOf(ranNumbr(10,999)));
						Thread.sleep(1000);					
					}
					takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilled.jpeg");
					if (optionSelect[opSel].equals("Create")){
						elementClick("ctl00_ContentZone_BtnPanelAddVDSubmit");
					}else {
						elementClick("ctl00_ContentZone_BtnPanelModifyVDSubmit");
					}					
					Thread.sleep(2000);
					if (driver.getPageSource().contains("End time must be later begin time") || driver.getPageSource().contains("Name already exists. Please choose another one") || driver.getPageSource().contains("The start date and end date must be greater than the current date")) {						
						errorText = getText("ctl00_ContentZone_LblPanelAddVDMsg");
						errorVDiscount = true;
						for (int i=5;i<8;i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
						}
						return;
					}
					if (optionSelect[opSel].equals("Create")){
						//Step from 6 to 8
						action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
						Thread.sleep(1000);
						action.moveToElement(driver.findElement(By.linkText("Create account"))).build().perform();
						Thread.sleep(1000);
						driver.findElement(By.linkText("Individual")).click();
						Thread.sleep(1000);											
						individualAccount();					
						Thread.sleep(1000);
					}
				}
				if (optionSelect[opSel].equals("Delete")) {
					elementClick("ctl00_ContentZone_BtnDelete");
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					String deleted = getText("ctl00_LblError");
					if (!deleted.contains("Volume discount deleted" )) {
						errorText = getText("ctl00_LblError");
						nodeleted=true;
						return;
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
				
		}		
		
}