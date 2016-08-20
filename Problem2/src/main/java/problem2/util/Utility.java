package problem2.util;

public class Utility {
	public static boolean validator(String lhs,String validate,String rhs)
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
