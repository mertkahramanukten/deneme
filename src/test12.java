import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.firefox.FirefoxDriver;




public class test12 {

    boolean isFail = false;
    boolean Giris = false;
    String testname = "Simpletest";
    ExtentReports extent;
    ExtentTest logger;
    WebDriver driver;

    @BeforeTest
    public void startReport() throws InterruptedException {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/cases/" + testname + "Report.html",
                true);
        extent.addSystemInfo("Host Name", "SoftwareTestingMaterial").addSystemInfo("Environment", testname + " CASE")
                .addSystemInfo("User Name", "Mert Kahraman UKTEN");
        extent.loadConfig(new File(System.getProperty("User.dir") + "\\extent-config.xml"));
        System.setProperty("webdriver.gecko.driver", "D:\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.get("https://tr-tr.facebook.com/");
        Thread.sleep(2000);
    }

    @Test(priority = 0)
    public void KullaniciAdi() throws Exception {


        try {
            logger = extent.startTest("KullaniciAdi");
            WebElement KullaniciAdi = driver.findElement(By.id("email"));
                    KullaniciAdi.sendKeys("defseda@sdvsdfv.com");
            String screenshotPath = getScreenshot(driver, "KullaniciAdi");
            Assert.assertTrue(true);
            logger.log(LogStatus.PASS, "KullaniciAdi Girildi." + logger.addScreenCapture(screenshotPath));


        } catch (Exception e) {
            String screenshotPath = getScreenshot(driver, "home1");
            logger = extent.startTest("KullaniciAdi");
            Assert.assertTrue(true);
            logger.log(LogStatus.FAIL,
                    "KullaniciAdi Alanı Bulunamadı." + logger.addScreenCapture(screenshotPath));
            isFail = true;
        }
        extent.endTest(logger);

    }

    @Test(priority = 1)
    public void sifre() throws Exception {


        try {
            logger = extent.startTest("sifre");
            WebElement Sifre = driver.findElement(By.id("pass"));
                    Sifre.sendKeys("defseda@sdvsdfv.com");
            String screenshotPath = getScreenshot(driver, "sifre");
            Assert.assertTrue(true);
            logger.log(LogStatus.PASS, "Şifre Girildi." + logger.addScreenCapture(screenshotPath));


        } catch (Exception e) {
            String screenshotPath = getScreenshot(driver, "sifre");
            logger = extent.startTest("sifre");
            Assert.assertTrue(true);
            logger.log(LogStatus.FAIL,
                    "Şifre Alanı Bulunamadı." + logger.addScreenCapture(screenshotPath));
            isFail = true;
        }
        extent.endTest(logger);
    }

    @Test(priority = 2)
    public void giris() throws Exception {


        try {
            logger = extent.startTest("giris");
            WebElement Giris = driver.findElement(By.xpath("(.//*[@id='u_0_2'])"));
            Giris.click();
            String screenshotPath = getScreenshot(driver, "giris");
            Assert.assertTrue(true);
            logger.log(LogStatus.PASS, "Girişe Basıldı." + logger.addScreenCapture(screenshotPath));

        } catch (Exception e) {
            String screenshotPath = getScreenshot(driver, "giris");
            logger = extent.startTest("giris");
            Assert.assertTrue(true);
            logger.log(LogStatus.FAIL,
                    "Girişe Basılamadı." + logger.addScreenCapture(screenshotPath));
            isFail = true;
        }

    }



    @AfterTest
    public void endReport() {
        extent.flush();
        logger.getEndedTime();
        extent.endTest(logger);
        extent.close();
        driver.close();


    }
    private void waitFunction(WebDriver driver2, String path) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
    }
    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(Long.valueOf(System.currentTimeMillis()));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

}

