package com.hl.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
 





import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class ExcelUtils {
	/*
	public static List<ExcelModel> readXls(String path) throws IOException {

        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ExcelModel xlsDto = null;
        List<ExcelModel> list = new ArrayList<ExcelModel>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                xlsDto = new ExcelModel();
                // 循环列Cell
                // 0学号 1姓名 2学院 3课程名 4 成绩
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                HSSFCell name = hssfRow.getCell(2);
                if (name == null) {
                    continue;
                }
                xlsDto.setName(getValue(name));
                
                HSSFCell id = hssfRow.getCell(0);
                if (id == null) {
                    continue;
                }
                xlsDto.setId(Integer.parseInt(getValue(id)));
                
                HSSFCell embassy = hssfRow.getCell(1);
                if (embassy == null) {
                    continue;
                }
                xlsDto.setEmbassy(getValue(embassy));
                HSSFCell birthdate = hssfRow.getCell(4);
                if (birthdate == null) {
                	xlsDto.setBirthDate("");
                }else{
                	xlsDto.setBirthDate(getValue(birthdate));
                }
                
                HSSFCell waijiaozhixian = hssfRow.getCell(7);
                if (waijiaozhixian == null) {
                	xlsDto.setWaijiaozhixian("");
                }else{
                	xlsDto.setWaijiaozhixian(getValue(waijiaozhixian));
                }
                
                HSSFCell yuzhong = hssfRow.getCell(6);
                if (yuzhong == null) {
                	xlsDto.setYuzhong("");
                }else{
                	xlsDto.setYuzhong(getValue(yuzhong));
                }
                
                
                HSSFCell xingzhengjibie = hssfRow.getCell(13);
                if (xingzhengjibie == null) {
                	xlsDto.setXingzhengjibie("");
                }else{
                	xlsDto.setXingzhengjibie(getValue(xingzhengjibie));
                }
                
                
                HSSFCell tuijiandanwei = hssfRow.getCell(19);
                if (tuijiandanwei == null) {
                	xlsDto.setTuijiandanwei("");
                }else{
                	xlsDto.setTuijiandanwei(getValue(tuijiandanwei));
                }
                
                
                HSSFCell gongzuodanwei = hssfRow.getCell(20);
                if (gongzuodanwei == null) {
                	xlsDto.setGongzuodanwei("");
                }else{
                	xlsDto.setGongzuodanwei(getValue(gongzuodanwei));
                }
                
                
                HSSFCell furenriqi = hssfRow.getCell(22);
                if (furenriqi == null) {
                	xlsDto.setFurenriqi("");
                }else{
                	xlsDto.setFurenriqi(getValue(furenriqi));
                }
                
                
                
                list.add(xlsDto);
            }
        }
        return list;
    }
	
	public static ExcelModel readXls(String path,String pname,String pembassy) throws IOException {

        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ExcelModel xlsDto =  new ExcelModel();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                
                HSSFCell name = hssfRow.getCell(2);
                if (name == null) {
                    continue;
                }
                String str_name=getValue(name);

                HSSFCell embassy = hssfRow.getCell(1);
                if (embassy == null) {
                    continue;
                }
                String str_embassy=getValue(embassy);
                
                HSSFCell id = hssfRow.getCell(0);
                if (id == null) {
                    continue;
                }
                xlsDto.setId(Integer.parseInt(getValue(id)));
                
            	if(str_name.equals(pname) && pembassy.equals(str_embassy)){

                    xlsDto.setName(str_name);
                    xlsDto.setEmbassy(str_embassy);
                    HSSFCell birthdate = hssfRow.getCell(4);
                    if (birthdate == null) {
                    	xlsDto.setBirthDate("");
                    }else{
                    	xlsDto.setBirthDate(getValue(birthdate));
                    }
                    
                    HSSFCell waijiaozhixian = hssfRow.getCell(7);
                    if (waijiaozhixian == null) {
                    	xlsDto.setWaijiaozhixian("");
                    }else{
                    	xlsDto.setWaijiaozhixian(getValue(waijiaozhixian));
                    }
                    
                    
                    HSSFCell yuzhong = hssfRow.getCell(6);
                    if (yuzhong == null) {
                    	xlsDto.setYuzhong("");
                    }else{
                    	xlsDto.setYuzhong(getValue(yuzhong));
                    }
                    
                    
                    HSSFCell xingzhengjibie = hssfRow.getCell(13);
                    if (xingzhengjibie == null) {
                    	xlsDto.setXingzhengjibie("");
                    }else{
                    	xlsDto.setXingzhengjibie(getValue(xingzhengjibie));
                    }
                    
                    
                    HSSFCell tuijiandanwei = hssfRow.getCell(19);
                    if (tuijiandanwei == null) {
                    	xlsDto.setTuijiandanwei("");
                    }else{
                    	xlsDto.setTuijiandanwei(getValue(tuijiandanwei));
                    }
                    
                    
                    HSSFCell gongzuodanwei = hssfRow.getCell(20);
                    if (gongzuodanwei == null) {
                    	xlsDto.setGongzuodanwei("");
                    }else{
                    	xlsDto.setGongzuodanwei(getValue(gongzuodanwei));
                    }
                    
                    
                    HSSFCell furenriqi = hssfRow.getCell(22);
                    if (furenriqi == null) {
                    	xlsDto.setFurenriqi("");
                    }else{
                    	xlsDto.setFurenriqi(getValue(furenriqi));
                    }
                    
                    break;
                }
            }
            break;
        }
        return xlsDto;
    }
	
	
	public static ExcelModel readXls(String path,String pname) throws IOException {

        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ExcelModel xlsDto =  new ExcelModel();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                
                HSSFCell name = hssfRow.getCell(2);
                if (name == null) {
                    continue;
                }
                String str_name=getValue(name);

                HSSFCell embassy = hssfRow.getCell(1);
                if (embassy == null) {
                    continue;
                }
                String str_embassy=getValue(embassy);
                
                HSSFCell id = hssfRow.getCell(0);
                if (id == null) {
                    continue;
                }
                
                
            	if(str_name.equals(pname)){
            		xlsDto.setId(Integer.parseInt(getValue(id)));
                    xlsDto.setName(str_name);
                    xlsDto.setEmbassy(str_embassy);
                    HSSFCell birthdate = hssfRow.getCell(4);
                    if (birthdate == null) {
                    	xlsDto.setBirthDate("");
                    }else{
                    	xlsDto.setBirthDate(getValue(birthdate));
                    }
                    
                    HSSFCell waijiaozhixian = hssfRow.getCell(7);
                    if (waijiaozhixian == null) {
                    	xlsDto.setWaijiaozhixian("");
                    }else{
                    	xlsDto.setWaijiaozhixian(getValue(waijiaozhixian));
                    }
                    
                    
                    HSSFCell yuzhong = hssfRow.getCell(6);
                    if (yuzhong == null) {
                    	xlsDto.setYuzhong("");
                    }else{
                    	xlsDto.setYuzhong(getValue(yuzhong));
                    }
                    
                    
                    HSSFCell xingzhengjibie = hssfRow.getCell(13);
                    if (xingzhengjibie == null) {
                    	xlsDto.setXingzhengjibie("");
                    }else{
                    	xlsDto.setXingzhengjibie(getValue(xingzhengjibie));
                    }
                    
                    
                    HSSFCell tuijiandanwei = hssfRow.getCell(19);
                    if (tuijiandanwei == null) {
                    	xlsDto.setTuijiandanwei("");
                    }else{
                    	xlsDto.setTuijiandanwei(getValue(tuijiandanwei));
                    }
                    
                    
                    HSSFCell gongzuodanwei = hssfRow.getCell(20);
                    if (gongzuodanwei == null) {
                    	xlsDto.setGongzuodanwei("");
                    }else{
                    	xlsDto.setGongzuodanwei(getValue(gongzuodanwei));
                    }
                    
                    
                    HSSFCell furenriqi = hssfRow.getCell(22);
                    if (furenriqi == null) {
                    	xlsDto.setFurenriqi("");
                    }else{
                    	xlsDto.setFurenriqi(getValue(furenriqi));
                    }
                    
                    break;
                }
            }
            break;
        }
        return xlsDto;
    }
	
	public static void removeXlsRow(String path,String pname,String pembassy) throws IOException {

        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                
                HSSFCell name = hssfRow.getCell(2);
                if (name == null) {
                    continue;
                }
                String str_name=getValue(name);

                HSSFCell embassy = hssfRow.getCell(1);
                if (embassy == null) {
                    continue;
                }
                String str_embassy=getValue(embassy);
            	if(str_name.equals(pname) && pembassy.equals(str_embassy)){
            		removeRow(hssfSheet,rowNum);
                }
            }
            break;
        }
        FileOutputStream os = new FileOutputStream(path);
        hssfWorkbook.write(os);
        is.close();  
        os.close();
    }
 */
    /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
    	
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        	DecimalFormat df = new DecimalFormat("0");
        	return df.format(hssfCell.getNumericCellValue());
            // 返回数值类型的值
            //return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
    public static void removeRow(HSSFSheet sheet, int rowIndex) {  
        int lastRowNum=sheet.getLastRowNum();  
        if(rowIndex>=0&&rowIndex<lastRowNum)  
            sheet.shiftRows(rowIndex+1,lastRowNum,-1);//将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行  
        if(rowIndex==lastRowNum){  
            HSSFRow removingRow=sheet.getRow(rowIndex);  
            if(removingRow!=null)  
                sheet.removeRow(removingRow);  
        }  
    }  
    
    /*
    public static void updateCells(String path,List<Personprofile> profiles) throws IOException{
    	InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

        
        for (Personprofile profile : profiles) {

        	boolean isExists=false;
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                
                HSSFCell name = hssfRow.getCell(2);
                if (name == null) {
                    continue;
                }
                String str_name=getValue(name);

                HSSFCell embassy = hssfRow.getCell(1);
                if (embassy == null) {
                    continue;
                }
                String str_embassy=getValue(embassy);
                
            	if(str_name.equals(profile.getName()) && str_embassy.equals(profile.getEmbassy())){
            		HSSFCell idcell=hssfRow.getCell(0);
            		idcell.setCellValue(profile.getId());
            		isExists=true;
                	break;
                }	
            }
            if(!isExists){
            	HSSFRow addrow=hssfSheet.createRow(hssfSheet.getLastRowNum()+1);
            	HSSFCell  cell = addrow.createCell(0);
                cell.setCellValue(profile.getId());
                HSSFCell  cell1 = addrow.createCell(1);
                cell1.setCellValue(profile.getEmbassy());
                HSSFCell  cell2 = addrow.createCell(2);
                cell2.setCellValue(profile.getName());
            }
		}
        FileOutputStream os = new FileOutputStream(path);
        hssfWorkbook.write(os);
        is.close();  
        os.close();
    }
    */
}
