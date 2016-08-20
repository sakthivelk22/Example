package problem2.rule;

import problem2.notification.handler.*;
import problem2.util.ruleobj;

import java.io.*;
import org.json.simple.parser.*;
import java.util.*;
import java.util.Map.Entry;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;
import org.json.simple.JSONObject;

public class RuleRunner implements Runnable  {
	private Handler handle;
	private String message;
	private String ruleConfig="resource/rule.config";
	private Map<Integer,ruleobj> a_map= new HashMap<Integer,ruleobj>();
	
	public RuleRunner(String configFile,String message) throws ParseException
	{
		this.message=message;
		this.ruleConfig=configFile;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(ruleConfig));
			String line = null;
			int key=1;
			while ((line = br.readLine()) != null) {
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(line);
				ruleobj obj = new ruleobj((String) jsonObject.get("name"),(String) jsonObject.get("symbol")
								,(String) jsonObject.get("value"),(String) jsonObject.get("handler"),
								(String)jsonObject.get("id"));
				a_map.put(key, obj);
				key++;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		Iterator<Entry<Integer, ruleobj>> i = a_map.entrySet().iterator();
		Map.Entry<Integer, ruleobj> entry;
		while (i.hasNext())
		{
			entry = i.next();
			new Thread(new RuleValidator(entry,message)).start();
		}
	}
}
