<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
	<section class="container pt-5">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="text-center">Application of investment success</h1>
				<div id="invest-wrap-border">
					<div id="invest-suc-wrap">
						<fmt:formatNumber type="currency" var="invest" value="${investMoney}" currencySymbol="" />
                        <span>
                            ${invest} investment in <br/>
                            '${title}' products has been completed.
                        </span>
						<a href="main.do" class="btn btn-info btn-lg">Go home</a>
<!-- 							<a href="myInfoInvest.do" class="btn btn-success btn-lg">Check out my investments</a> -->
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>