package problem2.rule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		//System.out.println(message);
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
				    		new Thread(new HandlerFactory(message,obj)).start();
				    }
			    	else if (name.equals(obj.getName()) && jsonObject.get(name).toString().equals(obj.getValue()))
			    	{
						checkForExtendedPatterns(obj,message);
			    	}
			    }
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private static synchronized String operateP1Matchfile(String Message,int i)
	{
		if (i==0)
		{
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("resource/p1.match",false));
				bw.write(Message);
				bw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Message;
		}
		else
		{
			String ret="";
			try {
				BufferedReader br = new BufferedReader(new FileReader("resource/p1.match"));
				ret = br.readLine();
					br.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return ret;
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkForExtendedPatterns(ruleobj obj,String message)
	{
		String validate = obj.getSymbol();
		Pattern p1=Pattern.compile("(.*)more than (\\d+) in (\\d+) msec(.*)");
		Matcher m1 = p1.matcher(validate);
		if (m1.find())
		{
			if (!(new File("resource/p1.match").exists()))
			{
				JSONObject jsonObject1 = new JSONObject();
				jsonObject1.put("time", System.currentTimeMillis());
				jsonObject1.put("count", 0);
				operateP1Matchfile(jsonObject1.toJSONString(),0);
				return false;
			}
			else
			{
				try {
					
					JSONParser parser = new JSONParser();
					JSONObject jsonObject2 = (JSONObject) parser.parse(operateP1Matchfile(null,1));
					Long currentEpoch= System.currentTimeMillis();
					if ((currentEpoch-Long.parseLong(jsonObject2.get("time").toString()))<Integer.parseInt(m1.group(3)))
					{
						JSONObject jsonObjec3 = new JSONObject();
						int counter = Integer.parseInt(jsonObject2.get("count").toString())+1;
						jsonObjec3.put("time", Long.parseLong(jsonObject2.get("time").toString()));
						jsonObjec3.put("count", counter);
						operateP1Matchfile(jsonObjec3.toJSONString(),0);
						if (counter > Integer.parseInt(m1.group(2)))
						{
							new Thread(new HandlerFactory(message,obj)).start();
						}
					}
					else
					{
						JSONObject jsonObjec4 = new JSONObject();
						jsonObjec4.put("time", System.currentTimeMillis());
						jsonObjec4.put("count", 0);
						operateP1Matchfile(jsonObjec4.toJSONString(),0);
					}
				} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
				} catch (NullPointerException np){
					return false;
				}
				
			}
		}
		else
			return false;
		
		return false;
	}

}
