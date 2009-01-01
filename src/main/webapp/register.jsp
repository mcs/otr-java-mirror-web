<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="Register">
    <s:layout-component name="contents">

        <s:errors globalErrorsOnly="true"/>

        <s:form action="/Register.action" focus="first">
            <p>Please provide the following information:</p>

            <table class="leftRightForm">
                <tr>
                    <th><s:label for="user.firstName"/>:</th>
                    <td>
                        <s:text name="user.firstName"/>
                        <s:errors field="user.firstName"/>
                    </td>
                </tr>
                <tr>
                    <th><s:label for="user.lastName"/>:</th>
                    <td>
                        <s:text name="user.lastName"/>
                        <s:errors field="user.lastName"/>
                    </td>
                </tr>
                <tr>
                    <th><s:label for="user.username"/>:</th>
                    <td>
                        <s:text name="user.username"/>
                        <s:errors field="user.username"/>
                    </td>
                </tr>
            </table>

            <div class="buttons">
                <s:submit name="gotoStep2" value="Next"/>
            </div>
        </s:form>
    </s:layout-component>
</s:layout-render>