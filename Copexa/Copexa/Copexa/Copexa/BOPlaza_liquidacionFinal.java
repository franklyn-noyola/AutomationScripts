package Copexa.Copexa;

import static org.junit.Assert.*;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOPlaza_liquidacionFinal{	
	private static String shiftNumbr;
	private static String liquidacionNumbr;
	private static String bagShiftTotal;
	private static String bagTotal;
	private static String turnoLaboral;
	private static boolean shiftPrevious=false;
	private static String lastDateCashout;
	private static List <String> windows;
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
	public void liquidacionFinalInit() throws Exception{
		configurationSection("Plaza", testNameSelected, testNameSelected);		
		testPlanReading(13,0,2,3);
		Thread.sleep(1000);	
		liquidacionFinal();
		Thread.sleep(1000);
		if (shiftPrevious==true) {
			actualResults.set(5, "Se ha creado una liquidación Final No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" del turno laboral: "+turnoLaboral+" con la fecha y hora de activación: "+lastDateCashout+" y un Total en Turno: "+bagShiftTotal+" y Total en Bolsa: "+bagTotal);
			System.out.println("Se ha creado una liquidación Final No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" del turno laboral: "+turnoLaboral+" con la fecha y hora de activación: "+lastDateCashout+" y un Total en Turno: "+bagShiftTotal+" y Total en Bolsa: "+bagTotal);
		}else {
			actualResults.set(5, "Se ha creado una liquidación Final No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" del turno laboral: "+turnoLaboral+" y un Total en Bolsa: "+bagTotal);
			System.out.println("Se ha creado una liquidación Final No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" del turno laboral: "+turnoLaboral+" y un Total en Bolsa: "+bagTotal);
		}
		Thread.sleep(1000);
		driver.switchTo().window(windows.get(0));
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(windows.get(1));
		driver.close();
		testLink();
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void liquidacionFinal() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la página de Login del BO Host de Copexa
			driver.get(BoPlazaUrl);
			takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
			Thread.sleep(1000);
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
			
			//Paso 3.- Ir a la Pantalla de Liquidación Parcial
			action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Liquidación final";
			elementClick(pageSelected);			
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}

			//Paso 4.- Ya en la Pantalla de Liquidación Final, llenar todos los campos pertinentes
			takeScreenShot("E:\\Selenium\\","liquidacionFinalPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionFinal.jpeg");
			shiftNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
			selectDropDownV("ctl00_ContentZone_cmb_shiftGroup_cmb_dropdown");
			WebElement shiftSelect = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_shiftGroup_cmb_dropdown"))).getFirstSelectedOption();
			turnoLaboral = shiftSelect.getText();
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
			if (driver.getPageSource().contains("Detalle de liquidaciones en turno")) {
				lastDateCashout = driver.findElement(By.id("ctl00_ContentZone_txt_LastCashoutTime_box_data")).getAttribute("value");;
				bagShiftTotal = getText("ctl00_ContentZone_LblPIBtotalPrevious");
				shiftPrevious = true;
			}
			Thread.sleep(500);
			SendKeys("ctl00_ContentZone_txt_numRetornos_box_data", String.valueOf(ranNumbr(1,100)));
			SendKeys("ctl00_ContentZone_txt_supervisor_box_data","00001");
			SendKeys("ctl00_ContentZone_txt_password_box_data","00001");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			
			//Paso 5.- Una vez llenado todos los campos, hacer click en el botón Confirmar
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			
			//Paso 6.- Hacer click en el botón Aceptar de la ventana emergente
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();				
			}
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
			
			takeScreenShot("E:\\Selenium\\","liquidacionFinalPDF"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionFinalPDF.jpeg");			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}

}