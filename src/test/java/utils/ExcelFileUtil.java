package utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileUtil {
    XSSFWorkbook wb;
    //write constructor to read excel path

    public ExcelFileUtil(String Excelpath) throws IOException {
        FileInputStream fi=new FileInputStream(Excelpath);
        wb=new XSSFWorkbook(fi);
    }

    //method to count no of rows
    public int rowCount(String Sheetname)
    {
        return wb.getSheet(Sheetname).getLastRowNum();
    }

    //method for reading data
    public String getCellData(String sheetname,int row,int col){
        String data="";
        if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()== CellType.NUMERIC){
            int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
            data=String.valueOf(celldata);
        }else
        {
            data = wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
        }
        return data;
    }
    //method for writing results
    public void setCellData(String sheetName,int row,int column,String status,String WriteExcel)throws Throwable{
        XSSFSheet ws = wb.getSheet(sheetName);
        XSSFRow rowNum = ws.getRow(row);
        XSSFCell cell=rowNum.createCell(column);
        cell.setCellValue(status);
        if(status.equalsIgnoreCase("Pass")){
            XSSFCellStyle style = wb.createCellStyle();
            XSSFFont font = wb.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            ws.getRow(row).getCell(column).setCellStyle(style);
        } else if (status.equalsIgnoreCase("Fail")) {
            XSSFCellStyle style = wb.createCellStyle();
            XSSFFont font = wb.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            ws.getRow(row).getCell(column).setCellStyle(style);
        }
        FileOutputStream fo = new FileOutputStream(WriteExcel);
        wb.write(fo);
    }

    public static void main(String[] args) throws Throwable {
        ExcelFileUtil xl = new ExcelFileUtil("D:/MyFile.xlsx");
        int rc = xl.rowCount("Emp");
        System.out.println(rc);
        for(int i=1;i<=rc;i++)
        {
            String fname = xl.getCellData("Emp", i, 0);
            String mname = xl.getCellData("Emp", i, 1);
            String lname = xl.getCellData("Emp", i, 2);
            String eid = xl.getCellData("Emp", i, 3);
            System.out.println(fname+"   "+mname+"   "+lname+"   "+eid);
            //xl.setCellData("Emp", i, 4, "pass", "D:/Results.xlsx");
            xl.setCellData("Emp", i, 4, "Fail", "D:/Results.xlsx");
        }
    }
}
