<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>add</title>
    <script src="../../js/jquery-1.7.1.min.js"></script>
    <script src="../../js/jquery-ui-1.8.17.custom.min.js"></script>
</head>
<body>
<form th:th:action="@{gis/addPram}" method="POST">
    <table>
        <tr>
            <td>选择父类：<select style="width: 200px;"></select></td>
        </tr>
        <tr><td>值：<input type="text" value=""/></td></tr>
        <tr>
            <td><input type="button" value="增加" onclick="add();"/>
                <input type="button" value="删除" onclick="del();"/>
                <input type="button" value="提交" onclick="sub();"/>
            </td>
        </tr>
    </table>
</form>
<script>
    $(document).ready(function(){

    });

    function add() {
        $("table").append("<tr><td>值：<input type=text value=''/></td></tr>");
    }

    function sub() {
        $("form").submit();
    }
    function del() {
        $("table tr:last").remove();
    }
</script>
</body>
</html>