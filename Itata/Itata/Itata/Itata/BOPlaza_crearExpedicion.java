package Itata;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOPlaza_crearExpedicion {
			 public static String title;
			 private static String liquidacionErr;
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
			public void crearExpedicion() throws Exception {				
				 action = new Actions(driver);
				configurationSection("Plaza",testNameSelected,testNameSelected);
				testPlanReading(16,0,2,3);				
				Thread.sleep(1000);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");			
				try {
					//Paso 1.- Entrar a la página de Login del BO de Itata
					driver.get(BoPlazaUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(500);
					pageSelected = "BackOffice Plaza";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","loginItataPlHoPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginItataPlHoPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					
					//Pasp 3.- Ir a la Pantalla de Crear Expedición (Haciendo click en Gestión de cobradores y después Creación de Expedición)
					action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
					pageSelected = "Creación de Expedición";
					elementClick(pageSelected);
					takeScreenShot("E:\\Selenium\\","ExpedicionPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ExpedicionPage.jpeg");
					
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					
					//Paso 4.- Ya en la pantalla principal principal de Creación de Expedición, hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(2000);
					
					//Paso 5.- Ya en la pantalla de Creación expedición: Detalles, si en la tabla de resultados no hay bolsas, no se puede crear la expedición, de lo contrario si hay bolsas disponibles, entonces se hace click en el botón Continuar
					WebElement tableBagsResults = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
					List<WebElement>bagNumbers = tableBagsResults.findElements(By.tagName("tr"));					
					if (bagNumbers.size()<2) {
						actualResults.set(4, "No se puede crear Expedicion porque no tiene Bolsas Disponibles. Favor de hacer una o varias liquidaciones parciales para hacer una Expedicion");
						actualResults.set(5, "N/A");
						executionResults.set(5, "");
						driver.close();
						testLink();
						System.out.println("No se puede crear Expedicion porque no tiene Bolsas Disponibles. Favor de hacer una o varias liquidaciones parciales para hacer una Expedicion");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(200);
					
					//Paso 6.- Llenar todos los campos pertinentes y hacer click en Confirmar
					int matNum = ranNumbr(5000,9999);
					int matlet = ranNumbr(0,matletT.length()-1);
					int matlet1 = ranNumbr(0,matletT.length()-1);
					int matlet2 = ranNumbr(0,matletT.length()-1);
					matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
					SendKeys("ctl00_ContentZone_txt_plate_box_data",matriNu);
					int selop1 = ranNumbr(0,nameOp.length-1);
					int selop2 = ranNumbr(0,nameOp.length-1);
					int selop3 = ranNumbr(0,nameOp.length-1);
					SendKeys("ctl00_ContentZone_txt_officer_box_data",nameOp[selop1]+" "+lastNameOp[selop1]+", "+nameOp[selop2]+" "+lastNameOp[selop2]+", "+nameOp[selop3]+" "+lastNameOp[selop3]);
					SendKeys("ctl00_ContentZone_txt_comment_box_data", "Expedicion creada para la matricula No. "+matriNu);
					String totalExpedido = driver.findElement(By.id("ctl00_ContentZone_txt_total_box_data")).getAttribute("value");
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","DataFilledPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilledPage.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					if (driver.getPageSource().contains("Hubo un error en la") || driver.getPageSource().contains("La operación ha fallado por un error") || driver.getPageSource().contains("Error de sistema")) {
						liquidacionErr=getText("ctl00_LblError");
						actualResults.set(5, "No se puede hacer una Expedición, debido a: "+liquidacionErr);
						summaryBug = liquidacionErr;
						erroTextBug = liquidacionErr;
						componentBug = "HM";
						priority = 1;
						severityBug = 4;
						testFailed = true;
						bugAttachment = true;
						pathAttachment = "E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\paginaInicioErr.jpeg";
						applicationVisible=true;	
						driver.close();
						testLink();
						System.out.println("No se puede hacer una Expedición, debido a: "+liquidacionErr);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("No se puede hacer una liquidación Parcial, debido a: "+liquidacionErr);
					}
					Thread.sleep(5000);		
					String wHandleBefore = driver.getWindowHandle();					
					for(String winHandle : driver.getWindowHandles()){
							if (!wHandleBefore.equals(winHandle)){
								driver.switchTo().window(winHandle);
							}
							Thread.sleep(1000);								
					}
					title = getText("LblTitle");
					assertEquals(title, "Informe de expedición");					
					takeScreenShot("E:\\Selenium\\","ExpedicionPDF"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ExpedicionPDF.jpeg");					
					Thread.sleep(3000);
					actualResults.set(5, "Se ha creado una Expedición con "+(bagNumbers.size()-1)+" bolsa/s y un total expedido "+totalExpedido);
					driver.close();
					testLink();
					System.out.println("Se ha creado una Expedición con "+(bagNumbers.size()-1)+" bolsa/s y un total expedido "+totalExpedido);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
				
				
			}
						
	}