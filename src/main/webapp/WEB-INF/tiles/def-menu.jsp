
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<c:set var="currentPage"
	value="${requestScope['javax.servlet.forward.request_uri']}" />
	
 <c:if test="${sessionScope.role == null}">
 <nav class="navbar navbar-default">
  <div class="container-fluid"> 
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
        <!-- <li><a href="">HOME <span class="sr-only">(current)</span></a></li> -->
					<li><a href="<c:url value="/contactus" />"
						${('/onlinetest/contactus' == currentPage) ? ' class="activeNavItem"' : ''}>Contact
							Us </a></li>

				</ul>
      
      <ul class="nav navbar-nav navbar-right">
      <c:if test="${pageContext.request.userPrincipal.name == null}">
        <li><a href="<c:url value="/login" />">Login</a></li>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
        <li><a href="<c:url value="/logout" />">Logout</a></li>
        </c:if>
      </ul>
    </div>
  </div> 
</nav> 
</c:if>
 <c:if test="${sessionScope.role == 'admin'}">
 <%@ include file="/WEB-INF/tiles/admin-menu.jsp"%>
 </c:if>
  <c:if test="${sessionScope.role == 'dba'}">
   <%@ include file="/WEB-INF/tiles/dba-menu.jsp"%>
  </c:if>
   <c:if test="${sessionScope.role == 'coach'}">
    <%@ include file="/WEB-INF/tiles/coach-menu.jsp"%>
   </c:if>


