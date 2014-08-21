package org.hicon.service.bean.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Group")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddGroupRequestType
{
   @XmlElement(name="Token")
   String token;
   @XmlElement(name="Name")
   String groupName;
   @XmlElement(name="Desc")
   String groupDesc;
   /**
    * @return the groupDesc
    */
   public String getGroupDesc()
   {
      return groupDesc;
   }
   /**
    * @param groupDesc the groupDesc to set
    */
   public void setGroupDesc(String groupDesc)
   {
      this.groupDesc = groupDesc;
   }
   /**
    * @return the groupName
    */
   public String getGroupName()
   {
      return groupName;
   }
   /**
    * @param groupName the groupName to set
    */
   public void setGroupName(String groupName)
   {
      this.groupName = groupName;
   }
   /**
    * @return the token
    */
   public String getToken()
   {
      return token;
   }
   /**
    * @param token the token to set
    */
   public void setToken(String token)
   {
      this.token = token;
   }
   
}