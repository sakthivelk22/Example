package problem2.rule;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import problem2.notification.handler.HandlerFactory;
import problem2.util.ruleobj;

public class RuleValidator implements Runnable {
	Map.Entry<Integer, ruleobj> entry;
	String message;
	
	public RuleValidator(Map.Entry<Integer, ruleobj> entry,String message)
	{
		this.entry=entry;
		this.message=message;
	}

	@SuppressWarnings("restriction")
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
				@SuppressWarnings("restriction")
				ScriptEngineManager mgr = new ScriptEngineManager();
			    @SuppressWarnings("restriction")
				ScriptEngine engine = mgr.getEngineByName("JavaScript");
			    
			    ruleobj obj = entry.getValue();
			    String name= i.next();
			    if (obj.getName().equals(name))
			    {
			    	String evals= jsonObject.get(name)+obj.getSymbol()+obj.getValue();
			    	if ((Boolean) engine.eval(evals))
			    	{
			    		//Start new thread for Notification
			    		new Thread(new HandlerFactory(message,obj)).start();
			    	}
			    }
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
