package org.hicon.service.bean.form;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserResponseType
{
   public boolean getStatus()
   {
      return status;
   }

   public void setStatus(Boolean status)
   {
      this.status = status;
   }
   @XmlElement(name="Status")
   boolean status;
}