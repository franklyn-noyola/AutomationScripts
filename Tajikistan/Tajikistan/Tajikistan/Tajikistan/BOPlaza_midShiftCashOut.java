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

public class BOPlaza_midShiftCashOut{	
	private static String shiftNumbr;
	private static String liquidacionNumbr;
	private static String bagTotal;	
	public static List <String> windows;
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
	public void midShiftCashOutInit() throws Exception{
		configurationSection("Plaza",testNameSelected,testNameSelected);		
		testPlanReading(22,0,2,3);
		Thread.sleep(1000);			
		midShiftCashOut();
		Thread.sleep(1000);
		actualResults.set(5,"A Mid Shift Cash Out has been created with No. "+liquidacionNumbr+" Shift Number: "+shiftNumbr+" and Bag Total: "+bagTotal);
		Thread.sleep(1000);
		driver.close();				
		testLink();
		Thread.sleep(1000);
		System.out.println("A Mid Shift Cash Out has been created with No. "+liquidacionNumbr+" Shift Number: "+shiftNumbr+" and Bag Total: "+bagTotal);
		System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
		
	}
	
	public static void midShiftCashOut() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Step 1.- Enter into Tajikistan Login Page
			driver.get(BoPlazaUrl);
			takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");
			loginPageErr();
			if (pageSelectedErr==true) {
				testLink();
				System.out.println("An error has ocurred in the Login Page");
				fail("An error has ocurred in the Login Page");
			}		
			// Step 2.- Log in with user 00001/00001
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOTajiPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajiaPage.jpeg");
			applicationVer = driver.findElement(By.id("ctl00_statusRight")).getText();
			Thread.sleep(500);			
			
			//Step 3.- Click on Collector Management then Mid shift cash out link
			action.moveToElement(driver.findElement(By.linkText("Collector management"))).build().perform();
			Thread.sleep(500);
			pageSelected="Mid shift cash out";
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
			
			//Paso 4.- Fill all fields accordingly
			takeScreenShot("E:\\Selenium\\","midshiftcashoutPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","midshiftcashoutPage.jpeg");
			shiftNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
			liquidacionNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");			
			Thread.sleep(100);
			dataFill();
			Thread.sleep(100);
			elementClick("ctl00_ContentZone_BtnCalculate");
			Thread.sleep(500);			
			bagTotal = getText("ctl00_ContentZone_LblPIBInBag");
			Thread.sleep(500);
			SendKeys("ctl00_ContentZone_txt_supervisor_box_data","00001");
			SendKeys("ctl00_ContentZone_txt_password_box_data","00001");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			
			//Step 5.- Once all fields are filled, click on Submit button
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			
			//Step 6.- Click on Accept button
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();				
			}
			Thread.sleep(10000);
			
			String sectionTitle = getText("ctl00_SectionZone_LblTitle");
			if (!sectionTitle.contains("Collector Cash-out Report")) {
				errorText = getText("ctl00_LblError");
				takeScreenShot("E:\\Selenium\\","midshiftcashoutErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","midshiftcashoutErr.jpeg");
				actualResults.set(5,"Unable to create Mid shift cash out because of: "+errorText);
				executionResults.set(5,"Fallado");						
				summaryBug = "Unable to create Mid shift cash out";
				erroTextBug = "Unable to create Mid shift cash out debause of: "+errorText;
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\midshiftcashoutErr.jpeg";						
				driver.close();
				testLink();
				System.out.println("Unable to create a Mid Shift Cash Out because of: "+errorText);
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));				
				fail("Unable to create a Mid Shift Cash Out because of: "+errorText);
			}
			
			Thread.sleep(7000);
			takeScreenShot("E:\\Selenium\\","midshiftcashoutPDF"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","midshiftcashoutPDF.jpeg");			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	
	public static void dataFill() throws Exception{
		if (ranNumbr(0,1)==0) {
			SendKeys("ctl00_ContentZone_NumberCASH01N10000_1",String.valueOf(ranNumbr(1,5)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N5000_1",String.valueOf(ranNumbr(1,7)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N2000_1",String.valueOf(ranNumbr(1,9)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N1000_1",String.valueOf(ranNumbr(1,11)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N500_1",String.valueOf(ranNumbr(1,100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C500_1",String.valueOf(ranNumbr(100,1000)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C300_1",String.valueOf(ranNumbr(100,1500)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N100_1",String.valueOf(ranNumbr(100,1100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C100_1",String.valueOf(ranNumbr(100,1100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N50_1",String.valueOf(ranNumbr(100,1600)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C25_1",String.valueOf(ranNumbr(100,1900)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N20_1",String.valueOf(ranNumbr(100,2100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C20_1",String.valueOf(ranNumbr(100,2100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C10_1",String.valueOf(ranNumbr(100,3500)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N5_1",String.valueOf(ranNumbr(100,4000)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C5_1",String.valueOf(ranNumbr(100,4000)));			
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N1_1",String.valueOf(ranNumbr(100,5500)));			
			Thread.sleep(100);
		}else {
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N10000_1",String.valueOf(ranNumbr(1,5)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N5000_1",String.valueOf(ranNumbr(1,7)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N2000_1",String.valueOf(ranNumbr(1,9)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N1000_1",String.valueOf(ranNumbr(1,11)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N500_1",String.valueOf(ranNumbr(1,100)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01C500_1",String.valueOf(ranNumbr(100,1000)));
				Thread.sleep(100);
			}
				SendKeys("ctl00_ContentZone_NumberCASH01C300_1",String.valueOf(ranNumbr(100,1500)));
				Thread.sleep(100);
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N100_1",String.valueOf(ranNumbr(100,1100)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01C100_1",String.valueOf(ranNumbr(100,1100)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N50_1",String.valueOf(ranNumbr(100,1600)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01C25_1",String.valueOf(ranNumbr(100,1900)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N20_1",String.valueOf(ranNumbr(100,2100)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01C20_1",String.valueOf(ranNumbr(100,2100)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01C10_1",String.valueOf(ranNumbr(100,3500)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N5_1",String.valueOf(ranNumbr(100,4000)));
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01C5_1",String.valueOf(ranNumbr(100,4000)));			
				Thread.sleep(100);
			}
			if (ranNumbr(0,1)==0) {
				SendKeys("ctl00_ContentZone_NumberCASH01N1_1",String.valueOf(ranNumbr(100,5500)));			
				Thread.sleep(100);
			}
		}
	}
	
}