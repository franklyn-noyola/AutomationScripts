package Copexa.Copexa;

import static org.junit.Assert.*;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
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
import org.openqa.selenium.support.ui.Select;

public class BOHost_operatorCreation{
			 private static String enviarViaVer;
			 private static String operatorId;
			 private static Statement stmt;
			 private static ResultSet rs;
			 private static String queryString;
			 private static ArrayList<String> transactions = new ArrayList<String>();
			 private static int i;			 
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
			public void crearOperadores() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);							
				testPlanReading(1,0,2,3);
				action = new Actions(driver);											
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");				
				try{
					//Paso 1.- 	Entrar a la página de login del BackOffice de CoviHonduras	
					driver.get(BoHostUrl);
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearte con el usuario 00001/00001
					loginPage("02019","02019");
					takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(500);					
					
					//Paso 3.- Entrar a la pantalla de Gestión de Operadores
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
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionOperadoresPage.jpeg");
					Thread.sleep(500);
					
					//Paso 4.- Hacer Click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearOperadoresPage.jpeg");
					
					//Paso 5.- Rellanar todos campos mandatorios o no mandatorios
					int opeId= ranNumbr(10,99999);
					operatorId = String.format("%05d", opeId);
					int userSel = ranNumbr(0,nameOp.length-1);
					SendKeys("ctl00_ContentZone_operatorId_box_data",operatorId);
					SendKeys("ctl00_ContentZone_forename_box_data",nameOp[userSel]);		
					SendKeys("ctl00_ContentZone_surname_box_data",lastNameOp[userSel]);						
					SendKeys("ctl00_ContentZone_email_box_data",nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es");					
					selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
					Thread.sleep(1000);
					WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
					String operatorG = operatorGroup.getText();
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_password_box_data",operatorId);
					SendKeys("ctl00_ContentZone_password2_box_data",operatorId);
					Thread.sleep(5000);
					takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","allfilleddata.jpeg");
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");					
					Thread.sleep(2000);
					//Paso 6.- acer click en el botón Confirmar
					if (driver.getPageSource().contains("dado ya existe")) {
						getText("lblError");												
						actualResults.set(5,"No se puede crear el operador debido a "+textSearched);
						for (int i=6;i<actualResults.size();i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						driver.close();
						testLink();
						System.out.println("No se puede crear el operador debido a "+textSearched);
						System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
						return;						
					}					
					takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreated.jpeg");
					
					//Paso 7.- Hacer click en el botón Enviar a Vías
					elementClick("ctl00_ButtonsZone_BtnDownload_IB_Button");
					if (isAlertPresent()==true){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(5000);
					String enviarViaLbl = getText("ctl00_LblError");		
					takeScreenShot("E:\\Selenium\\","downloadSent"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","downloadSent.jpeg");
					if (enviarViaLbl.contains("OK")){
						enviarViaVer = enviarViaLbl.substring(41).replace("'", "");
						actualResults.set(6,"La telecarga de Operadores se ha enviado a Plaza con la versión "+enviarViaVer);
						System.out.println("La telecarga de Operadores se ha enviado a Plaza con la versión "+enviarViaVer);
					}else{
						errorText=getText("ctl00_LblError");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","telecargaErr.jpeg");
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
					Thread.sleep(1000);
					//Paso 8.- Salir de la aplicación y entrar con el operador recién creado y verificar que entra con el perfil del grupo con que se creo					
					elementClick("ctl00_BtnLogOut");
					Thread.sleep(1500);
					if (isAlertPresent()==true){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					loginPage(operatorId, operatorId);					
					Thread.sleep(2000);
					actualResults.set(7, "Se ha Creado el operador "+operatorId+" con la contraseaña: "+operatorId+ " en el grupo de "+operatorG.substring(04));							
					System.out.println("Se ha Creado el operador "+operatorId+" con la contraseaña: "+operatorId+ " en el grupo de "+operatorG.substring(04));
					
					takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreatedscreenHome.jpeg");
					Thread.sleep(30000);
					
					//Paso 9.- Verificar en la BBDD que la telecarga de operadores ha bajado a Plaza
					String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=COPEXA_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
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
						System.out.println("La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));
						actualResults.set(8,"La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));
					}
					//Fin Ejecución desde el paso 7 (Si todo va bien)										
					driver.close();					
					//Actualización Plan de Pruebas en TestLink
					testLink();
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));

				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
				
			}
			
	}