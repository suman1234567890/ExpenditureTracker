package org.hicon.service.bean.form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
@XmlRootElement(name = "Login")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponseType
{
   /**
    * @return the authCode
    */
    
   public String getAuthCode()
   {
      return authCode;
   }

   /**
    * @param authCode the authCode to set
    */
   public void setAuthCode(String authCode)
   {
      this.authCode = authCode;
   }
   @XmlElement(name = "UserName")
   String authCode;
}