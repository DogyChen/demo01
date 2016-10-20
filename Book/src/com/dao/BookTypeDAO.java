package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.BookType;
import com.mysql.jdbc.Statement;
import com.utils.HibernateUtil;


public class BookTypeDAO {

    /*ÿҳ��ʾ��¼��Ŀ*/
    private final int PAGE_SIZE = 10;

    /*�����ѯ���ܵ�ҳ��*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*�����ѯ�����ܼ�¼��*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*���ͼ����Ϣ*/
    public void AddBookType(BookType bookType) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(bookType);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*��ѯBookType��Ϣ*/
    public ArrayList<BookType> QueryBookTypeInfo(int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From BookType bookType where 1=1";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List bookTypeList = q.list();
            return (ArrayList<BookType>) bookTypeList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�BookType��¼*/
    public ArrayList<BookType> QueryAllBookTypeInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From BookType";
            Query q = s.createQuery(hql);
            List bookTypeList = q.list();
            return (ArrayList<BookType>) bookTypeList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void CalculateTotalPageAndRecordNumber() {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From BookType bookType where 1=1";
            Query q = s.createQuery(hql);
            List bookTypeList = q.list();
            recordNumber = bookTypeList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����������ȡ����*/
    public BookType GetBookTypeByBookTypeId(int bookTypeId) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            BookType bookType = (BookType)s.get(BookType.class, bookTypeId);
            return bookType;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����BookType��Ϣ*/
    public void UpdateBookType(BookType bookType) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(bookType);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*ɾ��BookType��Ϣ*/
    public void DeleteBookType (int bookTypeId) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object bookType = s.get(BookType.class, bookTypeId);
            s.delete(bookType);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

}
