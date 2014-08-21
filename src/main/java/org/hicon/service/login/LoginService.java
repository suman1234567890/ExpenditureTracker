package org.hicon.service.login;

import org.hicon.service.bean.form.UserRequestType;
import org.hicon.service.bean.form.LoginRequestType;
import org.hicon.service.bean.form.LoginResponseType;
import org.hicon.service.bean.form.UserResponseType;
import org.hicon.service.constant.PGConstant;
import org.hicon.service.login.facade.LoginFacade;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/registration")
public class LoginService
{

   @POST
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_XML)
   @Path("/SignIn")
   public LoginResponseType SignIn(LoginRequestType loginData)
   {
       try{
         LoginFacade facade = new LoginFacade();
         return(facade.SignIn(loginData));
       }
       catch(InstantiationException e){
         return null;
       }
       catch(IllegalAccessException e){
         return null;
       }
     
   }

   @POST
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_XML)
   @Path("/SignUp")
   public UserResponseType SignUp(UserRequestType user)
   {
       try{
         LoginFacade facade = new LoginFacade();
         return(facade.SignUp(user));
       }
       catch(InstantiationException e){
         return null;
       }
       catch(IllegalAccessException e){
         return null;
       }
      
   }

   
}