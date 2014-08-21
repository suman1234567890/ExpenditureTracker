package org.hicon.service.bean.form;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.appengine.api.datastore.Entity;


@XmlRootElement(name="Diary")
@XmlAccessorType(XmlAccessType.FIELD)
public class SingleEntryResponseType
{
   @XmlElement(name="Token")
   String token;
   @XmlElement(name="Date")
   Date date;
   @XmlElement(name="Item")
   String item;
   @XmlElement(name="Price")
   int price;
   @XmlElement(name="DiaryId")
   String diaryId;
   @XmlElement(name="GroupId")
   String groupId;

 @XmlElement(name="GroupName")
   String groupName;

   

  public SingleEntryResponseType(){
    
  }

    public SingleEntryResponseType(Entity entry){
      this.token = (String)entry.getProperty("username");
  
  this.item = (String)entry.getProperty("item");

  try{
   this.date=  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse((String)entry.getProperty("Date"));
   
   }
   catch(ParseException ex){
     this.date =  new Date();
   }
   
  // this.price =Integer.valueOf((String)entry.getProperty("Price"));
   this.price = Integer.parseInt(entry.getProperty("Price").toString());
   this.diaryId =  (String)entry.getProperty("DiaryId");
   this.groupId =  (String)entry.getProperty("GroupId");
   this.groupName = (String)entry.getProperty("GroupName");

  
}
public String getGroupName()
   {
     
      return groupName;
   }
   public void setGroupName(String groupName)
   {
     
      this.groupName = groupName;
   }

public String getGroupId()
   {
     
      return groupId;
   }
   public void setGroupId(String groupId)
   {
     
      this.groupId = groupId;
   }

  
   public String getDiaryId()
   {
     
      return diaryId;
   }
   public void setDiaryId(String dairyId)
   {
     
      this.diaryId = dairyId;
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