package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOPlaza_LiquidacionFinal{
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
			public void plazaAccountLiquidacionFinalInit() throws Exception {
				configurationSection("Plaza",testNameSelected,testNameSelected);				
				Thread.sleep(1000);
				testPlanReading(11,5,7,8);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				plazaAccountLiquidacionFinal();
				Thread.sleep(1000);					
				driver.close();
				testLink();			
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
			}

			public static void plazaAccountLiquidacionFinal() throws Exception {
				action = new Actions(driver);
				try {
					//Paso 1.- Entrar a la página de login del BackOffice de Plaza de CoviHonduras
					driver.get(BoPlazaUrl);
					takeScreenShot("E:\\Selenium\\","loginBOPlazaCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPlazaCVHPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearte con el usuario 00001/00001					
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeBOPlazaCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOPlazaCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);
					
					//Paso 3.- Ir a la Pantalla de Liquidación Final
					action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
					Thread.sleep(2000);					
					pageSelected = "Liquidación final"; 
					elementClick(pageSelected);
					Thread.sleep(2000);
					plazaPage(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}					
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println(errorText);
						fail(errorText);
					}
					
					//Paso 4.- Ya en la Pantalla de Liquidación Final, llenar todos los campos pertinentes
					int shift = ranNumbr (1,3);
					new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_shiftGroup_cmb_dropdown"))).selectByIndex(shift);					
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N50000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N10000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N5000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_NumberCASH01N2000_1",String.valueOf(ranNumbr(1,4)));
					Thread.sleep(100);
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
					action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_NumberCD202_txt_formated")),String.valueOf(ranNumbr(10000,99999))).build().perform();
					SendKeys("ctl00_ContentZone_NumberCD201",String.valueOf(ranNumbr(1,5)));
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_BtnCalculate");		
					String bagNumber = driver.findElement(By.id("ctl00_ContentZone_txt_Seal_box_data")).getAttribute("value");
					String shiftNumber = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
					String cashout = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");
					String bagTotal = getText("ctl00_ContentZone_LblPIBInBag");					
					takeScreenShot("E:\\Selenium\\","LiquidacionFinalPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionFinalPage.jpeg");
					
					//Paso 5.- Una vez llenado todos los campos, hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
						
					}
					Thread.sleep(8000);
					//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
						
					}					
					Thread.sleep(20000);					
					//driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
					
					
					if (driver.getPageSource().contains("Detalle liquidación")) {
						errorText = getText("ctl00_LblError");
						takeScreenShot("E:\\Selenium\\","liquidacionFinalErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionFinalErr.jpeg");
						actualResults.set(4,"No se ha podido crear la Liquidación Final debido a: "+errorText);
						executionResults.set(4,"Fallado");						
						summaryBug = "No se ha podido crear la Liquidación Final";
						erroTextBug = "No se ha podido crear la Liquidación Final debido a: "+errorText;
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = true;
						pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\liquidacionFinalErr.jpeg";						
						driver.close();
						testLink();
						System.err.println("No se ha podido crear la Liquidación Final debido a: "+errorText);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("No se ha podido crear la Liquidación Final debido a: "+errorText);
					}
					
					takeScreenShot("E:\\Selenium\\","LiquidacionInvoice"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionInvoice.jpeg");
					actualResults.set(4,"se muestra una pantalla con un PDF confirmando que se ha creado una Liquidación Final con la Bolsa No. "+bagNumber+" del Turno No. "+shiftNumber+" y No. Liquidación "+cashout+" con un Monto total de la bolsa "+bagTotal);
					Thread.sleep(5000);
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
	
}		
	



