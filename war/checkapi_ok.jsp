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
  <div id="navBarOuter">
    <div class="container">
    <div id="navBar">
      <h1>RESTfulChecker</h1>
            <ul>
              <li><a href="../">Home</a></li>
              <li class="active"><a href="./">Testing</a></li>
              <li><a href="repeatedtesting">Repeated testing</a></li>
            </ul>
          </div>
        </div>
  </div>
  <div class="container">
  <h2>Test evaluation</h2>
  <p>${apiEntry.message}</p>
  </div>
  <div id="apiForm" class="container">
  <h2>Specify new test</h2>
    <form:form commandName="apiEntry" action="./">
    <fieldset>
    <legend>API entry point</legend>
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
      </fieldset>
      <fieldset>
      <legend>Limit discovered resources count</legend>
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
      </fieldset>
    </form:form>
    </div>
  </body>
</html>