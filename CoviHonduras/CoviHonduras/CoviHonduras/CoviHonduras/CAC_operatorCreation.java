package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class CAC_operatorCreation {
			 private static String lastcreated ;			 
			 private static WebElement tableResult;
			 private static List<WebElement> userResults;
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
				configurationSection("CAC",testNameSelected,testNameSelected);				
				Actions action = new Actions(driver);				
				testPlanReading(8,0,2,3);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					//Paso 1.- Entrar a la p�gina de login del CAC de CoviHonduras
					driver.get(CaCUrl);
					takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCACCVHPage.jpeg");
					
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la P�gina de Inicio");
						fail("Un error ha ocurrido en la P�gina de Inicio");
					}
					
					//Paso 2.- Loguearte con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					
					//Paso 3.- Entrar a la pantalla de Gesti�n de Operadores
					Thread.sleep(2000);					
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
						testLink();
						System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionOperadoresPage.jpeg");
					Thread.sleep(500);		
					
					//Paso 4.- Hacer click en el bot�n Confirmar
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
					SendKeys("ctl00_ContentZone_txt_country_box_data","Espa�a");
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_email_box_data",nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es");
					SendKeys("ctl00_ContentZone_txt_phone_box_data",workPhone1[userSel]);
					selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
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
					
					//P�so 6.- Hacer click en el bot�n Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpg");
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
						lastcreated = getText("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr[" + userResults.size() + "]/td[2]");
					}
					actualResults.set(5, "Se ha creado el operador: "+lastcreated+" con la contrase�a "+password+" correctamente");
					
					//Paso 7.- Salir de la aplicaci�n y entrar con el operador reci�n creado y verificar que entra con el perfil del grupo con que se creo
					elementClick("ctl00_BtnLogOut");
					Thread.sleep(500);
					driver.switchTo().alert().accept();
					Thread.sleep(1000);
					loginPage(lastcreated,password+"");		
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreatedscreenHome.jpeg");
					actualResults.set(6, "Se sale de la aplicaci�n correctamente y se entra con el usuario: "+lastcreated+" con la contrase�a: "+password+" y la aplicaci�n muestra la pantalla seg�n el perfil del usuario: "+operatorG.substring(04)+" con que se creo previamente");
					driver.close();
					testLink();
					System.out.println("Se ha probado en la versi�n del "+ getVersion(applevelTested));
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}		
	}