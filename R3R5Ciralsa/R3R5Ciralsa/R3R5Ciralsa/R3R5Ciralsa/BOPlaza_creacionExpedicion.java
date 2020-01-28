package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.fail;

import java.text.DecimalFormat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.interactions.Actions;


import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOPlaza_creacionExpedicion{	
	public static String pattern = "###,###.00"; 
	public static DecimalFormat ft;
	private static float amountValue = 0;
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
	public void creacionExpedicionInit() throws Exception{
		ft = new DecimalFormat(pattern);
		configurationSection("Plaza", testNameSelected, testNameSelected);		
		testPlanReading(16,0,2,3);
		Thread.sleep(1000);	
		creacionExpedicion();
		Thread.sleep(1000);
		if (noItemFound==true) {
			actualResults.set(4, "No se puede crear una Expedición, porque no hay bolsas disponibles de liquidaciones anteriores");
			for (int i=5;i<actualResults.size()-1;i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
				testLink();			
				System.out.println("No se puede crear una Expedición, porque no hay bolsas disponibles de liquidaciones anteriores");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
		System.out.println("Se ha creado Expedición de: "+expeditionImporte.length+" bolsa/s y con un Importe Total de: "+ft.format(amountValue)+" €");
		Thread.sleep(1000);
		driver.close();
		testLink();			
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void creacionExpedicion() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la página de Login del BackOffice de R3R5Ciralsa
			driver.get(BoPlazaUrl);
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
			
			//Paso 3.- Ir a la Pantalla de Crear Expedición (Haciendo click en Gestión de cobrador y después Creación de Expedición)
			action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Creación de Expedición";
			elementClick("Creación de expedición");			
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			takeScreenShot("E:\\Selenium\\","creaciondeExpedicionBSPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","creaciondeExpedicionBS.jpeg");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			//Paso 4.- Ya en la pantalla Crear Expedición: Selección de bolsas, hacer click en el botón Búsqueda
			elementClick(searchButton);
			Thread.sleep(10000);
			
			//Paso 5.- Si hay Bolsas disponibles, hacer click en una o barias bosas disponibles
			elementClick("ctl00_ContentZone_BtnUnselectAll");
			Thread.sleep(500);
			bagSelectionForExpedition();			
			Thread.sleep(500);			
			if (noItemFound==true) {
				return;
			}
			for (int i=0;i<expeditionImporte.length;i++) {
				expeditionImporte[i] = expeditionImporte[i].replace(".", "");
				expeditionImporte[i] = expeditionImporte[i].replace(",", ".");
				expeditionImporte[i] = expeditionImporte[i].replace(" €", "");
				amountValue = amountValue+Float.parseFloat(expeditionImporte[i]);						
			}
			
			Thread.sleep(500);
			
			//Paso 6.- Una vez seleccionadas las bolsas, hacer click en el botón Confirmar
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","creaciondeExpedicionPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","creaciondeExpedicionPage.jpeg");
			
			//Paso 7.- Llenar todos los campos pertinentes y hacer click en Confirmar
			int matNum = ranNumbr(5000,9999);
			int matlet = ranNumbr(1,matletT.length()-1);
			int matlet1 = ranNumbr(1,matletT.length()-1);
			int matlet2 = ranNumbr(1,matletT.length())-1;
			matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
			SendKeys("ctl00_ContentZone_dlgPlate_box_data",matriNu);
			ft = new DecimalFormat(pattern);
			for (int i = 0;i<3; i++) {
				selOp = ranNumbr(0,nameOp.length-1);
				driver.findElement(By.id("ctl00_ContentZone_dlgOfficer_box_data")).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]+", ");
			}
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_dlgComment_box_data","Creación de una Expedición de: "+expeditionImporte.length+" bolsa/s y con un Importe Total de: "+ft.format(amountValue)+" €");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","creaciondeExpedicionPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","creaciondeExpedicionPage.jpeg");
			elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
			Thread.sleep(500);
			elementClick("popup_ok");
			Thread.sleep(15000);
			takeScreenShot("E:\\Selenium\\","expedicionCreada"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","expedicionCreada.jpeg");			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	

}