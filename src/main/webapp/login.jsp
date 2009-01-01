<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="Login">
    <s:layout-component name="contents">

        <table style="vertical-align: top;">
            <tr>
                <td style="width: 25%; vertical-align: top;">
                    <!-- Somewhat contrived example of using the errors tag 'action' attribute. -->
                    <s:errors action="/Login.action"/>

                    <s:form action="/Login.action" focus="">
                        <table>
                            <tr>
                                <td style="font-weight: bold;"><s:label for="username"/>:</td>
                            </tr>
                            <tr>
                                <td><s:text name="username" value="${user.username}"/></td>
                            </tr>
                            <tr>
                                <td style="font-weight: bold;"><s:label for="password"/>:</td>
                            </tr>
                            <tr>
                                <td><s:password name="password"/></td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">
                                    <%-- If the security servlet attached a targetUrl, carry that along. --%>
                                    <s:hidden name="targetUrl" value="${request.parameterMap['targetUrl']}"/>
                                    <s:submit name="login" value="Login"/>
                                </td>
                            </tr>
                        </table>
                    </s:form>
                </td>
                <td style="vertical-align: top;">
                    <c:choose>
                        <c:when test="${empty user}">
                            <div class="sectionTitle">Welcome</div>

                            <p>Welcome to the OTR-Java-Mirror. If you haven't already
                            created an account, you may
                            <s:link href="/Register.jsp">register</s:link>
                            yourself in order to log in.</p>
                        </c:when>

                        <c:otherwise>
                            <p>You are already logged in as '${user.username}'.  Logging in again will cause
                            you to  be logged out, and then re-logged in with the username and password
                            supplied.</p>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>

    </s:layout-component>
</s:layout-render>
