<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<tilesx:useAttribute id="scripts" name="scripts" classname="java.util.List" />
<tilesx:useAttribute id="styles"  name="styles"  classname="java.util.List" />
<html>
	<head>
		<meta charset="utf-8" />
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible" />
		<meta content="width=device-width, initial-scale=1, maximum-scale=2, user-scalable=no" name="viewport" />
	    <meta content="Semantic-UI-Forest, collection of design, themes and templates for Semantic-UI." name="description" />
	    <meta content="Semantic-UI, Theme, Design, Template" name="keywords" />
	    <meta content="PPType" name="author" />
	    <meta content="#ffffff" name="theme-color" />
		<title>Hello_World<tiles:insertAttribute name="title" ignore="true"  /></title><c:forEach var="style" items="${styles}">		
    		<link type="text/css" rel="stylesheet" href="<c:url value="${style}"/>" /></c:forEach><c:forEach var="script" items="${scripts}">		
    		<script src="<c:url value="${script}"/>"></script></c:forEach>    		
    		<tiles:insertAttribute name="javascript"/>    	
	</head>
	<body>
		<div class="ui borderless fixed blue inverted pointing menu">
		  <div class="ui container">
		    <a class="header active item" href="blog.html#">Home</a>
		    <a class="item" href="<c:out value="/login"/>">New feature</a>
		    <a class="item" href="blog.html#">Press</a>
		    <a class="item" href="blog.html#">New hires</a>
		    <a class="item" href="blog.html#">About</a>
		  </div>
		</div>
		<tiles:insertAttribute name="body" />
	</body>	
</html>