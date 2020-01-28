package TestLink;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.Tajikistan_Settingsfields_File.configurationSection;
import static RedMine.CreateBug.createDefect;
import static RedMine.CreateBug.closeDefect;
import java.util.List;

//import org.junit.Before;
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TestLinkExecution{
	public static List <String> testSteps;
	public static String testName; 
	public static String s;
	public static int a;
	public static String defectLink;
	public static String TestCaseSelected;
	
	
	
	@Test
	public void testLinkinit() throws Exception{
		/*actualResults.add("Se entra correctamente a la página Login del BackOffice de CoviHonduras");
		executionResults.add("Pasado");
		executionNumber.add("1");
		actualResults.add("Usuario se loguea correctamente");
		executionResults.add("Pasado");
		executionNumber.add("2");
		actualResults.add("Se puede entrar a la pantalla Gestión de Operadores");
		executionResults.add("Pasado");
		executionNumber.add("3");
		actualResults.add("Se entraron en todos los campos mandatorios y no mandatorios");
		executionResults.add("Pasado");
		executionNumber.add("4");
		actualResults.add("Se ha creado el Operador correctamente con el Grupo Operador correcto");
		executionResults.add("Pasado");
		executionNumber.add("5");
		actualResults.add("Hay un error en envair telecargas a vía");
		executionResults.add("Fallado");
		executionNumber.add("6");*/
		configurationSection("Host","BOHost_MPSTDFlow-ValidateAfterDiscard","BOHost_MPSTDFlow-ValidateAfterDiscard");
		testPlanReading(14,25,27,28);
		testFailed = false;
		LanguageSelected = "Eng";
		componentBug = "HM";
		severityBug = 5;
		summaryBug = "Error en las telecargas de operadores a vía";
		erroTextBug="Error en las telecargas de operadores a vía";
		//testLinkTestCase = "BOHost_MPSTDFlow-ValidateAfterDiscard";
		applevelTested = "Version de Prueba de TestLink";
		testLinkProjectName = "12638:Tajikistan";
		pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\userCreatedscreenHome.jpeg";		
		testLink();
	}

	
	//@Before
	public void setup() throws Exception{		
			setUp();
		}
	
	//@Test
	public static void testLink() throws Exception{
		setUp();
		Actions action = new Actions (driver);
		try {
			driver.get(testLinkUrl);
			Thread.sleep(2000);
			String pageTitle = driver.getTitle();
			if (!pageTitle.equals("Usuario")) {
				if (LanguageSelected.equals("Eng")) {
					System.err.println("TestLink is not available, please check connection");
					fail("TestLink is not available, please check connection");
				}else {
					System.err.println("TestLink no está disponible, favor verificar conexión");
					fail("TestLink no está disponible, favor verificar conexión");
				}
			}
			Thread.sleep(3000);;
			SendKeys("tl_login","automated.user");
			SendKeys("tl_password","Demo.1234");			
			elementClick("//*[@id='login']/div[3]/input");
			Thread.sleep(1000);
			driver.switchTo().frame(0);
			new Select(driver.findElement(By.xpath("/html/body/div[3]/div/form/select"))).selectByVisibleText(testLinkProjectName);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			Thread.sleep(3000);
			action.click(driver.findElement(By.linkText("Ejecutar Casos de Prueba"))).build().perform();			
			Thread.sleep(1000);
			driver.switchTo().frame("treeframe");
			Thread.sleep(2000);			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ext-gen12"))));			
			elementClick("img.x-tree-ec-icon.x-tree-elbow-end-plus");			
			Thread.sleep(1000);
			WebElement relea = driver.findElement(By.id("ext-gen17"));
			List<WebElement> TestSelect = relea.findElements(By.tagName("li"));
			boolean TCaseFound = false;
			for (int i=1;i<=TestSelect.size();i++) {
				if (i==1) {					
					TestCaseSelected=getText("//*[@id='ext-gen17']/li/div/a/span/span"); 
					
					if (TestCaseSelected.contains(testLinkTestCase)) {
						elementClick("//ul[@id='ext-gen17']/li/div/a/span/span");	
						TCaseFound = true;
					}
				}else {
					TestCaseSelected=getText("//*[@id='ext-gen17']/li["+i+"]/div/a/span/span");					
					if (TestCaseSelected.contains(testLinkTestCase)) {
						elementClick("//*[@id='ext-gen17']/li["+i+"]/div/a/span/span");
						TCaseFound = true;
					}
				}
				if (i==TestSelect.size() && TCaseFound == false) {
					if (LanguageSelected.equals("Eng")) {
						driver.close();
						System.err.println("Unable to update Test Case: "+testLinkTestCase+" beacuse it is not found in TestLink, please check Test Case Name or creat it");
						fail("Unable to update Test Case: "+testLinkTestCase+" beacuse it is not found in TestLink, please check Test Case Name or creat it");						
					}else {
						driver.close();
						System.err.println("No se puede actualizar el Test Case: "+testLinkTestCase+" porque no se encuentra en TestLink, favor verificar nombre o crear dicho test cases");
						fail("No se puede actualizar el Test Case: "+testLinkTestCase+" porque no se encuentra en TestLink, favor verificar nombre o crear dicho test cases");						
					}
				}
			
			}
			Thread.sleep(2000);			
			driver.switchTo().parentFrame();
			Thread.sleep(3000);			
			driver.switchTo().frame("workframe");
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("main_content"))));
			Thread.sleep(3000);
			String parentHandle=driver.getWindowHandle();			
			if (testFailed==false) {
				if (driver.getPageSource().contains("Defectos Encontrados")) {
					WebElement defects = driver.findElement(By.xpath("//*[@id='execution_history']/table/tbody/tr[5]/td/table"));				
					List <WebElement> defectFoundList = defects.findElements(By.tagName("tr"));
					String defectSelected = getText("//*[@id='execution_history']/table/tbody/tr[5]/td/table/tbody/tr["+defectFoundList.size()+"]/td[3]/a");
					bugNumber = defectSelected.substring(0, defectSelected.indexOf(":")-1);
					elementClick(defectSelected);					
					for (String winHandle : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle);			    
					}
					Thread.sleep(1000);
					closeDefect();
					Thread.sleep(2000);
					driver.close();
					driver.switchTo().window(parentHandle);
					driver.switchTo().frame("mainframe");
					driver.switchTo().frame("workframe");
				}
			}		
			Thread.sleep(1000);
			WebElement execRow = driver.findElement(By.xpath("//*[@id='execSetResults']/div[10]/div[1]/table/tbody"));
			List <WebElement> execRowSel = execRow.findElements(By.tagName("tr"));
			a = 0;			
			int execution = 0;			
			for (int i=1;i<execRowSel.size();i++) {
				String rowS = driver.findElement(By.xpath("//*[@id='execSetResults']/div[10]/div[1]/table/tbody/tr["+i+"]")).getAttribute("id");
				
				if (rowS.contains("step_row")) {
					a = a+1;
					
					SendKeys("//*[@id='step_row_"+a+"']/td[5]/textarea[@class='step_note_textarea']",actualResults.get(execution));
					new Select(driver.findElement(By.xpath("//*[@id='step_row_"+a+"']/td[6]/select[@class='step_status']"))).selectByVisibleText(executionResults.get(execution));
					stepsToReproduce.add(getText("//*[@id='step_row_"+a+"']/td[2]/p"));
					Thread.sleep(100);					
					execution = execution +1;					
				}
				if (a==executionNumber.size()) {
					break;						
				}
				
				
			}
			if (testFailed==true) {
				Thread.sleep(500);
				if (applicationVisible==true) {
					SendKeys("//*[@id='execSetResults']/div[10]/table[1]/tbody/tr/td[1]/textarea","Se ha probado en la versión del: "+ getVersion(applevelTested));
				}
				Thread.sleep(1000);
				elementClick("//img[contains(@id,'fastExecf')]");
				Thread.sleep(500);										
				driver.switchTo().parentFrame();			
				driver.switchTo().frame("workframe");
				Thread.sleep(500);				
				WebElement linkBug = driver.findElement(By.xpath("//*[@id='execution_history']/table/tbody/tr[2]"));
				List <WebElement> linkBugCol = linkBug.findElements(By.tagName("td"));				
				for (int i=7;i<=linkBugCol.size();i++) {																			
						String linkDefect = driver.findElement(By.xpath("//*[@id='execution_history']/table/tbody/tr[2]/td["+i+"]/a/img")).getAttribute("title");
						if (linkDefect.equals("Enlazar Defecto Existente")) {
							defectLink = "//*[@id='execution_history']/table/tbody/tr[2]/td["+i+"]/a/img";
							elementClick(defectLink);
							i = linkBugCol.size()+1;
						}					
				}											
				Thread.sleep(500);					
				for (String winHandle : driver.getWindowHandles()) {					
					if (!winHandle.equals(parentHandle)) {
						driver.switchTo().window(winHandle);
						Thread.sleep(500);						
						elementClick("Acceso al Gestor");
					}										
				}
				Thread.sleep(2000);		
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle); 
				}			
				Thread.sleep(100);
				createDefect();				
				Thread.sleep(2000);
				driver.close();
				driver.switchTo().window(parentHandle);			
				Thread.sleep(2000);			
				driver.switchTo().parentFrame();
				driver.switchTo().frame("mainframe");
				driver.switchTo().frame("workframe");
				elementClick(defectLink);
				Thread.sleep(2000);
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);					
				}				
				SendKeys("bug_id",bugNumber);
				driver.findElement(By.id("bug_id")).sendKeys(Keys.ENTER);
				Thread.sleep(6000);		
				SendKeys("bug_notes", "Se ha creado la incidencia No. "+bugNumber+" y asignado al TestCase: "+testLinkTestCase);
				elementClick("addLinkToTL");								
				Thread.sleep(1000);
				elementClick("//input[@value='Cerrar']");				
				Thread.sleep(8000);
				if (LanguageSelected.equals("Esp")) {
					System.out.println("Plan de Pruebas Fallido con la Incidencia No.: "+bugNumber+" asignado a Plan de Pruebas: "+testLinkTestCase+" en TestLink");
				}else {
					System.out.println("Test Plan Failed with assigned Incicende No.: "+bugNumber+" to Test Plan: "+testLinkTestCase+" in TestLink");
				}
				return;
			}
			
				Thread.sleep(1000);
				if (LanguageSelected.equals("Esp")) {
					SendKeys("//*[@id='execSetResults']/div[10]/table[1]/tbody/tr/td[1]/textarea","Se ha probado en la Versión del: "+ getVersion(applevelTested));
				}else {
					SendKeys("//*[@id='execSetResults']/div[10]/table[1]/tbody/tr/td[1]/textarea","It has been tested in Version of: "+ getVersion(applevelTested));
				}
				Thread.sleep(1000);
				elementClick("//img[contains(@id,'fastExecp')]");
				Thread.sleep(2000);
				if (LanguageSelected.equals("Esp")) {
					System.out.println("Plan de Pruebas: "+testLinkTestCase+" se ha ejecutado correctamente, plan de pruebas actualizado en TestLink v1.9.17");
				}else {
					System.out.println("Test Plan: "+testLinkTestCase+" has been executed succesfully, Test plan updated in TestLink v1.9.17");
				}

		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw (e);
		}
		
	}
	
	
	
}