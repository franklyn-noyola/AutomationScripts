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


public class BOHost_shiftManagement{
			private static String [] optionShift = {"Validate","Remove validation"};
			private static int selOpt;
			private static boolean notValidated=false;
			private static boolean notRemoved=false;
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
			public void shiftManagementInit() throws Exception{
				configurationSection("Host",testNameSelected,testNameSelected);				
				testPlanReading(17,0,2,3);				
				selOpt = 0;ranNumbr(0,1);
				shiftManagement();
				if (notRemoved==true) {
					Thread.sleep(3000);
					actualResults.set(7, "Unable to remove validation to shift: "+optionSelectedId+" because of: "+confirmationMessage);
					driver.close();
					testLink();
					System.out.println("Unable to remove validation to shift: "+optionSelectedId+" because of: "+confirmationMessage);
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;					
				}
				if (notValidated==true) {
					Thread.sleep(3000);
					actualResults.set(6, "Unable to validate shift: "+optionSelectedId+" because of: "+errorText);
					actualResults.set(7, "N/A");
					executionResults.set(7, "");
					driver.close();
					testLink();
					System.out.println("Unable to validate shift: "+optionSelectedId+" because of: "+errorText);
					System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					return;
											
				}
				driver.close();
				testLink();
				System.out.println("It has been tested in version of: "+ getVersion(applevelTested));			
			}
			
			public static void shiftManagement() throws Exception {
				action = new Actions(driver);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
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
					takeScreenShot("E:\\Selenium\\","homeHostTajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeHostTajPage.jpeg");	
					Thread.sleep(2000);		
					applicationVer = getText("ctl00_statusRight");
					
					//Step 3.- Click on Shift management and then Shift management link
					action.moveToElement(driver.findElement(By.linkText("Shift management"))).build().perform();					
					Thread.sleep(1000);			
					pageSelected = "Shift management";
					elementClick("(//a[contains(text(),'Shift management')])[2]");
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.out.println(errorText);
						fail(errorText);
						System.out.println("Tested in Version of: "+ getVersion(applevelTested));
					}
					
					takeScreenShot("E:\\Selenium\\","shiftManagementPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","shiftManagementPage.jpeg");
					Thread.sleep(500);
					
					//Step 4.- Uncheck (if checked) Date field
					boolean dateChecked = driver.findElement(By.id("ctl00_ContentZone_chk_dates")).isSelected();
					if (dateChecked==true) {
						elementClick("ctl00_ContentZone_chk_dates");
						Thread.sleep(1000);
					}
					
					//Step 5.- Click on Search button in order to search all the Shift Managements available
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(2000);
					
					//Step 6.- Select any Shift Management
					itemSearchedandSelected();
					
					//Step 7.- Click on any Button either: Remove Validation or Validate
					if (optionShift[selOpt].equals("Remove validation")) {
						elementClick("ctl00_ContentZone_Button_unval");
						actualResults.set(6, "Remove validation button has been clicked, a pop up is displayed indicating if want to remove validation");
						if (isAlertPresent()) {
							driver.switchTo().alert().accept();
						}
						confirmationMessage = getText("ctl00_LblError");
						if (!confirmationMessage.contains("validation removed")) {
							notRemoved=true;
							return;
						}
						//Step 8.- If Validate button was clicked and it is in Validate Shift page, fill all the fields accordingly and click on Validate button and then Back button, otherwise if Remove Validation was clicked, click on Accept button

						actualResults.set(7, confirmationMessage);
						System.out.println(confirmationMessage);
						takeScreenShot("E:\\Selenium\\","shiftvalidationRemovedPage"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","shiftvalidationRemoved.jpeg");						
						return;						
					}
					
					if (optionShift[selOpt].equals("Validate")) {
						elementClick("ctl00_ContentZone_Button_validate");
						Thread.sleep(2000);
						String pageTitle = getText("ctl00_SectionZone_LblTitle");
						if (!pageTitle.contains("Validate shift")) {
							errorText = getText("ctl00_LblError");
							notValidated=true;
							return;
						}
						actualResults.set(6, "Validate button has been clicked, Validate Shift page is displayed with all corresponding fields");
						if (ranNumbr(0,1)==0) {
							elementClick("ctl00_ContentZone_box_Cash_txt_formated");
							SendKeys("ctl00_ContentZone_box_Cash_txt_plain",ranNumbr(100,50000)+"");
						}
						Thread.sleep(1000);
						SendKeys("ctl00_ContentZone_txt_valComment_box_data","Shift "+optionSelectedId+" is validated");
						Thread.sleep(1000);
						takeScreenShot("E:\\Selenium\\","shiftvalidatedPage"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","shiftvalidatedPage.jpeg");
						elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
						Thread.sleep(2000);
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						actualResults.set(7, "Shift "+optionSelectedId+" has been validated");
						System.out.println("Shift "+optionSelectedId+" has been validated");
					}					
						Thread.sleep(3000);
				
				}catch(Exception e){
					System.err.println(e.getMessage());
					e.printStackTrace();
					fail(e.getMessage());
					throw (e);
				}
			}		
      	
	}
