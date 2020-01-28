package Copexa.Copexa;

import static org.junit.Assert.*;
import static SettingFiles.Copexa_Settingsfields_File.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_accountCreationOnly{	
	public static int selaccount;
	public static String nextPage;
	public String testNameSelected = this.getClass().getSimpleName();
	
	@Before
	public void setup() throws Exception{
		setUp();
	}
	
	@After
	public void teardown() throws Exception{
		tearDown();
	}
	
	@Test
	public void creaciondeCuentaInit() throws Exception{		
		selaccount = ranNumbr(0,1);
		if (selaccount==0) {			
			configurationSection("Host", testNameSelected+" - PrePago", testNameSelected);			
			testPlanReading(0,0,2,3);
		}else {
			configurationSection("Host", testNameSelected+" - Exenta", testNameSelected);			
			testLinkTestCase = "BOHost_accountCreationOnly - Exenta";
			testPlanReading(0,5,7,8);
		}		
		Thread.sleep(1000);	
		creaciondeCuenta();
		Thread.sleep(1000);
		driver.close();
		actualResults.set(5, "Cuenta "+accountSelectedCopexa[selaccount]+" No. "+accountNumbr+" ha sido creada correctamente");
		testLink();	
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void creaciondeCuenta() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la página de Login del BO Host de Copexa
			driver.get(BoHostUrl);
			takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
			Thread.sleep(1000);
			loginPageErr();
			if (pageSelectedErr==true) {
				TestLink.TestLinkExecution.testLink();
				System.err.println("Un error ha ocurrido en la Página de Inicio");
				fail("Un error ha ocurrido en la Página de Inicio");
			}								
			//Paso 2.- Loguearse conel usuario 00001/00001
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
			applicationVer = getText("ctl00_statusRight");			
			Thread.sleep(500);			
			//Paso 3.- Hacer click en opción Crear Cuenta	
			action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
			Thread.sleep(500);
			action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();						
			switch (accountSelectedCopexa[selaccount]) {
				case "Prepago":					cuentaPrepago();				
												break;
				case "Exenta":					cuentaExenta();
												break;				
			}			
			Thread.sleep(1000);
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
		
	}
	
	public static void cuentaPrepago() throws Exception{
		
		//Paso 4.- Seleccionar Cuenta PrePago
		Thread.sleep(500);
		accountType = "Prepago";
		try {
			pageSelected = "Prepago";
			elementClick(pageSelected);
			pageSelectedErr(3);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","CuentaPrepagoPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CuentaPrepagoPage.jpeg");
			accountNumbr = getText("ctl00_SectionZone_LblTitle");
			accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
			//Paso 5.- Seleccionar cualquier Cuenta (Personal o Corporativa) y rellanar todos los campos tanto mandatorios como no mandatorios
			int selOp = ranNumbr(0,8);		
			int selOp2 = ranNumbr(0,8);
			if (ranNumbr(0,1)==0) {
				actualResults.set(4, "Se ha seleccionado una cuenta Personal y se rellena todos los campos mandatorios y no mandatorios");
				elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_0");						
				new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelcEsp[selOp]);
				SendKeys(namef,nameOp[selOp]);
				SendKeys(surnamef,lastNameOp[selOp]);
				SendKeys(addressf,addressTec[selOp]);
				SendKeys(cpf,cpAdress[selOp]);			
				SendKeys(countryf,"España");
				SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
				SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
				SendKeys(workPhone,workPhone1[selOp]);
				SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
				SendKeys(faxPhone,workPhone1[selOp]);				
				Thread.sleep(1000);				
			}else {
				actualResults.set(4, "Se ha seleccionado una cuenta Corporativa y se rellena todos los campos mandatorios y no mandatorios");
				elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_1");
				SendKeys(companyf,"Tecsidel, S.A");
				SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
				SendKeys(addressf,addressTec[2]);
				SendKeys(cpf,cpAdress[2]);
				SendKeys(countryf,"España");
				SendKeys(emailf,"info@tecsidel.es");
				SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
				SendKeys(workPhone,workPhone1[2]);
				SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
				SendKeys(faxPhone,workPhone1[selOp]);
				Thread.sleep(1000);								
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");
				SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date",dateSel(2019,2020));
				SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data",String.valueOf(ranNumbr(10,20)));
			}			
			takeScreenShot("E:\\Selenium\\","cuentaPrepagoFilledPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cuentaPrepagoFilledage.jpeg");
			//Paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
			if (accountModeCopexa.equals("Modificar")) {
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); //click on Modify button
			}else {
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save button
			}
			Thread.sleep(3000);
			takeScreenShot("E:\\Selenium\\","paymentDetailsPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentDetailsPage.jpeg");
			nextPage = getText("ctl00_SectionZone_LblTitle");
			assertEquals("Detalles del pago", nextPage);
			elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
			Thread.sleep(1000);		
			paymentfromCustomerEsp();
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());;			
			fail(e.getMessage());
			throw (e);
		}
		
	}		
			
	public static void cuentaExenta() throws Exception{		
		//Paso 4.- Seleccionar Cuenta Exenta
		Thread.sleep(500);
		accountType = "Exenta";
		try {			
			pageSelected = "Exento";
			elementClick(pageSelected);
			Thread.sleep(1000);			
			pageSelectedErr(3);
			if (pageSelectedErr==true) {
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			
			takeScreenShot("E:\\Selenium\\","CuentaExentaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CuentaExentaPage.jpeg");
			accountNumbr = getText("ctl00_SectionZone_LblTitle");
			accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
			//Paso 5.- Seleccionar cualquier Cuenta (Personal o Corporativa) y rellanar todos los campos tanto mandatorios como no mandatorios
			int selOp = ranNumbr(0,8);		
			int selOp2 = ranNumbr(0,8);
			if (ranNumbr(0,1)==0) {
				actualResults.set(4, "Se ha seleccionado una cuenta Personal y se rellena todos los campos mandatorios y no mandatorios");
				elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_0");						
				new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelcEsp[selOp]);
				SendKeys(namef,nameOp[selOp]);
				SendKeys(surnamef,lastNameOp[selOp]);
				SendKeys(addressf,addressTec[selOp]);
				SendKeys(cpf,cpAdress[selOp]);			
				SendKeys(countryf,"España");
				SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
				SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
				SendKeys(workPhone,workPhone1[selOp]);
				SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
				SendKeys(faxPhone,workPhone1[selOp]);				
				Thread.sleep(1000);				
			}else {
				actualResults.set(4, "Se ha seleccionado una cuenta Corporativa y se rellena todos los campos mandatorios y no mandatorios");
				elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_1");
				SendKeys(companyf,"Tecsidel, S.A");
				SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
				SendKeys(addressf,addressTec[2]);
				SendKeys(cpf,cpAdress[2]);
				SendKeys(countryf,"España");
				SendKeys(emailf,"info@tecsidel.es");
				SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
				SendKeys(workPhone,workPhone1[2]);
				SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
				SendKeys(faxPhone,workPhone1[selOp]);
				Thread.sleep(1000);								
			}
			
			selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
			Thread.sleep(200);			
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_0");
			}else {
				elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_1");
				SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_max_trips",String.valueOf(ranNumbr(100,900)));
				SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_trips_warning",String.valueOf(ranNumbr(5,20)));
			}

			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_0");
			}else {
				elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_1");
				SendKeys("ctl00_ContentZone_ctrlAccountExempt_cal_date_expiry_box_date",dateSel(2019,2021));
				SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_days_warning",String.valueOf(ranNumbr(5,20)));
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_notification_0");
			}else {
				elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_notification_1");
			}
			
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			//Paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
			if (accountModeCopexa.equals("Modificar")) {
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); //click on Modify button
			}else {
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save button
			}
			Thread.sleep(3000);
			String nextPage = getText("ctl00_SectionZone_LblTitle");
			Thread.sleep(1000);
			nextPage = getText("ctl00_SectionZone_LblTitle");
			assertEquals("Detalles del pago", nextPage);
			elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
			Thread.sleep(2000);						
			paymentfromCustomerEsp();
			Thread.sleep(1000);		
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw (e);
		}
	}
		

}