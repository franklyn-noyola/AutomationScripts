package R3R5Ciralsa.R3R5Ciralsa;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class BOHost_reconocimientoDeuda{
	public static int opSel;
	public static String [] deudaOptions = {"Crear","Tratar RD"};
	public static int [] estacion = {1,3};		
	public static String totalPay;
	public static String identifierId;	
	private static boolean notCreated=false;
	public static boolean noTratado = false;
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
	public void reconocimientoDeudaInit() throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
		Calendar calc = Calendar.getInstance();
		Date todayDate = calc.getTime();
		opSel = ranNumbr(0,1);		
		configurationSection("Host", testNameSelected, testNameSelected);
		borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		if (opSel==0) {
			configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
			testPlanReading(13,0,2,3);
		}
		if (opSel==1) {
			configurationSection("Host", testNameSelected+" - Tratar RD", testNameSelected);
			testPlanReading(13,5,7,8);
		} 
		reconocimientoDeuda();
		if (noTratado == true) {
			actualResults.set(5, "No se ha podido hacer un nuevo pago al identificador Id: "+identifierId+" debido a que el Reconocimiento de deuda ha sido cobrada");
			for (int i=6;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("No se ha podido hacer un nuevo pago al identificador Id: "+identifierId+" debido a que el Reconocimiento de deuda ha sido cobrada");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));	
			return;
		}		
		if (notCreated==true){
			driver.close();
			testLink();			
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));	
			return;
		}
		if (deudaOptions[opSel].equals("Crear")) {
			actualResults.set(6,"Se ha creado un Reconocimiento de deuda con el Identificador Id: "+identifierId+" por valor de: "+totalPay);
			System.out.println("Se ha creado un Reconocimiento de deuda con el Identificador Id: "+identifierId+" por valor de: "+totalPay);
		}
		if (deudaOptions[opSel].equals("Tratar RD")) {
			actualResults.set(9,"Se ha cobrado un Reconocimiento de deuda con el Identificador Id: "+identifierId+ " por un valor de "+totalPay+" en fecha: "+dateFormat.format(todayDate));
			System.out.println("Se ha cobrado un Reconocimiento de deuda con el Identificador Id: "+identifierId+ " por un valor de "+totalPay+" en fecha: "+dateFormat.format(todayDate));
		}
		driver.close();
		testLink();
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
	}
	
	public static void reconocimientoDeuda() throws Exception{
		action = new Actions(driver);
		try {
			//Paso 1.- Entrar a la página de Login del BO Host de R3R5Ciralsa
			driver.get(BoHostUrl);
			takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
			loginPageErr();
			if (pageSelectedErr==true) {
				TestLink.TestLinkExecution.testLink();
				System.err.println("Un error ha ocurrido en la Página de Inicio");
				fail("Un error ha ocurrido en la Página de Inicio");
			}
												
			//Paso 2.- Loguearse con el usuario 00001/00001
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOHostPage.jpeg");
			applicationVer = getText("ctl00_statusRight");
			Thread.sleep(1000);
			
			//Paso 3.- Hacer click en el Menu de Gestión de Pagos y luego Reconocimientos de Dedua
			action.moveToElement(driver.findElement(By.linkText("Gestión de pagos"))).build().perform();
			Thread.sleep(1000);
			pageSelected = "Reconocimientos de deuda";
			elementClick(pageSelected);
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			if (deudaOptions[opSel].equals("Crear")) {
				//TC BOHost_reconocimientoDeuda - Crear: Pasos del 4 al 7
				deudaCreate();
			}
			if (deudaOptions[opSel].equals("Tratar RD")) {
				//TC BOHost_reconocimientoDeuda - Tratar RD: Pasos del 4 al 9
				tratarRD();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail (e.getMessage());
			throw(e);
		}
	}
		public static void deudaCreate() throws Exception{
			action = new Actions(driver);			
			try {
				
				//Paso 4.- Hacer cilck en el botón Crear
				Thread.sleep(1000);			
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(500);
				
				//Paso 5.- Llenar todos los campos correspondientes
				new Select (driver.findElement(By.id("ctl00_ContentZone_dlgTollCompany_cmb_dropdown"))).selectByIndex(2);
				Thread.sleep(1000);
				new Select (driver.findElement(By.id("ctl00_ContentZone_dlgPlaza_cmb_dropdown"))).selectByIndex(estacion[ranNumbr(0,1)]);
				Thread.sleep(2000);
				List <WebElement> laneN = new Select(driver.findElement(By.id("ctl00_ContentZone_dlgLane_cmb_dropdown"))).getOptions();
				new Select (driver.findElement(By.id("ctl00_ContentZone_dlgLane_cmb_dropdown"))).selectByIndex(ranNumbr(1,laneN.size()-1));
				Thread.sleep(2000);
				int matNum = ranNumbr(5000,9999);
				int matlet = ranNumbr(1,matletT.length()-1);
				int matlet1 = ranNumbr(1,matletT.length()-1);
				int matlet2 = ranNumbr(1,matletT.length())-1;
				matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
				SendKeys("ctl00_ContentZone_DebtorPlateInput_box_data",matriNu);
				SendKeys("ctl00_ContentZone_DebtorDniInput_box_data",dniLetra("DNI",ranNumbr(10000000,99999999)));
				SendKeys("ctl00_ContentZone_DebtorNameInput_box_data",nameOp[ranNumbr(0,nameOp.length-1)]+" "+lastNameOp[ranNumbr(0,nameOp.length-1)]);
				int payInt = ranNumbr(1,999);
				int decimal = ranNumbr(0,99);
				String payMent;
				if (decimal<0) {
					decimal=0;
				}
				if (decimal<10) {
					payMent = String.valueOf(payInt)+"0"+String.valueOf(decimal);
					totalPay = String.valueOf(payInt)+","+"0"+String.valueOf(decimal)+"€";
				}else {
					payMent = String.valueOf(payInt)+String.valueOf(decimal);
					totalPay = String.valueOf(payInt)+","+String.valueOf(decimal)+"€";
				}
				action.click(driver.findElement(By.id("ctl00_ContentZone_dlgAmount_txt_formated"))).sendKeys(payMent).build().perform();
				Thread.sleep(2000);
				
				//Paso 6.- Hacer click en el botón Aceptar
				elementClick("//div[3]/div/button/span");
				Thread.sleep(1000);
				if (driver.getPageSource().contains("error desconocido")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(5,"No se ha podido crear un reconocimiento de deuda debido a: "+errorText);
					System.out.println("No se ha podido crear un reconocimiento de deuda debido a: "+errorText);
					notCreated = true;
					return;
				}
				//Paso 7.- Hacer click en el botón Aceptar de la ventaja emergente
				elementClick("popup_ok");
				Thread.sleep(3000);				
				String recordDis = getText("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");
				int recorDis = Integer.parseInt(recordDis.substring(recordDis.indexOf(" a ")+3,recordDis.indexOf("a ")+4).trim());
				int recorDis2 = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
				if (recorDis>recorDis2) {
					WebElement tableResults = driver.findElement(By.xpath("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table"));			
					List <WebElement> rowSelect = tableResults.findElements(By.tagName("tr"));
					int recordSel = rowSelect.size()-1;					
					identifierId = getText("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+recordSel+"]/td[2]");					
				}else {
					elementClick("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[5]/input");
					Thread.sleep(1000);
					WebElement tableResults = driver.findElement(By.xpath("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table"));			
					List <WebElement> rowSelect = tableResults.findElements(By.tagName("tr"));
					int recordSel = rowSelect.size()-1;					
					identifierId = getText("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+recordSel+"]/td[2]");					
				}
				Thread.sleep(1000);
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail (e.getMessage());
			throw(e);
		}		
	}
		public static void tratarRD() throws Exception{
			action = new Actions(driver);
			try {
				//Paso 4.- Ya en la pantalla Reconocimientos de Deuda, Si hay reconocimientos de deudas disponible, seleccionar un reconocimiento de deuda
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_filterDate_dt_from_box_date","01/01/2018");
				Thread.sleep(500);
				elementClick(searchButton);
				Thread.sleep(1000);
				debtorNoteTable();	
				String pendingPay=getText("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[9]");
				Thread.sleep(1000);				
				identifierId = optionSelectedId;
				
				//Paso 5.- Una vez seleccionado el reconocimiento de deuda, hacer click en el botón Tratar RD
				elementClick("ctl00_ContentZone_BtnEditRD");
				Thread.sleep(1000);
				
				//Paso 6.- Hacer click en el botón Nuevo Pago
				elementClick("ctl00_ContentZone_BtnCreatePayment");
				Thread.sleep(1000);
				if (driver.getPageSource().contains("Reconocimiento de deuda ya cobrado") || driver.getPageSource().contains("error desconocido")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					noTratado = true;
					return;
				}		
				
				//Paso 7.- Llenar todos los campos pertinentes
				String payInt = pendingPay.substring(0,pendingPay.indexOf(",")-0);
				String payDec = pendingPay.substring(pendingPay.indexOf(",")+1,pendingPay.indexOf(",")+3);
				totalPay = payInt+","+payDec+" €";				
				if (Integer.parseInt(payInt)<=0) {
					action.click(driver.findElement(By.id("ctl00_ContentZone_PaymentAmount_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(100,99999999))).build().perform();	
				}else {
					action.click(driver.findElement(By.id("ctl00_ContentZone_PaymentAmount_txt_formated"))).build().perform();
					action.sendKeys(payInt+payDec).build().perform();
				}
				
				Thread.sleep(500);
				selectDropDown("ctl00_ContentZone_cmb_paymentType_cmb_dropdown");
				Thread.sleep(500);
				
				//Paso 8.- Hacer click en el botón Aceptar
				elementClick("//div[3]/div/button/span");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("error desconocido")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					actualResults.set(7,"No se ha podido hacer un reconocimiento de deuda debido a: "+errorText);
					System.out.println("No se ha podido hacer un reconocimiento de deuda debido a: "+errorText);
					notCreated = true;
					return;
				}
				
				//Paso 9.- Hacer click en el botón Aceptar de la ventana emergente
				elementClick("popup_ok");
				Thread.sleep(3000);
				
				//Paso 10.- Hacer click en el botón Guardar
				elementClick("ctl00_ButtonsZone_BtnSaveRD_IB_Button");
				Thread.sleep(3000);
				
				
				
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail (e.getMessage());
			throw(e);
		}
	}
}