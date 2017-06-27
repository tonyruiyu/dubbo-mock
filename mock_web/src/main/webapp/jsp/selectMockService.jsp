<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/public.css' />" rel="stylesheet" />
<link href="<c:url value='/css/jPages.css' />" rel="stylesheet" />
<link href="<c:url value='/css/animate.css' />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value='/jquery/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.confirm.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" src='<c:url value="/js/laydate/laydate.js"/>'></script>
<script type="text/javascript" src="<c:url value='/js/page/page.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/page/jPages.js'/>"></script>
<jsp:useBean id="dateUtil" class="com.tony.test.DateUtil"></jsp:useBean>
<title>mock_web</title>
<script type="text/javascript">
    function openAddMockServiceTable() {
        document.getElementById("addMockServiceForm").submit();
    }

    function runningOrStopServiceBack(id) {
        return {
            type : "post",
            success : function(data) {
                var serviceStatus = data.serviceStatus;
                if (serviceStatus == "running") {
                    $("#serviceStatus" + id).find("input")[0].value = "running";
                    $("#serviceStatus" + id).find("input").addClass("running");
                    $("#serviceStatus" + id).find("input").removeClass("stop");
                    $("#running" + id).attr("disabled", false);
                    $("#running" + id).addClass("hiddenElement");
                    $("#stop" + id).removeClass("hiddenElement");
                } else {
                    $("#serviceStatus" + id).find("input")[0].value = "stop";
                    $("#serviceStatus" + id).find("input").addClass("stop");
                    $("#serviceStatus" + id).find("input").removeClass(
                            "running");
                    $("#stop" + id).attr("disabled", false);
                    $("#stop" + id).addClass("hiddenElement");
                    $("#running" + id).removeClass("hiddenElement");
                }

                $("#jquery_bg").css({
                    'display' : "none"
                });
                $("#query_hint").addClass("hiddenElement");
            },
            error : function() {
                $("#jquery_bg").css({
                    'display' : "none"
                });
                $("#query_hint").addClass("hiddenElement");
                alert("出错啦");
            }
        }
    }

    function runningOrStopService(serviceStatus, id) {

        var height = $(document).height();
        $("#jquery_bg").css({
            'display' : "block",
            'height' : height
        });
        $("#query_hint").removeClass("hiddenElement");

        var form = $("#runningOrStopServiceFrom" + id);
        var inputs = form.find("input");
        inputs[1].value = serviceStatus;

        $("#runningOrStopServiceFrom" + id).ajaxSubmit(
                runningOrStopServiceBack(id));
    }

    function selectMockOperDefine(formId, id) {
        var queryForm = document.getElementById(formId);
        queryForm.action = "selectMockOperDefine?selectid=" + id;
        queryForm.submit();
    }
    
    $(function() {
        $(".deleteButton").click( function(e) {
            if (confirm("确认删除?")) {
                document.getElementById("deleteMockServiceFrom" + $(e.target).attr("id")).submit();
            }
        });
    });
