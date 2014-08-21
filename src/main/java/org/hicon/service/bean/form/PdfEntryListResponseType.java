package org.hicon.service.bean.form;
import org.hicon.service.bean.form.SingleEntryResponseType;
import org.hicon.service.bean.form.PdfEntryUserEntryResponseType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Map;

public class PdfEntryListResponseType
{

   Map<String, PdfEntryUserEntryResponseType> map = new HashMap<String, PdfEntryUserEntryResponseType>();
   Map<String, String> groupName = new HashMap<String,String>();
   public void insertIntoList(SingleEntryResponseType entry){
        
        PdfEntryUserEntryResponseType pdfEntry= map.get(entry.getGroupId());
        if(pdfEntry == null){
          pdfEntry = new PdfEntryUserEntryResponseType();

        }
        
        
        pdfEntry.insertIntoList(entry.getToken(),entry);
        /*  
        PdfEntryUserEntryResponseType pdfs =  map.get(entry.getGroupId());
        if(pdfs == null){
            pdfs = new PdfEntryUserEntryResponseType();
        }*/
        //pdfs.add(pdfEntry);
        map.put(entry.getGroupId(),pdfEntry);
        groupName.put(entry.getGroupId(),entry.getGroupName());
          
   }
   
   public Map<String,PdfEntryUserEntryResponseType> getList(){
       return map;
   }  
   public String getGroupName(String groupId){
     return groupName.get(groupId);
   }

}

