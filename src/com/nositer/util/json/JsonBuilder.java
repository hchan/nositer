package com.nositer.util.json;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;


public class JsonBuilder {

	

	public static void printJSON(JspWriter out, List list) throws Exception {
		out.print("{ records: ");
		out.println(toJson(list));
		out.print("}");
	}

	/*
	public static void printJSON(JspWriter out, ArrayList list, Transformer transformer) throws Exception {

		out.print("{ records: ");
		
			out.print("[\n");
		
		int count = 0;
		for (SpHashMap spHashMap : spList) {
			count++;


			if (count > 1) {
				out.print("\t,\n");
			}
			out.print("\t{\n");
			int columnCount = 0;
			for (Object obj: spHashMap.keySet()) {
				String columnName = (String)obj;
				columnCount++;
				if (columnCount > 1) {
					out.print(",\n");
				}
				String valStr = "";

				if (spHashMap.get(columnName) != null) {
					valStr = spHashMap.get(columnName).toString();
				}

				if (transformer != null) {
					NameValuePair nameValuePair = transformer.transform(columnName, valStr);
					valStr = nameValuePair.getValue();
					columnName = nameValuePair.getName();
				} 
				out.print("\t\t");
				out.print(columnName);
				out.print(" : ");				
				valStr = toJson(valStr);
				out.print(valStr);
			}
			out.print("\n\t}\n");
			//out.flush();
		}

		out.print("], \n");
		out.print("\ttotalCount: ");
		
			// Note that we return count+1 -- the +1 so that we can get a NEXT button in GXT
			// on the search box - yeah - its a hack
			count = count+1;
		
		out.print(count);
		
		out.print("}");

	}
*/
	public static String toJson(Object obj) {
		String retval = "";
		retval = new com.google.gson.Gson().toJson(obj);
		if (retval.equals("")) {
			retval = "\"\"";
		}
		return retval;
	}
	
}
