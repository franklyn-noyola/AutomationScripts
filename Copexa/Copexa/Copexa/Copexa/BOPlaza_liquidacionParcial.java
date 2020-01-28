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

public class BOPlaza_liquidacionParcial{	
	private static String shiftNumbr;
	private static String liquidacionNumbr;
	private static String bagTotal;	
	public static List <String> windows;
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
	public void liquidacionParcialInit() throws Exception{
		configurationSection("Plaza", testNameSelected, testNameSelected);
		testPlanReading(12,0,2,3);
		Thread.sleep(1000);			
		liquidacionParcial();
		Thread.sleep(1000);
		actualResults.set(4,"Se ha creado una liquidaci�n parcial No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" y un Total en Bolsa: "+bagTotal);
		Thread.sleep(1000);
		driver.switchTo().window(windows.get(0));
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(windows.get(1));
		driver.close();
		Thread.sleep(1000);
		testLink();
		Thread.sleep(1000);
		System.out.println("Se ha creado una liquidaci�n parcial No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" y un Total en Bolsa: "+bagTotal);
		System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
		
	}
	
	public static void liquidacionParcial() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la p�gina de Login del BO Host de Copexa
			driver.get(BoPlazaUrl);
			takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
			Thread.sleep(1000);
			loginPageErr();
			if (pageSelectedErr==true) {
				testLink();
				System.err.println("Un error ha ocurrido en la P�gina de Inicio");
				fail("Un error ha ocurrido en la P�gina de Inicio");
			}									
			//Paso 2.- Loguearse con el usuario 00001/00001
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
			applicationVer = getText("ctl00_statusRight");
			Thread.sleep(500);			
			
			//Paso 3.- Ir a la Pantalla de Liquidaci�n Parcial
			action.moveToElement(driver.findElement(By.linkText("Gesti�n de cobrador"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Liquidaci�n parcial";
			elementClick(pageSelected);			
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			
			//Paso 4.- Ya en la Pantalla de Liquidaci�n Parcial, llenar todos los campos pertinentes
			takeScreenShot("E:\\Selenium\\","liquidacionParcialPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionParcial.jpeg");
			shiftNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
			liquidacionNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");			
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N100000_1",String.valueOf(ranNumbr(1,5)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N50000_1",String.valueOf(ranNumbr(1,7)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N20000_1",String.valueOf(ranNumbr(1,9)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N10000_1",String.valueOf(ranNumbr(1,11)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C5000_1",String.valueOf(ranNumbr(1,500)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N5000_1",String.valueOf(ranNumbr(1,20)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C2000_1",String.valueOf(ranNumbr(1,800)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01N2000_1",String.valueOf(ranNumbr(1,50)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C1000_1",String.valueOf(ranNumbr(1,1100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C500_1",String.valueOf(ranNumbr(1,1600)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C200_1",String.valueOf(ranNumbr(1,1900)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C100_1",String.valueOf(ranNumbr(1,2100)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C50_1",String.valueOf(ranNumbr(1,2800)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C20_1",String.valueOf(ranNumbr(1,3500)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C10_1",String.valueOf(ranNumbr(1,4500)));
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_NumberCASH01C5_1",String.valueOf(ranNumbr(1,6000)));			
			Thread.sleep(100);
			elementClick("ctl00_ContentZone_BtnCalculate");
			Thread.sleep(500);			
			bagTotal = getText("ctl00_ContentZone_LblPIBtotal");
			Thread.sleep(500);
			SendKeys("ctl00_ContentZone_txt_supervisor_box_data","00001");
			SendKeys("ctl00_ContentZone_txt_password_box_data","00001");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			
			//Paso 5.- Una vez llenado todos los campos, hacer click en el bot�n Confirmar
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
				actualResults.set(4,"No se ha podido crear la Liquidaci�n Final debido a: "+errorText);
				executionResults.set(4,"Fallado");						
				summaryBug = "No se ha podido crear la Liquidaci�n Parcial";
				erroTextBug = "No se ha podido crear la Liquidaci�n Parcial debido a: "+errorText;
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\liquidacionFinalErr.jpeg";						
				driver.close();
				testLink();
				System.err.println("No se ha podido crear la Liquidaci�n Final debido a: "+errorText);
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
				fail("No se ha podido crear la Liquidaci�n Final debido a: "+errorText);
			}
			
			Thread.sleep(7000);
			takeScreenShot("E:\\Selenium\\","liquidacionParcialPDF"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionParcialPDF.jpeg");			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	
}