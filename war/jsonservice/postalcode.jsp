<%@page import="java.util.List"%>
<%@page import="com.nositer.util.json.JsonBuilder"%>
<%@page import="com.nositer.webapp.Application"%>
<%@page import="com.nositer.hibernate.generated.domain.Postalcode"%>
<%@page import="com.nositer.hibernate.SqlHelper"%>
<%@page import="com.nositer.hibernate.HibernateUtil"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%
	String query = request.getParameter("query");
	int offset = 0;
	try {
		offset = Integer.parseInt(request.getParameter("offset"));
	} catch (Exception e) {
	}
	int limit = 50;
	try {
		limit = Integer.parseInt(request.getParameter("limit"));
	} catch (Exception e) {
	}
	int in_first_row = 1 + offset;
	int in_last_row = limit + in_first_row - 1;

	Session sess = HibernateUtil.getSession();
	Transaction trx = null;
	try {
		trx = sess.beginTransaction();
		List<Postalcode> results = sess
				.createSQLQuery(SqlHelper.FINDPOSTALCODEBYCODE.sql())
				.addEntity(Postalcode.class)
				.setString("CODE", query + "%")
				.setInteger("OFFSET", offset).setInteger("LIMIT", limit)
				.list();
		JsonBuilder.printJSON(out, results);
		trx.commit();
	} catch (Exception e) {
		HibernateUtil.rollbackTransaction(trx);
		Application.log.error("", e);
	} finally {
		HibernateUtil.closeSession(sess);
	}
%>

