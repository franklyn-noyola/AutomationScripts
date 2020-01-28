package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class CAC_accountCreationOnly{
		public static String accountSel;	
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
			public void accountCreationInit() throws Exception {
				configurationSection("CAC",testNameSelected,testNameSelected);				
				testPlanReading(1,0,2,3);
				accountCreation();				
				Thread.sleep(1000);
				actualResults.set(4,"Se ha creado una cuenta "+accountSel+" correctamente con el No: "+accountNumbr);
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				
			}

		public static void accountCreation() throws Exception {			
			action = new Actions(driver);
			accountSel = accountSelection[ranNumbr(0,1)];
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			try{
				//Paso 1.- Entrar a la página de Login del CAC de CoviHonduras
				driver.get(CaCUrl);
				takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCACCVHPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}								
				//Paso 2.- Loguearse conel usuario 00001/00001
				loginPage("00001","00001");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");						
				applicationVer = getText("ctl00_statusRight");
				
				//Paso 3.- Entrar a la pantalla de creación de Cuenta												
				Thread.sleep(1000);										
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);				
				action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();;
				Thread.sleep(1000);
				if (accountSel.equals("PrePago")) {
					pageSelected = "Cuenta Prepago";
					elementClick("Prepago");	
				}else {
					pageSelected = "Cuenta Postpagfo";
					elementClick("Postpago");
				}
												
				Thread.sleep(1000);				
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				takeScreenShot("E:\\Selenium\\","accountCreationPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountCreation.jpeg");
				//Thread.sleep(500);
				
				//Paso 4.- Seleccionar cualquier Cuenta (Personal o Corporativa) y rellanar todos los campos tanto mandatorios como no mandatorios
				int selOpt = ranNumbr(0,1);
				int selOp = ranNumbr(0,8);
				int selOp2 = ranNumbr(0,8);
				if (selOpt==0){
					actualResults.set(3, "Se ha seleccionado una cuenta Personal y se rellena todos los campos mandatorios y no mandatorios");
					elementClick(infoCuenta0);
					Thread.sleep(1000);
					SendKeys(RUCid,String.valueOf(ranNumbr(10000000,90000000))+String.valueOf(ranNumbr(100000,9000000)));
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
					actualResults.set(3, "Se ha seleccionado una cuenta Corporativa y se rellena todos los campos mandatorios y no mandatorios");
					elementClick(infoCuenta1);
					SendKeys(RUCid,String.valueOf(ranNumbr(10000000,90000000))+String.valueOf(ranNumbr(100000,9000000)));
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
				if (accountSel.equals("PrePago")) {
					selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_groupFare_cmb_dropdown");
				}
				if (accountSel.equals("PostPago")) {
					SendKeys("ctl00_ContentZone_ctrlAccountData_txt_statementDay_box_data",String.valueOf(ranNumbr(15,90)));					
				}
				Thread.sleep(1000);		
				takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
				
				//Paso 5.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Button");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","nextPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","nextPage.jpeg");
				pageSelected = "Detalles de Pago";						
				pageSelectedErr(4);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				if (driver.getPageSource().contains("Detalles de Pago")) {
					elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				}
				Thread.sleep(1000);
				
			}catch(Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}		
			}
