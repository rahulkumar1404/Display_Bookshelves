package testBase; 

import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 
import java.net.URL; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.Properties; 

import org.openqa.selenium.OutputType; 
import org.openqa.selenium.Platform; 
import org.openqa.selenium.TakesScreenshot; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver; 
import org.openqa.selenium.remote.DesiredCapabilities; 
import org.openqa.selenium.remote.RemoteWebDriver; 
import org.testng.annotations.AfterClass; 
import org.testng.annotations.BeforeClass; 
import org.testng.annotations.Parameters;

public class BaseClass { // Define the BaseClass

    static public WebDriver driver; // Declare a static WebDriver instance
    public Properties p; // Declare a Properties instance

    @BeforeClass // Annotation to run this method before any test methods in the class
    @Parameters({"os", "browser"}) // Define parameters for the method
    public void setup(String os, String br) throws IOException { // Setup method with parameters for OS and browser

        // Loading properties file
        FileReader file = new FileReader(".//src//test//resources//config.properties"); // Create FileReader for properties file
        p = new Properties(); // Initialize Properties object
        p.load(file); // Load properties from file

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) { // Check if execution environment is remote
            DesiredCapabilities capabilities = new DesiredCapabilities(); // Initialize DesiredCapabilities

            // Set platform based on OS parameter
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11); // Set platform to Windows 11
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC); // Set platform to Mac
            } else {
                System.out.println("No matching OS"); // Print message if no matching OS
                return; // Exit method
            }

            // Set browser based on browser parameter
            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome"); // Set browser to Chrome
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge"); // Set browser to Edge
                    break;
                default:
                    System.out.println("No matching browser"); // Print message if no matching browser
                    return; // Exit method
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities); // Initialize RemoteWebDriver with capabilities
        }

        if (p.getProperty("execution_env").equalsIgnoreCase("local")) { // Check if execution environment is local

            // Initialize local WebDriver based on browser parameter
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(); // Initialize ChromeDriver
                    break;
                case "edge":
                    driver = new EdgeDriver(); // Initialize EdgeDriver
                    break;
                default:
                    System.out.println("Invalid browser name."); // Print message if invalid browser name
                    return; // Exit method
            }
        }

        driver.get(p.getProperty("appURL")); // Open application URL
        driver.manage().deleteAllCookies(); // Delete all cookies
        driver.manage().window().maximize(); // Maximize browser window
    }

    @AfterClass // Annotation to run this method after all test methods in the class
    public void tearDown() { // Tear down method
        driver.quit(); // Quit the WebDriver instance
    }

    public String captureScreen(String tname) throws IOException { // Method to capture screenshot

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // Generate timestamp for screenshot file name
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver; // Cast WebDriver to TakesScreenshot
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE); // Capture screenshot as file

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png"; // Define target file path
        File targetFile = new File(targetFilePath); // Create target file

        sourceFile.renameTo(targetFile); // Rename source file to target file

        return targetFilePath; // Return target file path
    }
}
