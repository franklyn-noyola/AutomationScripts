package Itata;

import static org.junit.Assert.*;
import SettingFiles.Itata_Settingsfields_File;
import SettingFiles.Generic_Settingsfields_File;
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


public class BOHost_operatorCreation extends Generic_Settingsfields_File {
			 private static String lastcreated ;
			 private static WebElement tableResult;
			 private static List<WebElement> userResults;
			 private static String enviarViaVer;
			 private static Statement stmt;
			 private static ResultSet rs;
			 private static String queryString;
			 private static ArrayList<String> transactions = new ArrayList<String>();
			 private static int i;
			 private static String pwdCreate;
	
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
				SettingFiles.Itata_Settingsfields_File.configurationSection("Host","BOHost_operatorCreation","BOHost_operatorCreation");
				testPlanReading(10,0,2,3);			
				action = new Actions(driver);			
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");	
				try{
					//Paso 1.- Entrar a la p�gina de Login del BO de Itata
					driver.get(Itata_Settingsfields_File.BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
					Itata_Settingsfields_File.loginPageErr();
					if (pageSelectedErr==true) {
						TestLink.TestLinkExecution.testLink();
						System.err.println("Un error ha ocurrido en la P�gina de Inicio");
						fail("Un error ha ocurrido en la P�gina de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						TestLink.TestLinkExecution.testLink();
						System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOVHPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOVHPage.jpg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Paso 3.- Hacer click en el Menu Configuraci�n sistema, luego hacer click en Operadores y luego hacer click en la opci�n Gesti�n de Operadores
					action.moveToElement(driver.findElement(By.linkText("Configuraci�n sistema"))).build().perform();
					Thread.sleep(1000);		
					action.moveToElement(driver.findElement(By.linkText("Operadores"))).build().perform();
					Thread.sleep(500);
					pageSelected = "Gesti�n de operadores";
					elementClick(pageSelected);								
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						TestLink.TestLinkExecution.testLink();
						System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionOperadoresPage.jpg");
					Thread.sleep(500);
					
					//Paso 4.- Hacer Click en el bot�n Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearOperadoresPage.jpg");
					
					//Paso 5.- Rellanar todos campos mandatorios o no mandatorios
					int userSel = ranNumbr(0,nameOp.length-1);
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_title_cmb_dropdown"))).selectByVisibleText(sexSelcEsp[userSel]);
					Thread.sleep(100);
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_gender_cmb_dropdown"))).selectByVisibleText(genderSEsp[userSel]);
					SendKeys("ctl00_ContentZone_forename_box_data",nameOp[userSel]);		
					SendKeys("ctl00_ContentZone_surname_box_data",lastNameOp[userSel]);		
					SendKeys("ctl00_ContentZone_txt_address_box_data",addressTec[userSel]);		
					SendKeys("ctl00_ContentZone_txt_postcode_box_data",cpAdress[userSel]);		
					SendKeys("ctl00_ContentZone_txt_city_box_data",townC[userSel]);		
					SendKeys("ctl00_ContentZone_txt_country_box_data","Espa�a");		
					SendKeys("ctl00_ContentZone_email_box_data",nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es");
					SendKeys("ctl00_ContentZone_txt_phone_box_data",workPhone1[userSel]);
					//selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
					new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).selectByVisibleText("27: Administradores Pass");
					Thread.sleep(1000);
					WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
					String operatorG = operatorGroup.getText();
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeDoc_cmb_dropdown"))).selectByIndex(1);
					SendKeys("ctl00_ContentZone_txt_numberDoc_box_data",String.valueOf(ranNumbr(1000000,999999999)));
					SendKeys("ctl00_ContentZone_txt_pan_txt_token_box_data",String.valueOf(ranNumbr(10000,999999)));
					SendKeys("ctl00_ContentZone_dt_birth_box_date",dateSel(1970,1980));
					Thread.sleep(500);
					pwdCreate = String.format("%05d", ranNumbr(0,99999));
					SendKeys("ctl00_ContentZone_password_box_data",pwdCreate);
					SendKeys("ctl00_ContentZone_password2_box_data",pwdCreate);
					Thread.sleep(5000);
					takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","allfilleddata.jpg");
					
					//Paso 6.- Hacer click en el bot�n Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
						Thread.sleep(1000);
						pwdCreate = "Demo."+pwdCreate; 						
						SendKeys("ctl00_ContentZone_password_box_data",pwdCreate);
						SendKeys("ctl00_ContentZone_password2_box_data",pwdCreate);
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(500);
					}
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreated.jpg");
					String operNumber = getText("ctl00_ContentZone_tablePager_LblCounter");
					int operatorNumbers = Integer.parseInt(operNumber.substring(operNumber.indexOf("de ")+3));
					tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
					userResults = tableResult.findElements(By.tagName("tr"));		
					if (userResults.size()>operatorNumbers){
						for (int i = 1; i <= userResults.size(); i++){
							if (i == userResults.size()){
								lastcreated = getText("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[2]");
							}	
						}
					}else{
						elementClick("ctl00_ContentZone_tablePager_BtnLast");
						Thread.sleep(1500);
						tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
						userResults = tableResult.findElements(By.tagName("tr"));
						lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr[" + userResults.size() + "]/td[2]")).getText();
					}
					
					//Paso 7.- Hacer click en el bot�n Enviar a V�as
					elementClick("ctl00_ButtonsZone_BtnDownload_IB_Button");
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(5000);
					String enviarViaLbl = getText("ctl00_LblError");		
					if (enviarViaLbl.contains("OK")){
						enviarViaVer = enviarViaLbl.substring(41).replace("'", "");
						actualResults.set(6,"La telecarga de Operadores se ha enviado a Plaza con la versi�n "+enviarViaVer );
						System.out.println("La telecarga de Operadores se ha enviado a Plaza con la versi�n "+enviarViaVer);
					}else{
						errorText=getText("ctl00_LblError");
						actualResults.set(6,"Hay un error en enviar telecargas a v�a debido a: "+textSearched);
						executionResults.set(6,"Fallado");
						stepNotExecuted = executionNumber.size()-1;
						for (int i = stepNotExecuted;i>6;i--) {
							executionNumber.remove(i);
						}
						summaryBug = "Hay un error en enviar telecargas a v�a";
						erroTextBug = "Hay un error en enviar telecargas a v�a debido a "+errorText;
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = true;
						pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\telecargaErr.jpeg";
						driver.close();				
						TestLink.TestLinkExecution.testLink();		
						System.err.println("Hay un error en enviar telecargas a v�a debido a "+errorText);
						System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));						
						fail("Hay un error en envair telecargas a v�a");
					}
					
					//Paso 8.- Salir de la aplicaci�n y entrar con el operador reci�n creado y verificar que entra con el perfil del grupo con que se creo
					elementClick("ctl00_BtnLogOut");
					Thread.sleep(1000);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					SendKeys(loginField,lastcreated);
					SendKeys(passField,pwdCreate);
					elementClick(loginButton);
					Thread.sleep(2000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					actualResults.set(7, "Se ha Creado el operador "+lastcreated+" con la contrasea�a: "+pwdCreate+ " en el grupo de "+operatorG.substring(04));
					System.out.println("Se ha Creado el operador "+lastcreated+" con la contrasea�a: "+pwdCreate+ " en el grupo de "+operatorG.substring(04));		
					takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreatedscreenHome.jpg");
					Thread.sleep(20000);
					
					//Paso 9.- Verificar en la BBDD que la telecarga de operadores ha bajado a Plaza
					String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=ITATA_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
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
						actualResults.set(8,"ERROR: La Telecarga de Operadores no ha bajado a Plaza a�n");
						executionResults.set(8,"Fallado");						
						summaryBug = "ERROR: La Telecarga de Operadores no ha bajado a Plaza a�n";
						erroTextBug = "ERROR: La Telecarga de Operadores no ha bajado a Plaza a�n";
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = true;
						pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\telecargaErr.jpeg";
						driver.close();				
						TestLink.TestLinkExecution.testLink();		
						System.out.println("ERROR: La Telecarga de Operadores no ha bajado a Plaza a�n");
						System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));						
						fail("ERROR: La Telecarga de Operadores no ha bajado a Plaza a�n");
					}else{
						actualResults.set(8, "La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));
						driver.close();
						TestLink.TestLinkExecution.testLink();
						System.out.println("La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));					
						System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
					}
					
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
		
			}
	
}





