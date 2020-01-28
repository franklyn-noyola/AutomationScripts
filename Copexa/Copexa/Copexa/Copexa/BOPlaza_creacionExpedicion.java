package Copexa.Copexa;

import static org.junit.Assert.*;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOPlaza_creacionExpedicion{	
	private static int manual=0;
	private static int bagSelected; 
	private static boolean noBagscreated=false;
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
		configurationSection("Plaza", testNameSelected, testNameSelected);		
		testPlanReading(15,0,2,3);
		Thread.sleep(1000);	
		creacionExpedicion();
		Thread.sleep(1000);
		if (noBagscreated==true) {
			actualResults.set(4, "No se puede crear una Expedición, porque no se ha podido crear Bolsas Manuales y tampoco hay bolsas de liquidaciones anteriores");
			for (int i=5;i<actualResults.size()-1;i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();			
			System.out.println("No se puede crear una Expedición, porque no se ha podido crear Bolsas Manuales y tampoco hay bolsas de liquidaciones anteriores");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
		}
		if (bagSelected==0) {
			actualResults.set(5, "Se ha creado una Expedición solo "+manual+" bolsas manuales creadas");
			System.out.println("Se ha creado una Expedición solo "+manual+" bolsas manuales creadas");
		}else {
			if (manual==0) {
				actualResults.set(5, "Se ha creado una Expedición con "+bagSelected+" bolsas de liquidaciones previas");
				System.out.println("Se ha creado una Expedición con "+bagSelected+" bolsas de liquidaciones previas");
			}else {
				actualResults.set(5, "Se ha creado una Expedición con "+bagSelected+" bolsas de liquidaciones previas y "+manual+" bolsas manuales creadas");
				System.out.println("Se ha creado una Expedición con "+bagSelected+" bolsas de liquidaciones previas y "+manual+" bolsas manuales creadas");
			}
		}
		Thread.sleep(1000);
		driver.close();
		testLink();			
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void creacionExpedicion() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
			driver.get(BoPlazaUrl);
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
			takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
			applicationVer = getText("ctl00_statusRight");
			Thread.sleep(500);	
			
			//Paso 3.- Ir a la Pantalla de Crear Expedición (Haciendo click en Gestión de cobradores y después Creación de Expedición)
			action.moveToElement(driver.findElement(By.linkText("Gestión de supervisor"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Creación de Expedición";
			elementClick("Creación de Expedición");			
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
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","creaciondeExpedicionPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","creaciondeExpedicion.jpeg");
			WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			List <WebElement>tableResult = tableres.findElements(By.tagName("tr"));
			
			//Paso 4.- Ya en la pantalla principal principal de Creación de Expedición, hacer click en el botón Confirmar
			bagSelected = tableResult.size();
			if (bagSelected<2) {
				bagSelected = 0;
			}else {
				bagSelected = bagSelected-1;
			}
			
			//Paso 5.- Ya en la pantalla de Creación expedición: Detalles, si en la tabla de resultados no hay bolsas, no se puede crear la expedición, de lo contrario si hay bolsas disponibles.
			if (bagSelected==0) {
				int createBags = ranNumbr(1,7);
				int noCreated=0;
				for (int i = 1;i<=createBags;i++) {
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					SendKeys("ctl00_ContentZone_BoxCashier_box_data","00001");
					SendKeys("ctl00_ContentZone_BoxSeal_box_data",String.valueOf(ranNumbr(20,800)));
					selectDropDownV("ctl00_ContentZone_cmb_shiftGroup_cmb_dropdown");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(100);
					if (driver.getPageSource().contains("introducido ya existe")) {						
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						noCreated=noCreated+1;
					}
					Thread.sleep(1000);
				}
				manual = createBags-noCreated;
				if (manual==0) {
					noBagscreated = true;
					return;
				}
			}else {
				if (ranNumbr(0,1)==0) {
					int createBags = ranNumbr(1,5);
					int noCreated=0;
					for (int i = 1;i<=createBags;i++) {
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_BtnCreate");
						Thread.sleep(1000);
						SendKeys("ctl00_ContentZone_BoxCashier_box_data","00001");
						SendKeys("ctl00_ContentZone_BoxSeal_box_data",String.valueOf(ranNumbr(20,800)));
						selectDropDownV("ctl00_ContentZone_cmb_shiftGroup_cmb_dropdown");
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
						Thread.sleep(100);
						if (driver.getPageSource().contains("introducido ya existe")) {						
							elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
							noCreated=noCreated+1;
						}
						Thread.sleep(1000);
					}
					manual = createBags-noCreated;
					}
				}
				tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				tableResult = tableres.findElements(By.tagName("tr"));
				int recountFill = tableResult.size()-1;
				for (int i=0;i<recountFill;i++) {
					Thread.sleep(500);
					if (ranNumbr(0,1)==0) {
						action.click(driver.findElement(By.id("ctl00_ContentZone_mi_recount_"+i+"_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(10000,999999))).build().perform();
						SendKeys("ctl00_ContentZone_txt_comment_"+i,"New Recount added");
						Thread.sleep(500);
					}
				}
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
				
				//Paso 6.- Hacer click en el botón Confirmar y luego en el botón Aceptar del Popup que aparece
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				if (isAlertPresent()) {
					driver.switchTo().alert().accept();				
				}			
				Thread.sleep(3000);					
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