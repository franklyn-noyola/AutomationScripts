package Tajikistan.Tajikistan;
import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static Tajikistan.Tajikistan.BOHost_settlementsFunctionalities.*;
import static Tajikistan.Tajikistan.BOHost_MPFunctionalities.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationwithVehicle.*;

public class BOHost_MPSTDFlow{		
		public static int opSel2;	
		public static int step;
		public static boolean found; 		
		public static boolean noValidated=false;
		public static boolean noValidatePending=false;
		public static boolean noPending=false;
		public static boolean noRejected=false;
		public static boolean noSolved=false;
		public static boolean noDiscarded=false;
		public static String STDMPValidated;
		public static int opSel;		
		public static String [] optionSelect = {"Validate","Reject","SolveAfterReject","DiscardAfterReject","ValidateAfterSolve","ValidateAfterDiscard"};
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
		public void MPSTDFlowInit() throws Exception {					
			SDTOption = false;
			MPOption = false;			
			Thread.sleep(1000);
			accountModeTaj="New";
			accountOnly=false;
			opSel2 =ranNumbr(0,5);
			if (opSel2==0) {
				configurationSection("Host",testNameSelected+" - Validate",testNameSelected);
				testPlanReading(14,0,2,3);
				
			}
			if (opSel2==1) {
				configurationSection("Host",testNameSelected+" - Reject",testNameSelected);
				testPlanReading(14,5,7,8);
			}
			if (opSel2==2) {
				configurationSection("Host",testNameSelected+" - SolveAfterReject",testNameSelected);
				testPlanReading(14,10,12,13);				
			}
			if (opSel2==3) {
				configurationSection("Host",testNameSelected+" - DiscardAfterReject",testNameSelected);
				testPlanReading(14,15,17,18);				
			}
			if (opSel2==4) {
				configurationSection("Host",testNameSelected+"-ValidateAfterSolve",testNameSelected);
				testPlanReading(14,20,22,23);				
			}
			if (opSel2==5) {
				configurationSection("Host",testNameSelected+"-ValidateAfterDiscard",testNameSelected);
				testPlanReading(14,25,27,28);				
			}
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			MPSTDFlow();
			Thread.sleep(1000);
			
			if (noPending) {				
				takeScreenShot("E:\\Selenium\\","noPendingErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","noPendingErr.jpeg");				
				actualResults.set(19, "ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try");
				executionResults.set(19,"Fallado");
				stepNotExecuted = executionNumber.size()-1;
				for (int i = stepNotExecuted;i>19;i--) {
					executionNumber.remove(i);
				}
				summaryBug = "ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try";
				erroTextBug = "ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try";
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\noPendingErr.jpeg";
				driver.close();				
				testLink();		
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				fail("ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try");	
			}
			
			if (noValidatePending) {				
				takeScreenShot("E:\\Selenium\\","noValidatePendingErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","noValidatePendingErr.jpeg");				
				actualResults.set(47, "ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try");
				executionResults.set(47,"Fallado");
				stepNotExecuted = executionNumber.size()-1;
				for (int i = stepNotExecuted;i>47;i--) {
					executionNumber.remove(i);
				}
				summaryBug = "ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try";
				erroTextBug = "ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try";
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\noValidatePendingErr.jpeg";
				driver.close();				
				testLink();		
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				fail("ERROR: MP in account "+accountNumbr+ " in Vehicle with Plate "+matriNu+" is not in Pending status despite it was created with MP option at first try");	
			}
			
			if (noValidated) {
				
				if (optionSelect[opSel2].equals("Validate")){
					step = 34;
				}
				if (optionSelect[opSel2].equals("ValidateAfterSolve")){
					step = 43;
				}
				if (optionSelect[opSel2].equals("ValidateAfterDiscard")){
					step = 56;
				}
				takeScreenShot("E:\\Selenium\\","noValidatedErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","noValidatedErr.jpeg");				
				actualResults.set(step, flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				executionResults.set(step,"Fallado");
				stepNotExecuted = executionNumber.size()-1;				
				summaryBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				erroTextBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\noValidatedErr.jpeg";
				driver.close();				
				testLink();
				System.out.println(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));				
				fail(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");				
			}
			
			if (noRejected) {
				takeScreenShot("E:\\Selenium\\","noRejectedErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","noRejectedErr.jpeg");				
				actualResults.set(34, flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				executionResults.set(34,"Fallado");
				stepNotExecuted = executionNumber.size()-1;				
				summaryBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				erroTextBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\noRejectedErr.jpeg";
				driver.close();				
				testLink();
				System.out.println(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				fail(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				
			}
			if (noSolved) {
				takeScreenShot("E:\\Selenium\\","notSolvedErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","notSolvedErr.jpeg");				
				actualResults.set(43, flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				executionResults.set(43,"Fallado");
				stepNotExecuted = executionNumber.size()-1;				
				summaryBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				erroTextBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\notSolvedErr.jpeg";
				driver.close();				
				testLink();
				System.out.println(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				fail(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
			}
			
			if (noDiscarded) {
				takeScreenShot("E:\\Selenium\\","notDiscardedErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","notDiscardedErr.jpeg");				
				actualResults.set(40, flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				executionResults.set(40,"Fallado");
				stepNotExecuted = executionNumber.size()-1;				
				summaryBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				erroTextBug = flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status";
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\notDiscardedErr.jpeg";
				driver.close();				
				testLink();
				System.out.println(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				fail(flowSelect[opSel] +" with Settlement: "+SettlementName+" has not been Discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu+ " it is in "+STDMPValidated+" status");
			}
						
				if (optionSelect[opSel2].equals("Validate")){
					actualResults.set(34, flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);					
					System.out.println(flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Validated for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
				}
				
				if (optionSelect[opSel2].equals("Reject")){
					actualResults.set(34, flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
					System.out.println(flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
				}
				if (optionSelect[opSel2].equals("SolveAfterReject")){
					actualResults.set(43, flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Solved after rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
					System.out.println(flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Solved after rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
				}
				if (optionSelect[opSel2].equals("DiscardAfterReject")){
					actualResults.set(40, flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Discarded after rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
					System.out.println(flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Discarded after rejected for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
				}
				if (optionSelect[opSel2].equals("ValidateAfterSolve")){
					actualResults.set(43, flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Validated after rejected and solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
					System.out.println(flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Validated after rejected and solved for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
				}
				if (optionSelect[opSel2].equals("ValidateAfterDiscard")){
					actualResults.set(56, flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Validated after discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
					System.out.println(flowSelect[opSel]+" with Settlement: "+SettlementName+" has been Validated after discarded for Account "+accountNumbr+" in Vehicle with Plate "+matriNu);
				}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));

		}
	
		
		public static void MPSTDFlow() throws Exception{
			action = new Actions(driver);
			MPSTDFlow=true;
			catSelected=ranNumbr(0,2);			
			action = new Actions(driver);
			opSel = ranNumbr(0,1);
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
				
				//Step 3.- Click on System settings, then Fares & Currency and then Settlements
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
														Thread.sleep(500);
														for (int i=5;i<8;i++) {
															actualResults.set(i, "N/A");
															executionResults.set(i, "");						
														}
														SDTMPProcessFlow();
														break;
														
					case "MP":							MPOption = true;
														Thread.sleep(500);														
														SDTMPProcessFlow();
														break;								
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		public static void SDTMPProcessFlow() throws Exception{
			Thread.sleep(1000);
			action = new Actions (driver);
			try {
				Thread.sleep(1000);
				
				//From Step 4 to Step 5
				createSettlement();				
				Thread.sleep(1000);
				if (MPOption) {
					action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
					Thread.sleep(500);
					action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();
					Thread.sleep(500);
					driver.findElement(By.linkText("Monthly pass")).click();
					Thread.sleep(1000);
					createEditDeleteMP();
					Thread.sleep(1000);
				}				
				
				//Step 9.- Click on Customer Service and then click on Create account and then click on Individual account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Create account"))).build().perform();
				Thread.sleep(500);
				pageSelected = "Individual Accunt";
				elementClick("Individual");
				Thread.sleep(500);
				pageSelectedErr(8);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				
				//Step from 10 to 12				
				individualAccount();
				Thread.sleep(1500);
				
				//Step from 13 to 17
				optionVehicle = "CreateVehicle";
				vehicleCreation();
				Thread.sleep(1500);
				
				//Step 18.- Click on Edit button
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				
				//Step 19.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(1000);
				
				//Step 20.- Select the just created/modified vehicle and click on STD/MP button
				elementClick("ctl00_ContentZone_radio0");
				Thread.sleep(500);
				String MPtag = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (!MPtag.contains("Pending")) {
					noPending = true;
					return;
				}				
				elementClick("ctl00_ContentZone_BtnSDTMP");
				Thread.sleep(500);
				
				//Step 21.- Click on Register button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnRegister");
				Thread.sleep(500);
				
				//Step 22.- Upload or put the corresponding documents in each field
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
				
				//Step 23.- Click Apply button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSDTDocsApply");
				Thread.sleep(1500);
				
				//Step 24.- Click on OK button
				elementClick("popup_ok");
				Thread.sleep(1000);
				
				//Step 25.- Click on Close button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
				Thread.sleep(1000);
				
				//Step 26.- Check the Vehicle SDT/MP status
				MPtag = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (!MPtag.contains("Pending")) {
					noPending = true;
					return;
				}				
				System.out.println("Documents are uploaded and "+flowSelect[opSel]+" is in "+driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]")).getText()+" for Approval");
				actualResults.set(25, "Documents are uploaded and "+flowSelect[opSel]+" is in "+driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]")).getText()+" for Approval");

				//Step 27.- Click on back button and click on Save button
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				flowMPprocess();
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		public static void flowMPprocess() throws Exception{
			Thread.sleep(1000);
			action = new Actions(driver);
			try {
				
				//Step 28.- Click on Customer Service and then Track Account and then SDT Approval
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(500);
				pageSelected = "SDT approval";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(27);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				
				//Step 29.- Search for the account which Settlement (STD or MP) was assigned and select it
				searchSDTMP(4,28);	
				Thread.sleep(1000);
				if (opSel2==0) {
					validateOption();
				}
				if (opSel2==1) {
					rejectOption();
				}
				if (opSel2==2) {
					solveOption();
				}
				if (opSel2==3) {
					discardOption();
				}
				
				if (opSel2==4) {
					validateAfterSolve();
				}
				if (opSel2==5) {
					validateAfterDiscard();
				}
				
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
			
		public static void validateOption() throws Exception{
				action = new Actions(driver);
				Thread.sleep(1000);
				try {
					
					//Step 30.- After Account is selected, click on Validate button
					elementClick("ctl00_ContentZone_BtnValidate");
					Thread.sleep(2000);
					
					//Step 31.- Click on Customer Service and then click on Select Account
					action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Select account";
					elementClick(pageSelected);
					Thread.sleep(1000);
					pageSelectedErr(30);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.out.println(errorText);
						fail(errorText);
						System.out.println("Tested in Version of: "+ getVersion(applevelTested));
					}
					
					//Step 32.- Search for the just validated for STD/MP account
					SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
					Thread.sleep(500);
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(1000);
					
					//Step 33.- Click on the Account
					elementClick(accountNumbr);
					Thread.sleep(1000);				
					
					//Step 34.- Click on Vehicles button
					elementClick("ctl00_ContentZone_BtnVehicles");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","MPSDTValidated"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPSDTValidated.jpeg");
					
					//Step 35.- Verify if the Vehicle STD/MP status
					STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
					if (STDMPValidated.equals("SDT") || STDMPValidated.equals("MP")) {
						Thread.sleep(1000);
						return;
						
					}else {
						noValidated = true;
						return;
					}				

				}catch (Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
		
		public static void rejectOption() throws Exception{
			action = new Actions(driver);
			Thread.sleep(1000);
			try {
				//Step 30.- After Account is selected, type a comment in Rejected Reason field and then click on Reject button
				SendKeys("ctl00_ContentZone_txtRejectedReason",flowSelect[opSel]+" for account: "+accountNumbr+" has been rejected");
				elementClick("ctl00_ContentZone_BtnReject");
				Thread.sleep(2000);
				
				//Step 31.- Click on Customer Service and then click on Select Account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(30);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 32.- Search for the just rejected account with STD/MP
				SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 33.- Click on the Account
				elementClick(accountNumbr);
				Thread.sleep(1000);				
				
				//Step 34.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","MPSDTRejected"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPSDTRejected.jpeg");
				
				//Step 35.- Verify if the Vehicle STD/MP status
				STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (STDMPValidated.equals("Rejected")) {
					Thread.sleep(1000);
					return;
					
				}else {
					noRejected = true;
					return;
				}				
			
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void solveOption() throws Exception{
			action = new Actions(driver);
			Thread.sleep(1000);
			try {
				
				//Step 30.- After Account is selected, type a comment in Rejected Reason field and then click on Reject button
				SendKeys("ctl00_ContentZone_txtRejectedReason",flowSelect[opSel]+" for account: "+accountNumbr+" has been rejected");
				elementClick("ctl00_ContentZone_BtnReject");
				Thread.sleep(2000);
				
				//Step 31.- Click on Customer Service and then Track Account and then Rejecteds STDs Link
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Rejecteds SDTs";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(30);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 32.- Search for the account which Settlement (STD or MP) just rejected				
				searchSDTMP(2,31);		
				Thread.sleep(1000);
				
				//Step 33.- Click on Solve button 
				elementClick("ctl00_ContentZone_BtnSolve");
				Thread.sleep(500);
				
				//Step 34.- Click on Solve button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSolve");
				Thread.sleep(500);
				
				//Step 35.- Click on Apply button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSDTDocsApply");
				Thread.sleep(500);
				
				//Step 36.- Click on OK button
				elementClick("popup_ok");
				Thread.sleep(500);
				
				//Step 37.- Click on Close button			
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
				Thread.sleep(1000);
				
				//Step 38.- Click on Customer Service and then Track Account and then SDT Approval 
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(500);
				pageSelected="SDT approval";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(37);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 39.- Search for the account which Settlement (STD or MP) just solved and verify if Account is found for approval				
				searchSDTMP(4,38);		
				if (found=true) {
					actualResults.set(38, "Account number: "+accountNumbr+" is found in STDs for approval");
					System.out.println("Account number: "+accountNumbr+" is found in STDs for approval");
				}
				
				//Step 40.- Click on Customer Service and then click on Select Account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(39);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 41.- Search for the just solved account for STD/MP
				SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 42.- Click on the Account
				driver.findElement(By.linkText(accountNumbr)).click();
				Thread.sleep(1000);				
				
				//Step 43.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","MPSDTSolved"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPSDTSolved.jpeg");
				
				//Step 44.- Verify if the Vehicle STD/MP status
				STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (STDMPValidated.equals("Pending")) {
					Thread.sleep(1000);
					return;
					
				}else {
					noSolved = true;
					return;
				}				
			
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void discardOption() throws Exception{
			action = new Actions(driver);
			Thread.sleep(1000);
			try {
				
				//Step 30.- After Account is selected, type a comment in Rejected Reason field and then click on Reject button
				SendKeys("ctl00_ContentZone_txtRejectedReason","STD/MP for account: "+accountNumbr+" has been rejected");
				elementClick("ctl00_ContentZone_BtnReject");
				Thread.sleep(2000);
				
				//Step 31.- Click on Customer Service and then Track Account and then Rejecteds STDs Link
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(1000);
				pageSelected="Rejecteds SDTs";
				elementClick("Rejecteds SDTs");
				Thread.sleep(1000);
				pageSelectedErr(30);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 32.- Search for the account which Settlement (STD or MP) just rejected				
				searchSDTMP(2,31);				
				Thread.sleep(1000);
				
				//Step 33.- Click on Solve button
				elementClick("ctl00_ContentZone_BtnSolve");
				Thread.sleep(500);				
				
				//Step 34.- Click on Discard Button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnDiscard");
				Thread.sleep(500);
				
				//Step 35.- Click on OK button
				elementClick("popup_ok");
				Thread.sleep(500);
				
				//Step 36.- Click on Close button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
				Thread.sleep(1000);
				
				//Step 37.- Click on Customer Service and then click on Select Account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(36);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 38.- Search for the just discarded account for STD/MP
				SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 39.- Click on the Account
				elementClick(accountNumbr);
				Thread.sleep(1000);				
				
				//Step 40.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(2000);
				
				takeScreenShot("E:\\Selenium\\","MPSDTDiscarded"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPSDTDiscarded.jpeg");
				//Step 41.- Verify if the Vehicle STD/MP status
				STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (STDMPValidated.equals("Pending")) {
					Thread.sleep(1000);
					return;
					
				}else {
					noDiscarded = true;
					return;
				}				
			
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void validateAfterSolve() throws Exception{
			action = new Actions(driver);
			Thread.sleep(1000);
			try {
				
				//Step 30.- After Account is selected, type a comment in Rejected Reason field and then click on Reject button
				SendKeys("ctl00_ContentZone_txtRejectedReason","STD/MP for account: "+accountNumbr+" has been rejected");
				elementClick("ctl00_ContentZone_BtnReject");
				Thread.sleep(2000);
				
				//Step 31.- Click on Customer Service and then Track Account and then Rejecteds STDs Link
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Rejecteds SDTs";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(30);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				
				//Step 32.- Search for the account which Settlement (STD or MP) just rejected				
				searchSDTMP(2,31);				
				Thread.sleep(1000);
				
				//Step 33.- Click on Solve button
				elementClick("ctl00_ContentZone_BtnSolve");
				Thread.sleep(500);
				
				//Step 34.- Click on Solve button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSolve");
				Thread.sleep(500);
				
				//Step 35.- Click on Apply button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSDTDocsApply");
				Thread.sleep(500);
				
				//Step 36.- Click on OK
				elementClick("popup_ok");
				Thread.sleep(500);
				
				//Step 37.- Click on Close button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
				Thread.sleep(1000);
				
				//Step 38.- Click on Customer Service and then Track Account and then SDT Approval
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(500);
				pageSelected = "SDT approval";
				elementClick (pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(37);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 39.- Search for the account which Settlement (STD or MP) just solved and select it				
				searchSDTMP(4,38);
				if (found=true) {
					actualResults.set(38, "Account number: "+accountNumbr+" is found in STDs for approval");
					System.out.println("Account number: "+accountNumbr+" is found in STDs for approval");
				}								
				Thread.sleep(1000);
				//Step 40.- After Account is selected, click on Validate button
				elementClick("ctl00_ContentZone_BtnValidate");
				Thread.sleep(2000);
				
				//Step 41.- Click on Customer Service and then click on Select Account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
				elementClick(pageSelected);
				Thread.sleep(1000);			
				pageSelectedErr(40);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 42.- Click on the Account
				elementClick(accountNumbr);
				Thread.sleep(1000);				
				
				//Step 43.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","MPSDTValidated"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPSDTValidated.jpeg");
				
				//Step 44.- Verify if the Vehicle STD/MP status
				STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (STDMPValidated.equals("SDT") || STDMPValidated.equals("MP")) {
					Thread.sleep(1000);
					return;
					
				}else {
					noValidated = true;
					return;
				}				
			
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void validateAfterDiscard() throws Exception{
			action = new Actions(driver);
			Thread.sleep(1000);
			try {
				
				//Step 30.- After Account is selected, type a comment in Rejected Reason field and then click on Reject button
				SendKeys("ctl00_ContentZone_txtRejectedReason","STD/MP for account: "+accountNumbr+" has been rejected");
				elementClick("ctl00_ContentZone_BtnReject");
				Thread.sleep(2000);
				
				//Step 31.- Click on Customer Service and then Track Account and then Rejecteds STDs Link
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Rejecteds SDTs";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(30);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 32.- Search for the account which Settlement (STD or MP) just rejected
				searchSDTMP(2,31);
				Thread.sleep(1000);

				//Step 33.- Click on Solve button
				elementClick("ctl00_ContentZone_BtnSolve");
				Thread.sleep(500);				
				
				//Step 34.- Click on Discard button 
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnDiscard");
				Thread.sleep(500);
				
				//Step 35.- Click on OK button
				elementClick("popup_ok");
				Thread.sleep(500);
				
				//Step 36.- Click on Close button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
				Thread.sleep(1000);				
				
				//Step 37.- Click on Customer Service and then click on Select Account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(36);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 38.- Search for the just discarded account for STD/MP
				SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 39.- Click on the Account
				elementClick(accountNumbr);
				Thread.sleep(1000);				
				
				//Step 40.- Click on Edit button
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				
				//Step 41.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(2000);
				
				//Step 42.- Select the just discarded vehicle and click on STD/MP button
				elementClick("ctl00_ContentZone_radio0");
				Thread.sleep(1000);				
				elementClick("ctl00_ContentZone_BtnSDTMP");
				Thread.sleep(1000);
				
				//Step 43.- Click on Register button; Step 44.- Upload or put the corresponding documents in each field
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnRegister");
				Thread.sleep(1000);
				
				//Step 45.- Click Apply button 
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_btnSDTDocsApply");
				Thread.sleep(1000);
				
				//Step 46.- Click on OK button
				elementClick("popup_ok");
				Thread.sleep(500);
				
				//Step 47.- Click on Close button
				elementClick("ctl00_ContentZone_ctrlDiscountInfo_Button_ClosePopup");
				Thread.sleep(1000);
				
				//Step 48.- Check the Vehicle SDT/MP status 
				STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (!STDMPValidated.equals("Pending")) {
					noValidatePending = true;
					return;					
				}
				
				//Step 49.- Click on back button and click on Save button
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");				
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				
				//Step 50.- Click on Customer Service and then Track Account and then SDT Approval
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Track account"))).build().perform();
				Thread.sleep(500);
				pageSelected ="SDT approval";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(49);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//S
				//Step 51.- Search for the account which Settlement (STD or MP) has been registered and select it
				searchSDTMP(4,50);
								
				Thread.sleep(1000);
				//Step 52.- After Account is selected, click on Validate button
				elementClick("ctl00_ContentZone_BtnValidate");
				Thread.sleep(2000);
				
				//Step 53.- Click on Customer Service and then click on Select Account
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(52);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				//Step 54.- Search for the just validated for STD/MP account
				SendKeys("ctl00_ContentZone_txt_accountId_box_data",accountNumbr);
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 55.- Click on the Account
				driver.findElement(By.linkText(accountNumbr)).click();
				Thread.sleep(1000);				
				
				//Step 56.- Click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","MPSDTValidated"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","MPSDTValidated.jpeg");

				//Step 57.- Verify if the Vehicle STD/MP status
				STDMPValidated = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[8]");
				if (STDMPValidated.equals("SDT")|| STDMPValidated.equals("MP")) {
					Thread.sleep(1000);
					return;
					
				}else {
					noValidated = true;
					return;
				}				
			
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void searchSDTMP(int row, int stepIn) throws Exception{
			String counterSDT = getText("ctl00_ContentZone_tablePager_LblCounter");
			WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			List <WebElement> countT = tableres.findElements(By.tagName("tr"));				
			int STDC = Integer.parseInt(counterSDT.substring(counterSDT.indexOf("of ")+3));
			int rowIncr = 2;
			if (STDC>0) {
				for (int i=1;i<=STDC;i++) {
					if (rowIncr>countT.size()) {
						elementClick("ctl00_ContentZone_tablePager_BtnNext");
						Thread.sleep(1500);
						tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
						countT = tableres.findElements(By.tagName("tr"));
						rowIncr = 2;	
						Thread.sleep(1000);
					}
					String accountSelect = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncr+"]/td["+row+"]");
					if (accountSelect.equals(accountNumbr)) {
						elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowIncr+"]/td[1]/input");
						found = true;
						i = STDC+1;					
					}
					if (i==STDC && !accountSelect.equals(accountNumbr)) {
						noApprovalDocumentFound(stepIn);
					}
					rowIncr = rowIncr +1;										
				}
			}else {
				noApprovalDocumentFound(stepIn);
			}
		}
		
		public static void noApprovalDocumentFound(int steps) throws Exception{
			actualResults.set(steps, "Account "+accountNumbr+" for "+flowSelect[selOp]+" is not found for Approval");
			executionResults.set(steps,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i= stepNotExecuted;i>steps;i--) {
				executionNumber.remove(i);
			}
			summaryBug = "Account "+accountNumbr+" for "+flowSelect[selOp]+" is not found for Approval";
			erroTextBug = "Account "+accountNumbr+" for "+flowSelect[selOp]+" is not found for Approval";
			componentBug = "HM";
			severityBug = 1;
			priority = 2;
			testFailed = true;
			bugAttachment = false;							
			driver.close();				
			testLink();		
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
			fail("Account "+accountNumbr+" for "+flowSelect[selOp]+" is not found for Approval");
		}

			
}