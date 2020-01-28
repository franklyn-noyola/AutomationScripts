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


public class BOHost_authorisedPersonFunctionalities{
		private static boolean accountClosed = false;
		private static boolean NumbPerS = false;
		private static int NumbPer;
		private static String personC;
		private static String fullname2;
		private static int opSel;
		private static String accountType;		
		public static String fullname;		
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
		public void authorisedPersonFunctionalitiesInit() throws Exception {
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(5,0,2,3);
			Thread.sleep(1000);
			accountModeTaj = "Edit";
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");;
			authorisedPersonFunctionalities();
			Thread.sleep(1000);
			if (accountClosed){
				actualResults.set(5, "Unable to "+optionSelect[opSel]+" in Account "+accountNumbr+" because it is closed");
				for (int i=6;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+" in Account "+accountNumbr+" because it is closed");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;		
			}
			if (NumbPerS){
				actualResults.set(7, "Unable to "+optionSelect[opSel]+ " in the Account "+accountNumbr+" because there is "+NumbPer+" person/s assigned");
				for (int i=6;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("Unable to "+optionSelect[opSel]+ " in the Account "+accountNumbr+" because there is "+NumbPer+" person/s assigned");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;	
			}
			Thread.sleep(1000);
			if (optionSelect[opSel].equals("Create")){
				actualResults.set(10, "Authorised person "+ personC+ " has been created in Account "+accountNumbr+ " correctly");
				System.out.println("Authorised person "+ personC+ " has been created in Account "+accountNumbr+ " correctly");
			}
			if (optionSelect[opSel].equals("Modify")){				
				actualResults.set(10, "Authorised person "+ fullname2+ " has been Modified to another autothorised person "+ personC+ " in Account "+accountNumbr+ " correctly");
				System.out.println("Authorised person "+ fullname2+ " has been Modified to another autothorised person "+ personC+ " in Account "+accountNumbr+ " correctly");
				
			}
			if (optionSelect[opSel].equals("Delete")){
				actualResults.set(10, "Authorised person "+ fullname2+ " has been deleted in Account "+accountNumbr+ " correctly");
				System.out.println("Authorised person "+ fullname2+ " has been deleted in Account "+accountNumbr+ " correctly");
			}
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));

		}
		public static void authorisedPersonFunctionalities() throws Exception{			
			action = new Actions(driver);	
			try{
				
				//Step 1.- 'Enter into Tajikistan Login Page 			
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\Tajikistan\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");			
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
				
				// Step 3.- Click on Customer Service and then click on Select Account link
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				Thread.sleep(1000);		
				pageSelected = "Select account";
				driver.findElement(By.linkText(pageSelected)).click();
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
				Thread.sleep(1000);
				/*	SendKeys("ctl00_ContentZone_txt_accountId_box_data","1040");
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
				Thread.sleep(500);
				driver.findElement(By.linkText("000001040")).click();*/
				
				//Step 5.- Click on any desired account
				selectAccount();				
				takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				accountType = getText("ctl00_ContentZone_ctrlAccountData_lbl_accountType");
				String NumberPer = getText("ctl00_ContentZone_lbl_authpers");
				NumbPer = Integer.parseInt(NumberPer);
				takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");
				
				//Step 6.- Click on Edit button
				if(driver.getPageSource().contains("CLOSED ACCOUNT")){
					accountClosed = true;
					return;
				}
				opSel = ranNumbr(0,2);
				
				//Steps from 7 to 11
				switch (optionSelect[opSel]){
					case "Create":					Thread.sleep(1000);														
													createAuthorisedPerson();
													Thread.sleep(1000);													
													break;
					case "Modify":					Thread.sleep(1000);
													modify_deleteAuthorisedPerson();
													Thread.sleep(1000);
													break;
					case "Delete":					Thread.sleep(1000);
													modify_deleteAuthorisedPerson();
													Thread.sleep(1000);
													break;
				}
				
				
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}	
		
		public static void createAuthorisedPerson() throws Exception{
			try {
				selOp = ranNumbr(0,9);
				actualResults.set(7, "Create button has been clicked and Auth. Person creation screen is displayed");
				personC = nameOp[selOp]+" "+lastNameOp[selOp];
				if (accountType.equals("Individual")){			
					fullname = driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_forname_box_data")).getAttribute("value") + " " + driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_surname_box_data")).getAttribute("value");			 								
					while(personC==fullname){
						selOp = ranNumbr(0,9);
						personC = nameOp[selOp]+" "+lastNameOp[selOp];
					}
				}
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_BtnAuthPers");
				takeScreenShot("E:\\Selenium\\","AuthorisedPerdonPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonPage.jpeg");
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_BtnCreate");
				SendKeys("ctl00_ContentZone_tbAuthPersTitle", sexSelcEn[selOp]);
				Thread.sleep(500);
				SendKeys("ctl00_ContentZone_tbForename", nameOp[selOp]);
				Thread.sleep(500);
				SendKeys("ctl00_ContentZone_tbSurname", lastNameOp[selOp]);
				Thread.sleep(500);
				SendKeys("ctl00_ContentZone_tbRelationship", "Work Colleague");
				Thread.sleep(500);
				actualResults.set(8, "All data has been filled correctly accordingly");
				takeScreenShot("E:\\Selenium\\","AuthorisedPerdonFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonFilledPage.jpeg");
				elementClick("ctl00_ContentZone_BtnSubmit");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","AuthorisedPerdonCreatedPage"+timet+".jpg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonCreatedPage.jpeg");
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1000);
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
				Thread.sleep(1000);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void modify_deleteAuthorisedPerson() throws Exception{
			try {
				Thread.sleep(1000);
				if (NumbPer==0){
					NumbPerS = true;
					return;
				}
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				selOp = ranNumbr(0,9);
				personC = nameOp[selOp]+" "+lastNameOp[selOp];
				if (accountType.equals("Individual")){
					fullname = driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_forname_box_data")).getAttribute("value") + " " + driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_surname_box_data")).getAttribute("value");
				}
				elementClick("ctl00_ContentZone_BtnAuthPers");
				Thread.sleep(1000);
				WebElement tableRe = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List <WebElement> tableResu = tableRe.findElements(By.tagName("tr"));
				int recordSel = ranNumbr(2,tableResu.size());
				elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+recordSel+"]/td[1]/input");
				fullname2 = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+recordSel+"]/td[4]")).getText()+" "+driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+recordSel+"]/td[5]")).getText();
				if (optionSelect[opSel].equals("Modify")){
					actualResults.set(7, "Modify button has been clicked, and Auth. Person modification screen is displayed");
					while(personC==fullname) {
						selOp = ranNumbr(0,9);
						personC = nameOp[selOp]+" "+lastNameOp[selOp];
					}
					while(personC==fullname2) {
						selOp = ranNumbr(0,9);
						personC = nameOp[selOp]+" "+lastNameOp[selOp];
					}
					elementClick("ctl00_ContentZone_BtnModify");
					Thread.sleep(1000);
					SendKeys("ctl00_ContentZone_tbAuthPersTitle", sexSelcEn[selOp]);
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_tbForename", nameOp[selOp]);
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_tbSurname", lastNameOp[selOp]);
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_tbRelationship", "Work Colleague");
					Thread.sleep(500);
					actualResults.set(8, "All data have been modified accordingly");
					takeScreenShot("E:\\Selenium\\","AuthorisedPerdonModFilledPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonModFilledPage.jpeg");
					elementClick("ctl00_ContentZone_BtnSubmit");
					Thread.sleep(500);			
				}
				if (optionSelect[opSel].equals("Delete")){
					actualResults.set(7, "Delete button has been clicked, and a confirmation deletion pop up is displayed");
					elementClick("ctl00_ContentZone_BtnDelete");
					if (isAlertPresent()){
						driver.switchTo().alert().accept();					
					}
					actualResults.set(8, "Accept button has been clicked in the popup");
				}
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				mandatoryFieldsValidation();
				Thread.sleep(1000);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
	}
		
}