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

public class BOHost_assignTagToExistingAccount{
			 private static boolean NumbVehC = false;
			 private static boolean TagAssigned = false;
			 private static boolean accountClosed = false;
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
			public void assignTagToExistingAccountInit() throws Exception {
					configurationSection("Host",testNameSelected,testNameSelected);					
					testPlanReading(5,0,2,3);
					applevelTested="Host";									
					borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
					assignTagToExistingAccount();
					Thread.sleep(1000);
				
					if (accountClosed==true){
						actualResults.set(5, "No se puede asignar una Tarjeta Prepago a la cuenta "+accountNumbr+" porque está cerrada");
						for (int i=6;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede asignar una Tarjeta Prepago a la cuenta "+accountNumbr+" porque está cerrada");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
						return;		
					}
					if (NumbVehC==true){
						actualResults.set(6, "No se puede asignar un Tarjeta Prepago a la cuenta "+accountNumbr+" porque no hay vehículo asociado a la cuenta");
						for (int i=7;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede asignar un Tarjeta Prepago a la cuenta "+accountNumbr+" porque no hay vehículo asociado a la cuenta");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));									
						return;
					}
					if (TagAssigned==true){
						actualResults.set(9, "ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr+", "+confirmationMessage);										
						driver.close();
						testLink();
						System.out.println("ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr+", "+confirmationMessage);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
					Thread.sleep(1000);
					actualResults.set(9, "Se le asignado el PAN No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");										
					driver.close();
					testLink();
					System.out.println("Se le asignado el PAN No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void assignTagToExistingAccount() throws Exception {
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
					Thread.sleep(1000);
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeItataPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeItataPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Paso 3.- Ir al Menú Gestión de Cuentas y hacer click la opción Seleccionar Cuenta
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
					
					//Step 4.- Hacer Click en el botón Buscar
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Step 5.- Buscar y hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					
					//Step 6.- Hacer Click en el botón Tarjetas prepago
					//Paso 7.- Ya en la pantalla Tarjeta Prepago, hacer click en cualquier vehículo asignado
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					Thread.sleep(500);	
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					int NumbVeh = Integer.parseInt(numberVehicles);
					if (NumbVeh==0){
						NumbVehC = true;
						return;
					}else{
						elementClick("ctl00_ContentZone_BtnSmarts");
						takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jepg");
						Thread.sleep(1000);
						int vehCheck = ranNumbr(0,NumbVeh-1);
						if (vehCheck<0) {
							vehCheck =0;
						}
						int rowSel = vehCheck+2;
						elementClick("ctl00_ContentZone_chk_"+vehCheck);
						matriNu=getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+rowSel+"]/td[2]");
						Thread.sleep(500);
						
						//Paso 8.- Hacer click en el botón Asignación
						elementClick("ctl00_ContentZone_btn_token_assignment");
						Thread.sleep(500);
						
						//Paso 9.- Escribir un PAN Válido 
						SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
						Thread.sleep(500);
						elementClick("ctl00_ContentZone_btn_init_tag");
						Thread.sleep(1500);
						
						//Paso 10.- Hacer click en el botón Asignar
						confirmationMessage = getText("ctl00_ContentZone_lbl_information");
						if (confirmationMessage.contains("ya tiene un título asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
							TagAssigned = true;
							takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
							takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
							return;
						}else{
							int tabVeh = vehCheck+2;
							tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+tabVeh+"]/td[6]");
							takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
							takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
						}
						
					}
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw (e);
				}
			}

}