package Background;

import java.sql.Timestamp;

public class ConversionUtils {
	public static String getDatePortuguese(Timestamp t, boolean withHour){
		String ret = t.toString();
		String year = ret.substring(0,ret.indexOf('-'));
		ret = ret.substring(ret.indexOf('-') + 1);
		String month = ret.substring(0,ret.indexOf('-'));
		ret = ret.substring(ret.indexOf('-') + 1);
		String day = ret.substring(0,ret.indexOf(' '));
		if(withHour) ret = ret.substring(ret.indexOf(' ') + 1,ret.lastIndexOf(':')) + " ";
		else ret = "";
		return ret + day + "/" + month + "/" + year;
	}
}
