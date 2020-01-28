package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;


import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_gestionIngresos {
	private static String [] ingresosOption = {"Crear", "Modificar", "Cerrar", "Movimientos", "Ingreso", "Abono", "Traspaso", "Ingresos Pendientes"};
	private static int opSel;
	private String testNameSelected = this.getClass().getSimpleName();
	private static String bankDestination;
	private static int bankSel;
	private static boolean bankErr = false;
	private static boolean noBankFound = false;
	private static boolean bankAccountClosed=false;
	private static boolean noPendingTransfer = false;
	private static int depositValue;
	private static String valwithDecimal;
	private static String [] bankNames = {"Bank Sabadell", "BBVA","CaixaBank","Santander","Deutsche Bank","Banco Madrid","Banco Popular", "Bankia","Bankinter","Barclays","Banco Pastor"};
	private static int [] bankCode = {81,189,2100,49,19,59,75,2038,128,65,238};
	private static String [] bankAddress = {"Avda. Óscar Esplá 37\r\n03007 Alicante","Plaza San Nicolás 4\r\n48005 Bilbao","C/ Pintor Sorolla 2-4\r\n46002Valencia"+
			"Pº Pereda, 9-12\r\n39004 Santander","Paseo de la Castellana 18\r\n28046 Madrid", "‎Paseo de la Castellana 2\r\n28046 Madrid","Juan Ignacio Luca de Tena 11\r\n28027 Madrid"+
			"Social: C/ Pintor Sorolla 8\r\n46002 Valencia","Paseo de la Castellana 29\r\n28046 Madrid","Plaza Colón 1\r\n28046 Madrid","C/Cantón Pequeño 1\r\n15003 La Coruña"};
	private static String bankAccountES;
	private static String bankAccountESNew;
	private static String bankNameNew;
	public static int decimal;	
	private static String dateSelected;
	private static String dateNew;
	private static String valueFormatted;
	private static String depositValueNew;	
	
	
		@Before
		public void setup() throws Exception{
			setUp();
		}
		
		@After
		public void teardown() throws Exception{
			tearDown();
		}
		public void testPlanConf() throws Exception{
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			bankSel =ranNumbr(0,bankNames.length-1);
			if (bankSel<0) {
				bankSel=0;
			}			
			opSel = ranNumbr(0,ingresosOption.length-1);
			if (opSel<0) {
				opSel=0;
			}
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
				testPlanReading(8,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Modificar", testNameSelected);
				testPlanReading(8,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Cerrar", testNameSelected);
				testPlanReading(8,10,12,13);				
			}
			if (opSel==3) {
				configurationSection("Host", testNameSelected+" - Movimientos", testNameSelected);
				testPlanReading(8,15,17,18);				
			}
			if (opSel==4) {
				configurationSection("Host", testNameSelected+"-Ingreso", testNameSelected);
				testPlanReading(8,20,22,23);
			}
			if (opSel==5) {
				configurationSection("Host", testNameSelected+" - Abono", testNameSelected);
				testPlanReading(8,25,27,28);				
			}
			if (opSel==6) {
				configurationSection("Host", testNameSelected+" - Traspaso", testNameSelected);
				testPlanReading(8,30,32,33);
			}
			if (opSel==7) {
				configurationSection("Host", testNameSelected+" - Ingresos Pendientes", testNameSelected);
				testPlanReading(8,35,37,38);
			}
			
			
		}
		
		@Test
		public void gestionIngresosInit() throws Exception{
			testPlanConf();
			gestionIngresos();
			if (bankAccountClosed==true) {
				driver.close();
				testLink();
				System.out.println("No se puede cerrar la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
				return;
			}
			if (noBankFound==true) {
				actualResults.set(3,"No se puede hacer un "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a que no hay registros en la lista");
				for (int i=4;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a que no hay registros en la lista");
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
				return;	
			}
			if (noPendingTransfer==true) {
				actualResults.set(8,"No se pude hacer un transado a cuenta porque no hay ingresos pendientes");
				for (int i=9;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se pude hacer un transado a cuenta porque no hay ingresos pendientes");
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
				return;
			}
			
			
			if (bankErr == true) {				
				if (ingresosOption[opSel].equals("Ingresos Pendientes")) {					
					System.out.println("No se puede hacer un Traslado a cuenta en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
				}else {					
					System.out.println("No se puede hacer "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
				}				
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
				return;
			}
			
			if (ingresosOption[opSel].equals("Crear")) {
				actualResults.set(6,"Se ha creado una Cuenta Bancaria en: "+bankNames[bankSel]+" con el IBAN: "+bankAccountES+" con un saldo por valor de: "+depositValueNew);
				System.out.println("Se ha creado una Cuenta Bancaria en: "+bankNames[bankSel]+" con el IBAN: "+bankAccountES+" con un saldo por valor de: "+depositValueNew);
			}
			if (ingresosOption[opSel].equals("Modificar")) {
				actualResults.set(6,"Se ha Modificado una Cuenta Bancaria con la Entidad: "+bankNameNew+" que contenia el IBAN: "+bankAccountESNew+" con el Saldo: "+depositValueNew+" por una nueva Entidad: "+bankNames[bankSel]+" y otro IBAN: "+bankAccountES);
				System.out.println("Se ha Modificado una Cuenta Bancaria con la Entidad: "+bankNameNew+" que contenia el IBAN: "+bankAccountESNew+" con el Saldo: "+depositValueNew+" por una nueva Entidad: "+bankNames[bankSel]+" y otro IBAN: "+bankAccountES);
			}
			if (ingresosOption[opSel].equals("Cerrar")) {
				actualResults.set(5,"Se ha Cerrado una Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew);
				System.out.println("Se ha Cerrado una Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew);
			}
			if (ingresosOption[opSel].equals("Movimientos")) {
				actualResults.set(6,"La Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" posee "+selRecord+" movimientos hasta la fecha");
				System.out.println("La Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" posee "+selRecord+" movimientos hasta la fecha");
			}
			if (ingresosOption[opSel].equals("Ingreso") || ingresosOption[opSel].equals("Abono")) {
				actualResults.set(6,"Se ha hecho un "+ingresosOption[opSel]+" en la Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" de "+valueFormatted);
				System.out.println("Se ha hecho un "+ingresosOption[opSel]+" en la Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" de "+valueFormatted);
			}
			if (ingresosOption[opSel].equals("Traspaso")) {
				actualResults.set(7,"Se ha hecho un traspaso desde la Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" por un valor de: "+valueFormatted+" a la cuenta destino con el IBAN: "+bankDestination);
				System.out.println("Se ha hecho un traspaso desde la Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" por un valor de: "+valueFormatted+" a la cuenta destino con el IBAN: "+bankDestination);			
			}
			if (ingresosOption[opSel].equals("Ingresos Pendientes")) {
				actualResults.set(10,"Se ha hecho un traslado a cuenta desde la Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" por un valor de: "+valueFormatted+" desde la fecha "+dateSelected+" a la fecha "+dateNew);
				System.out.println("Se ha hecho un traslado a cuenta desde la Cuenta Bancaria con la Entidad: "+bankNameNew+" y el IBAN: "+bankAccountESNew+" por un valor de: "+valueFormatted+" desde la fecha "+dateSelected+" a la fecha "+dateNew);
			}
			driver.close();
			testLink();
			System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
		}
		
		public static void gestionIngresos() throws Exception{
			action = new Actions(driver);
			try {
				//Paso 1.- Entrar a la página de Login del BO Host de R3R5Ciralsa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la P�gina de Inicio");
					fail("Un error ha ocurrido en la P�gina de Inicio");
				}
													
				//Paso 2.- Loguearse con el usuario 00001/00001
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOHostPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(2000);				
				
				//Paso 3.- Hacer click en Configuración Sistema y luego Gestión de descuentos
				action.moveToElement(driver.findElement(By.linkText("Gesti�n de pagos"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Gesti�n de ingresos";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				takeScreenShot("E:\\Selenium\\","gestionIngresosMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionIngresosMainPage.jpeg");
				
				if (ingresosOption[opSel].equals("Modificar") || ingresosOption[opSel].equals("Cerrar")||ingresosOption[opSel].equals("Movimientos")||ingresosOption[opSel].equals("Ingreso")|| ingresosOption[opSel].equals("Abono")||ingresosOption[opSel].equals("Traspaso")||ingresosOption[opSel].equals("Ingresos Pendientes")) {
					//TC BOHost_gestionIngresos - Modificar; //TC BOHost_gestionIngresos - Cerrar; //TC BOHost_gestionIngresos - Movimientos; //TC BOHost_gestionIngresos - Ingreso; //TC BOHost_gestionIngresos - Abono; //TC BOHost_gestionIngresos - Ingresos Pendientes; //TC BOHost_gestionIngresos - Traspaso
					//Paso 4.- Ya en la pantalla Gestión de ingresos, y si hay cuentas disponibles, seleccionar cualquiera de las cuentas disponible
					itemSearchedandSelect();		
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","accountSelected"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSelected.jpeg");
					if (noItemFound==true) {
						noBankFound = true;
						return;
					}else {
						actualResults.set(3, "Se ha seleccionado la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew);
					}					
				}
				
				switch (ingresosOption[opSel]) {
					case "Crear":				//TC BOHost_gestionIngresos - Crear: Paso 4.- Hacer click en el botón Crear
												elementClick("ctl00_ContentZone_BtnCreate");												
												bankAccountCreate();
												break;
					case "Modificar":			//TC BOHost_gestionIngresos - Modificar: Paso 5.- Hacer click en el botón Modificar
												elementClick("ctl00_ContentZone_BtnEdit");												
												bankAccountModify();
												break;
					case "Cerrar":				//TC BOHost_gestionIngresos - Cerrar: Paso 5.- Hacer click en el botón Cerrar
												elementClick("ctl00_ContentZone_BtnClose");
												bankAccountClose();
												break;
					case "Movimientos":			//TC BOHost_gestionIngresos - Movimientos: Paso 5.- Hacer click en el botón Movimientos
												elementClick("ctl00_ContentZone_BtnMovementsQuery");
												bankAccountMovements();
												break;
					case "Ingreso":				//TC BOHost_gestionIngresos - Ingreso: Paso 5.- Hacer click en el botón Ingreso
												elementClick("ctl00_ContentZone_BtnRevenue");
												bankAccountRevenue();
												break;
					case "Abono":				//TC BOHost_gestionIngresos - Abono: Paso 5.- Hacer click en el botón Abono
												elementClick("ctl00_ContentZone_BtnPayment");
												bankAccountPayment();
												break;
					case "Traspaso":			//TC BOHost_gestionIngresos - Traspaso: Paso 5.- Hacer click en el botón Traspaso
												elementClick("ctl00_ContentZone_BtnTransfer");
												bankAccountTransfer();
												break;
					case "Ingresos Pendientes":	//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 5.- Hacer click en el botón Ingresos Pendientes
												elementClick("ctl00_ContentZone_BtnPendingDeposits");
												bankAccountPendingDeposits();
												break;
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void bankAccountCreate() throws Exception{
			action = new Actions(driver);
			try {
				//TC BOHost_gestionIngresos - Crear: Paso 5.- Llenar todos los datos correspondientes
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountCreatePage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountCreatePage.jpeg");
				SendKeys("ctl00_ContentZone_dlgEntity_box_data",bankNames[bankSel]);
				SendKeys("ctl00_ContentZone_dlgAdress_box_data",bankAddress[bankSel]);
				SendKeys("ctl00_ContentZone_dlgIBAN_box_data",accountBankCalculation(bankCode[bankSel],ranNumbr(100,2000),ranNumbr(100,2000),ranNumbr(100,2000),ranNumbr(100,2000)));
				depositValue=ranNumbr(5000,99999999);
				action.click(driver.findElement(By.id("ctl00_ContentZone_dlgAmount_txt_formated"))).sendKeys(String.valueOf(depositValue)).build().perform();;
				Thread.sleep(1000);				
				takeScreenShot("E:\\Selenium\\","bankAccountDataFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountDataFilledPage.jpeg");
				
				//TC BOHost_gestionIngresos - Crear: Paso 6.- Hacer click en el botón Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
				Thread.sleep(1000);
				//TC BOHost_gestionIngresos - Crear: Paso 7.- Hacer click en el botón Aceptar de la pantalla emergente
				elementClick("popup_ok");
				Thread.sleep(2000);				
				elementClick("#content > div.ng-scope > div > div:nth-child(1) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > table > tbody > tr > td:nth-child(5) > input");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","bankAccountCreated"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountCreated.jpeg");
				WebElement tableRes = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
				List <WebElement> rowsTable = tableRes.findElements(By.tagName("tr"));
				int lastRow = rowsTable.size()-1;
				depositValueNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+lastRow+"]/td[4]");
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		public static void bankAccountModify() throws Exception{
			try {
				//TC BOHost_gestionIngresos - Modificar: Paso 6.- Modifica los campos correspondientes
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountEditPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountEditPage.jpeg");
				SendKeys("ctl00_ContentZone_dlgEntity_box_data",bankNames[bankSel]);
				SendKeys("ctl00_ContentZone_dlgAdress_box_data",bankAddress[bankSel]);
				SendKeys("ctl00_ContentZone_dlgIBAN_box_data",accountBankCalculation(bankCode[bankSel],ranNumbr(100,2000),ranNumbr(100,2000),ranNumbr(100,2000),ranNumbr(100,2000)));
				takeScreenShot("E:\\Selenium\\","accountModified"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountModified.jpeg");
				
				//TC BOHost_gestionIngresos - Modificar: Paso 7.- Hacer click en el botón Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
				Thread.sleep(1000);
				
				//TC BOHost_gestionIngresos - Modificar: Paso 7.- Hacer click en el botón Aceptar de la pantalla emergente
				elementClick("popup_ok");
				Thread.sleep(2000);
				SendKeys("ctl00_ContentZone_filterIBAN_box_data",bankAccountES);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","accountModifiedViewPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountModifiedViewPage.jpeg");
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void bankAccountClose() throws Exception{
			try {
				
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountClosePopup"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountClosePopup.jpeg");
				if (driver.getPageSource().contains("La cuenta ya está cerrada")||driver.getPageSource().contains("una cuenta con saldo positivo")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					bankAccountClosed = true;
					actualResults.set(4,"No se puede cerrar la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					actualResults.set(5, "N/A");
					executionResults.set(5, "");
					return;
				}
				//TC BOHost_gestionIngresos - Cerrar: Paso 6.- Hacer click en el botón Aceptar de la ventaja emergente
				elementClick("popup_ok");
				Thread.sleep(1000);
				if (driver.getPageSource().contains("No se puede cerrar la cuenta.")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(5,"No se puede cerrar la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					bankAccountClosed = true;
					return;
				}
				takeScreenShot("E:\\Selenium\\","bankAccountClosed"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountClosed.jpeg");
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void bankAccountMovements() throws Exception{							
			try {
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountMovementsPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountMovementsPage.jpeg");
				
				//TC BOHost_gestionIngresos - Movimientos: Paso 6.- Si el campo Fecha Desde está seleccioado, hacer click en el campo
				if (driver.findElement(By.id("ctl00_ContentZone_FilterMovementDate_chk_dates")).isSelected()) {
					elementClick("ctl00_ContentZone_FilterMovementDate_chk_dates");
				}
				
				//TC BOHost_gestionIngresos - Movimientos: Paso 7.- Hacer click en el botón Buscar
				elementClick(searchButton);
				Thread.sleep(3000);						
				String recordDis = getText("//*[@id='content']/div[2]/div/div[2]/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");  			  		
				selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));				  		
				takeScreenShot("E:\\Selenium\\","bankAccountMovementsPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountMovementsPage.jpeg");					
					
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void bankAccountRevenue() throws Exception{
			action = new Actions(driver);
			try {
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountRevenuePage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountRevenuePage.jpeg");
				if (driver.getPageSource().contains("No se pueden realizar movimientos")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(4,"No se puede hacer "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					bankErr=true;
					return;
				}
				//TC BOHost_gestionIngresos - Ingresos: Paso 6.- Ya en la panrtalla de ingresos a la cuenta llenar todos los campos corresdpondientes
				
				if (ranNumbr(0,1)==0) {
					SendKeys("ctl00_ContentZone_MovementDate_box_date",dateSel(2019,2019));
					SendKeys("ctl00_ContentZone_MovementDate_box_hour",hourFormat(0,23,0,59));	
				}				
				Thread.sleep(1000);				
				valueFormatted(depositValue=ranNumbr(5000,999999),decimal=ranNumbr(0,99));
				action.click(driver.findElement(By.id("ctl00_ContentZone_MovementAmount_txt_formated"))).sendKeys(String.valueOf(valwithDecimal)).build().perform();;
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_MovementDescription_box_data","Se ha hecho un ingreso de: "+valueFormatted);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountRevenueDone"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountRevenueDone.jpeg");
				//TC BOHost_gestionIngresos - Ingresos: Paso 7.- Hacer click en el botón Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");				
				Thread.sleep(2000);
				if (driver.getPageSource().contains("La fecha del movimiento no puede ser futura") || driver.getPageSource().contains("La fecha del movimiento debe ser mayor")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(6,"No se puede hacer "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);					
					bankErr = true;
					return;
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		public static void bankAccountPayment() throws Exception{
			action = new Actions(driver);
			try {				
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountPaymentPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountPaymentPage.jpeg");
				if (driver.getPageSource().contains("No se pueden realizar movimientos")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(4,"No se puede hacer "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					bankErr=true;
					return;
				}				
				//TC BOHost_gestionIngresos - Abono: Paso 6.- Ya en la panrtalla de Abono desde la cuenta llenar todos los campos corresdpondientes
				if (ranNumbr(0,1)==0) {
					SendKeys("ctl00_ContentZone_MovementDate_box_date",dateSel(2019,2019));
					SendKeys("ctl00_ContentZone_MovementDate_box_hour",hourFormat(0,23,0,59));	
				}				
				Thread.sleep(1000);				
				valueFormatted(depositValue=ranNumbr(5000,999999),decimal=ranNumbr(0,99));
				action.click(driver.findElement(By.id("ctl00_ContentZone_MovementAmount_txt_formated"))).build().perform();
				action.sendKeys(String.valueOf(valwithDecimal)).build().perform();;
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_MovementDescription_box_data","Se ha hecho un abono de: "+valueFormatted);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountPaymentDone"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountPaymentDone.jpeg");
				//TC BOHost_gestionIngresos - Abono: Paso 7.- Hacer click en el botón Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("La fecha del movimiento no puede ser futura") || driver.getPageSource().contains("La fecha del movimiento debe ser mayor")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(6,"No se puede hacer "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					bankErr = true;
					return;
				}		
				
					
				Thread.sleep(2000);
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void bankAccountTransfer() throws Exception{
			action = new Actions(driver);
			try {
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountTransferPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountTransferPage.jpeg");
				if (driver.getPageSource().contains("No se pueden realizar movimientos")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(4,"No se puede hacer un "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					bankErr=true;
					return;
				}
				//TC BOHost_gestionIngresos - Traspaso: Paso 6.- Ya en la pantalla transferencia a otra cuenta, llenar todos los datos correspondientes
				if (ranNumbr(0,1)==0) {
					SendKeys("ctl00_ContentZone_MovementDate_box_date",dateSel(2019,2019));
					SendKeys("ctl00_ContentZone_MovementDate_box_hour",hourFormat(0,23,0,59));	
				}				
				Thread.sleep(1000);				
				valueFormatted(depositValue=ranNumbr(5000,999999),decimal=ranNumbr(0,99));
				action.click(driver.findElement(By.id("ctl00_ContentZone_MovementAmount_txt_formated"))).sendKeys(String.valueOf(valwithDecimal)).build().perform();;
				Thread.sleep(1000);

				//TC BOHost_gestionIngresos - Traspaso: Paso 7.- Seleccionar una cuenta destino en el campo cuenta de destino
				List <WebElement> accountSelected = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_transferAccounts_cmb_dropdown"))).getOptions();
				selectAccount = ranNumbr(0,accountSelected.size()-1);
						if (selectAccount <0) {
							selectAccount = 0;
						}
				new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_transferAccounts_cmb_dropdown"))).selectByIndex(selectAccount);
				bankDestination = accountSelected.get(selectAccount).getText();
				actualResults.set(6, "Se ha seleccionado la cuenta destino "+bankDestination+" para la transferencia");
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_MovementDescription_box_data","Se ha hecho un traspaso de: "+valueFormatted);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountTransferDone"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountTransferDone.jpeg");
				
				//TC BOHost_gestionIngresos - Traspaso: Paso 8.- Hacer click en el botón Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
				Thread.sleep(2000);				
				if (driver.getPageSource().contains("La fecha del movimiento no puede ser futura") || driver.getPageSource().contains("La fecha del movimiento debe ser mayor")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(7,"No se puede hacer un "+ingresosOption[opSel]+ " en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					bankErr = true;
					return;
				}
				Thread.sleep(2000);
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void bankAccountPendingDeposits() throws Exception{
			action = new Actions(driver);
			try {
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountPendingDepositsPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountPendingDeposits.jpeg");
				if (driver.getPageSource().contains("No se pueden realizar movimientos")) {
					errorText = getText("//*[@id='toast-container']/div/div");		
					actualResults.set(4,"No se puede hacer un Traslado a cuenta en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					for (int i=5;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					bankErr=true;
					return;
				}				
				//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 6.- Si el campo Fecha Desde está seleccionado hacer click en el campo para buscar todos los ingresos pendientes hasta la fecha
				if (driver.findElement(By.id("ctl00_ContentZone_PendingDepositDateFilter_chk_dates")).isSelected()) {
					elementClick("ctl00_ContentZone_PendingDepositDateFilter_chk_dates");			
					//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 7.- Hacer click en el botón Buscar
					elementClick(searchButton);
				}				
				
				Thread.sleep(1000);
				//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 8.-Seleccionar cualquiera de los ingresos disponibles
				itemSearch();
				if (noItemFound==true) {
					noPendingTransfer = true;					
					return;
				}				
				//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 9.-Hacer click en el botón Trasladar a cuenta
				elementClick("ctl00_ContentZone_BtnRegisterPendingDeposit");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccountrasladoPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccountrasladoPage.jpeg");
				//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 10.-Llenar todos los campos correspondientes
				dateNew = driver.findElement(By.id("ctl00_ContentZone_MovementDate_box_date")).getAttribute("value")+" "+driver.findElement(By.id("ctl00_ContentZone_MovementDate_box_hour")).getAttribute("value");
				if (ranNumbr(0,1)==0) {
					SendKeys("ctl00_ContentZone_MovementDate_box_date",dateSel(2019,2019));
					SendKeys("ctl00_ContentZone_MovementDate_box_hour",hourFormat(0,23,0,59));	
				}				
				Thread.sleep(1000);				
				valueFormatted(depositValue=ranNumbr(5000,999999),decimal=ranNumbr(0,99));
				action.click(driver.findElement(By.id("ctl00_ContentZone_MovementAmount_txt_formated"))).build().perform();
				action.sendKeys(String.valueOf(valwithDecimal)).build().perform();;
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_MovementDescription_box_data","Traslado a cuenta por valor de: "+valueFormatted);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","bankAccounttrasladoDone"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","bankAccounttrasladoDone.jpeg");
				
				//TC BOHost_gestionIngresos - Ingresos Pendientes: Paso 11.- Hacer click en el boton Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("La fecha del movimiento no puede ser futura") || driver.getPageSource().contains("La fecha del movimiento debe ser mayor")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(11,"No se puede hacer un Traslado a cuenta en la cuenta con la entidad: "+bankNameNew+" y con el IBAN: "+bankAccountESNew+" debido a: "+errorText);
					bankErr = true;
					return;
				}
				Thread.sleep(2000);
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		
		public static String accountBankCalculation(int numb1, int numb2, int numb3, int numb4, int numb5) throws Exception{
			try {
				String accountBankNumr = String.format("%04d", numb1)+String.format("%04d", numb2)+String.format("%04d", numb3)+String.format("%04d", numb4)+String.format("%04d", numb5)+"142800"; 
				String accountBankNumber = String.format("%04d", numb1)+String.format("%04d", numb2)+String.format("%04d", numb3)+String.format("%04d", numb4)+String.format("%04d", numb5);
				BigInteger accountNum1 = new BigInteger(accountBankNumr);
				BigInteger accountNum2 = new BigInteger("97");						
				BigInteger esNumberPartial = accountNum1.mod(accountNum2);
				String numbES = String.valueOf(esNumberPartial);
				int esNumberTotal = 98-(Integer.parseInt(numbES));
				bankAccountES = "ES"+String.format("%02d", esNumberTotal)+accountBankNumber; 				
				return bankAccountES;
									
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}

		public static void itemSearchedandSelect() throws Exception{
			Thread.sleep(1000);
	  		String recordDis = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[1]/td[2]/div/div/table/tbody/tr/td[3]/span");
	  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
			if (selRecord>0) {
				recordDis = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[1]/td[2]/div/div/table/tbody/tr/td[3]/span");
				selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));								
				WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));			
				selectedRow = 1;
				int countRow = 1;
				if (tablerows.size()<2) {
					noItemFound = true;
					return;
				}
				for (int i =1; i<=selRecord;i++) {						
					tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
					tablerows = tableres.findElements(By.tagName("tr"));						
					if (selRecord>=tablerows.size()) {
						if (driver.findElement(By.cssSelector("#content > div.ng-scope > div > div:nth-child(1) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > table > tbody > tr > td:nth-child(4) > input")).isEnabled()){
							elementClick("#content > div.ng-scope > div > div:nth-child(1) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > table > tbody > tr > td:nth-child(4) > input");
						}else {
							noItemFound = true;
							return;
						}
						selectedRow=1;
						countRow = countRow+tablerows.size()-2;							
					}						
					bankNameNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[2]");					
					recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[1]/input")).getAttribute("id");
					bankAccountESNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[3]");
					depositValueNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[4]");										
						for (selectedRow=1;selectedRow<=tablerows.size()-1;selectedRow++) {															
							if (countRow == selRecord) {														
								elementClick(recordFound);					
								i = selRecord+1;
								break;									
							}else {
								countRow=countRow+1;
							}
							if (countRow>selRecord) {
								selectedRow=1;
								countRow = countRow-2;
							}
							bankNameNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[2]");					
							recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[1]/input")).getAttribute("id");
							bankAccountESNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[3]");
							depositValueNew = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[4]");
							Thread.sleep(500);						
						}
						Thread.sleep(100);				
				}
			}else {
				noItemFound = true;
				return;
			}
		}
		public static void itemSearch() throws Exception{
	  		String recordDis = getText("//*[@id='content']/div[2]/div/div[5]/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");	  	//
	  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
			if (selRecord>0) {
				selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));								
				WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[5]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));			
				selectedRow = 1;
				int countRow = 1;
				if (tablerows.size()<2) {
					noItemFound = true;
					return;
				}
				for (int i =1; i<=selRecord;i++) {						
					tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[5]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
					tablerows = tableres.findElements(By.tagName("tr"));						
					if (selRecord>=tablerows.size()) {
						if (driver.findElement(By.cssSelector("#content > div.ng-scope > div > div:nth-child(5) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > table > tbody > tr > td:nth-child(4) > input")).isEnabled()){
							elementClick("#content > div.ng-scope > div > div:nth-child(5) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > table > tbody > tr > td:nth-child(4) > input");
						}else {
							noItemFound = true;
							return;
						}
						selectedRow=1;
						countRow = countRow+tablerows.size()-2;							
					}						
					dateSelected = getText("//*[@id='content']/div[2]/div/div[5]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[2]");
						for (selectedRow=1;selectedRow<=tablerows.size()-1;selectedRow++) {															
							if (countRow == selRecord) {																
								elementClick("//*[@id='content']/div[2]/div/div[5]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[1]/input");
								i = selRecord+1;
								break;									
							}else {
								countRow=countRow+1;
							}
							if (countRow>selRecord) {
								selectedRow=1;
								countRow = countRow-2;
							}
							dateSelected = getText("//*[@id='content']/div[2]/div/div[5]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selectedRow+"]/td[2]");												
							Thread.sleep(500);						
						}
						Thread.sleep(100);				
				}
			}else {
				noItemFound = true;
				return;
			}
		}
		public static String valueFormatted(int valInteger, int valDecimal) {
			String valtotal;
			if (valDecimal<0) {
				valDecimal=0;
			}
			if (valDecimal<10) {
				valwithDecimal = String.valueOf(valInteger)+"0"+String.valueOf(valDecimal);
				valtotal= String.valueOf(valInteger)+"."+"0"+valDecimal;
			}else {
				valwithDecimal=  String.valueOf(valInteger)+String.valueOf(valDecimal);
				valtotal = String.valueOf(valInteger)+"."+valDecimal;
			}			
			double totalValue = Double.parseDouble(valtotal);
			DecimalFormat formateador = new DecimalFormat("###,###.##");
			valueFormatted = formateador.format(totalValue)+"€";
			return valueFormatted;
		}
	
}