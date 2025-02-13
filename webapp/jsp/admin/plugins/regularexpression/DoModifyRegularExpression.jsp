<%@ page errorPage="../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.regularexpression.web.RegularExpressionJspBean"%>

${ regularExpressionJspBean.init( pageContext.request, RegularExpressionJspBean.RIGHT_REGULAR_EXPRESSION_MANAGEMENT ) }
${ pageContext.response.sendRedirect( regularExpressionJspBean.doModifyRegularExpression( pageContext.request ) ) }
