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
<link href="<c:url value='/css/animate.css' />" rel="stylesheet" />
<link href="<c:url value='/css/jquery.multiselect.css' />" rel="stylesheet"/>
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value='/jquery/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery-ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.multiselect.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.confirm.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" src='<c:url value="/js/laydate/laydate.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/common/json.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/common/json_format.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/common/keys.js"/>'></script>
<jsp:useBean id="dateUtil" class="com.tony.test.DateUtil"></jsp:useBean>
<title>selectMockOperDefine</title>
<script type="text/javascript">

    function formatContent(target) {
        var content = target.value;
        target.value = formatJson(content);
    }
    
    function showLoadingIcon(element) {
        element.disabled = true;
        var height = $(document).height();
        $("#jquery_bg").css({
            display : "block",
            'height' : height
        });
        $("#query_hint").removeClass("hiddenElement");
    }
    
    function hiddenLoadingIcon(element) {
        element.disabled = false;
        $("#jquery_bg").css({
            display : "none"
        });
        $("#query_hint").addClass("hiddenElement");
    }

    function runningOrStopService(element) {
        if ($(element).is(':checked')) {
			$("#serviceStatus").find("input")[0].value = "running";
        } else {
			$("#serviceStatus").find("input")[0].value = "stop";
        }
        
        showLoadingIcon(element);

        var form = $("#mockServicesForm");
        form.ajaxSubmit(runningOrStopServiceBack(element));
    }
    
    function runningOrStopServiceBack(element) {
        return {
            type : "post",
            success : function(data) {
                hiddenLoadingIcon(element);
            },
            error : function() {
                if ($(element).is(':checked')) {
                    $(element).checked = false;
                } else {
                    $(element).checked = true;
                }
                showErrorInfo(element,"出错啦！");
            }
        }
    }

    document.onkeydown = function(event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 27) { // 按 Esc 
            hiddenDivTable();
        }
    }

    function showAddTable(table) {
        $("#show" + table + "Button").attr("disabled", true);
        $("#add" + table + "Table").removeClass("hiddenElement");
    };
    
    function hiddenDivTable() {
        var popdivs = $(".popdiv");
        for ( var index in popdivs) {
            $("#" + popdivs[index].id).addClass("hiddenElement");
        }
        $("#detailDiv").addClass("hiddenElement");
        $("#bg").css({
            'display' : "none"
        });
    };

    function addFormOptionsBack(table) {
        return {
            type : "post",
            success : function(data) {
                //清空添加table
                var values = $("#add" + table + "Table input");
                for (var index = 0; index < values.length; ++index) {
                    values[index].value = "";
                }

                var values = $("#add" + table + "Table textarea");
                for (var index = 0; index < values.length; ++index) {
                    values[index].value = "";
                }

                //添加一行元素
                var tempTr = $("#" + table + "Table tr").eq(0).clone();

                var tdText = tempTr.find("td");
                var tdInput = tempTr.find(".updateInput");

                if (tdInput.length > 0) {
                    var index = 0;
                    for ( var key in data) {
                        for ( var index in tdInput) {
                            if (tdInput[index].name == key) {
                                tdInput[index].value = data[key];
                            }
                        }
                    }
                } else {
                    var index = 1;
                    for ( var key in data) {
                        tdText[index++].innerText = data[key];
                    }
                }

                var radioButton = tempTr.find("." + table + "Radio");
                if (radioButton[0] != null) {
                    radioButton[0].id = table + "Radio" + data.id;
                    radioButton[0].value = data.id;
                }

                var radioForm = tempTr.find("." + table + "RadioForm");
                if (radioForm[0] != null) {
                    radioForm[0].id = table + "RadioForm" + data.id;
                }

                var hiddenInput = tempTr.find("#hidden" + table + "Id");
                if (hiddenInput[0] != null) {
                    hiddenInput[0].value = data.id;
                }

                var updateButton = tempTr.find(".updateButton");
                if (updateButton[0] != null) {
                    updateButton[0].value = data.id;
                }
                tempTr.removeClass("hiddenElement");
                tempTr.appendTo("#" + table + "Table");

                $("#show" + table + "Button").attr("disabled", false);
                $("#add" + table + "Table").addClass("hiddenElement");
            }
        }
    };

    function addMockTestTr() {
        var tempTr = $("#MockTestAddTr").eq(0).clone();
        tempTr.removeClass("hiddenElement");
        tempTr.appendTo("#MockTestKeyValueTable");
        return tempTr;
    }

    function deleteMockTestTr(element) {
        $(element).parent().remove();
    }

    function insertOneRow(table) {
        $("#add" + table + "Form").ajaxSubmit(addFormOptionsBack(table));
    };

    function radioChange(event, table) {

        var radioValue;
        var tds = $(event).find("td");

        if (tds.length == 0) {
            radioValue = table + "Radio" + event.value;
            tds = $(event).parent().parent().find("td");
        } else {
            radioValue = table + "Radio" + tds[1].innerText;
        }

        var values = $("." + table + "Radio");

        for (var index = 0; index < values.length; ++index) {
            if (values[index].id != radioValue) {
                values[index].checked = false;
            } else {
                values[index].checked = true;
            }
        }

        $("#" + table + "Id")[0].value = tds[1].innerText;
        $("#" + table + "Name")[0].value = tds[2].innerText;

        hiddenDivTable();
    };

    function openMockTestTable(table) {
        // 取得要提交的参数  
        var mockTestIds = [];

        var serviceId = ${mockOperDefine.mockServices[0].id} + "";

        $('input[name="mockTestCheckbox"]:checked').each(function(index) {
            mockTestIds.push($(this).val()); 
        });

        $('#MockTestKeyValueTable').children().find('.MockTestAddTrClass').remove();

        if(mockTestIds.length > 0) {
            // 取得要提交页面的URL
            var action = '<c:url value="/mvc/selectMockRuleScript"/>';
            // 创建Form  
            var form = $('<form></form>');  
            // 设置属性  
            form.attr('action', action);  
            form.attr('method', 'post');  
            // form的target属性决定form在哪个页面提交  
            // _self -> 当前页面 _blank -> 新页面  
            form.attr('target', '_self');  
            
            // 创建Input  
            var mockTestIdsInput = $('<input type="text" name="mockTestIds" />');  
            mockTestIdsInput.attr('value', mockTestIds);  
    
            // 创建Input  
            var serviceIdInput = $('<input type="text" name="serviceId" />');  
            serviceIdInput.attr('value', serviceId);
            
            // 附加到Form  
            form.append(mockTestIdsInput);
            form.append(serviceIdInput);
            // 提交表单  
            form.ajaxSubmit(openMockTestTableBack(table));
        }
    }

    function openMockTestTableBack(element) {
        return {
            type : "post",
            success : function(data) {
                console.log(data);
                $("#mockTestMethodNameValue").empty();
                $("#mockTestMethodRule").html("");
                $("#mockTestInfoInput").val("");

                $.each($.parseJSON(data),function(key,value) {
                    $("#mockTestMethodNameValue").append("<option value='" + key + "'>" + key + "</option>");
                    $("#mockTestMethodRule").append("<div id='mockTestMethodRule" + key + "' class='mockTestMethodRuleDiv'>" + formatJson(value,{'semicolonAfterNewLine':true,'quotationMarksCurlyBracesRemoveNewLine':true}) + "</div>");
                });

                changeMockTestMethodNameValue();
                
                showDivTable(element);
            },
            error : function() {
                showErrorInfo(element,"出错啦！");
            }
        }
    };

    function changeMockTestMethodNameValue() {
        var mockTestMethodNameValue = $("#mockTestMethodNameValue").val();
        $(".mockTestMethodRuleDiv").addClass("hiddenElement");
        $("#mockTestMethodRule" + mockTestMethodNameValue).removeClass("hiddenElement");
    }

    function showErrorInfo(element,errorMessage) {
        if(element != null) {
            hiddenLoadingIcon(element);
        }
        alert(errorMessage);
    }

    function showDivTable(table) {
        var height = $(document).height();
        
        $("#bg").css({
            'display' : "block",
            'height' : height
        });
        
        var top = $(window).height() * 0.1;
        var left = $(window).width() * 0.1;
        var scrollTop = $(document).scrollTop();
        var scrollLeft = $(document).scrollLeft();
        $("#" + table + "Div").css({
            'top' : top + scrollTop,
            'left' : left + scrollLeft
        });
        $("#" + table + "Div").removeClass("hiddenElement");
    }

    function openMethedUpdateTable(element, table) {
        var id = element.value;

        var inputs = $("#MethedAddFormTable").find("input");
        var textareas = $("#MethedAddFormTable").find("textarea");

        //重置输入框的值
        if (id != null && id != "") {
            var divs = $("#MethedTr" + id).find("div");
            for ( var divsIndex in divs) {
                for ( var index in inputs) {
                    if (inputs[index].name == divs[divsIndex].id) {
                        inputs[index].value = divs[divsIndex].innerText;
                    }
                }
                for ( var textareaIndex in textareas) {
                    if (textareas[textareaIndex].name == divs[divsIndex].id) {
                        textareas[textareaIndex].value = divs[divsIndex].innerText;
                    }
                }
            }
        } else {
            for ( var index in inputs) {
                if (inputs[index].name == "methodName" || inputs[index].name == "id" || inputs[index].name == "execSort") {
                    inputs[index].value = "";
                }
            }
            for ( var textareaIndex in textareas) {
                textareas[textareaIndex].value = "";
            }
        }

        showDivTable(table);
    }

    function addOrUpdateMethedTableBack(id) {
        return {
            type : "post",
            success : function(data) {
                if (id != null && id != "") {
                    var divs = $("#MethedTr" + id + " div");
                    for ( var index in divs) {
                        for ( var key in data) {
                            if (divs[index].id == key) {
                                divs[index].innerText = data[key];
                            }
                        }
                    }
                } else {
                    //添加一行元素
                    var tempTr = $("#MethedAddTr").clone();
                    var divs = tempTr.find("div");
                    for ( var index in divs) {
                        for ( var key in data) {
                            if (divs[index].id == key) {
                                divs[index].innerText = data[key];
                            }
                        }
                    }

                    var buttons = tempTr.find("button");
                    buttons[0].value = data.id;

                    var inputs = tempTr.find("input");
                    inputs[0].id = data.methodName;
                    inputs[0].value = data.id;
                    inputs[1].value = data.id;
                    inputs[2].value = data.serviceId;
                    inputs[3].value = data.methodName;

                    tempTr[0].id = "MethedTr" + data.id;
                    tempTr.removeClass("hiddenElement");
                    
                    var array_values = new Array();
                    $("#serviceMethedRules").find("input:checkbox").each(function() {
                        array_values.push($(this).attr("id"));
                    });
                    if(array_values.indexOf(data.methodName) == -1) {
                        $('#selectMockRuleNames').append("<option value=" + data.methodName + ">" + data.methodName + "</option>");
                        $('#selectMockRuleNames').multiselect("refresh"); 
                    }
                    
                    
                    addTr("serviceMethedRules", 0, tempTr);
                    /* var serviceMethedRules = ("#MethedTable");
                    serviceMethedRules.innerHTML = tempTr.innerHTML + serviceMethedRules.innerHTML;
                    tempTr.prepend("#serviceMethedRules"); */
                    
                    
                    if($("#MethedTable").find("tbody")[0].id == "") {
                        $("#MethedTable").find("tbody")[0].id = "serviceMethedRules";
                        $("#serviceMethedRules").sortable({
                            stop: function( event, ui ) {
                                sortableStop(this);
                            }
                        });
                    }
                    
                }
                $("#bg").css({
                    'display' : "none"
                });
                $("#MethedAddDiv").addClass("hiddenElement");
            }
        }
    };
    
    function addTr(tab, row, tempTr) {  
        //获取table最后一行 $("#tab tr:last")
        //获取table第一行 $("#tab tr").eq(0)
        //获取table倒数第二行 $("#tab tr").eq(-2)
         
        var $tr = $("#" + tab + " tr").eq(row);
        if ($tr.size() == 0) { alert("指定的table id或行数不存在！"); 
            return; 
        } $tr.before(tempTr); 
    }

    function addOrUpdateMethedTable() {
        var id = "";
        var inputs = $("#MethedAddFormTable").find("input");
        for (var index = 0; index < inputs.length; index++) {
            if (inputs[index].name == "id") {
                id = inputs[index].value;
            }
        }
        var textareas = $("#MethedAddFormTable").find("textarea");
        for (var index = 0; index < textareas.length; index++) {
            if (textareas[index].name == "returnMessage") {
                textareas[index].value = formatJson(textareas[index].value);
            }
        }
        $("#MethedAddForm").ajaxSubmit(addOrUpdateMethedTableBack(id));
    }

    function updateOneRow(element, form) {
        var needAjaxSubmit = false;
        var values = $("#" + form)[0];
        for (var index = 0; index < values.length; ++index) {
            if (values[index].className == "updateInput") {
                if (values[index].disabled == true) {
                    needAjaxSubmit = false;
                    values[index].disabled = false;
                    element.innerText = "提交";
                } else {
                    needAjaxSubmit = true;
                    break;
                }
            }
        }

        if (needAjaxSubmit) {
            /* showLoadingIcon(element); */
            $("#" + form).ajaxSubmit(updateOneRowBack(element, form));
        }
    };

    function updateOneRowBack(element, form) {
        return {
            type : "post",
            success : function(data) {
                var values = $("#" + form)[0];
                for (var index = 0; index < values.length; ++index) {
                    if (values[index].className == "updateInput") {
                        if (values[index].disabled == false) {
                            values[index].disabled = true;
                            element.innerText = "修改";
                        }
                    }
                }
 /*                var form = $("#selectMockOperDefine");
                var inputs = form.find("input");
                inputs[0].value = data.id;
                form.submit(); */

            },
            error : function() {
                showErrorInfo(element,"出错啦！");
            }
        }
    };
    
    //表单转对象
    $.fn.form2object = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (this.value) {
                o[this.name] = this.value;
            }
        });
        return o;
    };

    //对象转表单
    $.fn.object2form = function (obj) {
        for (var key in obj) {
            $(this).find("input[name='" + key + "']").val(obj[key]);
            $(this).find("textarea[name='" + key + "']").val(obj[key]);
            $(this).find("select[name='" + key + "']").val(obj[key]);
        }
    };

    //将数据显示到form表单
    //$("xxxform").object2form(data);

    //从form表单获取数据
    //var data = $("xxxform").form2object();

    function showDetailDiv(element) {
        var offset = $(element).offset();
        var width = $(element).width();
        var top = offset.top;
        var left = offset.left;
        $("#detailTextarea")[0].innerText = element.innerText;
        $("#detailDiv").css({
            'top' : top - 2,
            'left' : left - 2,
            'width' : width + 4
        });
        $("#detailTextarea").css({
            'width' : width - 2
        });
        $("#detailDiv").removeClass("hiddenElement");
    }

    function hiddenDetailDiv() {
        $("#detailDiv").addClass("hiddenElement");
    }

    function deleteMethedRuleBack(element) {
        return {
            type : "post",
            success : function(data) {
                $(element).parent().parent().parent().remove();

                var array_values = new Array();
                $("#serviceMethedRules").find("input:checkbox").each(function() {
                    array_values.push($(this).attr("id"));
                });

                if(array_values.indexOf(data.methodName) == -1){
                    var options = $("#selectMockRuleNames").find("option");
                    if (options.length == 1){
                        $("#serviceMethedRules").remove();
                    }
                    for (var index = 0; index < options.length; index++) {
                        if(options[index].value == data.methodName) {
                            options[index].remove();
                            $("#selectMockRuleNames").multiselect('refresh');
                        }
                    }
                }
                checkboxAllIsNeedChange();
            },
            error : function() {
                showErrorInfo(element,"出错啦！");
            }
        }
    }

    function deleteMethedRule(element) {
        if (confirm("确认删除?")) {
            var deleteForm = $(element).parent().find("form").prevObject;
            deleteForm.ajaxSubmit(deleteMethedRuleBack(element));
        }
    }

    function submitMockTestForm() {

        var mockRules = $("#MockTestForm").serialize();
        
        var mockTestIds = [];

        var serviceId = ${mockOperDefine.mockServices[0].id} + "";

        $('input[name="mockTestCheckbox"]:checked').each(function(index) {
            mockTestIds.push($(this).val()); 
        });

        if (mockTestIds.length > 0) {
            // 取得要提交页面的URL
            var action = '<c:url value="/mvc/selectMockRuleResults"/>';
            // 创建Form  
            var form = $('<form></form>');  
            // 设置属性  
            form.attr('action', action);  
            form.attr('method', 'post');  
            // _self -> 当前页面 _blank -> 新页面  
            form.attr('target', '_self');  

            var mockRulesInput = $('<input type="text" name="mockRules" />');  
            mockRulesInput.attr('value', mockRules);  
            
            // 创建Input  
            var mockTestIdsInput = $('<input type="text" name="mockTestIds" />');  
            mockTestIdsInput.attr('value', mockTestIds);
    
            // 创建Input  
            var serviceIdInput = $('<input type="text" name="serviceId" />');  
            serviceIdInput.attr('value', serviceId);
            
            // 附加到Form
            form.append(mockRulesInput);
            form.append(mockTestIdsInput);
            form.append(serviceIdInput);
            
            // 提交表单  
            form.ajaxSubmit(submitMockTestFormBack());
        }
    }

    function submitMockTestFormBack() {
        return {
            type : "post",
            success : function(data) {
                $("#mockTestResult").html("");
                data = $.parseJSON(data);
                if(data.result == 'success') {
                    $("#mockTestResult").append("<div class='mockTestMethodRuleDiv'>" + formatJson(data.context) + "</div>");
                } else {
                    $("#mockTestResult").append("<div class='mockTestMethodRuleDiv'>" + data.context + "</div>");
                }
            },
            error : function() {
                showErrorInfo(null,"出错啦！");
            }
        }
    }

    
    function changeCheckBoxAll(element) {
        var checkStatus = element.checked;
        var checkboxs = $("#serviceMethedRules").find("input:checkbox");
        for (var index = 0;index < checkboxs.length;index ++) {
            checkboxs[index].checked = checkStatus;
        }
        refreshSelectCheckboxName();
    }

    function checkboxAllIsNeedChange() {
        var checkboxAllChecked = true;
        var checkboxs = $("#serviceMethedRules").find("input:checkbox");
        if(checkboxs != null && checkboxs.length > 0) {
            for (var index = 0;index < checkboxs.length;index ++) {
                if(checkboxs[index].checked == false) {
                    checkboxAllChecked = false;
                }
            }
        } else {
            checkboxAllChecked = false;
        }
        $("#checkboxAll")[0].checked = checkboxAllChecked;
        refreshSelectCheckboxName();
    }

    function refreshSelectCheckboxName() {
        var selectMockRuleNames = new Array();
        var checkboxs = $("#serviceMethedRules").find("input:checkbox");
        for (var index = 0;index < checkboxs.length;index ++) {
            if(checkboxs[index].checked == true) {
                selectMockRuleNames.push(checkboxs[index].id);
            }
        }
        $('#selectMockRuleNames').val(selectMockRuleNames);
        $('#selectMockRuleNames').multiselect("refresh");
    }
    
    function setCheckboxCheckValue() {
        var array_of_checked_values = $("#selectMockRuleNames").multiselect("getChecked").map(function() {
            return this.value;
        }).get();
        var checkboxs = $("#serviceMethedRules").find("input:checkbox");
        for ( var index in checkboxs) {
            checkboxs[index].checked = false;
            for ( var valuesIndex in array_of_checked_values) {
                if(checkboxs[index].id == array_of_checked_values[valuesIndex]) {
                    checkboxs[index].checked = true;
                }
            }
        }
        checkboxAllIsNeedChange();
    }
    
    function selectMockTestInfos() {
        
        var serviceId = ${mockOperDefine.mockServices[0].id} + "";
        
        // 取得要提交页面的URL
        var action = '<c:url value="/mvc/selectMockTestInfos"/>';
        // 创建Form  
        var form = $('<form></form>');  
        // 设置属性  
        form.attr('action', action);  
        form.attr('method', 'post');  
        // _self -> 当前页面 _blank -> 新页面  
        form.attr('target', '_self');
        // 创建Input  
        var serviceIdInput = $('<input type="text" name="serviceId" />');  
        serviceIdInput.attr('value', serviceId);
        
        form.append(serviceIdInput);
        
        // 提交表单  
        form.ajaxSubmit(selectMockTestInfosBack());
    }
    
    var mockTestInfos;
    
    function selectMockTestInfosBack() {
        return {
            type : "post",
            success : function(data) {
                
                $("#mockTestInfoSelect").empty();
                //转化为Json对象
                mockTestInfos = $.parseJSON(data);
                
                $.each(mockTestInfos,function(key,value) {
                    $("#mockTestInfoSelect").append("<option value='" + key + "'>" + key + "</option>");
                });
/*                 $("#mockTestInfoInput").val(""); */
                $("#mockTestInfoSelect").val("");
            },
            error : function() {
                showErrorInfo(element,"出错啦！");
            }
        }
    }
    
    function saveMockTestInfos() {
        var serviceId = ${mockOperDefine.mockServices[0].id} + "";
        
        var mockRules = $("#MockTestForm").serialize();
        
        var testSubjectTitle = $("#mockTestInfoInput").val();

        // 取得要提交页面的URL
        var action = '<c:url value="/mvc/saveMockTestInfos"/>';
        // 创建Form  
        var form = $('<form></form>');  
        // 设置属性  
        form.attr('action', action);  
        form.attr('method', 'post');  
        // _self -> 当前页面 _blank -> 新页面  
        form.attr('target', '_self');
        // 创建Input  
        var serviceIdInput = $('<input type="text" name="serviceId" />');  
        serviceIdInput.attr('value', serviceId);
        
        var mockRulesInput = $('<input type="text" name="mockRules" />');  
        mockRulesInput.attr('value', mockRules);
        
        var mockTestInfoInput = $('<input type="text" name="testSubjectTitle" />');  
        mockTestInfoInput.attr('value', testSubjectTitle);  
        
        form.append(serviceIdInput);
        form.append(mockRulesInput);
        form.append(mockTestInfoInput);
        
        // 提交表单  
        form.ajaxSubmit(saveMockTestInfosBack());
    }
    
    function saveMockTestInfosBack() {
        return {
            type : "post",
            success : function(data) {
                selectMockTestInfos();
            },
            error : function() {
                showErrorInfo(element,"出错啦！");
            }
        }
    }
    
    function mockTestSelectChange(element) {
        //赋值select参数到input
        $("#mockTestInfoInput").val(element.value);
        //移除手工添加的所有行
        var mockTestInfoValues = mockTestInfos[element.value];
        $('#MockTestKeyValueTable').children().find('.MockTestAddTrClass').remove();
        
        for (var index = 0; index < mockTestInfoValues.length; index++) {
            if(mockTestInfoValues[index].parKey == 'methodName') {
                continue;
            }
            var tempTr = addMockTestTr();
            tempTr.find("[name='mockTestKey']").val(mockTestInfoValues[index].parKey);
            tempTr.find("[name='mockTestValue']").val(mockTestInfoValues[index].parValue);
            tempTr.find("[name='mockTestType']").val(mockTestInfoValues[index].parType);
        }
    }
    
    function sortableStop(element){
        showLoadingIcon(element);
        var tr = $(element).find("tr");
        var trlength = tr.length;
        var doneSize = 1;
        $.each(tr, function (key,value) {
            var idDiv = $(value).find("div[id='id']");
            var serviceIdDiv = $(value).find("div[id='serviceId']");
            var execSortDiv = $(value).find("div[id='execSort']");
            var index = key + 1;
            $.ajax({
                type: "post",
                url: "updateOrInsertServiceMethedRule",
                data: "id=" + idDiv[0].innerHTML + "&execSort=" + index + "&serviceId=" + serviceIdDiv[0].innerHTML,
                success: function (data) {
                    execSortDiv[0].innerHTML = index;
                    if(doneSize < trlength){
                        doneSize ++ ;
                    } else {
                        hiddenLoadingIcon(element);
                    }
                },
                error : function() {
                    showErrorInfo(element,"出错啦！");
                    if(doneSize < trlength){
                        doneSize ++ ;
                    } else {
                        hiddenLoadingIcon(element);
                    }
                }
            });
        });
    }
    
    $(document).ready(function() {
        
        $("#serviceMethedRules").sortable({
            stop: function( event, ui ) {
                sortableStop(this);
            }
        });
        
        var w1, w2, outer, inner;
        outer = document.createElement('div');
        inner = document.createElement('div');
        outer.appendChild(inner);
    
        outer.style.display = 'block';
        outer.style.position = 'absolute';
        outer.style.width = '50px';
        outer.style.height = '50px';
        outer.style.overflow = 'hidden';
    
        inner.style.height = '100px';
        inner.style.width = 'auto';
    
        document.body.appendChild(outer);
    
        w1 = inner.offsetWidth;
        outer.style.overflow = 'scroll';
    
        w2 = inner.offsetWidth;
    
        if (w1 === w2) {
            w2 = outer.clientWidth;
        }
    
        document.body.removeChild(outer);
        var scrollbarWidth = w1 - w2;
        
        $(".fixedHeader").css({
            'padding-right' : scrollbarWidth
        });
        
        $("#selectMockRuleNames").multiselect({
            noneSelectedText: "选择测试项", 
            checkAllText: "全选", 
            uncheckAllText: '清空',
            selectedList:2
        });
        
        var height = $(document).height();
        $("#bg").css({
            'height' : height
        });

        var registryId = ${mockOperDefine.mockServices[0].registryId} + "";

        var registryRadios = $(".RegistryRadio");

        for (var index = 0; index < registryRadios.length; ++index) {
            if (registryRadios[index].value == registryId) {
                registryRadios[index].checked = true;
            }
        }

        var protocolId = ${mockOperDefine.mockServices[0].protocolId} + "";

        var protocolRadios = $(".ProtocolRadio");

        for (var index = 0; index < protocolRadios.length; ++index) {
            if (protocolRadios[index].value == protocolId) {
                protocolRadios[index].checked = true;
            }
        }

    });
