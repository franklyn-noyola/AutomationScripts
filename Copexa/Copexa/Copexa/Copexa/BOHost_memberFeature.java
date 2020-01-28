package Copexa.Copexa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_memberFeature {
			 private static String [] optionsToSelect = {"Crear", "Modificar", "Eliminar"};			 
			 private static boolean accountClosed = false;
			 public static boolean NumbPerC=false;
			 public static int selOption;
			 public static String optionSelected;
			 public static String Nombre1;
			 public static String Apellido1;
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
			public void memberFeatureInit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);				
				testPlanReading(11,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				selOption = ranNumbr(0,2);
				optionSelected = optionsToSelect[selOption];
				memberFeature();
				Thread.sleep(1000);
				if (NumbPerC) {
					actualResults.set(8, "No se puede "+optionSelected+" en la cuenta "+accountNumbr+" porque no tiene Miembros creados");
					for (int i=9;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede "+optionSelected+" en la cuenta "+accountNumbr+" porque no tiene Miembros creados");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;					
				}
				
				if (accountClosed){
					actualResults.set(5, "No se puede "+optionSelected+" en la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede "+optionSelected+" en la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
										
				}		
				Thread.sleep(1000);
				//Paso 12.- Hacer click el el botón Guardar
				if (optionSelected.equals("Crear")) {
					actualResults.set(11,"Se ha creado el Miembro "+sexSelcEsp[selOp]+" "+nameOp[selOp]+" "+lastNameOp[selOp]+" en la cuenta "+accountNumbr+" correctamente");
				}
				if (optionSelected.equals("Modificar")) {
					actualResults.set(11,"Se ha Modificado el Miembro "+Nombre1+" "+Apellido1+" por "+nameOp[selOp]+" "+lastNameOp[selOp]+" en la cuenta "+accountNumbr+" correctamente");					
				}
				if (optionSelected.equals("Eliminar")) {
					actualResults.set(11,"Se ha Eliminado el Miembro "+Nombre1+" "+Apellido1+" en la cuenta "+accountNumbr+" correctamente");					
				}
				Thread.sleep(1000);
				driver.close();
				testLink();		
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}
			
			public static void memberFeature() throws Exception {
				action = new Actions(driver);
				try {
					
					//Paso 1.- Entrar a la página de Login del BO Host de Copexa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginCopexaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCopexaPage.jpeg");
					Thread.sleep(1000);
					loginPageErr();
					if (pageSelectedErr==true) {
						TestLink.TestLinkExecution.testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearse conel usuario 00001/00001					
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCopexaHomePage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCopexaHomePage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);		
					
					//Paso 3.- 'Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta
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
					//Paso 4.- Seleccionar Exenta en la lista desplegable tipo de Cuenta
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Exenta");
					
					//Paso 5.- Hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Paso 6.- Buscar y hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					Thread.sleep(500);	
					String numberPersonas = getText("ctl00_ContentZone_lbl_members");
					int NumbPer = Integer.parseInt(numberPersonas);
					if (optionSelected.equals("Modificar") || optionSelected.equals("Eliminar")) {
						if (NumbPer==0){
							NumbPerC = true;
							return;
						}
					}
					//Paso 7.- Hacer click en el botón Editar
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");		
					
					//Paso 8.- Ya en la cuenta en modo edición, hacer click en el botón Miembros
					elementClick("ctl00_ContentZone_BtnMembers");				
					if (optionSelected.equals("Modificar") || optionSelected.equals("Eliminar")) {					
						int perCheck = ranNumbr(0,NumbPer-1);
						if (perCheck<0) {
							perCheck = 0;
						}
						elementClick("ctl00_ContentZone_radio"+perCheck);
						int nameSel = perCheck+2;
						Nombre1 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+nameSel+"]/td[4]");
						Apellido1 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+nameSel+"]/td[5]");
					}
					
					//Paso 9.- Hacer click en el botón correspondiente (si es Modificar o Eliminar, seleccionar primero el miembro que esté en la lista)
					//Paso 10.- Si se ha elegido Eliminar (y se ha elegido el meimbro correspondiente), aparecerá un popup si desea eliminar el miembro,  si has elegido Crear irá a la pantalla de creación de miembro, ahi llenar todos los datos correspondientes y dar click al botón Confirmar, si has elegido Modificar, y a seleccionado el miembro correspondientes aparecerá la pantalla de edición del miembro y una vez ahí modificar  los datos correspondientes y dar click el botón Confirmar
					//Paso 11.- Una vez hecho la acción correspondiente hacer click en el botón Atrás
					switch (optionSelected) {
						case "Crear":				memberMode = "Crear";
													BOHost_accountCreationWithMember.accountCreationWithMember();
													break;
						case "Modificar":			memberMode = "Modificar";
													BOHost_accountCreationWithMember.accountCreationWithMember();
													break;
						case "Eliminar":			elementClick("ctl00_ContentZone_BtnDelete");
													if (isAlertPresent()) {
														driver.switchTo().alert().accept();
													}
													Thread.sleep(1000);
													elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
													Thread.sleep(1500);
													elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
													Thread.sleep(2500);													
													break;
					}
				
					Thread.sleep(1000);
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getLocalizedMessage());
					throw(e);
				}
			}
	}