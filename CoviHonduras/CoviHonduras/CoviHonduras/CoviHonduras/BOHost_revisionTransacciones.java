package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class BOHost_revisionTransacciones{
	private String testNameSelected=this.getClass().getSimpleName();
		
		@Before
		public void setup() throws Exception{
			setUp();
		}

		@After
		public void teardown() throws Exception{			  
		    tearDown();
		}
		
		@Test
			public void revisionTransaccionesPage() throws Exception{
				configurationSection("Host",testNameSelected,testNameSelected);				
				Actions action = new Actions(driver);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					driver.get(BoHostUrl);
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
					takeScreenShot("E:\\Selenium\\","homepageCVH_"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homepageCVH.jpeg");
					Thread.sleep(2000);		
					applicationVer = getText("ctl00_statusRight");
					action.moveToElement(driver.findElement(By.linkText("Transacciones"))).build().perform();					
					Thread.sleep(1000);
					elementClick("Revisi�n de Transacciones");								
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","revisionTransicionesPage_"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","revisionTransicionesPage.jpeg");
					if (driver.getPageSource().contains("Se ha detectado un error")) {
						errorText = getText("ctl00_ContentZone_lblMsg");
						System.err.println ("No se pued entrar a la p�gina Liquidaci�n Final a causa de: "+errorText);
						fail ("No se pued entrar a la p�gina Liquidaci�n Final a causa de: "+errorText);
					}
					SendKeys("ctl00_ContentZone_dateSelector_dt_from_box_date","01/01/2017");
					Thread.sleep(500);					
					elementClick("ctl00_ContentZone_Button_transactions");
					Thread.sleep(3000);
					takeScreenShot("E:\\Selenium\\","revisionTransicionesResults_"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","revisionTransicionesResults.jpeg");
					if (driver.getPageSource().contains("No hay transacciones para la selecci�n actual")){
						System.err.println("No hay transacciones para la selecci�n actual");
						fail("No hay transacciones para la selecci�n actual");
						Thread.sleep(2000);
						return;
					}
					String elementsFound = getText("ctl00_ContentZone_tablePager_LblCounter");				
					Thread.sleep(1500);
					System.out.println("Busqueda Completa: "+ elementsFound);
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
