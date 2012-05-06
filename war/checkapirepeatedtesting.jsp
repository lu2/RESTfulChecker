<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head> 
    <meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
    <title>RESTfulChecker</title>
    <link type="text/css" rel="stylesheet" href="../style.css"/>
    <script type="text/javascript">
function displayAddHeader() {
  var headersTable = document.getElementById("headersTable");
  var rows = headersTable.getElementsByTagName("tr");
  var nextHeaderIndex = (rows.length);
  var y = document.createElement('tr');
  y.innerHTML = '<td><input id="requestHeaders.inUse'+nextHeaderIndex+'" name="requestHeaders['+nextHeaderIndex+'].inUse" type="checkbox" checked="yes"/></td><td class="headerField"><input id="requestHeaders.headerKey'+nextHeaderIndex+'" name="requestHeaders['+nextHeaderIndex+'].headerKey" type="text"/></td><td class="headerField">:</td><td class="headerField"><input id="requestHeaders.headerValue'+nextHeaderIndex+'" name="requestHeaders['+nextHeaderIndex+'].headerValue" type="text"/></td>';
  headersTable.appendChild(y);
}
function toggleVisibility(item) {
	if (item.style.visibility == '') item.style.visibility = 'visible'; 
	else item.style.visibility = '';
}
  </script>
  </head> 
  <body>
  <h1>RESTfulChecker</h1>
  <p>${apiEntry.message}</p>
    <form:form commandName="apiEntry" >
      <table>
        <tr>
          <td>Insert API entry URL:</td>
          <td><form:input path="url" /> </td>
          <td><input type="submit" value="check this" /></td>
        </tr>
      </table>
      <input type="button" onclick="displayAddHeader();" value="Add header"/>
      <table id="headersTable">
      <c:set var="lastHeaderIndex" value="-1"/>
      <c:forEach items="${apiEntry.requestHeaders}" varStatus="status" var="requestHeader">
        <tr>
          <td><form:checkbox path="requestHeaders[${status.index}].inUse" /> </td>
          <td class="headerField"><form:input path="requestHeaders[${status.index}].headerKey" /></td>
          <td>:</td>
          <td class="headerField"><form:input path="requestHeaders[${status.index}].headerValue" /></td>
        </tr>
        <c:set var="lastHeaderIndex" value="${status.index}"/>
      </c:forEach>
      </table>
      <table>
      <tr>
      	<td><form:label path="maxSiblings">max siblings:</form:label></td>
      	<td><form:input path="maxSiblings"/></td>
      </tr>
      <tr>
      	<td><form:label path="baseUrl">base Url:</form:label></td>
      	<td><form:input path="baseUrl"/></td>
      </tr>
      </table>
      
    </form:form>
    
  </body>
</html>