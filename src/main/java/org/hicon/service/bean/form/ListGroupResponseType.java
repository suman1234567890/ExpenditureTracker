package org.hicon.service.bean.form;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListGroupResponseType
{
   public List<SingleGroupResponseType> getList()
   {
      return results;
   }

   public void setList(List<SingleGroupResponseType> result)
   {
      this.results = result;
   }
   @XmlElement(name = "entry")
   List<SingleGroupResponseType> results;
}