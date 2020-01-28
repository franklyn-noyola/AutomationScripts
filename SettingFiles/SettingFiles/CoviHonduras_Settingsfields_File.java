package SettingFiles;
import static SettingFiles.Generic_Settingsfields_File.*; 


public class CoviHonduras_Settingsfields_File{	
	public static String BoHostUrl="http://virtualbo-qa/BOQAHostCoviHonduras/web/forms/core/login.aspx";
	public static String BoPlazaUrl="http://virtualbo-qa/BOQAPlazaCoviHonduras/web/forms/core/login.aspx";		
	public static String CaCUrl="http://virtualcac-qa/CACQACovihonduras/web/forms/core/login.aspx";	
	public static String MCSUrl = "http://virtualmcs-qa/MCS_CoviHonduras";
	public static String [] accountSelection = {"PrePago","PostPago"};
	
	public static void loginPageErr() throws Exception{
		String pageTitle = driver.getTitle();					
		if (!pageTitle.equals("Página de Acceso")){						
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");
			actualResults.set(0,"Un error ha ocurrido en la Página de Inicio");
			executionResults.set(0,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>0;i--) {
				executionNumber.remove(i);
			}
			summaryBug = "Un error ha ocurrido en la Página de Inicio";
			erroTextBug = "Un error ha ocurrido en la Página de Inicio";
			componentBug = "BO";
			severityBug = 3;
			priority = 2;
			testFailed = true;
			bugAttachment = true;
			pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\paginaInicioErr.jpeg";						
			driver.close();
		}		
				
	}
	public static void configurationSection(String applevel, String testname, String classname) throws Exception{
		LanguageSelected="Esp";
		projectNamePath = "CoviHonduras\\";
		projectName = "CoviHonduras";
		redMineProjectName= "13392-Covihonduras (Desarrollo)";
		testLinkProjectName = "13392:CoviHonduras";
		testClassName = classname;
		testPlanPath = "E:\\workspace\\CoviHonduras\\CoviHonduras\\CoviHonduras\\";				
		testLinkTestCase= testname;
		applevelTested=applevel;
	}
         	
}
