package RedMine;

import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.Generic_Settingsfields_File.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CreateBug{
	/*public static List <String> testSteps;	
	public static String testName; 
	public static String [] stepstoRe;
	public static String redMineProjectName = "12638-Tajikistan TCS (Desarrollo)";
	public static int execNumber = 5;	
	public static String bugErr = "Error en las telercargas de BackOffice";
	//public static String redMineProjectName = "12638-Tajikistan TCS (Desarrollo)";
	public static String BOVersion = "BO_25.0.21_f8a36ba0";
	public static String hmVersion = "HM_25.0.21";*/
	
	@Test
	public void redMineInit() throws Exception{
		setUp();
		LanguageSelected = "Esp";
		//stepstoRe = new String[] {"1.- Entrar al Backoffice", "2.- Loguearse al backoffice", "3.- Entrar al Gestor de Operadores", "4.- Crear Operador", "5.- Bajar a Telecargas"};
		testClassName = "BOHost_operatorCreation";
		driver.get("http://virqaautomation:81/redmine/");
		//driver.get(redMineUrl);
		//String pageTitle = driver.getTitle();
		if (!driver.getPageSource().contains("Tecsidel")) {
			if (LanguageSelected.equals("Eng")) {
				System.err.println("RedMine is not available, please check connection");
				fail("RedMine is not available, please check connection");
			}else {
				System.err.println("RedMine no está disponible, favor verificar conexión");
				fail("RedMine no está disponible, favor verificar conexión");
			}
		}
		
		Thread.sleep(100);
		componentBug = "HM";
		severityBug = 2;
		priority = 3;
		pathAttachment="E:\\qa.png";
		summaryBug = "Error en las telecargas";
		erroTextBug = "Error en las telecargas";
		bugAttachment = true;
		bugNumber= "9";
		//closeDefect();
		createDefect();
	}
	public static void login() throws Exception{
		Thread.sleep(3000);		
		if (!driver.getPageSource().contains("Tecsidel")) {
			if (LanguageSelected.equals("Eng")) {
				System.err.println("RedMine is not available, please check connection");
				fail("RedMine is not available, please check connection");
			}else {
				System.err.println("RedMine no está disponible, favor verificar conexión");
				fail("RedMine no está disponible, favor verificar conexión");
			}
		}
		Thread.sleep(1000);		
		if (driver.getPageSource().contains("Iniciar sesión")) {
			elementClick("Iniciar sesión");
		}else{
			elementClick("Sign in");
		}

		SendKeys("username","automated.user");		
		SendKeys("password","Demo.1234");
		elementClick("login");
		Thread.sleep(100);
	}
	
	//@Before
	public static void setup() throws Exception{
			setUp();
		}
	
	public static void createDefect() throws Exception{
			login();

		try {			
			Thread.sleep(1000);
			elementClick("project-jump");
			Thread.sleep(1000);
			SendKeys("projects-quick-search",redMineProjectName);
			elementClick(redMineProjectName);
			Thread.sleep(1000);
			elementClick("new-object");
			Thread.sleep(1000);
			elementClick("Nueva petición");
			SendKeys("issue_subject",summaryBug);
			driver.findElement(By.id("issue_description")).sendKeys("PASOS A SEGUIR:\n");
			int stepsNumbr = 1;
			for (int i=0;i<executionNumber.size();i++) {
				driver.findElement(By.id("issue_description")).sendKeys(stepsNumbr+".- "+stepsToReproduce.get(i));
				driver.findElement(By.id("issue_description")).sendKeys("\n");
				stepsNumbr = stepsNumbr + 1;
			}			
			driver.findElement(By.id("issue_description")).sendKeys("\nRESULTADOS ACTUALES:\n");			
			driver.findElement(By.id("issue_description")).sendKeys(erroTextBug);
			new Select(driver.findElement(By.id("issue_assigned_to_id"))).selectByVisibleText("Franklyn Garcia");
			new Select(driver.findElement(By.id("issue_custom_field_values_1"))).selectByIndex(severityBug);
			new Select(driver.findElement(By.id("issue_custom_field_values_2"))).selectByVisibleText(componentBug);							
			new Select(driver.findElement(By.id("issue_priority_id"))).selectByIndex(priority);
			
			Thread.sleep(100);
			if (bugAttachment==true) {
				driver.findElement(By.name("attachments[dummy][file]")).sendKeys(pathAttachment);
			}
			elementClick("commit");
			Thread.sleep(3000);
			String getBug = getText("//*[@id='content']/h2");
			bugNumber = getBug.substring(getBug.indexOf("#")+1);			
			Thread.sleep(2000);
			
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw (e);
		}
		
	}
	
	public static void closeDefect() throws Exception{		
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Calendar cal = Calendar.getInstance();
		Thread.sleep(1000);
		elementClick("Iniciar sesión");
		Thread.sleep(500);
		SendKeys("username","automated.user");		
		SendKeys("password","Demo.1234");
		elementClick("login");
		try {
			Thread.sleep(2000);
			SendKeys("q",bugNumber);
			driver.findElement(By.id("q")).submit();
			Thread.sleep(500);
			elementClick("Modificar");
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("issue_status_id"))).selectByVisibleText("Done");
			Thread.sleep(1000);
			SendKeys("issue_due_date",dateFormat.format(cal.getTime()));
			Thread.sleep(500);
			WebElement donePer = new Select(driver.findElement(By.id("issue_done_ratio"))).getFirstSelectedOption();
			String donePercentage = donePer.getText();
			if (!donePercentage.equals("100 %")) {
				new Select(driver.findElement(By.id("issue_done_ratio"))).selectByVisibleText("100 %");;
			}			
			Thread.sleep(1000);
			SendKeys("issue_notes","Verificada en maqueta QA:\n");
			Thread.sleep(1000);
			getVersion(applevelTested);
			driver.findElement(By.id("issue_notes")).sendKeys("BO: "+BOVersion+"\n");
			Thread.sleep(1000);
			driver.findElement(By.id("issue_notes")).sendKeys("HM: "+hmVersion);			
			Thread.sleep(1000);
			elementClick("//*[@id='issue-form']/input[6]");
			Thread.sleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
			System.err.print(e.getMessage());
			fail(e.getMessage());
			throw (e);
		}
	}
	
	
	
	
}