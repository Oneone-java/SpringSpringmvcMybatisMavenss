<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  
	SpringMVC 处理静态资源:
	1. 为什么会有这样的问题:
	优雅的 REST 风格的资源URL 不希望带 .html 或 .do 等后缀
	若将 DispatcherServlet 请求映射配置为 /, 
	则 Spring MVC 将捕获 WEB 容器的所有请求, 包括静态资源的请求, SpringMVC 会将他们当成一个普通请求处理, 
	因找不到对应处理器将导致错误。
	2. 解决: 在 SpringMVC 的配置文件中配置 <%--<mvc:default-servlet-handler/>--%>-->
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){

		$(".deletes").click(function(){
			var href = $(this).attr("href");
			$("form").attr("action", href).submit();			
			return false;
		});

		$("#guoji").change(function (){
			window.location.href="emps?locale="+$(this).val();
		});

		$("#pageSizes").change(function (){
			window.location.href="emps?pageNo=1&pageSize=" +$(this).val();
		});


	});


</script>
</head>
<body>


<%--若要使用delete请求则需要先用post请求转换成delete请求--%>
<form action="" method="POST">
	<input type="hidden" name="_method" value="DELETE"/>
</form>


<select id="guoji">

	<option value="zh_CN" <c:if test="${locale == 'zh_CN'}">selected</c:if> ><fmt:message key="employee.Chinese"/></option>
	<option value="en_US" <c:if test="${locale == 'en_US'}">selected</c:if> ><fmt:message key="employee.English"/></option>

</select>


<br><br>
	<%--<c:if test="${empty requestScope.employee }">
		<fmt:message key="employee.NotUser"/>
	</c:if>--%>
<br><br>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th><fmt:message key="employee.headUrl"/></th>
				<th><fmt:message key="employee.lastName"/></th>
				<th><fmt:message key="employee.email"/></th>
				<th><fmt:message key="employee.gender"/></th>
				<th><fmt:message key="employee.departmentName"/></th>
				<th><fmt:message key="employee.birth"/></th>
				<th><fmt:message key="employee.salary"/></th>
				<th><fmt:message key="employee.Edit"/></th>
				<th><fmt:message key="employee.Delete"/></th>
			</tr>



			<c:forEach items="${page.list }" var="emp" >
				<tr>
					<td>${emp.id }</td>
					<c:set value="${emp.headUrl} " var="chh" scope="request"></c:set>
					<td><img  alt="" height="60px" width="60px" src="headshow?path=<%=java.net.URLEncoder.encode((String)request.getAttribute("chh"))%>"/></td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender == 0 ? '女' : '男' }</td>
					<td>${emp.department.departmentName }</td>
					<td>${emp.birth }</td>
					<td>${emp.salary }</td>
					<td><a href="emp/${emp.id}"><fmt:message key="employee.Edit"/></a></td>
					<td><a class="deletes" href="emp/${emp.id}"><fmt:message key="employee.Delete"/></a></td>
				</tr>
			</c:forEach>
		</table>

	<br><br>

		<%--<a href="<%=request.getContextPath()%>/emps?pageNo=1&pageSize=${page.pageSize}">首页</a>
		<c:if test="${page.pageNo != 1}">
		<a href="<%=request.getContextPath()%>/emps?pageNo=${page.pageNo - 1}&pageSize=${page.pageSize}">上页</a>
		</c:if>
		<c:if test="${page.pageNo != page.pageCount}">
		<a href="<%=request.getContextPath()%>/emps?pageNo=${page.pageNo + 1}&pageSize=${page.pageSize}">下页</a>
		</c:if>
		<a href="<%=request.getContextPath()%>/emps?pageNo=${page.pageCount }&pageSize=${page.pageSize}">尾页</a>--%>

	<!-- 在表达式里面 requestScope 可以默认不写 -->
	<a href="<%=request.getContextPath()%>/emps?pageNo=1&pageSize=${page.pageSize}">首页</a>&nbsp;
	<c:if test="${page.pageNo -1!= 0}">
		<a href="<%=request.getContextPath()%>/emps?pageNo=${page.pageNo -1}&pageSize=${page.pageSize}">上页</a>&nbsp;
	</c:if>
	<c:if test="${page.pageNo +1<= page.pageCount}">
		<a href="<%=request.getContextPath()%>/emps?pageNo=${page.pageNo +1}&pageSize=${page.pageSize}">下页</a>&nbsp;
	</c:if>
	<a href="<%=request.getContextPath()%>/emps?pageNo=${page.pageCount}&pageSize=${page.pageSize}">尾页</a>&nbsp;

	<br><br>

		<select id="pageSizes">
			<option value="3" <c:if test="${page.pageSize == 3}">selected</c:if>>3</option>
			<option value="4" <c:if test="${page.pageSize == 4}">selected</c:if>>4</option>
			<option value="5" <c:if test="${page.pageSize == 5}">selected</c:if>>5</option>
		</select>

	<br><br>
	
	<a href="emp"><fmt:message key="employee.Add"/></a>

<br><br>
<br><br>

</body>
</html>