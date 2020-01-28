package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MCS_cambiarModoVia{
			private static String Modo;
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
			public void cambiarModoVia() throws Exception {
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
					elementClick("Cambiar el modo de la vía");
					Thread.sleep(600);		
					if (ranNumbr(0,1)==1){
						Modo = "Automática";
						elementClick("Automática");
					}else{
						elementClick("Mixta");		
						Modo = "Mixta";
					}
					takeScreenShot("E:\\Selenium\\","DetalleViaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DetalleViaPage.jpeg");
					String operationWindow = getText("titlebar");//driver.findElement(By.xpath("//*[@id='lbl_alert_title']")).getText();
					operationWindow = operationWindow.trim();
					if (operationWindow.equals("Error")){
						String errormessage = getText("lbl_message");
						System.err.println(operationWindow+": "+errormessage);
						fail(errormessage);
						
					}					
					Thread.sleep(1000);
					String confirmMessage = getText("lbl_message");
					takeScreenShot("E:\\Selenium\\","cambiarModoResults"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cambiarModoResults.jpeg");
					String operationWindow2 =  getText("lbl_alert_title");
					System.out.println(operationWindow2+": "+confirmMessage+" "+Modo);	
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));		
					Thread.sleep(1000);						
				}catch(Exception e){
					System.err.println(e.getMessage());
					e.printStackTrace();
					fail(e.getMessage());
					throw(e);
				}
			}		
       	
}