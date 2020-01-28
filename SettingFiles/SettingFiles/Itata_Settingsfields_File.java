package SettingFiles;
import static SettingFiles.Generic_Settingsfields_File.*; 


public class Itata_Settingsfields_File{
	//Execution fields integration conf.
		
	public static String accountModeIta="Crear";	
	public static String memberMode="Crear";	
	public static String [] accountSelectedIta = {"Estándar","Comercial","Exenta"};		
	public static String BoHostUrl="http://virtualbo-qa/BOQAHostItata/web/forms/core/login.aspx";
	public static String BoPlazaUrl="http://virtualbo-qa/BOQAPlazaItata/web/forms/core/login.aspx";
	public static String MCSUrlIta = "http://virtualmcs-qa/MCS_Itata";	
	
	public static void loginPageErr() throws Exception{
		String pageTitle = driver.getTitle();
		if (!pageTitle.equals("Página de Acceso")){	
			pageSelectedErr=true;
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
		projectNamePath = "Itata\\";
		projectName = "Itata";
		redMineProjectName="14051 Evolutivos Itata 2018 (Desarrollo)";
		testLinkProjectName = "14051:Itata";
		testClassName = classname;
		testPlanPath = "E:\\workspace\\Itata\\Itata\\Itata\\";				
		testLinkTestCase= testname;
		applevelTested=applevel;
	}
	
	
}