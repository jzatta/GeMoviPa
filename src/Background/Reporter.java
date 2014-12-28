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
	 
	 public static void generateReport(){
		 JasperReport jasperReport;
		    JasperPrint jasperPrint;
		    try
		    {
		      jasperReport = JasperCompileManager.compileReport(
		          "ReportTemplates/report7.jrxml");
		      Map parameters = new HashMap();
			  parameters.put("ReportTitle", "Relatório Rateio");
			  parameters.put("DataFile", "ResultadoRateio.csv - CSV fonte");
		      jasperPrint = JasperFillManager.fillReport(
		          jasperReport, parameters, getDataSource());
		      JasperExportManager.exportReportToPdfFile(
		          jasperPrint, "ReportTemplates/simple_report.pdf");
		    }
		    catch (JRException e)
		    {
		      e.printStackTrace();
		    }
	 }
	 
	 private static JRCsvDataSource getDataSource() throws JRException
	  {
	    String[] columnNames = new String[]{"Embarcação", "Data", "Média","% Carga","% Rateio", "Valor"};
	    JRCsvDataSource ds = new JRCsvDataSource(JRLoader.getLocationInputStream("ResultadoRateio.csv"));
	    ds.setFieldDelimiter('\t');
	    ds.setRecordDelimiter("\n");
	    ds.setColumnNames(columnNames);
	    return ds;
	  }
}
