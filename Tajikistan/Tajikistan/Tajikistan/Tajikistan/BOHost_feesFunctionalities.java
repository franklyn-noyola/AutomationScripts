package Tajikistan.Tajikistan;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationwithVehicle.*;

public class BOHost_feesFunctionalities{
			 private static boolean accountClosed = false;
			 private static String [] options = {"Create", "Modify", "Delete", "Creation by Type"};
			 private static int selOptions;
			 private static String [] recargoOptions = {"Account creation", "Account update", "Vehicle creation", "TAG missing", "Smart card missing"};
			 private static int recargoSel;
			 private static String recargoSelected;
			 private static String tipoSelected;
			 private static boolean noDeleted = false;
			 private static String value;
			 private static int NumbVeh;
			 private static boolean NoVeh;
			 private static boolean noTag=false;	
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
			public void feesFunctionalitiesInit() throws Exception {
				Thread.sleep(1000);
				selOptions = ranNumbr(0,3);
				if (selOptions==3) {
					configurationSection("Host","BOHost_feesFlow",testNameSelected);					
					testPlanReading(11,5,7,8);
				}else {
					configurationSection("Host",testNameSelected,testNameSelected);					
				}
				recargoSel = ranNumbr(0,4);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				feesFunctionalities();
				Thread.sleep(200);
				if (accountClosed){
					actualResults.set(5, "Unable to assign a fee into account: "+accountNumbr+" because it is closed or account is Exempt");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Unable to assign a fee into account: "+accountNumbr+" because it is closed or account is Exempt");
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;																				
				}				
				if (noVehAllowed==true) {
					actualResults.set(5, "Fee "+recargoSelected+" of Type "+tipoSelected+" with ammount "+value+", but fee was not applied by Tag missing to the account: "+accountNumbr+" due to: "+errorText);
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Fee "+recargoSelected+" of Type "+tipoSelected+" with ammount "+value+", but fee was not applied by Tag missing to the account: "+accountNumbr+" due to: "+errorText);
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;					
				}
				
				if (NoVeh==true) {
					actualResults.set(5, "Fee "+recargoSelected+" of Type "+tipoSelected+" with ammount "+value+", but fee was not applied by Tag/Smart card missing to the account: "+accountNumbr+" due to account has not vehicle assigned");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Fee "+recargoSelected+" of Type "+tipoSelected+" with ammount "+value+", but fee was not applied by Tag/Smart card missing to the account: "+accountNumbr+" due to account has not vehicle assigned");
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;								
				}
				
				if (noTag==true) {
					actualResults.set(5, "Fee "+recargoSelected+" of Type: "+tipoSelected+" with Amount: "+value+" has been created, but it is unable to set by Tag Missing due to Vehicle with plate: "+matriNu+" of Account: "+accountNumbr+" has not tag assigned to set the action Fee by Tag missing");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Fee "+recargoSelected+" of Type: "+tipoSelected+" with Amount: "+value+" has been created, but it is unable to set by Tag Missing due to Vehicle with plate: "+matriNu+" of Account: "+accountNumbr+" has not tag assigned to set the action Fee by Tag missing");
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;
								
				}
				
				if (noDeleted==true) {
					actualResults.set(5, "Unable to delete the selected fee due to: "+errorText);
					driver.close();
					testLink();
					System.out.println("Unable to delete the selected fee due to: "+errorText);
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;
								
				}
				if (options[selOptions].equals("Delete")) {
					actualResults.set(5, "Fee "+optionSelectedId+" has been deleted correctly");
					System.out.println("Fee "+optionSelectedId+" has been deleted correctly");
				}
				if (options[selOptions].equals("Modify")) {
					actualResults.set(5, "Fee: "+optionSelectedId+" has been modified/replaced for new fee: "+recargoSelected+" with new type and amount "+tipoSelected+", "+value+" correctly");
					System.out.println("Fee: "+optionSelectedId+" has been modified/replaced for new fee: "+recargoSelected+" with new type and amount "+tipoSelected+", "+value+" correctly");					
				}

				if (options[selOptions].equals("Create")) {
					actualResults.set(5, "Fee: "+recargoSelected+" has been created with type: "+tipoSelected+" with amount: "+value+" correctly");
					System.out.println("Fee: "+recargoSelected+" has been created with type: "+tipoSelected+" with amount: "+value+" correctly");
				}
				
				if (options[selOptions].equals("Creation by Type")) {
					if (tipoSelected.equals("Account creation")){
						actualResults.set(6, "Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly, and account "+accountNumbr+" has been created and the Fee has been applied correctly.");
						System.out.println("Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly, and account "+accountNumbr+" has been created and the Fee has been applied correctly.");
					}
					if (tipoSelected.equals("Account update")){
						actualResults.set(6, "Fee: "+recargoSelected+" has been created by Type:: "+tipoSelected+" and amount: "+value+" correctly and account "+accountNumbr+" has been modified and Fee has been applied correctly.");
						System.out.println("Fee: "+recargoSelected+" has been created by Type:: "+tipoSelected+" and amount: "+value+" correctly and account "+accountNumbr+" has been modified and Fee has been applied correctly.");
					}
					if (tipoSelected.equals("Vehicle creation")){
						actualResults.set(6, "Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly and a vehicle with plate No.: "+matriNu+" has been created and the Fee has been aplied to account No. "+accountNumbr);
						System.out.println("Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly and a vehicle with plate No.: "+matriNu+" has been created and the Fee has been aplied to account No. "+accountNumbr);
					}
					if (tipoSelected.equals("TAG missing")){
						actualResults.set(6, "Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly and a Tag No.: "+tagIdNmbr+" has been set to missed to vehicle with plate No.: "+matriNu+" in the account No. "+accountNumbr+" and the Fee has been applied to the account");
						System.out.println("Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly and a Tag No.: "+tagIdNmbr+" has been set to missed to vehicle with plate No.: "+matriNu+" in the account No. "+accountNumbr+" and the Fee has been applied to the account");
					}
					if (tipoSelected.equals("Smart card missing")){
						actualResults.set(6, "Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly and a Smart card No.: "+tagIdNmbr+" has been set to missed to vehicle with plate No.: "+matriNu+" in the account No. "+accountNumbr+" and the Fee has been applied to the account");
						System.out.println("Fee: "+recargoSelected+" has been created by Type: "+tipoSelected+" and amount: "+value+" correctly and a Smart card No.: "+tagIdNmbr+" has been set to missed to vehicle with plate No.: "+matriNu+" in the account No. "+accountNumbr+" and the Fee has been applied to the account");
					}
				}
				Thread.sleep(1000);
				driver.close();
				testLink();					
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
			}

