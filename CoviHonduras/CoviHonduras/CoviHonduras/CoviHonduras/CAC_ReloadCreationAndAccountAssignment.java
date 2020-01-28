package CoviHonduras.CoviHonduras;
import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static CoviHonduras.CoviHonduras.CAC_accountCreationWithVehicle.*;



public class CAC_ReloadCreationAndAccountAssignment{
			 private static boolean accountClosed = false;
			 private static boolean reloadCreated = false;		 
			 private static String applicationType;
			 private static String applicationTypeText;
			 public static String reloadDescription;
			 private static int cargoAmount;
			 private static int optionclick;
			 private static int tipoChoice;
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
			public void reloadCreationInit() throws Exception {
				tipoChoice = ranNumbr(0,3);
				if (tipoChoice==0) {
					configurationSection("CAC",testNameSelected+" - Creación de Cuenta",testNameSelected);					
					testPlanReading(9,0,2,3);
				}
				if (tipoChoice==1) {
					configurationSection("CAC",testNameSelected+" - Actualización de cuenta",testNameSelected);					
					testPlanReading(9,5,7,8);
				}
				if (tipoChoice==2) {
					configurationSection("CAC",testNameSelected+" - Creación de vehículo",testNameSelected);					
					testPlanReading(9,10,12,13);					
				}
				if (tipoChoice==3) {
					configurationSection("CAC",testNameSelected+" - Pérdida de Tag",testNameSelected);					
					testPlanReading(9,15,17,18);
				}
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				accountReload();
				Thread.sleep(1000);
				if (accountClosed){
					actualResults.set(9, "No se puede asignar el Recargo: "+applicationType+"a la cuenta "+accountNumbr+" porque está cerrada");									
					for (int i=10;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					driver.close();
					testLink();		
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;					
				}
				
				if (reloadCreated==true){
					int finalstepSel = executionNumber.size()-1;
					actualResults.set(finalstepSel, "Se muestra la pantalla principal con el Monto del Recargo "+applicationType+" aplicado a la cuenta: "+accountNumbr+" correctamente");
					driver.close();
					testLink();
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					Thread.sleep(1000);
				}else{
					int stepSel=0;
					if (tipoChoice==0) {
						actualResults.set(13, "El recargo: "+applicationType+" se ha creado pero no se ha podido aplicar a la cuenta "+accountNumbr+" por un error, verificar pantallazo o log de error");
						stepSel=13;
					}
					if (tipoChoice==1) {
						actualResults.set(16, "El recargo: "+applicationType+" se ha creado pero no se ha podido aplicar a la cuenta "+accountNumbr+" por un error, verificar pantallazo o log de error");
						stepSel=16;
					}
					if (tipoChoice==2) {					
						actualResults.set(13, "El recargo: "+applicationType+" se ha creado pero no se ha podido aplicar a la cuenta "+accountNumbr+" por un error, verificar pantallazo o log de error");
						stepSel=20;
					}
					if (tipoChoice==3) {
						actualResults.set(13, "El recargo: "+applicationType+" se ha creado pero no se ha podido aplicar a la cuenta "+accountNumbr+" por un error, verificar pantallazo o log de error");
						stepSel=19;
					}					
					executionResults.set(stepSel, "Fallado");
					stepNotExecuted = executionNumber.size()-1;
					for (int i = stepNotExecuted;i>stepSel;i--) {
						executionNumber.remove(i);
					}
					summaryBug = "El recargo: "+applicationType+" se ha creado pero no se ha podido aplicar a la cuenta"+accountNumbr;
					erroTextBug = "El recargo: "+applicationType+" se ha creado pero no se ha podido aplicar a la cuenta"+accountNumbr+" por un error, verificar pantallazo o log de error";
					componentBug = "BO";
					severityBug = 2;
					priority = 3;
					testFailed = true;
					bugAttachment = true;
					pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\noRecargoAssigned.jpeg";
					driver.close();					
					testLink();
					System.out.println("Se ha probado en la versión del "+ getVersion(applevelTested));
					return;							
				}
				
		
			}

			public static void accountReload() throws Exception {
				Actions action = new Actions(driver);
				try {
					//Paso 1.- 	Entrar a la página de login del CAC de CoviHonduras	
					driver.get(CaCUrl);
					takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCACCVHPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					
					//Paso 2.- Loguearte con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);			
					
					//3.- Entrar a la pantalla Recargos (click en Configuración Sistema, luego Parámetros de cuenta y después Recargos)
					action.moveToElement(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
					Thread.sleep(1000);	
					action.moveToElement(driver.findElement(By.linkText("Parámetros de cuenta"))).build().perform();
					Thread.sleep(1500);
					pageSelected = "Recargos";
					elementClick(pageSelected);
					Thread.sleep(2000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadPage.jpeg");
					
					//Paso 4.- Ya en la pantalla de Recargos, hacer click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","ReloadCreatioPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadCreatioPage.jpeg");
					Thread.sleep(500);
					selectDropDown("ctl00_ContentZone_cmb_type_cmb_dropdown");
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).selectByIndex(tipoChoice);
					Thread.sleep(500);
					applicationType = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).getFirstSelectedOption().getText();	
					applicationTypeText = applicationType+"-"+timet.substring(4, 14);
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_txt_name_box_data",applicationTypeText);
					
					Thread.sleep(2000);
					reloadDescription = "Recargo para "+applicationTypeText;			
					SendKeys("ctl00_ContentZone_txt_description_box_data",reloadDescription);
					if (!applicationType.equals("Creación de cuenta")){
						selectDropDown("ctl00_ContentZone_cmb_applicationType_cmb_dropdown");
					}
					cargoAmount = ranNumbr(10000,20000);
					action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_money_amount_txt_formated")),String.valueOf(cargoAmount)).build().perform();
					Thread.sleep(3000);
					
					//Paso 6.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(3000);
					actualResults.set(5,  "Se ha creado el Recargo: "+applicationTypeText+" con el Monto de: "+cargoAmount);
					
					switch (applicationType){
						case "Creación de cuenta":				accountCreation();
																Thread.sleep(500);
																break;
						case "Actualización de cuenta":			accountUpdate();
																Thread.sleep(500);
																break;
						case "Creación de vehículo":			vehicleCreation();
																Thread.sleep(500);
																break;
						case "Pérdida de Tag":					tagMissed();
																Thread.sleep(500);
																break;
					}

				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}

			public static void accountCreation() throws Exception{
				Actions action = new Actions(driver);				
				Thread.sleep(2000);					
				try {
					
					//Pasp 7.- Vaya al Menú Gestión de Cuentas, luego hacer click en la opción "Crear Cuenta" y luego en el link "PrePago"
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);		
					action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
					Thread.sleep(500);
					pageSelected = "Cuenta Prepago";
					elementClick("Prepago");								
					Thread.sleep(1000);
					pageSelectedErr(6);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					takeScreenShot("E:\\Selenium\\","accountCreationPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountCreation.jpeg");
					
					//Paso 8.- Ya en la pantalla de Creación de Cuentas, hacer click en el botón Recargos;
					elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 9.- Seleccionar el Recargo recién creado
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
					Thread.sleep(1000);
					actualResults.set(8,  "Se ha seleccionado el recargo: "+applicationTypeText+" recién creado correctamente");
					
					//Paso 10.- Hacer click en el botón Añadir
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(500);
					actualResults.set(9,  "Se ha añadido el recargo: "+applicationTypeText+" a la columna recargos en esta cuenta correctamente");
					
					//Paso 11.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(100);
					
					//Paso 12.- Ya en la pantalla principal de craeción de cuentas, elegir cualquier cuenta, Personal o Corporativa y rellanar todos los  campos mandatorios y no mandatorios
					int selOpt = ranNumbr(0,1);
					int selOp = ranNumbr(0,8);
					int selOp2 = ranNumbr(0,8);
					if (selOpt==0){
						elementClick(infoCuenta0);
						Thread.sleep(1000);
						SendKeys(RUCid,ranNumbr(10000000,90000000)+""+ranNumbr(100000,9000000));
						new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelcEsp[selOp]);
						SendKeys(namef,nameOp[selOp]);
						SendKeys(surnamef,lastNameOp[selOp]);
						SendKeys(addressf,addressTec[selOp]);
						SendKeys(cpf,cpAdress[selOp]);			
						SendKeys(countryf,"España");
						SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
						SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
						SendKeys(workPhone,workPhone1[selOp]);
						SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
						SendKeys(faxPhone,workPhone1[selOp]);				
						Thread.sleep(4000);
					}else{
						elementClick(infoCuenta1);
						SendKeys(RUCid,ranNumbr(10000000,90000000)+""+ranNumbr(100000,9000000));
						Thread.sleep(1000);
						SendKeys(companyf,"Tecsidel, S.A");
						SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
						SendKeys(addressf,addressTec[2]);
						SendKeys(cpf,cpAdress[2]);
						SendKeys(countryf,"España");
						SendKeys(emailf,"info@tecsidel.es");
						SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
						SendKeys(workPhone,workPhone1[2]);
						SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
						SendKeys(faxPhone,workPhone1[selOp]);
						Thread.sleep(1000);								
					}
					selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_groupFare_cmb_dropdown");
					Thread.sleep(1000);		
					takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSave_IB_Button");
					
					//Del Paso 14 al Paso 19	
					pageSelected = getText("ctl00_SectionZone_LblTitle");
					Thread.sleep(2000);
					pageSelectedErr(13);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					reloadConfirmation();
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}

			public static void accountUpdate() throws Exception{
				Actions action = new Actions(driver);
				Thread.sleep(1000);
				try {
					
					//Paso 7.- Vaya al Menú Gestión de Cuentas, luego hacer click en la opción Seleccionar cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(1000);
					pageSelectedErr(6);
					if (pageSelectedErr==true) {						
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					//Paso 8.- Ya en la pantalla Selección de Cuentga, hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");					
					takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
					
					//Paso 9.- Hacer click en cualquiera de las cuentas que estén en la tabla de resultados
					selectAccount();
					Thread.sleep(1000);
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");				
					Thread.sleep(1000);
					
					//Paso 10.- Ya en la pantalla de edición de cuenta, si la cuenta está cerrada, el botón Editar está desactivado y no se podrá asignar un Recargo, de lo contrario, hacer click en el Boton Editar
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(500);
					
					//Paso 11.- Hacer click en el botón Recargo
					elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 12.- Seleccionar el Recargo recién creado
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
					Thread.sleep(1000);
					
					//Paso 13.- Hacer click al botón "Añadir"
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(500);
					
					//Paso 14.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1000);
					
					//Paso 15.- Ya en la pantalla principal de Actualización de cuentas, modificar la cuenta con uno o varios campos con valores nuevos
					SendKeys(RUCid,ranNumbr(10000000,90000000)+""+ranNumbr(100000,9000000));
					Thread.sleep(1000);		
					takeScreenShot("E:\\Selenium\\","dataChangeded"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataChanged.jpeg");
					
					//Paso 16.- Una vez modificado  los campos correspondientes, hacer click en el botón Guardar
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					
					//Del Paso 17 al Paso 22
					pageSelected = getText("ctl00_SectionZone_LblTitle");
					Thread.sleep(2000);
					pageSelectedErr(16);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					reloadConfirmation();
									
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
	
			public static void vehicleCreation() throws Exception{
				Actions action = new Actions(driver);
				Thread.sleep(1000);
				try {
					//Paso 7.- Vaya al Menú Gestión de Cuentas, luego hacer click en la opción Seleccionar cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(1000);
					pageSelectedErr(6);
					if (pageSelectedErr==true) {						
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					//Paso 8.- Ya en la pantalla Selección de Cuentga, hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");					
					takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
					
					//Paso 9.- Hacer click en cualquiera de las cuentas que estén en la tabla de resultados
					selectAccount();
					Thread.sleep(1000);
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");				
					Thread.sleep(1000);
					
					//Paso 10.- Ya en la pantalla de edición de cuenta, si la cuenta está cerrada, el botón Editar está desactivado y no se podrá asignar un Recargo, de lo contrario, hacer click en el Boton Editar
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}					
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(500);
					
					//Paso 11.- Hacer click en el botón Recargo
					elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 12.- Seleccionar el Recargo recién creado
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
					Thread.sleep(1000);
					
					//Paso 13.- Hacer click al botón "Añadir"
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(500);
					
					//Paso 14.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(2000);
					
					//Del Paso 15 al Paso 19
					accountCreationWithVehicle();
					
					//Del Paso 20 al Paso 26
					pageSelected = getText("ctl00_SectionZone_LblTitle");
					Thread.sleep(2000);
					pageSelectedErr(19);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
						reloadConfirmation();
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
	
			public static void tagMissed() throws Exception{
				Actions action = new Actions(driver);
				Thread.sleep(1000);
				try {
					
					//Paso 7.- Vaya al Menú Gestión de Cuentas, luego hacer click en la opción Seleccionar cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(2000);
					pageSelectedErr(6);
					if (pageSelectedErr==true) {						
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					//Paso 8.- Ya en la pantalla Selección de Cuentga, hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");		
					takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
					
					//Paso 9.- Hacer click en cualquiera de las cuentas que estén en la tabla de resultados
					selectAccount();
					Thread.sleep(1000);
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountPage.jpeg");				
					Thread.sleep(1000);
					
					//Paso 10.- Ya en la pantalla de edición de cuenta, si la cuenta está cerrada, el botón Editar está desactivado y no se podrá asignar un Recargo, de lo contrario, hacer click en el Boton Editar
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}										
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(500);
					
					//Paso 11.- Hacer click en el botón Recargo
					elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 12.- Seleccionar el Recargo recién creado
					new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
					Thread.sleep(1000);
					
					//Paso 13.- Hacer click al botón "Añadir"
					elementClick("ctl00_ContentZone_btn_add");
					Thread.sleep(500);
					
					//Paso 14.- Hacer click en el botón Confirmar y luego hacer click en el botón Guardar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(2000);
					
					//Paso 15.- Ya en la pantalla principal de la Cuenta, si no hay vehículo asignado, crear uno en consecuencia y luego de haber creado y guarado la cuenta con el vehículo asignado hacer click en el botón TelePeajes
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					int NumbVeh = Integer.parseInt(numberVehicles);
					if (NumbVeh==0){
						accountCreationWithVehicle();
					}else{
						elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
						Thread.sleep(1500);
					}					
					elementClick("ctl00_ContentZone_BtnTags");
					takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 16.- Si hay vehículo y no tiene tag, asignarle un tag y luego seleccionarlo
					elementClick("ctl00_ContentZone_chk_0");
					String tagid = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]");
					if (tagid.equals("")){
						elementClick("ctl00_ContentZone_btn_token_assignment");
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
						Thread.sleep(500);
						elementClick("ctl00_ContentZone_btn_init_tag");
						Thread.sleep(500);
						elementClick("ctl00_ContentZone_chk_0");
					}
					
					//Paso 17.- Hacer click en el botón Desaparecido
					elementClick("ctl00_ContentZone_btn_token_stolen");
					Thread.sleep(1000);
					
					//Paso 18.- Seleccionar el la opción desaparecido: perdido y hacer click en el checkbox Perdido por el Cliente
					elementClick("ctl00_ContentZone_btn_stolen");
					Thread.sleep(1500);
					
					//Del Paso 19 al Paso 25
					reloadConfirmation();		
				}catch (Exception e){
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
	
	
			public static void reloadConfirmation() throws Exception{
				Thread.sleep(3000);
				try {
					
					//Paso GENXX.- Una vez en la pantalla Detalles de Pago verificar si está el Recargo recién creado para la operación a que fue creada
					WebElement tablereload = driver.findElement(By.id("ctl00_ContentZone_CtNumbers_m_table_fees"));
					List<WebElement> tablere = tablereload.findElements(By.tagName("tr"));		
					takeScreenShot("E:\\Selenium\\","PayDetailPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","PayDetailPage.jpeg");
					if (tablere.size()>1){
						for (int i = 2; i <=tablere.size();i++){
							String reload = getText("//*[@id='ctl00_ContentZone_CtNumbers_m_table_fees']/tbody/tr["+i+"]/td[1]");
							if (reload.contains(reloadDescription)){
								reloadCreated = true;
								if (tipoChoice==0) {
									actualResults.set(13, "Se encuentra el la descripción del Recargo: "+applicationTypeText+" que se creo recientemente en la pantalla Detalles del Pago");
								}
								if (tipoChoice==1) {
									actualResults.set(16, "Se encuentra el la descripción del Recargo: "+applicationTypeText+" que se creo recientemente en la pantalla Detalles del Pago");
								}
								if (tipoChoice==2) {					
									actualResults.set(20, "Se encuentra el la descripción del Recargo: "+applicationTypeText+" que se creo recientemente en la pantalla Detalles del Pago");
								}
								if (tipoChoice==3) {
									actualResults.set(19, "Se encuentra el la descripción del Recargo: "+applicationTypeText+" que se creo recientemente en la pantalla Detalles del Pago");
								}
							}else{
								reloadCreated = false;
								takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","noRecargoAssigned.jpeg");
								return;								
								
							}
						}
					}else{
						reloadCreated = false;
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","noRecargoAssigned.jpeg");
						return;
					}
					
					//Paso GENXX.- Hacer  Click en el botón Continuar
					elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
					Thread.sleep(1000);

					if (!applicationTypeText.equals("Pérdida de Tag")){
						optionclick = ranNumbr(0,3);
					}else{
						optionclick = ranNumbr(0,2);
					}
					//Paso GENXX.- Seleccionar el Modo de Pago disponible
					elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+optionclick);
					int rowSel = optionclick+1;
					String optionSelected = getText("//*[@id='ctl00_ContentZone_CtType_radioButtonList_payBy']/tbody/tr["+rowSel+"]/td/label");
					actualResults.set(15, "Se ha seleccionado el Modo de Pago: "+optionSelected);
					int optionclick1 = ranNumbr(0,1);
					if (optionclick1==1){
						elementClick("ctl00_ContentZone_CtType_chk_present");
					}
					
					Thread.sleep(1000);		
					takeScreenShot("E:\\Selenium\\","ReloadPageDetail"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReloadPageDetail.jpeg");
					
					//Paso GENXX.- Una vez seleccionado el modo de Pago Hacer click en el botón Continuar
					elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
					Thread.sleep(3000);
					takeScreenShot("E:\\Selenium\\","accountReloadConfirmationPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountReloadConfirmationPage.jpg");
					
					//Paso GENXX.- Dependiendo del modo de pago que se eligio, ejecutar las acciones pertinentes y después hacer click en el botón Finalizar
					switch (optionclick){
						case 0:				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
						case 1:				SendKeys("ctl00_ContentZone_CtbyCard_BoxAuthCode_box_data",String.valueOf(ranNumbr(100000,999999)));
											Thread.sleep(500);
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
						case 2:				SendKeys("ctl00_ContentZone_CtbyCheque_txt_number_box_data",String.valueOf(ranNumbr(1000000,9999999)));
											Thread.sleep(500);
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
						case 3:				SendKeys("ctl00_ContentZone_CtbyDepoBancario_BoxReference_box_data","REF. "+ranNumbr(1000000,9999999));
											Thread.sleep(500);
											elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
											break;
					}
					Thread.sleep(4000);
					takeScreenShot("E:\\Selenium\\","accountReloadInvoicePage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountReloadInvoicePage.jpeg");
					//Paso GENXX.- Una vez en la pantalla de Recibo, hacer click en el botón Finalizar
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(2000);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
}