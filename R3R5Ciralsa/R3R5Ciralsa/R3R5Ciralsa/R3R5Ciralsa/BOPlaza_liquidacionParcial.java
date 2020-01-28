package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;
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

public class BOPlaza_liquidacionParcial{	
	private static String shiftNumbr;
	private static String liquidacionNumbr;
	private static String bagTotal;	
	private static boolean bagDuplicate=false;
	private static String totalCashout;
	private static int bagNumbers;
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
		testPlanReading(18,0,2,3);
		Thread.sleep(1000);			
		liquidacionParcial();
		Thread.sleep(1000);
		
		if (bagDuplicate==true) {
			actualResults.set(5,"No se puede crear una Liquidación Parcial debido a: "+errorText);
			System.out.println("No se puede crear una Liquidación Parcial debido a: "+errorText);			
		}else {
			actualResults.set(5,"Se ha creado una liquidación parcial No. "+liquidacionNumbr+" con el No. de Turno: "+shiftNumbr+" y un Total en Bolsa: "+bagTotal);
			System.out.println("Se ha creado una liquidación parcial No. "+liquidacionNumbr+" con un total de bolsas "+bagNumbers+" con el No. de Turno: "+shiftNumbr+" y un Total en Bolsa/Liquidado: "+bagTotal+"/"+totalCashout);
		}				
		driver.close();
		Thread.sleep(1000);
		testLink();
		Thread.sleep(1000);		
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void liquidacionParcial() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la página de Login del BO Host de Copexa
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
			
			//Paso 3.- Ir a la Pantalla de Liquidación Parcial
			action.moveToElement(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
			Thread.sleep(500);
			pageSelected = "Liquidación parcial";
			elementClick(pageSelected);			
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			
			//Paso 4.- Ya en la Pantalla de Liquidación Parcial, llenar todos los campos pertinentes
			takeScreenShot("E:\\Selenium\\","liquidacionParcialPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionParcial.jpeg");
			shiftNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Shift_box_data")).getAttribute("value");
			liquidacionNumbr = driver.findElement(By.id("ctl00_ContentZone_txt_Cashout_box_data")).getAttribute("value");
			selectDropDown("ctl00_ContentZone_cmb_numBags_cmb_dropdown");
			Thread.sleep(1000);
			WebElement bagNum = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_numBags_cmb_dropdown"))).getFirstSelectedOption();
			bagNumbers = Integer.parseInt(bagNum.getText());				
			Thread.sleep(1000);					
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			for (int i = 1; i<=bagNumbers;i++) {
				SendKeys("ctl00_ContentZone_SealCASH_"+i,String.valueOf(ranNumbr(1,99999)));												
				SendKeys("ctl00_ContentZone_NumberCASH03N50000_"+i,String.valueOf(ranNumbr(1,5)));
				SendKeys("ctl00_ContentZone_NumberCASH03N20000_"+i,String.valueOf(ranNumbr(1,7)));			
				SendKeys("ctl00_ContentZone_NumberCASH03N10000_"+i,String.valueOf(ranNumbr(1,9)));
				SendKeys("ctl00_ContentZone_NumberCASH03N10000_"+i,String.valueOf(ranNumbr(1,9)));
				SendKeys("ctl00_ContentZone_NumberCASH03N5000_"+i,String.valueOf(ranNumbr(1,13)));
				SendKeys("ctl00_ContentZone_NumberCASH03N2000_"+i,String.valueOf(ranNumbr(1,18)));
				SendKeys("ctl00_ContentZone_NumberCASH03N1000_"+i,String.valueOf(ranNumbr(1,18)));
				SendKeys("ctl00_ContentZone_NumberCASH03N500_"+i,String.valueOf(ranNumbr(1,22)));				
				SendKeys("ctl00_ContentZone_NumberCASH03C200_"+i,String.valueOf(ranNumbr(1,28)));
				SendKeys("ctl00_ContentZone_NumberCASH03C100_"+i,String.valueOf(ranNumbr(1,35)));
				SendKeys("ctl00_ContentZone_NumberCASH03C50_"+i,String.valueOf(ranNumbr(1,100)));
				SendKeys("ctl00_ContentZone_NumberCASH03C20_"+i,String.valueOf(ranNumbr(1,200)));
				SendKeys("ctl00_ContentZone_NumberCASH03C10_"+i,String.valueOf(ranNumbr(1,500)));
				SendKeys("ctl00_ContentZone_NumberCASH03C5_"+i,String.valueOf(ranNumbr(1,1000)));
				SendKeys("ctl00_ContentZone_NumberCASH03C2_"+i,String.valueOf(ranNumbr(1,1500)));
				SendKeys("ctl00_ContentZone_NumberCASH03C1_"+i,String.valueOf(ranNumbr(1,2000)));		
			}
			Thread.sleep(100);
			SendKeys("ctl00_ContentZone_txt_comment_box_data", "Nueva Liquidación Parcial");
			Thread.sleep(500);			
			bagTotal = getText("ctl00_ContentZone_LblPIBInBag");
			totalCashout = getText("ctl00_ContentZone_LblPIBCashout");
			Thread.sleep(500);			
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			
			//Paso 5.- Una vez llenado todos los campos, hacer click en el botón Confirmar
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			if (driver.getPageSource().contains("Confirmación")) {
				elementClick("popup_ok");			
			}
			if (driver.getPageSource().contains("valor introducido ya existe")) {
				errorText = getText("//*[@id='toast-container']/div/div");
				bagDuplicate = true;
				return;
			}
			
			//Paso 6.- Hacer click en Aceptar del popup
			if (driver.getPageSource().contains("Confirmación")) {
				elementClick("popup_ok");
				driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			}
	
			if (driver.getPageSource().contains("Detalle liquidación")) {
				errorText = getText("ctl00_LblError");
				takeScreenShot("E:\\Selenium\\","liquidacionFinalErr"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","liquidacionParcialErr.jpeg");
				actualResults.set(5,"No se ha podido crear la Liquidación Final debido a: "+errorText);
				executionResults.set(5,"Fallado");						
				summaryBug = "No se ha podido crear la Liquidación Parcial";
				erroTextBug = "No se ha podido crear la Liquidación Parcial debido a: "+errorText;
				componentBug = "HM";
				severityBug = 1;
				priority = 4;
				testFailed = true;
				bugAttachment = true;
				pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\liquidacionParcialrr.jpeg";						
				driver.close();
				testLink();
				System.err.println("No se ha podido crear la Liquidación Parcial debido a: "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
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