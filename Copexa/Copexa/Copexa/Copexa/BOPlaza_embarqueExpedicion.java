package Copexa.Copexa;

import static org.junit.Assert.*;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOPlaza_embarqueExpedicion{	
	private static String recount;
	private static int bagSelected; 
	private static boolean noBankTour=false;
	private static String bankTourDate;
	private static String bankTourNumber;
	private static List<String> windows;
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
	public void embarqueExpedicionInit() throws Exception{
		configurationSection("Plaza", testNameSelected, testNameSelected);		
		testPlanReading(16,0,2,3);
		Thread.sleep(1000);	
		embarqueExpedicion();
		Thread.sleep(1000);
		if (noBankTour==true) {
			actualResults.set(3, "No se puede crear un Embarque de Expedición, ya que no hay Expediciones existentes");
			for (int i=4;i<actualResults.size()-1;i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}			
			driver.close();
			testLink();
			System.out.println("No se puede crear un Embarque de Expedición, ya que no hay Expediciones existentes");
			return;
		}				
		actualResults.set(5,"Se ha creado un Embarque de Expedición a la Expedición "+bankTourNumber+" de fecha: "+bankTourDate+" con un total de "+bagSelected+" bolsas y un valor de: "+recount);
		driver.switchTo().window(windows.get(0));
		driver.close();
		Thread.sleep(500);
		driver.switchTo().window(windows.get(1));
		driver.close();
		Thread.sleep(500);
		testLink();
		System.out.println("Se ha creado un Embarque de Expedición a la Expedición "+bankTourNumber+" de fecha: "+bankTourDate+" con un total de "+bagSelected+" bolsas y un valor de: "+recount);						
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void embarqueExpedicion() throws Exception{
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
			
			//Paso 3.- Ir a la Pantalla de Embarque Expedición (Haciendo click en Gestión de Supervisor y después Embarque de expedición)
			action.moveToElement(driver.findElement(By.linkText("Gestión de supervisor"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Embarque de expedición";
			elementClick(pageSelected);			
			Thread.sleep(1000);			
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			takeScreenShot("E:\\Selenium\\","embarqueExpedicionPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","embarqueExpedicion.jpeg");			
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","creaciondeExpedicionPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","creaciondeExpedicion.jpeg");
			
			//Paso 4.- Ya en la pantalla principal principal de Embarque de expedición, seleccionar un embarque de la lista hacer click en el botón Confirmar
			WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			List <WebElement>tableResult = tableres.findElements(By.tagName("tr"));
			int bankTour = tableResult.size();			
			if (bankTour<2) {
				bankTour = 0;
			}
			if (bankTour==0) {
				noBankTour = true;
				return;
			}
			int selRow =ranNumbr(2,bankTour);
			elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[1]/input");
			bankTourDate = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[2]");
			bankTourNumber = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[3]");
			recount = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[5]");
			Thread.sleep(1000);
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","embarqueExpedicion2Page"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","embarqueExpedicion2.jpeg");
			
			//Paso 5.- Ya en la pantalla de Embarque de expedición: Detalles, completar todos los campos correspondientes
			int matNum = ranNumbr(5000,9999);
			int matlet = ranNumbr(0,matletT.length()-1);
			int matlet1 = ranNumbr(0,matletT.length()-1);
			int matlet2 = ranNumbr(0,matletT.length()-1);
			matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
			SendKeys("ctl00_ContentZone_txt_plate_box_data",matriNu);
			SendKeys("ctl00_ContentZone_txt_offnumber_box_data",ranNumbr(10,100000)+"");
			selOp = ranNumbr(0,nameOp.length-1);
			int selOp2 = ranNumbr(0,nameOp.length-1);
			SendKeys ("ctl00_ContentZone_txt_officer_box_data", nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
			SendKeys("ctl00_ContentZone_txt_comment_box_data", "Nuevo Embarque para la Expedición "+bankTourNumber);
			tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			tableResult = tableres.findElements(By.tagName("tr"));
			bagSelected = tableResult.size()-1;
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");		
			
			//Paso 6.- Hacer click en el botón Confirmar y luego en el botón Aceptar del Popup que aparece
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();				
			}	
			Thread.sleep(10000);
			windows = new ArrayList<String>();
			for (String wHandle : driver.getWindowHandles()) {
				windows.add(wHandle);												
			}
			
			if (windows.size()==1) {
				errorText = getText("ctl00_LblError");
				takeScreenShot("E:\\Selenium\\","liquidacionFinalErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionFinalErr.jpeg");
				actualResults.set(4,"No se ha podido crear la Liquidación Final debido a: "+errorText);
				executionResults.set(4,"Fallado");						
				summaryBug = "No se ha podido crear la Liquidación Parcial";
				erroTextBug = "No se ha podido crear la Liquidación Parcial debido a: "+errorText;
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\liquidacionFinalErr.jpeg";						
				driver.close();
				testLink();
				System.err.println("No se ha podido crear la Liquidación Final debido a: "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				fail("No se ha podido crear la Liquidación Final debido a: "+errorText);
			}
			Thread.sleep(8000);					
			takeScreenShot("E:\\Selenium\\","embarquePDF"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","embarquePDF.jpeg");
			Thread.sleep(2000);		
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}

	
}