package Background;

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
				"ResultadoMovimentoGeral","ReportTemplates/movimento_geral.jrxml");
	}

	public static void generateApportionReport(){
		genericGenerateReport(new String[]{"Embarcação", "Data", "Média","% Carga","% Rateio", "Valor"}, 
				"ResultadoRateio","ReportTemplates/rateio.jrxml");
	}
	
	public static void generateTotalApportionReport(){
		genericGenerateReport(new String[]{"De","Até","Embarcação", "Bruto","Líquido","Líquido rateio","% Carga","% Rateio"}, 
				"ResultadoMovimentoRateioGeral","ReportTemplates/movimento_rateio_geral.jrxml");
	}
	
	public static void generateTotalSaleReport(){
		genericGenerateReport(new String[]{"De", "Até", "Vendedor","Data venda","Embarcação", "Total Pass.","Comissão"}, 
				"ResultadoVendasGeral","ReportTemplates/Movimento_vendas_geral.jrxml");
	}
	 
	public static void genericGenerateReport(String[] columnNames, String outFile, String reportTemplateFile){
		JasperReport jasperReport;
	    JasperPrint jasperPrint;
	    try
	    {
	      jasperReport = JasperCompileManager.compileReport(
	    		  reportTemplateFile);
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
