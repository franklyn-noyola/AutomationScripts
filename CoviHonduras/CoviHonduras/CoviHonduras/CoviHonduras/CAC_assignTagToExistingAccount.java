package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class CAC_assignTagToExistingAccount{
			 private static boolean NumbVehC = false;
			 private static boolean TagAssigned = false;
			 private static boolean accountClosed = false;
			 private static String vehSelected;
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
			public void assignTagToExistingAccountInit() throws Exception {
				configurationSection("CAC",testNameSelected,testNameSelected);
				testPlanReading(6,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				assignTagToExistingAccount();
				Thread.sleep(1000);				
				if (accountClosed){
					actualResults.set(5,"No se puede asignar un Tag a la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					driver.close();
					testLink();
					System.out.println("No se puede asignar un Tag a la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
					//
				}
				if (NumbVehC){
					actualResults.set(5,"No se puede asignar un Tag a la cuenta "+accountNumbr+" porque no tiene vehículo asignado");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					driver.close();
					testLink();
					System.out.println("No se puede asignar un Tag a la cuenta "+accountNumbr+" porque no tiene vehículo asignado");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;

				}
				if (TagAssigned){
					actualResults.set(9,"No se puede asignar tag al Vehículo: "+vehSelected +"de la la cuenta: "+accountNumbr+" debido a: " +confirmationMessage);
					driver.close();
					testLink();
					System.out.println("No se puede asignar tag al Vehículo: "+vehSelected +"de la la cuenta: "+accountNumbr+" debido a: " +confirmationMessage);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
					
				}
				Thread.sleep(1000);
				actualResults.set(9,"Se le asignado el tag No."+tagIdNmbr+" al vehículo: "+vehSelected+" asignado a la cuenta "+accountNumbr+" correctamente");
				driver.close();
				testLink();
				System.out.println("Se le asignado el tag No."+tagIdNmbr+" al vehículo: "+vehSelected+" asignado a la cuenta "+accountNumbr+" correctamente");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void assignTagToExistingAccount() throws Exception {
				Actions action = new Actions(driver);
				try {
					//Paso 1.- Entrar a la página de Login del CAC de CoviHonduras
					driver.get(CaCUrl);
					takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCACCVHPage.jpeg");
					
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- 'Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);
					
					//Paso 3.- Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(2000);
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
					Thread.sleep(1000);
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					Thread.sleep(1000);
					
					//paso 6.- Hacer click en el botón Telepeajes
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					int NumbVeh = Integer.parseInt(numberVehicles);
					if (NumbVeh==0){
						NumbVehC = true;
						return;
					}
					elementClick("ctl00_ContentZone_BtnTags");
					takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
					Thread.sleep(1000);
					int vehCheck;
					if (NumbVeh==1) {
						vehCheck=0;
					}else {
						vehCheck= ranNumbr(0,NumbVeh-1);
					}
					
					//Paso 7.- Ya en la pantalla operaciones de Telepeajes, hacer click en cualquier vehículo asignado 
					elementClick("ctl00_ContentZone_chk_"+vehCheck);
					Thread.sleep(500);
					
					//Paso 8.- Hacer click en el botón Asignación
					elementClick("ctl00_ContentZone_btn_token_assignment");
					Thread.sleep(500);
					
					//Paso 9.- Escribir un Tag Válido
					SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
					Thread.sleep(500);
					
					//Paso 10.- Hacer Click en el botón Asignación
					elementClick("ctl00_ContentZone_btn_init_tag");
					Thread.sleep(1500);
					int tabVeh = vehCheck+2;
					confirmationMessage = getText("ctl00_ContentZone_lbl_information");
					if (confirmationMessage.contains("ya tiene un título asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
						TagAssigned = true;
						vehSelected = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+tabVeh+"]/td[2]");
						takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
						return;	
					}else{
						vehSelected = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+tabVeh+"]/td[2]");
						tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+tabVeh+"]/td[6]");
						takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
					}
					
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}

	}		