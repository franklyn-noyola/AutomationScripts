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

public class BOHost_assignPersonaAutorizadaCuenta{
			 private static String [] optionsToSelect = {"Crear", "Modificar", "Eliminar"};			 
			 private static boolean accountClosed = false;
			 public static boolean NumbPerC=false;
			 public static int selOption;
			 public static String optionSelected;
			 public static String Nombre1;
			 public static String Apellido1;
			 private String testNameSelected = this.getClass().getSimpleName();
	
			@Before
			public void seuUp() throws Exception{
					setUp();
			}

			@After
			public void teardown() throws Exception{			  
				    tearDown();

			}
			
			@Test
			public void assignPersonaAutorizadaInit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);				
				Thread.sleep(1000);				
				applevelTested="Host";				
				testPlanReading(5,0,2,3);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				selOption = ranNumbr(0,2);
				optionSelected = optionsToSelect[selOption];
				assignPersonaAutorizada();
				Thread.sleep(1000);
				if (NumbPerC) {
					actualResults.set(7, "No se puede "+optionSelected+" en la cuenta "+accountNumbr+" porque no tiene Personas creadas");
					for (int i=8;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede "+optionSelected+" en la cuenta "+accountNumbr+" porque no tiene Personas creadas");
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
				if (optionSelected.equals("Crear")) {
					actualResults.set(10, "Se ha creado la Persona Autorizada "+sexSelcEsp[selOp]+" "+nameOp[selOp]+" "+lastNameOp[selOp]+" en la cuenta "+accountNumbr+" correctamente");
					System.out.println("Se ha creado la Persona Autorizada "+sexSelcEsp[selOp]+" "+nameOp[selOp]+" "+lastNameOp[selOp]+" en la cuenta "+accountNumbr+" correctamente");
				}
				if (optionSelected.equals("Modificar")) {
					actualResults.set(10, "Se ha Modificado la Persona Autorizada "+Nombre1+" "+Apellido1+" por "+nameOp[selOp]+" "+lastNameOp[selOp]+" en la cuenta "+accountNumbr+" correctamente");
					System.out.println("Se ha Modificado la Persona Autorizada "+Nombre1+" "+Apellido1+" por "+nameOp[selOp]+" "+lastNameOp[selOp]+" en la cuenta "+accountNumbr+" correctamente");
				}
				if (optionSelected.equals("Eliminar")) {
					actualResults.set(10, "Se ha Eliminado la Persona Autorizada "+Nombre1+" "+Apellido1+" en la cuenta "+accountNumbr+" correctamente");
					System.out.println("Se ha Eliminado la Persona Autorizada "+Nombre1+" "+Apellido1+" en la cuenta "+accountNumbr+" correctamente");
				}
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}
			
			public static void assignPersonaAutorizada() throws Exception {
				action = new Actions(driver);
				//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginCopexaPage"+timet+".jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}	
				//Paso 2.- Loguearse con el usuario 00001/00001
				loginPage("00001","00001");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","homeCopexaHomePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCopexaHomePage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(2000);					
				
				//Paso 3.- Ir a la pantalla Seleccionar Cuenta (Click en Gestión Cuentas y luego Seleccionar Cuenta)
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);
				pageSelected="Seleccionar cuenta";
				elementClick(pageSelected);
				Thread.sleep(2000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				
				//Paso 4.- Hacer click en el botón Buscar
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				
				//Paso 5.- Hacer click en cualquier cuenta
				selectAccount();
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));				
				accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
				actualResults.set(4, "Se ha seleccionado la cuenta No."+accountNumbr+" de una "+accountType);
				if(driver.getPageSource().contains("CUENTA CERRADA")){
					accountClosed = true;
					return;
				}
				Thread.sleep(500);	
				String numberPersonas = getText("ctl00_ContentZone_lbl_authpers");
				int NumbPer = Integer.parseInt(numberPersonas);
				
				//Paso 6.- Hacer click en el botón Editar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				
				//Paso 7.- Hacer click en el boton Pers. Autoriz.
				elementClick("ctl00_ContentZone_BtnAuthPers");					
				if (optionSelected.equals("Modificar") || optionSelected.equals("Eliminar")) {
					if (NumbPer==0){
						NumbPerC = true;
						return;
					}
				}							
				if (optionSelected.equals("Modificar") || optionSelected.equals("Eliminar")) {
					int perCheck = ranNumbr(0,NumbPer-1);
					if (perCheck<0) {
						perCheck = 0;
					}
					elementClick("ctl00_ContentZone_radio"+perCheck);
					Nombre1 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+perCheck+2+"]/td[4]");
					Apellido1 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+perCheck+2+"]/td[5]");
				}
				
				//Paso 9.- Si se ha elegido Crear o Editar, llenar o modificiar todos los campos correspondientes y hacer click en el botón Confirmar, si se ha elegido Eliminar hacer click en el botón Aceptar del PopUp
				switch (optionSelected) {
					case "Crear":				actualResults.set(7, "Se ha cliqueado en la opción "+optionSelected+" para crear una nueva Persona Autorizada");
												elementClick("ctl00_ContentZone_BtnCreate");
												break;
					case "Modificar":			actualResults.set(7, "Se ha cliqueado en la opción "+optionSelected+" para modificar la persona autorizada "+Nombre1+" "+Apellido1);
												elementClick("ctl00_ContentZone_BtnModify");
												break;
					case "Eliminar":			actualResults.set(7, "Se ha cliqueado en la opción "+optionSelected+" para eliminar la persona autorizada "+Nombre1+" "+Apellido1);			
												elementClick("ctl00_ContentZone_BtnDelete");
												if (isAlertPresent()) {
													driver.switchTo().alert().accept();
												}
												break;
				}
				if (optionSelected.equals("Modificar") || optionSelected.equals("Crear")) {
					selOp = ranNumbr(0,nameOp.length-1);					
						if (optionSelected.equals("Modificar")) {
							String newName = nameOp[selOp];
							while (newName.equals(Nombre1)) {
								selOp = ranNumbr(0,nameOp.length-1);
								newName = nameOp[selOp];
							}
						}					
					takeScreenShot("E:\\Selenium\\","PersonaAutorizadaMainPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","PersonaAutorizadaPage.jpeg");
					SendKeys("ctl00_ContentZone_tbAuthPersTitle", sexSelcEsp[selOp]);
					SendKeys("ctl00_ContentZone_tbForename",nameOp[selOp]);
					SendKeys("ctl00_ContentZone_tbSurname",lastNameOp[selOp]);
					SendKeys("ctl00_ContentZone_tbRelationship","Compañero de Trabajo");
					elementClick("ctl00_ContentZone_BtnSubmit");
				}
				takeScreenShot("E:\\Selenium\\","PersonaAutorizadaDataPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","PersonaAutorizadaDataPage.jpeg");
				Thread.sleep(100);
				
				//Paso 10.- Hacer click en el botón Volver
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				
				//Paso 11.- Hacer click en el botón Guardar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
			}

	}