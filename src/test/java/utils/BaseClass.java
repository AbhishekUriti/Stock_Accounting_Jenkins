package utils;

import ERPpages.AdminLogin;
import ERPpages.AdminLogout;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Properties conpro;

    @BeforeSuite
    public void setupReport(){
        ExtentSparkReporter spark=new ExtentSparkReporter("reports/ERPTest.html");
        extent=new ExtentReports();
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Data Driven Testing");
        spark.config().setReportName("Test Execution Results");
    }

    @BeforeMethod
    public void setupBrowser(ITestResult result) throws IOException {
        test=extent.createTest(result.getMethod().getMethodName());
        conpro=new Properties();
        String propPath = System.getProperty("user.dir") + "/PropertyFiles/stock.properties";
        conpro.load(new FileInputStream(propPath));
        if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get(conpro.getProperty("Url"));
            AdminLogin adminLogin=new AdminLogin(driver);
            adminLogin.login("admin","master");
        }else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(conpro.getProperty("Url"));
            AdminLogin loginpage = new AdminLogin(driver);
            loginpage.login("admin", "master");
        }else {
            try {
                throw new IllegalArgumentException("Browser value not matching");
            }catch (IllegalArgumentException e){
                Reporter.log(e.getMessage(),true);
            }
        }
    }

    @AfterMethod
    public void tearBrowser(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.SUCCESS){
            test.pass("Passed");
        } else if (result.getStatus()==ITestResult.FAILURE) {
            String path=captureScreenshot(result.getName());
            test.fail("Failed: "+result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }
        AdminLogout logout=new AdminLogout(driver);
        logout.adminLogout();
        driver.quit();

        if(driver!=null) driver.quit();
    }
    @AfterSuite
    public void flushReport(){
        extent.flush();
    }

    public String captureScreenshot(String name) throws IOException {
        String path=System.getProperty("user.dir")+"reports/screenshots/"+name+".png";
        FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE),new File(path));
        return path;
    }
}