			public static void feesFunctionalities() throws Exception {
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
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Step 3.- Click on System settings, then Account parameters and then click on Fees link
					action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
					Thread.sleep(1000);
					action.moveToElement(driver.findElement(By.linkText("Account parameters"))).build().perform();
					pageSelected = "Fees";
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
					
					//Step 4.- Click on any Button: Create (Create a new Fee), Modify (after selecting any existig Fee, in order to modify the Fee), Delete (after selecting any existing Fee to delete the fee) - BOHost_feesFunctionalities (Test Case)
					if (options[selOptions].equals("Modify") || options[selOptions].equals("Delete")) {
						itemSearchandSelect();
						if (options[selOptions].equals("Modify")) {
							elementClick("ctl00_ContentZone_BtnModify");
							actualResults.set(3, "Modify button has been clicked and Fee Edit page is displayed");
						}
						if (options[selOptions].equals("Delete")) {
							elementClick("ctl00_ContentZone_BtnDelete");
							actualResults.set(3, "Delete button has been clicked and deletion pop up confirmation is displayed");							
							Thread.sleep(1000);
							if (isAlertPresent()) {
								driver.switchTo().alert().accept();
							}
							Thread.sleep(4000);
							if (driver.getPageSource().contains("Cannot delete the item")) {
								errorText = getText("ctl00_LblError");
								noDeleted = true;
								Thread.sleep(1000);
								return;
							}
							return;
						}
					}
					Thread.sleep(3000);
					if (options[selOptions].equals("Create") || options[selOptions].equals("Creation by Type")) {
						
						//Step 4.- Click on Create button - BOHost_feesFlow (Test Case)
						elementClick("ctl00_ContentZone_BtnCreate");
					}
					takeScreenShot("E:\\Selenium\\","recargoPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","recargoPage.jpeg");
					if (options[selOptions].equals("Creation by Type")) {
						//BOHost_FeeFlow (Test Case);
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).selectByVisibleText(recargoOptions[recargoSel]);
						actualResults.set(4, "All mandatory fields have been filled and Fee Type selected was: "+recargoOptions[recargoSel]);
					}else {
						selectDropDown("ctl00_ContentZone_cmb_type_cmb_dropdown");
						//BOHost_FeeFlow (Test Case);
						actualResults.set(3, "Create button has been clicked and Fee Creation page is displayed");
					}
					
