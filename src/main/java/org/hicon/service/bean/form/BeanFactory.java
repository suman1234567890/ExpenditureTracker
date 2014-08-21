package org.hicon.service.bean.form;

import org.hicon.service.bean.form.AddEntryResponseType;
import org.hicon.service.bean.form.DeleteEntryResponseType;
import org.hicon.service.bean.form.EditEntryResponseType;
import org.hicon.service.bean.form.ListEntryResponseType;
import org.hicon.service.bean.form.LoginResponseType;
import org.hicon.service.bean.form.UserResponseType;
import org.hicon.service.bean.form.SingleEntryResponseType;
import org.hicon.service.bean.form.AddGroupResponseType;
import org.hicon.service.bean.form.DeleteGroupResponseType;
import org.hicon.service.bean.form.EditGroupResponseType;
import org.hicon.service.bean.form.ListGroupResponseType;
import org.hicon.service.bean.form.SingleGroupResponseType;

public class BeanFactory
{
  public static LoginResponseType getLoginResponse() throws InstantiationException,IllegalAccessException{
    return LoginResponseType.class.newInstance();
  }
  public static UserResponseType getUserResponse() throws InstantiationException, IllegalAccessException{
    return UserResponseType.class.newInstance();
  }
  public static AddEntryResponseType getAddEntryResponse() throws InstantiationException, IllegalAccessException{
    return AddEntryResponseType.class.newInstance();
  }
  public static EditEntryResponseType getEditEntryResponse() throws InstantiationException, IllegalAccessException{
    return EditEntryResponseType.class.newInstance();
  }
  public static ListEntryResponseType getListEntryResponse() throws InstantiationException, IllegalAccessException{
    return ListEntryResponseType.class.newInstance();
  }
  public static DeleteEntryResponseType getDeleteEntryResponse() throws InstantiationException, IllegalAccessException{
    return DeleteEntryResponseType.class.newInstance();
  }
  public static SingleEntryResponseType getSingleEntryResponse() throws InstantiationException, IllegalAccessException{
    return SingleEntryResponseType.class.newInstance();
  }
  public static AddGroupResponseType getAddGroupResponse() throws InstantiationException, IllegalAccessException{
    return AddGroupResponseType.class.newInstance();
  }
  public static EditGroupResponseType getEditGroupResponse() throws InstantiationException, IllegalAccessException{
    return EditGroupResponseType.class.newInstance();
  }
  public static ListGroupResponseType getListGroupResponse() throws InstantiationException, IllegalAccessException{
    return ListGroupResponseType.class.newInstance();
  }
  public static DeleteGroupResponseType getDeleteGroupResponse() throws InstantiationException, IllegalAccessException{
    return DeleteGroupResponseType.class.newInstance();
  }
  public static SingleGroupResponseType getSingleGroupResponse() throws InstantiationException, IllegalAccessException{
    return SingleGroupResponseType.class.newInstance();
  }
}