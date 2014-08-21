package org.hicon.service.diary;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hicon.service.bean.form.AddGroupRequestType;
import org.hicon.service.bean.form.AddGroupResponseType;
import org.hicon.service.bean.form.EditGroupRequestType;
import org.hicon.service.bean.form.EditGroupResponseType;
import org.hicon.service.bean.form.ListGroupRequestType;
import org.hicon.service.bean.form.ListGroupResponseType;
import org.hicon.service.bean.form.DeleteGroupRequestType;
import org.hicon.service.bean.form.DeleteGroupResponseType;
import org.hicon.service.session.facade.SessionFacade;
import org.hicon.service.diary.facade.DiaryFacade;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/Group")
public class GroupServices
{
private static final Logger LOG = Logger.getLogger(GroupServices.class.getName());
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/AddGroup")
     public AddGroupResponseType AddToGroup(AddGroupRequestType addGroupData){
       try {
            String userName = validateUser(addGroupData.getToken());
            if (userName == null) {
                return null;
            } else {
                addGroupData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.AddToGroup(addGroupData));

            }
        } catch (InstantiationException e) {
            LOG.log(Level.SEVERE,"error", e);
        } catch (IllegalAccessException e) {
            LOG.log(Level.SEVERE,"error", e);
        }

       return null;
     }
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/EditGroup") 
     public EditGroupResponseType EditGroup(EditGroupRequestType editGroupData){
       try {
            String userName = validateUser(editGroupData.getToken());
            if (userName == null) {
                return null;
            } else {
                editGroupData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.EditToGroup(editGroupData));

            }
        } catch (InstantiationException e) {
            LOG.log(Level.SEVERE,"error", e);
        } catch (IllegalAccessException e) {
           LOG.log(Level.SEVERE,"error", e);
        }

       return null;
     }
     @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/DeleteGroup")
     public DeleteGroupResponseType DeleteGroup(DeleteGroupRequestType deleteGroupData){
       try {
            String userName = validateUser(deleteGroupData.getToken());
            if (userName == null) {
                return null;
            } else {
                deleteGroupData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.DeleteFromGroup(deleteGroupData));

            }
        } catch (InstantiationException e) {
            LOG.log(Level.SEVERE,"error", e);
        } catch (IllegalAccessException e) {
            LOG.log(Level.SEVERE,"error", e);
        }

       return null;
     }
     @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/ListGroup")
     public ListGroupResponseType ListGroup(ListGroupRequestType viewGroupData){
       try {
            String userName = validateUser(viewGroupData.getToken());
            if (userName == null) {
                return null;
            } else {
                viewGroupData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                
                   return (diaryFacade.ListOfGroups(viewGroupData));
                

            }
        } catch (InstantiationException e) {
            LOG.log(Level.SEVERE,"error", e);
        } catch (IllegalAccessException e) {
            LOG.log(Level.SEVERE,"error", e);
        }

       return null;
     }
     private String validateUser(String tokenValue) {
        SessionFacade facade = new SessionFacade();
        String userName = facade.validateUser(tokenValue);
        return userName;
    }
    private boolean eligibilityCheck(String userName, String diaryId) {
        SessionFacade facade = new SessionFacade();
        return facade.eligiblityCheck(userName, diaryId);
    }


}