package Copexa.Copexa;


import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Copexa.Copexa.BOHost_accountCreationOnly.*;
import static Copexa.Copexa.BOHost_accountCreationWithVehicle.*;
import static Copexa.Copexa.BOHost_accountCreationWithMember.*;

public class BOHost_accountCreationAssigningTag {
			private static String [] tagSelection = {"Tag", "Smart Card"};			
			public static int selOpt;
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
			public void accountCreationAssigningTagInit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);
				memberMode="Crear";
				Actions action = new Actions(driver);			
				testPlanReading(3,0,2,3);
				selOpt = ranNumbr(0,1);				
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				if (tagSelection[selOpt].equals("Tag")) {
					creaciondeCuenta();
				}
				if (tagSelection[selOpt].equals("Smart Card")) {
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}						
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(500);			
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(500);
					action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();												
					cuentaExenta();
				} 
				
				Thread.sleep(200);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");// Guardar Cuenta con el botón
				Thread.sleep(200);
				if (tagSelection[selOpt].equals("Tag")) {
					accountCreationWithVehicle();
				}
				if (tagSelection[selOpt].equals("Smart Card")) {			
					elementClick("ctl00_ContentZone_BtnMembers");
					Thread.sleep(200);
					accountCreationWithMember();
				} 				
				Thread.sleep(500);						
				accountCreationAssigningTag();
				Thread.sleep(1000);
				if (errorTagAssignment){
					if (tagSelection[selOpt].equals("Smart Card")) {
						actualResults.set(15, "Smart Card Invalido: No se puede asignar un Smart Card al miembro "+nameOp[selOp]+" "+lastNameOp[selOp]+" de la cuenta "+accountNumbr);
					}else {
						actualResults.set(15, "Tag Invalido: No se puede asignar un Tag al Vehiculo "+matriNu+" de la cuenta "+accountNumbr);
					}					
					System.out.println("ERROR AL ASIGNAR TAG O SMART CARD a la cuenta: "+accountNumbr+", "+confirmationMessage);
					driver.close();
					testLink();
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));;
					return;
				}
				if (tagSelection[selOpt].equals("Tag")) {
					actualResults.set(15,"Se ha creado la cuenta: "+accountNumbr+" con un Vehiculo con la matricula "+matriNu+" y el tag asignado No.: "+ tagIdNmbr);
					System.out.println("Se ha creado la cuenta: "+accountNumbr+" con un Vehiculo con la matricula "+matriNu+" y el tag asignado No.: "+ tagIdNmbr);
				}
				if (tagSelection[selOpt].equals("Smart Card")) {
					actualResults.set(15,"Se ha creado la cuenta: "+accountNumbr+" con el Miembro: "+nameOp[selOp]+" "+lastNameOp[selOp]+"  y la tarjeta inteligente asignada No.: "+ tagIdNmbr);
					System.out.println("Se ha creado la cuenta: "+accountNumbr+" con el Miembro: "+nameOp[selOp]+" "+lastNameOp[selOp]+"  y la tarjeta inteligente asignada No.: "+ tagIdNmbr);
				}
				Thread.sleep(3000);
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));;
			}

			public static void accountCreationAssigningTag() throws Exception {
				Thread.sleep(2000);
				//Paso 12.- Hacer Click en el botón Telepeajes o Smart Card
				if (tagSelection[selOpt].equals("Tag")) {
					elementClick("ctl00_ContentZone_BtnTags");
				}
				if (tagSelection[selOpt].equals("Smart Card")) {
					elementClick("ctl00_ContentZone_BtnSmarts");
				} 
				takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");

				//Paso 13.- Seleccionar el Vehículo o Miembro recién creado
				Thread.sleep(500);
				
				if (tagSelection[selOpt].equals("Smart Card")) {
					matriNu = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[3]")+" "+getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[4]");
				}
				
				elementClick("ctl00_ContentZone_chk_0");
				Thread.sleep(500);
				
				//Paso 14.- Hacer click en el botón Asignación
				elementClick("ctl00_ContentZone_btn_token_assignment");
				Thread.sleep(500);
				
				//Paso 15.- Escribir un tag o Smart Card válido 
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
				
				Thread.sleep(1000);
				//Paso 16.- Hacer click en el botón Asignar
				elementClick("ctl00_ContentZone_btn_init_tag");
				Thread.sleep(500);
				confirmationMessage = getText("ctl00_ContentZone_lbl_operation");				
				if (driver.getPageSource().contains("Error:")) {
					confirmationMessage = getText("ctl00_ContentZone_lbl_operation");
				}
				if (driver.getPageSource().contains("ya tiene un tag asignado")) {
					confirmationMessage = getText("ctl00_ContentZone_lbl_information");
				}				
				
				if (confirmationMessage.contains("ya tiene un tag asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto") || confirmationMessage.contains("Este título ya está asignado al miembro")){
					errorTagAssignment = true;
					takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
				}else{
					if  (tagSelection[selOpt].equals("Smart Card")) {
						tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[5]");
					}else {
						tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]");
					}
					
					takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
				}
				Thread.sleep(1000);				
			}
	}		