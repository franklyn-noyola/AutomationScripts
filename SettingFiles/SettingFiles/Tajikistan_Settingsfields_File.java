package SettingFiles;
import static SettingFiles.Generic_Settingsfields_File.*; 



public class Tajikistan_Settingsfields_File {
	
	
	//Execution fields integration conf.
	public static String accountModeTaj;
	public static boolean fieldCheck=true;
	public static String [] companyLink = {"Prepayment","Exempt"}; 	
	public static String BoHostUrl="http://virtualbo-qa/BOQAHostTajikistan/web/forms/core/login.aspx";
	public static String BoPlazaUrl="http://virtualbo-qa/BOQAPlazaTajikistan/web/forms/core/login.aspx";
	public static String MCSUrl = "http://virtualmcs-qa/MCS_Tajikistan";


	public static void loginPageErr() throws Exception{
		if (!driver.getPageSource().contains("Back-Office")){	
			pageSelectedErr = true;
			takeScreenShot("E:\\Selenium\\","paginaInicioErr"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paginaInicioErr.jpeg");					
			actualResults.set(0,"An error has ocurred in the Login Page");
			executionResults.set(0,"Fallado");
			stepNotExecuted = executionNumber.size()-1;
			for (int i = stepNotExecuted;i>0;i--) {
				executionNumber.remove(i);
			}
			summaryBug = "An error has ocurred in the Login Page";
			erroTextBug = "An error has ocurred in the Login Page";
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
		LanguageSelected="Eng";
		projectNamePath = "Tajikistan\\";
		projectName = "Tajikistan";
		redMineProjectName="12638-Tajikistan TCS (Desarrollo)";
		testLinkProjectName = "12638:Tajikistan";
		testClassName = classname;
		testPlanPath = "E:\\workspace\\Tajikistan\\Tajikistan\\Tajikistan\\";				
		testLinkTestCase= testname;
		applevelTested=applevel;
	}
	
}