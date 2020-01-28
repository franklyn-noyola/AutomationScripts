package Itata;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_insertarTransaccionesManuales{
	public static int transactionNumber;
	public static int transactionNoCreated=0;
	public static int transactionCreated=0;
	public static String transactionsId="";
	public static String highName;
	public static String plazaName;
	public static String laneName;
	private static boolean closedCycle;
	public static List <String> transactions;
	public static boolean transactionNotCreated = false;
	public static boolean noFares = false;
	public static boolean noLane = false;
	public static String [] matriNumber = {"","65432PAT","87456SIG","32145TEC","112233PAL","78112MER","43210TAG","54321JCX"};
	public static String [] TSCNumber= {"","00123456","00098765","00321456","05544663","00336655","00854633","02145688"};
	public static String transactionType;
	public static boolean errorFound=false;
	public static int payMeans;
	private static String confirmationText;
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
	public void insertarTransaccionesInit() throws Exception{
		configurationSection("Host", testNameSelected, testNameSelected);
		testPlanReading(17,0,2,3);
		borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		insertarTransacciones();
		if (noLane==true) {
			takeScreenShot("E:\\Selenium\\","notSearched"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","notSearched.jpeg");
			actualResults.set(7, "No ha se producido la búsqueda corrrespondiente debido a: "+errorText);
			for (int i=6;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("No ha se producido la búsqueda corrrespondiente debido a: "+errorText);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
		}
		if (closedCycle==true) {
			takeScreenShot("E:\\Selenium\\","cycleclosed"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","cycleclosed.jpeg");		
			actualResults.set(5,"Se ha cerrado un ciclo existente de la Autopista: "+highName+" de la Estación: "+plazaName+" de la vía: "+laneName+" y con el cobrador: 00001"+selRecord+" transacciones existentes con las trasacciones Ids: "+'\n'+transactionsId);
			for (int i=6;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Se ha cerrado un ciclo existente de la Autopista: "+highName+" de la Estación: "+plazaName+" de la vía: "+laneName+" y con el cobrador: 00001"+selRecord+" transacciones existentes con las trasacciones Ids: "+'\n'+transactionsId);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;			
		}
		if (noFares==true) {
			actualResults.set(9, "No se puede insertar transacciones manuales debido a que: "+errorText+" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName);
			for (int i=10;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("No se puede insertar transacciones manuales debido a que: "+errorText+" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
		}
		if (transactionNotCreated==true) {
			actualResults.set(9, "No se ha podido crear una transacción debido a: "+errorText);
			executionResults.set(5,"Fallado");
			for (int i=10;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			summaryBug = "No se ha podido crear una transacción debido a: "+errorText;
			erroTextBug = "No se ha podido crear una transacción debido a: "+errorText;			
			componentBug = "HM";
			severityBug = 1;
			priority = 4;
			testFailed = true;
			bugAttachment = true;
			pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\notCreatedErr.jpeg";			
			driver.close();
			testLink();
			System.out.println("No se ha podido crear una transacción debido a: "+errorText);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			fail("No se ha podido crear una transacción debido a: "+errorText);			
		}
		Thread.sleep(5000);
		
		/*if (transactionType.equals("batchTransaction")&& payMeans==0) {
			
		}*/
		
		
		if (transactionCreated>0 || (transactionType.equals("batchTransaction")&& payMeans==0)) {
			if ((transactionType.equals("batchTransaction")&& payMeans==0)) {
				String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
				selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));				
				Thread.sleep(1000);
				transactions = new ArrayList<String>();
				int transNumber = 2;
				for (int i=1;i<=selRecord;i++) {
					String transact = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+transNumber+"]/td[1]");
					transactions.add(transact);
					transNumber=transNumber+1;
				}
				for (int i=0;i<selRecord;i++) {
					transactionsId = transactions.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");				
				}
				System.out.println("Se ha añadido un Lote de "+selRecord+" transacciones  con las transacciones Id: "+'\n'+transactionsId +" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );
			}else {
			
			transactions = new ArrayList<String>();
			int transNumber = 2;
			for (int i=1;i<=transactionCreated;i++) {
				String transact = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+transNumber+"]/td[1]");
				transactions.add(transact);
				transNumber=transNumber+1;
			}
			for (int i=0;i<transactionCreated;i++) {
				transactionsId = transactions.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
				
			}
				actualResults.set(12,"Se ha insertado manualmente "+transactionCreated+" transacciones con las transacciones Id: "+'\n'+transactionsId +" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );
				System.out.println("Se ha insertado manualmente "+transactionCreated+" transacciones con las transacciones Id: "+'\n'+transactionsId +" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );	
			}	
		}else {
			if ((transactionType.equals("batchTransaction")&& payMeans==1)) {
				confirmationText = getText("ctl00_LblError");
				System.out.println(confirmationText);	
			}else {
				actualResults.set(12,"No se han creado transacciones por transacciones invalidas creadas para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );
				System.out.println("No se han creado transacciones por transacciones invalidas creadas para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName);	
			}
			
		}
		driver.close();
		testLink();
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
	
	}
	public void insertarTransacciones() throws Exception{
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
			Thread.sleep(1000);
			
			//Paso 3.- Hacer click en Transacciones y luego Insertar transacciones
			action.moveToElement(driver.findElement(By.linkText("Transacciones"))).build().perform();
			Thread.sleep(1000);
			pageSelected = "Transacciones manuales";
			elementClick(pageSelected);
			Thread.sleep(1000);		
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			
			takeScreenShot("E:\\Selenium\\","insertarTransaccionesMainPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","insertarTransaccionesMainPage.jpeg");
			//List <WebElement> highwayNumbr = new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_tollCompany_cmb_dropdown"))).getOptions();
			//List <WebElement> plazaNumbr = new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_plaza_cmb_dropdown"))).getOptions();			
			
			//Paso 4.- Ya en la pantalla Insertar Transacciones, Ir al campo desplegable Autopista y selecciona una autopista
			new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_tollCompany_cmb_dropdown"))).selectByIndex(1);
			Thread.sleep(5000);
			WebElement hihName = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_tollCompany_cmb_dropdown"))).getFirstSelectedOption();
			highName = hihName.getText();
			actualResults.set(3, "Se ha seleccionado la Concesionaria: "+highName);
			
			//Paso 5.- Seleccionar la Estación corresopndiente

			Thread.sleep(5000);
			WebElement plazaN = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_plaza_cmb_dropdown"))).getFirstSelectedOption();
			plazaName = plazaN.getText();
			actualResults.set(4, "Se ha seleccionado la Plaza: "+plazaName);		
			List <WebElement> laneOptions = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_lane_cmb_dropdown"))).getOptions();
			if (laneOptions.size()>0) {
				int lane = ranNumbr(0,laneOptions.size()-1);
				
				//Paso 6.- Seleccionar la vía correspondiente
				new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_lane_cmb_dropdown"))).selectByIndex(lane);
				Thread.sleep(5000);
				WebElement laneN = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaLane_cmb_lane_cmb_dropdown"))).getFirstSelectedOption();
				laneName = laneN.getText();
				actualResults.set(5, "Se ha seleccionado la Vía: "+laneName);
			}
			Thread.sleep(1000);
			
			//Paso 7.- Introducir el cobrador correspondiente en el campo Cobrador
			SendKeys("ctl00_ContentZone_txt_operator_box_data","00001");
			
			//Paso 8.- Hacer click en el botón Buscar
			elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
			Thread.sleep(2000);
			
			
			//Paso 9.- Si hay Ciclo abierto de transacciones y hay lista de transaciones, hacer click en el botón Cerrar Ciclo de transacciones de lo contrario Hacer click en Añadir Transacciones y añadir el número de transacciones pertinentes o requeridas
			if (driver.getPageSource().contains("No hay ningún ciclo de transacciones")) {
				int selTran = ranNumbr(0,1);
				if (selTran==0) {
					transactionType = "singleTransaction";
				}else {
					transactionType = "batchTransaction";
				}
								
			}else {
				String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
				selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));				
				Thread.sleep(1000);
				transactions = new ArrayList<String>();
				int transNumber = 2;
				for (int i=1;i<=selRecord;i++) {
					String transact = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+transNumber+"]/td[1]");
					transactions.add(transact);
					transNumber=transNumber+1;
				}
				for (int i=0;i<transactionCreated;i++) {
					transactionsId = transactions.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
					
				}
				elementClick("ctl00_ContentZone_BtnCloseTrset");
				closedCycle = true;				
				return;
			}
			if (transactionType.equals("singleTransaction")) {
			transactionNumber = ranNumbr(1,5);
			//Paso 10.- Si se ha cliqueado sobre Añadir Transacción
			for (int i=1; i<=transactionNumber;i++) {				
				elementClick("ctl00_ContentZone_BtnAddTransaction");
				Thread.sleep(2000);				
				takeScreenShot("E:\\Selenium\\","insertTransactionPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","insertTransactionPage_"+i+".jpeg");
				payMeans = ranNumbr(0,1);
				new Select (driver.findElement(By.id("ctl00_ContentZone_cmbPnlPaymentMeans_cmb_dropdown"))).selectByIndex(payMeans);
				Thread.sleep(500);
				int vehicleSel = ranNumbr(1,7);
				new Select (driver.findElement(By.id("ctl00_ContentZone_cmbPnlVehicleClass_cmb_dropdown"))).selectByIndex(vehicleSel);
				
				//Paso 11.- Ya en la pantalla Añadir Transacción, llenar todos los campos pertinentes
				if (payMeans==0) {
					SendKeys("ctl00_ContentZone_txtPnlPlate_box_data",matriNumber[vehicleSel]);	
				}else {
					SendKeys("ctl00_ContentZone_txtPnlPlate_box_data",TSCNumber[vehicleSel]);
				}
				
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_btnCheckPlate");
				Thread.sleep(500);
				//if (driver.getPageSource().contains("ERROR"))
				//Paso 12.- Hacer cilck en el botón Añadir Transacción
				elementClick("ctl00_ContentZone_BtnPnlAddTransaction");
				Thread.sleep(5000);
				if (driver.getPageSource().contains("La operación ha fallado")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					transactionNotCreated = true;
					return;
				}
				if (driver.getPageSource().contains("Revise el tiempo de pase")||driver.getPageSource().contains("entrada debe ser un momento anterior al tiempo de pase de salida")) {
					elementClick("ctl00_ContentZone_BtnPnlCancel");
					Thread.sleep(3000);				
				}else {
					transactionCreated = transactionCreated+1;
				}
			}
		}else {
			Thread.sleep(500);
			elementClick("ctl00_ContentZone_BtnAddTrBatch");
			Thread.sleep(300);
			payMeans = ranNumbr(0,1);
			new Select (driver.findElement(By.id("ctl00_ContentZone_cmbPnlBatchPaymentMeans_cmb_dropdown"))).selectByIndex(payMeans);
			Thread.sleep(500);
			int vehicleSel = ranNumbr(1,7);
			new Select (driver.findElement(By.id("ctl00_ContentZone_cmbPnlBatchVehicleClass_cmb_dropdown"))).selectByIndex(vehicleSel);
			if (payMeans==0) {
				elementClick("ctl00_ContentZone_BtnPnlBatchAddTransaction");
				Thread.sleep(5000);
			}else {
				driver.findElement(By.name("ctl00$ContentZone$FileSelect")).sendKeys("C:\\archivosPruebas\\20190710050000_00001_TSC.csv");
				elementClick("ctl00_ContentZone_BtnPnlBatchAddTransaction");
				Thread.sleep(5000);
			}
		}
			Thread.sleep(3000);			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail (e.getMessage());
			throw(e);
		}
	}
		
}