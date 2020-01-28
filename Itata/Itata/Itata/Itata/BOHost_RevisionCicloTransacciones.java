package Itata;

import static org.junit.Assert.*;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_RevisionCicloTransacciones{
			public static int i;			
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
			public void RevisionCicloTransacciones() throws Exception {
				
				action = new Actions(driver);			
				configurationSection("Host",testNameSelected,testNameSelected);
				testPlanReading(13,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");	
				try{
					//Paso 1.- Entrar a la página de Login del BO de Itata
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(500);			
					
					//Paso 3.- Ir al Menú Transacciones y hacer click en Revisión ciclos de transacciones
					action.moveToElement(driver.findElement(By.linkText("Transacciones"))).build().perform();
					Thread.sleep(500);		
					pageSelected="Revisión Ciclos de transacciones";
					elementClick(pageSelected);								
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsPage.jpeg");
					Thread.sleep(500);		
					//new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_tollCompany_cmb_dropdown"))).selectByIndex(2);
					
					//Paso 4.- Poner una Fecha de búsqueda el campo Desde y luego hacer click en el botón Búsqueda
					SendKeys("ctl00_ContentZone_dateSelector_dt_from_box_date","01/01/2018");
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Paso 5.- Hacer clicck en cualquier ciclo de transacción
					itemSearchandSelect();
					String cycleSelected = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectedRow+"]/td[5]");
					actualResults.set(4, "Se ha seleccionado el ciclo: "+cycleSelected);
					
					//Paso 6.- Hacer click en el botón Todos
					elementClick("ctl00_ContentZone_Button_transactions");
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsAllPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsAllPage.jpeg");
					
					//Paso 7.- Hacer click en cualquier Transacción disponible
					selectLinkTable();
					actualResults.set(6, "Se ha cliqueado la transacción: "+optionSelectedId+" y aparece la pantalla Revisión de Transacciones:Todas con las transacciones asociadas a la transaccion cliqueada previamente");					
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsValidatePage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsValidatePage.jpeg");
					WebElement tableResults = driver.findElement(By.id("ctl00_ContentZone_tbl_trs"));
					List<WebElement> tableRes = tableResults.findElements(By.tagName("tr"));
					int recordSel = tableRes.size()-2;
					int recordSelected = ranNumbr(1,recordSel);
					if (recordSelected<1) {
						recordSelected =1;
					}
					//Paso 8.- Seleccionar una, varias o todas las transacciones en el checkbox que se encuentra al final de las transacciones
					if (recordSelected==recordSel) {
						elementClick("ctl00_ContentZone_CheckBox_selAll");
						actualResults.set(7, "Se ha seleccionado todas las transacciones");
					}else {						
						for (i=1; i<=recordSelected;i++) {
							int selRow = ranNumbr (3,recordSel);
							elementClick("//*[@id=\"ctl00_ContentZone_tbl_trs\"]/tbody/tr["+selRow+"]/td[24]/input");
						}
						actualResults.set(7, "Se ha seleccionado "+recordSelected+" transacciones");
					}
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsSelPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsSelPage.jpeg");
					
					//Paso 9.- Hacer click en el botón Validar seleccionadas
					elementClick("ctl00_ContentZone_Button_validate");
					Thread.sleep(1000);
					
					if (!driver.getPageSource().contains("transacciones fueron validadas")) {
						actualResults.set(8, "ERROR al tratar de validar transacciones en la Transacción Id: "+optionSelectedId+" del ciclo: "+cycleSelected);									
						for (int i=9;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.err.println("ERROR al tratar de validar transacciones en la Transacción Id: "+optionSelectedId+" del ciclo: "+cycleSelected);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
						
					}
					
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsSelValPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsSelValPage.jpeg");
					
					//Paso 10.- Hacer click en el botón Volver
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsBack1Page"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsBack1Page.jpeg");
					
					//Paso 11.- Hacer click en el botón Volver
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","RevisionCicloTransaccionsBack2Page"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RevisionCicloTransaccionsBack2Page.jpeg");
					
					//Paso 12.- Hacer click en el botón Revisión ciclo
					elementClick("ctl00_ContentZone_Button_review");
					
					//Paso 13.- Hacer click en el botón aceptar del popup
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(3000);
					takeScreenShot("E:\\Selenium\\","CicleValidatedPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","CicleValidatedPage.jpeg");
					String confirmationValidation =getText("ctl00_LblError");
					if (!confirmationValidation.equals("OK: Revisión del ciclo de Transacciones")) {
						actualResults.set(12,"ERROR al validar la Revisión de Ciclo de transacciones con concerniente al ciclo No.: "+cycleSelected+" debido a :"+confirmationValidation);
						driver.close();
						testLink();
						System.err.println("ERROR al validar la Revisión de Ciclo de transacciones con concerniente al ciclo No.: "+cycleSelected+" debido a :"+confirmationValidation);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
					actualResults.set(12,"La Revision del Ciclo de Transacciones concerniente al ciclo No.: "+cycleSelected+" ha sido validado correctamente en la Transacción Id: "+optionSelectedId);
					driver.close();
					testLink();
					System.out.println("La Revision del Ciclo de Transacciones concerniente al ciclo No.: "+cycleSelected+" ha sido validado correctamente en la Transacción Id: "+optionSelectedId);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
		}
		
}