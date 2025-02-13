<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.regularexpression.web.RegularExpressionJspBean"%>

${ regularExpressionJspBean.init( pageContext.request, RegularExpressionJspBean.RIGHT_REGULAR_EXPRESSION_MANAGEMENT ) }
${ regularExpressionJspBean.getModifyRegularExpression( pageContext.request ) }
 
<%@ include file="../../AdminFooter.jsp" %>