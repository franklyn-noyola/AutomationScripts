package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

public class BOHost_insertarTransacciones{
	private String testNameSelected = this.getClass().getSimpleName();
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
		testPlanReading(10,0,2,3);
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
			actualResults.set(8,"Se ha cerrado un ciclo existente de la Autopista: "+highName+" de la Estación: "+plazaName+" de la vía: "+laneName+" y con el cobrador: 00001"+selRecord+" transacciones existentes con las trasacciones Ids: "+'\n'+transactionsId);
			for (int i=9;i<actualResults.size();i++) {
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
			actualResults.set(11, "No se puede insertar transacciones manuales debido a que: "+errorText+" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName);
			driver.close();
			testLink();
			System.out.println("No se puede insertar transacciones manuales debido a que: "+errorText+" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
		}
		if (transactionNotCreated==true) {
			actualResults.set(11, "No se ha podido crear una transacción debido a: "+errorText);
			executionResults.set(5,"Fallado");
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
		
		
		if (transactionCreated>0) {			
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
			actualResults.set(11,"Se ha insertado manualmente "+transactionCreated+" transacciones con las transacciones Id: "+'\n'+transactionsId +" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );
			System.out.println("Se ha insertado manualmente "+transactionCreated+" transacciones con las transacciones Id: "+'\n'+transactionsId +" para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );
		}else {
			actualResults.set(11,"No se han creado transacciones por transacciones invalidas creadas para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName );
			System.out.println("No se han creado transacciones por transacciones invalidas creadas para la Autopista: "+highName+" con la Estación: "+plazaName+" y la vía: "+laneName);
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
			pageSelected = "Insertar transacciones";
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
			int highway = 1;//ranNumbr(0,highwayNumbr.size()-1);
			int plaza = 2;//ranNumbr(0,plazaNumbr.size()-1);
			
			//Paso 4.- Ya en la pantalla Insertar Transacciones, Ir al campo desplegable Autopista y selecciona una autopista
			new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_tollCompany_cmb_dropdown"))).selectByIndex(highway);
			Thread.sleep(5000);
			WebElement hihName = new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_tollCompany_cmb_dropdown"))).getFirstSelectedOption();
			highName = hihName.getText();
			actualResults.set(3, "Se ha seleccionado la Autopista: "+highName);
			
			//Paso 5.- Seleccionar la Estación corresopndiente
			new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_plaza_cmb_dropdown"))).selectByIndex(plaza);
			Thread.sleep(5000);
			WebElement plazaN = new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_plaza_cmb_dropdown"))).getFirstSelectedOption();
			plazaName = plazaN.getText();
			actualResults.set(4, "Se ha seleccionado la Estación: "+plazaName);
		
			List <WebElement> laneOptions = new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_lane_cmb_dropdown"))).getOptions();
			if (laneOptions.size()>0) {
				int lane = ranNumbr(0,laneOptions.size()-1);
				
				//Paso 6.- Seleccionar la vía correspondiente
				new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_lane_cmb_dropdown"))).selectByIndex(lane);
				Thread.sleep(5000);
				WebElement laneN = new Select(driver.findElement(By.id("ctl00_ContentZone_cplCompanyPlazaLane_cmb_lane_cmb_dropdown"))).getFirstSelectedOption();
				laneName = laneN.getText();
				actualResults.set(5, "Se ha seleccionado la Vía: "+laneName);
			}
			Thread.sleep(1000);
			
			//Paso 7.- Introducir el cobrador correspondiente en el campo Cobrador
			SendKeys("ctl00_ContentZone_txtCollector_box_data","00001");
			
			//Paso 8.- Hacer click en el botón Buscar
			elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
			Thread.sleep(2000);
			if (driver.getPageSource().contains("No se ha seleccionado vía")) {
				errorText = getText("//*[@id='toast-container']/div/div");
				noLane=true;
				return;
			}
			
			//Paso 9.- Si hay Ciclo abierto de transacciones y hay lista de transaciones, hacer click en el botón Cerrar Ciclo de transacciones de lo contrario Hacer click en Añadir Transacciones y añadir el número de transacciones pertinentes o requeridas
			if (driver.getPageSource().contains("No hay ningún ciclo de transacciones")) {
				elementClick("//*[@id='toast-container']/div/button");
			}else {
				String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
				selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
				elementClick("ctl00_ContentZone_BtnCloseTrset");
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
				closedCycle = true;				
				return;
			}
			transactionNumber = ranNumbr(1,5);
			//Paso 10.- Si se ha cliqueado sobre Añadir Transacción
			for (int i=1; i<=transactionNumber;i++) {				
				elementClick("ctl00_ContentZone_BtnAddTransaction");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("Tarifa no encontrada")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					noFares=true;
					takeScreenShot("E:\\Selenium\\","noFares"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","insertarTransaccionesnoFares.jpeg");
					return;
				}
				takeScreenShot("E:\\Selenium\\","insertTransactionPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","insertTransactionPage_"+i+".jpeg");
				selectDropDown("ctl00_ContentZone_cplPnlOriginCompanyPlazaLane_cmb_tollCompany_cmb_dropdown");
				Thread.sleep(500);
				
				//Paso 11.- Ya en la pantalla Añadir Transacción, llenar todos los campos pertinentes
				List <WebElement> plazaCount = new Select(driver.findElement(By.id("ctl00_ContentZone_cplPnlOriginCompanyPlazaLane_cmb_plaza_cmb_dropdown"))).getOptions();
				if (plazaCount.size()>0) {
					selectDropDown("ctl00_ContentZone_cplPnlOriginCompanyPlazaLane_cmb_plaza_cmb_dropdown");
					selectDropDown("ctl00_ContentZone_cplPnlOriginCompanyPlazaLane_cmb_lane_cmb_dropdown");
				}
				/*SendKeys("ctl00_ContentZone_dtPnlOriginTime_box_date",dateSel(2018,2019));
				SendKeys("ctl00_ContentZone_dtPnlOriginTime_box_hour",hourFormat(0,23,0,59));*/
				SendKeys("ctl00_ContentZone_txtPnlCatLevel_box_data",String.valueOf(ranNumbr(1,5)));
				SendKeys("ctl00_ContentZone_txtPnlSerial_box_data",String.format("%06d", ranNumbr(0,999999)));
				SendKeys("ctl00_ContentZone_txtPnlTicketId_box_data",String.format("%06d", ranNumbr(0,999999)));
				selectDropDown("ctl00_ContentZone_cmbPnlTicketMode_cmb_dropdown");
				selectDropDown("ctl00_ContentZone_cmbPnlTicketType_cmb_dropdown");
				selectDropDown("ctl00_ContentZone_cmbPnlTicketLevel_cmb_dropdown");
				selectDropDown("ctl00_ContentZone_cmbPnlAbnormalTicket_cmb_dropdown");
				//SendKeys("ctl00_ContentZone_dtPnlPassageTime_box_date",dateSel(2017,2019));
				selectDropDown("ctl00_ContentZone_cmbPnlVehicleClass_cmb_dropdown");
				int matNum = ranNumbr(5000,9999);
				int matlet = ranNumbr(1,matletT.length()-1);
				int matlet1 = ranNumbr(1,matletT.length()-1);
				int matlet2 = ranNumbr(1,matletT.length())-1;
				matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
				SendKeys("ctl00_ContentZone_txtPnlPlate_box_data",matriNu);
				SendKeys ("ctl00_ContentZone_txtPnlComment_box_data","Se ha creado la "+(transactionCreated+1)+" transacción");
				
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
			
			Thread.sleep(3000);			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail (e.getMessage());
			throw(e);
		}
	}
	
}