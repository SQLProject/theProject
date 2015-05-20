package parser;

import entities.Country;

public abstract class abstract_parser {
	
	
	
	
	
	public static String[] getFirstTwoTags(String line){
		
		/* we'll save the yagoId, this is the first tag in the line */
		String yagoID= line.substring((line.indexOf('<',0)+1), (line.indexOf('>',0)+1));
		line=line.substring(line.indexOf('>',0)+1);
		
		/* we'll save the country name, this is the second tag in the line */
		String name=line.substring(line.indexOf('<',0), line.indexOf('>',0));
		
		String[] result= {yagoID,name};
		return result;
	}
	
	
	
	

}
