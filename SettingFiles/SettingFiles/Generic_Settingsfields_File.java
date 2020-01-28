package SettingFiles;

import static org.junit.Assert.fail;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Generic_Settingsfields_File {
	public static boolean applicationVisible = true;
	public static String PAN;
	public static Actions action;		
	public static String redMineProjectName;
	public static int selRecord;
	public static String searchButton = "ctl00_ButtonsZone_BtnSearch_IB_Button";
	public static String confirmarButton="ctl00_ButtonsZone_BtnSubmit_IB_Button";
	public static int aTable;
	public static String confirmButton = "ctl00_ButtonsZone_BtnValidate_IB_Button"; 
	public static boolean noItemFound = false;
	public static boolean accountClosed = false;
	public static String accountMode;
	public static String projectName;
	public static int rowSel;
	public static String LanguageSelected;	
	public static String testPlanPath;	
	public static boolean recordFoundDisabled=false;
	public static boolean pageSelectedErr = false;
	public static boolean noRecordFound = false;
	public static String applicationVer;
	public static String [] genderS = new String[] {"Femenino", "Femenino", "Masculino", "Femenino", "Femenino", "Masculino", "Masculino", "Masculino", "Masculino", "Femenino"};
	public static final String Letter_Comb = "TRWAGMYFPDXBNJZSQVHLCKE";
	public static String RUCid = "ctl00_ContentZone_ctrlAccountData_txt_RUC_box_data";
	public final static String testLinkUrl ="http://172.18.131.129/testlink"; 
	public static int stepNotExecuted; 
	public static String applevelTested;
	public static String mcsVersion = "lbl_version";
	public static boolean settlementFunc=false;
	public static int  priority;
	public static int selRow;
	public static int selectedRow;
	public static String [] passportLetter= {"A","B","C"};
	public static String SettlementName;
	public static int rowCalc;
	public static String catSelect;
	public static String dTablesName;
	public static boolean noVehAllowed;
	//public final static String mantisBTUrl = "http://172.18.129.176/mantis";	
	public final static String redMineUrl = "http://virqaautomation:81/redmine/login";
	//Execution fields integration conf.
	public static boolean passTax=false;
	public static int countRow;
	public static boolean duplicatePass=false;
	public static String textSearched;
	public static String projectNamePath;
	public static String testLinkTestCase;
	public static ArrayList<String>stepData=new ArrayList<String>();;
	public static List<String> executionNumber=new ArrayList<String>();;
	public static List <String>executionResults=new ArrayList<String>();;
	public static List <String> actualResults=new ArrayList<String>();;
	public static List <String> stepsToReproduce=new ArrayList<String>();;	
	public static String testLinkProjectName;
	public static String componentBug;
	public static boolean testFailed = false;
	public static boolean bugAttachment=false;
	public static int severityBug;
	public static String summaryBug;
	public static String pathAttachment;
	public static String bugNumber;
	public static String erroTextBug;
	public static String testClassName;	
	public static String dateverTransacciones;	
	public static String hmVersion;
	public static int catSelected;
	public static boolean NoDocumentToSelect = false;
	public static String optionVehicle; 
	public static String recordFound;
	public static int a;
	public static String optionSelectedId;
	public static boolean MPSTDFlow = false;
	public static boolean SDTOption = false;
	public static boolean noSDT= false;	
	public static boolean MPOption = false;
	public static int selectAccount;
	public static int selOp;
	public static String pageSelected;	public static boolean vehicleErr = false;
	public static boolean discountTable= false;
	public static boolean vDiscountSel = false;
	public static boolean accountOnly = true;
	public static String vDiscountName;
	public static String companylinkselected;
	public static int carModelSel;
	public static String confirmationMessage2;
	public static String accountType;
	public static String errorMessage;
	public static String paymentType;
	public static String accountSelected;
	public static String passPortid = "ctl00_ContentZone_ctrlAccountData_txt_ID_box_data";	
	public static String taxId = "ctl00_ContentZone_ctrlAccountData_txt_taxNumber_box_data";
	public static String infoCuenta0 = "ctl00_ContentZone_ctrlAccountData_radio_company_0";
	public static String infoCuenta1 = "ctl00_ContentZone_ctrlAccountData_radio_company_1";
	public static String titulofield = "ctl00_ContentZone_ctrlAccountData_cmb_title_cmb_dropdown";
	public static String namef = "ctl00_ContentZone_ctrlAccountData_txt_forname_box_data";
	public static String surnamef ="ctl00_ContentZone_ctrlAccountData_txt_surname_box_data";
	public static String addressf = "ctl00_ContentZone_ctrlAccountData_txt_address_box_data";
	public static String cpf = "ctl00_ContentZone_ctrlAccountData_txt_postcode_box_data";
	public static String townf = "ctl00_ContentZone_ctrlAccountData_txt_town_box_data";
	public static String countryf = "ctl00_ContentZone_ctrlAccountData_txt_country_box_data";
	public static String emailf ="ctl00_ContentZone_ctrlAccountData_txt_email_box_data";
	public static String phoneCel = "ctl00_ContentZone_ctrlAccountData_txt_mobile_box_data";
	public static String workPhone = "ctl00_ContentZone_ctrlAccountData_txt_daytimephone_box_data";
	public static String perPhone = "ctl00_ContentZone_ctrlAccountData_txt_homephone_box_data";
	public static String faxPhone = "ctl00_ContentZone_ctrlAccountData_txt_fax_box_data";
	public static String companyf = "ctl00_ContentZone_ctrlAccountData_txt_company_box_data";
	public static String contactf = "ctl00_ContentZone_ctrlAccountData_txt_contact_box_data";	
	public static String confirmationMessage;
	public static String [] sexSelc = new String[] {"Sra", "Sra", "Sr", "Sra", "Sra", "Sr", "Sr", "Sr", "Sr", "Sra"};
	public static boolean errorTagAssignment = false;
	public static String tagIdNmbr;
	public static String [] colorS = new String[]{"Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo"};
	public static String matletT = "TRWAGMYFPDXBNJZSQVHLCKE";
	public static String accountNumbr; 
	public static int carSel;
	public static int carModel;
	public static String matriNu;
	public static String vehtypeModel;
	public static String vehtypeKind;
	public static String [][] cocheModels = {{"Seat","Volkswagen","Ford","Fiat"},{"Ibiza","Polo","Fiesta","Punto"},{"León","Passat","Focus","Stilo"}};
	public static String [][] camionModels = {{"Mercedes-Benz","Scania"},{"Axor","R500"},{"Actros","P400"}};
	public static String [][] furgonetaModels = {{"Mercedes-Benz","Fiat"},{"Vito","Scudo"},{"Citan","Ducato"}};
	public static String [][] cicloModels = {{"Yamaha","Honda"},{"XT1200Z","Forza 300"},{"T-MAX SX","X-ADV"}};
	public static String [][] autoBusModels = {{"DAIMLER-BENZ","VOLVO"},{"512-CDI","FM-12380"},{"DB 605","FM 300"}};	
	public static Calendar calF; 
	public static Calendar calT;
	public static SimpleDateFormat sft;
	private static int dateMFrom;
	public static int delm;
	public static String fileDatafilled;
	public static String fileError;
	public static String folderSel;
	public static String projectSel;
	public static String additionalTitle;
	public static String descriptionSubject;
	public static String errorLev;
	public static WebDriver driver;	
	public boolean acceptNextAlert = true;	
	public static StringBuffer verificationErrors = new StringBuffer();
	public static int numbering;	
	public static String errorText;
	public static String tipoSel;
	public static String loginField = "BoxLogin";
	public static String passField = "BoxPassword";
	public static String loginButton = "BtnLogin";
	public static String opIdField = "ctl00_ContentZone_operatorId_box_data";
	public static String nameField = "ctl00_ContentZone_forename_box_data";
	public static String lastNameField = "ctl00_ContentZone_surname_box_data";
	public static String emailField = "ctl00_ContentZone_email_box_data";
	public static String groupOperator = "ctl00_ContentZone_group_cmb_dropdown";
	public static String pwdField = "ctl00_ContentZone_password_box_data";
	public static String repeatpwdField = "ctl00_ContentZone_password2_box_data";
	public static String hourNumber = "ctl00_ContentZone_TxtNomHousr_box_data";
	public static String submitBtn = "ctl00_ButtonsZone_BtnSubmit";	
	public static String MCSVersion;
	public static String BOVersion;	
	public static String [] nameOp = new String[] {"Pilar", "Mavi", "Franklyn", "Javier", "Fatima", "Marc", "Miguel", "Francisco", "Oscar", "Maria"};
	public static String [] genderSEng = new String[] {"Female", "Female", "Male", "Male", "Female", "Male", "Male", "Male", "Male", "Female"};
	public static String [] genderSEsp = new String[] {"Femenino", "Femenino", "Masculino", "Masculino", "Femenino", "Masculino", "Masculino", "Masculino", "Masculino", "Femenino"};
	public static String [] sexSelcEsp = new String[] {"Sra", "Sra", "Sr", "Sr", "Sra", "Sr", "Sr", "Sr", "Sr", "Sra"};
	public static String [] sexSelcEn = new String[] {"Mrs", "Mrs", "Mr", "Mr", "Mrs", "Mr", "Mr", "Mr", "Mr", "Mrs"};
	public static String [] addressTec = new String[] {"CALLE SAN MAXIMO, 3","CALLE SAN MAXIMO, 3","Castanyer 29", "CALLE SAN MAXIMO, 3","CALLE SAN MAXIMO, 3","Catanyer 29","Edificio Tecsidel, P.T. de Boecillo","Edificio Tecsidel, P.T. de Boecillo","Edificio Tecsidel, P.T. de Boecillo","Edificio Tecsidel, P.T. de Boecillo"};
	public static String [] cpAdress = new String[]{"280410", "280410", "080220", "280410", "280410","080220","471510","471510","471510","471510"};
	public static String [] townC = new String []{"Madrid", "Madrid", "Barcelona", "Madrid", "Madrid", "Barcelona", "Valladolid","Valladolid","Valladolid","Valladolid"};
	public static String [] workPhone1 = new String []{"913530810","913530810","932922110","913530810","913530810","932922110","983546603","983546603","983546603","983546603"};
	public static String [] lastNameOp = new String[] {"Bonilla", "Garrido", "Garcia", "Chico", "Romano", "Navarro", "Sanchez", "Castro", "Bailon", "Blanco"};
	public static String [] lastNameOp2 = new String[] {"Rol", "Sanchez", "Noyola", "Muñoz", "Cerror", "Ordoñez", "Grande", "Castaño", "Azcaray", "Ramos"};
	public static String sectionTitle;
	public static String emptyField;
	  //Edit buttons icons configuration.	  	  
	  public static String [] mandaFields = {"ctl00_ContentZone_ctrlAccountData_txt_surname_box_data","ctl00_ContentZone_ctrlAccountData_txt_forname_box_data",
			  								"ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data","ctl00_ContentZone_ctrlAccountData_txt_address_box_data","ctl00_ContentZone_ctrlAccountData_txt_Settlements_box_data","ctl00_ContentZone_ctrlAccountData_txt_country_box_data",
			  								"ctl00_ContentZone_ctrlAccountData_txt_mobile_box_data","ctl00_ContentZone_type_payment_cmb_dropdown"};

	  //Edit buttons icons configuration.	  	  
	  public static Timestamp timest = new Timestamp (System.currentTimeMillis());
	  public static String timet = timest.toString().replace("-", "").replace(" ", "").replace(":", "").substring(0,14);	  	  
	  public static void takeScreenShot(String pathS, String fname) throws Exception {
		    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(pathS, fname));
	  }
	  
	  public static void setUp() throws Exception{
  		System.setProperty("webdriver.chrome.driver", "e:\\workspace\\SeleniumLibrary\\chromedriver.exe");
  		ChromeOptions options = new ChromeOptions();
  		options.addArguments("--lang=es-ES");  		
  			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
  			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
  			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
  				//ChromeOptions opts =  new ChromeOptions(); poner esta opción cuando se vaya a subir a Git
  				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); poner esta opción cuando se vaya a subir a Git
  				driver = new ChromeDriver(options);//opts); poner esta opción cuando se vaya a subir al Git
  				driver.manage().window().maximize();	
  				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	  }	  	  	  
		public static void tearDown() throws Exception{			  
		    driver.quit();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		}
	  
	  public static void borrarArchivosTemp(String tempPath) throws Exception{
			File imagTmp = new File (tempPath);
			if (imagTmp.exists()){
				if (imagTmp.isDirectory()){
					File [] imaglist = imagTmp.listFiles();				
					if (imaglist.length > 0){
					 for (int i = 0; i< imaglist.length;i++){
							File delimg = imaglist[i];						
							delimg.delete();						
						}				
					}
				}
			}
			Thread.sleep(1000);
		  }	
	  
	  

    	public static void elementClick(String byID) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);			
			if (byID.contains("//")){
    			driver.findElement(By.xpath(byID)).click();
    			return;
    		}else{
    			List <WebElement> idFound = driver.findElements(By.id(byID));
    			if (idFound.size()>0) {
    				driver.findElement(By.id(byID)).click();;    			
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    				return;
    			}
    			List <WebElement> nameFound = driver.findElements(By.name(byID));
    			if (nameFound.size()>0) {
    				driver.findElement(By.name(byID)).click();
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    				return;
    			}
    			List <WebElement> linkFound = driver.findElements(By.linkText(byID));
    			if (linkFound.size()>0) {
    				driver.findElement(By.linkText(byID)).click();
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    				return;
    			}
    			List <WebElement> partialLinkFound = driver.findElements(By.partialLinkText(byID));
    			if (partialLinkFound.size()>0) {
    				driver.findElement(By.partialLinkText(byID)).click();
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    				return;
    			}
    			
    			List <WebElement> cssSelectorFound = driver.findElements(By.cssSelector(byID));
    			if (cssSelectorFound.size()>0) {
    				driver.findElement(By.cssSelector(byID)).click();
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    				return;
    			}
    			
    			List <WebElement> classFound = driver.findElements(By.className(byID));
    			if (classFound.size()>0) {
    				driver.findElement(By.className(byID)).click();
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    				return;
    			}
    			
    			
										
    		}
				if (LanguageSelected.equals("Eng")) {
					System.err.println (byID+" WebElement not found, please check WebElement Name");
					fail (byID+" WebElement not found, please check WebElement Name");
				}else {
					System.err.println(byID+ " Elemento Web no encontrado, favor verifique el nombre del elemento");
					fail(byID+ " Elemento Web no encontrado, favor verifique el nombre del elemento");
				}
    	}
    	
   	public static void selectDropDownV(String by) {
   			Select vDropdown;
   			if (by.contains("//")) {
   				vDropdown = new Select (driver.findElement(By.xpath(by)));
   			}else {
   				vDropdown = new Select (driver.findElement(By.id(by)));
   			}
				List<WebElement> dd = vDropdown.getOptions();		
    			Random rand = new Random();
    		int vdd = rand.nextInt(dd.size());
    			if (vdd<=0){vdd = 1;}	
    			if (vdd>=dd.size()){vdd=vdd-1;}    
    		if (by.contains("//")) {
    			new Select (driver.findElement(By.xpath(by))).selectByIndex(vdd);
    		}else {
    			new Select (driver.findElement(By.id(by))).selectByIndex(vdd);
    		}
    		
    		
    	  }
    	
    	public static void selectDropDown(String by) {
    		Select vDropdown = new Select (driver.findElement(By.id(by)));
    		List<WebElement> dd = vDropdown.getOptions();
    		if (dd.size()>0) {
    			Random rand = new Random();
    			int vdd = rand.nextInt(dd.size());
    			if (vdd<0){vdd = 0;}	
    			if (vdd>=dd.size()){vdd=vdd-1;}
    			if (by.contains("//")) {
    				new Select (driver.findElement(By.xpath(by))).selectByIndex(vdd);
    			}else {
    				new Select (driver.findElement(By.id(by))).selectByIndex(vdd);
    			}
    		}else {
    			return;
    		}
    		
    	  }
   

   	
   	public static String dateSel(int date1,int date2 ) throws Exception{
			calF = Calendar.getInstance();
			calT = Calendar.getInstance();
		 sft = new SimpleDateFormat("dd/MM/yyyy");
		calF.set(ranNumbr(date1,date2), dateMFrom, ranNumbr(1,31));
		calT.set(ranNumbr(date1,date2), ranNumbr(1,12), ranNumbr(1,31));
		return sft.format(calT.getTime());

	}
   	public static int ranNumbr(int min, int max) {
 		  Random rand = new Random();
 		  numbering = min+rand.nextInt((max-min)+1);
 		  if (numbering <0){
 			 numbering = 0;
 		  }
 		  return numbering; 		  
 	  }

   	public static void loginPageMCS(String usr, String pwd) throws Exception{
			driver.findElement(By.id("txt_login")).sendKeys(usr);
			driver.findElement(By.id("txt_password")).sendKeys(pwd);
			driver.findElement(By.id("btn_login")).click();
			Thread.sleep(1000);
	}
   	
   	public static void loginPage(String usr, String pwd) throws Exception{
   			driver.findElement(By.id(loginField)).sendKeys(usr);
   			driver.findElement(By.id(passField)).sendKeys(pwd);
   			driver.findElement(By.id(loginButton)).click();
   			Thread.sleep(1000);
   	}
   	
   	public static void SendKeys (String path, String toSend){   		
   		if (path.contains("//")){
   			driver.findElement(By.xpath(path)).clear();
   			driver.findElement(By.xpath(path)).sendKeys(toSend);
   		}else{
   			driver.findElement(By.id(path)).clear();
   			driver.findElement(By.id(path)).sendKeys(toSend);    			
   		}
   	}
   	
   	public static String hourFormat(int Hour1,int Hour2,int Min1, int Min2){
			int Hour = ranNumbr(Hour1,Hour2);
			int Min = ranNumbr(Min1,Min2);			
			return String.format("%1$02d",Hour)+":"+String.format("%1$02d",Min);					
		}
   	
   	public static String hourFormat2(int Hour1,int Hour2,int Min1, int Min2,int Seg1, int Seg2){
			int Hour = ranNumbr(Hour1,Hour2);
			int Min = ranNumbr(Min1,Min2);			
			int Seg = ranNumbr(Seg1,Seg2);
			return String.format("%1$02d",Hour)+String.format("%1$02d",Min)+String.format("%1$02d",Seg);
			
		}
		public static boolean isAlertPresent() throws Exception{
			try{
				driver.switchTo().alert();
				return true;
			}catch (Exception e){
				return false;
			}

		}
		
		public static String getVersion (String applicationName){     		
     		if (applicationName.equals("Host") || applicationName.equals("CAC") || applicationName.equals("Plaza")) {
     			if (applicationVer.contains("\n")){								
     				hmVersion = applicationVer.substring(applicationVer.indexOf("\n")+1);
     				BOVersion = applicationVer.substring(0,applicationVer.indexOf("\n"));				
     			}else {					
     				BOVersion = applicationVer.substring(0);
     				hmVersion = "is not running";
     			}		
		
     			if (applicationName.equals("Host")){     				
     				applicationName = "BackOffice Host: "+BOVersion+"/Host Manager: "+hmVersion;				
     			}
     			if (applicationName.equals("Plaza")){ 				 			
     				applicationName = "BackOffice Plaza: "+BOVersion+"/Plaza Manager: "+hmVersion;
     			}			     		     		
     			if (applicationName.equals("CAC")){ 				 			
     				applicationName = "CAC : "+BOVersion+"/CAC Manager: "+hmVersion;
     			}			
     		}
     		if (applicationName.equals("MCS")){ 				 			
     			applicationName = "MCS: "+applicationVer;
 			}			
				return applicationName;				
						
     	}
		
		public static void testPlanReading (int testCase, int stepsData, int actualR, int status) throws Exception{
	 		try { 
	 			File f = new File(testPlanPath+"Plan de Pruebas.xlsx");
	 			FileInputStream ios = new FileInputStream(f); 
	 			XSSFWorkbook workbook = new XSSFWorkbook(ios); 
	 			XSSFSheet sheet = workbook.getSheetAt(testCase); 
	 			Iterator<Row> rowIterator = sheet.iterator(); 
	 			stepData = new ArrayList<>(); 
	 			actualResults = new ArrayList<String>();
	 			executionNumber = new ArrayList<String>();
	 			executionResults = new ArrayList<String>();
	 			while (rowIterator.hasNext()){ 
	 				Row row = rowIterator.next(); 
	 				Iterator<Cell> cellIterator = row.cellIterator(); 
	 				while (cellIterator.hasNext()) { 
	 					Cell cell = cellIterator.next(); 
	 					if(row.getRowNum() > 0){		 						
	 						if(cell.getColumnIndex() == stepsData){// To match column index switch (cell.getCellType()) { case Cell.CELL_TYPE_NUMERIC: columndata.add(cell.getNumericCellValue()+""); break; case Cell.CELL_TYPE_STRING: columndata.add(cell.getStringCellValue()); break; } } } } } ios.close(); System.out.println(columndata); } catch (Exception e) { e.printStackTrace(); } return columndata; }
	 							executionNumber.add(cell.getStringCellValue());		 							
	 						}
	 						if(cell.getColumnIndex() == actualR) {
	 							actualResults.add(cell.getStringCellValue());
	 						}
	 						if(cell.getColumnIndex() == status) {
	 							executionResults.add(cell.getStringCellValue());
	 						}
	 					}
	 				}
	 				ios.close();
	 				
	 			}	 	
			 	workbook.close();
	 		}catch (Exception e) {
	 			e.printStackTrace();
	 		}	 	
 
		}

		public static void searchandSelect() throws Exception{
			String resultsDisplayed = getText("ctl00_ContentZone_tablePager_LblCounter");			
			int NumberShow= resultsDisplayed.indexOf("of ")+3;
			if (NumberShow>0) {
				int countsel = ranNumbr(1,NumberShow)-20;
				int countsel2=0;			
				if (countsel <= 20){ 
					countsel2 = 0;
				}else{
					if (countsel>20 || countsel<=40){
						countsel2 = 1;
					}
					if (countsel>40){
						countsel2 = Math.round((float)countsel/20); 
						}
					for (int i = 1;i<=countsel2;i++){
						elementClick("ctl00_ContentZone_tablePager_BtnNext");
						Thread.sleep(500);
					}
				}
				Thread.sleep(1000);		
				WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List<WebElement> table = tableres.findElements(By.tagName("tr"));
				selectAccount = ranNumbr(2,table.size());
				elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/input");
		
			}else {
				NoDocumentToSelect=true;			
			}
		}
		public static void mandatoryFieldsValidation() throws Exception{
			selOp = ranNumbr(0,9);
			if (emptyField.equals(mandaFields[0])) {
				SendKeys(surnamef,lastNameOp[selOp]);
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[1])) {
				SendKeys(namef,nameOp[selOp]);
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[2])) {
				SendKeys("ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data",lastNameOp2[selOp]);
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[3])) {
				SendKeys(addressf,addressTec[selOp]);
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[4])) {
				elementClick("ctl00_ContentZone_ctrlAccountData_chk_settlements");
				Thread.sleep(300);
				selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_settlements_cmb_dropdown");
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[5])) {
				SendKeys(countryf, "España");
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[6])) {
				SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
				Thread.sleep(200);
			}
			if (emptyField.equals(mandaFields[7])) {
				selectDropDownV("ctl00_ContentZone_type_payment_cmb_dropdown");
				Thread.sleep(1000);
			}
			elementClick(confirmButton);
			
		}
		
	public static void selectAccount() throws Exception{
		String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
		int selRecord;
		if (LanguageSelected.equals("Eng")) {
			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("of")+3));
			if (selRecord<=0) {
				noItemFound = true;
				return;
			}else {
				selRecord = ranNumbr(1,selRecord);
			}
		}else {
			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
			if (selRecord<=0) {
				noItemFound = true;
				return;
			}else {
				selRecord = ranNumbr(1,selRecord);				
			}
		}
		int countRow = 1;					
		for (int i =1; i<=selRecord;i++) {						
			WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));				
			a=2;			 
				for (a=2;a<=tablerows.size();a++) {															
					if (countRow == selRecord) {
						optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[1]/a");
						elementClick(optionSelectedId);
						i = selRecord+1;
						break;									
					}else {
						countRow=countRow+1;						
					}					
			}
			if (countRow != selRecord) {
				elementClick("ctl00_ContentZone_tablePager_BtnNext");
			}
			Thread.sleep(500);				
		}
	}
	public static void selectLinkTable() throws Exception{
		String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");					
		if (LanguageSelected.equals("Eng")) {
			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("of")+3));
			if (selRecord<=0) {
				noItemFound = true;
				return;
			}else {
				selRecord = ranNumbr(1,selRecord);
			}
		}else {
			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
			if (selRecord<=0) {
				noItemFound = true;
				return;
			}else {
				selRecord = ranNumbr(1,selRecord);
			}
		}				
		int countRow = 1;					
		for (int i =1; i<=selRecord;i++) {						
			WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_tbl_logs"));
			List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));						
			aTable=3;
			for (aTable=3;aTable<=tablerows.size();aTable++) {
				optionSelectedId = getText("//*[@id='ctl00_ContentZone_tbl_logs']/tbody/tr["+aTable+"]/td[1]/a");
				if (countRow == selRecord) {						
					elementClick(optionSelectedId);
					i = selRecord+1;
					break;									
				}else {
					countRow=countRow+1;
				}										
			}
			if (countRow != selRecord) {
				elementClick("ctl00_ContentZone_tablePager_BtnNext");
			}
			Thread.sleep(500);				
		}
	}
	  public static String getText(String byID) {
	  		if (byID.contains("//")){
	  			textSearched = driver.findElement(By.xpath(byID)).getText();
	  		}else{
	  			textSearched = driver.findElement(By.id(byID)).getText();
	  		}
	  			return textSearched;
	  		}
	
	  public static void paymentfromCustomerEsp() throws Exception{
			Thread.sleep(500);	
			action = new Actions (driver);
			sectionTitle = getText("ctl00_SectionZone_LblTitle");	
			if (sectionTitle.contains("Pago del cliente") ){						
				takeScreenShot("E:\\Selenium\\","paymentCustomerPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentCustomer.jpeg");
				Thread.sleep(1000);	
				WebElement paymentOp = driver.findElement(By.id("ctl00_ContentZone_CtType_radioButtonList_payBy"));
				List <WebElement> paymentList = paymentOp.findElements(By.tagName("tr"));
				int paymentSel = ranNumbr(0,paymentList.size()-1);
				if (paymentSel<0) {
					paymentSel = 0;
				}
				int selRow = paymentSel+1; 
				elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+paymentSel);
				paymentType = getText("//*[@id='ctl00_ContentZone_CtType_radioButtonList_payBy']/tbody/tr["+selRow+"]/td/label");			
				if (ranNumbr(0,1)==1){
					elementClick("ctl00_ContentZone_CtType_chk_present");
				}
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(3000);
				if (paymentType.equals("cheque")||paymentType.equals("efectivo")){
					if (paymentType.equals("efectivo")){
						action.click(driver.findElement(By.id("ctl00_ContentZone_CtbyCash_txt_received_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(ranNumbr(100,10000))).build().perform();						
						elementClick("ctl00_ContentZone_CtbyCash_BtnCalculate");
						Thread.sleep(100);
					}
					if (paymentType.equals("cheque")){
						SendKeys("ctl00_ContentZone_CtbyCheque_txt_number_box_data", String.valueOf(ranNumbr(10000,999999999)));			
					}				
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","PagodelClientePage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","PagodelClientePayed.jpeg");
					elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
					Thread.sleep(3000);
					sectionTitle = getText("ctl00_SectionZone_LblTitle");;
					if (sectionTitle.contains("Recibo")){
						takeScreenShot("E:\\Selenium\\","ReciboPage"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","RecipoPage.jpeg");
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					}
				}
			}
		
		}  
	  
	public static void paymentfromCustomerEng() throws Exception{
		action= new Actions(driver);
		sectionTitle = getText("ctl00_SectionZone_LblTitle");	
		if (sectionTitle.contains("Payment from customer") ){						
			takeScreenShot("E:\\Selenium\\","paymentCustomerPage"+timet+".jpeg");
			takeScreenShot("E:\\Selenium\\"+projectNamePath+testClassName+"\\attachments\\","paymentCustomer.jpeg");
			Thread.sleep(1000);	
			WebElement paymentOp = driver.findElement(By.id("ctl00_ContentZone_CtType_radioButtonList_payBy"));
			List <WebElement> paymentList = paymentOp.findElements(By.tagName("tr"));
			int paymentSel = ranNumbr(0,paymentList.size()-1);
			if (paymentSel<0) {
				paymentSel = 0;
			}
			int selRow = paymentSel+1; 
			elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+paymentSel);
			paymentType = getText("//*[@id='ctl00_ContentZone_CtType_radioButtonList_payBy']/tbody/tr["+selRow+"]/td/label");			
			if (ranNumbr(0,1)==1){
				elementClick("ctl00_ContentZone_CtType_chk_present");
			}
			Thread.sleep(1000);
			elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
			Thread.sleep(3000);
			if (paymentType.equals("Cheque")||paymentType.equals("Cash")){
				if (paymentType.equals("Cash")){
					action.click(driver.findElement(By.id("ctl00_ContentZone_CtbyCash_txt_received_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(100,10000))).build().perform();
					elementClick("ctl00_ContentZone_CtbyCash_BtnCalculate");
					Thread.sleep(100);
				}
				if (paymentType.equals("Cheque")){
					SendKeys("ctl00_ContentZone_CtbyCheque_txt_number_box_data", String.valueOf(ranNumbr(10000,999999999)));			
				}				
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","PagodelClientePage"+timet+".jpeg");
				takeScreenShot("E:\\Selenium\\"+projectNamePath+testClassName+"\\attachments\\","PagodelClientePayed.jpeg");
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(3000);
				sectionTitle =getText("ctl00_SectionZone_LblTitle");
				if (sectionTitle.contains("Receipt")){
					takeScreenShot("E:\\Selenium\\","ReciboPage"+timet+".jpeg");
					takeScreenShot("E:\\Selenium\\"+projectNamePath+testClassName+"\\attachments\\","RecipoPage.jpeg");
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				}
			}
		}
	
	}
	public static void passportValidation() throws Exception{
		String passPortErr = driver.getPageSource();
		if (passPortErr.contains("must have (1 letter + 7 numbers) or (1 letter + 8 numbers)")|| passPortErr.contains("value already exists in the system")) {
			while (passPortErr.contains("must have (1 letter + 7 numbers) or (1 letter + 8 numbers)")|| passPortErr.contains("value already exists in the system")) {
				SendKeys(passPortid, passportLetter[ranNumbr(0,2)]+ranNumbr(1000000,99999999));
				Thread.sleep(1000);
				elementClick(confirmButton);
				Thread.sleep(1000);
				passPortErr = driver.getPageSource();
			}				
		}
	}
	
	public static void itemSearchedandSelected() throws Exception{
  		String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
  		int itemSelected = 0;
  		if (LanguageSelected.equals("Esp")) {
  			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3));
  			if (selRecord<=0) {
  				noItemFound = true;
  				return;
  			}else {  				
  				itemSelected = ranNumbr(1,selRecord);
  			}
  		}else {
  			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("of")+3));
  			if (selRecord<=0) {
  				noItemFound = true;
  				return;
  			}else {  				
  				itemSelected = ranNumbr(1,selRecord);
  			}
  		}
									
			countRow = 1;
			int sumRow = 1;			
			for (int i =1; i<=selRecord;i++) {						
				WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));		
				countRow = sumRow*(tablerows.size()-1);	
				if (itemSelected<tablerows.size()) {
					selRow = itemSelected+1;						
					recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[1]/input")).getAttribute("id");
					optionSelectedId = getText ("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[3]");
					elementClick(recordFound);
					Thread.sleep(5000);
					break;
				}
				
				if (itemSelected>countRow) {
					if (driver.findElement(By.id("ctl00_ContentZone_tablePager_BtnNext")).isEnabled()){
						elementClick("ctl00_ContentZone_tablePager_BtnNext");	
						Thread.sleep(1000);
						sumRow = sumRow+1;
					}
											
				}				
								
				if (itemSelected==countRow) {
					selRow = tablerows.size();
					recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[1]/input")).getAttribute("id");
					optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[3]");
					elementClick(recordFound);
					Thread.sleep(5000);
					break;
				}
				if (sumRow > 1 && itemSelected<countRow) {
					rowCalc = countRow-itemSelected;
					selRow = tablerows.size()-rowCalc;
					recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[1]/input")).getAttribute("id");
					optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRow+"]/td[3]");
					elementClick(recordFound);
					Thread.sleep(5000);
					break;
				}
				
			}
								
		
	
	}
	
	public static void mainPage (int stepNumbr) throws Exception{
		if (driver.getPageSource().contains("Server Error in")){			
			errorText = driver.findElement(By.xpath("/html/body/span/h1")).getText();
			if (LanguageSelected.equals("Esp")){
				errorText = "No se pude entrar a "+pageSelected+" debido al error: "+errorText+" detectado en BackOffice "+applevelTested;
			}else {
				errorText = "Unable to get into "+pageSelected+" due to an error: "+errorText+" detected in BackOffice "+applevelTested;
			}
			pageSelectedErr = true;
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");					
			actualResults.set(stepNumbr,errorText);
			executionResults.set(stepNumbr,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>stepNumbr;i--) {
				executionNumber.remove(i);
			}
			summaryBug = errorText;
			erroTextBug = errorText;
			componentBug = "BO";
			priority = 4;
			severityBug = 1;
			testFailed = true;
			bugAttachment = true;
			pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\paginaInicioErr.jpeg";
			applicationVisible=false;			

		}
	}
	
	public static void plazaPage (int stepNumbr) throws Exception{
		if (driver.getPageSource().contains("La operación ha fallado")){
			errorText = getText("ctl00_LblError");
			pageSelectedErr = true;
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");					
			actualResults.set(stepNumbr,errorText);
			executionResults.set(stepNumbr,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>stepNumbr;i--) {
				executionNumber.remove(i);
			}
			summaryBug = errorText;
			erroTextBug = errorText;
			componentBug = "BO";
			priority = 2;
			severityBug = 3;
			testFailed = true;
			bugAttachment = true;
			pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\paginaInicioErr.jpeg";
			applicationVisible=true;			

		}
	}
	
	public static void pageSelectedErr (int stepNumbr) throws Exception{
		if (driver.getPageSource().contains("An error has been detected") || driver.getPageSource().contains("Se ha detectado un error")  || driver.getPageSource().contains("La operación ha fallado")){
			errorText = getText("ctl00_ContentZone_lblMsg");
			if (LanguageSelected.equals("Esp")){
				errorText = "No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested;
			}else {
				errorText = "Unable to get into Page "+pageSelected+" due to an error: "+errorText+" detected in BackOffice "+applevelTested;
			}
			pageSelectedErr = true;
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");					
			actualResults.set(stepNumbr,errorText);
			executionResults.set(stepNumbr,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>stepNumbr;i--) {
				executionNumber.remove(i);
			}
			summaryBug = errorText;
			erroTextBug = errorText;
			componentBug = "BO";
			priority = 2;
			severityBug = 3;
			testFailed = true;
			bugAttachment = true;
			pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\paginaInicioErr.jpeg";
			applicationVisible=true;			

		}
	}
	
	
	public static void itemSearchandSelect() throws Exception{
  		String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");  		
  		if (LanguageSelected.equals("Esp")) {
  			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
  		}else {
  			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("of")+3));
  		}
		 
		if (selRecord>0) {
			if (LanguageSelected.equals("Esp")) {
				selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));
			}else {
				selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("of")+3)));
			}					
			int countRow = 1;			
			for (int i =1; i<=selRecord;i++) {
				WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));										
				selectedRow=2;
					for (selectedRow=2;selectedRow<=tablerows.size();selectedRow++) {
						optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectedRow+"]/td[2]");				
						recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectedRow+"]/td[1]/input")).getAttribute("id");
						if (countRow == selRecord) {														
							elementClick(recordFound);					
							i = selRecord+1;
							break;									
						}else {
							countRow=countRow+1;
						}
					}	
					if (countRow!=selRecord) {
						elementClick("ctl00_ContentZone_tablePager_BtnNext");													
					}
					Thread.sleep(500);				
			}
		}else {
			noItemFound = true;
			return;
		}
	}
	
	public static void itemSearchTable() throws Exception{	
  		String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
  		int selRecord;
  		selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
		if (selRecord>0) {
			selRecord=ranNumbr(1,selRecord);									
			int rangeRow = 0;											
			for (int i =1; i<=selRecord;i++) {										
				WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));														
					rowSel = 2;
					for (a=1;a<=tablerows.size();a++) {										
						recordFound = driver.findElement(By.id("ctl00_ContentZone_radio"+rangeRow)).getAttribute("id");
						if (recordFound.equals("ctl00_ContentZone_radio"+(selRecord-1))) {									
							optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSel+"]/td[2]");
							elementClick("ctl00_ContentZone_radio"+rangeRow);														
							i = selRecord+1;
							break;									
						}else {
							rowSel = rowSel+1;
							rangeRow = rangeRow+1;
						}
					}		
						if (!recordFound.equals("ctl00_ContentZone_radio"+(selRecord-1))){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(800);
			}			}
		}else {
			noItemFound = true;
			return;
		}
	}
	
}
