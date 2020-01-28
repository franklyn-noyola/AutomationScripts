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


public class BOHost_tagFuncionalities{
		private static boolean accountClosed = false;
		private static boolean NumbVehC = false;
		private static int NumbVeh;
		private static int opSel;
		private static boolean noTag=false;
		private static boolean sameTag=false;
		public static int rowSel;
		public static String matriNum;
		private static String [] optionSelect = {"Assign Tag/Card","Return Tag/Card","Suspend Tag/Card", "Missing Tag/Card"};
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
		public void tagFuncionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);
			testPlanReading(18,0,2,3);
			Thread.sleep(1000);
			accountMode = "Edit";
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			tagFuncionalities();;
			Thread.sleep(1000);
			if (accountClosed){
				actualResults.set(4, "Unable to "+optionSelect[opSel]+" to a vehicle in Account "+accountNumbr+" because it is closed");
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+" to a vehicle in Account "+accountNumbr+" because it is closed");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;	
								
			}
			if (NumbVehC){
				actualResults.set(5, "Unable to "+optionSelect[opSel]+ " to a vehicle in Account "+accountNumbr+" because there is "+NumbVeh+" vehicle/s assigned");
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+ " to a vehicle in Account "+accountNumbr+" because there is "+NumbVeh+" vehicle/s assigned");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}
			
			if (sameTag){
				actualResults.set(7, "Unable to "+optionSelect[opSel]+ " to a vehicle in Account "+accountNumbr+" because there is "+NumbVeh+" vehicle/s assigned");
				driver.close();
				testLink();
				System.out.println("Unable to assign Tag to Vehicle "+matriNum+" to the account "+accountNumbr+" due to:"+errorMessage);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}
			
			if (noTag){
				actualResults.set(7, "Unable to "+optionSelect[opSel]+ " to a with plate: "+matriNum+"  in Account "+accountNumbr+" because it has not Tag");
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+ " to a with plate: "+matriNum+"  in Account "+accountNumbr+" because it has not Tag");
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
				return;
			}
			
			Thread.sleep(1000);
			if (optionSelect[opSel].equals("Assign Tag/Card")){
				actualResults.set(7, "A tag/card No. "+tagIdNmbr+" has been assigned to Vehicle with plate: " +matriNum+" in the Account "+accountNumbr);
				System.out.println("A tag/card No. "+tagIdNmbr+" has been assigned to Vehicle with plate: " +matriNum+" in the Account "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Return Tag/Card")){
				actualResults.set(7, "Tag/Card No.: " +tagIdNmbr+ " has been returned from Vehicle with Plate: "+matriNum+" in Account "+accountNumbr);
				System.out.println("Tag/Card No.: " +tagIdNmbr+ " has been returned from Vehicle with Plate: "+matriNum+" in Account "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Suspend Tag/Card")){
				actualResults.set(7, "Tag/Card No.: " +tagIdNmbr+ " has been suspended from Vehicle with Plate: "+matriNum+" in Account "+accountNumbr);
				System.out.println("Tag/Card No.: " +tagIdNmbr+ " has been suspended from Vehicle with Plate: "+matriNum+" in Account "+accountNumbr);
			}
			if (optionSelect[opSel].equals("Missing Tag/Card")){
				actualResults.set(7, "Tag/Card No.: " +tagIdNmbr+ " has been missed from Vehicle with Plate: "+matriNum+" in Account "+accountNumbr);
				System.out.println("Tag/Card No.: " +tagIdNmbr+ " has been missed from Vehicle with Plate: "+matriNum+" in Account "+accountNumbr);
			}
			Thread.sleep(1000);
			testLink();
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
		}
		
		public static void tagFuncionalities() throws Exception{
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
				
				//Step 4.- Click on Search Button
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(1000);
				
				//Step 5.- Click on any desired account
				selectAccount();
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");
				
				
				if(driver.getPageSource().contains("CLOSED ACCOUNT")){
					accountClosed = true;
					return;
				}
				String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
				Thread.sleep(1000);
				NumbVeh = Integer.parseInt(numberVehicles);
				if (NumbVeh==0){
					NumbVehC = true;
					return;
				}
				WebElement paymentType = new Select (driver.findElement(By.id("ctl00_ContentZone_type_payment_cmb_dropdown"))).getFirstSelectedOption();
				String paymentyp = paymentType.getText();
				
				//Step 6.- Check if selected account has vehicle assigned and If Selected account is Individual and Payment means is TAG, click on Electronic toll button, if payment means is smart card, click on Smart card button, if account selected is Company - Prepayment and Payment Means is TAG, click on Electronic toll button, if payment means is smart card, click on Smart card button, if account selected is Company - Exempt, click on Smart cards button
				if (paymentyp.equals("Smart Card")){
					elementClick("ctl00_ContentZone_BtnSmarts");
				}else{
					elementClick("ctl00_ContentZone_BtnTags");
				}
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");		
				WebElement tableR = driver.findElement(By.id("ctl00_ContentZone_m_table_members"));
				List<WebElement>TableResult = tableR.findElements(By.tagName("tr"));
				rowSel = ranNumbr(2,TableResult.size());
				String vehSelected = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+rowSel+"]/td[1]/input")).getAttribute("id");
				
				//Step 7.- Select any vehicle found in the table
				elementClick(vehSelected);
				opSel = ranNumbr(0,3);
				
				//Step 8.- Click on any desired button and make the proper action: Assignment, Return, Suspension or Missing
				switch (optionSelect[opSel]){
					case "Assign Tag/Card":		Thread.sleep(1000);												
												tagfuncionality();
												Thread.sleep(1000);													
												break;
					case "Return Tag/Card":		Thread.sleep(1000);
												tagfuncionality();
												Thread.sleep(1000);
												break;
					case "Suspend Tag/Card":	Thread.sleep(1000);
												tagfuncionality();
												Thread.sleep(1000);
												break;
					case "Missing Tag/Card":	Thread.sleep(1000);
												tagfuncionality();
												Thread.sleep(1000);
												break;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void tagfuncionality() throws Exception{		
			matriNum = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+rowSel+"]/td[2]");
			tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+rowSel+"]/td[6]");
			Thread.sleep(500);
			if (optionSelect[opSel].equals("Assign Tag/Card")){
				elementClick("ctl00_ContentZone_btn_token_assignment");
				Thread.sleep(500);
				SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",ranNumbr(1,99999)+"");
				Thread.sleep(500);
				elementClick("ctl00_ContentZone_btn_init_tag");
				Thread.sleep(500);
			}
			if (optionSelect[opSel].equals("Return Tag/Card")){
				elementClick("ctl00_ContentZone_btn_token_return");
				Thread.sleep(1000);
				int returnS = ranNumbr(0,2);
				if (returnS==0){
					elementClick("ctl00_ContentZone_radio_status_0");
				}
				if (returnS==1){
					elementClick("ctl00_ContentZone_radio_status_1");
				}
				if (returnS==2){
					elementClick("ctl00_ContentZone_radio_status_2");
				}
				Thread.sleep(500);
				elementClick("ctl00_ContentZone_btn_apply_return");
				Thread.sleep(1000);				
			}
			if (optionSelect[opSel].equals("Suspend Tag/Card")){
				elementClick("ctl00_ContentZone_btn_token_suspension");
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_btn_susp_not");
				Thread.sleep(1000);				
			}
			if (optionSelect[opSel].equals("Missing Tag/Card")){
				elementClick("ctl00_ContentZone_btn_token_stolen");
				Thread.sleep(1000);
				int returnS = ranNumbr(0,1);
				if (returnS==0){
					elementClick("ctl00_ContentZone_radio_stolen_0");
				}
				if (returnS==1){
					elementClick("ctl00_ContentZone_radio_stolen_1");
				}
				if (ranNumbr(0,1)==0){
					elementClick("ctl00_ContentZone_chk_lostbycustomer");
				}
				
				Thread.sleep(500);
				elementClick("ctl00_ContentZone_btn_stolen");
				Thread.sleep(1000);				
			}
			
			confirmationMessage = getText("ctl00_ContentZone_lbl_operation");
			confirmationMessage2 = getText("ctl00_ContentZone_lbl_information");
			if (confirmationMessage.contains("Error: This token is already assigned") || confirmationMessage.contains("There was an error saving in the database")){
				errorMessage = getText("ctl00_ContentZone_lbl_operation");
				sameTag = true;
				takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
				return;
			}
			if (confirmationMessage2.contains("has already an assigned token") || confirmationMessage.contains("There was an error saving in the database")){
				errorMessage = getText("ctl00_ContentZone_lbl_information");
				sameTag = true;
				takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
				return;
			}
			if (confirmationMessage.contains("Please select one or more tokens") || confirmationMessage.contains("There was an error saving in the database") || confirmationMessage.contains("At least one vehicle/tag must be selected")){
				errorMessage = getText("ctl00_ContentZone_lbl_operation");
				noTag= true;
				takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
				return;
			}
			if (sameTag!=true){
				tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+rowSel+"]/td[6]");
			}
			takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
			Thread.sleep(1000);
		
		}

}