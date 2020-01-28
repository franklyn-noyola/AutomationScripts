package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;



public class MCS_alarmaBusqueda {
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
			public void alarmaBusqueda() throws Exception {
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
					elementClick("//div[@onclick=\"dropdownmenu(this, event, menu3, '250px')\"]");
					elementClick("Ver alarmas");
					Thread.sleep(1000);
					driver.switchTo().frame(0);
					takeScreenShot("E:\\Selenium\\","verAlarmasPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","verAlarmasPage.jpeg");
					Thread.sleep(500);
					new Select(driver.findElement(By.id("cbDia1"))).selectByVisibleText("01");
					new Select(driver.findElement(By.id("cbMes1"))).selectByVisibleText("ene");
					selectDropDown("cmb_type");
					Thread.sleep(1000);		
					elementClick("btn_search");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","verAlarmasResults"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","verAlarmasResults.jpeg");
					Thread.sleep(1000);
					String elementsFound = getText("lbl_showing");				
					Thread.sleep(1500);
					System.out.println("Busqueda Completa: "+ elementsFound);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					Thread.sleep(1000);					
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}		
       	
}
