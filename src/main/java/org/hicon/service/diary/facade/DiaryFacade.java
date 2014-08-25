package org.hicon.service.diary.facade;

import org.hicon.service.bean.form.AddEntryRequestType;
import org.hicon.service.bean.form.AddEntryResponseType;
import org.hicon.service.bean.form.AddGroupRequestType;
import org.hicon.service.bean.form.AddGroupResponseType;
import org.hicon.service.bean.form.BeanFactory;
import org.hicon.service.bean.form.DeleteEntryRequestType;
import org.hicon.service.bean.form.DeleteEntryResponseType;
import org.hicon.service.bean.form.DeleteGroupRequestType;
import org.hicon.service.bean.form.DeleteGroupResponseType;
import org.hicon.service.bean.form.EditEntryRequestType;
import org.hicon.service.bean.form.EditEntryResponseType;
import org.hicon.service.bean.form.EditGroupRequestType;
import org.hicon.service.bean.form.EditGroupResponseType;
import org.hicon.service.bean.form.ListEntryRequestType;
import org.hicon.service.bean.form.ListEntryResponseType;
import org.hicon.service.bean.form.ListGroupRequestType;
import org.hicon.service.bean.form.ListGroupResponseType;
import org.hicon.service.bean.form.SingleEntryResponseType;
import org.hicon.service.bean.form.SingleEntryRequestType;
import org.hicon.service.bean.form.SingleGroupResponseType;
import org.hicon.service.bean.form.SingleGroupRequestType;
import org.hicon.service.constant.PGConstant;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;



public class DiaryFacade {
    public AddEntryResponseType AddToDiary(AddEntryRequestType addDiaryData)
    throws InstantiationException, IllegalAccessException {
        AddEntryResponseType response = BeanFactory.getAddEntryResponse();
        String diaryId = Integer.toString(GenerateUserId());
        Key customerKey = KeyFactory.createKey("Diary", diaryId);
        Entity diary = new Entity("Diary", customerKey);
        diary.setProperty("item", addDiaryData.getItem());
        diary.setProperty("Date", addDiaryData.getDate().toString());
        diary.setProperty("DiaryId", diaryId);
        diary.setProperty("GroupId", addDiaryData.getGroupId());
        diary.setProperty("Price", addDiaryData.getPrice());
        diary.setProperty("username", addDiaryData.getToken());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(diary);
        response.setStatus(diaryId);
        return response;
    }
    public AddGroupResponseType AddToGroup(AddGroupRequestType addGroupData)
    throws InstantiationException, IllegalAccessException {
        AddGroupResponseType response = BeanFactory.getAddGroupResponse();
        String groupId = Integer.toString(GenerateUserId());
        Key customerKey = KeyFactory.createKey("Group", groupId);
        Entity diary = new Entity("Group", customerKey);
        diary.setProperty("Name", addGroupData.getGroupName());
        diary.setProperty("GroupId", groupId);
        diary.setProperty("Desc", addGroupData.getGroupDesc());
        diary.setProperty("Creator", addGroupData.getToken());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(diary);
        response.setStatus(groupId);
        return response;
    }

    public EditEntryResponseType EditToDiary(EditEntryRequestType editDiaryData)
    throws InstantiationException, IllegalAccessException {
        EditEntryResponseType response = BeanFactory.getEditEntryResponse();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Diary");
        query.addFilter("DiaryId", FilterOperator.EQUAL, editDiaryData.getDiaryId());
        PreparedQuery pq = datastore.prepare(query);
        Entity diary = pq.asSingleEntity();
        diary.setProperty("item", editDiaryData.getItem());
        diary.setProperty("DiaryId", editDiaryData.getDiaryId());
        diary.setProperty("Price", editDiaryData.getPrice());
        diary.setProperty("username", editDiaryData.getToken());
        datastore.put(diary);
        response.setStatus(editDiaryData.getDiaryId());
        return response;
    }
    public EditGroupResponseType EditToGroup(EditGroupRequestType editGroupData)
    throws InstantiationException, IllegalAccessException {
        EditGroupResponseType response = BeanFactory.getEditGroupResponse();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Group");
        query.addFilter("GroupId", FilterOperator.EQUAL, editGroupData.getGroupId());
        PreparedQuery pq = datastore.prepare(query);
        Entity diary = pq.asSingleEntity();
        diary.setProperty("Desc", editGroupData.getStatus());
        datastore.put(diary);
        response.setStatus(editGroupData.getGroupId());
        return response;
    }

