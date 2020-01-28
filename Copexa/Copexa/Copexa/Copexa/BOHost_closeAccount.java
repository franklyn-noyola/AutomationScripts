package Copexa.Copexa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_closeAccount{
			 private static boolean accountClosed = false;
			 private static boolean unableClose; 	
			 private static boolean otherReason = false;
			 private static int NumbVeh;
			 private static int amount=0;
			 private static int NumbMemb;
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
				configurationSection("Host", testNameSelected, testNameSelected);				
				testPlanReading(8,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				closeAccount();
				Thread.sleep(200);
				if (accountClosed==true){
					actualResults.set(4, "No se puede cerrar la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=5;i<actualResults.size();i++) {
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
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículo/s asignado/s y un saldo de: "+amountValue);
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículo/s asignado/s y un saldo de: "+amountValue);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;													
					}
					if (NumbVeh>0 && NumbMemb>0) {
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículo/s asignado/s y "+NumbMemb+" miembros asignados");
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículo/s asignado/s y "+NumbMemb+" miembros asignados");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
					
					if (NumbVeh>0) {
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículo/s asignado/s");
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbVeh+" vehículo/s asignado/s");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;						
					}
					
					if (NumbMemb>0) {
						actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbMemb+" miembros asignado/s");
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede cerrar la cuenta "+accountNumbr+" porque la cuenta tiene "+NumbMemb+" miembros asignado/s");
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
				Thread.sleep(500);
				actualResults.set(8, "Se ha cerrado la cuenta "+accountNumbr+" correctamente");
				driver.close();
				testLink();				
				System.out.println("Se ha cerrado la cuenta "+accountNumbr+" correctamente");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void closeAccount() throws Exception {
				action = new Actions(driver);			
				try {
					//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOHostCPaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOHostCPPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						TestLink.TestLinkExecution.testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					//Paso 2.- 'Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					takeScreenShot("E:\\Selenium\\","homeBOCPPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCPPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Paso 3.- Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta
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
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					
					//Pasp 6.- Hacer click en el botón Cerrar Cuenta
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					if (accountType.equals("CUENTA EXENTA")) {
						String numberMembers= getText("ctl00_ContentZone_lbl_members");
						NumbMemb = Integer.parseInt(numberMembers);
					}					
					NumbVeh = Integer.parseInt(numberVehicles);
					
					Thread.sleep(500);
					
					if (!accountType.equals("CUENTA EXENTA")){
						amountValue = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
						String amountValue1 = amountValue.replace("$","").replace(" ", "").replace(".","");
						amount = Integer.parseInt(amountValue1);					
					}
					if (NumbVeh>0 || amount!=0 || NumbMemb>0) {
						unableClose = true;
						return;
					}
					//Paso 7.- Ya en la pantalla Cierre de cuenta, verificar si se puede cerrar la cuenta
					elementClick("ctl00_ContentZone_BtnCloseAccount");
					Thread.sleep(1000);
					if (NumbVeh>0 || amount!=0 || NumbMemb>0) {
						unableClose = true;
						return;
					}
					errorText = getText("ctl00_ContentZone_lbl_information");
					if (!errorText.contains("Esta cuenta será cerrada")) {							
							otherReason = true;
							return;
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