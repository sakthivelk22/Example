package problem2.util;

public class ruleobj {
	
	private String name;
	private String symbol;
	private String value;
	private String handlerName;
	public ruleobj(String name,String symbol,String value,String handlerName)
	{
		this.name=name;
		this.symbol=symbol;
		this.value=value;
		this.handlerName=handlerName;
	}
	public String toString()
	{
		return name+":"+symbol+":"+value+":"+handlerName;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getValue() {
		return value;
	}
	public String getHandlerName() {
		return handlerName;
	}
	
}