    public ListEntryResponseType listOfEntries(ListEntryRequestType listGroupData) throws InstantiationException, IllegalAccessException {
        ListEntryResponseType response = BeanFactory.getListEntryResponse();
        Query query = new Query("Diary").addSort("Date", Query.SortDirection.DESCENDING);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        List < Entity > entries =
            datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
           ListGroupRequestType req = new ListGroupRequestType();
           req.setToken(listGroupData.getToken());
           HashMap<String,String> map = new HashMap();
        List<SingleGroupResponseType> groupResults = ListOfGroups(req).getList();
        for(SingleGroupResponseType res : groupResults){
          map.put(res.getGroupId(),res.getGroupName());
        }
        List < SingleEntryResponseType > results = new ArrayList < SingleEntryResponseType > ();
        for (Entity entity: entries) {
             entity.setProperty("GroupName",map.get((String)entity.getProperty("GroupId")));
            results.add(new SingleEntryResponseType(entity));
        }
        response.setList(results);
        return response;

    }
    public ListGroupResponseType ListOfGroups(ListGroupRequestType listDiaryData) throws InstantiationException, IllegalAccessException {
        ListGroupResponseType response = BeanFactory.getListGroupResponse();
        Query query = new Query("Group");
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        List < Entity > entries =
            datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
        List < SingleGroupResponseType > results = new ArrayList < SingleGroupResponseType > ();
        for (Entity entity: entries) {
            if ("True".equals(listDiaryData.getFilter())) {
                if ("active".equals((String) entity.getProperty("Desc"))) {
                    results.add(new SingleGroupResponseType(entity));
                }

            } else {
            if (!"Deleted".equals((String) entity.getProperty("Desc"))) {


                results.add(new SingleGroupResponseType(entity));
            }}
        }
        response.setList(results);
        return response;

    }
     public HashMap<String,String> ListOfGroupNames() throws InstantiationException, IllegalAccessException {
        HashMap<String,String> groupNames = new HashMap<String,String>();
        Query query = new Query("Group");
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        List < Entity > entries =
            datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
        
        for (Entity entity: entries) {
           groupNames.put((String)entity.getProperty("GroupId"),(String)entity.getProperty("Name"));
           
        }
       
        return groupNames;

    }

    


    public DeleteEntryResponseType DeleteFromDiary(DeleteEntryRequestType listDiaryData) throws InstantiationException, IllegalAccessException {
        DeleteEntryResponseType response = BeanFactory.getDeleteEntryResponse();
        Query query = new Query("Diary");
        query.addFilter("DiaryId", FilterOperator.EQUAL, listDiaryData.getDiaryId());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery pq = datastore.prepare(query);
        Entity diary = pq.asSingleEntity();
        datastore.delete(diary.getKey());
        response.setStatus(true);
        return response;
    }
    public DeleteGroupResponseType DeleteFromGroup(DeleteGroupRequestType deleteGroupData) throws InstantiationException, IllegalAccessException {
        DeleteGroupResponseType response = BeanFactory.getDeleteGroupResponse();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Group");
        query.addFilter("GroupId", FilterOperator.EQUAL, deleteGroupData.getGroupId());
        PreparedQuery pq = datastore.prepare(query);
        Entity diary = pq.asSingleEntity();
        diary.setProperty("Desc", "Deleted");
        datastore.put(diary);
        response.setStatus(true);

        return response;
    }


    private int GenerateUserId() {
        Random rn = new Random();
        int n = PGConstant.userNameMax - PGConstant.userNameMin + 1;
        int i = rn.nextInt() % n;
        return (PGConstant.userNameMin + i);
    }
    public SingleEntryResponseType GetDetailOfEntry(SingleEntryRequestType singleEntry) throws InstantiationException, IllegalAccessException {
        SingleEntryResponseType response = null;
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Diary");
        query.addFilter("DiaryId", FilterOperator.EQUAL, singleEntry.getDiaryId());
        PreparedQuery pq = datastore.prepare(query);
        Entity diary = pq.asSingleEntity();
        if (diary == null) {
            return response;
        } else {
            response = new SingleEntryResponseType(diary);
        }
        return response;
    }

}