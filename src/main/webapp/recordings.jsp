<%@ include file="/taglibs.jsp" %>

<s:useActionBean beanclass="otr.mirror.web.action.ShowRecordingsActionBean" var="showRecords" validate="false"/>
<s:layout-render name="/layout/standard.jsp" title="Recordings">
    <s:layout-component name="contents">

        <s:errors globalErrorsOnly="true"/>
        <table>
            <thead>
                <th>Format</th>
                <th>Name</th>
                <th>Size (MB)</th>
            </thead>
            <tbody>
                <c:forEach items="${showRecords.recordings}" var="recording" varStatus="rowstat">
                    <tr>
                        <td>${recording.format}</td>
                        <td>
                            <s:link beanclass="otr.mirror.web.action.DownloadActionBean"
                                    addSourcePage="true">
                            <s:param name="file"
                                    value="${recording.filename}"/>
                            <s:param name="key"
                                    value="${showRecords.keys[rowstat.index]}"/>
                            ${recording.filename}
                            </s:link>
                        </td>

                        <td align="right">
                            <fmt:formatNumber
                                    value="${recording.filesize / 1024 / 1024}"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </s:layout-component>
</s:layout-render>
