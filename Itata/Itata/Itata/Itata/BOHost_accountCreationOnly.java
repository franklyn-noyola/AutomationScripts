package Itata;

import static org.junit.Assert.*;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class BOHost_accountCreationOnly {	
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
			configurationSection("Host",testNameSelected,testNameSelected);			
			testPlanReading(0,0,2,3);			
			Thread.sleep(1000);	
			creaciondeCuenta();
			Thread.sleep(1000);
			actualResults.set(5, "Cuenta "+accountSelectedIta[selaccount]+" No. "+accountNumbr+" ha sido creada correctamente");
			driver.close();
			testLink();
			System.out.println("Cuenta "+accountSelectedIta[selaccount]+" No. "+accountNumbr+" ha sido creada correctamente");
			System.out.println("Probado en la Versión de: "+ getVersion(applevelTested));
		
		}
	
		public static void creaciondeCuenta() throws Exception{
			action = new Actions(driver);			
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			try{
				//Paso 1.- Entrar a la página de Login del BO de Itata
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}
				//Paso 2.- Loguearse con el usuario 00001/00001
				loginPage("00001","00001");
				pageSelected = "BackOffice Host";
				mainPage(1);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				takeScreenShot("E:\\Selenium\\","homeBOTajPage"+timet+".jpg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajPage.jpeg");				
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(500);
				
				//Paso 3.- Hacer click en Gestión de cuentas y luego a Crear cuentas
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();			
				selaccount = ranNumbr(0,2);
				
				//Paso 4.- Hacer click en la opción de cuenta que desee: Estándar, Comrecial o Exenta
				switch (accountSelectedIta[selaccount]) {
					case "Estándar":				pageSelected = "Cuenta Estándar";
													elementClick("Estándar");		
													Thread.sleep(1000);
													pageSelectedErr(3);
													if (pageSelectedErr==true) {
														driver.close();
														testLink();
														System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
														fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
													}
													cuentaEstandar();				
													break;
					case "Comercial":				pageSelected = "Cuenta Comercial";
													elementClick("Comercial");
													Thread.sleep(1000);
													pageSelectedErr(3);
													if (pageSelectedErr==true) {
														driver.close();
														testLink();
														System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
														fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
													}
													cuentaComercial();
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
	
		public static void cuentaEstandar() throws Exception{
			Thread.sleep(500);
			accountType = "Estándar";
			try {
				actualResults.set(3, "Se entra a la página de creación de cuenta 'Estándar'");
				takeScreenShot("E:\\Selenium\\","CuentaEstandarPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","CuentaEstandarPage.jpeg");
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				
				//Paso 5.- Llenar todos los campos mandatorios o no mandatorios
				int selOp = ranNumbr(0,8);			
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
				takeScreenShot("E:\\Selenium\\","cuentaEstandardFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","cuentaEstandardFilledage.jpeg");
				if (accountModeIta.equals("Modificar")) {
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); //click on Modify button
				}else {
					elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save button
				}
				Thread.sleep(3000);
				takeScreenShot("E:\\Selenium\\","paymentDetailsPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentDetailsPage.jpeg");
				nextPage = getText("ctl00_SectionZone_LblTitle");
				assertEquals("Detalles del pago", nextPage);
				
				//Paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(1000);		
				paymentfromCustomerEsp();
			
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());			
				fail(e.getMessage());
				throw (e);
			}
		
		}
	
		public static void cuentaComercial() throws Exception{
			Thread.sleep(500);
			accountType = "Comercial";
			try {			
				actualResults.set(3, "Se entra a la página de creación de cuenta 'Comercial'");
				takeScreenShot("E:\\Selenium\\","CuentaComercialPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CuentaComercialPage.jpeg");
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				
				//Paso 5.- Llenar todos los campos mandatorios o no mandatorios
				int selOp = ranNumbr(0,8);
				int selOp2 = ranNumbr(0,8);
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
				takeScreenShot("E:\\Selenium\\","cuentaComercialFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cuentaComercialFilledage.jpeg");
				if (accountModeIta.equals("Modificar")) {
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); //click on Modify button
				}else {
					elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save button
				}
				Thread.sleep(3000);
				takeScreenShot("E:\\Selenium\\","paymentDetailsPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentDetailsPage.jpeg");
				nextPage = getText("ctl00_SectionZone_LblTitle");
				assertEquals("Detalles del pago", nextPage);
				
				//Paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
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
			Thread.sleep(500);
			accountType = "Exenta";
			try {			
				pageSelected = "Cuenta Exenta";
				elementClick("Exenta");				
				Thread.sleep(1000);
				pageSelectedErr(3);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				actualResults.set(3, "Se entra a la página de creación de cuenta 'Exenta'");
				takeScreenShot("E:\\Selenium\\","CuentaExentaPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CuentaExentaPage.jpeg");
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				
				//Paso 5.- Llenar todos los campos mandatorios o no mandatorios
				int selOpt = ranNumbr(0,1);
				int selOp = ranNumbr(0,8);
				int selOp2 = ranNumbr(0,8);
				if (selOpt==0){
					driver.findElement(By.id(infoCuenta0)).click();
					Thread.sleep(1000);				
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
					Thread.sleep(4000);
				}else{
					elementClick(infoCuenta1);				
					Thread.sleep(1000);
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
				selectDropDown("ctl00_ContentZone_ctrlAccountExempt_cmb_plaza_cmb_dropdown");
				Thread.sleep(200);
				selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
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
				
				takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
				if (accountModeIta.equals("Modificar")) {
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); //click on Modify button
				}else {
					elementClick("ctl00_ButtonsZone_BtnSave_IB_Button"); //click on Save button
				}
				Thread.sleep(3000);
				String nextPage = getText("ctl00_SectionZone_LblTitle");
				Thread.sleep(1000);
				nextPage = getText("ctl00_SectionZone_LblTitle");
				assertEquals("Detalles del pago", nextPage);
				
				//Paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
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