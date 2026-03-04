package driverFactory;

import ERPpages.Customers;
import ERPpages.Suppliers;
import org.testng.annotations.Test;
import utils.BaseClass;
import utils.ExcelFileUtil;

import java.io.IOException;

import static utils.BaseClass.driver;
import static utils.BaseClass.test;

public class AppTest extends BaseClass {
    String inputPath="./DataTables/ERPData.xlsx";
    String supplierPath="./DataTables/SupplierResults.xlsx";
    String customerpath="./DataTables/CustomerResults.xlsx";

    @Test(priority = 0)
    public void startSupplier() throws Throwable {
        ExcelFileUtil xl=new ExcelFileUtil(inputPath);
        int rc=xl.rowCount("suppliers");
        test.info("No of rows in supplier sheet "+rc);
        System.out.println("No of rows in supplier sheet "+rc);
        for (int i=1;i<=rc;i++){
            test.assignAuthor("Abhishek");
            test.assignCategory("Functional Testing");
            String sname=xl.getCellData("suppliers",i,0);
            String address=xl.getCellData("suppliers",i,1);
            String city=xl.getCellData("suppliers",i,2);
            String country=xl.getCellData("suppliers",i,3);
            String cperson=xl.getCellData("suppliers",i,4);
            String pnumber=xl.getCellData("suppliers",i,5);
            String email=xl.getCellData("suppliers",i,6);
            String mnumber=xl.getCellData("suppliers",i,7);
            String notes=xl.getCellData("suppliers",i,8);
            test.info(sname+"   "+address+"   "+city+"   "+country+"    "+cperson+"   "+pnumber+"   "+email+"    "+mnumber);
            Suppliers sup=new Suppliers(driver);
            boolean res=sup.Add_Supplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
            if(res){
                xl.setCellData("suppliers",i,9,"pass",supplierPath);
                test.pass("Supplier Added Sucessfully");
            }else {
                xl.setCellData("suppliers",i,9,"fail",supplierPath);
                test.fail("Supplier Added Unsucessfully");
            }
        }
    }

    @Test(priority = 1)
    public void startCustomer() throws Throwable {
        ExcelFileUtil xl=new ExcelFileUtil(inputPath);
        int rc=xl.rowCount("Customers");
        test.info("No of rows in customers sheet "+rc);
        System.out.println("No of rows in customers sheet "+rc);
        for (int i=1;i<=rc;i++) {
            test.assignAuthor("Abhishek");
            test.assignCategory("Functional Testing");
            String custname = xl.getCellData("Customers", i, 0);
            String custaddress = xl.getCellData("Customers", i, 1);
            String custcity = xl.getCellData("Customers", i, 2);
            String custcountry = xl.getCellData("Customers", i, 3);
            String custcperson = xl.getCellData("Customers", i, 4);
            String custpnumber = xl.getCellData("Customers", i, 5);
            String custemail = xl.getCellData("Customers", i, 6);
            String custmnumber = xl.getCellData("Customers", i, 7);
            String custnotes = xl.getCellData("Customers", i, 8);
            test.info(custname + "   " + custaddress + "   " + custcity + "   " + custcountry + "    " + custcperson + "   " + custpnumber + "   " + custemail + "    " + custmnumber+" "+custnotes);
            Customers cust = new Customers(driver);
            boolean res = cust.Add_Customer(custname, custaddress, custcity, custcountry, custcperson, custpnumber, custemail, custmnumber, custnotes);
            if (res) {
                xl.setCellData("Customers", i, 9, "pass",customerpath);
                test.pass("Customers Added Sucessfully");
            } else {
                xl.setCellData("Customers", i, 9, "fail", customerpath);
                test.fail("Customers Added Unsucessfully");
            }
        }
    }
}