</script>
</head>
<body>
    <div id="jquery_bg" class="bg"></div>
    <form id="addMockServiceForm" action='<c:url value="/mvc/selectMockOperDefine"/>' method="get" target="_blank">
        <input type="hidden" name="selectid" value="-1" />
    </form>
    <form id="queryForm" action='<c:url value="/mvc/selectMockService"/>' method="post">
        <table>
            <tr>
                <td colspan="6"><h3 align="left">配置服务查询</h3></td>
                <td class="buttonTd">
                    <button class="addButton mockServicesButton" onclick='openAddMockServiceTable()' type="button">新增</button>
                </td>
            </tr>
            <tr>
                <td class="queryitem">ID</td>
                <td><input type="text" name="selectid" align="left" value="${page.params.id}"></td>
                <td class="queryitem">服务名称</td>
                <td><input type="text" name="serviceInterface" align="left" value="${page.params.serviceInterface}"></td>
                <td class="queryitem">应用名称</td>
                <td><input type="text" name="applicationName" align="left" value="${page.params.applicationName}"></td>
                <td></td>
            </tr>
            <tr>
                <td class="queryitem">Group</td>
                <td><input type="text" name="groupName" align="left" value="${page.params.groupName}"></td>
                <td class="queryitem">Version</td>
                <td><input type="text" name="version" align="left" value="${page.params.version}"></td>
                <td class="queryitem">服务状态</td>
                <td><select name="serviceStatus">
                        <option value="">全部</option>
                        <option <c:if test="${page.params.serviceStatus == 'running'}">selected='selected'</c:if> value="running">running</option>
                        <option <c:if test="${page.params.serviceStatus == 'stop'}">selected='selected'</c:if> value="stop">stop</option>
                    </select></td>
                <td class="queryButtonTd">
                    <button type="submit">查询</button> <input type="hidden" id="pageNo" name="pageNo" value="${page == null ? 1 : page.pageNo}" /> <input type="hidden" id="pageSize" name="pageSize" value="${page == null ? 20 : page.pageSize}" />
                </td>
            </tr>
        </table>
    </form>
    <tags:page pageRange="5" pageNo="${page == null ? 1 : page.pageNo}" pageSize="${page == null ? 20 : page.pageSize}" totalPage="${page == null ? 0 : page.totalPage}" totalRecord="${page == null ? 0 : page.totalRecord}" formId="queryForm" />
    <div class="holder" style="display: none;"></div>
    <table>
        <thead>
            <tr class="queryitem">
                <th>ID</th>
                <th>注册中心</th>
                <th>协议</th>
                <th>服务名称</th>
                <th>应用名称</th>
                <th>Group</th>
                <th>Version</th>
                <th>超时时间</th>
                <th>重试次数</th>
                <th>更新时间</th>
                <th colspan="2">服务状态</th>
                <th style="width: 69px;">操作</th>
            </tr>
        </thead>
        <c:if test="${not empty page.results}">
            <tbody id="itemContainer">
                <c:forEach items="${page.results}" var="resultInfo">
                    <tr class="itemtr" ondblclick="selectMockOperDefine('id${resultInfo.id}','${resultInfo.id}')" />
                    <td>
                        <form id='id${resultInfo.id}' action='<c:url value="/mvc/selectMockOperDefine"/>' method="post" target="_blank"></form> <c:out value="${resultInfo.id}" />
                    </td>
                    <td><c:out value="${resultInfo.registryProtocol }" /></td>
                    <td><c:out value="${resultInfo.protocolName }" /></td>
                    <td><c:out value="${resultInfo.serviceInterface }" /></td>
                    <td><c:out value="${resultInfo.applicationName }" /></td>
                    <td><c:out value="${resultInfo.groupName }" /></td>
                    <td><c:out value="${resultInfo.version }" /></td>
                    <td><c:out value="${resultInfo.timeout }" /></td>
                    <td><c:out value="${resultInfo.retries }" /></td>
                    <td><c:out value="${dateUtil.date2String(resultInfo.updateTime,'yyyy-MM-dd HH:mm:ss')}" /></td>
                    <td id="serviceStatus${resultInfo.id}" class="serviceStatusTd"><input class="${resultInfo.serviceStatus} serviceStatusInput" type="text" name="serviceStatus" readonly="readonly" value="${resultInfo.serviceStatus}" /></td>
                    <td class="serviceStatusTd">
                        <form id='runningOrStopServiceFrom${resultInfo.id}' action='<c:url value="/mvc/updateOrInsertMockService"/>' method="post">
                            <input type="hidden" name="id" value="${resultInfo.id}" /> <input type="hidden" name="serviceStatus" />
                        </form>
                        <button onclick='runningOrStopService("running","${resultInfo.id}")' ondblclick='if(document.all)event.cancelBubble=true;else event.stopPropagation();' class="serviceStatusButton runningButton <c:if test='${resultInfo.serviceStatus == "running"}'>hiddenElement</c:if>"
                        type="button" name="${resultInfo.id}" id="running${resultInfo.id}" value="running">running</button>
                        <button onclick='runningOrStopService("stop","${resultInfo.id}")' ondblclick='if(document.all)event.cancelBubble=true;else event.stopPropagation();'
                        class="serviceStatusButton stopButton <c:if test='${resultInfo.serviceStatus == null || resultInfo.serviceStatus == "stop"}'>hiddenElement</c:if>" type="button" name="${resultInfo.id}" id="stop${resultInfo.id}" value="stop">stop</button>
                    </td>
                    <td class="buttonTd">
                        <form id='deleteMockServiceFrom${resultInfo.id}' action='<c:url value="/mvc/deleteMockService"/>' method="post">
                            <input type="hidden" name="id" value="${resultInfo.id}"> <input type="hidden" name="selectid" value="${page.params.id}"> <input type="hidden" name="serviceInterface" value="${page.params.serviceInterface}"> <input type="hidden" name="registryId"
                                value="${page.params.registryId}"> <input type="hidden" name="protocolId" value="${page.params.protocolId}"> <input type="hidden" name="applicationName" value="${page.params.applicationName}"> <input type="hidden" name="groupName"
                                value="${page.params.groupName}"> <input type="hidden" name="version" value="${page.params.version}"> <input type="hidden" name="serviceStatus" value="${page.params.serviceStatus}"> <input type="hidden" id="pageNo" name="pageNo"
                                value="${page == null ? 1 : page.pageNo}" /> <input type="hidden" id="pageSize" name="pageSize" value="${page == null ? 20 : page.pageSize}" />
                        </form>
                        <button class="deleteButton" id="${resultInfo.id}" type="submit">删除</button>
                    </td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
    <div id="query_hint" class="query_hint hiddenElement">
        <img src="../css/images/waiting.gif" />正在执行，请稍等...
    </div>
</body>
</html>