package com.cardpay.pccredit.manager.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.manager.dao.ManagerPerformmanceDao;
import com.cardpay.pccredit.manager.form.BankListForm;
import com.cardpay.pccredit.manager.form.DeptMemberForm;
import com.cardpay.pccredit.manager.form.ManagerPerformmanceForm;
import com.cardpay.pccredit.manager.model.ManagerPerformmance;
import com.cardpay.pccredit.manager.model.ManagerPerformmanceSum;
import com.cardpay.pccredit.manager.model.ManagerSalaryForm;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.id.IDGenerator;

@Service
public class ManagerPerformmanceService {

	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	ManagerPerformmanceDao managerPerformmanceDao;
	public void  insertManager(ManagerPerformmance ManagerPerformmance){
		managerPerformmanceDao.insertManager(ManagerPerformmance);
	}
	public void insertmanagerPerformmance(ManagerPerformmance managerPerformmance) {
		
		
		commonDao.insertObject(managerPerformmance);
		
	}

	public List<DeptMemberForm> findMumberByDeptId(String id) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.findMumberByDeptId(id);
	}

	public List<ManagerPerformmance> finManagerPerformmanceById(String managerId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.finManagerPerformmanceById(managerId);
	}
	
	public ManagerPerformmance finManagerPerformmanceById1(String managerId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.finManagerPerformmanceById1(managerId);
	}

	public ManagerPerformmance finManagerPerformmanceSumById(String managerId,String changedate) {
		// TODO Auto-generated method stub
		
		return managerPerformmanceDao.finManagerPerformmanceSumById(managerId,changedate);
	}

	public void insertManagerPerformmanceSum(ManagerPerformmanceSum managerPerformmance) {
		
		
		commonDao.insertObject(managerPerformmance);
	}
