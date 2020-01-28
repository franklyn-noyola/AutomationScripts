package Tajikistan.Tajikistan;
import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static Tajikistan.Tajikistan.BOHost_settlementsFunctionalities.*;
import static Tajikistan.Tajikistan.BOHost_MPFunctionalities.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationwithVehicle.*;


public class BOHost_MPSDTwithExistingAccount{		
		private static boolean accountClosed = false;
		public static boolean vehFound;
		private static int NumbVeh;;	
		public static boolean alreadyMPSDT=false;
		public static String existingMPSDT;
		public static int opSel;		
		//public static String [] optionSelect = {"Validate","Reject","ReValidate","Remove","RegisterAfterRemove"};
		private static String [] flowSelect = {"SDT","MP"};
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
		public void MPSDTwithExistingAccountInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);
			testPlanReading(13,0,2,3);			
			Thread.sleep(1000);
			accountModeTaj = "Edit";
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			MPSDTwithExistingAccount();
			Thread.sleep(1000);
			if (accountClosed) {
				actualResults.set(11, "Unable to set a "+flowSelect[opSel]+" to the Account "+accountNumbr+" because it is closed");
				for (int i=12;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");					
				}
				driver.close();
				testLink();
				System.out.println("Unable to set a "+flowSelect[opSel]+" to the Account "+accountNumbr+" because it is closed");				
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;	
			}
			
			if (alreadyMPSDT) {
				actualResults.set(16, "Unable to set a "+flowSelect[opSel] +" to the Account "+accountNumbr+" because there is an existing one in Vehicle with Plate "+matriNu);
				for (int i=17;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");					
				}
				driver.close();
				testLink();
				System.out.println("Unable to set a "+flowSelect[opSel] +" to the Account "+accountNumbr+" because there is an existing one in Vehicle with Plate "+matriNu);				
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;	
			}
			actualResults.set(29, flowSelect[opSel]+" is created and assigned to Account "+accountNumbr+" in Vehicle with Plate "+matriNu+" with Documemnts already uploaded and pending for approval/reject");
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println(flowSelect[opSel]+" is created and assigned to Account "+accountNumbr+" in Vehicle with Plate "+matriNu+" with Documemnts already uploaded and pending for approval/reject");
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));

		}
	
		
		public static void MPSDTwithExistingAccount() throws Exception{
			Thread.sleep(1000);
			opSel = ranNumbr(0,1);
			optionVehicle = "NotCreated";
			MPSTDFlow=true;
			catSelected=ranNumbr(0,2);
			action = new Actions(driver);
			
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
				//Steps 3.- Click on System settings, then Fares & Currency and then Settlements
				action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Fares & currency"))).build().perform();
				Thread.sleep(1000);
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
				switch (flowSelect[opSel]) {
					case "SDT":							SDTOption = true;
														for (int i=5;i<8;i++) {
															actualResults.set(i, "N/A");
															executionResults.set(i, "");						
														}
														noSDT=true;
														MPOption = false;
														Thread.sleep(500);															
														selectDocumentTocreateMPSDT();
														break;
														
					case "MP":							MPOption = true;
														SDTOption = false;
														Thread.sleep(500);															
														selectDocumentTocreateMPSDT();
														break;								
				}
		
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}

		}
		public static void selectDocumentTocreateMPSDT() throws Exception{
			Thread.sleep(1000);
			action = new Actions (driver);
			try {
				Thread.sleep(1000);
				//From Step 4 to 5:
				createSettlement();
				actualResults.set(4, "Settlement with name: "+SettlementName+" has been created correctly");				
				Thread.sleep(1000);
				
				if (MPOption) {
					//Step 6.- If only for STD go to step 9, otherwise if for Montly Pass, click on Settings, then Account Parameters and then Montly Pass link
					action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
					Thread.sleep(500);
					action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();
					Thread.sleep(500);
					driver.findElement(By.linkText("Monthly pass")).click();
					Thread.sleep(1000);
					
					//From Step 7 to 8
					createEditDeleteMP();
					Thread.sleep(1000);
				}			
			
			//Step 9.- Click on Customer Service and then click on Select Account
			action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
			Thread.sleep(500);
			driver.findElement(By.linkText("Select account")).click();
			Thread.sleep(500);
			
			//Step 10.- Select Individual Account in the Account Type and click on Search button
			new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByIndex(1);
			Thread.sleep(200);
			elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
			Thread.sleep(1000);		
			
			//Step 11.- Click on any individual account
			selectAccount();
			//To entry a specific account number:
			/*SendKeys("ctl00_ContentZone_txt_accountId_box_data","3368");
			Thread.sleep(1000);		
			elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
			Thread.sleep(1000);		
			driver.findElement(By.linkText("000003368")).click();	*/							
			takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");			
			accountNumbr = getText("ctl00_SectionZone_LblTitle");
			accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
			
			//Step 12.- Click on Edit button
			if(driver.getPageSource().contains("CLOSED ACCOUNT")){
				accountClosed = true;
				return;
			}
			String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
			Thread.sleep(1000);
			NumbVeh = Integer.parseInt(numberVehicles);
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(2000);
			
			//Step 13.- If Settlement Checbox is unchecked, check it and assign the just created Settlement otherwise only assign just created settlement
			if (!driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_chk_settlements")).isSelected()){
					elementClick("ctl00_ContentZone_ctrlAccountData_chk_settlements");
					Thread.sleep(1000);
					new Select(driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_cmb_settlements_cmb_dropdown"))).selectByVisibleText(SettlementName);
					Thread.sleep(1000);
				}else {
					new Select(driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_cmb_settlements_cmb_dropdown"))).selectByVisibleText(SettlementName);
					Thread.sleep(1000);							
				}
			
			//Step 14.- Click on Save button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");				
			Thread.sleep(1000);
			List<WebElement>RedClassCount = driver.findElements(By.xpath("//*[@class='generalBoxRed']"));			
			if (RedClassCount.size()>0) {				
				for (int i=1;i<=RedClassCount.size();i++) {
					emptyField = driver.findElement(By.xpath("//*[@class='generalBoxRed']")).getAttribute("id");					
					Thread.sleep(1000);
					mandatoryFieldsValidation();
					Thread.sleep(100);										
				}				
			}
			if (driver.getPageSource().contains("must have (1 letter + 7 numbers) or (1 letter + 8 numbers)")|| driver.getPageSource().contains("value already exists in the system")) {
				passportValidation();
			}
			Thread.sleep(1000);
			
			//Step 15.- Click on Edit button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(1000);
			
			//Step 16.- Click on Vehicles button
			elementClick("ctl00_ContentZone_BtnVehicles");

			//Step 17.- Verify if there is a Vehicle at least, if so, verify that Vehicle has not STD or MP already assigned (Rejected or pending status) and click on Modify button, but if there is not Vehicle assigned to Account click on Create button
			if (NumbVeh>0) {
				int rowIncre = 2;
				for (int i=1;i <=NumbVeh;i++) {
					String SDTMPtag = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[8]");
					Thread.sleep(500);					
					if (SDTMPtag.contains("SDT")||SDTMPtag.contains("MP")||SDTMPtag.contains("Pending")||SDTMPtag.contains("Rejected")){
						alreadyMPSDT = true;	
						return;
					}
					rowIncre = rowIncre+1; 
				}
				rowIncre = 2;
				String catVe= getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[2]");				
				for (int i=1;i <=NumbVeh;i++) {
					catVe= getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[2]");
					Thread.sleep(1000);					
					if (catVe.contains(catSelect.substring(4))){
						elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[1]");
						matriNu= getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[3]");
						i = NumbVeh;
						vehFound = true;
					}
					if (i==NumbVeh && !catVe.contains(catSelect.substring(4))){
						vehFound = false;
					}
					rowIncre = rowIncre+1; 
				}
				
				//From Step 18 to 20
				if (vehFound=true){
					elementClick("ctl00_ContentZone_BtnModify");
					Thread.sleep(1000);
					if (matriNu.isEmpty()) {
						vehicleCreation();					 
						Thread.sleep(1000);
					}else {				
						if (SDTOption) {
							elementClick("ctl00_ContentZone_discount_1");
						}
						if (MPOption) {
							elementClick("ctl00_ContentZone_discount_2");
						}
					}
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						Thread.sleep(2000);
						elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");										
					}else {
						elementClick("ctl00_ContentZone_BtnCreate");
						Thread.sleep(1000);
						vehicleCreation();
						Thread.sleep(1000);
					}	
				
				Thread.sleep(1000);
				}else {
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					vehicleCreation();
					Thread.sleep(1000);
				}
			uploadDocumentSDT();
                                             			
			}catch (Exception e){
				System.err.println(e.getMessage());
				e.printStackTrace();
				fail(e.getMessage());
				throw (e);
			}
			
	}
		public static void uploadDocumentSDT() throws Exception{
			Thread.sleep(1000);
			String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
			Thread.sleep(1000);
			NumbVeh = Integer.parseInt(numberVehicles);
			
			//Step 21.- Click on Edit button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(500);			
			
			//Step 22.- Click on Vehicles button
			elementClick("ctl00_ContentZone_BtnVehicles");
			Thread.sleep(500);	
			int rowIncre = 2;
			
			//Step 23.- Select the just created/modified vehicle and click on STD/MP button
			for (int i=1;i<=NumbVeh;i++) {
				String SDTMPtag = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[8]");
				Thread.sleep(500);					
				if (SDTMPtag.contains("Pending")){
					elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncre+"]/td[1]");
					i = NumbVeh;
				}
				rowIncre = rowIncre+1; 
			}
			elementClick("ctl00_ContentZone_BtnSDTMP");
			Thread.sleep(500);
			
			//Step 24.- Click on Register button
			elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnRegister");
			Thread.sleep(500);
			
			//Step 25.- Upload or put the corresponding documents in each field
			driver.findElement(By.name("ctl00$ContentZone$ctrlDiscountInfo$FileUploadUserPassport")).sendKeys("C:\\archivosPruebas\\CursoLinux_v1.1.pdf");
			Thread.sleep(1000);
			driver.findElement(By.name("ctl00$ContentZone$ctrlDiscountInfo$FileUploadVehiclePassport")).sendKeys("C:\\archivosPruebas\\DIAGRAMA.docx");				
			Thread.sleep(1000);
			driver.findElement(By.name("ctl00$ContentZone$ctrlDiscountInfo$FileUploadRegistrationDoc")).sendKeys("C:\\archivosPruebas\\documentoPro.docx");				
			Thread.sleep(1000);
			driver.findElement(By.name("ctl00$ContentZone$ctrlDiscountInfo$FileUploadVehicleImage")).sendKeys("C:\\archivosPruebas\\qa.jpg");				
			Thread.sleep(1000);
			driver.findElement(By.name("ctl00$ContentZone$ctrlDiscountInfo$FileUploadSignedContract")).sendKeys("C:\\archivosPruebas\\Encaje.docx");
			Thread.sleep(1000);
			
			//Step 26.- Click on Apply button
			elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSDTDocsApply");
			Thread.sleep(1500);
			
			//Step 27.- Click on Ok button 
			elementClick("popup_ok");
			Thread.sleep(1000);
			
			//From step 28 to 29
			elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
			Thread.sleep(1000);	
			
			// Step 30.- Click on back button and click on Save button
			elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
			Thread.sleep(1000);
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(1000);			
		}
		
		
	}	
		