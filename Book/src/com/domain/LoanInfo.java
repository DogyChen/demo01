package com.domain;

public class LoanInfo {
    /*���ı��*/
    private int loadId;
    public int getLoadId() {
        return loadId;
    }
    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    /*ͼ�����*/
    private Book book;
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    /*���߶���*/
    private Reader reader;
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    /*����ʱ��*/
    private String borrowTIme;
    public String getBorrowTIme() {
        return borrowTIme;
    }
    public void setBorrowTIme(String borrowTIme) {
        this.borrowTIme = borrowTIme;
    }

    /*�黹ʱ��*/
    private String returnTIme;
    public String getReturnTIme() {
        return returnTIme;
    }
    public void setReturnTIme(String returnTIme) {
        this.returnTIme = returnTIme;
    }

}