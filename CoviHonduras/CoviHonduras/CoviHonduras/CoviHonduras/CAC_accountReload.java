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

public class CAC_accountReload {
			 private static boolean accountClosed = false;
			 private static String Saldo;
			 private static String reloadPayMean;
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
			public void accountReloadInit() throws Exception {
				configurationSection("CAC",testNameSelected,testNameSelected);				
				testPlanReading(7,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
					accountReload();
				Thread.sleep(1000);
				if (accountClosed){
					actualResults.set(5,"No se puede hacer Recarga a la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					driver.close();
					testLink();
					System.out.println("Se ha probado en la versión del "+ getVersion(applevelTested));
					return;									
					
				}
			
				Thread.sleep(1000);
				actualResults.set(11, "Se Recargado la cuenta "+accountNumbr+" correctamente y posee un saldo de: "+Saldo);
				System.out.println("Se Recargado la cuenta "+accountNumbr+" correctamente y posee un saldo de: "+Saldo);
				testLink();
				System.out.println("Se ha probado en la versión del "+ getVersion(applevelTested));
			}

			public static void accountReload() throws Exception {
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
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Paso 3.- Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta;
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
					takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 6.- Hacer click en el botón Recargos
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}	
					elementClick("ctl00_ContentZone_BtnLoads");
					Thread.sleep(1000);	
					int optionclick = ranNumbr(0,3);
					int paySelected = optionclick+1;
					reloadPayMean = getText("//*[@id='ctl00_ContentZone_CtType_radioButtonList_payBy']/tbody/tr["+paySelected+"]/td/label");
					
					//Paso 7.- Ya en la pantalla Pago del Cliente, elije cualquiera de las opciones de medio de pago disponibles
					actualResults.set(6, "Se ha seleccionado el medio de pago: "+reloadPayMean);
					elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+optionclick);
					int optionclick1 = ranNumbr(0,1);
					if (optionclick1==1){
						elementClick("ctl00_ContentZone_CtType_chk_present");
					}
					Thread.sleep(1000);
					
					//Paso 8.- Introducir la cantidad a recargar deseada en el campo Cantidad total
					action.click(driver.findElement(By.id("ctl00_ContentZone_CtType_text_total_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(100000,900000))).build().perform();
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","accountReloadPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountReloadPage.jpeg");
					
					//Paso 9.- Hacer click en el botón Continuar
					elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
					Thread.sleep(3000);
					takeScreenShot("E:\\Selenium\\","accountReloadConfirmationPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountReloadConfirmationPage.jpeg");
					
					//Paso 10.- Dependiendo del medio de pago seleccionado, introducir los valores que se requieren
					switch (optionclick){
						case 0:				//Paso 11.- Hacer click en el botón Finalizar
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
						case 1:				int authNumber = ranNumbr(100000,999999);
											SendKeys("ctl00_ContentZone_CtbyCard_BoxAuthCode_box_data",String.valueOf(authNumber));
											actualResults.set(9,"Se ha seleccioando el medio de Pago "+reloadPayMean+" y se ha introducido el número de autorización: "+authNumber);
											Thread.sleep(500);
											
											//Paso 11.- Hacer click en el botón Finalizar
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
											
						case 2:				int checkNumber = ranNumbr(10000000,9999999);
											SendKeys("ctl00_ContentZone_CtbyCheque_txt_number_box_data",String.valueOf(checkNumber));
											actualResults.set(9,"Se ha seleccioando el medio de Pago "+reloadPayMean+" y se ha introducido el número de cheque: "+checkNumber);
											Thread.sleep(500);
											
											//Paso 11.- Hacer click en el botón Finalizar
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
											
						case 3:				int depBank = ranNumbr(100000,999999);
											SendKeys("ctl00_ContentZone_CtbyDepoBancario_BoxReference_box_data","REF. "+depBank);
											actualResults.set(9,"Se ha seleccioando el medio de Pago "+reloadPayMean+" y se ha introducido el Depósito Bancario: REF. "+depBank);
											Thread.sleep(500);
											
											//Paso 11.- Hacer click en el botón Finalizar
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
					}
					Thread.sleep(8000);
					takeScreenShot("E:\\Selenium\\","accountReloadInvoicePage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountReloadInvoicePage.jpeg");
					
					//Paso 12.- Hacer click en el botón Finalizar
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(2000);
					Saldo = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
					
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
}