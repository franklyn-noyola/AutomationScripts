package Copexa.Copexa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_assignTagFeatures{
			private static String [] tagSelection = {"Tag", "Smart Card"};			
			public static int selOpt;
			private static boolean NumbVehC = false;
			private static boolean TagAssigned = false;
			private static String [] optionsTag = {"Asignación", "Devolución", "Suspensión", "Desaparecido"};			 
			private static boolean accountClosed = false;
			private static int selOption;
			private static boolean noTag = false;			 
			private static int vehCheck;
			private static int NumbVeh;
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
			public void assignTagFeatureInit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);				
				selOpt = ranNumbr(0,1);	
				testPlanReading(7,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				assignTagFeature();
				Thread.sleep(1000);
				
				if (accountClosed){
					actualResults.set(4, "No se puede realizar ninguna "+optionsTag[selOption]+" en la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede realizar ninguna "+optionsTag[selOption]+" en la cuenta "+accountNumbr+" porque está cerrada");					
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;					
					
				}
				if (NumbVehC){
					actualResults.set(5, "No se puede realizar ninguna acción de "+tagSelection[selOpt] +" en la cuenta "+accountNumbr+" porque no hay vehículo/miembro asociado a la cuenta");
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede realizar ninguna acción de "+tagSelection[selOpt] +" en la cuenta "+accountNumbr+" porque no hay vehículo/miembro asociado a la cuenta");					
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;										
				}
				
				if (TagAssigned){
					actualResults.set(7, "ERROR AL ASIGNAR TAG/SMART CARD a la cuenta: "+accountNumbr+", "+confirmationMessage);					
					driver.close();					
					testLink();
					System.out.println("ERROR AL ASIGNAR TAG/SMART CARD a la cuenta: "+accountNumbr+", "+confirmationMessage);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				if (noTag){
					if (optionsTag[selOption].equals("Tag")) {						
						actualResults.set(7,"No se puede realizar ninguna de "+optionsTag[selOption]+" en la cuenta "+accountNumbr+" porque no hay Tag asignado al vehículo con matricula "+matriNu);
						driver.close();
						testLink();
						System.out.println("No se puede realizar ninguna de "+optionsTag[selOption]+" en la cuenta "+accountNumbr+" porque no hay Tag asignado al vehículo con matricula "+matriNu);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}else {
						actualResults.set(7,"No se puede realizar ninguna de "+optionsTag[selOption]+" de Smart Card en la cuenta "+accountNumbr+" porque no hay tag/smart tag asignado al miembro: "+matriNu);
						driver.close();
						testLink();
						System.out.println("No se puede realizar ninguna de "+optionsTag[selOption]+" de Smart Card en la cuenta "+accountNumbr+" porque no hay tag/smart tag asignado al miembro: "+matriNu);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
				}
			
				Thread.sleep(1000);	
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		}
			public static void assignTagFeature() throws Exception{
				action = new Actions(driver);
				try {
					//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(500);			
					
					//Paso 3.- Ir a la pantalla Seleccionar Cuenta (Click en Gestión Cuentas y luego Seleccionar Cuenta)
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(500);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(500);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					takeScreenShot("E:\\Selenium\\","seleccionarCuentaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","seleccionarCuenta.jpeg");					
					if (tagSelection[selOpt].equals("Smart Card")) {
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Exenta");
					}
					//Paso 4.- Hacer click en el botón Buscar
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Paso 5.- Hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					
				
					Thread.sleep(500);
					if (tagSelection[selOpt].equals("Tag")) {
						String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
						NumbVeh = Integer.parseInt(numberVehicles);
					}
					if (tagSelection[selOpt].equals("Smart Card")) {
						String numberVehicles = getText("ctl00_ContentZone_lbl_members");
						NumbVeh = Integer.parseInt(numberVehicles);
					}
					
					//Paso 6.- Si la cuenta seleccionada es PrePago, hacer click en TelePeajes si es Exenta o Telepeajes o Tarjetas Inteligentes
					if (NumbVeh==0){
						NumbVehC = true;
						return;
					}else{
						if (tagSelection[selOpt].equals("Tag")) {
							elementClick("ctl00_ContentZone_BtnTags");
						}
						if (tagSelection[selOpt].equals("Smart Card")) {
							elementClick("ctl00_ContentZone_BtnSmarts");
						} 												
						takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
						Thread.sleep(1000);
						
						vehCheck = ranNumbr(0,NumbVeh-1);	
						if (vehCheck<0) {
							vehCheck=0;
						}
						int selVeh = vehCheck+2;
						//Paso 7.- Seleccionar cualquier vehículo o miembro que se encuentre en la lista
						elementClick("ctl00_ContentZone_chk_"+vehCheck);
						if (tagSelection[selOpt].equals("Tag")) {
							matriNu=getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[2]");
							tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[6]");
						}else {
							matriNu=getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[3]")+" "+getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[4]");
							tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[5]");
						}						
						selOption = ranNumbr(0,optionsTag.length-1);
						
						//Paso 8.- Elegir una opción correspondiente: Asignación, Devolución, Suspensión o Desaparecido.
						if (optionsTag[selOption].equals("Asignación")) {					
							Thread.sleep(500);							
							elementClick("ctl00_ContentZone_btn_token_assignment");
							Thread.sleep(500);
							if (tagSelection[selOpt].equals("Tag")) {
								char letterNumbr = matletT.charAt(ranNumbr(0,matletT.length()-1));
								char letterNumbr2 = matletT.charAt(ranNumbr(0,matletT.length()-1));
								char letterNumbr3 = matletT.charAt(ranNumbr(0,matletT.length()-1));
								char letterNumbr4 = matletT.charAt(ranNumbr(0,matletT.length()-1));
								tagIdNmbr = ""+letterNumbr+letterNumbr2+letterNumbr3+letterNumbr4+ranNumbr(10000000,99999999);
								SendKeys("ctl00_ContentZone_txt_pan_token",tagIdNmbr);
								selectDropDown("ctl00_ContentZone_cmb_issuer_cmb_dropdown");
							}
							if (tagSelection[selOpt].equals("Smart Card")) {
								SendKeys("ctl00_ContentZone_txt_pan_token",String.valueOf(ranNumbr(1,9999999)));
								Thread.sleep(500);
							} 
							elementClick("ctl00_ContentZone_btn_init_tag");
							Thread.sleep(1500);
							confirmationMessage = getText("ctl00_ContentZone_lbl_information");
							if (confirmationMessage.contains("ya tiene un título asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
								TagAssigned = true;
								takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
								takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
								return;
							}else{
								if (tagSelection[selOpt].equals("Smart Card")) {									
									tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[5]");
									System.out.println("Se ha asignado el Smart Card No."+tagIdNmbr+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								}else {
									tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[6]");
									actualResults.set(7, "Se ha asignado el tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha asignado el tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								}
							}							
							return;
							
						}
						if (tagIdNmbr.equals("")) {
							noTag = true;
							return;
						}
						if (optionsTag[selOption].equals("Devolución")) {							
							elementClick("ctl00_ContentZone_btn_token_return");
							Thread.sleep(100);
							WebElement estado = driver.findElement(By.id("ctl00_ContentZone_radio_status"));
							List<WebElement> estatNumbr = estado.findElements(By.tagName("tr"));
							int devSelect = ranNumbr(0,estatNumbr.size()-1);
							if (devSelect<0) {
								devSelect = 0;
							}
							int rowSel = devSelect+1;
							elementClick("ctl00_ContentZone_radio_status_"+devSelect);
							String devStat = getText("//*[@id='ctl00_ContentZone_radio_status']/tbody/tr[+"+rowSel+"]/td/label");
							Thread.sleep(100);
							elementClick("ctl00_ContentZone_btn_apply_return");
							Thread.sleep(1000);
							if (tagSelection[selOpt].equals("Smart Card")) {
								actualResults.set(7, "Se ha hecho una devolución con el estado "+devStat+" a la Tarjeta Inteligente No."+tagIdNmbr+" del Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								System.out.println("Se ha hecho una devolución con el estado "+devStat+" a la Tarjeta Inteligente No."+tagIdNmbr+" del Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
							}else {
								actualResults.set(7, "Se ha hecho una devolución con el estado "+devStat+" al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								System.out.println("Se ha hecho una devolución con el estado "+devStat+" al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
							}
						return;
						}
						
						if (optionsTag[selOption].equals("Suspensión")) {							
								String suspendStat = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[7]");
								elementClick("ctl00_ContentZone_btn_token_suspension");
								Thread.sleep(1000);
								elementClick("ctl00_ContentZone_btn_susp_not");						
								Thread.sleep(1000);
								if (suspendStat.equals("Suspendido")){
									if (tagSelection[selOpt].equals("Smart Card")) {
										actualResults.set(7, "Se ha hecho una Activación de la Targeta Inteligente No."+tagIdNmbr+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
										System.out.println("Se ha hecho una Activación de la Targeta Inteligente No."+tagIdNmbr+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									}else {
										actualResults.set(7, "Se ha hecho una Activación al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
										System.out.println("Se ha hecho una Activación al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									}
									
								}else {
									if (tagSelection[selOpt].equals("Smart Card")) {
										actualResults.set(7, "Se ha hecho una Suspensión de la Targeta Inteligente No."+tagIdNmbr+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
										System.out.println("Se ha hecho una Suspensión de la Targeta Inteligente No."+tagIdNmbr+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									}else {
										actualResults.set(7, "Se ha hecho una Suspensión al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
										System.out.println("Se ha hecho una Suspensión al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									}																		
								}								
								return;
						}
						
						if (optionsTag[selOption].equals("Desaparecido")) {												
							elementClick("ctl00_ContentZone_btn_token_stolen");
							Thread.sleep(500);
							int stat = ranNumbr(0,1);
							if (stat<0) {
								stat = 0;
							}
							String statString;
							if (stat==1) {
								statString = "perdido";
							}else {
								statString = "robado";
							}
							elementClick("ctl00_ContentZone_radio_stolen_"+stat);
							Thread.sleep(500);
							int lostCustomer = 0;
							if (ranNumbr(0,1)==0) {
								elementClick("ctl00_ContentZone_chk_lostbycustomer");
								lostCustomer = 1;
							}							
							Thread.sleep(500);
							elementClick("ctl00_ContentZone_btn_stolen");						
							Thread.sleep(1000);
							String nextPage = getText("ctl00_SectionZone_LblTitle");
							if (nextPage.contains("Detalles del pago")) {
								elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
								paymentfromCustomerEsp();
							}
							if (lostCustomer==1){
								if (tagSelection[selOpt].equals("Smart Card")) {
									actualResults.set(7, "Se ha Desaparecido una Targeta Inteligente con el No."+tagIdNmbr+" en estado: "+statString+" y Perdido por el Cliente al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha Desaparecido una Targeta Inteligente con el No."+tagIdNmbr+" en estado: "+statString+" y Perdido por el Cliente al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");	
								}else {
									actualResults.set(7, "Se ha Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" y Perdido por el Cliente al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" y Perdido por el Cliente al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								}
							}else {
								if (tagSelection[selOpt].equals("Smart Card")) {
									actualResults.set(7, "Se ha hecho Desaparecido una Tarjeta Inteligente con el No."+tagIdNmbr+" en estado: "+statString+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha Desaparecido una Tarjeta Inteligente con el No."+tagIdNmbr+" en estado: "+statString+" al Miembro: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");	
								}else {
									actualResults.set(7, "Se ha Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								}
																	
							}
							return;
						}
						
						takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
						Thread.sleep(1000);
					}
					
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw (e);
				}
			}

}
