package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class BOHost_entradaRecuentoBolsas{
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
			public void bagsRecount() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);
				testPlanReading(12,0,2,3);
				action = new Actions(driver);			
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");	
				try{
					//Paso 1.- Entrar a la página de Login del BO Host de R3R5Ciralsa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
														
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(500);					
					
					//Paso 3.- Hacer click en el Menu de Gestión de Turno y luego Entrada recuento de bolsas
					action.moveToElement(driver.findElement(By.linkText("Gestión de turno"))).build().perform();
					Thread.sleep(500);		
					pageSelected = "Entrada recuento de bolsas";
					elementClick(pageSelected);								
					Thread.sleep(500);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					takeScreenShot("E:\\Selenium\\","recuentoBolsasPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","recuentoBolsasPage.jpeg");
					Thread.sleep(500);					
					elementClick("ctl00_ContentZone_dateSelector_chk_dates");
					Thread.sleep(500);												
					
					//Paso 4.- Hacer click en el Botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(500);					
					
					//Paso 5.- Seleccionar cualquier bolsa
					itemSearchandSelect();					
					if (noItemFound==true) {
						actualResults.set(4, "No hay bolsas disponibles para seleccionar");
						for (int i=5;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No hay bolsas disponibles para seleccionar");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
						return;
					}
					
					Thread.sleep(1000);
					
					//Paso 6.- Hacer click en el botón Recuento
					elementClick("ctl00_ContentZone_BtnReCount");
					takeScreenShot("E:\\Selenium\\","recuentoBolsasPopup"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","recuentoBolsasPopup.jpeg");
					
					//Paso 7.- Llenar o modificar todos los campos disponibles
					String bagNumbr = driver.findElement(By.id("ctl00_ContentZone_BoxSeal_box_data")).getAttribute("value");
					String collectorNumbr = driver.findElement(By.id("ctl00_ContentZone_BoxOperatorSeal_box_data")).getAttribute("value");					
					String dateTime = driver.findElement(By.id("ctl00_ContentZone_BoxDateSeal_box_data")).getAttribute("value");
					int cash = ranNumbr(100,100000);					
					action.click(driver.findElement(By.id("ctl00_ContentZone_RecountCA201_txt_formated"))).sendKeys(String.valueOf(cash)).build().perform();
										
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
					
					//Paso 8.- Hacer click el el Botón Confirmar
					elementClick("ctl00_ContentZone_Button_InsertApply");
					Thread.sleep(1000);
					actualResults.set(7, "El Recuento ha sido hecho correctamente en la bolsa No. "+bagNumbr+" del Cobrador No. "+collectorNumbr+" de la fecha y hora: "+dateTime+" en Efectivo: "+cash);
					driver.close();
					testLink();
					System.out.println("El Recuento ha sido hecho correctamente en la bolsa No. "+bagNumbr+" del Cobrador No. "+collectorNumbr+" de la fecha y hora: "+dateTime+" en Efectivo: "+cash);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
		}
		
}