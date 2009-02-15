<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>

<s:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html>
        <head>
            <title>SYN-OTR-Mirror - <fmt:message key="${title}"/></title>
            <link rel="stylesheet" type="text/css" href="${ctx}/otrmirror.css"/>
<!--            <script type="text/javascript" src="${ctx}/otrmirror.js"></script> -->
            <script type="text/javascript" src="${ctx}/sorttable.js"></script>
            <s:layout-component name="html-head"/>
        </head>
        <body>
            <div id="contentPanel">
                <s:layout-component name="header">
                    <jsp:include page="/layout/header.jsp"/>
                </s:layout-component>

                <div id="pageContent">
                    <div class="sectionTitle"><fmt:message key="${title}"/></div>
                    <s:messages/>
                    <s:layout-component name="contents"/>
                </div>

                <div id="footer">
                    <!--
                    <a href="http://otrinfo.de/distro" target="_blank">
                        powered by Distro @ otr:info
                    </a>
                    | -->
                    <a href="http://www.otrkey.com">www.otrkey.com</a>
                </div>
            </div>
        </body>
    </html>
</s:layout-definition>