					Thread.sleep(100);
					value = String.valueOf(ranNumbr(100,10000));
					WebElement recargSel = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).getFirstSelectedOption();
					tipoSelected = recargSel.getText();
					recargoSelected = tipoSelected+"_"+ranNumbr(100,1000);
					SendKeys("ctl00_ContentZone_txt_name_box_data", recargoSelected);
					SendKeys("ctl00_ContentZone_txt_description_box_data", "Recargo para "+tipoSelected);
					if (!tipoSelected.equals("Account creation")) {
						selectDropDown("ctl00_ContentZone_cmb_applicationType_cmb_dropdown");
					}
					Thread.sleep(1000);
					action.click(driver.findElement(By.id("ctl00_ContentZone_money_amount_txt_formated"))).build().perform();
					action.sendKeys(value).build().perform();
					Thread.sleep(1000);					
					takeScreenShot("E:\\Selenium\\","filledData"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","filledData.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					if (options[selOptions].equals("Creation by Type")) {
						switch (tipoSelected) {
							case "Account creation":			createAccount();
																break;
							case "Account update":				otherActions();
																break;
							case "Vehicle creation":			otherActions();
																break;
							case "TAG missing":					otherActions();
																break;					
							case "Smart card missing":			otherActions();
																break;
							
						}
					}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
			
			public static void createAccount() throws Exception{
				Thread.sleep(2000);
				fieldCheck=false;
				accountModeTaj = "New";
				action = new Actions(driver);
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				action.moveToElement(driver.findElement(By.linkText("Create account"))).build().perform();
				if (ranNumbr(0,1)==0) {
					pageSelected = "Individual Account";
					elementClick("Individual");
					Thread.sleep(1000);
					pageSelectedErr(5);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.out.println(errorText);
						fail(errorText);
						System.out.println("Tested in Version of: "+ getVersion(applevelTested));
					}
					SendKeys(passPortid, passportLetter[ranNumbr(0,2)]+ranNumbr(1000000,99999999));		
					Thread.sleep(200);
					elementClick("ctl00_ContentZone_BtnCheckCI");
					Thread.sleep(1000);
					String errorPage = driver.getPageSource(); 
					if (errorPage.contains("The passport/tax value already exists in the system")){
						while (!errorPage.contains("The passport/tax value already exists in the system")) {
							SendKeys(passPortid, passportLetter[ranNumbr(0,2)]+ranNumbr(1000000,99999999));		
							Thread.sleep(200);
							elementClick("ctl00_ContentZone_BtnCheckCI");
							Thread.sleep(1000);
							errorPage = driver.getPageSource();
						}
					}
					elementClick("ctl00_ContentZone_BtnFees");
					Thread.sleep(100);
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(recargoSelected);
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(100);
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(100);
					individualAccount();
					actualResults.set(3, "Fees creation page is displayed");
				}else {
					action.clickAndHold(driver.findElement(By.linkText("Company"))).build().perform();
					driver.findElement(By.linkText("Prepayment")).click();
					Thread.sleep(1000);
					SendKeys(passPortid, String.valueOf(ranNumbr(100000000,999999999)));		
					Thread.sleep(200);
					elementClick("ctl00_ContentZone_BtnCheckCI");
					Thread.sleep(1000);
					String errorPage = driver.getPageSource(); 
					if (errorPage.contains("The passport/tax value already exists in the system")){
						while (!errorPage.contains("The passport/tax value already exists in the system")) {
							SendKeys(passPortid, passportLetter[ranNumbr(0,2)]+ranNumbr(1000000,99999999));		
							Thread.sleep(200);
							elementClick("ctl00_ContentZone_BtnCheckCI");
							Thread.sleep(1000);
							errorPage = driver.getPageSource();
						}
					}
					elementClick("ctl00_ContentZone_BtnFees");
					Thread.sleep(100);
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(recargoSelected);
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(100);
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(100);
					companyAccount();
					
					//BOHost_FeeFlow (Test Case)
					actualResults.set(3, "Fees creation page is displayed");
				}				
				Thread.sleep(4000);
				
					//BOHost_FeeFlow (Test Case)
					actualResults.set(5, "Fee is created and assigned to Account: "+accountNumbr+" based on Fee selected"+recargoOptions[recargoSel]);
			}
			public static void otherActions() throws Exception{
				accountModeTaj="Modify";
				Thread.sleep(2000);
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
				pageSelected= "Select account";
				elementClick("Select account");
				Thread.sleep(1000);
				pageSelectedErr(5);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
				int accountSelected =ranNumbr(0,1);
				if (accountSelected == 1) {
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_mode_cmb_dropdown"))).selectByVisibleText("Individual account");
				}else {
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_mode_cmb_dropdown"))).selectByVisibleText("Company account");					
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Оплату компании");
				}
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				selectAccount();
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_accountType");
				if(driver.getPageSource().contains("CLOSED ACCOUNT") || accountType.equals("EXEMPT ACCOUNT")){
					accountClosed = true;
					return;
				}
				String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
				NumbVeh = Integer.parseInt(numberVehicles);
				if (tipoSelected.equals("TAG missing") || tipoSelected.equals("Smart card missing")) {
					if (NumbVeh==0){
						NoVeh = true;
						return;
					}
				}				
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				elementClick("ctl00_ContentZone_BtnFees");
				Thread.sleep(100);
				new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(recargoSelected);
				elementClick("ctl00_ContentZone_btn_add");
				Thread.sleep(100);
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				List<WebElement>RedClassCount = driver.findElements(By.xpath("//*[@class='generalBoxRed']"));
				if (RedClassCount.size()>0) {						
					for (int i=1;i<=RedClassCount.size();i++) {
						emptyField = driver.findElement(By.xpath("//*[@class='generalBoxRed']")).getAttribute("id");					
						Thread.sleep(100);
						mandatoryFieldsValidation();
						Thread.sleep(100);			
						
					}				
				}			
				actualResults.set(5, "Fee is created and assigned to Account: "+accountNumbr+" based on Fee selected"+recargoOptions[recargoSel]);			
				Thread.sleep(1000);
				if (tipoSelected.equals("Account update")) {					
					accountModeTaj = "Edit";
					String nextPage = getText("ctl00_SectionZone_LblTitle");
					if (nextPage.contains("Payment details")) {
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						paymentfromCustomerEng();
					}else{ 						
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
						fieldCheck=false;
						if (accountSelected == 1) {							
							individualAccount();
						}else {							
							companyAccount();					
						}
					}
					actualResults.set(3, "Fees creation page is displayed");
					
				}
				if (tipoSelected.equals("Vehicle creation")) {						
					Thread.sleep(1000);					
					optionVehicle = "CreateVehicle";
					vehicleCreation();					
				}
				if (tipoSelected.equals("TAG missing") || tipoSelected.equals("Smart card missing")) {					
					int vehCheck = ranNumbr(0,NumbVeh-1);
					if (vehCheck<0) {
						vehCheck=0;
					}
					int selVeh = vehCheck+2;
					Thread.sleep(3000);
					WebElement paymeansS = new Select(driver.findElement(By.id("ctl00_ContentZone_type_payment_cmb_dropdown"))).getFirstSelectedOption();
					String paymeanSText = paymeansS.getText();
					if (paymeanSText.equals("TAG")){
						elementClick("ctl00_ContentZone_BtnTags");
					}else {
						elementClick("ctl00_ContentZone_BtnSmarts");
					}
					
					Thread.sleep(1000);					
					matriNu=getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[2]");
					tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[6]");
					if (tagIdNmbr.equals("")) {
						noTag = true;
						return;
					}
					elementClick("ctl00_ContentZone_chk_"+vehCheck);
					elementClick("ctl00_ContentZone_btn_token_stolen");
					int stat = ranNumbr(0,1);
					elementClick("ctl00_ContentZone_radio_stolen_"+stat);
					elementClick("ctl00_ContentZone_btn_stolen");						
					Thread.sleep(1000);
					String nextPage = getText("ctl00_SectionZone_LblTitle");
					if (nextPage.contains("Payment details")) {
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						paymentfromCustomerEng();
					}
				}				
				Thread.sleep(4000);							
			}
			
}		