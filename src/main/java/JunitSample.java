import jdk.nashorn.internal.runtime.ECMAErrors;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by GR389658 on 13/10/2017.
 */
public class JunitSample {

    protected  static  WebDriver driver;
    protected  static  final String sampleBankBaseURL = "http://localhost:8080/samplebank/index";

    @BeforeClass
    public static void setUpBeforeClass() throws  Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\GR389658\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(sampleBankBaseURL);
        WebElement userName = driver.findElement(By.id("username"));
        WebElement passWord = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@value='Login']"));
        userName.sendKeys("admin");
        passWord.sendKeys("admin");
        loginButton.click();
        //verifying login successful
        Assert.assertEquals("Welcome, admin (logout)", driver.findElement(By.id("sb-username")).getText());
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.close();
        driver.quit();
    }

    @Before
    public void setUp() throws  Exception {

    }

    @After
    public  void tearDown() throws  Exception {

    }

    @Test
    public void createAccount() throws Exception{
        WebElement addAccLinkButton = driver.findElement(By.xpath("//a[@href='addAccount']"));
        addAccLinkButton.click();

        WebElement customerID = driver.findElement(By.id("ownerCpf"));
        WebElement createAccButton = driver.findElement(By.xpath("//*[@value='Create Account']"));
        customerID.sendKeys("12345678910");
        createAccButton.click();
        Assert.assertEquals("Operation completed with success", driver.findElement(By.id("sb-return-message")).getText());
    }

    @Test
    public void depositBalance() {
        WebElement depositLinkButton = driver.findElement(By.xpath("//a[@href='deposit']"));
        depositLinkButton.click();

        Select drpAccount = new Select(driver.findElement(By.id("targetAccount")));
        WebElement amountText = driver.findElement(By.id("ammount"));
        WebElement depositButton = driver.findElement(By.xpath("//*[@value='Deposit']"));

        drpAccount.selectByIndex(1);
        amountText.sendKeys("98889");
        depositButton.click();

        Assert.assertEquals("Operation completed with success", driver.findElement(By.id("sb-return-message")).getText());
    }

    @Test
    public void withdrawBalance() {
        WebElement depositLinkButton = driver.findElement(By.xpath("//a[@href='deposit']"));
        depositLinkButton.click();

        Select drpAccount = new Select(driver.findElement(By.id("targetAccount")));
        WebElement amountText = driver.findElement(By.id("ammount"));
        WebElement depositButton = driver.findElement(By.xpath("//*[@value='Deposit']"));

        drpAccount.selectByIndex(1);
        amountText.sendKeys("98889");
        depositButton.click();

        Assert.assertEquals("Operation completed with success", driver.findElement(By.id("sb-return-message")).getText());
    }

}
