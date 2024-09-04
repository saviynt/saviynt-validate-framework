package com.saviynt.custom.OrganizationBulkProcessor;

import java.util.ArrayList;
import java.util.List;

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
    public static void main( String[] args )
    {
        String jsonString  = "{\"actionType\":\"Update\",\"customproperty2\":\"dscds\",\"objectKey\":\"31\",\"custid\":\"31\",\"id\":\"31\",\"startdate\":\"May 29, 2020\",\"customername\":\"asddgf\",\"savmodule\":\"null\"}";
        
		/*
		 * OrganizationBulkProcessor processor = new OrganizationBulkProcessor();
		 * processor.processOrganization(jsonString);
		 */
        
        List<Student> studentList = new ArrayList<Student>(); 
        //Student student = new App.Student();
        		//student.firstname = "abx";
        method(studentList);
        
        
        String updatedJson = "{\"entitlements\":[{\"entitlementvalue\":\"ZSAVTESTG1\"}],\"endpoints\":[{\"cp2\":null,\"cp1\":null,\"cp4\":null,\"endpointname\":\"AzureAD\",\"cp3\":null,\"cp5\":null}],\"tab\":\"sendforapproval\",\"roles\":[{\"rolename\":\"Role1\"}],\"details\":{\"customertype\":4,\"savUpdateDate\":\"2020-10-12T01:59:11Z\",\"comments\":\"updated\",\"createdate\":\"2020-10-11T14:55:07Z\",\"description\":\"desc_123\",\"source\":\"admin\",\"sourceaction\":\"updateOrg\",\"startdate\":\"2020-10-10T18:30:00Z\",\"createuser\":\"admin\",\"isApi\":\"FALSE\",\"eventsource\":11,\"versionkey\":1,\"savCreateDate\":\"2020-10-12T01:59:11Z\",\"score\":0,\"enddate\":\"2020-10-29T18:30:00Z\",\"updatedate\":\"2020-10-12T02:01:32Z\",\"versionstatus\":2,\"isCreateOrg\":\"FALSE\",\"risk\":0,\"customername\":\"orgcreate_9\",\"status\":1,\"updateuser\":\"admin2\"},\"owners\":[{\"owner\":\"admin\",\"rank\":1}],\"source\":\"admin\",\"sourceaction\":\"updateOrg\"}";
        OrganizationCustomValidator validator = new OrganizationCustomValidator();
        validator.processOrganization(updatedJson);
    }
    
    
    
     /**
      * The Class Student.
      */
     class Student {
    	 
 	    /**
 	     * Instantiates a new student.
 	     */
 	    public Student() {
    		 
    	 }
    	 
    	 /** The firstname. */
 	    String firstname;
    	
    }
     
     /**
      * Method.
      *
      * @param studentList the student list
      */
     public static void method(List<Student> studentList) {
    	 
    	 
    	 
     }
}
