package org.hicon.service.bean.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
 import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRequestType
{

   @XmlElement(name="UserId")
   private String username; 
   
   @XmlElement(name="password")
   private String password;
   
   public UserRequestType(){
     username = "";
     password = "";
   }
   
   public UserRequestType(String username, String password)
   {
     
      this.username = username;
      this.password = password;
   }

   @NotNull(message = "Username is compulsory")
   @Pattern(regexp = "[a-z-A-Z]*", message = "Username has invalid characters")
   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }
  
   @NotNull(message = "Username is compulsory")
   @Pattern(regexp = "[a-z-A-Z]*", message = "Username has invalid characters")
   public String getUsername()
   {
      return username;
   }

  
   public void setUsername(String username)
   {
      this.username = username;
   }

}