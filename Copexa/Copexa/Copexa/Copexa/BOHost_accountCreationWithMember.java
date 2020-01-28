package Copexa.Copexa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Copexa.Copexa.BOHost_accountCreationOnly.*;


public class BOHost_accountCreationWithMember {
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
			public void accountCreationWithMemberInit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);
				memberMode = "Crear";				
				applevelTested="Host";
				testPlanReading(4,0,2,3);
				Actions action = new Actions(driver);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				
				//Paso 1.- Entrar a la página de Login del BO Host de Copexa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}	
				//Paso 2.- Loguearse conel usuario 00001/00001
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(500);	
				
				//Paso 3.- Entrar a la pantalla de creación de Cuenta Exenta
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(500);
				action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
				Thread.sleep(500);				
				cuentaExenta();						
				Thread.sleep(1000);
				
				//Paso 7.- Hacer click en el botón Editar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(500);
				
				//Paso 8.- Hacer Click en el botón Miembros
				elementClick("ctl00_ContentZone_BtnMembers");
				accountCreationWithMember();
				Thread.sleep(500);				
				takeScreenShot("E:\\Selenium\\","VehiclewithMemberCreated"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","VehiclewithMemberCreated.jpeg");
				actualResults.set(11, "Se ha creado la cuenta: "+accountNumbr+" correctamente con el miembro: "+nameOp[selOp]+" "+lastNameOp[selOp]);
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta: "+accountNumbr+" correctamente con el miembro: "+nameOp[selOp]+" "+lastNameOp[selOp]);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void accountCreationWithMember() throws Exception {				
				try {
					
					//Paso 9.- Hacer click en el botón Crear
					if (memberMode.equals("Crear")) {
						elementClick("ctl00_ContentZone_BtnCreate");
					}
					if (memberMode.equals("Modificar")){
						elementClick("ctl00_ContentZone_BtnModify");
					}					
					Thread.sleep(1000);		
					takeScreenShot("E:\\Selenium\\","memberPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","memberPage.jpeg");					
					Thread.sleep(1000);
					
					//Paso 10.- Llenar todos los campos correspondientes
					selOp = ranNumbr(0,nameOp.length-1);
					new Select(driver.findElement(By.id("ctl00_ContentZone_combo_title"))).selectByVisibleText(sexSelcEsp[selOp]);
					SendKeys("ctl00_ContentZone_text_forename",nameOp[selOp]);
					SendKeys("ctl00_ContentZone_text_surname",lastNameOp[selOp]);
					if (memberMode.equals("Crear")) {
						SendKeys("ctl00_ContentZone_text_comments", "Miembro Creado correctamente");
					}else{
						SendKeys("ctl00_ContentZone_text_comments", "Miembro ha sido Modificado correctamente");
					}
					selectDropDown("ctl00_ContentZone_cmb_class_cmb_dropdown");					
						if (ranNumbr(0,1)==0) {
							elementClick("ctl00_ContentZone_CtrlExemptData_RadioExpDate_0");
						}else {
							elementClick("ctl00_ContentZone_CtrlExemptData_RadioExpDate_1");
							SendKeys("ctl00_ContentZone_CtrlExemptData_BoxExpDate_box_date",dateSel(2019,2021));
							SendKeys("ctl00_ContentZone_CtrlExemptData_BoxExpDateWarn",String.valueOf(ranNumbr(5,20)));
						}					
					takeScreenShot("E:\\Selenium\\","memberDataFilledPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","memberDataFilledPage.jpeg");
					Thread.sleep(1000);		
					
					//Paso 11.- Hacer click en el botón Confirmar 
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");	
					Thread.sleep(1500);			
					
					//Paso 12.- Hacer click en el botón Back y hacer Click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1500);
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(2500);
					String nextPage = getText("ctl00_SectionZone_LblTitle");
					if (nextPage.contains("Detalles del pago")) {
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						paymentfromCustomerEsp();
					}
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
}		
	




