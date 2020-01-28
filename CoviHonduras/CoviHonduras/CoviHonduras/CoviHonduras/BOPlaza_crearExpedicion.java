package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class BOPlaza_crearExpedicion{
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
			public void crearExpedicion() throws Exception{
				configurationSection("Plaza",testNameSelected,testNameSelected);				
				testPlanReading(12,5,7,8);
				Actions action = new Actions(driver);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					//Paso 1.- 	Entrar a la página de login del BackOffice de Plaza de CoviHonduras
					driver.get(BoPlazaUrl);
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","loginBOCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCVHPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}

					//Paso 2.- Loguearte con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);										
					takeScreenShot("E:\\Selenium\\","homeHostCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeHostCVHPage.jpeg");
					
					//Paso 3.- Entrar a la pantalla de Crear Expedición
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();					
					Thread.sleep(1000);
					pageSelected = "Creación de Expedición"; 
					elementClick(pageSelected);								
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","creacionExpedicionPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","creacionExpedicionPage.jpeg");
					
					//Paso 4.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					
					//Paso 5.- Ya en la pantalla de Creación expedición: Detalles, si en la tabla de resultados no hay bolsas, no se puede crear la expedición, de lo contrario si hay bolsas disponibles, entonces se hace click en el botón Continuar
					WebElement tableRes = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
					List <WebElement> rowResults = tableRes.findElements(By.tagName("tr"));
					if (rowResults.size()<2) {
						actualResults.set(4, "No se puede crear Expedición debido a que no hay Bolsas disponibles para hacer Expedición");
						actualResults.set(5, "N/A");
						executionResults.set(5,"");
						testLink();
						System.out.println("No se puede crear Expedición debido a que no hay Bolsas disponibles para hacer Expedición");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					//Paso 6.- Ya en la pantalla Crear Expedición, llenar todos los campos y hacer click en el botón Confirmar
					String totalExpedido = driver.findElement(By.id("ctl00_ContentZone_txt_total_box_data")).getAttribute("value");
					int matNum = ranNumbr(5000,9999);
					int matlet = ranNumbr(0,matletT.length()-1);
					int matlet1 = ranNumbr(0,matletT.length()-1);
					int matlet2 = ranNumbr(0,matletT.length()-1);
					tableRes = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
					rowResults = tableRes.findElements(By.tagName("tr"));						
					matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
					int selOp = ranNumbr(0,nameOp.length-1);
					int selOp2 = ranNumbr(0,nameOp.length-1);
					SendKeys("ctl00_ContentZone_txt_plate_box_data",matriNu);
					Thread.sleep(100);
					SendKeys ("ctl00_ContentZone_txt_officer_box_data",nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_comment_box_data","Se ha creado una Expedición para las Bolsas: ");
						for (int i=2;i<=rowResults.size();i++) {
							if (i==rowResults.size()) {
								SendKeys("ctl00_ContentZone_txt_comment_box_data",getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[1]"));
							}else{
								SendKeys("ctl00_ContentZone_txt_comment_box_data",getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[1]")+", ");
							}
							Thread.sleep(100);
						}
						Thread.sleep(500);			
						
						//Paso 7.- Hacer click en el botón Confirmar
						elementClick ("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(500);
						if (isAlertPresent()) {
							driver.switchTo().alert().accept();
						}
						Thread.sleep(8000);						
						if (driver.getPageSource().contains("Crear expedición")) {
							errorText = getText("ctl00_LblError");
							takeScreenShot("E:\\Selenium\\","expedicionErr"+timet+".jpeg");
							takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","expedicionErr.jpeg");
							actualResults.set(5,"No se ha podido crear la Expedición debido a: "+errorText);
							executionResults.set(5,"Fallado");
							stepNotExecuted = executionNumber.size()-1;						
							summaryBug = "No se ha podido crear la Expedición debido a un error";
							erroTextBug = "No se ha podido crear la Expedición debido a: "+errorText;
							componentBug = "HM";
							severityBug = 1;
							priority = 4;
							testFailed = true;
							bugAttachment = true;
							pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\expedicionErr.jpeg";						
							driver.close();
							testLink();
							System.err.println("No se ha podido crear la Expedición debido a: "+errorText);
							System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
							fail("No se ha podido crear la Expedición debido a: "+errorText);																		
						}						
						actualResults.set(5,"Se ha creado una Expedición con un total de: "+totalExpedido);
						driver.close();
						testLink();
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw (e);
				}

			}
}