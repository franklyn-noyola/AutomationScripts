package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static Tajikistan.Tajikistan.BOHost_accountCreationwithVehicle.*;


public class BOHost_vehicleFunctionalities{
		private static boolean accountClosed = false;
		private static boolean NumbVehC = false;
		private static int NumbVeh;
		private static int opSel;
		private static int documentSel;
		private static boolean nodeleted=false;
		public static String fileSelected;
		public static String matriNum;
		private static String [] documentOptions = {"Create","Delete"};
		private static String [] optionSelect = {"Create Vehicle","Modify Vehicle","Delete Vehicle","Create/Delete Document"};
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
		public void vehicleFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(19,0,2,3);			
			Thread.sleep(1000);
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			vehicleFunctionalities();;
			Thread.sleep(1000);
			accountModeTaj = "Edit";
			if (vehicleErr) {
				int step;
				if (optionSelect[opSel].equals("Create Vehicle")){
					step= 9;
				}else {
					step = 11;
				}
				actualResults.set(step, "Unable to "+optionSelect[opSel]+" with Plate No. "+matriNu+" in Account: "+accountNumbr+" due to: "+errorText);
				for (int i=step+1;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+" with Plate No. "+matriNu+" in Account: "+accountNumbr+" due to: "+errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;				
			}
			
			if (accountClosed){
				actualResults.set(4, "Unable to "+optionSelect[opSel]+" in Account "+accountNumbr+" because it is closed");
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+" in Account "+accountNumbr+" because it is closed");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;	
			}
			if (NumbVehC){
				actualResults.set(7, "Unable to "+optionSelect[opSel]+ " in the Account "+accountNumbr+" because there is "+NumbVeh+" vehicle/s assigned");
				for (int i=8;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+ " in the Account "+accountNumbr+" because there is "+NumbVeh+" vehicle/s assigned");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;								
			}
			if (nodeleted){
				actualResults.set(12, "Unable to "+optionSelect[opSel]+ " with plate: "+matriNum+"  in Account "+accountNumbr+" because to: "+errorMessage);
				for (int i=17;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+ " with plate: "+matriNum+"  in Account "+accountNumbr+" because to: "+errorMessage);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;			
			}
			if(NoDocumentToSelect) {
				actualResults.set(16, "Unable to delete a Document from Vehicle with plate: "+matriNum+"  in Account "+accountNumbr+" because to there is not Document created");
				for (int i=18;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to delete a Document from Vehicle with plate: "+matriNum+"  in Account "+accountNumbr+" because to there is not Document created");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;				
			}
			Thread.sleep(1000);
			if (optionSelect[opSel].equals("Create Vehicle")){
				actualResults.set(18, "A Vehicle with plate: " +matriNu+" has been created correctly in the Account "+accountNumbr);
				System.out.println("A Vehicle with plate: " +matriNu+" has been created correctly in the Account "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Modify Vehicle")){
				actualResults.set(18, "Vehicle with plate: " +matriNum+" has been Modified correctly and has a new plate "+matriNu+" in the Account "+accountNumbr);
				System.out.println("Vehicle with plate: " +matriNum+" has been Modified correctly and has a new plate "+matriNu+" in the Account "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Delete Vehicle")){
				actualResults.set(18, "Vehicle with plate: "+matriNum+ " has been deleted from Account: "+accountNumbr+" correclty");
				System.out.println("Vehicle with plate: "+matriNum+ " has been deleted from Account: "+accountNumbr+" correclty");
			}
			if (optionSelect[opSel].equals("Create/Delete Document")){
				if (documentOptions[documentSel].equals("Create")) {
					actualResults.set(18, "Document "+fileSelected+ " has been created to Vehicle with plate: "+matriNum+ " correclty in account "+accountNumbr);
					System.out.println("Document "+fileSelected+ " has been created to Vehicle with plate: "+matriNum+ " correclty in account "+accountNumbr);	
				}
				if (documentOptions[documentSel].equals("Delete")) {
					actualResults.set(18, "Document "+fileSelected+ " has been deleted from Vehicle with plate: "+matriNum+ " correclty in account "+accountNumbr);
					System.out.println("Document "+fileSelected+ " has been deleted from Vehicle with plate: "+matriNum+ " correclty in account "+accountNumbr);	
				}				
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
		}
		
		public static void vehicleFunctionalities() throws Exception{
			action = new Actions(driver);	
			opSel = ranNumbr(0,3);
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
				
				//Step 3.- Click on Customer service and the Select account Link
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Select account";
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
				Thread.sleep(1000);
				
				//Step 4.- Click on Search Button
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				
				//Step 5.- Click on any desired account
				selectAccount();
				Thread.sleep(1000);				
				takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");				
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");
				if(driver.getPageSource().contains("CLOSED ACCOUNT")){
					accountClosed = true;
					return;
				}
				
				switch (optionSelect[opSel]){
					case "Create Vehicle":			Thread.sleep(1000);
													optionVehicle = "CreateVehicle";
													//Step from 6 to 10 and then 19
													actualResults.set(7, "Create button has been clicked and Vehicle Creation page is displayed");
													vehicleCreation();
													Thread.sleep(1000);							
													for (int i=10;i<18;i++) {
														actualResults.set(i, "N/A");
														executionResults.set(i, "");
														
													}
													break;
					case "Modify Vehicle":			Thread.sleep(1000);
													modify_deleteVehicle();
													Thread.sleep(1000);
													break;
					case "Delete Vehicle":			Thread.sleep(1000);
													modify_deleteVehicle();
													Thread.sleep(1000);
													break;
													
					case "Create/Delete Document":	Thread.sleep(1000);
													vehicleDocument();
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
		public static void modify_deleteVehicle() throws Exception{			
			try {
				Thread.sleep(1000);
				optionVehicle = "ModifyVehicle";
				String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
				Thread.sleep(1000);				
				NumbVeh = Integer.parseInt(numberVehicles);
				if (NumbVeh==0){
					NumbVehC = true;
					return;
				}			
				
				//Step 6.- Click on Edit button
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);			
				
				//Step 7.- In Account edit mode, click on Vehicles button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
				WebElement tableR = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List<WebElement>tableResults = tableR.findElements(By.tagName("tr"));
				int selrow = ranNumbr(2,tableResults.size());			
				String optionS = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selrow+"]/td[1]/input")).getAttribute("id");
				matriNum = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selrow+"]/td[3]");						
				elementClick(optionS);
				
				//Step 8.- Click on desired button: Create (to create a new vehicle - Execute Cases from 9 to 10); Modified (first select a Vehicle to modify a Vehicle data - Execute Step from 11 to 12); Delete (first select a Vehicle, to delete a selected vehicle - execute step 13); Documents (first select a vehicle, to create (upload) or delete Documents - execute steps from 14 to 18)
				if (optionSelect[opSel].equals("Modify Vehicle")){
					//Step 11.- if a Vehicle has been selected and Modify button has been clicked, in Vehicle Modify page, modify all the fields accordingly
					elementClick("ctl00_ContentZone_BtnModify");
					actualResults.set(7, "Modify button has been clicked and Vehicle Edit page is displayed");
					for (int i=8;i<10;i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					for (int i=12;i<18;i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					//From step 11 to 12
					vehicleCreation();				
					return;
				}else{
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_BtnDelete");
					actualResults.set(7, "Delete button has been clicked and Deletion popup is displayed");
					for (int i=8;i<12;i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					for (int i=13;i<18;i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					Thread.sleep(1000);
					
					//Step 13.- if a Vehicle has been selected and Delete button has been clicked, in the Pop Up, click on Accept button
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);
					if (driver.getPageSource().contains("A vehicle with assigned TAG cannot be deleted")){
						errorMessage = getText("ctl00_LblError");
						nodeleted=true;
						return;
					}
					
					//Step 19.- Click on Back button and then Click on Save button
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(2000);
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(1000);
				}
			}catch(Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		
		}
		
	public static void vehicleDocument() throws Exception{
		try {
			Thread.sleep(1000);
			documentSel = ranNumbr(0,1);
			if (documentSel<0) {
				documentSel=0;
			}
			String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
			Thread.sleep(1000);
			NumbVeh = Integer.parseInt(numberVehicles);
			if (NumbVeh==0){
				NumbVehC = true;
				return;
			}
			
			//Step 6.- Click on Edit button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(1000);			
			
			//Step 7.- In Account edit mode, click on Vehicles button
			elementClick("ctl00_ContentZone_BtnVehicles");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
			WebElement tableR = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			List<WebElement>tableResults = tableR.findElements(By.tagName("tr"));
			int selrow = ranNumbr(2,tableResults.size());			
			String optionS = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selrow+"]/td[1]/input")).getAttribute("id");
			matriNum = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selrow+"]/td[3]");						
			elementClick(optionS);
			Thread.sleep(500);
			
			//Step 8.- Click on desired button: Create (to create a new vehicle - Execute Cases from 9 to 10); Modified (first select a Vehicle to modify a Vehicle data - Execute Step from 11 to 12); Delete (first select a Vehicle, to delete a selected vehicle - execute step 13); Documents (first select a vehicle, to create (upload) or delete Documents - execute steps from 14 to 18)
			elementClick("ctl00_ContentZone_BtnDocuments");
			actualResults.set(7, "Document button has been clicked and Document main page is displayed");
			for (int i=8;i<13;i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","vehicleDocumentsPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDocumentsPage.jpeg");
			if (documentOptions[documentSel].equals("Delete")) {
				actualResults.set(13, "Delete button has been clicked and Document deletion popup is displayed");
				for (int i=14;i<16;i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				Thread.sleep(1000);
				searchandSelect();
				if(NoDocumentToSelect) {
					return;				
				}
				fileSelected=getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[2]");
				
				//Step 17.- If Document is selected and Delete button has been clicked, in the confirmation pop up, click on Accept button
				elementClick("ctl00_ContentZone_BtnDelete");
				Thread.sleep(500);
				if (isAlertPresent()){
					driver.switchTo().alert().accept();
				}
				Thread.sleep(2000);
			}
			
			if (documentOptions[documentSel].equals("Create")) {				
				elementClick("ctl00_ContentZone_BtnCreate");
				actualResults.set(13, "Create button has been clicked and Document creation upload page is displayed");				
				actualResults.set(16, "N/A");
				executionResults.set(16, "");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","vehicleAddDocumentsPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleAddDocumentsPage.jpeg");
				Thread.sleep(1000);
				
				//Step 15.- if Create button has been clicked, in Document creation page, select a File to Upload by clicking Select file button and then type description in the Description field
				fileSelected="qa.jpg";				
				String documentRoute = "C:\\archivosPruebas\\"+fileSelected;
				driver.findElement(By.name("ctl00$ContentZone$FileUploadControl")).sendKeys(documentRoute);
				Thread.sleep(1000);			
				SendKeys("ctl00_ContentZone_txt_description_box_data","New Document created");
				takeScreenShot("E:\\Selenium\\","DocumentAdded"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DocumentAdded.jpeg");
				
				//Step 16.- Click on Submit button
				elementClick("ctl00_ContentZone_BtnSubmit");
				Thread.sleep(2000);
			}
			
				//Step 18.- Click on Back button
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1000);
				
				//Step 19.- Click on Back button and then Click on Save button
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				return;
			
		
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	
}