package layout;

<%@ include file="/taglibs.jsp" %>

<div id="imageHeader">
    <table style="padding: 5px; margin: 0px; width: 100%;">
        <tr>
            <td id="pageHeader">otr java mirror</td>
            <td id="loginInfo">
                <c:if test="${not empty user}">
                    Welcome: ${user.firstName} ${user.lastName}
                    |
                    <s:link href="/examples/Logout.action">Logout</s:link>
                </c:if>
            </td>
        </tr>
    </table>
    <div id="navLinks">
        <s:link href="/example.jsp">Example Link</s:link>
    </div>
</div>