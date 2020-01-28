package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class BOHost_VerTransacciones {
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
			public void verTransaccionesInit() throws Exception{
				configurationSection("Host",testNameSelected,testNameSelected);				
				dateverTransacciones = "01/09/2017";
				verTransacciones();
				String elementsFound = getText("ctl00_ContentZone_tablePager_LblCounter");				
				String endDate = driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_to_box_date")).getAttribute("value");
				Thread.sleep(1500);
				System.out.println("Hay "+elementsFound.substring(elementsFound.indexOf("of ")+3)+" transacciones desde la fecha "+dateverTransacciones+" hasta la fehca "+endDate);
				Thread.sleep(1000);	
			}
			
			public static void verTransacciones() throws Exception {
				action = new Actions(driver);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					driver.get(BoHostUrl);
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");	
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeHostTajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeHostTajPage.jpeg");	
					Thread.sleep(2000);					
					action.moveToElement(driver.findElement(By.linkText("Transactions"))).build().perform();					
					Thread.sleep(1000);
					pageSelected = "View transactions";
					elementClick(pageSelected);
					Thread.sleep(1000);
					if (driver.getPageSource().contains("An error has been detected") || driver.getPageSource().contains("Se ha detectado un error")  || driver.getPageSource().contains("La operación ha fallado")){
						errorText = getText("ctl00_ContentZone_lblMsg");
						System.out.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","verTransaccionesPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","verTransaccionesPage.jpeg");
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_dateSelector_dt_from_box_date",dateverTransacciones);
					Thread.sleep(1000);		
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","verTransaccionesResults"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Tajikistan\\"+projectNamePath+testClassName+"\\attachments\\","verTransaccionesRetults.jpeg");
					Thread.sleep(1000);
				
				}catch(Exception e){
					System.out.println(e.getMessage());
					e.printStackTrace();
					fail(e.getMessage());
					throw (e);
				}
			}		
      	
	}
