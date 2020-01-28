package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;



public class BOHost_VerTransacciones{
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
				String elementsFound = driver.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();				
				Thread.sleep(1500);
				System.out.println("Busqueda Completa: "+ elementsFound);
				Thread.sleep(1000);	
			}
			
			public static void verTransacciones() throws Exception {
				Actions action = new Actions(driver);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					driver.get(BoHostUrl);
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","loginBOCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCVHPage.jpeg");
					String pageTitle = driver.getTitle();					
					if (!pageTitle.equals("P�gina de Acceso")){						
						takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");
						driver.close();
						System.err.println("Un error ha ocurrido en la P�gina de Inicio");
						fail("Un error ha ocurrido en la P�gina de Inicio");
					}
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeHostCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeHostCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					action.moveToElement(driver.findElement(By.linkText("Transacciones"))).build().perform();					
					Thread.sleep(1000);
					elementClick("Ver transacciones");								
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","verTransaccionesPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","verTransaccionesPage.jpeg");
					if (driver.getPageSource().contains("Se ha detectado un error")) {
						errorText = getText("ctl00_ContentZone_lblMsg");
						System.err.println ("No se puede entrar a la p�gina Ver transacciones a causa de: "+errorText);
						fail ("No se pued entrar a la p�gina Ver transacciones a causa de: "+errorText);
					}					
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_dateSelector_dt_from_box_date",dateverTransacciones);
					Thread.sleep(1000);		
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","verTransaccionesResults"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","verTransaccionesRetults.jpeg");
					Thread.sleep(1000);
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
				
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}		
      	
	}
