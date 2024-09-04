package com.saviynt.custom.UserPreprocessor;

// TODO: Auto-generated Javadoc
/**
 * Hello world!.
 */
public class App 
{
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main( String[] args ) {
    	
    	String str = "L=BNG-CC1,OU=SKO,DC=Shell,dc=com";
    	String output = str.substring(str.indexOf("L=")+2, str.indexOf(","));
    	
    	//customerAttribute?.value?.substring(customerAttribute?.value?.indexOf("L=")+2, customerAttribute?.value?.indexOf(","))
    	System.out.println(output);
        System.out.println( "Hello World!" );
    }
}
