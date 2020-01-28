package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class CAC_LiquidacionParcial{
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
			public void accountLiquidacionParcialInit() throws Exception {
				configurationSection("CAC",testNameSelected,testNameSelected);				
				testPlanReading(10,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				accountLiquidacionParcial();
				Thread.sleep(1000);							
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}

			public static void accountLiquidacionParcial() throws Exception {
				action = new Actions(driver);
				//Paso 1.- 	Entrar a la página de login del CAC de CoviHonduras
				try {
					driver.get(CaCUrl);
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
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Paso 3.- Ir a la Pantalla de Liquidación Parcial
					action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Liquidación parcial";
					elementClick(pageSelected);					
					Thread.sleep(2000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
										
					//Paso 4.- Ya en la Pantalla de Liquidación Parcial, llenar todos los campos pertinentes									
					SendKeys("ctl00_ContentZone_NumberCASH01N50000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_NumberCASH01N10000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_NumberCASH01N5000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_NumberCASH01N2000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_NumberCASH01N1000_1",String.valueOf(ranNumbr(1,10)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N500_1",String.valueOf(ranNumbr(1,20)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N200_1",String.valueOf(ranNumbr(1,50)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N100_1",String.valueOf(ranNumbr(1,100)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01C50_1",String.valueOf(ranNumbr(1,200)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01C20_1",String.valueOf(ranNumbr(1,500)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01C10_1",String.valueOf(ranNumbr(1,1000)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01C5_1",String.valueOf(ranNumbr(1,2000)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCH02201",String.valueOf(ranNumbr(1,5)));
					action.click(driver.findElement(By.id("ctl00_ContentZone_NumberCH02202_txt_formated"))).sendKeys(String.valueOf(ranNumbr(10000,99999))).build().perform();
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCD201",String.valueOf(ranNumbr(1,5)));
					action.click(driver.findElement(By.id("ctl00_ContentZone_NumberCD202_txt_formated"))).sendKeys(String.valueOf(ranNumbr(10000,99999))).build().perform();
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberBD201",String.valueOf(ranNumbr(1,5)));
					action.click(driver.findElement(By.id("ctl00_ContentZone_NumberBD202_txt_formated"))).sendKeys("ctl00_ContentZone_NumberBD202_txt_plain",String.valueOf(ranNumbr(10000,99999))).build().perform();
					Thread.sleep(100);
					action.click(driver.findElement(By.id("ctl00_ContentZone_NumberCD202_txt_formated"))).build().perform();
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","LiquidacionParcialPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionParcialPage.jpeg");
					
					elementClick("ctl00_ContentZone_BtnCalculate");
					Thread.sleep(2000);
					String bagNumber = driver.findElement(By.id("ctl00_ContentZone_txt_Seal_box_data")).getAttribute("value");
					String shiftNumber = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
					String cashout = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");
					String bagTotal = getText("ctl00_ContentZone_LblPIBInBag");
					takeScreenShot("E:\\Selenium\\","LiquidacionParcialPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionParcialPage.jpeg");
	
					//Paso 5.- Una vez llenado todos los campos, hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(500);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
						
					}
					driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);

					if (driver.getPageSource().contains("Detalle liquidación")) {
						errorText = getText("ctl00_LblError");
						takeScreenShot("E:\\Selenium\\","liquidacionParcialErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionParcialErr.jpeg");
						actualResults.set(4,"No se ha podido crear la Liquidación Parcial debido a: "+errorText);
						executionResults.set(4,"Fallado");						
						summaryBug = "No se ha podido crear la Liquidación Parcial";
						erroTextBug = "No se ha podido crear la Liquidación Parcial debido a: "+errorText;
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = true;
						pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\liquidacionParcialErr.jpeg";						
						driver.close();
						testLink();							
						System.err.println("No se ha podido crear la Liquidación Parcial debido a: "+errorText);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("No se ha podido crear la Liquidación Parcial debido a: "+errorText);
					}
					takeScreenShot("E:\\Selenium\\","LiquidacionInvoice"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionInvoice.jpeg");
					actualResults.set(4,"Se muestra una pantalla con un PDF confirmando que se ha creado una Liquidación Parcial con la Bolsa No. "+bagNumber+" del Turno No. "+shiftNumber+" y No. Liquidación "+cashout+" con un Monto total de la bolsa "+bagTotal);
					Thread.sleep(8000);
					
	
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}


}