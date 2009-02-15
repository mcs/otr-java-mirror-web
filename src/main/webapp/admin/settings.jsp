<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="site.settings">
    <s:layout-component name="contents">
        <h2>Attribute des Servlet-Context</h2>
        <s:form beanclass="otr.mirror.web.action.admin.SettingsActionBean">
        <table border="1">
            <thead>
                <tr>
                    <th>Schlüssel</th>
                    <th>Wert</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Download erlaubt</td>
                    <td><s:checkbox name="enabled" checked="${actionBean.enabled}"/></td>
                </tr>
            </tbody>
        </table>
        <s:submit name="save"/><s:reset name="save"/>
        </s:form>
    </s:layout-component>
</s:layout-render>
