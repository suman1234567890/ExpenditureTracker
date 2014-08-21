package org.hicon.service.mail.facade;
import org.hicon.service.bean.form.BeanFactory;
import org.hicon.service.bean.form.ListEntryResponseType;
import org.hicon.service.bean.form.ListGroupResponseType;
import org.hicon.service.bean.form.PdfEntryListResponseType;
import org.hicon.service.bean.form.SingleEntryResponseType;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import org.hicon.service.bean.form.PdfEntryUserEntryResponseType;
import org.hicon.service.diary.facade.DiaryFacade;

import java.util.Iterator;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.itextpdf.text.BaseColor;
import java.text.SimpleDateFormat;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.itextpdf.text.Paragraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
public class MailFacade {

    public void generateTable(Document document)
    throws InstantiationException, IllegalAccessException, DocumentException, ParseException {

        Query query = new Query("Diary").addSort("Date", Query.SortDirection.DESCENDING);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        List < Entity > entries =
            datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
        PdfEntryListResponseType pdf = new PdfEntryListResponseType();;
        for (Entity entry: entries) {
            SingleEntryResponseType ent = new SingleEntryResponseType(entry);
            pdf.insertIntoList(ent);
        }
        /*
                for(PdfEntryUserEntryResponseType p : pdf.getList()){
                  document.add(createPDF(p));
                }*/
        Map < String, PdfEntryUserEntryResponseType > map = pdf.getList();
         DiaryFacade facade = new DiaryFacade();
         HashMap<String,String> groupList =  facade.ListOfGroupNames();
        Iterator < String > itr = map.keySet().iterator();
        while (itr.hasNext()) {
        String name = itr.next();
        String groupName = groupList.get(name);
        Paragraph paragraph = new Paragraph();
        document.add(new Paragraph("GroupName :"+groupName));
            PdfEntryUserEntryResponseType element = map.get(name);
            document.add(createPDF(element));
        }
    }
    public PdfPTable createPDF(PdfEntryUserEntryResponseType pdfEntry) throws ParseException {
        PdfPTable table = new PdfPTable(15);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Rank"));
        cell.setColspan(1);
        table.addCell(cell);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell = new PdfPCell(new Phrase("Date"));
        cell.setColspan(2);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Item"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Member"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Price"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Total"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        Map < String, ArrayList < SingleEntryResponseType >> map = pdfEntry.getMap();
        int groupTotal = 0;
        for (String userName: map.keySet()) {
            int counter = 0;
            int userTotal = 0;

            for (SingleEntryResponseType entry: map.get(userName)) {
                cell = new PdfPCell(new Phrase(String.valueOf(++counter)));
                cell.setColspan(1);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(new SimpleDateFormat("MM-dd-yyyy").format(entry.getDate())));
                cell.setColspan(2);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(entry.getItem()));
                cell.setColspan(3);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(entry.getToken()));
                cell.setColspan(3);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(entry.getPrice())));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
                table.addCell(cell);
                userTotal += entry.getPrice();
            }
            cell = new PdfPCell(new Phrase(""));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBorderColorBottom(BaseColor.BLACK);
            cell.setColspan(12);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(userTotal)));
            cell.setColspan(3);
            table.addCell(cell);
            groupTotal += userTotal;

        }
        cell = new PdfPCell(new Phrase(""));
        

        cell.setColspan(12);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(groupTotal)));
        
        cell.setColspan(3);
        table.addCell(cell);
        return table;

    }

    public PdfPTable createFirstTable() {
        // a table with three columns
        PdfPTable table = new PdfPTable(12);
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Rank"));
        cell.setColspan(1);
        table.addCell(cell);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell = new PdfPCell(new Phrase("Date"));
        cell.setColspan(2);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Item"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Member"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Price"));
        cell.setColspan(3);
        cell.setBorderColorBottom(BaseColor.BLACK);
        table.addCell(cell);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Diary").addSort("Date", Query.SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(query);
        int counter = 0;
        for (Entity result: pq.asIterable()) {
            cell = new PdfPCell(new Phrase(String.valueOf(++counter)));
            cell.setColspan(1);
            table.addCell(cell);
            String date = (String) result.getProperty("Date");
            SimpleDateFormat format = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy");
            String dateFinal = "";
            try {
                Date parseDate = format.parse(date);
                SimpleDateFormat format1 = new SimpleDateFormat(
                    "dd-mm-yyyy");
                System.out.println(format1.format(parseDate));
                dateFinal = format1.format(parseDate);
            } catch (ParseException e) {

            }
            cell = new PdfPCell(new Phrase(dateFinal));
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((String) result.getProperty("item")));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((String) result.getProperty("username")));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(result.getProperty("Price"))));
            cell.setColspan(3);
            table.addCell(cell);
        }
        return table;
    }

}