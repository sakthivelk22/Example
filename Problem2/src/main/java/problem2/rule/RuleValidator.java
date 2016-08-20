package problem2.rule;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import problem2.notification.handler.HandlerFactory;
import problem2.util.*;

public class RuleValidator implements Runnable {
	Map.Entry<Integer, ruleobj> entry;
	String message;
	
	public RuleValidator(Map.Entry<Integer, ruleobj> entry,String message)
	{
		this.entry=entry;
		this.message=message;
	}

	public void run()
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(message);
		
			@SuppressWarnings("unchecked")
			Set<String> Keys = jsonObject.keySet();
			Iterator<String> i = Keys.iterator();
			while (i.hasNext())
			{
			    
			    ruleobj obj = entry.getValue();
			    String name= i.next();
			    if (obj.getName().equals(name))
			    {
			    	if (Utility.validator(jsonObject.get(name).toString(),obj.getSymbol(),obj.getValue()))
			    	{
			    		//Start new thread for Notification
			    		//System.out.println("Its true");
			    		new Thread(new HandlerFactory(message,obj)).start();
			    	}
			    }
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
