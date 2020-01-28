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

public class BOHost_accountReload  {
			 private static String [] optionsToSelect = {"Recargas", "Subsanaciones", "Ajustes"};			 
			 private static boolean accountClosed = false;
			 public static String amount;
			 public static boolean limitAmount=false;
			 public static int selOption;
			 public static String optionSelected;
			 public static String Nombre1;
			 public static String Apellido1;
			 private static String radioFrom;
			 private static String listpayBy;
			 public static int amountPaid;
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
			public void accountReloadnit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);
				Thread.sleep(1000);
				testPlanReading(6,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				selOption = ranNumbr(0,2);
				optionSelected = optionsToSelect[selOption];
				accountReload();
				Thread.sleep(1000);
				if (limitAmount) {
					actualResults.set(7, "No se puede hacer "+optionSelected+" en la cuenta "+accountNumbr+" debido a: "+ errorMessage);
					for (int i=8;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede hacer "+optionSelected+" en la cuenta "+accountNumbr+" debido a: "+ errorMessage);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;											

				}
				
				if (accountClosed){
					actualResults.set(4, "No se puede hacer "+optionSelected+" en la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede hacer "+optionSelected+" en la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;						
				}		
				Thread.sleep(100);
				if (optionSelected.equals("Recargos") || optionSelected.equals("Subsanaciones")) {
					actualResults.set(10, "Se ha hecho "+optionSelected+" con un monto de: "+amountPaid+" hecho con/en" +listpayBy+" y el saldo actual es: "+amount+" en la cuenta "+accountNumbr);
					System.out.println("Se ha hecho "+optionSelected+" con un monto de: "+amountPaid+" hecho con/en" +listpayBy+" y el saldo actual es: "+amount+" en la cuenta "+accountNumbr);
				}
				if (optionSelected.equals("Ajustes")) {
					if (listpayBy.contains("cargo en cuenta de peaje")) {
						actualResults.set(10, "Se ha hecho "+optionSelected+" "+radioFrom+" con un monto de: "+amountPaid+" hecho con/en" +listpayBy+" y el saldo actual es: "+amount+" en la cuenta "+accountNumbr);
						System.out.println("Se ha hecho "+optionSelected+" "+radioFrom+" con un monto de: "+amountPaid+" hecho con/en" +listpayBy+" y el saldo actual es: "+amount+" en la cuenta "+accountNumbr);
					}else {
						actualResults.set(10, "Se ha hecho "+optionSelected+" "+radioFrom+" con un monto de: "+amountPaid+" hecho con/en" +listpayBy+" en la cuenta "+accountNumbr);
						System.out.println("Se ha hecho "+optionSelected+" "+radioFrom+" con un monto de: "+amountPaid+" hecho con/en" +listpayBy+" en la cuenta "+accountNumbr);
					}					
				}								
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}
			
			public static void accountReload() throws Exception {
				action = new Actions(driver);
				try {
					//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginCopexaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCopexaPage.jpeg");
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
					
					//Paso 4.- Seleccionar tipo de Cuenta PrePago en la lista desplegable Tipo de cuenta
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Prepago");
				
					//Paso 5.- Hacer click en el botón Buscar
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				
					//Paso 6.- Hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));				
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					actualResults.set(4, "Se ha seleccionado la cuenta No."+accountNumbr+" de una "+accountType);
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					
					//Paso 7.- Selecciona cualquier opción: Recargas, Subsanaciones, Ajustes
					amountPaid = ranNumbr(1000,100000);
					if (optionSelected.equals("Recargas")) {
						elementClick("ctl00_ContentZone_BtnLoads");
					}
					if (optionSelected.equals("Subsanaciones")) {
						elementClick("ctl00_ContentZone_BtnRetrievals");
					}
					
					//Paso 8.- Si es Ajustes seleccionado elegir cualquier opcion Pago del Cliente o Al cliente poner el monto desado y escribir una descripción y darle al botón Confirmar
						if (optionSelected.equals("Ajustes")) {
							elementClick("ctl00_ContentZone_BtnAdjustments");
							Thread.sleep(1000);
							WebElement option = driver.findElement(By.xpath("//*[contains(@id,'ctl00_ContentZone_RadioFrom')]"));
							List <WebElement> optionCount = option.findElements(By.tagName("tr"));
							int optSel = ranNumbr(0,optionCount.size()-1);
							if (optSel<=0) {
								optSel = 0;
							}
							int selOp = optSel+1;					
							elementClick("ctl00_ContentZone_txt_amount_txt_formated");
							SendKeys("ctl00_ContentZone_txt_amount_txt_plain",amountPaid+"");
							elementClick("ctl00_ContentZone_RadioFrom_"+optSel);
							radioFrom = getText("//*[contains(@id,'ctl00_ContentZone_RadioFrom')]/tbody/tr["+selOp+"]/td/label");
							SendKeys("ctl00_ContentZone_BoxDescription","Ajustes hecho por "+radioFrom);
							Thread.sleep(1000);
							elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
							Thread.sleep(2000);
							if (driver.getPageSource().contains("El límite a pagar al cliente es")) {
								errorMessage = getText("ctl00_LblError");
								limitAmount = true;
								return;						
							}
							
						}
						//Ya en la pantalla Pago al Cliente selecciona cualquier opción, si es Ajustes tendrá 3 opciones: cargo en cuenta de peaje, efectivo y tarjeta de credito, si es Subasanaciones o Recargos, tendrá dos opciones: efectivo y tarjeta de crédito, seleccionar las opciones correspondientes e introducir el monto si aplica
						WebElement option = driver.findElement(By.id("ctl00_ContentZone_CtType_radioButtonList_payBy"));
						List <WebElement> optionCount = option.findElements(By.tagName("tr"));
						int optSel = ranNumbr(0,optionCount.size()-1);
						if (optSel<=0) {
							optSel = 0;
						}
						int selOp = optSel+1;
						elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+optSel);
						listpayBy= getText("//*[contains(@id,'ctl00_ContentZone_CtType_radioButtonList_payBy')]/tbody/tr["+selOp+"]/td/label");
						
						if (optionSelected.equals("Recargas") || optionSelected.equals("Subsanaciones")) {
							elementClick("ctl00_ContentZone_CtType_text_total_txt_formated");
							SendKeys("ctl00_ContentZone_CtType_text_total_txt_plain",amountPaid+"");
						}
				
						Thread.sleep(1000);
						
						//9.- Hacer click en el boton Confirmar
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						Thread.sleep(1000);				
						if (driver.getPageSource().contains("El límite a pagar al cliente es")) {
							errorMessage = getText("ctl00_LblError");
							limitAmount = true;
							return;						
						}
				
						//Paso 10.- Si no se ha seleccionado cargo a cuenta de peaje y se ha seleccinado efectivo hacer click en el botón Finalizar, si se ha seleccinado Tarjea de crédito, llenar el campo código de autorización correctamente y hacer click en el botón Finalizar 
						if (listpayBy.equals("efectivo")) {
							elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						}
										
						if (listpayBy.contains("tarjeta de crédito/débito")) {
							SendKeys("ctl00_ContentZone_CtbyCard_BoxAuthCode",String.valueOf(ranNumbr(100000,9999999)));
							elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						}
						Thread.sleep(1000);
						
						//Paso 11. Si está en la pantalla Recibo, hacer clicn el el botón Finalizar
						if (listpayBy.equals("efectivo")|| listpayBy.contains("tarjeta de crédito/débito")) {
							elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						}
				
						if (listpayBy.contains("cargo en cuenta de peaje")|| optionSelected.equals("Recargos") || optionSelected.equals("Subsanaciones")) {
							amount = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
						}
						
						
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
	}