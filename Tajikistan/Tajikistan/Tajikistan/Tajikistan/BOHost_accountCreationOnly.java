package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import java.util.Calendar;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class BOHost_accountCreationOnly{
	private String testNameSelected = this.getClass().getSimpleName();
	private static Calendar dateYear = Calendar.getInstance();
	private static int currentYear = dateYear.get(Calendar.YEAR);
	
	
	
	@Before
	public void setup() throws Exception{
			setUp();
	}
	
	@After
	public void teardown() throws Exception{
			tearDown();
	}
	
	
	@Test
	public void accountCreationOnlyInit() throws Exception{						
		configurationSection("Host",testNameSelected,testNameSelected);
		testPlanReading(0,0,2,3);
		accountModeTaj = "New";
		fieldCheck = true;
		Thread.sleep(1000);	
		accountCreationOnly();		
		if (passTax){
			actualResults.set(4, "Unable to create an Account due to: "+ errorMessage);
			for (int i=5;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Unable to create an Account due to: "+ errorMessage);
			System.out.println("It has been tested in: "+ getVersion(applevelTested));												
			return;				
		}
		if (duplicatePass){
			actualResults.set(4, "Unable to create an Account due to: "+ errorMessage);
			for (int i=5;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Unable to create an Account due to: "+ errorMessage);
			System.out.println("It has been tested in: "+ getVersion(applevelTested));												
			return;	
		}
		Thread.sleep(1000);
		actualResults.set(6, accountSelected+" account with account No. "+accountNumbr+" created correctly");
		driver.close();
		testLink();	
		System.out.println(accountSelected+" account with account No. "+accountNumbr+" created correctly");
		System.out.println("It has been tested in: "+ getVersion(applevelTested));
		
	}
	
	public static void accountCreationOnly() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
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
			
			//Step 3.-  Click on Customer Service and then click on Create account link 
			Thread.sleep(2000);			
			action.moveToElement(driver.findElement(By.linkText("Customer service"))).build().perform();
			Thread.sleep(1000);
			action.moveToElement(driver.findElement(By.linkText("Create account"))).build().perform();
			Thread.sleep(1000);
			
			int selaccount = ranNumbr(0,1);
			
			//Step 4.- Select one of the links (either individual or Company)
			if (selaccount == 0){
				pageSelected = "Create account: Individual";
				elementClick("Individual");
				accountSelected = "Individual";
				Thread.sleep(1000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
			}
				
			individualAccount();				
			}else{
				action.clickAndHold(driver.findElement(By.linkText("Company"))).build().perform();				
				companylinkselected = companyLink[ranNumbr(0,1)];
				pageSelected = "Create account: Company "+companylinkselected;
				elementClick(companylinkselected);	
				Thread.sleep(500);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.out.println(errorText);
					fail(errorText);
					System.out.println("Tested in Version of: "+ getVersion(applevelTested));
				}
			companyAccount();
			}
			Thread.sleep(3000);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	
	public static void individualAccount() throws Exception{	
		try {
			Thread.sleep(2000);	
			if (accountOnly==true){
				actualResults.set(3, "Individual create account page is displayed");
			}		
			takeScreenShot("E:\\Selenium\\","individualAccountPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","individualAccountPage.jpeg");
			accountNumbr = getText("ctl00_SectionZone_LblTitle");
			accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
		
			//Step 5.- Type either Tax or Passport (depending on option selected to create an account) and click on Check Passport/Tax)
			if (fieldCheck==true) {
				SendKeys(passPortid, passportLetter[ranNumbr(0,2)]+ranNumbr(1000000,99999999));
				Thread.sleep(200);
				elementClick("ctl00_ContentZone_BtnCheckCI");
			}
			Thread.sleep(1000);
			if (driver.getPageSource().contains("The passport/tax  value already exists in the system")){
				errorText = getText("ctl00_LblError");
				if (settlementFunc==true) {
					while (driver.getPageSource().contains("The passport/tax  value already exists in the system")){
						SendKeys(passPortid, passportLetter[ranNumbr(0,2)]+ranNumbr(1000000,99999999));
						Thread.sleep(200);
						elementClick("ctl00_ContentZone_BtnCheckCI");
					}
				}else {
					passTax = true;
					return;	
				}
				
			}
		
			//Step 6.- Fill all the data accordingly (mandatory or not)
			SendKeys(taxId, String.valueOf(ranNumbr(100000000,999999999)));
			Thread.sleep(200);
			selOp = ranNumbr(0,9);
			new Select(driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelcEn[selOp]);
			Thread.sleep(200);
			SendKeys(surnamef,lastNameOp[selOp]);
			Thread.sleep(200);
			SendKeys(namef,nameOp[selOp]);
			Thread.sleep(200);
			SendKeys("ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data",lastNameOp2[selOp]);
			Thread.sleep(200);
			SendKeys(addressf,addressTec[selOp]);
			Thread.sleep(200);		
			Thread.sleep(1000);
			if (settlementFunc==false){			
				selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_settlements_cmb_dropdown");				
			}
			if (settlementFunc==true){
				if  (driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_chk_settlements")).isSelected()) {
					elementClick("ctl00_ContentZone_ctrlAccountData_chk_settlements");
					elementClick("ctl00_ContentZone_ctrlAccountData_chk_settlements");
				}else {
					elementClick("ctl00_ContentZone_ctrlAccountData_chk_settlements");	
				}
				
				new Select(driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_cmb_settlements_cmb_dropdown"))).selectByVisibleText(SettlementName);
			}
			Thread.sleep(300);
			SendKeys(countryf, "España");
			Thread.sleep(200);
			SendKeys(cpf, cpAdress[selOp]);
			Thread.sleep(200);
			SendKeys(emailf, nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
			Thread.sleep(200);
			SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
			Thread.sleep(200);
			SendKeys(workPhone,workPhone1[selOp]);
			Thread.sleep(200);
			SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
			Thread.sleep(200);
			SendKeys(faxPhone,workPhone1[selOp]);
			Thread.sleep(200);
			if (ranNumbr(0,1)==0){
				elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");
				Thread.sleep(200);
				SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date", dateSel(currentYear+1,currentYear+15));
				Thread.sleep(200);
				SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data",String.valueOf(ranNumbr(1,15)));
			}
			Thread.sleep(200);
			selectDropDownV("ctl00_ContentZone_type_payment_cmb_dropdown");
			Thread.sleep(1000);
			if (vDiscountSel==true || discountTable==true) {
				if (vDiscountSel==true && discountTable==false ) {	
					elementClick("ctl00_ContentZone_discount_strategy_1");
					Thread.sleep(500);
					new Select(driver.findElement(By.id("ctl00_ContentZone_Volu_discoubtaList_cmb_dropdown"))).selectByVisibleText(vDiscountName);
				}
				if (discountTable==true && vDiscountSel==false) {
					elementClick("ctl00_ContentZone_discount_strategy_2");
					Thread.sleep(500);
					new Select(driver.findElement(By.id("ctl00_ContentZone_perce_discoubtaList_cmb_dropdown"))).selectByVisibleText(dTablesName);
				}
			}else {
				int radbutton = ranNumbr(0,2);
				if (radbutton == 0){
					elementClick("ctl00_ContentZone_discount_strategy_0");
				}
				if (radbutton == 1){
					elementClick("ctl00_ContentZone_discount_strategy_1");
					Thread.sleep(500);
					selectDropDown("ctl00_ContentZone_Volu_discoubtaList_cmb_dropdown");
			}
				if (radbutton == 2){
					elementClick("ctl00_ContentZone_discount_strategy_2");
					Thread.sleep(500);
					selectDropDown("ctl00_ContentZone_perce_discoubtaList_cmb_dropdown");
				}
			}
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","individualAccountFilledPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","individualAccountFilledage.jpeg");
			
			//Step 7.- Click on Save Button
			if (accountModeTaj.equals("New")){
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save button
			}else {
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			}
			
			Thread.sleep(3000);
			takeScreenShot("E:\\Selenium\\","paymentDetailsPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentDetailsPage.jpeg");
			String nextPageTitle = getText("ctl00_SectionZone_LblTitle");
			if (nextPageTitle.contains("Payment details")) {
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(3000);
				paymentfromCustomerEng();
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	
	public static void companyAccount() throws Exception{
		try {
			Thread.sleep(2000);		
			actualResults.set(3, "Company create account page is displayed");
			takeScreenShot("E:\\Selenium\\","CompanyAccountPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CompanyAccountPage.jpeg");
			accountNumbr = getText("ctl00_SectionZone_LblTitle");
			accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
			
			
			//Step 5.- Type either Tax or Passport (depending on option selected to create an account) and click on Check Passport/Tax)
			if (fieldCheck==true) {
				SendKeys(passPortid, String.valueOf(ranNumbr(100000000,999999999)));
				Thread.sleep(200);
				elementClick("ctl00_ContentZone_BtnCheckCI");
			}
			
			Thread.sleep(1000);
			if (driver.getPageSource().contains("The passport/tax  value already exists in the system")){
				errorText = getText("ctl00_LblError");
				passTax = true;
				return;
			}
			
			//Step 6.- Fill all the data accordingly (mandatory or not)
			selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_companyType_cmb_dropdown");
			Thread.sleep(200);
			SendKeys(companyf, "Tecsidel, S.A.");
			Thread.sleep(200);
			selOp = ranNumbr(0,9);
			int selOp2 = ranNumbr(0,9);
			SendKeys(contactf, nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
			Thread.sleep(200);
			SendKeys(addressf, "C/Castanyer No. 29");
			Thread.sleep(200);
			SendKeys("ctl00_ContentZone_ctrlAccountData_txt_Settlements_box_data", "New Settlements Data");
			Thread.sleep(200);
			SendKeys(countryf, "España");
			Thread.sleep(200);
			SendKeys(cpf, "080220");
			Thread.sleep(200);
			SendKeys(emailf, "info@tecsidel.es");
			Thread.sleep(200);
			SendKeys(phoneCel, String.valueOf(ranNumbr(600000000,699999999)));
			Thread.sleep(200);
			SendKeys(workPhone,workPhone1[2]);
			Thread.sleep(200);
			SendKeys(perPhone,workPhone1[2]);
			Thread.sleep(200);
			SendKeys(faxPhone,workPhone1[2]);
			Thread.sleep(300);
			if (fieldCheck==true){
				if (companylinkselected.equals("Prepayment")){
					accountSelected = "Prepayment";
					if (ranNumbr(0,1)==0){
					elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");
					Thread.sleep(200);
					SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date", dateSel(currentYear,currentYear+1));
					Thread.sleep(200);
					SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data",String.valueOf(ranNumbr(1,15)));
				}
			}
			if (companylinkselected.equals("Exempt")){
				accountSelected = "Exempt";
				elementClick("ctl00_ContentZone_ctrlAccountExempt_mck_plaza_img_expand");
				String [] excentpToll = {"ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl01", "ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl03","ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl05", "ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl07", "ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl09", "ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl11", "ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl13", "ctl00_ContentZone_ctrlAccountExempt_mck_plaza_ctl15"};
				Thread.sleep(200);
				int selectOption = ranNumbr(1,8);
				for (int i=1;i<=selectOption;i++){
					int clickOp = ranNumbr(0,7);
					elementClick(excentpToll[clickOp]);
					Thread.sleep(300);
				}
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ctrlAccountExempt_mck_plaza_img_expand");;
				Thread.sleep(300);
				selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
				Thread.sleep(300);
				if (ranNumbr(0,1)==0){
					elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_0");
				}else{
					elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_1");
					SendKeys("ctl00_ContentZone_ctrlAccountExempt_cal_date_expiry_box_date",dateSel(currentYear,currentYear+1));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_days_warning_box_data",String.valueOf(ranNumbr(1,20)));
				}
				Thread.sleep(500);				
				if (ranNumbr(0,1)==0){
					elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_0");
				}else{
					elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_1");
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_max_trips_box_data",String.valueOf(ranNumbr(100,999)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_trips_warning_box_data",String.valueOf(ranNumbr(1,20)));
					}
				}
			}			
			if (ranNumbr(0,1)==0){
				elementClick("ctl00_ContentZone_chk_PublicTransport");
				Thread.sleep(200);
				selectDropDown("ctl00_ContentZone_cmb_PublicTransport_cmb_dropdown");
			}
			Thread.sleep(300);
			selectDropDownV("ctl00_ContentZone_type_payment_cmb_dropdown");
			Thread.sleep(300);
			selectDropDown("ctl00_ContentZone_type_threshold_cmb_dropdown");
			Thread.sleep(200);
			int radbutton = ranNumbr(0,2);
			if (radbutton == 0){
				elementClick("ctl00_ContentZone_discount_strategy_0");
			}
			if (radbutton == 1){
				elementClick("ctl00_ContentZone_discount_strategy_1");
			}
			if (radbutton == 2){
				elementClick("ctl00_ContentZone_discount_strategy_2");
			}
			Thread.sleep(300);
			takeScreenShot("E:\\Selenium\\","CompanyAccountFilledPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CompanyAccountFilledPage.jpeg");
			
			//Step 7.- Click on Save Button
			if (accountModeTaj.equals("New")){
			if (fieldCheck==true) {
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save Button
				Thread.sleep(3000);			
				if (companylinkselected.equals("Prepayment")){
						takeScreenShot("E:\\Selenium\\","CompanyAccountPayedPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CompanyAccountPayedPage.jpeg");
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button"); //click on Continue button						
					}
				}
			}else {
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(3000);
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
			}
			Thread.sleep(2000);
			takeScreenShot("E:\\Selenium\\","CompanyAccountCreatedPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CompanyAccountCreatedPage.jpeg");
			paymentfromCustomerEng();
			Thread.sleep(1000);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	

}