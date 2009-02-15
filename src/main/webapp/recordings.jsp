<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:useActionBean beanclass="otr.mirror.web.action.ShowRecordingsActionBean" var="showRecords" validate="false"/>

<s:layout-render name="/layout/standard.jsp" title="site.recordings">
    <s:layout-component name="contents">

        <s:errors globalErrorsOnly="true"/>
        <table class="sortable" border="1px">
            <thead>
                <tr>
                    <th>Ausstrahlungsdatum</th>
                    <th>Name</th>
                    <th>TV-Channel</th>
                    <th class="sorttable_numeric">Size (MB)</th>
                    <th>Format</th>
                    <c:if test="${user.access eq 'ADMIN'}">
                        <th>DLs</th>
                        <th>lastChange</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${showRecords.recordings}" var="recording" varStatus="rowstat">
                    <s:format var="name" formatType="70" value="${recording.name}"/>
                    <tr>
                        <td sorttable_customkey="<fmt:formatDate value="${recording.startTime}" type="date" pattern="yyyyMMddHHmm"/>">
                            <fmt:formatDate value="${recording.startTime}" type="date" />,
                            <fmt:formatDate value="${recording.startTime}" type="time" timeStyle="short" />
                            -
                            <fmt:formatDate value="${recording.endTime}" type="time" timeStyle="short" />
                        </td>
                        <td>
                            <s:link beanclass="otr.mirror.web.action.ShowFileActionBean" title="${recording.filename}">
                                <s:param name="recording.filename" value="${recording.filename}"/>
                                 ${name}
                            </s:link>
                        </td>
                        <td>${recording.tvChannel}</td>
                        <td align="right" sorttable_customkey="${recording.filesize}">
                            <fmt:formatNumber
                                value="${recording.filesize / 1024 / 1024}"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                        </td>
                        <td id="${recording.format}" align="center">${recording.format}</td>
                        <c:if test="${user.access eq 'ADMIN'}">
                            <td>${recording.downloaded}</td>
                            <td sorttable_customkey="<fmt:formatDate value="${recording.modifiedDate}" type="date" pattern="yyyyMMddHHmm"/>"><fmt:formatDate value="${recording.modifiedDate}" type="date" /></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </s:layout-component>
</s:layout-render>
