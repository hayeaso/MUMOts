<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      <button class="btn btn-warning btn-sm alignright" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
        <h5 class="modal-title" id="exampleModalLongTitle">${titleMessage}</h5>
        
           <!-- <button type="button" class="btn btn-secondary btn-sm alignright" data-dismiss="modal">Close</button>
         -->
      </div>
      <div class="modal-body">
       <p>${bodyMessage.content}</p>
				<ul>
					<c:forEach items="${lines}" var="line">
						<li>${line.content}"</li>
					</c:forEach>
				</ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">
	$(window).on('load', function() {
		$('#exampleModalLong').modal('show');
	});
</script>