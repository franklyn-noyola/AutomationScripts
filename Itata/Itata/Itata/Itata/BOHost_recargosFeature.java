package Itata;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static Itata.BOHost_accountCreationWithVehicle.*;
import static TestLink.TestLinkExecution.*;
import static Itata.BOHost_accountCreationOnly.*;

public class BOHost_recargosFeature {
			 private static boolean accountClosed = false;
			 private static String [] options = {"Crear", "Modificar", "Eliminar", "Creacion por Tipo"};
			 private static int selOptions;
			 private static String [] recargoOptions = {"Creación de cuenta", "Actualización de cuenta", "Creación de vehículo", "Pérdida de Tag"};
			 private static int recargoSel;
			 private static String recargoSelected;
			 private static String tipoSelected;
			 private static boolean noDeleted = false;
			 private static String value;
			 private static int NumbVeh;
			 private static boolean NoVeh;
			 private static boolean noTag=false;
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
			public void recargosFeatureInit() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);
				testPlanReading(11,0,2,3);
				selOptions = ranNumbr(0,3);
				recargoSel = ranNumbr(0,3);
				if (selOptions<3) {
						configurationSection("Host",testNameSelected+" - Functionalities",testNameSelected);
						testPlanReading(11,0,2,3);					
				}else {
					if (recargoSel==0) {
						configurationSection("Host",testNameSelected+" - Creación de Cuenta",testNameSelected);
						testPlanReading(11,5,7,8);
					}
					if (recargoSel==1) {
						configurationSection("Host", testNameSelected+" - Actualización de cuenta",testNameSelected);
						testPlanReading(11,10,12,13);
					}
					if (recargoSel==2) {
						configurationSection("Host",testNameSelected+" - Creación de vehículo",testNameSelected);
						testPlanReading(11,15,17,18);
					}
					if (recargoSel==3) {
						configurationSection("Host",testNameSelected+"-Pérdida de Tag",testNameSelected);
						testPlanReading(11,20,22,23);
					}
				}
				
				Thread.sleep(1000);				
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				recargosFeature();
				Thread.sleep(200);
				if (noItemFound==true) {
					actualResults.set(2, "No se puede ni Modificar ni Eliminar un recargo, ya que no existen recargos existentes");									
					for (int i=3;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se pude ni Modificar ni Eliminar un recargo, ya que no existen recargos existentes");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;			
				}
				
				if (accountClosed){
					actualResults.set(9, "No se puede hacer un recargo a la cuenta "+accountNumbr+" porque está cerrada");									
					for (int i=10;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede hacer un recargo a la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;					
				}				
				if (noVehAllowed==true) {
					actualResults.set(17, "Se ha creado el recargo "+recargoSelected+" de tipo "+tipoSelected+" con valor "+value+", pero no se puede crear Vehiculo debido a: "+errorText);
					for (int i=18;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Se ha creado el recargo "+recargoSelected+" de tipo "+tipoSelected+" con valor "+value+", pero no se puede crear Vehiculo debido a: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));	
					return;	
				}
				if (NoVeh==true) {
					actualResults.set(15, "Se ha creado el recargo "+recargoSelected+" de tipo "+tipoSelected+" con valor "+value+", pero no se dar por pérdida de Tag debido a que la cuenta: "+accountNumbr+" no tiene vehiculo asociado");
					for (int i=16;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Se ha creado el recargo "+recargoSelected+" de tipo "+tipoSelected+" con valor "+value+", pero no se dar por pérdida de Tag debido a que la cuenta: "+accountNumbr+" no tiene vehiculo asociado");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));	
					return;	
				}
				if (noTag==true) {
					actualResults.set(15, "Se ha creado el recargo "+recargoSelected+" de tipo "+tipoSelected+" con valor "+value+", pero no se dar por pérdida de Tag debido a que el vehículo con la matricula No.:"+matriNu+" de la cuenta: "+accountNumbr+" no tiene tag asociado para hacer la acción Pérdida por tag");
					for (int i=16;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Se ha creado el recargo "+recargoSelected+" de tipo "+tipoSelected+" con valor "+value+", pero no se dar por pérdida de Tag debido a que el vehículo con la matricula No.:"+matriNu+" de la cuenta: "+accountNumbr+" no tiene tag asociado para hacer la acción Pérdida por tag");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));	
					return;			
				}
				if (noDeleted==true) {
					actualResults.set(4,"No se puede borrar Recargo: '"+optionSelectedId+"' debido a: "+errorText);
					driver.close();
					testLink();
					System.out.println("No se puede borrar Recargo: '"+optionSelectedId+"' debido a: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				if (options[selOptions].equals("Eliminar")) {
					actualResults.set(4,"Se ha borrado el recargo "+optionSelectedId+" correctamente");
					System.out.println("Se ha borrado el recargo "+optionSelectedId+" correctamente");
				}
				if (options[selOptions].equals("Modificar")) {
					actualResults.set(4,"Se ha modificado el recargo: "+optionSelectedId+" por el nuevo recargo: "+recargoSelected+" con nuevo tipo y valor "+tipoSelected+", "+value+" correctamente");
					System.out.println("Se ha modificado el recargo: "+optionSelectedId+" por el nuevo recargo: "+recargoSelected+" con nuevo tipo y valor "+tipoSelected+", "+value+" correctamente");
				}
				
				if (options[selOptions].equals("Crear")) {
					actualResults.set(4,"Se ha creado el recargo: "+recargoSelected+" con el tipo: "+tipoSelected+" y el valor: "+value+" correctamente");
					System.out.println("Se ha creado el recargo: "+recargoSelected+" con el tipo: "+tipoSelected+" y el valor: "+value+" correctamente");
				}
				if (options[selOptions].equals("Creacion por Tipo")) {
					if (tipoSelected.equals("Creación de cuenta")){
						actualResults.set(18,"Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha creado y aplicado a la cuenta No. "+accountNumbr);
						System.out.println("Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha creado y aplicado a la cuenta No. "+accountNumbr);
					}
					if (tipoSelected.equals("Actualización de cuenta")){
						actualResults.set(21,"Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha modificado y aplicado en la cuenta No. "+accountNumbr);
						System.out.println("Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha modificado y aplicado en la cuenta No. "+accountNumbr);
					}
					if (tipoSelected.equals("Creación de vehículo")){
						actualResults.set(25,"Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha creado un vehiculo con la matricula No.: "+matriNu+" en la cuenta No. "+accountNumbr);
						System.out.println("Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha creado un vehiculo con la matricula No.: "+matriNu+" en la cuenta No. "+accountNumbr);
					}
					if (tipoSelected.equals("Pérdida de Tag")){
						actualResults.set(24,"Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha dado por pérdido el Tag No.: "+tagIdNmbr+" del vehiculo con la matricula No.: "+matriNu+" en la cuenta No. "+accountNumbr);
						System.out.println("Se ha creado el recargo: "+recargoSelected+" del tipo: "+tipoSelected+" y valor: "+value+" correctamente y se ha dado por pérdido el Tag No.: "+tagIdNmbr+" del vehiculo con la matricula No.: "+matriNu+" en la cuenta No. "+accountNumbr);
					}
				}
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void recargosFeature() throws Exception {
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
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);
					
					//Paso 3.- Entrar a la pantalla Recargos (click en Configuración Sistema, luego Parámetros de cuenta y después Recargos)
					action.moveToElement(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
					Thread.sleep(1000);
					action.moveToElement(driver.findElement(By.linkText("Parámetros de cuenta"))).build().perform();
					pageSelected = "Recargos";
					elementClick(pageSelected);
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					//Paso 4.- Entrar a la pantalla Recargos (click en Configuración Sistema, luego Parámetros de cuenta y después Recargos)
					if (options[selOptions].equals("Modificar") || options[selOptions].equals("Eliminar")) {
						itemSearchandSelect();						
						if (noItemFound==true) {
							return;
						}
						if (options[selOptions].equals("Modificar")) {
							elementClick("ctl00_ContentZone_BtnModify");
							actualResults.set(3, "Se ha cliqueado el botón Modificar y se entra en la pantalla de edición del recargo: "+optionSelectedId);
						}
						if (options[selOptions].equals("Eliminar")) {
							elementClick("ctl00_ContentZone_BtnDelete");
							actualResults.set(3, "Se ha cliqueado el botón Eliminar y aparece un popup indicando que se va a eliminar el recargo: "+optionSelectedId);
							Thread.sleep(1000);
							if (isAlertPresent()) {
								driver.switchTo().alert().accept();
							}
							Thread.sleep(1000);
							if (driver.getPageSource().contains("No se puede borrar el elemento")) {
								errorText = getText("ctl00_LblError");
								noDeleted = true;
								return;
							}
							return;
						}
					}
					Thread.sleep(3000);
					
			
					if (options[selOptions].equals("Crear") || options[selOptions].equals("Creacion por Tipo")) {
						elementClick("ctl00_ContentZone_BtnCreate");
						actualResults.set(3, "Se ha cliqueado el botón Crear y aparece la pantalla para la creación de un nuevo recargo");
					}
					takeScreenShot("E:\\Selenium\\","recargoPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","recargoPage.jpeg");
					if (options[selOptions].equals("Creacion por Tipo")) {
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).selectByVisibleText(recargoOptions[recargoSel]);
					}else {
						selectDropDown("ctl00_ContentZone_cmb_type_cmb_dropdown");
					}
					
					//Paso 5 y 6.- Llenar campos y hacer click en el botón confirmar
					Thread.sleep(100);
					value = String.valueOf(ranNumbr(100,10000));
					WebElement recargSel = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).getFirstSelectedOption();
					tipoSelected = recargSel.getText();
					recargoSelected = tipoSelected+"_"+ranNumbr(100,1000);
					SendKeys("ctl00_ContentZone_txt_name_box_data", recargoSelected);
					SendKeys("ctl00_ContentZone_txt_description_box_data", "Recargo para "+tipoSelected);
					if (!tipoSelected.equals("Creación de cuenta")) {
						selectDropDown("ctl00_ContentZone_cmb_applicationType_cmb_dropdown");
					}
					action.click(driver.findElement(By.id("ctl00_ContentZone_money_amount_txt_formated"))).build().perform();
					action.sendKeys(value).build().perform();;					
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","filledData"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","filledData.jpeg");
					
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					if (options[selOptions].equals("Creacion por Tipo")) {
						switch (tipoSelected) {
							case "Creación de cuenta":			crearCuenta();
																break;
							case "Actualización de cuenta":		otherActions();
																break;
							case "Creación de vehículo":		otherActions();
																break;
							case "Pérdida de Tag":				otherActions();
																break;					
							
						}
					}
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
			
			public static void crearCuenta() throws Exception{
				Thread.sleep(2000);
				Actions action = new Actions(driver);
				try {
					//Paso 7.- Vaya al Menú Gestión de Cuentas, luego hacer click en la opción "Crear Cuenta" y luego en el link "Estándar o Comercial"
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
				
					//Pasos desde el 8 al 19.
					if (ranNumbr(0,1)==0) {
						pageSelected = "Cuenta Estándar";
						elementClick("Estándar");
						Thread.sleep(500);
						pageSelectedErr(6);
						if (pageSelectedErr==true) {
							driver.close();
							testLink();
							System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
							fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						}
						actualResults.set(6, "Se ha entrado a la página de creación de cuenta Estándar");
						elementClick("ctl00_ContentZone_BtnFees");
						Thread.sleep(100);
						new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(recargoSelected);
						elementClick("ctl00_ContentZone_btn_add");
						Thread.sleep(100);
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(100);
						cuentaEstandar();
					}else {
						pageSelected = "Cuenta Comercial";
						elementClick("Comercial");
						Thread.sleep(500);
						pageSelectedErr(6);
						if (pageSelectedErr==true) {
							driver.close();
							testLink();
							System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
							fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						}
						actualResults.set(6, "Se ha entrado a la página de creación de cuenta Comercial");
						elementClick("ctl00_ContentZone_BtnFees");
						Thread.sleep(100);
						new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(recargoSelected);
						elementClick("ctl00_ContentZone_btn_add");
						Thread.sleep(100);
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(100);
						cuentaComercial();
					}				
					Thread.sleep(4000);
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void otherActions() throws Exception{
				accountModeIta="Modificar";
				Thread.sleep(2000);
				Actions action = new Actions(driver);
				try {
					//Paso 7.- Vaya al Menú Gestión de Cuentas, luego hacer click en la opción Seleccionar cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(500);
					pageSelectedErr(6);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					int accountSelected =ranNumbr(0,1);
					if (accountSelected == 1) {
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Estándar");
					}else {
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Comercial");					
					}
				
					//Paso 8.- Ya en la pantalla Selección de Cuentga, hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				
					//Paso 9.- Hacer click en cualquiera de las cuentas que estén en la tabla de resultados
					selectAccount();
					Thread.sleep(1000);
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					
					//Paso 10.- Ya en la pantalla de edición de cuenta, si la cuenta está cerrada, el botón Editar está desactivado y no se podrá asignar un Recargo, de lo contrario, hacer click en el Boton Editar
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					NumbVeh = Integer.parseInt(numberVehicles);
					if (tipoSelected.equals("Pérdida de Tag")) {
						if (NumbVeh==0){
							NoVeh = true;
							return;
						}
					}				
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				
					//Paso 11.- Hacer click en el botón Recargo
					elementClick("ctl00_ContentZone_BtnFees");
					Thread.sleep(100);
					
					//Paso 12.- Seleccionar el Recargo recién creado
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(recargoSelected);
				
					//Paso 13.- Hacer click al botón "Añadir"
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(100);
				
					//Paso 14.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(100);
					String nextPage = getText("ctl00_SectionZone_LblTitle");
					if (nextPage.contains("Detalles del pago")) {
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						paymentfromCustomerEsp();
					}
				
					//BOHost_recargosFeature - Actualización de cuenta - Pasos desde 15 al 22
					if (tipoSelected.equals("Actualización de cuenta")) {
						if (accountSelected == 1) {
							cuentaEstandar();
						}else {
							cuentaComercial();					
						}
					}
				
					//BOHost_recargosFeature - Creación de vehículo - Pasos desde 15 al 26
					if (tipoSelected.equals("Creación de vehículo")) {
						accountCreationWithVehicle();					
					}
				
					//BOHost_recargosFeature- Pérdida de Tag - Pasos desde el 15 al 25 
					if (tipoSelected.equals("Pérdida de Tag")) {					
						int vehCheck = ranNumbr(0,NumbVeh-1);
						if (vehCheck<0) {
							vehCheck=0;
						}
						int selVeh = vehCheck+2;
						elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
						elementClick("ctl00_ContentZone_BtnSmarts");
						Thread.sleep(1000);					
						matriNu=getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[2]");
						tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+selVeh+"]/td[6]");
						if (tagIdNmbr.equals("")) {
							noTag = true;
							return;
						}
						elementClick("ctl00_ContentZone_chk_"+vehCheck);
						elementClick("ctl00_ContentZone_btn_token_stolen");
						int stat = ranNumbr(0,1);
						elementClick("ctl00_ContentZone_radio_stolen_"+stat);
						elementClick("ctl00_ContentZone_btn_stolen");						
						Thread.sleep(1000);
						nextPage = getText("ctl00_SectionZone_LblTitle");
						if (nextPage.contains("Detalles del pago")) {
							elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
							paymentfromCustomerEsp();
						}
					}				
					Thread.sleep(4000);
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
}		