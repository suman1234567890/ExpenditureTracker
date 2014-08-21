package org.hicon.service.session.facade;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;


public class SessionFacade
{
   public String validateUser(String tokenValue){
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Query query = new Query("session");
      query.addFilter("sessionid", FilterOperator.EQUAL, tokenValue);
      PreparedQuery pq = datastore.prepare(query);
      Entity user = pq.asSingleEntity();
      if(user==null){
        return null;
      }
      else{
        return user.getProperty("username").toString();
      }
   }
   public String checkSession(String userName){
     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Query query = new Query("session");
      query.addFilter("username", FilterOperator.EQUAL, userName);
      PreparedQuery pq = datastore.prepare(query);
      Entity user = pq.asSingleEntity();
      if(user==null){
        return null;
      }
      else{
        return user.getProperty("sessionid").toString();
      }
   }
   public boolean eligiblityCheck(String userName , String diaryId){
   DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

     Query query = new Query("Diary");
	  query.addFilter("username", FilterOperator.EQUAL, userName);
    query.addFilter("DiaryId", FilterOperator.EQUAL, diaryId);
	  PreparedQuery pq = datastore.prepare(query);
	  Entity result = pq.asSingleEntity();
   if(result !=null ){
     return true;
   }
   else{
     return false;
   }
   }
}