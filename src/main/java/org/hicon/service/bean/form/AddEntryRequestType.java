package org.hicon.service.bean.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Diary")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddEntryRequestType
{
   @XmlElement(name="Token")
   String token;
   @XmlElement(name="Date")
   Date date;
   @XmlElement(name="Item")
   String item;
   @XmlElement(name="Price")
   int price;
  
    @XmlElement(name="GroupId")
   String groupId;

public String getGroupId()
   {
     
      return groupId;
   }

   public void setGroupId(String groupId)
   {
      this.groupId = groupId;
   }
  

   
   public Date getDate()
   {
     if(date == null){
       return new Date();
     }
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }
   @NotNull(message = "Username is compulsory")
   
   public String getItem()
   {
      return item;
   }

   public void setItem(String item)
   {
      this.item = item;
   }
   
   @NotNull(message = "Username is compulsory")
   public int getPrice()
   {
      return price;
   }

   public void setPrice(int price)
   {
      this.price = price;
   }
   @NotNull(message = "Username is compulsory")
   @Pattern(regexp = "[a-z-A-Z]*", message = "Username has invalid characters")
   public String getToken()
   {
      return token;
   }

   public void setToken(String token)
   {
      this.token = token;
   }

}