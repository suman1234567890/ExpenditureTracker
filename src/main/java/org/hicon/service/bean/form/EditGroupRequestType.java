package org.hicon.service.bean.form;
import com.google.appengine.api.datastore.Entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Group")
@XmlAccessorType(XmlAccessType.FIELD)
public class EditGroupRequestType
{
   @XmlElement(name="Token")
   String token;
  @XmlElement(name="GroupId")
   String groupId;

   @XmlElement(name="Status")
   String status;

   
   
  public EditGroupRequestType(){
    this.token = "";
    
this.status="";

    this.groupId="";
  } 

  public EditGroupRequestType(Entity entry){
   this.token = (String)entry.getProperty("username");
  this.status =  (String)entry.getProperty("Status");

  
   this.groupId =  (String)entry.getProperty("GroupId");
   
}
   public String getGroupId()
   {
      return groupId;
   }

   public void setGroupId(String groupId)
   {
      this.groupId = groupId;
   }
   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
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