package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MCS_cerrarV�a {
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
			public void cerrarVia() throws Exception {
				configurationSection("MCS",testNameSelected,testNameSelected);				
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					driver.get(MCSUrl);
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","loginMCSCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginMCSCVHPage.jpeg");
					applicationVer = getText(mcsVersion);
					loginPageMCS("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeMCSCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeMCSCVHPage.jpeg");	
					Thread.sleep(2000);
					elementClick("lane_name_link_26");
					Thread.sleep(1000);
					elementClick("//*[@id='lyr_menu']/div[2]");
					Thread.sleep(600);
					elementClick("Cerrar la v�a");
					Thread.sleep(600);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}	
					SendKeys("sipt_fieldform_fieldpart_pwduser","00001");
					SendKeys("sipt_fieldform_fieldpart_pwdpassword","00001");
					Thread.sleep(1000);
					elementClick("btn_sipt_fieldform_okpart_pwd");
					takeScreenShot("E:\\Selenium\\","DetalleViaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DetalleViaPage.jpeg");
					Thread.sleep(1000);
					String operationWindow = getText("titlebar");//driver.findElement(By.xpath("//*[@id='lbl_alert_title']")).getText();
					operationWindow = operationWindow.trim();
					if (operationWindow.equals("Error")){
						String errormessage = getText("lbl_message");
						System.err.println(operationWindow+": "+errormessage);
						fail(errormessage);
						
					}					
					Thread.sleep(1000);
					String confirmMessage = getText("lbl_message");		
					takeScreenShot("E:\\Selenium\\","cerrarViaResults"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cerrarViaResults.jpeg");		
					System.out.println(operationWindow+": "+confirmMessage);
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
					Thread.sleep(1000);					
				}catch(Exception e){
					System.err.println(e.getMessage());
					e.printStackTrace();
					fail(e.getMessage());
					throw(e);
				}
			}

		
}