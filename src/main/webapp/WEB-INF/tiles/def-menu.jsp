<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<spring:url value="resources/css/styles.css" var="styleCss" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/flatly/bootstrap.min.css" rel="stylesheet" integrity="sha384-+ENW/yibaokMnme+vBLnHMphUYxHs34h9lpdbSLuAwGkOKFRl4C34WkjazBtb7eT" crossorigin="anonymous">
<link href="${styleCss	}" rel="stylesheet" />

 <nav class="navbar navbar-inverse">
  <div class="container-fluid"> 
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">OTS</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
        <!-- <li><a href="">HOME <span class="sr-only">(current)</span></a></li> -->
        <li><a href="">CONTACT US <span class="sr-only">(current)</span></a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/login" />">Login</a></li>
      </ul>
    </div>
  </div> 
</nav> 
<%-- <div class="hor-menu ">
	<ul class="nav navbar-nav bimal" >
		<li class="bimal"><a href="<c:url value="/login" />">Login</a></li>
		<li><a href="<c:url value="/contactus" />">Contact us</a></li>
	</ul>
</div> --%>