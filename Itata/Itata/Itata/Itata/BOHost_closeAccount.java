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

public class BOHost_closeAccount{
			 private static boolean accountClosed = false;
			 private static boolean unableClose; 	
			 private static boolean otherReason = false;
			 private static int NumbVeh;
			 private static int amount=0;
			 private static String amountValue;
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
			public void closeAccountInit() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);
				testPlanReading(9,0,2,3);
				Thread.sleep(1000);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				closeAccount();
				Thread.sleep(200);
				if (accountClosed==true){
					actualResults.set(5, "No se puede cerrar la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;
															
				}
				if (unableClose==true){
					if (NumbVeh>0 && amount!=0) {
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículos asignado/s y un saldo de: "+amountValue);
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículos asignado/s y un saldo de: "+amountValue);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
						return;					
					}
					if (NumbVeh>0) {
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículos asignado/s");
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículos asignado/s");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
						return;
					}
					if (amount!=0) {
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene un saldo de: "+amountValue);
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene un saldo de: "+amountValue);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
						return;						
						
					}
				}
				if (otherReason==true) {
					actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" debido a: "+errorText);
					for (int i=7;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede cerrar la cuenta "+accountNumbr+" debido a: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;
				}
				actualResults.set(8, "Se ha cerrado la cuenta "+accountNumbr+" correctamente");
				Thread.sleep(500);
				driver.close();
				testLink();
				System.out.println("Se ha cerrado la cuenta "+accountNumbr+" correctamente");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void closeAccount() throws Exception {
				action = new Actions(driver);			
				try {
					//Paso 1.- Entrar a la página de Login del BO de Itata
					driver.get(BoHostUrl);
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
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);				
					
					//Paso 3.-Ir al Menú Gestión de Cuentas y hacer click en Seleccionar Cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					//Paso 4.- Hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Paso 5.- Buscar y hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					
					//Paso 6.- Hacer click en el botón Cerrar Cuenta
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
										
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					NumbVeh = Integer.parseInt(numberVehicles);				
					Thread.sleep(500);
					
					if (!accountType.equals("CUENTA EXENTA")){
						amountValue = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
						String amountValue1 = amountValue.replace("$","").replace(" ", "").replace(".","");
						amount = Integer.parseInt(amountValue1);					
					}
					if (NumbVeh>0 || amount!=0) {
						unableClose = true;
						return;
					}
					
					
					//Paso 7.- Ya en la pantalla Cierre de cuenta, verificar si se puede cerrar la cuenta
					elementClick("ctl00_ContentZone_BtnCloseAccount");
					Thread.sleep(1000);
					errorText = getText("ctl00_ContentZone_lbl_information");
					if (!errorText.contains("Esta es una cuenta exenta")) {
						if (!errorText.contains("Esta cuenta será cerrada")) {							
							otherReason = true;
							return;
						}
					}
					
					//Paso 8.- Entrar un comentario en el campo Comentario y hacer click al botón Cerrar Cuenta
					SendKeys("ctl00_ContentZone_txtComment","Esta cuenta será cerrada");
					takeScreenShot("E:\\Selenium\\","accountClosePage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosePage.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					
					//Paso 9.- Hacer click en el botón Volver
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(4000);
					takeScreenShot("E:\\Selenium\\","accountClosed"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosed.jpeg");							
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}

			}
}