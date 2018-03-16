<%@ include file="header.jsp"%>

<c:forEach var="reimb" items="${reimbs}">
<c:if test="${reimb.status_id.status == 'Pending'}">
	<tr>
		<td><fmt:formatDate type="date" dateStyle="long"
				value="${reimb.date_submitted}" /></td>
		<td><c:out value="${reimb.author_id.fullName}">
			</c:out></td>
		<td><c:out value="${reimb.type_id.type}">
			</c:out></td>
		<td><c:out value="${reimb.description}">
			</c:out></td>
		<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
				type="currency" value="${reimb.amount}" /></td>
		<td><c:out value="${reimb.status_id.status}" /></td>

		<td>
			<form action="/ers/approve.do" method="POST">
				<button type="submit" name="approveReimb"
					class="btn btn-default btn-sm btn-block btn-approve"
					onclick="statusClick(this.id);"
					value="<c:out value="${reimb.id}"/>">Approve</button>
			</form>
			<form action="/ers/deny.do" method="POST">
				<button type="submit" name="denyReimb"
					class="btn btn-default btn-sm btn-block btn-deny"
					onclick="statusClick(this.id);"
					value="<c:out value="${reimb.id}"/>">Deny</button>
			</form>
		</td>
			<td>	<c:if test="${reimb.resolver_id.first_name != null}">
	        				<fmt:formatDate type="date" dateStyle="long" value="${reimb.date_resolved}" />						
	                	</c:if>														</td>
	            <td>	<c:if test="${reimb.resolver_id.first_name != null}">
        					<c:out value="${reimb.resolver_id.fullName}">	</c:out>
						</c:if>														</td>
        	</tr>
           	
          	</c:if>
          	
       </c:forEach>



		<%@ include file="footer.jsp"%>