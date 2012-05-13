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
              <li><a href="./">Testing</a></li>
              <li class="active"><a href="repeatedtesting">Repeated testing</a></li>
            </ul>
          </div>
        </div>
  </div>
  <div class="container">
  <p>Here you can set up the repeated test of an API. The RESTfulChecker can check APIs, which are accessible over HTTP or HTTPS. So you put the URL of the API into "API entry URL". By clicking to "Add header" button, you can specify HTTP headers if the tested API needs them.</p> 
  <p>Some APIs are too big to be checked entirely. To limit API checking, you fill "max siblings" and "base Url". The max siblings determines how many links will be followed from one API's response. The base Url determines scope - all links outside of the scope won't be followed. It is a good idea to put root address of tested API (eg. https://api.dropbox.com/1/ ) to base Url field. </p>
  <p>The output of the test will be XML file with information about the test, tested API and its evaluation.</p>
  
  <p>${apiEntry.message}</p>
  </div>
  <div id="apiForm" class="container">
  <h2>Specify the test</h2>
    <form:form commandName="apiEntry" action="repeatedtesting" >
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