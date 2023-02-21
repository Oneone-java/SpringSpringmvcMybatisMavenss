<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<%--<form action="testConversionServiceConverer" method="POST">
    <!-- lastname-email-gender-department.id 例如: GG-gg@atguigu.com-0-105 -->
    crud.Employee: <input type="text" name="employee"/>
    <input type="submit" value="Submit"/>
</form>--%>
<br><br>

<br><br>
<%--@elvariable id="employee" type="crud"--%>
<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee" enctype="multipart/form-data">

    <c:if test="${employee.id != null }">

        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT"/>
        <c:set value="${emp.headUrl} " var="xl" scope="request"></c:set>
        <td><img  alt="" height="60px" width="60px" src="headshow?path=<%=java.net.URLEncoder.encode((String)request.getAttribute("xl"))%>"/></td>
    </c:if>
    <br>

    <fmt:message key="employee.headUrl"/>:<input type="file" name="head"/>
    <form:errors path="headUrl"/>
    <br>
    <c:if test="${employee.id == null }">
        <!-- path 属性对应 html 表单标签的 name 属性值 -->
        <fmt:message key="employee.lastName"/>: <form:input path="lastName"/>
        <form:errors path="lastName"/>
    </c:if>

    <br>
    <fmt:message key="employee.email"/>: <form:input path="email"/>
    <form:errors path="email"/>

    <br>
    <fmt:message key="employee.gender"/>: <form:radiobuttons path="gender" items="${genders }"/>
    <form:errors path="gender"/>

    <br>
    <fmt:message key="employee.departmentName"/>: <form:select path="department.id" items="${departments }" itemValue="id" itemLabel="departmentName"/>
    <form:errors path="department"/>
    <br>

    <fmt:message key="employee.birth"/>: <form:input path="birth"/>
    <form:errors path="birth"/>
    <br>

    <fmt:message key="employee.salary"/>: <form:input path="salary"/>
    <form:errors path="salary"/>

    <input type="submit" value="<fmt:message key="employee.Submit"/>"/>

</form:form>

</body>
</html>