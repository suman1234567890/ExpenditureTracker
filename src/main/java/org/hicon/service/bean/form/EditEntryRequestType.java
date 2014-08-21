package org.hicon.service.bean.form;

import com.google.appengine.api.datastore.Entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Diary")
@XmlAccessorType(XmlAccessType.FIELD)
public class EditEntryRequestType
{
   @XmlElement(name="token")
   String token;
   
   @XmlElement(name="Item")
   String item;
   @XmlElement(name="Price")
   int price;
   @XmlElement(name="Id")
   String diaryId;
  public EditEntryRequestType(){
    this.token = "";
    
    this.item = "";
    this.price = 0;
    this.diaryId="";
  } 
  public EditEntryRequestType(Entity entry){
   this.token = (String)entry.getProperty("username");
  // this.date = (Date)(entry.getProperty("Date").toString());
   this.item = (String)entry.getProperty("item");
  // this.price =Integer.valueOf((String)entry.getProperty("Price"));
   this.price = Integer.parseInt(entry.getProperty("Price").toString());
   this.diaryId =  (String)entry.getProperty("DiaryId");
}
   public String getDiaryId()
   {
      return diaryId;
   }

   public void setDiaryId(String diaryId)
   {
      this.diaryId = diaryId;
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