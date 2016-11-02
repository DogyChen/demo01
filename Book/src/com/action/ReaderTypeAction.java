package com.action;

import java.util.List;

import com.dao.ReaderTypeDAO;
import com.domain.ReaderType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.TestUtil;

public class ReaderTypeAction extends ActionSupport {

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

    private int readerTypeId;
    public void setReaderTypeId(int readerTypeId) {
        this.readerTypeId = readerTypeId;
    }
    public int getReaderTypeId() {
        return readerTypeId;
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
    ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();

    /*��������ReaderType����*/
    private ReaderType readerType;
    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }
    public ReaderType getReaderType() {
        return this.readerType;
    }

    /*��ת�����ReaderType��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���ReaderType��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddReaderType() {
        ActionContext ctx = ActionContext.getContext();
        try {
            readerTypeDAO.AddReaderType(readerType);
            ctx.put("message",  java.net.URLEncoder.encode("ReaderType��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ReaderType���ʧ��!"));
            return "error";
        }
    }

    /*��ѯReaderType��Ϣ*/
    public String QueryReaderType() {
        if(currentPage == 0) currentPage = 1;
        List<ReaderType> readerTypeList = readerTypeDAO.QueryReaderTypeInfo(currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        readerTypeDAO.CalculateTotalPageAndRecordNumber();
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = readerTypeDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = readerTypeDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("readerTypeList",  readerTypeList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        return "query_view";
    }

    /*��ѯҪ�޸ĵ�ReaderType��Ϣ*/
    public String ModifyReaderTypeQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������readerTypeId��ȡReaderType����*/
        ReaderType readerType = readerTypeDAO.GetReaderTypeByReaderTypeId(readerTypeId);

        ctx.put("readerType",  readerType);
        return "modify_view";
    }

    /*�����޸�ReaderType��Ϣ*/
    public String ModifyReaderType() {
        ActionContext ctx = ActionContext.getContext();
        try {
            readerTypeDAO.UpdateReaderType(readerType);
            ctx.put("message",  java.net.URLEncoder.encode("ReaderType��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ReaderType��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��ReaderType��Ϣ*/
    public String DeleteReaderType() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            readerTypeDAO.DeleteReaderType(readerTypeId);
            ctx.put("message",  java.net.URLEncoder.encode("ReaderTypeɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ReaderTypeɾ��ʧ��!"));
            return "error";
        }
    }

}
