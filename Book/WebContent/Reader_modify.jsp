<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.domain.Reader" %>
<%@ page import="com.domain.ReaderType" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���е�readerType��Ϣ
    List<ReaderType> readerTypeList = (List<ReaderType>)request.getAttribute("readerTypeList");
    Reader reader = (Reader)request.getAttribute("reader");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>�޸Ķ���</TITLE>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
<script language="javascript">
/*��֤��*/
function checkForm() {
    var readerNo = document.getElementById("reader.readerNo").value;
    if(readerNo=="") {
        alert('��������߱��!');
        return false;
    }
    var readerSex = document.getElementById("reader.readerSex").value;
    if(readerSex=="") {
        alert('�������������!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>
<BODY>
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><s:form action="Reader/Reader_ModifyReader.action" method="post" onsubmit="return checkForm();" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
  <tr>
    <td width=30%>���߱��:</td>
    <td width=70%><input id="reader.readerNo" name="reader.readerNo" type="text" value="<%=reader.getReaderNo() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%>
      <select name="reader.readerType.readerTypeId">
      <%
        for(ReaderType readerType:readerTypeList) {
          String selected = "";
          if(readerType.getReaderTypeId() == reader.getReaderType().getReaderTypeId())
            selected = "selected";
      %>
          <option value='<%=readerType.getReaderTypeId() %>' <%=selected %>><%=readerType.getReaderTypeName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>����:</td>
    <td width=70%><input id="reader.readerName" name="reader.readerName" type="text" size="20" value='<%=reader.getReaderName() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="reader.readerSex" name="reader.readerSex" type="text" size="2" value='<%=reader.getReaderSex() %>'/></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='����' >
        &nbsp;&nbsp;
        <input type="reset" value='��д' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
