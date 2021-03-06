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

    /*每页显示记录数目*/
    private final int PAGE_SIZE = 10;

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加图书信息*/
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

    /*查询BookType信息*/
    public ArrayList<BookType> QueryBookTypeInfo(int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From BookType bookType where 1=1";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始记录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List bookTypeList = q.list();
            return (ArrayList<BookType>) bookTypeList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的BookType记录*/
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

    /*计算总的页数和记录数*/
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

    /*根据主键获取对象*/
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

    /*更新BookType信息*/
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

    /*删除BookType信息*/
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
