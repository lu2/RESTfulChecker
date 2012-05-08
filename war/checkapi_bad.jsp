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
    <script type="text/javascript" src="../scripts.js"></script>
  </head> 
  <body>
  <h1>RESTfulChecker</h1>
  <h3>Problem: cannot communicate with API (perhaps wrong API entry request). Bellow is response from which you should obtain details about how to correct API entry point information.</h3>
    <form:form commandName="apiEntry" action="./">
      <table>
        <tr>
          <td>API entry URL:</td>
          <td><form:input path="url" /> </td>
          <td><input type="submit" value="Run Tests" /></td>
        </tr>
      </table>
      <p>
      <input type="button" onclick="displayAddHeader();" value="Add header"/>
      </p>
      <table id="headersTable">
      <tr style="display:none;"><td style="display:none;"> </td><td style="display:none;"> </td></tr>
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
      
      <table id="questionnairesTable">
      <tr>
      <td><form:label path="questionnaires.q1a">${apiEntry.questionnaires.q1}</form:label></td>
      <td><form:checkbox path="questionnaires.q1a" id="questionnaires.q1a" /></td>
      </tr>
      <tr>
      <td><form:label path="questionnaires.q2a">${apiEntry.questionnaires.q2}</form:label></td>
      <td><form:checkbox path="questionnaires.q2a" id="questionnaires.q2a" /></td>
      </tr>
      <tr>
      <td><form:label path="questionnaires.q3a">${apiEntry.questionnaires.q3}</form:label></td>
      <td><form:checkbox path="questionnaires.q3a" id="questionnaires.q3a" /></td>
      </tr>
      <tr>
      <td><form:label path="questionnaires.q11a">${apiEntry.questionnaires.q11}</form:label></td>
      <td><form:checkbox path="questionnaires.Q11a" id="questionnaires.q11a" /></td>
      </tr>
      <tr>
      <td><form:label path="questionnaires.q12a">${apiEntry.questionnaires.q12}</form:label></td>
      <td><form:checkbox path="questionnaires.q12a" id="questionnaires.q12a" /></td>
      </tr>
      <tr>
      <td><form:label path="questionnaires.q13a">${apiEntry.questionnaires.q13}</form:label></td>
      <td><form:checkbox path="questionnaires.q13a" id="questionnaires.q13a" /></td>
      </tr>
      </table>
    </form:form>
    
    <h2>Response:</h2>
    <p><c:out value="${apiEntry.responseCode}"/> <c:out value="${apiEntry.responseMessage}"/></p>
    <table class="responseHeaders">
    <c:forEach items="${apiEntry.responseHeaders}" var="responseHeader">
      <tr>
        <td><c:out value="${responseHeader.headerKey}"/></td>
        <td><c:out value="${responseHeader.headerValue}"/></td>
      </tr>
    </c:forEach>
    </table>
    <p><textarea><c:out value="${apiEntry.responseBody}"/></textarea></p>
    
  </body>
</html>