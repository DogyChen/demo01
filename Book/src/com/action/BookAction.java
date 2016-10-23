package com.action;

import java.util.List;

import com.dao.BookDAO;
import com.dao.BookTypeDAO;
import com.domain.Book;
import com.domain.BookType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.TestUtil;

public class BookAction extends ActionSupport {

    /*�������Ҫ��ѯ������: ͼ������*/
    private String bookName;
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookName() {
        return this.bookName;
    }

    /*�������Ҫ��ѯ������: ͼ���������*/
    private BookType bookType;
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
    public BookType getBookType() {
        return this.bookType;
    }

    /*�������Ҫ��ѯ������: ͼ��������*/
    private String barcode;
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getBarcode() {
        return this.barcode;
    }

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

    /*��ǰ��ѯ���ܼ�¼��Ŀ*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*ҵ������*/
    BookDAO bookDAO = new BookDAO();

    /*��������Book����*/
    private Book book;
    public void setBook(Book book) {
        this.book = book;
    }
    public Book getBook() {
        return this.book;
    }

    /*��ת�����Book��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�BookType��Ϣ*/
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        List<BookType> bookTypeList = bookTypeDAO.QueryAllBookTypeInfo();
        ctx.put("bookTypeList", bookTypeList);
        return "add_view";
    }

    /*���Book��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddBook() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤ͼ���������Ƿ��Ѿ�����*/
        String barcode = book.getBarcode();
        Book db_book = bookDAO.GetBookByBarcode(barcode);
        if(null != db_book) {
            ctx.put("error",  java.net.URLEncoder.encode("��ͼ���������Ѿ�����!"));
            return "error";
        }
        try {
            BookTypeDAO bookTypeDAO = new BookTypeDAO();
            bookType = bookTypeDAO.GetBookTypeByBookTypeId(book.getBookType().getBookTypeId());
            book.setBookType(bookType);
            bookDAO.AddBook(book);
            ctx.put("message",  java.net.URLEncoder.encode("Book��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Book���ʧ��!"));
            return "error";
        }
    }

    /*��ѯBook��Ϣ*/
    public String QueryBook() {
        if(currentPage == 0) currentPage = 1;
        if(bookName == null) bookName = "";
        if(barcode == null) barcode = "";
        List<Book> bookList = bookDAO.QueryBookInfo(bookName, bookType, barcode, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        bookDAO.CalculateTotalPageAndRecordNumber(bookName, bookType, barcode);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = bookDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = bookDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("bookList",  bookList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("bookName", bookName);
        ctx.put("bookType", bookType);
        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        List<BookType> bookTypeList = bookTypeDAO.QueryAllBookTypeInfo();
        ctx.put("bookTypeList", bookTypeList);
        ctx.put("barcode", barcode);
        return "query_view";
    }

    /*��ѯҪ�޸ĵ�Book��Ϣ*/
    public String ModifyBookQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������barcode��ȡBook����*/
        Book book = bookDAO.GetBookByBarcode(barcode);

        BookTypeDAO bookTypeDAO = new BookTypeDAO();
        List<BookType> bookTypeList = bookTypeDAO.QueryAllBookTypeInfo();
        ctx.put("bookTypeList", bookTypeList);
        ctx.put("book",  book);
        return "modify_view";
    }

    /*�����޸�Book��Ϣ*/
    public String ModifyBook() {
        ActionContext ctx = ActionContext.getContext();
        try {
            BookTypeDAO bookTypeDAO = new BookTypeDAO();
            bookType = bookTypeDAO.GetBookTypeByBookTypeId(book.getBookType().getBookTypeId());
            book.setBookType(bookType);
            bookDAO.UpdateBook(book);
            ctx.put("message",  java.net.URLEncoder.encode("Book��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Book��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Book��Ϣ*/
    public String DeleteBook() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            bookDAO.DeleteBook(barcode);
            ctx.put("message",  java.net.URLEncoder.encode("Bookɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Bookɾ��ʧ��!"));
            return "error";
        }
    }

}
