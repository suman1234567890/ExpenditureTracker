package org.hicon.service.bean.form;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.appengine.api.datastore.Entity;


@XmlRootElement(name="Group")
@XmlAccessorType(XmlAccessType.FIELD)

public class SingleGroupResponseType
{
@XmlElement(name="Token")
   String token;
   @XmlElement(name="GroupName")
   String groupName;
   @XmlElement(name="GroupDesc")
   String groupDesc;
   @XmlElement(name="GroupId")
   String groupId;
  
public SingleGroupResponseType(){
    
  }

    public SingleGroupResponseType(Entity entry){
      this.token = (String)entry.getProperty("username");
  
  this.groupName = (String)entry.getProperty("Name");

  this.groupDesc = (String)entry.getProperty("Desc");
   
  
   
   this.groupId =  (String)entry.getProperty("GroupId");
  
}
  
   public String getGroupId()
   {
     
      return groupId;
   }
   public void setDiaryId(String groupId)
   {
     
      this.groupId = groupId;
   }

   
   
   @NotNull(message = "Username is compulsory")
   public String getGroupName()
   {
      return groupName;
   }

   public void setItem(String groupName)
   {
      this.groupName = groupName;
   }
   
   @NotNull(message = "Username is compulsory")
   public String getGroupDesc()
   {
      return groupDesc;
   }

   public void setPrice(String  groupDesc)
   {
      this.groupDesc = groupDesc;
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