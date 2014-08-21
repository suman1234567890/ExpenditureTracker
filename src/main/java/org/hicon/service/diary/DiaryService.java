package org.hicon.service.diary;

import org.hicon.service.bean.form.AddEntryRequestType;
import org.hicon.service.bean.form.AddEntryResponseType;
import org.hicon.service.bean.form.DeleteEntryRequestType;
import org.hicon.service.bean.form.DeleteEntryResponseType;
import org.hicon.service.bean.form.EditEntryRequestType;
import org.hicon.service.bean.form.EditEntryResponseType;
import org.hicon.service.bean.form.ListEntryRequestType;
import org.hicon.service.bean.form.ListEntryResponseType;
import org.hicon.service.diary.facade.DiaryFacade;
import org.hicon.service.session.facade.SessionFacade;
import org.hicon.service.bean.form.SingleEntryRequestType;
import org.hicon.service.bean.form.SingleEntryResponseType;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Diary")
public class DiaryService {
private static final Logger LOG = Logger.getLogger(DiaryService.class.getName());

  	@POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/AddEntry")
    public AddEntryResponseType AddToDiary(AddEntryRequestType addDiaryData) {
        try {
            String userName = validateUser(addDiaryData.getToken());
            if (userName == null) {
                return null;
            } else {
                addDiaryData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.AddToDiary(addDiaryData));

            }
        } catch (InstantiationException e) {
           LOG.log(Level.SEVERE,"error", e);
        } catch (IllegalAccessException e) {
           LOG.log(Level.SEVERE, "error ", e);
        }

return null;
    }
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/EditEntry")
    public EditEntryResponseType EditToDiary(EditEntryRequestType editDiaryData) {
        EditEntryResponseType temp = new EditEntryResponseType();
        try {
            String userName = validateUser(editDiaryData.getToken());
            if (userName == null) {
                temp.setStatus("UserName is null;");
                return temp;
            } else if (!eligibilityCheck(userName, editDiaryData.getDiaryId())) {
                temp.setStatus("eligibility  is failed;" + userName + " id " + editDiaryData.getDiaryId());
                return temp;
            } else {
                editDiaryData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.EditToDiary(editDiaryData));

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
    @Path("/DeleteEntry")
    public DeleteEntryResponseType DeleteInDiary(DeleteEntryRequestType deleteDiaryData) {

        try {
            String userName = validateUser(deleteDiaryData.getToken());
            if (userName == null) {
                return null;
            } else {
                deleteDiaryData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.DeleteFromDiary(deleteDiaryData));

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
    @Path("/ListEntry")
    public ListEntryResponseType ListFromDiary(ListEntryRequestType listDiaryData) {
        try {
            String userName = validateUser(listDiaryData.getToken());
            if (userName == null) {
                return null;
            } else {
                listDiaryData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.listOfEntries(listDiaryData));

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
    @Path("/ListSingleEntry")
    public SingleEntryResponseType ListSingleFromDiary(SingleEntryRequestType singleListDiaryData) {
        try {
            String userName = validateUser(singleListDiaryData.getToken());
            if (userName == null) {
                return null;
            } else {
                singleListDiaryData.setToken(userName);

                DiaryFacade diaryFacade = new DiaryFacade();
                return (diaryFacade.GetDetailOfEntry(singleListDiaryData));

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