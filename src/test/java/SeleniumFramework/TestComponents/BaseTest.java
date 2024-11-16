package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	
	public WebDriver driver;
	public LandingPage lPage;
	
	public WebDriver initializerDriver() throws IOException {
		
		//properties class
		
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFramework\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser"); 
		
		
		if(browsername.contains("chrome")) {
			//Chromedriver
			ChromeOptions options = new ChromeOptions(); //Tu run chrome in headless mode
			
			if(browsername.contains("headless")) {
				options.addArguments("headless"); //to run chrome in headless mode
			}
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver(options);
		    
		    driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browsername.equalsIgnoreCase("edge")){
			//Edgedriver
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox")){
			//firefox
		}
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	       driver.manage().window().maximize();
	       return driver;
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json to string\
		
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to hashmap - Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			});
		 
		return data;
		
	}
	
	public String getScreenshot1(String testcaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver= initializerDriver();
		lPage = new LandingPage(driver);
	    lPage.goTo();
	    return lPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
}
