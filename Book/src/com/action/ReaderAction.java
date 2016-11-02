package com.action;

import java.util.List;

import com.dao.ReaderDAO;
import com.dao.ReaderTypeDAO;
import com.domain.Reader;
import com.domain.ReaderType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.TestUtil;

public class ReaderAction extends ActionSupport {

    /*�������Ҫ��ѯ������: ���߱��*/
    private String readerNo;
    public void setReaderNo(String readerNo) {
        this.readerNo = readerNo;
    }
    public String getReaderNo() {
        return this.readerNo;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private ReaderType readerType;
    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }
    public ReaderType getReaderType() {
        return this.readerType;
    }

    /*�������Ҫ��ѯ������: ����*/
    private String readerName;
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
    public String getReaderName() {
        return this.readerName;
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
    ReaderDAO readerDAO = new ReaderDAO();

    /*��������Reader����*/
    private Reader reader;
    public void setReader(Reader reader) {
        this.reader = reader;
    }
    public Reader getReader() {
        return this.reader;
    }

    /*��ת�����Reader��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�ReaderType��Ϣ*/
        ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
        List<ReaderType> readerTypeList = readerTypeDAO.QueryAllReaderTypeInfo();
        ctx.put("readerTypeList", readerTypeList);
        return "add_view";
    }

    /*���Reader��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddReader() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤���߱���Ƿ��Ѿ�����*/
        String readerNo = reader.getReaderNo();
        Reader db_reader = readerDAO.GetReaderByReaderNo(readerNo);
        if(null != db_reader) {
            ctx.put("error",  java.net.URLEncoder.encode("�ö��߱���Ѿ�����!"));
            return "error";
        }
        try {
            ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
            readerType = readerTypeDAO.GetReaderTypeByReaderTypeId(reader.getReaderType().getReaderTypeId());
            reader.setReaderType(readerType);
            readerDAO.AddReader(reader);
            ctx.put("message",  java.net.URLEncoder.encode("Reader��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Reader���ʧ��!"));
            return "error";
        }
    }

    /*��ѯReader��Ϣ*/
    public String QueryReader() {
        if(currentPage == 0) currentPage = 1;
        if(readerNo == null) readerNo = "";
        if(readerName == null) readerName = "";
        List<Reader> readerList = readerDAO.QueryReaderInfo(readerNo, readerType, readerName, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        readerDAO.CalculateTotalPageAndRecordNumber(readerNo, readerType, readerName);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = readerDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = readerDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("readerList",  readerList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("readerNo", readerNo);
        ctx.put("readerType", readerType);
        ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
        List<ReaderType> readerTypeList = readerTypeDAO.QueryAllReaderTypeInfo();
        ctx.put("readerTypeList", readerTypeList);
        ctx.put("readerName", readerName);
        return "query_view";
    }

    /*��ѯҪ�޸ĵ�Reader��Ϣ*/
    public String ModifyReaderQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������readerNo��ȡReader����*/
        Reader reader = readerDAO.GetReaderByReaderNo(readerNo);

        ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
        List<ReaderType> readerTypeList = readerTypeDAO.QueryAllReaderTypeInfo();
        ctx.put("readerTypeList", readerTypeList);
        ctx.put("reader",  reader);
        return "modify_view";
    }

    /*�����޸�Reader��Ϣ*/
    public String ModifyReader() {
        ActionContext ctx = ActionContext.getContext();
        try {
            ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
            readerType = readerTypeDAO.GetReaderTypeByReaderTypeId(reader.getReaderType().getReaderTypeId());
            reader.setReaderType(readerType);
            readerDAO.UpdateReader(reader);
            ctx.put("message",  java.net.URLEncoder.encode("Reader��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Reader��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Reader��Ϣ*/
    public String DeleteReader() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            readerDAO.DeleteReader(readerNo);
            ctx.put("message",  java.net.URLEncoder.encode("Readerɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Readerɾ��ʧ��!"));
            return "error";
        }
    }

}
