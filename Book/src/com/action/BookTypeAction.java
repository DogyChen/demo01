package com.action;

import java.util.List;

import com.dao.BookTypeDAO;
import com.domain.BookType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.TestUtil;

public class BookTypeAction extends ActionSupport {

    /*��ǰ�ڼ�ҳ*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*һ������ҳ*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    private int bookTypeId;
    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }
    public int getBookTypeId() {
        return bookTypeId;
    }

    /*��ǰ��ѯ���ܼ�¼��Ŀ*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*ҵ������*/
    BookTypeDAO bookTypeDAO = new BookTypeDAO();

    /*��������BookType����*/
    private BookType bookType;
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
    public BookType getBookType() {
        return this.bookType;
    }

    /*��ת�����BookType��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���BookType��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddBookType() {
        ActionContext ctx = ActionContext.getContext();
        try {
            bookTypeDAO.AddBookType(bookType);
            ctx.put("message",  java.net.URLEncoder.encode("BookType��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("BookType���ʧ��!"));
            return "error";
        }
    }

    /*��ѯBookType��Ϣ*/
    public String QueryBookType() {
        if(currentPage == 0) currentPage = 1;
        List<BookType> bookTypeList = bookTypeDAO.QueryBookTypeInfo(currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        bookTypeDAO.CalculateTotalPageAndRecordNumber();
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = bookTypeDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = bookTypeDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("bookTypeList",  bookTypeList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        return "query_view";
    }

    /*��ѯҪ�޸ĵ�BookType��Ϣ*/
    public String ModifyBookTypeQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������bookTypeId��ȡBookType����*/
        BookType bookType = bookTypeDAO.GetBookTypeByBookTypeId(bookTypeId);

        ctx.put("bookType",  bookType);
        return "modify_view";
    }

    /*�����޸�BookType��Ϣ*/
    public String ModifyBookType() {
        ActionContext ctx = ActionContext.getContext();
        try {
            bookTypeDAO.UpdateBookType(bookType);
            ctx.put("message",  java.net.URLEncoder.encode("BookType��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("BookType��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��BookType��Ϣ*/
    public String DeleteBookType() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            bookTypeDAO.DeleteBookType(bookTypeId);
            ctx.put("message",  java.net.URLEncoder.encode("BookTypeɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("BookTypeɾ��ʧ��!"));
            return "error";
        }
    }

}
