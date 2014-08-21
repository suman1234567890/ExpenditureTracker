package org.hicon.service.bean.form;

import org.hicon.service.bean.form.SingleEntryResponseType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PdfEntryUserEntryResponseType{
  Map<String, ArrayList<SingleEntryResponseType>> map = new HashMap<String, ArrayList<SingleEntryResponseType>>();
  public void insertIntoList(String userName,SingleEntryResponseType res){
  ArrayList<SingleEntryResponseType> temp = map.get(userName);
  if(temp == null){
    temp = new ArrayList<SingleEntryResponseType>();
  }
  temp.add(res);
  map.put(userName,temp);

     
  }
  public Map<String, ArrayList<SingleEntryResponseType>> getMap(){
      return map;
  }

}