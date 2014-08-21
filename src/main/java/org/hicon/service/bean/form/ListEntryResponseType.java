package org.hicon.service.bean.form;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListEntryResponseType
{
   public List<SingleEntryResponseType> getList()
   {
      return results;
   }

   public void setList(List<SingleEntryResponseType> result)
   {
      this.results = result;
   }
   @XmlElement(name = "entry")
   List<SingleEntryResponseType> results;
}