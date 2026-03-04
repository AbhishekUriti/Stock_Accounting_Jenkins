package ERPpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Customers {
    WebDriver driver;
    @FindBy(xpath = "//li[@id='mi_a_customers']") WebElement clickCustomers;
    @FindBy(xpath = "(//a[@data-original-title='Add'])[1]") WebElement clickAddBtn;
    @FindBy(xpath= "//input[@name='x_Customer_Number']") WebElement customerNumber;
    @FindBy(xpath= "//input[@name='x_Customer_Name']") WebElement customername;
    @FindBy(xpath= "//textarea[@name='x_Address']") WebElement customerAddress;
    @FindBy(xpath= "//input[@name='x_City']") WebElement customerCity;
    @FindBy(xpath= "//input[@name='x_Country']") WebElement customerCountry;
    @FindBy(xpath= "//input[@name='x_Contact_Person']") WebElement customerContactPerson;
    @FindBy(xpath= "//input[@name='x_Phone_Number']") WebElement customerPhoneNumber;
    @FindBy(xpath= "//input[@name='x__Email']") WebElement customerEmail;
    @FindBy(xpath= "//input[@name='x_Mobile_Number']") WebElement customerMobileNumber;
    @FindBy(xpath= "//input[@name='x_Notes']") WebElement customerNotes;
    @FindBy(xpath= "//button[@name='btnAction']") WebElement customerAddbtn;
    @FindBy(xpath = "//button[text()='OK!']") WebElement clickConfirmBtn;
    @FindBy(xpath = "//button[contains(@class,'ajs-button') and normalize-space()='OK']") WebElement clickAlertOk;
    @FindBy(xpath = "//button[@data-caption='Search Panel']") WebElement clickSearchPanel;
    @FindBy(xpath = "//input[@name='psearch']") WebElement EnterserachTextbox;
    @FindBy(xpath = "//button[@id='btnsubmit']") WebElement clickSearchbutton;
    @FindBy(xpath = "//table[@id='tbl_a_customerslist']//td[@data-name='Customer_Number']//span[@id='el1_a_customers_Customer_Number']") WebElement webTable;

    public Customers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //write boolean method for supplier
    public boolean Add_Customer(String customerName,String Address,String City,
                                String Country,String cperson,String Pnumber,String email,String mNumber,String Notes) throws InterruptedException {
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mywait.until(ExpectedConditions.elementToBeClickable(this.clickCustomers)).click();
        mywait.until(ExpectedConditions.elementToBeClickable(this.clickAddBtn)).click();
        mywait.until(ExpectedConditions.visibilityOf(this.customerNumber));
        //capture supplier number
        String Expected_cus= this.customerNumber.getAttribute("value");
        this.customername.sendKeys(customerName);
        this.customerAddress.sendKeys(Address);
        this.customerCity.sendKeys(City);
        this.customerCountry.sendKeys(Country);
        this.customerContactPerson.sendKeys(cperson);
        this.customerPhoneNumber.sendKeys(Pnumber);
        this.customerEmail.sendKeys(email);
        this.customerMobileNumber.sendKeys(mNumber);
        this.customerNotes.sendKeys(Notes);
        this.customerAddbtn.sendKeys(Keys.ENTER);
        mywait.until(ExpectedConditions.elementToBeClickable(this.clickConfirmBtn));
        this.clickConfirmBtn.click();
        Thread.sleep(2000);
        mywait.until(ExpectedConditions.elementToBeClickable(this.clickAlertOk));
        this.clickAlertOk.click();
        Thread.sleep(1000);
        if(!this.EnterserachTextbox.isDisplayed()) {
            mywait.until(ExpectedConditions.elementToBeClickable(this.clickSearchPanel)).click();
        }
        mywait.until(ExpectedConditions.visibilityOf(this.EnterserachTextbox));
        this.EnterserachTextbox.clear();
        this.EnterserachTextbox.sendKeys(Expected_cus);
        this.clickSearchbutton.click();
        Thread.sleep(2000);
        String Actual_cus = webTable.getText().trim();
       return Actual_cus.equals(Expected_cus);

    }

}