//将当天数量累加到总进度
	public void updateManagerPerformmanceSum(ManagerPerformmanceSum managerPerformmance) {
		
		
		managerPerformmanceDao.updateManagerPerformmanceSum(managerPerformmance);
	}

	public void updatemanagerPerformmance(ManagerPerformmance managerPerformmance) {
		
		commonDao.updateObject(managerPerformmance);
	}

	//查询总进度
	public ManagerPerformmanceForm findSumPerformmanceById(String Id,String startDate,String endDate){
		
		return managerPerformmanceDao.findSumPerformmanceById(Id,startDate,endDate);
	
}
	//查询指定机构总进度
	public ManagerPerformmanceForm findDeptSumPerformmanceById(String id,String satrtDate,String endDate) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.findDeptSumPerformmanceById(id,satrtDate,endDate);
	}
	//查找所有支行
	public List<BankListForm> findALlbank() {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.findALlbank();
	}

	public ManagerPerformmanceForm findALLDeptSumPerformmanceById(String satrtDate,String endDate) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.findALLDeptSumPerformmanceById(satrtDate,endDate);
	}

	public void updateManagerPerformmanceSumInfor(ManagerPerformmance managerPerformmanceSum) {
		
		managerPerformmanceDao.updateManagerPerformmanceSumInfor(managerPerformmanceSum);
	}

	
	//业务进度入录 数据自动读取 查询方法
	public int queryRefuse(String userId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.queryRefuse(userId);
	}

	public int queryApply(String userId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.queryApply(userId);
	}

	public int queryAudit(String userId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.queryAudit(userId);
	}

	public int queryWill(String userId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.queryWill(userId);
	}

	public int queryPass(String userId) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.queryPass(userId);
	}


	//导出excel
	public void getExportWageData(List<ManagerPerformmanceForm> gxperformList, HttpServletResponse response) {
		
		
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        HSSFCell cellTmp = row.createCell((short) 0);
        String titles="阳泉农村信用社联社客户经理业绩进度";
       /* if(satrtDate!=""&&endDate!=""){
        	titles="阳泉农村信用社联社"+satrtDate+"日至"+endDate+"日客户经理业绩进度";
        }*/
        cellTmp.setCellValue(titles);  //设置表格标题
		// 设置标题字体
		HSSFFont font16 = wb.createFont();
		font16.setFontHeightInPoints((short) 20);
		font16.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font16.setFontName("华文楷体");
		
		// 设置标题字体
		HSSFFont font1 = wb.createFont();
		font1.setFontHeightInPoints((short) 12);
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font1.setFontName("宋体");
		
		// 设置单元格居中
		HSSFCellStyle styleCenter = wb.createCellStyle();
		styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleCenter.setFont(font16);
		
		// 设置居右
		HSSFCellStyle styleFirst = wb.createCellStyle();
		styleFirst.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleFirst.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		styleFirst.setFont(font1);
		// 设置居右
		HSSFCellStyle styleSecond = wb.createCellStyle();
		styleFirst.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleFirst.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleFirst.setFont(font1);
		
		// 合并单元格
		CellRangeAddress region = new CellRangeAddress(0, 0, 0,14);
		sheet.addMergedRegion(region);
		cellTmp.setCellStyle(styleCenter);
		
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        // 创建一个居中格式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        
        // 设置第二行 制表日期
        row = sheet.createRow((int) 1);
        HSSFCell tmp = row.createCell((short) 11);
        tmp.setCellValue("制表日期："+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        CellRangeAddress reg = new CellRangeAddress(1, 1, 12,14);
        sheet.addMergedRegion(reg);
        tmp.setCellStyle(styleSecond);
        
        // excel 正文内容
        row = sheet.createRow((int) 2);
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("区域");  
        cell.setCellStyle(style);
        	        
        cell = row.createCell((short) 1);  
        cell.setCellValue("团队");  
        cell.setCellStyle(style);  
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("客户经理");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("拜访数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("申请数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("申请拒绝数");  
        cell.setCellStyle(style);
        sheet.setColumnWidth(4, 10*256);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("征信数");  
        cell.setCellStyle(style);
//        sheet.setColumnWidth(5, 25*256);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("征信拒绝数");  
        cell.setCellStyle(style);
        sheet.setColumnWidth(6, 10*256);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("实调数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("报告数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("内审数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("上会数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("通过数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("签约数");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("放款数");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 15);  
        cell.setCellValue("放款金额");  
        cell.setCellStyle(style);

        for(int i=0;i<gxperformList.size();i++){
        	row = sheet.createRow((int) i + 3);
        	ManagerPerformmanceForm gxperform = gxperformList.get(i);
        	row.createCell((short) 0).setCellValue((String) gxperform.getOrdteam());            //管辖行
        	row.createCell((short) 1).setCellValue((String) gxperform.getTeam()); 
        	row.createCell((short) 2).setCellValue((String) gxperform.getName());//姓名                       
        	row.createCell((short) 3).setCellValue(gxperform.getVisitcount()+"("+gxperform.getVisitcount_s()+")");     	//拜访数       
        	row.createCell((short) 4).setCellValue(gxperform.getApplycount()+"("+gxperform.getApplycount_s()+")");      //申请数
        	row.createCell((short) 5).setCellValue(gxperform.getApplyrefuse()+"("+gxperform.getApplyrefuse_s()+")");    //申请拒绝数 
        	row.createCell((short) 6).setCellValue(gxperform.getCreditcount()+"("+gxperform.getCreditcount_s()+")");    //征信数       
        	row.createCell((short) 7).setCellValue(gxperform.getCreditrefuse()+"("+gxperform.getCreditrefuse_s()+")");  //征信拒绝数     			 
        	row.createCell((short) 8).setCellValue(gxperform.getRealycount()+"("+gxperform.getRealycount_s()+")");      //实调数   
        	row.createCell((short) 9).setCellValue(gxperform.getReportcount()+"("+gxperform.getReportcount_s()+")");  //报告数    							     ）  
        	row.createCell((short) 10).setCellValue(gxperform.getInternalcount()+"("+gxperform.getInternalcount_s()+")");  //内审数
        	row.createCell((short) 11).setCellValue(gxperform.getMeetingcout()+"("+gxperform.getMeetingcout_s()+")");  	//上会数						  
        	row.createCell((short) 12).setCellValue(gxperform.getPasscount()+"("+gxperform.getPasscount_s()+")");       //通过数          
        	row.createCell((short) 13).setCellValue(gxperform.getSigncount()+"("+gxperform.getSigncount_s()+")");       //签约数
        	row.createCell((short) 14).setCellValue(gxperform.getGivemoneycount()+"("+gxperform.getGivemoneycount_s()+")");  //放款数									          
        	row.createCell((short) 15).setCellValue(gxperform.getMoney1());  //放款金额								          
        }
        try {
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=GBK");
        response.setHeader("Content-Disposition", "attachment;filename="
   + new String("阳泉农村信用社联社客户经理业绩进度表.xls".getBytes(), "iso-8859-1"));
        OutputStream out = response.getOutputStream();  
		        wb.write(out);     
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//指定支行当天进度
	public ManagerPerformmance findDeptTodayPerformmanceById(String id) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.findDeptTodayPerformmanceById(id);
	}
    //总行当天进度
	public ManagerPerformmance findDeptTodaySumPerformmanceById() {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.findDeptTodaySumPerformmanceById();
	}
	//根据客户经理id和日期查询进度
	public ManagerPerformmance finManagerPerformmanceByIdAndDate(String managerId, Date reportDate) {
		// TODO Auto-generated method stub
		return managerPerformmanceDao.finManagerPerformmanceByIdAndDate(managerId,reportDate);
	}

	public List<ManagerPerformmance> findSumPerformmanceRanking(String orgId, String satrtDate, String endDate,
			String classes) {
		
		List<ManagerPerformmance> ls= managerPerformmanceDao.findSumPerformmanceRanking(orgId,satrtDate,endDate,classes);
		for (int i = 0; i < ls.size(); i++) {
		ls.get(i).setManagername(managerPerformmanceDao.findmanagerName(ls.get(i).getManager_id()));
		}
		return ls;
	}
	
	//导出排名excel
		public void getRankingExport(List<ManagerPerformmance> gxperformList, HttpServletResponse response,String satrtDate,String endDate) {
			
			
			// 第一步，创建一个webbook，对应一个Excel文件  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet = wb.createSheet("sheet1");  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        HSSFCell cellTmp = row.createCell((short) 0);
	        String titles="济南农村商业银行客户经理业绩进度排名";
	        if(satrtDate!=""&&endDate!=""){
	        	titles="济南农村商业银行"+satrtDate+"日至"+endDate+"日客户经理业绩进度排名";
	        }
	        cellTmp.setCellValue(titles);  //设置表格标题
			// 设置标题字体
			HSSFFont font16 = wb.createFont();
			font16.setFontHeightInPoints((short) 20);
			font16.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font16.setFontName("华文楷体");
			
			// 设置标题字体
			HSSFFont font1 = wb.createFont();
			font1.setFontHeightInPoints((short) 12);
			font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font1.setFontName("宋体");
			
			// 设置单元格居中
			HSSFCellStyle styleCenter = wb.createCellStyle();
			styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleCenter.setFont(font16);
			
			// 设置居右
			HSSFCellStyle styleFirst = wb.createCellStyle();
			styleFirst.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleFirst.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleFirst.setFont(font1);
			// 设置居右
			HSSFCellStyle styleSecond = wb.createCellStyle();
			styleFirst.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleFirst.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			styleFirst.setFont(font1);
			
			// 合并单元格
			CellRangeAddress region = new CellRangeAddress(0, 0, 0,14);
			sheet.addMergedRegion(region);
			cellTmp.setCellStyle(styleCenter);
			
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        // 创建一个居中格式
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setWrapText(true);
	        
	        // 设置第二行 制表日期
	        row = sheet.createRow((int) 1);
	        HSSFCell tmp = row.createCell((short) 12);
	        tmp.setCellValue("制表日期："+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	        CellRangeAddress reg = new CellRangeAddress(1, 1, 12,14);
	        sheet.addMergedRegion(reg);
	        tmp.setCellStyle(styleSecond);
	        
	        // excel 正文内容
	        row = sheet.createRow((int) 2);
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("排名");  
	        cell.setCellStyle(style);
	        	        
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("客户经理");  
	        cell.setCellStyle(style);  
	        
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("拜访数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("申请数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 4);  
	        cell.setCellValue("申请拒绝数");  
	        cell.setCellStyle(style);
	        sheet.setColumnWidth(4, 10*256);
	        
	        cell = row.createCell((short) 5);  
	        cell.setCellValue("征信数");  
	        cell.setCellStyle(style);
//	        sheet.setColumnWidth(5, 25*256);
	        
	        cell = row.createCell((short) 6);  
	        cell.setCellValue("征信拒绝数");  
	        cell.setCellStyle(style);
	        sheet.setColumnWidth(6, 10*256);
	        
	        cell = row.createCell((short) 7);  
	        cell.setCellValue("实调数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 8);  
	        cell.setCellValue("报告数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 9);  
	        cell.setCellValue("内审数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 10);  
	        cell.setCellValue("上会数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 11);  
	        cell.setCellValue("通过数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 12);  
	        cell.setCellValue("签约数");  
	        cell.setCellStyle(style);
	        
	        cell = row.createCell((short) 13);  
	        cell.setCellValue("放款数");  
	        cell.setCellStyle(style);

	        cell = row.createCell((short) 14);  
	        cell.setCellValue("放款金额");  
	        cell.setCellStyle(style);

	        for(int i=0;i<gxperformList.size();i++){
	        	row = sheet.createRow((int) i + 3);
	        	ManagerPerformmance gxperform = gxperformList.get(i);
	        	row.createCell((short) 0).setCellValue(i+1);         //姓名                       
	        	row.createCell((short) 1).setCellValue(gxperform.getManagername());            //管辖行
	        	row.createCell((short) 2).setCellValue(gxperform.getVisitcount());     	//拜访数       
	        	row.createCell((short) 3).setCellValue(gxperform.getApplycount());      //申请数
	        	row.createCell((short) 4).setCellValue(gxperform.getApplyrefuse());    //申请拒绝数 
	        	row.createCell((short) 5).setCellValue(gxperform.getCreditcount());    //征信数       
	        	row.createCell((short) 6).setCellValue(gxperform.getCreditrefuse());  //征信拒绝数     			 
	        	row.createCell((short) 7).setCellValue(gxperform.getRealycount());      //实调数   
	        	row.createCell((short) 8).setCellValue(gxperform.getReportcount());  //报告数    							     ）  
	        	row.createCell((short) 9).setCellValue(gxperform.getInternalcount());  //内审数
	        	row.createCell((short) 10).setCellValue(gxperform.getMeetingcout());  	//上会数						  
	        	row.createCell((short) 11).setCellValue(gxperform.getPasscount());       //通过数          
	        	row.createCell((short) 12).setCellValue(gxperform.getSigncount());       //签约数
	        	row.createCell((short) 13).setCellValue(gxperform.getGivemoneycount());  //放款数									          
	        	row.createCell((short) 14).setCellValue(gxperform.getMoney());  //放款金额								          
	        }
	        try {
	        response.setHeader("Connection", "close");
	        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=GBK");
	        response.setHeader("Content-Disposition", "attachment;filename="
	        + new String("济南农村商业银行客户经理业务进度排名表.xls".getBytes(), "iso-8859-1"));
	        OutputStream out = response.getOutputStream();  
	        wb.write(out);
	     
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		public void insertNewManager(){
			List<ManagerPerformmanceForm>result=managerPerformmanceDao.selectNewManager();
			for(int i=0;i<result.size();i++){
				ManagerPerformmanceForm from= managerPerformmanceDao.selectManagerYj(result.get(i).getManager_id());
				from.setManager_id(result.get(i).getManager_id());
				from.setId(IDGenerator.generateID());
				from.setTeam(result.get(i).getTeam());
				from.setOrdteam(result.get(i).getOrdteam());
				managerPerformmanceDao.insertALLManager(from);
			}
		}
		public void updateManagerDateByUser(ManagerPerformmance from){
			managerPerformmanceDao.updateManagerDateByUser(from);
		}
		public List<ManagerPerformmanceForm>selectAllManegerTeam(){
			return managerPerformmanceDao.selectAllManegerTeam();
			
		}
		public List<ManagerPerformmanceForm>selectAllManegerYj(ManagerPerformmanceForm from){
			return managerPerformmanceDao.selectAllManegerYj(from);
		}
		public ManagerPerformmanceForm selectAllTeamYj(ManagerPerformmanceForm from){
			return managerPerformmanceDao.selectAllTeamYj(from);
		}
public List<ManagerPerformmanceForm>selectManagerTeam(@Param(value="id")String id){
			return managerPerformmanceDao.selectManagerTeam(id);
		}
		public List<ManagerPerformmanceForm>selectAllManagerByTeam(ManagerPerformmanceForm ManagerPerformmanceForm){
			return managerPerformmanceDao.selectAllManagerByTeam(ManagerPerformmanceForm);
		}
		public List<ManagerPerformmanceForm>selectAllManagerByOrgTeam(ManagerPerformmanceForm ManagerPerformmanceForm){
			return managerPerformmanceDao.selectAllManagerByOrgTeam(ManagerPerformmanceForm);
		}
}



