package problem2.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	static public boolean validator(String lhs,String validate,String rhs)
	{
		try{
		     switch (validate) {
	         	case "isequalto":
	         		return lhs.equals(rhs);
	         	case "isnotequalto":
	         		return !lhs.equals(rhs);
	         	case "islessthan":
	         		return lhs.compareTo(rhs)<0?true:false; 
	         	case "isgreaterthen":
	         		return lhs.compareTo(rhs)<0?false:true;
	         	case "islessthanequalto":
	         		return lhs.compareTo(rhs)<=0?true:false;
	         	case "isgreaterthenequalto":
	         		return lhs.compareTo(rhs)<=0?false:true;
	         	case "==":
	         		return Double.parseDouble(lhs)==Double.parseDouble(rhs);
	         	case "!=":
	         		return Double.parseDouble(lhs)!=Double.parseDouble(rhs);
	         	case "<=":
	         		return Double.parseDouble(lhs)<=Double.parseDouble(rhs);
	         	case ">=":
	         		return Double.parseDouble(lhs)>=Double.parseDouble(rhs);
	         	case "<":
	         		return Double.parseDouble(lhs)<Double.parseDouble(rhs);
	         	case ">":
	         		return Double.parseDouble(lhs)>Double.parseDouble(rhs);
	         	default:
	         		return false;
		     }
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
