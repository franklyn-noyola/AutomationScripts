package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class BOHost_bagRecount{
			public static String dateSeal;
			public static String bankTour;
			public static String collector;
			public static String shift;
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
			public void bagRecountInit() throws Exception{
				configurationSection("Host",testNameSelected,testNameSelected);				
				testPlanReading(6,0,2,3);				
				bagRecount();				
				actualResults.set(7, "Seal No. "+optionSelectedId+" of Bank tour: "+bankTour+" of Collectr: "+collector+" of the shift No. "+shift+" of Date Seal: "+dateSeal+" has been recounted");
				Thread.sleep(1000);
				driver.close();
				testLink();
				System.out.println("Seal No. "+optionSelectedId+" of Bank tour: "+bankTour+" of Collectr: "+collector+" of the shift No. "+shift+" of Date Seal: "+dateSeal+" has been recounted");
				System.out.println("Tested in Version of: "+ getVersion(applevelTested));				
			}
			
			public static void bagRecount() throws Exception {
				action = new Actions(driver);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					//Step 1.- Enter into Tajikistan Login Page
					driver.get(BoHostUrl);
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.out.println("An error has ocurred in the Login Page");
						fail("An error has ocurred in the Login Page");
					}
					
					//Step 2.- Log with user 00001/00001					
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeHostTajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeHostTajPage.jpeg");	
					Thread.sleep(2000);		
					applicationVer = getText("ctl00_statusRight");
					
					//Step 3.- Click on Shift management and then click on Bag re-count input link  
					action.moveToElement(driver.findElement(By.linkText("Shift management"))).build().perform();					
					Thread.sleep(1000);
					pageSelected = "Bag re-count input";
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
					takeScreenShot("E:\\Selenium\\","bagRecountInputPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bagRecountInput.jpeg");
					Thread.sleep(500);
					boolean dateChecked = driver.findElement(By.id("ctl00_ContentZone_dateSelector_chk_dates")).isSelected();
					if (dateChecked==true) {
						elementClick("ctl00_ContentZone_dateSelector_chk_dates");
						Thread.sleep(1000);
					}
					//Step 4.- Click on Search Button
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(2000);
					
					//Step 5.- Select any Item
					itemSearchedandSelected();
					dateSeal = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[2]");
					bankTour = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[4]");
					collector = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[5]");
					shift = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[6]");
					Thread.sleep(2000);
					
					//Step 6.- Click on Re-count Button
					elementClick("ctl00_ContentZone_BtnReCount");
					Thread.sleep(2000);
					
					//Step 7.- Fill or modify all fields accordingly
					action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_RecountCA201_txt_formated")), String.valueOf(ranNumbr(100,50000))).build().perform();					
					Thread.sleep(1000);
					
					//Step 8.- Click on Submit button
					elementClick("ctl00_ContentZone_Button_InsertApply");
					Thread.sleep(5000);
				
				}catch(Exception e){
					System.out.println(e.getMessage());
					e.printStackTrace();
					fail(e.getMessage());
					throw (e);
				}
			}		
      	
	}