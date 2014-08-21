package org.hicon.service.bean.form;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Diary")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListGroupRequestType
{
   @XmlElement(name = "Token")
   String token;
   @XmlElement(name = "Filter")
   String filter;


   @NotNull(message = "Username is compulsory")
   @Pattern(regexp = "[a-z-A-Z]*", message = "Username has invalid characters")
   public String getToken()
   {
      return token;
   }

   public void setFilter(String filter)
   {
      this.filter = filter;
   }
   public String getFilter()
   {
      return filter;
   }

   public void setToken(String token)
   {
      this.token = token;
   }
}