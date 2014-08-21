package org.hicon.service.bean.form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="AddEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EditEntryResponseType
{
public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }
   @XmlElement(name="DiaryId")
   String status;

}