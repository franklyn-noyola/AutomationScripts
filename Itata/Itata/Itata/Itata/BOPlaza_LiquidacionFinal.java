package Itata;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOPlaza_LiquidacionFinal{
			 private static String liquidacionErr;
			 private static String dateTime;
			 private static List <String> windows;
			 private static String [] bancos = {"Banco Santander-Chile", "Banco Ing-Chile", "Banco Estado", "Itaú-Corpbanca", "BBVA-Chile", "Banco Nova"};
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
			public void LiquidacionFinal() throws Exception {
				configurationSection("Plaza",testNameSelected,testNameSelected);
				testPlanReading(14,0,2,3);				
				action = new Actions(driver);		
				Thread.sleep(1000);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");			
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
					
					//Paso 3.- Ir a la Pantalla de Liquidación Final (Haciendo click en Gestión de cobradores y después Liquidación Final)
					action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
					pageSelected = "Liquidación final";					
					elementClick(pageSelected);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					
					//Paso 4.- Ya en la Pantalla de Liquidación Final, llenar todos los campos pertinentes
					takeScreenShot("E:\\Selenium\\","loginLiFinalHoPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginLiFinalPage.jpeg");
					String bagNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Seal_box_data")).getAttribute("value");
					String turnoNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
					String liquidaNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");
					if (driver.getPageSource().contains("Fecha/Hora de la última liquidación")) {
						String dateT = driver.findElement(By.id("ctl00_ContentZone_txt_LastCashoutTime_box_data")).getAttribute("value");
						dateTime = " en fecha: "+dateT;
					}else {
						dateTime="";
					}

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
					Thread.sleep(300);
					if (ranNumbr(0,1)>0) {
						elementClick("ctl00_ContentZone_NumberCH02");
						Thread.sleep(100);
						elementClick("ctl00_ContentZone_BtnCreateCheque");
						Thread.sleep(100);
						int bankSel = ranNumbr(0,bancos.length-1);
						selOp = ranNumbr(0,nameOp.length-1);
						SendKeys("ctl00_ContentZone_txtPnlBank_box_data", bancos[bankSel]);
						SendKeys("ctl00_ContentZone_txtPnlPage_box_data", String.valueOf(ranNumbr(100000,99999999)));
						SendKeys ("ctl00_ContentZone_txtPnlName_box_data",nameOp[selOp]+" "+lastNameOp[selOp]);
						SendKeys("ctl00_ContentZone_txtPnlRUT_box_data", String.valueOf(ranNumbr(1000000,99999999)));
						SendKeys("ctl00_ContentZone_txtPnlPhone_box_data", String.valueOf(ranNumbr(600000000,699999999)));
						int matNum = ranNumbr(5000,9999);
						int matlet = ranNumbr(0,matletT.length()-1);
						int matlet1 = ranNumbr(0,matletT.length()-1);
						int matlet2 = ranNumbr(0,matletT.length()-1);
						matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
						SendKeys("ctl00_ContentZone_txtPnlPlate_box_data",matriNu);
						SendKeys("ctl00_ContentZone_txtPnlAmount_box_data", String.valueOf(ranNumbr(1000,200000)));
						Thread.sleep(200);
						elementClick("ctl00_ContentZone_BtnPnlEditDetailChequeSumit");
						Thread.sleep(200);
						elementClick("ctl00_ContentZone_BtnPnlDetailChequeClose");
					}
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","DataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","DataFilled.jpeg");
					
					//Paso 5.- Una vez llenado todos los campos, hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					
					//Paso 6.- Hacer click en Aceptar del popup (aparecería otro popup si la liquidación final no corresponde con todo lo recaudado, hacer click en Aceptar)
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}			
					Thread.sleep(2000);					
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					if (driver.getPageSource().contains("Hubo un error en la") || driver.getPageSource().contains("La operación ha fallado por un error") || driver.getPageSource().contains("Error de sistema")) {
						liquidacionErr=getText("ctl00_LblError");						
						actualResults.set(5,"No se puede hacer una Liquidación Final, debido a: "+liquidacionErr);
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
						System.out.println("No se puede hacer una Liquidación Final, debido a: "+liquidacionErr);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("No se puede hacer una liquidación Final, debido a: "+liquidacionErr);
						
					}
					Thread.sleep(4000);
					windows = new ArrayList<String>();
					for (String wHandle : driver.getWindowHandles()) {
						windows.add(wHandle);												
					}
					
					if (windows.size()==1) {
						errorText = getText("ctl00_LblError");
						takeScreenShot("E:\\Selenium\\","liquidacionFinalErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionFinalErr.jpeg");
						actualResults.set(4,"No se ha podido crear la Liquidación Final debido a: "+errorText);
						executionResults.set(4,"Fallado");						
						summaryBug = "No se ha podido crear la Liquidación Parcial";
						erroTextBug = "No se ha podido crear la Liquidación Parcial debido a: "+errorText;
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
					takeScreenShot("E:\\Selenium\\","LiquidacionFinallPDF"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","LiquidacionFinalPDF.jpeg");
					Thread.sleep(1000);
					actualResults.set(5,"Se ha creado Liquidación Final No. "+liquidaNumbr+" con la Bolsa "+bagNumbr+" y el turno "+turnoNumbr+" correctamente"+dateTime);
					driver.switchTo().window(windows.get(0));
					driver.close();
					Thread.sleep(1000);
					driver.switchTo().window(windows.get(1));
					driver.close();										
					testLink();					
					System.out.println("Se ha creado Liquidación Final No. "+liquidaNumbr+" con la Bolsa "+bagNumbr+" y el turno "+turnoNumbr+" correctamente"+dateTime);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
				
				
			}
						
	}