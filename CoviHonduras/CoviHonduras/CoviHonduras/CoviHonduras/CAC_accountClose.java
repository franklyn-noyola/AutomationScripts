package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;


public class CAC_accountClose{
			 private static boolean accountClosed = false;
			 private static boolean NumbVehC = false;
			 public static int NumbVeh;
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
			public void accountCloseInit() throws Exception {	
				configurationSection("CAC",testNameSelected,testNameSelected);				
				testPlanReading(4,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				accountClose();
				Thread.sleep(1000);
				if (accountClosed){
					actualResults.set(5, "No se puede cerrar la cuenta "+accountNumbr+" porque ya está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;

				}
				if (NumbVehC){
					actualResults.set(6, "No se puede cerrar la cuenta "+accountNumbr+" porque tiene "+NumbVeh+" vehículo/s asignado/s");
					for (int i=7;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}					
					driver.close();
					testLink();
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
					
				}
		
				Thread.sleep(1000);
				actualResults.set(8, "Vuelve a la pantalla de edición de la cuenta: "+accountNumbr+" con un mensaje en rojo, CUENTA CERRADA");
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void accountClose() throws Exception {
				Actions action = new Actions(driver);
				try {
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
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
						
					Thread.sleep(2000);
					//Paso 3.- Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(2000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					//Paso 4.- Hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountSearchPage.jpeg");
					
					//5.- Buscar y hacer click en cualquier cuenta
					selectAccount();					
					Thread.sleep(1000);
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpeg");
					takeScreenShot("E:\\Selenium\\CoviHonduras\\accountClose\\attachments\\","accountPage.jpeg");
					getText("ctl00_ContentZone_lbl_vehicles");
					Thread.sleep(1000);
					
					//Paso 6.- Hacer click en el botón Cerrar Cuenta
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					
					//Paso 7.- Ya en la pantalla Cierre de cuenta, verificar si se puede cerrar la cuenta
					NumbVeh = Integer.parseInt(textSearched);
					if (NumbVeh>0){
						NumbVehC = true;
						return;
					}else{
						elementClick("ctl00_ContentZone_BtnCloseAccount");
						Thread.sleep(500);
						//Paso 8.- Entrar un comentario en el campo Comentario y hacer click al botón Cerrar Cuenta
						SendKeys("ctl00_ContentZone_txtComment","Esta Cuenta se cerrará");
						takeScreenShot("E:\\Selenium\\","accountClosePage"+timet+".jpeg");	
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosePage.jpeg");
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(500);
						if (isAlertPresent()) {
							driver.switchTo().alert().accept();
						}
						Thread.sleep(1000);	
						
						//Paso 9.- Hacer click en el botón Volver
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						takeScreenShot("E:\\Selenium\\","accountClosedPage"+timet+".jpeg");	
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosedPage.jpeg");
						Thread.sleep(1000);
					}
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
					}
			}

}		