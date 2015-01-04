package Background;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Reporter {	
	
	public static void generateTotalMovReport(){
		genericGenerateReport(new String[]{"De","Até","Embarcação", "Bruto","Líquido","Comissão", "Descontos","Pass. Inteiros",
					"Pass. Free","Total Pass."}, 
				"ResultadoMovimentoGeral","ReportTemplates/movimento_geral");
	}

	public static void generateApportionReport(){
		genericGenerateReport(new String[]{"Embarcação", "Data", "Média","% Carga","% Rateio", "Valor"}, 
				"ResultadoRateio","ReportTemplates/rateio");
	}
	
	public static void generateTotalApportionReport(){
		genericGenerateReport(new String[]{"De","Até","Embarcação", "Bruto","Líquido","Líquido rateio","% Carga","% Rateio"}, 
				"ResultadoMovimentoRateioGeral","ReportTemplates/movimento_rateio_geral");
	}
	
	public static void generateTotalSaleReport(){
		genericGenerateReport(new String[]{"De", "Até", "Vendedor","Data venda","Embarcação", "Total Pass.","Comissão"}, 
				"ResultadoVendasGeral","ReportTemplates/Movimento_vendas_geral");
	}
	
	public static void generateEnterpriseSaleReport(){
		genericGenerateReport(new String[]{"De", "Até", "Empresa","Vendedor","Data venda","Embarcação", "Total Pass.","Comissão"}, 
				"ResultadoVendasEmpresa","ReportTemplates/Movimento_vendas");
	}
	
	public static void serializes(Object obj, String filePath){
		try{
			FileOutputStream file = new FileOutputStream(filePath);
			ObjectOutputStream outStream = new ObjectOutputStream(file);
			outStream.writeObject(obj);
			outStream.close();
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Object unSerializes(String filePath){
		try{
			FileInputStream file = new FileInputStream(filePath);
			ObjectInputStream inStream = new ObjectInputStream(file);
			Object obj = inStream.readObject();
			inStream.close();
			file.close();
			return obj;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void compileTemplate(String templateName){
		try{
			JasperReport jasperReport = JasperCompileManager.compileReport(
					templateName+".jrxml");
			serializes(jasperReport,templateName+".jasper");
		}catch(JRException e){
			e.printStackTrace();
		}
	}
	
	 
	public static void genericGenerateReport(String[] columnNames, String outFile, String reportTemplateFile){
		JasperReport jasperReport;
	    JasperPrint jasperPrint;
	    try
	    {
	      /*jasperReport = JasperCompileManager.compileReport(
	    		  reportTemplateFile);*/
	    	jasperReport = (JasperReport)unSerializes(reportTemplateFile+".jasper");
	      
	      jasperPrint = JasperFillManager.fillReport(
	          jasperReport, new HashMap(), getDataSource(columnNames,outFile+".csv"));
	      JasperExportManager.exportReportToPdfFile(
	          jasperPrint, outFile+".pdf");
	    }
	    catch (JRException e)
	    {
	      e.printStackTrace();
	    }
	}
	 
	private static JRCsvDataSource getDataSource(String[] columnNames, String csvFile) throws JRException {
	    JRCsvDataSource ds = new JRCsvDataSource(JRLoader.getLocationInputStream(csvFile));
	    ds.setFieldDelimiter('\t');
	    ds.setRecordDelimiter("\n");
	    ds.setColumnNames(columnNames);
	    return ds;
	}
}
