package admin;

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="site.context">
    <s:layout-component name="contents">
        <h2>Attribute des Servlet-Context</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Schlüssel</th>
                    <th>Wert</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${actionBean.attributes}" var="attribute">
                <tr>
                    <td>${attribute.key}</td>
                    <td>${attribute.value}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </s:layout-component>
</s:layout-render>
