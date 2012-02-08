<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head> 
    <meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
    <title>RESTfulChecker</title> 
  </head> 
  <body>
  <h1>RESTfulChecker</h1>
  <h2>Request:</h2>
    <form:form commandName="remoteResource" action="/checkapi/" >
      <table>
        <tr>
          <td>Perform </td>
          <td>
            <form:select path="method">
              <form:option value="OPTIONS"/>
              <form:option value="GET"/>
              <form:option value="HEAD"/>
              <form:option value="POST"/>
              <form:option value="PUT"/>
              <form:option value="DELETE"/>
              <form:option value="CONNECT"/>
            </form:select>
          </td>
          <td>on API entry URL:</td>
          <td><form:input path="url" /> </td>
          <td><input type="submit" value="check this" /></td>
        </tr>
      </table>
      
      <table>
      <c:set var="lastHeaderIndex" value="-1"/>
      <c:forEach items="${remoteResource.requestHeaders}" varStatus="status" var="requestHeader">
        <tr>
          <td><form:input path="requestHeaders[${status.index}].headerKey" /></td>
          <td><form:input path="requestHeaders[${status.index}].headerValue" /></td>
        </tr>
        <c:set var="lastHeaderIndex" value="${status.index}"/>
      </c:forEach>
      <c:set var="lastHeaderIndex" value="${lastHeaderIndex+1}"/>
        <tr>
          <td><input id="requestHeaders.headerKey" name="requestHeaders[${lastHeaderIndex}].headerKey" type="text"/></td>
          <td><input id="requestHeaders.headerValue" name="requestHeaders[${lastHeaderIndex}].headerValue" type="text"/></td>
        </tr>
      </table>
    </form:form>
    
    <h2>Response:</h2>
    

  </body>
</html>