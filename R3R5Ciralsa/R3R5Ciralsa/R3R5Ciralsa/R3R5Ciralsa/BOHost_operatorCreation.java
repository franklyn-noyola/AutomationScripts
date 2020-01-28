package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import org.openqa.selenium.interactions.Actions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;


public class BOHost_operatorCreation  {
			 private String testNameSelected = this.getClass().getSimpleName();
			 public static String operatorId;
			 public static Boolean wait;			 
			 private static String enviarViaVer;
			 private static Statement stmt;
			 private static ResultSet rs;
			 public static String Operator="";
			 private static String queryString;
			 private static ArrayList<String> transactions = new ArrayList<String>();
			 private static int i;
	
			@Before
			public void setup() throws Exception{
				setUp();
			}

			@After
			public void teardown() throws Exception{			  
				    tearDown();
			}

			@Test
			public void crearOperadores() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);
				testPlanReading(11,0,2,3);
				action = new Actions(driver);	
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					//Paso 1.- Entrar a la página de Login del BO Host de R3R5Ciralsa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						TestLink.TestLinkExecution.testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
														
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeBOPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOPage.jpeg");
					applicationVer = getText("ctl00_statusRight");		
					Thread.sleep(2000);					
					
					//Paso 3.- Hacer click en el Menu Configuración sistema, luego hacer click en Operadores y luego hacer click en la opción Gestión de Operadores
					action.moveToElement(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
					Thread.sleep(1000);		
					action.moveToElement(driver.findElement(By.linkText("Operadores"))).build().perform();
					Thread.sleep(500);
					pageSelected = "Gestión de operadores";
					elementClick(pageSelected);								
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionOperadoresPage.jpeg");
					wait = (new WebDriverWait(driver,20))
							.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_WaitLabel")));
					
					//Paso 4.- Hacer Click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearOperadoresPage.jpeg");
					
					//Paso 5.- Rellanar todos campos mandatorios o no mandatorios
					int opeId= ranNumbr(10,99999);
					operatorId = String.format("%05d", opeId);
					SendKeys("ctl00_ContentZone_operatorId_box_data",operatorId);
					Thread.sleep(100);
					selectDropDown("ctl00_ContentZone_cmb_tollCompany_cmb_dropdown");
					int userSel = ranNumbr(0,nameOp.length-1);										
					Thread.sleep(100);
					new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_title_cmb_dropdown"))).selectByVisibleText(sexSelc[userSel]);
					Thread.sleep(100);
					new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_gender_cmb_dropdown"))).selectByVisibleText(genderS[userSel]);
					Thread.sleep(100);		
					SendKeys("ctl00_ContentZone_forename_box_data",nameOp[userSel]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_surname_box_data",lastNameOp[userSel]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_address_box_data",addressTec[userSel]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_postcode_box_data",cpAdress[userSel]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_city_box_data",townC[userSel]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_country_box_data","España");
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_email_box_data",nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es");
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_phone_box_data",String.valueOf(ranNumbr(600000000,699999999)));
					Thread.sleep(100);
					selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_txt_numberDoc_box_data",dniLetra("DNI",ranNumbr(10000000,50000000)));		
					Thread.sleep(100);				
					SendKeys("ctl00_ContentZone_opPANControl_txt_pan",String.valueOf(ranNumbr(100000000,999999999)));
					Thread.sleep(100);				
					SendKeys("ctl00_ContentZone_opPANISOControl_txt_pan",String.valueOf(ranNumbr(100000000,999999999)));
					Thread.sleep(100);		
					SendKeys("ctl00_ContentZone_dt_birth_box_date",dateSel(1972,1979));
					Thread.sleep(1000);
					WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
					String operatorG = operatorGroup.getText();				
					SendKeys("ctl00_ContentZone_password_box_data",operatorId);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_password2_box_data",operatorId);
					Thread.sleep(5000);
					takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","allfilleddata.jpeg");
					
					//Paso 6.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_lbl_btnSubmit"); //clickquear botón Confirmar
					Thread.sleep(3000);
					if (driver.getPageSource().contains("Un operador con el identificador dado ya existe")) {
						errorText = getText("//*[@id='toast-container']/div/div");
						actualResults.set(5,"No se puede crear el opeador: "+operatorId+" a causa de que: "+errorText);
						for (int i=6;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.err.println("No se puede crear el opeador: "+operatorId+" a causa de que: "+errorText);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						return;
					}
					
					takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreated.jpeg");		
					Thread.sleep(1000);					
					
					//Paso 7.- Hacer click en el botón Enviar a Vías
					elementClick("ctl00_ButtonsZone_StaticFileOption_BtnDownloadControl_IB_Button");
					Thread.sleep(1000);
					elementClick("popup_ok");
					Thread.sleep(5000);
					String enviarViaLbl = getText("//div[@class='toast-message']");				
					if (enviarViaLbl.contains("OK")){
						enviarViaVer = enviarViaLbl.substring(enviarViaLbl.indexOf("ón ")+3);
						actualResults.set(6,"La telecarga de Operadores se ha enviado a Vía con la versión "+enviarViaVer);
						System.out.println("La telecarga de Operadores se ha enviado a Vía con la versión "+enviarViaVer);
					}else{						
						errorText=getText("ctl00_LblError");
						actualResults.set(6,"Hay un error en enviar telecargas a vía debido a: "+textSearched);
						executionResults.set(6,"Fallado");
						stepNotExecuted = executionNumber.size()-1;
						for (int i = stepNotExecuted;i>6;i--) {
							executionNumber.remove(i);
						}
						summaryBug = "Hay un error en enviar telecargas a vía";
						erroTextBug = "Hay un error en enviar telecargas a vía debido a "+errorText;
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = true;
						pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\telecargaErr.jpeg";
						driver.close();				
						testLink();		
						System.err.println("Hay un error en enviar telecargas a vía debido a "+errorText);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("Hay un error en enviar telecargas a vía");
					}
					takeScreenShot("E:\\Selenium\\","downloadsSendOK"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","downloadsSendOK.jpeg");
					
					//Paso 8.- Salir de la aplicación y entrar con el operador recién creado y verificar que entra con el perfil del grupo con que se creo
					elementClick("ctl00_BtnLogOut");
					Thread.sleep(1000);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					loginPage(operatorId,operatorId);
					actualResults.set(7,"Se ha Creado el operador "+operatorId+" con la contraseaña: "+operatorId+" en el grupo de "+operatorG.substring(04));
					System.out.println("Se ha Creado el operador "+operatorId+" con la contraseaña: "+operatorId+" en el grupo de "+operatorG.substring(04));					
					takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreatedscreenHome.jpeg");
					Thread.sleep(30000);
					
					//Paso 9.- Verificar en la BBDD que la telecarga de operadores ha bajado a Plaza
					String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=R3R5CIRALSA_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection conn = DriverManager.getConnection(connectionUrlPlaza, "Fgarcia", "Demo.1234");
					stmt = conn.createStatement();
					queryString = "select version, filename from dbo.atable where tabletype='operators' and version='"+enviarViaVer+"'";
					rs = stmt.executeQuery(queryString);
					String [] transaction = new String[2]; 			   
					while (rs.next()) {
						for (i = 0; i < transaction.length;i++){
							transaction[0]= rs.getString("version");
							transaction[1] = rs.getString("filename");
							transactions.add(transaction[i]);
						}
					}		
					if (transaction[0]==null){
						actualResults.set(8,"ERROR: La Telecarga de Operadores no ha bajado a Plaza aún");												
						executionResults.set(8,"Fallado");
						summaryBug = "ERROR: La Telecarga de Operadores no ha bajado a Plaza aún";
						erroTextBug = summaryBug;
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment=false;						
						driver.close();
						testLink();
						System.err.println("ERROR: La Telecarga de Operadores no ha bajado a Plaza aún");
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
						fail("ERROR: La Telecarga de Operadores no ha bajado a Plaza aún");
					}else{
						actualResults.set(8,"La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));
						System.out.println("La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));
					}
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
		
			}
		
}





