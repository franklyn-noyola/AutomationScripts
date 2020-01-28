package SettingFiles;
import static SettingFiles.Generic_Settingsfields_File.*; 

import static org.junit.Assert.fail;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class R3R5Ciralsa_Settingsfields_File {
	public static String [] accountLink= new String[]{"Cliente", "Exenta"};
	public static boolean noPan = false;
	public static String vehicleFeature = "Crear"; 
	public static boolean panExist  = false;
	public static int rowSelectedCi;
	public static String [] expeditionImporte;
	public static String BoHostUrl="http://virtualbo-qa/BOQAHostr3r5ciralsa/web/forms/core/login.aspx";
	public static String BoPlazaUrl="http://virtualbo-qa/BOQAPlazar3r5ciralsa/web/forms/core/login.aspx";
	public static String MCSUrl = "http://virtualmcs-qa/MCS_ciralsa";
	
	public static void loginPageErr() throws Exception{
		String pageTitle = driver.getTitle();
		if (!pageTitle.equals("Página de acceso")){	
			pageSelectedErr=true;
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");					
			actualResults.set(0,"Un error ha ocurrido en la Página de Acceso");
			executionResults.set(0,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>0;i--) {
				executionNumber.remove(i);
			}
			summaryBug = "Un error ha ocurrido en la Página de Acceso";
			erroTextBug = "Un error ha ocurrido en la Página de Acceso";
			componentBug = "BO";
			priority = 3;
			severityBug = 2;
			testFailed = true;
			bugAttachment = true;
			pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\paginaInicioErr.jpeg";
			applicationVisible=false;
			driver.close();
		}		
				
	}
	public static void configurationSection(String applevel, String testname, String classname) throws Exception{
			LanguageSelected="Esp";
			projectNamePath = "R3R5Ciralsa\\";
			projectName = "R3R5Ciralsa";
			redMineProjectName = "13772-Sistema de Peaje R3 R5 Ciralsa (Desarrollo)";
			testLinkProjectName = "13772:R3R5Ciralsa";
			testClassName = classname;
			testPlanPath = "E:\\workspace\\R3R5Ciralsa\\R3R5Ciralsa\\R3R5Ciralsa\\";				
			testLinkTestCase= testname;
			applevelTested=applevel;
	}   
	
	public static String dniLetra (String Dtype, int dni){
 		if (Dtype == "DNI"){
 			return String.valueOf(dni)+(matletT.charAt(dni % 23));
 		}else{
 			return String.valueOf(matletT.charAt(dni % 23))+(dni);
 		}
	  }
	public static void itemSearchandSelectCiralsa() throws Exception{		
  		String recordDis = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span")).getText();
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
  					 
		if (selRecord>0) {
			selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));				
			WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));			
			rowSelectedCi = 2;
			int countRow = 1;
			if (tablerows.size()<2) {
				noItemFound = true;
				return;
			}
			for (int i =1; i<=selRecord;i++) {		
				
				tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table"));
				tablerows = tableres.findElements(By.tagName("tr"));						
				if (selRecord>=tablerows.size()) {
					if (driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[5]/input")).isEnabled()){
						elementClick("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[5]/input");
					}else {
						noItemFound = true;
						return;
					}
					rowSelectedCi=2;
					countRow = countRow+tablerows.size()-2;							
				}	
				
				optionSelectedId = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[2]");				
				recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[1]/input")).getAttribute("id");
					for (rowSelectedCi=2;rowSelectedCi<=tablerows.size();rowSelectedCi++) {															
						if (countRow == selRecord) {														
							elementClick(recordFound);					
							i = selRecord+1;
							break;									
						}else {
							countRow=countRow+1;
						}
						if (countRow>selRecord) {
							rowSelectedCi=2;
							countRow = countRow-5;
						}
						optionSelectedId = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[2]");				
						recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[1]/input")).getAttribute("id");					
						Thread.sleep(500);						
					}
					Thread.sleep(100);				
			}
		}else {
			noItemFound = true;
			return;
		}
	}
	
	public static void merchantTable() throws Exception{		
  		String recordDis =getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
  					 
		if (selRecord>0) {
			selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));				
			WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));			
			rowSelectedCi = 2;
			int countRow = 1;
			if (tablerows.size()<2) {
				noItemFound = true;
				return;
			}
			for (int i =1; i<=selRecord;i++) {		
				
				tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table"));
				tablerows = tableres.findElements(By.tagName("tr"));						

				if (selRecord>=tablerows.size()) {
					if (driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input")).isEnabled()){
						elementClick("//*[@id='content']/div[2]/div/div/div/div/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
					}else {
						noItemFound = true;
						return;
					}
					rowSelectedCi=2;
					countRow = countRow+tablerows.size()-2;							
				}	
				
				optionSelectedId = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[2]");				
				recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[1]/input")).getAttribute("id");
					for (rowSelectedCi=2;rowSelectedCi<=tablerows.size();rowSelectedCi++) {															
						if (countRow == selRecord) {														
							elementClick(recordFound);					
							i = selRecord+1;
							break;									
						}else {
							countRow=countRow+1;
						}
						if (countRow>selRecord) {
							rowSelectedCi=2;
							countRow = countRow-5;
						}
						optionSelectedId = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[2]");				
						recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[1]/input")).getAttribute("id");					
						Thread.sleep(500);						
					}
					Thread.sleep(100);				
			}
		}else {
			noItemFound = true;
			return;
		}
	}
	public static void rangeTable() throws Exception{	
  		String recordDis = getText("//*[@id='rangesZoneTable']/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
		if (selRecord>0) {
			selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));
			WebElement tableres = driver.findElement(By.xpath("//*[@id='rangesZoneTable']/table/tbody/tr[2]/td[2]/div"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));			
			rowSelectedCi = 3;
			int rangeRow = 1;			
			int countRow = 1;
			if (tablerows.size()<3) {
				noItemFound = true;
				return;
			}
			for (int i =1; i<=selRecord;i++) {		
				
				tableres = driver.findElement(By.xpath("//*[@id='rangesZoneTable']/table/tbody/tr[2]/td[2]/div"));
				tablerows = tableres.findElements(By.tagName("tr"));						
				
				if (selRecord>=tablerows.size()) {
					if (driver.findElement(By.xpath("//*[@id='rangesZoneTable']/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input")).isEnabled()){
						elementClick("//*[@id='rangesZoneTable']/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
					}else {
						noItemFound = true;
						return;
					}
					rowSelectedCi=3;					
					rangeRow = rangeRow+(tablerows.size()-2);
					countRow = countRow+tablerows.size()-3;							
				}	

		
				recordFound = driver.findElement(By.xpath("//*[@id='rangeTable_row_"+rangeRow+"']/td[1]/input")).getAttribute("id");
					for (rowSelectedCi=3;rowSelectedCi<=tablerows.size();rowSelectedCi++) {															
						if (countRow == selRecord) {														
							elementClick("rangeTable_row_"+rangeRow);					
							i = selRecord+1;
							break;									
						}else {
							countRow=countRow+1;
						}
						if (countRow>selRecord) {
							rowSelectedCi=2;
							countRow = countRow-5;
						}				
						recordFound = driver.findElement(By.xpath("//*[@id='rangeTable_row_"+rangeRow+"']/td[1]/input")).getAttribute("id");;					
						Thread.sleep(500);
						rangeRow = rangeRow +1;
					}
					Thread.sleep(100);				
			}
		}else {
			noItemFound = true;
			return;
		}
	}
	public static void debtorNoteTable() throws Exception{	
  		String recordDis = getText("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");  	//
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
		if (selRecord>0) {
			selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));
			WebElement tableres = driver.findElement(By.xpath("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));						
			int rangeRow = 1;						
			if (tablerows.size()<2) {
				noItemFound = true;
				return;
			}			
			for (int i =1; i<=selRecord;i++) {										
				tableres = driver.findElement(By.xpath("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table"));
				tablerows = tableres.findElements(By.tagName("tr"));														
																				
					for (rowSelectedCi=1;rowSelectedCi<tablerows.size();rowSelectedCi++) {										
						recordFound = driver.findElement(By.xpath("//*[@id='debtorNoteTable_radio_"+rangeRow+"']")).getAttribute("id");
						if (recordFound.equals("debtorNoteTable_radio_"+selRecord)) {							
							optionSelectedId = getText("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[2]");
							elementClick("debtorNoteTable_radio_"+selRecord);														
							i = selRecord+1;
							break;									
						}else {
							rangeRow = rangeRow+1;
						}
					}		
						if (!recordFound.equals("debtorNoteTable_radio_"+selRecord)){
							elementClick("//*[@id='debtorNoteList']/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
							Thread.sleep(500);
			}			}
		}else {
			noItemFound = true;
			return;
		}
	}
	
	public static void bagSelectionForExpedition() throws Exception{
		try {			
			String recordDis = getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");
	  		int selRecord;
	  		int boxChecked=0;
	  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
	  		
			if (selRecord>0) {
				if (selRecord>5) {
		  			boxChecked = ranNumbr(1,3);
		  		}else {
		  			boxChecked = 1;
		  		}				
				WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));				
				String bagSelected;
				expeditionImporte = new String[boxChecked];
				int boxSel = 0;
				for (int selection = 1; selection<= boxChecked;selection++) {					
					selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));

					int countRow=1;
					if (driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]/input")).isEnabled()) {
						elementClick("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]/input");
					}
					for (int i =1; i<=selRecord;i++) {						
						tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table"));
						tablerows = tableres.findElements(By.tagName("tr"));						
							for (rowSelectedCi=1;rowSelectedCi<=tablerows.size()-1;rowSelectedCi++) {															
								if (countRow == selRecord) {
									bagSelected = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[1]/input")).getAttribute("class");
									if (bagSelected.contains("ng-empty")) {
										elementClick("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[1]/input");
										expeditionImporte[boxSel]=getText("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[8]");										
										
									}
									boxSel = boxSel+1; 
									i = selRecord+1;									
									break;									
							}else {
								countRow=countRow+1;
							}																
							Thread.sleep(100);
							rowSelectedCi = rowSelectedCi +1;
						}
							if (countRow != selRecord) {
								elementClick("//*[@id='content']/div[2]/div/div[1]/div[3]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
								rowSelectedCi = 1;
							}
						Thread.sleep(100);				
					}
				}
			}else {
				noItemFound = true;
				return;
			}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	
	}
	public static void memberSearch() throws Exception{
		try {			
			String recordDis = getText("//*[@id='memberSearchDiv']/div[2]/div[1]/table/tbody/tr/td[3]/span");
	  		int selRecord;
	  		int boxChecked=0;
	  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
	  		int selectedPan = ranNumbr(1,selRecord);
			if (selRecord>0) {
				if (selectedPan == selRecord) {
					elementClick("ctl00_ContentZone_BtnSelectAllMembers");
		  			return;
				}else {
					if (selRecord>5) {
						boxChecked = ranNumbr(1,3);
					}else {
						elementClick("ctl00_ContentZone_BtnSelectAllMembers");
						return;
					}
				}
				WebElement tableres = driver.findElement(By.xpath("//*[@id='memberSearchDiv']/div[2]/div[2]/table"));				
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));				
				String bagSelected;
				expeditionImporte = new String[boxChecked];
				int boxSel = 0;
				for (int selection = 1; selection<= boxChecked;selection++) {					
					selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));

					int countRow=1;
					if (driver.findElement(By.xpath("//*[@id='memberSearchDiv']/div[2]/div[1]/table/tbody/tr/td[1]/input")).isEnabled()) {
						elementClick("//*[@id='memberSearchDiv']/div[2]/div[1]/table/tbody/tr/td[1]/input");
					}
					for (int i =1; i<=selRecord;i++) {						
						tableres = driver.findElement(By.xpath("//*[@id='memberSearchDiv']/div[2]/div[2]/table"));
						tablerows = tableres.findElements(By.tagName("tr"));						
							for (rowSelectedCi=1;rowSelectedCi<=tablerows.size()-1;rowSelectedCi++) {															
								if (countRow == selRecord) {									//
									bagSelected = driver.findElement(By.xpath("//*[@id='memberSearchDiv']/div[2]/div[2]/table/tbody/tr["+rowSelectedCi+"]/td[1]/input")).getAttribute("class");
									if (bagSelected.contains("ng-empty")) {
										elementClick("//*[@id='memberSearchDiv']/div[2]/div[2]/table/tbody/tr["+rowSelectedCi+"]/td[1]/input");																	
									}
									boxSel = boxSel+1; 
									i = selRecord+1;									
									break;									
							}else {
								countRow=countRow+1;
							}																
							Thread.sleep(100);
							rowSelectedCi = rowSelectedCi +1;
						}
							if (countRow != selRecord) {
								elementClick("//*[@id='memberSearchDiv']/div[2]/div[1]/table/tbody/tr/td[4]/input");
								rowSelectedCi = 1;
							}
						Thread.sleep(100);				
					}
				}
			}else {
				noItemFound = true;
				return;
			}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	
	}
	public static void transactionsSearch() throws Exception{
		try {			
			String recordDis = getText("//*[@id='transactionsSearchDiv']/div[2]/table/tbody/tr/td[3]/span");
	  		int selRecord;
	  		int boxChecked=0;
	  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
	  		
			if (selRecord>0) {
				if (selRecord>5) {
		  			boxChecked = ranNumbr(1,3);
		  		}else {
		  			boxChecked = 1;
		  		}				
				WebElement tableres = driver.findElement(By.xpath("//*[@id='transactionsSearchDiv']/div[3]/table"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));				
				String bagSelected;				
				int boxSel = 0;
				for (int selection = 1; selection<= boxChecked;selection++) {					
					selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));

					int countRow=1;
					if (driver.findElement(By.xpath("//*[@id='transactionsSearchDiv']/div[2]/table/tbody/tr/td[1]/input")).isEnabled()) {
						elementClick("//*[@id='transactionsSearchDiv']/div[2]/table/tbody/tr/td[1]/input");
					}
					for (int i =1; i<=selRecord;i++) {						
						tableres = driver.findElement(By.xpath("//*[@id='transactionsSearchDiv']/div[3]/table"));
						tablerows = tableres.findElements(By.tagName("tr"));						
							for (rowSelectedCi=1;rowSelectedCi<=tablerows.size()-1;rowSelectedCi++) {															
								if (countRow == selRecord) {
									bagSelected = driver.findElement(By.xpath("//*[@id='transactionsSearchDiv']/div[3]/table/tbody/tr["+rowSelectedCi+"]/td[1]/img")).getAttribute("class");
									if (!bagSelected.contains("ng-hide")) {
										elementClick("//*[@id='transactionsSearchDiv']/div[3]/table/tbody/tr["+rowSelectedCi+"]/td[1]/img");
									}
									boxSel = boxSel+1; 
									i = selRecord+1;									
									break;									
							}else {
								countRow=countRow+1;
							}																
							Thread.sleep(100);
							rowSelectedCi = rowSelectedCi +1;
						}
							if (countRow != selRecord) {
								elementClick("//*[@id='transactionsSearchDiv']/div[2]/table/tbody/tr/td[4]/input");
								rowSelectedCi = 1;
							}
						Thread.sleep(100);				
					}
				}
			}else {
				noItemFound = true;
				return;
			}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	
	}

	public static void invoicesSearch() throws Exception{	
  		String recordDis = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");
  		
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
		if (selRecord>0) {
			selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));
			WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));						
			int rangeRow = 1;						
						
			for (int i =1; i<=selRecord;i++) {										
				tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table"));
				tablerows = tableres.findElements(By.tagName("tr"));																																		
					for (rowSelectedCi=1;rowSelectedCi<tablerows.size();rowSelectedCi++) {		
						recordFound = driver.findElement(By.id("statementsTable_radio_"+rangeRow)).getAttribute("id");
						if (recordFound.equals("statementsTable_radio_"+selRecord)) {							
							optionSelectedId = getText("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[3]");
							elementClick("statementsTable_radio_"+selRecord);														
							i = selRecord+1;
							break;									
						}else {
							rangeRow = rangeRow+1;
						}
					}		
						if (!recordFound.equals("statementsTable_radio_"+selRecord)){
							elementClick("//*[@id='content']/div[2]/div/div/div[4]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
							Thread.sleep(500);
			}			}
		}else {
			noItemFound = true;
			return;
		}
	}
	
	public static void discountSearch() throws Exception{	
  		String recordDis = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");  		
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
		if (selRecord>0) {
			selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));
			WebElement tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));						
			int rangeRow = 1;						
			for (int i =1; i<=selRecord;i++) {										
				tableres = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table"));
				tablerows = tableres.findElements(By.tagName("tr"));																																		
					for (rowSelectedCi=1;rowSelectedCi<tablerows.size();rowSelectedCi++) {		
						//*[@id="discountTable_radio_12"]
						recordFound = driver.findElement(By.xpath("//*[@id='discountTable_radio_"+rangeRow+"']")).getAttribute("id");
						if (recordFound.equals("discountTable_radio_"+selRecord)) {							
							optionSelectedId = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[2]");
							elementClick("discountTable_radio_"+selRecord);														
							i = selRecord+1;
							break;									
						}else {
							rangeRow = rangeRow+1;
						}
					}		
						if (!recordFound.equals("discountTable_radio_"+selRecord)){
							elementClick("//*[@id='content']/div[2]/div/div/div/div[1]/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
							Thread.sleep(500);
			}			}
		}else {
			noItemFound = true;
			return;
		}
	}
	
}
