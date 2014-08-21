package org.hicon.service.bean.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "Login")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequestType
{
   @XmlElement(name = "UserName")
   private String userName;

   @XmlElement(name = "PassWord")
   private String passWord;
   
   
   

   @NotNull(message = "PassWord is compulsory")
   @Pattern(regexp = "[a-z-A-Z]*", message = "PassWord has invalid characters")
   public String getPassWord()
   {
      return passWord;
   }

   /**
   * @param passWord the passWord to set
   */
   public void setPassWord(String passWord)
   {
      this.passWord = passWord;
   }

   @NotNull(message = "Username is compulsory")
   @Pattern(regexp = "[a-z-A-Z]*", message = "UserName has invalid characters")
   public String getUserName()
   {
      return userName;
   }

   /**
   * @param userName the userName to set
   */
   public void setUserName(String userName)
   {
      this.userName = userName;
   }

}