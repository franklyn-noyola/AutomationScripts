package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;



public class BOHost_accountClose{
		private static boolean accountClosed = false;
		private static boolean NumbVehC = false;
		private static int NumbVeh;
		private static float currBal;
		private static boolean currentBalance = false;
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
	public void accountCloseInit() throws Exception {		
		configurationSection("Host",testNameSelected,testNameSelected);		
		testPlanReading(1,0,2,3);				
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		accountClose();
		Thread.sleep(1000);
		if (accountClosed){
			actualResults.set(4, "Unable to close Account "+accountNumbr+" because it is already closed");
			for (int i=5;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Unable to close Account "+accountNumbr+" because it is already closed");
			System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
			return;					
		}
		if (NumbVehC){
			actualResults.set(6, "Unable to close Account "+accountNumbr+" because it has "+NumbVeh+" vehicle/s already assigned");
			for (int i=7;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Unable to close Account "+accountNumbr+" because it has "+NumbVeh+" vehicle/s already assigned");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;	
		}
		if (currentBalance){
			actualResults.set(6, "Unable to close Account "+accountNumbr+" because it has "+currBal+" and it is not set to 0.00");
			for (int i=7;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Unable to close Account "+accountNumbr+" because it has "+currBal+" and it is not set to 0.00");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;			
		}
		Thread.sleep(3000);
		actualResults.set(8, "Account "+accountNumbr+" closed correclty");
		driver.close();
		testLink();
		System.out.println("Account "+accountNumbr+" closed correclty");
		System.out.println("Tested in Version of: "+ getVersion(applevelTested));
	}
	
	public static void accountClose() throws Exception{		
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
			takeScreenShot("E:\\Selenium\\","homeBOTajPage"+timet+".jpg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajPage.jpeg");
			applicationVer = getText("ctl00_statusRight");
			Thread.sleep(2000);			
			
			//Step 3.- Go to Customer Service and then click on Select Account
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
			
			//Step 4.- Click on Search button
			elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
			Thread.sleep(1000);
			
			//Step 5.-  Click on any desired Account
	  		selectAccount();
			Thread.sleep(1000);					
			takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
			
			Thread.sleep(1000);
			accountNumbr = getText("ctl00_SectionZone_LblTitle");
			accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
			takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");
			
			//Step 6.- Click on Close Account button
			if(driver.getPageSource().contains("CLOSED ACCOUNT")){
				accountClosed = true;
				return;
			}
			String accountType = getText("ctl00_ContentZone_ctrlAccountData_lbl_accountType");
			String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
			Thread.sleep(1000);
			NumbVeh = Integer.parseInt(numberVehicles);
			if (NumbVeh>0){
				NumbVehC = true;
				return;
			}
			if (accountType.equals("PREPAY ACCOUNT")|| accountType.equals("INDIVIDUAL ACCOUNT")){
				String currentBala = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
				String balancS = (currentBala.substring(4).replace(",", "."));
				currBal = Float.parseFloat(balancS);
				if (currBal<0 || currBal>0){
					currentBalance = true;
					return;
				}
			}
			elementClick("ctl00_ContentZone_BtnCloseAccount");
			Thread.sleep(1000);
			
			//Step 7.- Already in the Close Account screen, verify if Account can be closed
			takeScreenShot("E:\\Selenium\\","accountClosePage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosePage.jpeg");
			
			//Step 8.- Enter any comment in the Comment field and click on  Close account button
			SendKeys("ctl00_ContentZone_txtComment", "Account will be closed");
			Thread.sleep(1000);
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","accountClosed"+timet+".jpeg");
			takeScreenShot("E:\\WorkSpace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosed.jpeg");
			
			//Step 9.- Click on Back Button
			elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
			Thread.sleep(2000);
			takeScreenShot("E:\\Selenium\\","accountClosedConfirmed"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosedConfirmed.jpeg");										

		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
				
	}
		
}