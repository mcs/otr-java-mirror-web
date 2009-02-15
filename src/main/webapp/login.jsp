<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="site.login">
    <s:layout-component name="contents">

        <h2>Login</h2>

        <s:form action="POST" beanclass="otr.mirror.web.action.LoginActionBean">
            <table>
                <tr>
                    <td>Benutzer:</td>
                    <td><s:text name="user.login"/></td>
                </tr>
                <tr>
                    <td>Passwort:</td>
                    <td><s:password name="user.password"/></td>
                </tr>
            </table>
            <%-- If the security servlet attached a targetUrl, carry that along. --%>
            <s:hidden name="targetUrl" value="${request.parameterMap['targetUrl']}"/>
            <s:submit name="login" value="Einloggen"/>
        </s:form>

    </s:layout-component>
</s:layout-render>
