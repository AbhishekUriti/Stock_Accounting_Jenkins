package ERPpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Suppliers {
WebDriver driver;
    @FindBy(xpath = "//li[@id='mi_a_suppliers']") WebElement clickSuppliers;
    @FindBy(xpath = "(//a[@data-caption='Add'])[1]") WebElement clickAddBtn;
    @FindBy(xpath= "//input[@name='x_Supplier_Number']") WebElement supplierNumber;
    @FindBy(xpath= "//input[@name='x_Supplier_Name']") WebElement suppliername;
    @FindBy(xpath= "//textarea[@name='x_Address']") WebElement supplierAddress;
    @FindBy(xpath= "//input[@name='x_City']") WebElement supplierCity;
    @FindBy(xpath= "//input[@name='x_Country']") WebElement supplierCountry;
    @FindBy(xpath= "//input[@name='x_Contact_Person']") WebElement supplierContactPerson;
    @FindBy(xpath= "//input[@name='x_Phone_Number']") WebElement supplierPhoneNumber;
    @FindBy(xpath= "//input[@id='x__Email']") WebElement supplierEmail;
    @FindBy(xpath= "//input[@name='x_Mobile_Number']") WebElement supplierMobileNumber;
    @FindBy(xpath= "//textarea[@name='x_Notes']") WebElement supplierNotes;
    @FindBy(xpath= "//button[@name='btnAction']") WebElement supplierAddbtn;
    @FindBy(xpath = "//button[text()='OK!']") WebElement clickConfirmBtn;
    @FindBy(xpath = "//button[contains(@class,'ajs-button') and normalize-space()='OK']") WebElement clickAlertOk;
    @FindBy(xpath = "//button[@data-caption='Search Panel']") WebElement clickSearchPanel;
    @FindBy(xpath = "//input[@name='psearch']") WebElement EnterserachTextbox;
    @FindBy(xpath = "//button[@name='btnsubmit']") WebElement clickSearchbutton;
    @FindBy(xpath = "//table[@id='tbl_a_supplierslist']//td[@data-name='Supplier_Number']//span[@id='el1_a_suppliers_Supplier_Number']") WebElement webTable;

    public Suppliers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //write boolean method for supplier
    public boolean Add_Supplier(String supplierName,String Address,String City,
                                String Country,String cperson,String Pnumber,String email,String mNumber,String Notes) throws Throwable
    {
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mywait.until(ExpectedConditions.elementToBeClickable(this.clickSuppliers));
        this.clickSuppliers.click();
        mywait.until(ExpectedConditions.elementToBeClickable(this.clickAddBtn));
        this.clickAddBtn.click();
        mywait.until(ExpectedConditions.visibilityOf(this.supplierNumber));
        //capture supplier number
        String Expected_Sup= this.supplierNumber.getAttribute("value");
        this.suppliername.sendKeys(supplierName);
        this.supplierAddress.sendKeys(Address);
        this.supplierCity.sendKeys(City);
        this.supplierCountry.sendKeys(Country);
        this.supplierContactPerson.sendKeys(cperson);
        this.supplierPhoneNumber.sendKeys(Pnumber);
        this.supplierEmail.sendKeys(email);
        this.supplierMobileNumber.sendKeys(mNumber);
        this.supplierNotes.sendKeys(Notes);
        this.supplierAddbtn.sendKeys(Keys.ENTER);
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
        this.EnterserachTextbox.sendKeys(Expected_Sup);
        this.clickSearchbutton.click();
        Thread.sleep(2000);
        String Actual_Sup = webTable.getText().trim();
        return Actual_Sup.equals(Expected_Sup);
    }
}
