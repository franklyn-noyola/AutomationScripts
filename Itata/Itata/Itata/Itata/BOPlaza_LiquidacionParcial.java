package Itata;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;


public class BOPlaza_LiquidacionParcial{
			 private static String liquidacionErr;
			 public String testNameSelected = this.getClass().getSimpleName();
	
			@Before
			public void setup() throws Exception{
					setUp();
			}

			@After
			public void teardown() throws Exception{			  
				    tearDown();
			}

			@Test
			public void LiquidacionParcial() throws Exception {
				action = new Actions(driver);
				configurationSection("Plaza",testNameSelected,testNameSelected);
				testPlanReading(15,0,2,3);				
				Thread.sleep(1000);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");			
				try {
					//Paso 1.- Entrar a la página de Login del BO de Itata
					driver.get(BoPlazaUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpeg");
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
					
					//Paso 3.- Ir a la Pantalla de Liquidación Parcial (Haciendo click en Gestión de cobradores y después Liquidación Parcial)
					action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
					
					pageSelected = "Liquidación parcial";	
					elementClick(pageSelected);
					takeScreenShot("E:\\Selenium\\","loginLiParcialHoPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginLiParcialPage.jpeg");				
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					
					//Paso 4.- Ya en la Pantalla de Liquidación Parcial, llenar todos los campos pertinentes
					String bagNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Seal_box_data")).getAttribute("value");
					String turnoNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
					String liquidaNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");
					SendKeys("ctl00_ContentZone_NumberCASH01N20000_1",String.valueOf(ranNumbr(1,5)));
					SendKeys("ctl00_ContentZone_NumberCASH01N10000_1",String.valueOf(ranNumbr(1,5)));
					SendKeys("ctl00_ContentZone_NumberCASH01N5000_1",String.valueOf(ranNumbr(1,5)));
					SendKeys("ctl00_ContentZone_NumberCASH01N2000_1",String.valueOf(ranNumbr(1,10)));
					SendKeys("ctl00_ContentZone_NumberCASH01N1000_1",String.valueOf(ranNumbr(1,20)));
					SendKeys("ctl00_ContentZone_NumberCASH01C500_1",String.valueOf(ranNumbr(1,40)));
					SendKeys("ctl00_ContentZone_NumberCASH01C100_1",String.valueOf(ranNumbr(1,100)));
					SendKeys("ctl00_ContentZone_NumberCASH01C50_1",String.valueOf(ranNumbr(1,400)));
					SendKeys("ctl00_ContentZone_NumberCASH01C10_1",String.valueOf(ranNumbr(1,1000)));
					SendKeys("ctl00_ContentZone_NumberCASH01C5_1",String.valueOf(ranNumbr(1,1500)));
					SendKeys("ctl00_ContentZone_NumberCASH01C1_1",String.valueOf(ranNumbr(1,2000)));
					Thread.sleep(200);
					takeScreenShot("E:\\Selenium\\","DataFilled"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilled.jpeg");
					
					//Paso 5.- Una vez llenado todos los campos, hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					
					//Paso 6.- Hacer click en Aceptar del popup
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(15000);
					if (driver.getPageSource().contains("Hubo un error en la") || driver.getPageSource().contains("La operación ha fallado por un error") || driver.getPageSource().contains("Error de sistema")) {
						liquidacionErr=getText("ctl00_LblError");
						actualResults.set(5, "No se puede hacer una liquidación Parcial, debido a: "+liquidacionErr);
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
						System.out.println("No se puede hacer una liquidación Parcial, debido a: "+liquidacionErr);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("No se puede hacer una liquidación Parcial, debido a: "+liquidacionErr);
					}
					Thread.sleep(8000);					
					for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
					String title = getText("LblTitle");					
					assertEquals(title, "Liquidación parcial");					
					takeScreenShot("E:\\Selenium\\","LiquidacionParcialPDF"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionParcialPDF.jpeg");
					Thread.sleep(3000);
					actualResults.set(5, "Se ha creado Liquidación Parcial No. "+liquidaNumbr+" con la Bolsa "+bagNumbr+" y el turno "+turnoNumbr+" correctamente");
					driver.close();
					testLink();
					System.out.println("Se ha creado Liquidación Parcial No. "+liquidaNumbr+" con la Bolsa "+bagNumbr+" y el turno "+turnoNumbr+" correctamente");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
				
				
			}

	}