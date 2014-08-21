package org.hicon.service.bean.form;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Diary")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteEntryRequestType
{
  @XmlElement(name = "token")
   String token;
   
   @XmlElement(name = "diaryId")
   String diaryId;

   @NotNull(message = "DiaryId is compulsory")
   public String getDiaryId()
   {
      return diaryId;
   }
  
   public void setDiaryId(String diaryId)
   {
      this.diaryId = diaryId;
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