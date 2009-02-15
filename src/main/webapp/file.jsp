<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-render name="/layout/standard.jsp" title="site.file">
    <s:layout-component name="contents">

        <c:if test="${!actionBean.context.downloadEnabled}">
            <h2 style="color:red">Download zurzeit nicht möglich!</h2>
        </c:if>
        Infos zur Datei
        <br/>
        <b>${actionBean.recording.filename}</b>
        <br/><br/>
        <table>
            <tr>
                <td>Name:</td>
                <td>${actionBean.recording.name}</td>
            </tr>
            <tr>
                <td>Sendedatum:</td>
                <td><fmt:formatDate value="${actionBean.recording.startTime}" type="date"/></td>
            </tr>
            <tr>
                <td>Sendezeit:</td>
                <td><fmt:formatDate value="${actionBean.recording.startTime}" type="time" timeStyle="short" /> - <fmt:formatDate value="${actionBean.recording.endTime}" type="time" timeStyle="short" /></td>
            </tr>
            <tr>
                <td>TV-Sender:</td>
                <td>${actionBean.recording.tvChannel}</td>
            </tr>
            <tr>
                <td>Format:</td>
                <td><span id="${actionBean.recording.format}">${actionBean.recording.format}</span></td>
            </tr>
            <tr>
                <td>Dateigroesse:</td>
                <td>
                    <fmt:formatNumber
                        value="${actionBean.recording.filesize / 1024 / 1024}"
                        minFractionDigits="2"
                        maxFractionDigits="2"/>
                        MB
                </td>
            </tr>
        </table>
        <br/>
        <s:link beanclass="otr.mirror.web.action.DownloadActionBean">
            <s:param name="file"
                     value="${actionBean.recording.filename}"/>
            <s:param name="key"
                     value="${actionBean.key}"/>
                     <s:form action="null" partial="true">
                         <s:button name="download" value="Download" disabled="${!actionBean.context.downloadEnabled}"/>
                     </s:form>
        </s:link>

    </s:layout-component>
</s:layout-render>
