package problem2.notification.handler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import problem2.util.ruleobj;

public class HandlerFactory implements Runnable {
	private String Message;
	private ruleobj rule;
	public HandlerFactory(String Message,ruleobj obj)
	{
		this.Message=Message;
		this.rule=obj;
	}
	
	@SuppressWarnings("unchecked")
	public void run()
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(Message);
		
			jsonObject.put("alert", rule.toString());
			// Pass the JSON message to Handlers as necessary //
			switch(rule.getHandlerName())
			{
				case "Handler1":
					//System.out.println(jsonObject.toJSONString());
					Handle1 handle = new Handle1(jsonObject.toJSONString());
					handle.generateNotificationMessage();
					break;			
				default: 
					System.out.println(jsonObject.toJSONString());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
