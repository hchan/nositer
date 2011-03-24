package com.nositer.hibernate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;
import org.hibernate.type.BigDecimalType;

@SuppressWarnings("rawtypes")
public class RevEng extends DelegatingReverseEngineeringStrategy {

	public RevEng(ReverseEngineeringStrategy delegate) {
		super(delegate);		
	}
	
	@Override
	public String columnToHibernateTypeName(TableIdentifier table,
			String columnName, int sqlType, int length, int precision,
			int scale, boolean nullable, boolean generatedIdentifier) {
	
		String retval = super.columnToHibernateTypeName(table, columnName, sqlType, length,
				precision, scale, nullable, generatedIdentifier);
				
		if (retval.equals("int")) {
			retval = "Integer";
		}
		return retval;
	}
	
	@Override
	public List getPrimaryKeyColumnNames(TableIdentifier identifier) {
		//appendFile(identifier.getName());
		List<String> retval =  null;//super.getPrimaryKeyColumnNames(identifier);
		//Object firstElem = retval.get(0);
		retval = new ArrayList<String>();
		retval.add("id");
		return retval;
	}
	
	
	
	
	public void appendFile(String str) {
		try {
			FileWriter fw = new FileWriter("/var/log/nositer/reveng.txt", true);
			fw.write(str + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			
		}
	}
}