</script>
</head>
<body>
    <form id="selectMockOperDefine" action='<c:url value="/mvc/selectMockOperDefine"/>' method="post">
        <input type="hidden" name="selectid" value="-1" />
    </form>
    <div id="bg" class="bg" onclick="hiddenDivTable()"></div>
    <div id="jquery_bg" class="bg"></div>
    <div class="holder" style="display: none;"></div>
    <form id='mockServicesForm' action='<c:url value="/mvc/updateOrInsertMockService"/>' method="post">
    <table class="tableAutoWidth">
        <thead>
            <tr class="itemtr">
                <td colspan="13">
                    <h3 align="left">配置服务:${mockOperDefine.mockServices.get(0).serviceInterface }</h3>
                </td>
            </tr>
            <tr class="queryitem">
                <th style="display: none">ID</th>
                <th>注册中心</th>
                <th>协议</th>
                <th>服务名称</th>
                <th>应用名称</th>
                <th>Group</th>
                <th>Version</th>
                <th>超时时间(毫秒)</th>
                <th>重试次数</th>
                <th>服务状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody id="itemContainer">
            <c:if test="${not empty mockOperDefine.mockServices}">
                <c:forEach items="${mockOperDefine.mockServices}" var="mockServices">
                    <tr class="itemtr">
                        <td style="display: none" class="updateTd"><input disabled="true" class="updateInput" type="text" name="id" readonly="readonly" value="${mockServices.id}" /> <input name="id" type="hidden" value="${mockServices.id}" /></td>
                        <td class="updateTd" style="display: none"><input disabled="true" class="updateInput" type="text" name="registryId" onclick='showDivTable("Registry")' id="RegistryId" readonly="readonly" value="${mockServices.registryId }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" onclick='showDivTable("Registry")' id="RegistryName" readonly="readonly" value="${mockServices.registryProtocol }" /></td>
                        <td class="updateTd" style="display: none"><input disabled="true" class="updateInput" type="text" name="protocolId" onclick='showDivTable("Protocol")' id="ProtocolId" readonly="readonly" value="${mockServices.protocolId }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" onclick='showDivTable("Protocol")' id="ProtocolName" readonly="readonly" value="${mockServices.protocolName }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" name="serviceInterface" value="${mockServices.serviceInterface }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" name="applicationName" value="${mockServices.applicationName }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" name="groupName" value="${mockServices.groupName }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" name="version" value="${mockServices.version }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" name="timeout" value="${mockServices.timeout }" /></td>
                        <td class="updateTd"><input disabled="true" class="updateInput" type="text" name="retries" value="${mockServices.retries }" /></td>
                        <td id="serviceStatus" class="serviceStatusTd">
                            <input style="display: none" type="text" name="serviceStatus" readonly="readonly" value="${mockServices.serviceStatus}" />
                            <div class="buttonswitch">
                                <input class="buttonswitch-checkbox" id="onoffswitch" type="checkbox" 
                                    <c:if test='${mockServices.serviceStatus == "running"}'>checked</c:if> onclick='runningOrStopService(this)'>
                                <label class="buttonswitch-label" for="onoffswitch">
                                    <span class="buttonswitch-inner" data-on="running" data-off="stop"></span>  
                                    <span class="buttonswitch-switch"></span>  
                                </label>
                            </div>
                        </td>
                        <td class="buttonTd">
                            <button class="updateButton mockServicesButton" type="button" onclick='updateOneRow(this,"mockServicesForm")' value="${mockServices.id}">${mockServices.id == null ? "新增" : "修改"}</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
    </form>
    <div class='intervalDiv'></div>
    <table id="MethedTable">
        <thead>
            <tr class="itemtr">
                <td colspan="6">
                <span class="spanleft">
                    <h3 align="left">服务Mock规则</h3>
                </span>
                <span class="spanright">
                    <button id="testServiceMethedRuleButton" <c:if test='${mockOperDefine.mockServices[0].id == null}'>disabled='disabled'</c:if> class="addButton mockServicesButton" onclick='openMockTestTable("MockTest");selectMockTestInfos()' type="button">测试</button>
                </span>
                <span class="spanright" style="margin: 0px 3px;">
                    <select id="selectMockRuleNames" multiple="multiple" onchange="setCheckboxCheckValue()">
                        <c:forEach items="${mockOperDefine.mockRuleNames}" var="ruleName">
                            <option value="${ruleName}">${ruleName}</option>
                        </c:forEach>
                    </select>
                </span>
                </td>
                <td class="buttonTd">
                    <button id="addServiceMethedRuleButton" <c:if test='${mockOperDefine.mockServices[0].id == null}'>disabled='disabled'</c:if> class="addButton mockServicesButton" onclick='openMethedUpdateTable(this,"MethedAdd")' type="button">新增</button>
                </td>
            </tr>
            <tr class="queryitem">
                <th style="display: none" class="divWidth30">ID</th>
                <th style="display: none" class="divWidth100">服务ID</th>
                <th class="divWidth300">方法名称</th>
                <th class="divWidth300">条件脚本</th>
                <th class="divWidth300">返回报文</th>
                <th class="divWidth150">更新时间</th>
                <th class="divWidth100">执行优先级</th>
                <th class="checkboxtd"><input type="checkbox" id="checkboxAll" name="checkboxAll" onclick="changeCheckBoxAll(this)"/></th>
                <th class="buttonTd">操作</th>
            </tr>
            <tr class="itemtr hiddenElement" id="MethedAddTr">
                <td style="display: none" class="divWidth30">
                    <div class="updateDiv2Row" id="id"></div>
                </td>
                <td style="display: none" class="divWidth100">
                    <div class="updateDiv2Row" id="serviceId"></div>
                </td>
                <td class="divWidth300">
                    <div ondblclick="showDetailDiv(this)" class="updateDiv2Row" id="methodName"></div>
                </td>
                <td class="divWidth300">
                    <div ondblclick="showDetailDiv(this)" class="updateDiv2Row" id="whenScript"></div>
                </td>
                <td class="divWidth300">
                    <div ondblclick="showDetailDiv(this)" class="updateDiv2Row" id="returnMessage"></div>
                </td>
                <td class="divWidth150">
                    <div class="updateDiv2Row" id="updateTime"></div>
                </td>
                <td class="divWidth100">
                    <div class="updateDiv2Row" id="execSort"></div>
                </td>
                <td class="checkboxtd">
                    <input name="mockTestCheckbox" id="" type="checkbox" onchange="checkboxAllIsNeedChange()" value="" />
                </td>
                <td class="buttonTd">
                    <button class="updateButton mockServicesButton" onclick='openMethedUpdateTable(this,"MethedAdd")' type="button">修改</button>
                    <form id='deleteServiceMethedRule' action='<c:url value="/mvc/deleteServiceMethedRule"/>' method="post">
                        <input type="hidden" name="id" value="" /> 
                        <input type="hidden" name="serviceId" value="" />
                        <input type="hidden" name="methodName" value="" />
                        <button class="deleteButtonOneRow mockServicesButton" type="button" onclick="deleteMethedRule(this)">删除</button>
                    </form>
                </td>
            </tr>
        </thead>
        <c:if test="${not empty mockOperDefine.serviceMethedRules}">
            <tbody id="serviceMethedRules">
                <c:forEach items="${mockOperDefine.serviceMethedRules}" var="methed">
                    <tr class="itemtr" id="MethedTr${methed.id}">
                        <td style="display: none" class="divWidth30">
                            <div class="updateDiv2Row" id="id">${methed.id}</div>
                        </td>
                        <td style="display: none" class="divWidth100">
                            <div class="updateDiv2Row" id="serviceId">${methed.serviceId}</div>
                        </td>
                        <td class="divWidth300">
                            <div ondblclick="showDetailDiv(this)" class="updateDiv2Row" id="methodName">${methed.methodName}</div>
                        </td>
                        <td class="divWidth300">
                            <div ondblclick="showDetailDiv(this)" class="updateDiv2Row" id="whenScript">${methed.whenScript}</div>
                        </td>
                        <td class="divWidth300">
                            <div ondblclick="showDetailDiv(this)" class="updateDiv2Row" id="returnMessage">${methed.returnMessage}</div>
                        </td>
                        <td class="divWidth150">
                            <div class="updateDiv2Row" id="updateTime">${dateUtil.date2String(methed.updateTime,"yyyy-MM-dd HH:mm:ss")}</div>
                        </td>
                        <td class="divWidth100">
                            <div class="updateDiv2Row" id="execSort">${methed.execSort}</div>
                        </td>
                        <td class="checkboxtd">
                            <input name="mockTestCheckbox" id="${methed.methodName}" type="checkbox" onchange="checkboxAllIsNeedChange()" value="${methed.id}"/>
                        </td>
                        <td class="buttonTd">
                            <button class="updateButton mockServicesButton" onclick='openMethedUpdateTable(this,"MethedAdd")' value="${methed.id}" type="button">修改</button>
                            <form id='deleteServiceMethedRule' action='<c:url value="/mvc/deleteServiceMethedRule"/>' method="post">
                                <input type="hidden" name="id" value="${methed.id}" /> 
                                <input type="hidden" name="serviceId" value="${methed.serviceId}" />
                                <input type="hidden" name="methodName" value="${methed.methodName}" />
                                <button class="deleteButtonOneRow mockServicesButton" type="button" onclick="deleteMethedRule(this)">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
    <div id='MethedAddDiv' class='hiddenElement popdiv'>
        <div class='poptopdiv'>
            <form id="MethedAddForm" action="<c:url value="/mvc/updateOrInsertServiceMethedRule"/>" method="post">
                <table id="MethedAddFormTable" class="tableAutoWidth">
                    <tr>
                        <td colspan="4">
                            <h3 align="left">配置服务Mock规则</h3>
                        </td>
                    </tr>
                    <tr>
                        <td class="queryitem">方法名称</td>
                        <td><input name="methodName" type="text" /> <input type="hidden" name="id" /> <input type="hidden" name="serviceId" id="addServiceMethedRuleId" value="${mockOperDefine.mockServices[0].id}" /></td>
                    </tr>
                    <tr>
                        <td class="queryitem">条件脚本</td>
                        <td class="width85" colspan="3"><div><textarea name="whenScript"></textarea></div></td>
                    </tr>
                    <tr>
                        <td class="queryitem">返回报文</td>
                        <td class="width85" colspan="3"><textarea name="returnMessage" onkeydown="keydownFn(event,this,formatContent)" onkeyup="keyUpFn(event,this)"></textarea></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class='intervalDiv'></div>
        <div class='popbottomdiv'>
            <input id='addOrUpdateMethedAddButton' onclick='addOrUpdateMethedTable()' type='button' class="divbutton divbuttonRight" value='提交'> <input id='closeMethedAddButton' onclick='hiddenDivTable()' type='button' class="divbutton divbuttonLeft" value='关闭'>
        </div>
    </div>
    <div id='RegistryDiv' class='hiddenElement popdiv'>
        <div class='poptopdiv'>
            <table id="RegistryTable" class="tableAutoWidth">
                <thead>
                    <tr class="itemtr hiddenElement" id="registryTr" ondblclick='radioChange(this,"Registry")'>
                        <td class="buttonTd"><input type="radio" onclick='radioChange(this,"Registry")' class="RegistryRadio" name="RegistryRadio"></td>
                        <td style="display: none"></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="5">
                            <h3 align="left">选择注册中心</h3>
                        </td>
                    </tr>
                    <tr class="queryitem">
                        <th style="width: 69px;"></th>
                        <th style="display: none">ID</th>
                        <th>注册中心</th>
                        <th>地址</th>
                        <th>超时时间(毫秒)</th>
                        <th>更新时间</th>
                    </tr>
                </thead>
                <tbody id="itemContainer">
                    <c:if test="${not empty mockOperDefine.registryConfigs}">
                        <c:forEach items="${mockOperDefine.registryConfigs}" var="registry">
                            <tr class="itemtr" id="registryTr" ondblclick='radioChange(this,"Registry")'>
                                <td class="buttonTd"><input type="radio" onclick='radioChange(this,"Registry")' id="RegistryRadio${registry.id}" class="RegistryRadio" name="RegistryRadio" value="${registry.id}"></td>
                                <td style="display: none"><c:out value="${registry.id}" /></td>
                                <td><c:out value="${registry.registryProtocol }" /></td>
                                <td><c:out value="${registry.registryAddress }" /></td>
                                <td><c:out value="${registry.registryTimeout }" /></td>
                                <td><c:out value='${dateUtil.date2String(registry.updateTime,"yyyy-MM-dd HH:mm:ss")}' /></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <form id="addRegistryForm" action="<c:url value="/mvc/updateOrInsertRegistryConfig"/>" method="post">
                <table id="addRegistryTable" class="hiddenElement tableAutoWidth" style="margin-top: 6px">
                    <tr>
                        <td class="queryitem">注册中心</td>
                        <td><input name="registryProtocol" type="text" /></td>
                        <td class="queryitem">地址</td>
                        <td><input name="registryAddress" type="text" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="queryitem">超时时间(毫秒)</td>
                        <td colspan="3"><input name="registryTimeout" type="text" /></td>
                        <td class="queryButtonTd" onclick='insertOneRow("Registry")' id="addRegistryButton">
                            <button type="button">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class='intervalDiv'></div>
        <div class='popbottomdiv'>
            <input id='showRegistryButton' onclick='showAddTable("Registry")' type='button' class="divbutton divbuttonRight" value='新增'> <input id='closeRegistryButton' onclick='hiddenDivTable()' type='button' class="divbutton divbuttonLeft" value='关闭'>
        </div>
    </div>
    <div id='ProtocolDiv' class='hiddenElement popdiv'>
        <div class='poptopdiv'>
            <table id="ProtocolTable" class="tableAutoWidth">
                <thead>
                    <tr class="itemtr hiddenElement" id="protocolTr" ondblclick='radioChange(this,"Protocol")'>
                        <td class="buttonTd"><input onclick='radioChange(this,"Protocol")' type="radio" class="ProtocolRadio" name="ProtocolRadio"></td>
                        <td style="display: none"></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <h3 align="left">选择协议</h3>
                        </td>
                    </tr>
                    <tr class="queryitem">
                        <th style="width: 69px;"></th>
                        <th style="display: none">ID</th>
                        <th>协议</th>
                        <th>端口</th>
                        <th>更新时间</th>
                    </tr>
                </thead>
                <tbody id="itemContainer">
                    <c:if test="${not empty mockOperDefine.protocolConfigs}">
                        <c:forEach items="${mockOperDefine.protocolConfigs}" var="protocol">
                            <tr class="itemtr" id="protocolTr" ondblclick='radioChange(this,"Protocol")'>
                                <td class="buttonTd"><input onclick='radioChange(this,"Protocol")' type="radio" id="ProtocolRadio${protocol.id}" class="ProtocolRadio" name="ProtocolRadio" value="${protocol.id}"></td>
                                <td style="display: none"><c:out value="${protocol.id}" /></td>
                                <td><c:out value="${protocol.protocolName }" /></td>
                                <td><c:out value="${protocol.protocolPort }" /></td>
                                <td><c:out value='${dateUtil.date2String(protocol.updateTime,"yyyy-MM-dd HH:mm:ss")}' /></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <form id="addProtocolForm" action="<c:url value="/mvc/updateOrInsertProtocolConfig"/>" method="post">
                <table id="addProtocolTable" class="hiddenElement tableAutoWidth" style="margin-top: 6px">
                    <tr>
                        <td class="queryitem">协议</td>
                        <td><input name="protocolName" type="text" /></td>
                        <td class="queryitem">端口</td>
                        <td><input name="protocolPort" type="text" /></td>
                        <td class="queryButtonTd" onclick='insertOneRow("Protocol")' id="addProtocolButton">
                            <button type="button">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class='intervalDiv'></div>
        <div class='popbottomdiv'>
            <input id='showProtocolButton' onclick='showAddTable("Protocol")' type='button' class="divbutton divbuttonRight" value='新增'> <input id='closeProtocolButton' onclick='hiddenDivTable()' type='button' class="divbutton divbuttonLeft" value='关闭'>
        </div>
    </div>

    <div id='MockTestDiv' class='hiddenElement popdiv' >
        <div class='poptopdiv' >
            <table id="MockTestTable" class="tableAutoWidth">
                <thead>
                    <tr>
                        <td colspan="2">
                            <div style="position: relative;display: inline-table;">
                                <h3 align="left">服务Mock规则测试</h3>
                            </div>
                            <div style="position: relative;display: inline;">
                                <span style="width: 18px;position: absolute;">
                                    <select id="mockTestInfoSelect" onchange="mockTestSelectChange(this)" style="width: 218px;" ></select>
                                    <input id="mockTestInfoInput" name="box" style="width: 200px; position: absolute; left: 0px;">
                                </span>
                            </div>
                            <div style="position: absolute;display: inline;margin-left: 221px;">
                                <button id="" class="addButton mockServicesButton" onclick='saveMockTestInfos()' type="button" >保存</button>
                            </div>
                        </td>
                    </tr>
                </thead>
                <tbody id="itemContainer">
                    <tr>
                        <td class="tableTd210">
                            <div class="fixedHeader">
                                <table class="tableTdTable">
                                    <colgroup>
                                        <col />
                                        <col />
                                        <col />
                                        <col class="addTrTd"/>
                                    </colgroup>
                                    <thead>
                                        <tr class="queryitem">
                                            <th>key</th>
                                            <th>value</th>
                                            <th>type</th>
                                            <th>
                                                <div align="center">
                                                    <input type="button" class="addTrButton mockServicesButton" onclick="addMockTestTr()" />
                                                </div>
                                            </th>
                                        </tr>
                                        <tr id="MockTestAddTr" class="hiddenElement itemtr MockTestAddTrClass">
                                            <td class="updateTd"><input name="mockTestKey" class="updateInput" type="text" value="" /></td>
                                            <td class="updateTd"><input name="mockTestValue" class="updateInput" type="text" value="" /></td>
                                            <td class="updateTd">
                                                <select name="mockTestType" class="tdSelect">
                                                    <option value="json">json</option>
                                                    <option value="object">object</option>
                                                </select>
                                            </td>
                                            <td class="addTrTd" onclick="deleteMockTestTr(this)"><input type="button" class="deleteTrButton mockServicesButton" /></td>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="tableOverflow189">
                                <form id="MockTestForm">
                                    <table id="MockTestKeyValueTable" class="tableTdTable">
                                        <colgroup>
                                            <col />
                                            <col />
                                            <col />
                                            <col class="addTrTd"/>
                                        </colgroup>
                                        <tbody>
                                            <tr class="itemtr">
                                                <td class="updateTd">
                                                    <input name="mockTestKey" id="mockTestMethodNameKey" class="updateInput" type="text" value="methodName" readonly="readonly"/>
                                                </td>
                                                <td class="updateTd">
                                                    <select name="mockTestValue" id="mockTestMethodNameValue" class="tdSelect" onchange="changeMockTestMethodNameValue()"></select>
                                                </td>
                                                <td class="updateTd">
                                                    <select name="mockTestType" class="tdSelect">
                                                        <option value="json">json</option>
                                                        <option value="object">object</option>
                                                    </select>
                                                </td>
                                                <td class="addTrTd"></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </td>
                        <td rowspan="2" class="divTd">
                            <div class="fixedHeader">
                                <table class="tableTdTable">
                                    <colgroup>
                                        <col class="queryitem" />
                                    </colgroup>
                                    <thead>
                                        <tr class="queryitem">
                                            <th>mockTestMethodRule</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="tableOverflow419">
                                <table class="tableTdTable419">
                                    <tbody>
                                        <tr>
                                            <td id="mockTestMethodRule" class="divTd"></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tableTd210">
                            <div class="fixedHeader">
                                <table class="tableTdTable">
                                    <colgroup>
                                        <col class="queryitem"/>
                                    </colgroup>
                                    <thead>
                                        <tr class="queryitem">
                                            <th>mockTestResult</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="tableOverflow189">
                                <table class="tableTdTable189">
                                    <tbody>
                                        <tr>
                                            <td id="mockTestResult" class="divTd" ></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='intervalDiv'></div>
        <div class='popbottomdiv'>
            <input id='showMockTestButton' onclick='submitMockTestForm()' type='button' class="divbutton divbuttonRight" value='测试'> <input id='closeProtocolButton' onclick='hiddenDivTable()' type='button' class="divbutton divbuttonLeft" value='关闭'>
        </div>
    </div>

    <div id='detailDiv' onmouseout="hiddenDetailDiv()" class='hiddenElement detailDiv'>
        <div class='detailTopDiv'>
            <pre id="detailTextarea" readonly="readonly" class="textareaDiv"></pre>
        </div>
    </div>
    
    <div id="query_hint" class="query_hint hiddenElement">
        <img src="../css/images/waiting.gif" />正在执行，请稍等...
    </div>
</body>
</html>