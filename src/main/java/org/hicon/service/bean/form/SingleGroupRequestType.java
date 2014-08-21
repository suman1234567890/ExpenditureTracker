package org.hicon.service.bean.form;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Diary")
@XmlAccessorType(XmlAccessType.FIELD)
public class SingleGroupRequestType
{
   @XmlElement(name = "token")
   String token;
   
   @XmlElement(name = "groupId")
   String groupId;

   @NotNull(message = "DiaryId is compulsory")
   public String getGroupId()
   {
      return groupId;
   }
  
   public void setGroupId(String groupId)
   {
      this.groupId = groupId;
   }
   
   @NotNull(message = "Token is compulsory")
   public String getToken()
   {
      return token;
   }

   public void setToken(String token)
   {
      this.token = token;
   }

}