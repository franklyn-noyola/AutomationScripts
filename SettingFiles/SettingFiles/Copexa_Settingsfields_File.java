package SettingFiles;
import static SettingFiles.Generic_Settingsfields_File.*; 


public class Copexa_Settingsfields_File {	
	//Execution fields integration conf.	
	public static String [] accountSelectedCopexa = {"Prepago","Exenta"};	
	public static String accountModeCopexa="Crear";
	public static String memberMode="Crear";	
	public static String BoHostUrl="http://virtualbo-qa/BOQAHostCopexa/web/forms/core/login.aspx";
	public static String BoPlazaUrl="http://virtualbo-qa/BOQAPlazaCopexa/web/forms/core/login.aspx";
	public static String MCSUrl = "http://virtualmcs-qa/MCS_Copexa";	
	
	public static void loginPageErr() throws Exception{
		String pageTitle = driver.getTitle();
		if (!pageTitle.equals("Página de Login")){	
			pageSelectedErr=true;
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");					
			actualResults.set(0,"Un error ha ocurrido en la PÃ¡gina de Inicio");
			executionResults.set(0,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>0;i--) {
				executionNumber.remove(i);
			}
			summaryBug = "Un error ha ocurrido en la Página de Inicio";
			erroTextBug = "Un error ha ocurrido en la Página de Inicio";
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
		projectNamePath = "Copexa\\";
		projectName = "Copexa";
		redMineProjectName = "14044-(Inst. 10295) Maintenance Copexa 2018 (Desarrollo)";
		testLinkProjectName = "14044:Copexa";
		testClassName = classname;
		testPlanPath = "E:\\workspace\\Copexa\\Copexa\\Copexa\\";				
		testLinkTestCase= testname;
		applevelTested=applevel;
	}
	
	
}