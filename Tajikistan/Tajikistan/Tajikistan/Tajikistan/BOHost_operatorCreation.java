package Tajikistan.Tajikistan;

import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
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
			 private static String usercreated ;			 			 
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
			public void OperatorCreation() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);				
				testPlanReading(15,0,2,3);
				action = new Actions(driver);	
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					
					//Step 1.- Enter into Tajikistan Login Page
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOTajPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOTajPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.out.println("An error has ocurred in the Login Page");
						fail("An error has ocurred in the Login Page");
					}		
					// Step 2.- Log with user 00001/00001
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeBOTajPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOTajPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);			
					
					//Step 3.- Click on System settings, then Operators and then Operators Management link
					action.moveToElement(driver.findElement(By.linkText("System settings"))).build().perform();
					Thread.sleep(1000);
					action.moveToElement(driver.findElement(By.linkText("Operators"))).build().perform();
					Thread.sleep(500);
					pageSelected = "Operators management";
					elementClick("pageSelected");								
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.out.println(errorText);
						fail(errorText);
						System.out.println("Tested in Version of: "+ getVersion(applevelTested));
					}
					takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionOperadoresPage.jpeg");
					Thread.sleep(500);
					
					//Step 4.- Click on Create button
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearOperadoresPage.jpeg");
					
					//Step 5.- Fill all the fields accordingly
					usercreated = driver.findElement(By.id("ctl00_ContentZone_operatorId_box_data")).getAttribute("value");
					int userSel = ranNumbr(0,nameOp.length-1);
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_title_cmb_dropdown"))).selectByVisibleText(sexSelcEn[userSel]);
					Thread.sleep(100);
					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_gender_cmb_dropdown"))).selectByVisibleText(genderSEng[userSel]);
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
					SendKeys("ctl00_ContentZone_txt_numberDoc_box_data",String.valueOf(ranNumbr(10000000,900000000)+String.valueOf(ranNumbr(1000000,9000000))));		
					Thread.sleep(1000);
					WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
					String operatorG = operatorGroup.getText();						
					SendKeys("ctl00_ContentZone_dt_birth_box_date",dateSel(1970,1980));
					Thread.sleep(3000);
					SendKeys("ctl00_ContentZone_password_box_data",usercreated);
					SendKeys("ctl00_ContentZone_password2_box_data",usercreated);
					Thread.sleep(5000);
					takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","allfilleddata.jpeg");
					
					//Step 6.- Click on Submit button
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");					
					System.out.println("User "+usercreated+" with password: "+usercreated+ " with operator group "+operatorG.substring(04)+" has been created correctly");
					actualResults.set(5, "User "+usercreated+" with password: "+usercreated+ " with operator group "+operatorG.substring(04)+" has been created correctly");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreated.jpeg");
					
					//Step 7.- Click on Download to Nodes button
					elementClick("ctl00_ButtonsZone_BtnDownload_IB_Button");
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(5000);
					String enviarViaLbl = getText("ctl00_LblError");		
					if (enviarViaLbl.contains("OK")){
						enviarViaVer = enviarViaLbl.substring(48).replace("'", "");
						System.out.println("La telecarga de Operadores se ha enviado a Vía con la versión "+enviarViaVer);
						actualResults.set(6, "La telecarga de Operadores se ha enviado a Vía con la versión "+enviarViaVer);
					}else{
						actualResults.set(6, "There is an error in downloading to nodes");
						executionResults.set(6,"Fallado");
						stepNotExecuted = executionNumber.size()-1;
						for (int i = stepNotExecuted;i>6;i--) {
							executionNumber.remove(i);
						}
						summaryBug = "There is an error in downloading to nodes";
						erroTextBug = "There is an error in downloading to nodes";
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = false;						
						driver.close();				
						testLink();		
						System.err.println("There is an error in downloading to nodes");
						System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
						fail("There is an error in downloading to nodes");					
					}
					
					//Step 8.- Log out and log in with just created User 
					elementClick("ctl00_BtnLogOut");
					Thread.sleep(1000);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}	
					Thread.sleep(1000);
					loginPage(usercreated, usercreated);
					actualResults.set(5, "Application was logged out and User "+usercreated+" with password: "+usercreated+ " has logged in Application based on permison of: "+operatorG.substring(04));
					System.out.println("Application was logged out and User "+usercreated+" with password: "+usercreated+ " has logged in Application based on permison of: "+operatorG.substring(04));					
					takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","userCreatedscreenHome.jpeg");
					Thread.sleep(60000);
					String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=TAJIKISTAN_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
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
						actualResults.set(8, "Operators Download has not been downloaded to Plaza based on BBDD");
						executionResults.set(8,"Fallado");						
						summaryBug = "Operators Download has not been downloaded to Plaza based on BBDD";
						erroTextBug = "Operators Download has not been downloaded to Plaza based on BBDD";
						componentBug = "HM";
						severityBug = 1;
						priority = 4;
						testFailed = true;
						bugAttachment = false;						
						driver.close();				
						testLink();		
						System.err.println("Operators Download has not been downloaded to Plaza based on BBDD");
						System.out.println("It has been tested in version of: "+ getVersion(applevelTested));							
						fail("Operators Download has not been downloaded to Plaza based on BBDD");
					}
						Thread.sleep(1000);
						actualResults.set(8, "Operators Download with version: "+transactions.get(0)+" has been downloaded to Plaza with filename: "+transactions.get(1));
						driver.close();
						testLink();
						System.out.println("Operators Download with version: "+transactions.get(0)+" has been downloaded to Plaza with filename: "+transactions.get(1));
						System.out.println("It has been tested in version of: "+ getVersion(applevelTested));
					
				}catch(Exception e){
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}

}