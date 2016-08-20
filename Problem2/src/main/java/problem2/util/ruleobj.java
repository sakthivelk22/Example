package problem2.util;

import org.json.simple.JSONObject;

public class ruleobj {
	
	private String name;
	private String symbol;
	private String value;
	private String handlerName;
	private String id;
	public ruleobj(String name,String symbol,String value,String handlerName,String id)
	{
		this.name=name;
		this.symbol=symbol;
		this.value=value;
		this.handlerName=handlerName;
		this.id=id;
	}
	public String toString()
	{
		JSONObject obj= new JSONObject();
		obj.put("name", name);
		obj.put("symbol", symbol);
		obj.put("value", value);
		obj.put("handlerName", handlerName);
		obj.put("id", id);
		
		return obj.toJSONString();
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
	public String iD() {
		return id;
	}
	
}
