package org.hicon.service.login.facade;

import org.hicon.service.bean.form.UserRequestType;
import org.hicon.service.bean.form.BeanFactory;
import org.hicon.service.bean.form.LoginRequestType;
import org.hicon.service.bean.form.LoginResponseType;
import org.hicon.service.bean.form.UserResponseType;
import org.hicon.service.constant.PGConstant;
import org.hicon.service.session.facade.SessionFacade;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import java.util.Random;

public class LoginFacade
{
   public LoginResponseType SignIn(LoginRequestType loginData)  throws InstantiationException,IllegalAccessException{
     
      LoginResponseType response = BeanFactory.getLoginResponse();
    
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Query query = new Query("User");
      query.addFilter("username", FilterOperator.EQUAL, loginData.getUserName());
      query.addFilter("password", FilterOperator.EQUAL, loginData.getPassWord());
      PreparedQuery pq = datastore.prepare(query);
      Entity user = pq.asSingleEntity();
      if (user == null)
      {
         return response;
      }
      else
      {
         SessionFacade session = new SessionFacade();
         String authCode = session.checkSession(loginData.getUserName());
         if(authCode ==null){
           int userId = GenerateUserId();
           authCode = Integer.toString(userId);
            Key authKey = KeyFactory.createKey("session", authCode);
  
       Entity auth = new Entity("session", authKey);
         auth.setProperty("username", loginData.getUserName()); 
         auth.setProperty("sessionid",authCode);
         datastore.put(auth);
         }
         
        
         response.setAuthCode(authCode);
         return response;
      }
    
  }
  
  public UserResponseType SignUp(UserRequestType signUpData) throws InstantiationException, IllegalAccessException{
      UserResponseType response = BeanFactory.getUserResponse();
      String userId =  Integer.toString(GenerateUserId());
      Key customerKey = KeyFactory.createKey("User", userId);

      Entity User = new Entity("User", customerKey);
      User.setProperty("username", signUpData.getUsername());
      User.setProperty("password", signUpData.getPassword());
      User.setProperty("userid", userId);

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      datastore.put(User);
      response.setStatus(true);
      return response;     
      
  }
  private int GenerateUserId()
   {
      Random rn = new Random();
      int n = PGConstant.userNameMax - PGConstant.userNameMin + 1;
      int i = rn.nextInt() % n;
      return (PGConstant.userNameMin + i);
   }
}