package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


public class BOHost_accountInvoicesFeatures {		
		private static String [] invoicesOption = {"pago atípico", "pagos electronicos", "abonos", "factura negativa"};
		private static int opSel;
		private static float amount;
		private static float Discount;		
		private static float IVA;
		private static boolean noInvoices = false;
		private static float totalAmount;		
		private static int transitsSelected;
		private static DecimalFormat ft;
		private static String invoiceSelected;
		private static String invoiceDate;
		private static String totalPayment;
		private static boolean noTransits = false;
		private static boolean noPayment = false;
		private static boolean noPaymentCreated = false;
		private static boolean noPanAssigned = false;
		private static boolean noNegativeStatement = false;
		private static String selectedMonth;
		private static int step;
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
		public void accountInvoicesFeaturesInit() throws Exception{
			Thread.sleep(1000);
			opSel = ranNumbr(0,invoicesOption.length-1);
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Pago Atipico", testNameSelected);
				testPlanReading(3,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Pagos Electronicos", testNameSelected);
				testPlanReading(3,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Abonos Pagos Electronicos", testNameSelected);
				testPlanReading(3,10,12,13);
			}
			if (opSel==3) {
				configurationSection("Host", testNameSelected+" - Factura Negativa", testNameSelected);
				testPlanReading(3,15,17,18);
			}
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			
			accountInvoicesFeatures();
			Thread.sleep(1000);
			if (accountClosed==true) {
				actualResults.set(5, "No se puede crear: "+invoicesOption[opSel]+" en la cuenta "+accountNumbr+" porque está cerrada");
				for (int i=6;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede crear: "+invoicesOption[opSel]+" en la cuenta "+accountNumbr+" porque está cerrada");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
				return;			
			}
			
			if (noPaymentCreated==true) {					
				driver.close();
				testLink();
				System.out.println("No se puede crear "+invoicesOption[opSel]+" debido a que: "+errorText+" en el mes: "+selectedMonth+" de la cuenta No.: "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			
			if (noPayment==true) {
				actualResults.set(10,"No se puede crear "+invoicesOption[opSel]+" debido a que: "+errorText+" en el mes: "+selectedMonth+" de la cuenta No.: "+accountNumbr);
				driver.close();
				testLink();
				System.out.println("No se puede crear "+invoicesOption[opSel]+" debido a que: "+errorText+" en el mes: "+selectedMonth+" de la cuenta No.: "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			if (noPanAssigned==true) {
				actualResults.set(step,"No se puede crear "+invoicesOption[opSel]+" debido a que no hay tarjetas/OBE asignada a la cuenta No.: "+accountNumbr);			
				for (int i=step+1;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede crear "+invoicesOption[opSel]+" debido a que no hay tarjetas/OBE asignada a la cuenta No.: "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			
			if (noTransits==true) {
				actualResults.set(10,"No se ha podido crear efectuar pagos de abono debido a que no hay transitos para abonar o los tránsitos ya han sido abonados anteriormente con los OBEs/Tarjetas seleccionadas en la cuenta No.: "+accountNumbr);
				for (int i=11;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se ha podido crear efectuar pagos de abono debido a que no hay transitos para abonar o los tránsitos ya han sido abonados anteriormente con los OBEs/Tarjetas seleccionadas en la cuenta No.: "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			if (noNegativeStatement==true) {
				actualResults.set(9,"No se ha generado una factura negativa, porque la factura No.: "+invoiceSelected+" ya está abonada para la cuenta No.: "+accountNumbr+" asociada");
				actualResults.set(10, "N/A");
				executionResults.set(10, "");
				driver.close();
				testLink();
				System.out.println("No se ha generado una factura negativa, porque la factura No.: "+invoiceSelected+" ya está abonada para la cuenta No.: "+accountNumbr+" asociada");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			if (noInvoices==true) {
				actualResults.set(7,"No se puede generar factura negativa porque no hay facturas generadas en la cuenta No.: "+accountNumbr);
					for (int i=8;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}				
				driver.close();
				testLink();
				System.out.println("No se puede generar factura negativa porque no hay facturas generadas en la cuenta No.: "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			
			if (invoicesOption[opSel].equals("pago atípico")) {
				actualResults.set(9,"Se ha generado una factura de pago atípico con el número: "+invoiceSelected+" en la cuenta: "+accountNumbr+" por un valor de: "+ft.format(amount)+" € con un Descuento de: "+ft.format(Discount)+" € más el IVA: "+ft.format(IVA)+" € para un total de: "+ft.format(totalAmount)+" € y se genera el PDF correspondiente a la factura");
				System.out.println("Se ha generado una factura de pago atípico con el número: "+invoiceSelected+" en la cuenta: "+accountNumbr+" por un valor de: "+ft.format(amount)+" € con un Descuento de: "+ft.format(Discount)+" € más el IVA: "+ft.format(IVA)+" € para un total de: "+ft.format(totalAmount)+" € y se genera el PDF correspondiente a la factura");
			}
			if (invoicesOption[opSel].equals("pagos electronicos")) {
				actualResults.set(10,"Se ha generado una factura de pagos electrónicos con el número: "+invoiceSelected+" con fecha: "+invoiceDate+" por un monto de: "+totalPayment+" en la cuenta: "+accountNumbr);
				System.out.println("Se ha generado una factura de pagos electrónicos con el número: "+invoiceSelected+" con fecha: "+invoiceDate+" por un monto de: "+totalPayment+" en la cuenta: "+accountNumbr);
			}
			if (invoicesOption[opSel].equals("abonos")) {
				actualResults.set(12,"Se ha creado un abono de pagos electrónicos para "+transitsSelected+" tránsito/s con el número de factura: "+invoiceSelected+" en fecha: "+invoiceDate+" por un monto de: "+totalPayment+" en la cuenta: "+accountNumbr);
				System.out.println("Se ha creado un abono de pagos electrónicos para "+transitsSelected+" tránsito/s con el número de factura: "+invoiceSelected+" en fecha: "+invoiceDate+" por un monto de: "+totalPayment+" en la cuenta: "+accountNumbr);
			}
			if (invoicesOption[opSel].equals("factura negativa")) {
				actualResults.set(10,"Se ha generado una factura negativa a la factura número: "+invoiceSelected+" en la cuenta: "+accountNumbr);
				System.out.println("Se ha generado una factura negativa a la factura número: "+invoiceSelected+" en la cuenta: "+accountNumbr);
			}
			driver.close();
			testLink();
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			
		}
		public static void accountInvoicesFeatures() throws Exception{
			action = new Actions(driver);
			try {
				//Paso 1.- Entrar a la página de Login del BO Host de R3R5Ciralsa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}
													
				//Paso 2.- Loguearse con el usuario 00001/00001
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOHostPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(500);
				
				//Paso 3.- Hacer click en Gestión de cuentas y luego Seleccionar cuentas
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(500);
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
				takeScreenShot("E:\\Selenium\\","selectAccounttPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","selectAccounttPage.jpeg");
				
				//Paso 4.- Ir a la lista desplegable Tipo de Cuenta y seleccionar la opción Cliente
				new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByIndex(1);
				//Paso 5.- Hacer click en el botón Buscar
				elementClick(searchButton);
				//Paso 6.- Hacer click en la cuenta deseada
				selectAccount();				
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				takeScreenShot("E:\\Selenium\\","accountMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMainPage.jpeg");				
				if (driver.getPageSource().contains("CUENTA CERRADA")){
					takeScreenShot("E:\\Selenium\\","closedAccount"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","closedAccount.jpeg");
					accountClosed = true;
					return;
				}
				actualResults.set(5,"Aparece la pantalla de edición de la cuenta: "+accountNumbr+" con su informacióin correspondiente");
				
				//Paso 7.- Si la cuenta no está cerrada, hacer click en el botón Facturación
				elementClick("ctl00_ContentZone_BtnStatements");
				takeScreenShot("E:\\Selenium\\","invoicesMaingPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","invoicesMaingPage.jpeg");
				switch (invoicesOption[opSel]) {
					case "pago atípico":					Thread.sleep(1000);
															crearPagoAtipico();
															break;
					case "pagos electronicos": 				Thread.sleep(1000);
															crearPagosElectronicos();
															break;
					case "abonos":							Thread.sleep(1000);
															crearAbonosPagosElectronicos();
															break;
					case "factura negativa":				Thread.sleep(1000);
															crearFacturaNegativa();
															break;
				}
				
				Thread.sleep(5000);
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
		}
	}
	public static void crearPagoAtipico() throws Exception{
		action = new Actions(driver);
		try {			
			Thread.sleep(1000);
			
			//Paso 8.- Ya en la pantalla de Facturación hacer click en el botón Crear factura de pago atícipico
			elementClick("ctl00_ContentZone_BtnCreateAtypicalPayment");
			
			//Paso 9.- Llenar todos los campos correspondientes
			int payment = ranNumbr(100,10000);
			int decimal = ranNumbr(0,99);
			if (decimal<0) {
				decimal=0;
			}
			takeScreenShot("E:\\Selenium\\","createAtipicalPayPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","createAtipicalPayPage.jpeg");
			String totalPay;
			
			if (decimal<9) {
				totalPay = String.valueOf(payment)+"0"+String.valueOf(decimal);
				amount = Float.parseFloat(String.valueOf(payment)+"."+"0"+String.valueOf(decimal));
			}else {
				totalPay = String.valueOf(payment)+"."+String.valueOf(decimal);
				amount = Float.parseFloat(String.valueOf(payment)+"."+String.valueOf(decimal));
			}
			action.click(driver.findElement(By.id("ctl00_ContentZone_dlgAmount_txt_formated"))).sendKeys(totalPay).build().perform();
			Thread.sleep(1000);
			
			payment = ranNumbr(10,500);
			decimal = ranNumbr(0,99);
			if (decimal<0) {
				decimal=0;
			}
			String discountTotal;
			if (decimal<9) {
				discountTotal = String.valueOf(payment)+"0"+String.valueOf(decimal);
				Discount = Float.parseFloat(String.valueOf(payment)+"."+"0"+String.valueOf(decimal));
			}else {
				discountTotal = String.valueOf(payment)+"."+String.valueOf(decimal);
				Discount = Float.parseFloat(String.valueOf(payment)+"."+String.valueOf(decimal));
			}
			action.click(driver.findElement(By.id("ctl00_ContentZone_dlgDiscount_txt_formated"))).sendKeys(discountTotal).build().perform();
			Thread.sleep(500);			
			amount = amount - Discount;				
			IVA = (float) (amount * 0.21);
			totalAmount = amount + IVA;			
			Thread.sleep(1000);
			ft = new DecimalFormat("###,###.00");
			SendKeys("dlgText", "Se ha hecho un pago Atípico a la cuenta: "+accountNumbr+" por un valor de: "+ft.format(amount)+" € con un Descuento de: "+ft.format(Discount)+" € más el IVA: "+ft.format(IVA)+" € para un total de: "+ft.format(totalAmount)+" €");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			
			//Paso 10.- Hacer click en el botón Aceptar
			elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
			if (driver.getPageSource().contains("Error desconocido") || driver.getPageSource().contains("Unknown Error")) {				
				errorText = getText("//*[@id='toast-container']/div/div");				
				noPaymentCreated = true;
				actualResults.set(9,"No se ha podido hacer "+invoicesOption[opSel]+" debido a: "+errorText+" en la cuenta No.: "+accountNumbr);
				return;
			}
			Thread.sleep(9000);
			takeScreenShot("E:\\Selenium\\","atipicalPDF"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","atipicalPDF.jpeg");
			invoiceSelected = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[3]");
			Thread.sleep(1000);
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	
	public static void crearPagosElectronicos() throws Exception{
		action = new Actions(driver);
		try {
			Thread.sleep(1000);
			//Paso 8.- Ya en la pantalla de Facturación hacer click en el botón Crear factura pagos electrónicos
			elementClick("ctl00_ContentZone_BtnCreateElectronicStatement");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","createElectronicPayPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","createElectronicPayPage.jpeg");
			String panAvailable = getText("//*[@id='electronicStatementDialog']/div[3]/div[1]/table/tbody/tr/td[3]/span");						
			if (Integer.parseInt(panAvailable.substring(panAvailable.indexOf("de ")+3))==0) {
				step = 9;
				takeScreenShot("E:\\Selenium\\","noPanAssigned"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","noPanAssigned.jpeg");
				noPanAssigned = true;
				return;
			}
			
			//Paso 9.- Elegir el mes correspondiente a dicho pago 
			selectDropDown("ctl00_ContentZone_cmb_memberMonth_cmb_dropdown");
			WebElement monthSelected = new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_memberMonth_cmb_dropdown"))).getFirstSelectedOption();
			selectedMonth= monthSelected.getText();
			actualResults.set(8,"Se ha seleccionado el mes "+selectedMonth+" para dicho pago electrónico");
			int clickedPan = ranNumbr(1,Integer.parseInt(panAvailable.substring(panAvailable.indexOf("de ")+3)));
			//Pago 10.- 
			if (clickedPan== Integer.parseInt(panAvailable.substring(panAvailable.indexOf("de ")+3))){
				elementClick("ctl00_ContentZone_AddAllMembersBtn");
				actualResults.set(9, "Se ha hecho click en el botón Añadir todas y se ha elegido todas las tarjetas asignadas de la cuenta");
			}else {	
				int clickedOBE = 1;
				for (int i=1;i<=clickedPan;i++) {
					
					int transClick= ranNumbr(1,clickedPan);					
					String transClicked = driver.findElement(By.xpath("//*[@id='electronicStatementDialog']/div[3]/div[2]/table/tbody/tr["+transClick+"]/td[5]/img")).getAttribute("class");					
					if (!transClicked.contains("ng-hide")) {
						elementClick("//*[@id='electronicStatementDialog']/div[3]/div[2]/table/tbody/tr["+transClick+"]/td[5]/img");
						actualResults.set(9, "Se ha elegido "+clickedOBE+" tarjetas/OBE asignadas de la cuenta");
						clickedOBE = clickedOBE+1;
					}					
					Thread.sleep(500);
					
				}
			}
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
			Thread.sleep(4000);
			if (driver.getPageSource().contains("Hay tránsitos")) {
				errorText = getText("//*[@id='toast-container']/div/div");
				noPayment = true;
				return;
			}
			if (driver.getPageSource().contains("Error: No se han")) {
				errorText = getText("//*[@id='toast-container']/div/div");
				noPayment = true;
				return;
			}
			if (driver.getPageSource().contains("El mes seleccionado ya se ha facturado")) {				
				errorText = getText("//*[@id='toast-container']/div/div");
				noPayment = true;
				return;
			}
			
			
			if (driver.getPageSource().contains("Error desconocido") || driver.getPageSource().contains("Unknown Error")) {
				actualResults.set(10,"No se puede crear "+invoicesOption[opSel]+" debido a que: "+errorText+" en el mes: "+selectedMonth+" de la cuenta No.: "+accountNumbr);
				errorText = getText("//*[@id='toast-container']/div/div");
				noPaymentCreated = true;
				return;
			}
			takeScreenShot("E:\\Selenium\\","pagoElectronicoCreated"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","pagoElectronicoCreated.jpeg");
			invoiceSelected = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[3]");
			invoiceDate = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[2]");
			totalPayment = getText ("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[8]");
			Thread.sleep(1000);
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	public static void crearAbonosPagosElectronicos() throws Exception{
		action = new Actions(driver);
		try {
			Thread.sleep(1000);
			
			//Paso 8.- Ya en la pantalla de Facturación hacer click en el botón Crear abono pagos electrónicos
			elementClick("ctl00_ContentZone_BtnCreateElectronicNegativeStatement");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","createElectronicPayPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","createElectronicPayPage.jpeg");
			//Paso 9.- Ya en la pantalla de Crear abono pagos electrónicos, si hay Tarjetas u OBEs asignada a la cuenta aparecera una lista de Tarjetas u OBES disponibles, seleccionar una, varias o todas las OBEs/Tarjetas, si quiere seleccionar todas, hacer click botón seleccionar todas
			if (driver.getPageSource().contains("La cuenta no tiene")) {
				step = 8;
				errorText = getText("//*[@id='toast-container']/div/div");
				noPanAssigned = true;
				return;
			}
			
			memberSearch();			
			takeScreenShot("E:\\Selenium\\","panSelectedPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","panSelectedPage.jpeg");
			
			//Paso 10.- Una vez seleccionadas las Tarjetas/OBEs, hacer click en el botón Buscar Transacciones
			elementClick("ctl00_ContentZone_BtnSearchTransits");
			takeScreenShot("E:\\Selenium\\","transitsSelectionPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","transitsSelectionPage.jpeg");
			Thread.sleep(1000);
			//11.- Si hay transacciones disponibles, aparecerá una lista para seleccionar las transacciones, seleccioar una, varias o todas las transacciones disponibles (las que no tienen la imagen de +)
			transactionsSearch();
			 if (noItemFound == true) {
				 noTransits = true;
				return; 
			 }
			WebElement transactions = driver.findElement(By.xpath("//*[@id='transactionsDiv']/div/table"));
			List <WebElement> transCount = transactions.findElements(By.tagName("tr"));			
			if (transCount.size()<2) {
				noTransits = true;
				return;
			}							
			transitsSelected = transCount.size()-1;
			elementClick("ctl00_ButtonsZone_BtnCreate_IB_Button");
			Thread.sleep(500);
			elementClick("popup_ok");
			Thread.sleep(500);
			if (driver.getPageSource().contains("Error desconocido") || driver.getPageSource().contains("Unknown Error") || driver.getPageSource().contains("Object reference not set")) {				
				errorText = getText("//*[@id='toast-container']/div/div");
				actualResults.set(10,"No se puede crear "+invoicesOption[opSel]+" debido a que: "+errorText+" en la cuenta No.: "+accountNumbr);
				actualResults.set(11, "N/A");
				executionResults.set(11, "");
				noPaymentCreated = true;
				return;
			}
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);			
			takeScreenShot("E:\\Selenium\\","abonoPDFPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","abonoPDF.jpeg");
			invoiceSelected = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[3]");
			invoiceDate = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[2]");
			totalPayment = getText ("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[8]");
			Thread.sleep(1000);
			
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	public static void crearFacturaNegativa() throws Exception{
		action = new Actions(driver);		
		try {
			Thread.sleep(1000);
			if (driver.findElement(By.id("ctl00_ContentZone_FilterStatementDate_chk_dates")).isSelected()) {
				elementClick("ctl00_ContentZone_FilterStatementDate_chk_dates");
			}
			elementClick(searchButton);
			Thread.sleep(2000);
			invoicesSearch();
			if (noItemFound==true) {
				noInvoices=true;
				return;
			}
			invoiceSelected = optionSelectedId;
			elementClick("ctl00_ContentZone_BtnCreateNegative");
			if (driver.getPageSource().contains("La factura ya estaba")) {				
				noNegativeStatement = true;
				return;
			}
			elementClick("popup_ok");
			Thread.sleep(500);
			if (driver.getPageSource().contains("Error desconocido") || driver.getPageSource().contains("Unknown Error") || driver.getPageSource().contains("Object reference not set")) {
				errorText = getText("//*[@id='toast-container']/div/div");
				noPaymentCreated = true;
				return;
			}
			Thread.sleep(7000);
			takeScreenShot("E:\\Selenium\\","facturaNegativaPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","facturaNegativaPage.jpeg");
			Thread.sleep(1000);
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
}
