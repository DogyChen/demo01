package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.ReaderType;
import com.mysql.jdbc.Statement;
import com.utils.HibernateUtil;


public class ReaderTypeDAO {

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
    public void AddReaderType(ReaderType readerType) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(readerType);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*��ѯReaderType��Ϣ*/
    public ArrayList<ReaderType> QueryReaderTypeInfo(int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From ReaderType readerType where 1=1";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List readerTypeList = q.list();
            return (ArrayList<ReaderType>) readerTypeList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�ReaderType��¼*/
    public ArrayList<ReaderType> QueryAllReaderTypeInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From ReaderType";
            Query q = s.createQuery(hql);
            List readerTypeList = q.list();
            return (ArrayList<ReaderType>) readerTypeList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void CalculateTotalPageAndRecordNumber() {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From ReaderType readerType where 1=1";
            Query q = s.createQuery(hql);
            List readerTypeList = q.list();
            recordNumber = readerTypeList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����������ȡ����*/
    public ReaderType GetReaderTypeByReaderTypeId(int readerTypeId) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            ReaderType readerType = (ReaderType)s.get(ReaderType.class, readerTypeId);
            return readerType;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����ReaderType��Ϣ*/
    public void UpdateReaderType(ReaderType readerType) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(readerType);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*ɾ��ReaderType��Ϣ*/
    public void DeleteReaderType (int readerTypeId) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object readerType = s.get(ReaderType.class, readerTypeId);
            s.delete(readerType);
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
