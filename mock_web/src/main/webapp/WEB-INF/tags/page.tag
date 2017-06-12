<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageRange" type="java.lang.Integer" required="true"%>
<%@ attribute name="pageNo" type="java.lang.Integer" required="true"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true"%>
<%@ attribute name="totalPage" type="java.lang.Integer" required="true"%>
<%@ attribute name="totalRecord" type="java.lang.Integer" required="totalRecord"%>
<%@ attribute name="formId" type="java.lang.String" required="true"%>

<%
    long begin = Math.max(1, pageNo - pageRange/2);
	long end = Math.min(begin + (pageRange - 1),totalPage);
	
    while (end - begin < pageRange - 1 && begin > 1) {
        begin--;
    }
    
    request.setAttribute("p_begin", begin);
    request.setAttribute("p_end", end);
%>
<table class="page">
	<tr>
		<% if (pageNo > 1) {%>
		<td><a href="javascript:gotoPage(1,<%=formId%>)">首页</a></td>
		<td><a href="javascript:gotoPage(<%=pageNo - 1%>,<%=formId%>)">上一页</a></td>
		<% } else { %>
		<td><a href="javascript:gotoPage(1,<%=formId%>)">首页</a></td>
		<td><a  class="disabled" href="#">上一页</a></td>
		<% } %>

		<c:forEach var="i" begin="${p_begin}" end="${p_end}">
			<c:choose>
				<c:when test="${i == pageNo}">
					<td class="active"><a href="javascript:gotoPage(${i},<%=formId%>)">${i}</a></td>
				</c:when>
				<c:otherwise>
					<td><a href="javascript:gotoPage(${i},<%=formId%>)">${i}</a></td>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<% if (pageNo.equals(totalPage)){%>
			<td><a class="disabled" href="#">下一页</a></td>
			<td><a href="javascript:gotoPage(<%=totalPage%>,<%=formId%>)">末页</a></td>
		<% } else { %>
			<td>
				<a href="javascript:gotoPage(<%=pageNo+1%>,<%=formId%>)">下一页</a>
			</td>
			<td>
				<a href="javascript:gotoPage(<%=totalPage%>,<%=formId%>)">末页</a>
			</td>
		<%}%>
		<td><%=totalRecord%>条 </td>
		<td><%=totalPage%>页 </td>
		<td>跳转到</td>
		<td class="input_li">
			<input type="hidden"id="p_totalPage" value="<%=totalPage%>" />
			<input type="text" id="p_pageNo" style="width: 36px; height: 18px; padding: 0px 3px 0 3px; text-indent: 0px;" maxlength="5" value="<%=pageNo%>" />
		</td>
		<td>页</td>
		<td class="input_li">
			<input type="button" id="gotoBtn" style="width: 36px; height: 20px; line-height:18px; padding: 1px 0 1px 0;cursor:pointer; text-indent: 0px;" onclick="gotoPageByBtn(<%=formId%>)" value="GO" />
		</td>
		<td>每页</td>
		<td class="input_li">
			<select style="width: 60px; height: 20px;padding: 1px 0 1px 0;" id="p_pageSizeSelect" onchange="gotoPage(1,<%=formId%>)">
				<option value="20" <c:if test="<%=pageSize==20%>">selected</c:if>>20条</option>
				<option value="50" <c:if test="<%=pageSize==50%>">selected</c:if>>50条</option>
				<option value="100" <c:if test="<%=pageSize==100%>">selected</c:if>>100条</option>
			</select>
		</td>
	</tr>
</table>