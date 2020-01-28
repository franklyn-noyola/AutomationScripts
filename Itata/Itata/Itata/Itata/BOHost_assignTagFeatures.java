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

public class BOHost_assignTagFeatures{
			 private static boolean NumbVehC = false;
			 private static boolean TagAssigned = false;
			 private static String [] optionsTag = {"Asignación", "Devolución", "Suspensión", "Desaparecido"};			 
			 private static boolean accountClosed = false;
			 private static int selOption;
			 private static boolean noTag = false;			 
			 private static int vehCheck;
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
			public void assignTagFeatureInit() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);				
				testPlanReading(4,0,2,3);				
				Thread.sleep(1000);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				assignTagFeature();
				Thread.sleep(1000);
		
				if (accountClosed){
					actualResults.set(4, "No se puede realizar ninguna "+optionsTag[selOption]+" de Tag en la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede realizar ninguna "+optionsTag[selOption]+" de Tag en la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;							
				}
				if (NumbVehC){
					actualResults.set(5, "No se puede realizar ninguna acción de Tag en la cuenta "+accountNumbr+" porque no hay vehículo asociado a la cuenta");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();										
					System.out.println("No se puede realizar ninguna acción de Tag en la cuenta "+accountNumbr+" porque no hay vehículo asociado a la cuenta");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;	
				}
				if (TagAssigned){
					actualResults.set(7, "ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr+", "+confirmationMessage);
					driver.close();
					testLink();
					System.out.println("ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr+", "+confirmationMessage);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				if (noTag){
					actualResults.set(7, "No se puede realizar ninguna "+optionsTag[selOption]+" de Tag en la cuenta "+accountNumbr+" porque no hay tag asignado al vehículo con matricula "+matriNu);
					driver.close();
					testLink();
					System.out.println("No se puede realizar ninguna "+optionsTag[selOption]+" de Tag en la cuenta "+accountNumbr+" porque no hay tag asignado al vehículo con matricula "+matriNu);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				
				Thread.sleep(1000);
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void assignTagFeature() throws Exception {
				action = new Actions(driver);
				selOption = ranNumbr(0,optionsTag.length-1);
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
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					Thread.sleep(500);	
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					int NumbVeh = Integer.parseInt(numberVehicles);
					
					//Step 6.- Hacer Click en el botón Tarjetas prepago
					if (NumbVeh==0){
						NumbVehC = true;
						return;
					}else{
						elementClick("ctl00_ContentZone_BtnSmarts");
						takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
						Thread.sleep(1000);
						vehCheck = ranNumbr(0,NumbVeh-1);	
						if (vehCheck<0) {
							vehCheck=0;
						}
						
						//Step 7.- Seleccionar cualquier vehículo que se encuentre en la lista
						int selVeh = vehCheck+2;
						elementClick("ctl00_ContentZone_chk_"+vehCheck);
						matriNu=getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[2]");
						tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[6]");
						
						//Step 8.- Elegir una opción correspondiente: Asignación, Devolución, Suspensión o Desaparecido.
						if (optionsTag[selOption].equals("Asignación")) {					
							Thread.sleep(500);							
							elementClick("ctl00_ContentZone_btn_token_assignment");
							Thread.sleep(500);
							SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
							Thread.sleep(500);
							elementClick("ctl00_ContentZone_btn_init_tag");
							Thread.sleep(1500);
							confirmationMessage = getText("ctl00_ContentZone_lbl_information");
							if (confirmationMessage.contains("ya tiene un título asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
								TagAssigned = true;
								takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
								takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
								return;
							}else{
								tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[6]");							
								}
							actualResults.set(7, "Se ha asignado el tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
							System.out.println("Se ha asignado el tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
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
							System.out.println("Se ha hecho una devolución con el estado "+devStat+" al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
							return;
						}
						
						if (optionsTag[selOption].equals("Suspensión")) {							
								String suspendStat = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[7]");
								elementClick("ctl00_ContentZone_btn_token_suspension");
								Thread.sleep(1000);
								elementClick("ctl00_ContentZone_btn_susp_not");						
								Thread.sleep(1000);
								if (suspendStat.equals("Suspendido")){
									actualResults.set(7,"Se ha hecho una Activación al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha hecho una Activación al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								}else {
									actualResults.set(7, "Se ha hecho una Suspensión al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
									System.out.println("Se ha hecho una Suspensión al tag No."+tagIdNmbr+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");									
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
								actualResults.set(7, "Se ha hecho Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" y Perdido por el Cliente al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								System.out.println("Se ha hecho Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" y Perdido por el Cliente al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
							}else {
								actualResults.set(7, "Se ha hecho Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");
								System.out.println("Se ha hecho Desaparecido un tag con el No."+tagIdNmbr+" en estado: "+statString+" al vehiculo con la matricula: "+matriNu+" de la cuenta "+accountNumbr+" correctamente");									
							}
							return;
						}
						
						takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
						Thread.sleep(1000);
					}
					
				}catch (Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
	}