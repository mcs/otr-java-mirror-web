<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="Register">
    <s:layout-component name="contents">

        <s:form action="/Register.action" focus="user.password">
            <s:errors/>

            <p>Welcome ${actionBean.user.firstName}, please pick a password:</p>

            <table class="leftRightForm">
                <tr>
                    <th><s:label for="user.password"/>:</th>
                    <td><s:password name="user.password"/></td>
                </tr>
                <tr>
                    <th><s:label for="confirmPassword"/>:</th>
                    <td><s:password name="confirmPassword"/></td>
                </tr>
            </table>

            <div class="buttons">
                <s:submit name="register" value="Create Account"/>
            </div>
        </s:form>
    </s:layout-component>
</s:layout-render>