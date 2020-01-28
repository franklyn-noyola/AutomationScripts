package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class BOHost_operatorCreation{
			 private static String lastcreated ;
			 private static WebElement tableResult;
			 private static List<WebElement> userResults;
			 private static String enviarViaVer;
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
				configurationSection("Host",testNameSelected,testNameSelected);				
				testPlanReading(0,0,2,3);
				Actions action = new Actions(driver);	
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					//Paso 1.- 	Entrar a la página de login del BackOffice de CoviHonduras					
					driver.get(BoHostUrl);					
					takeScreenShot("E:\\Selenium\\","loginBOCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCVHPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						TestLink.TestLinkExecution.testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearte con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeBOVHPage"+timet+".jpeg");					
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);	
					
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

					takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionOperadoresPage.jpg");
					Thread.sleep(500);		
					
					
					//Paso 4.- Hacer Click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearOperadoresPage.jpeg");
								
					
					//Paso 5.- Rellanar todos campos mandatorios o no mandatorios
					int userSel = ranNumbr(0,nameOp.length-1);
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_title_cmb_dropdown"))).selectByVisibleText(sexSelcEsp[userSel]);
					Thread.sleep(100);
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_gender_cmb_dropdown"))).selectByVisibleText(genderSEsp[userSel]);
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
					SendKeys("ctl00_ContentZone_txt_phone_box_data",workPhone1[userSel]);
					selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
					Thread.sleep(100);
					selectDropDown("ctl00_ContentZone_cmb_typeDoc_cmb_dropdown");
					Thread.sleep(1000);
					WebElement Docselected = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
					String DocSelectedText = Docselected.getText();
					if (DocSelectedText.equals("TI")){
						SendKeys("ctl00_ContentZone_txt_numberDoc_box_data",String.valueOf(ranNumbr(1000000,90000000)+String.valueOf(ranNumbr(1000000,9000000))));
					}else{
						SendKeys("ctl00_ContentZone_txt_numberDoc_box_data",String.valueOf(ranNumbr(10000000,900000000)+String.valueOf(ranNumbr(1000000,9000000))));
					}
					Thread.sleep(1000);
					WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
					String operatorG = operatorGroup.getText();		
					SendKeys("ctl00_ContentZone_dt_birth_box_date",dateSel(1970,1980));
					Thread.sleep(3000);
					int password = ranNumbr(10000,99999);
					SendKeys("ctl00_ContentZone_password_box_data",String.valueOf(password));
					SendKeys("ctl00_ContentZone_password2_box_data",String.valueOf(password));
					Thread.sleep(5000);
					takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","allfilleddata.jpeg");
											
					//Paso 6.- acer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreated.jpeg");

					String operNumber = getText("ctl00_ContentZone_tablePager_LblCounter");
					int operatorNumbers = Integer.parseInt(operNumber.substring(operNumber.indexOf("de ")+3));
					tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
					userResults = tableResult.findElements(By.tagName("tr"));		
					if (userResults.size()>operatorNumbers){
						for (int i = 1; i <= userResults.size(); i++){
							if (i == userResults.size()){
								lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[2]")).getText();
							}	
						}
					}else{
						elementClick("ctl00_ContentZone_tablePager_BtnLast");
						Thread.sleep(1500);
			            tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			            userResults = tableResult.findElements(By.tagName("tr"));
			            lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr[" + userResults.size() + "]/td[2]")).getText();
					}
					//actualResults.set(5, "Se ha creado el operador: "+lastcreated+" con la contraseña "+password+" correctamente");
					//Paso 7.- Hacer click en el botón Enviar a Vías
					elementClick("ctl00_ButtonsZone_BtnDownload_IB_Button");
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(5000);
					String enviarViaLbl = getText("ctl00_LblError");		
					if (enviarViaLbl.contains("OK")){
						enviarViaVer = enviarViaLbl.substring(41).replace("'", "");
						System.out.println("La telecarga de Operadores se ha enviado a Vía con la versión "+enviarViaVer);						
						
					}else{
						takeScreenShot("E:\\Selenium\\","telecargaErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","telecargaErr.jpeg");
						errorText = getText("ctl00_LblError");
						actualResults.set(6,"Hay un error en enviar telecargas a vía debido a: "+errorText);
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
					
					//Paso 8.- Salir de la aplicación y entrar con el operador recién creado y verificar que entra con el perfil del grupo con que se creo
					elementClick("ctl00_BtnLogOut");
					Thread.sleep(1000);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					loginPage(lastcreated,String.valueOf(password));
					Thread.sleep(2000);
					actualResults.set(7, "Se sale de la aplicación correctamente y se entra con el usuario: "+lastcreated+" con la contraseña: "+password+" y la aplicación muestra la pantalla según el perfil del usuario: "+operatorG.substring(04)+" con que se creo previamente");
					System.out.println("Se ha Creado el operador "+lastcreated+" con la contraseaña: "+password+ " en el grupo de "+operatorG.substring(04));		
					takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreatedscreenHome.jpeg");					
					Thread.sleep(20000);
					
					//Paso 9.- Verificar en la BBDD que la telecarga de operadores ha bajado a Plaza
					String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=COVIHONDURAS_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
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
						System.out.println("ERROR: La Telecarga de Operadores no ha bajado a Plaza aún");						
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






