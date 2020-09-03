<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="crowding funding">
<meta name="author" content="9age">

<title>${title}</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<!-- Custom CSS -->
<link href="css/scrolling-nav.css" rel="stylesheet">

<!-- ABC Funding CSS -->
<link href="css/abcstyle.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

    <!-- intro Section -->
    <div class="row">
        <div id="intro-section">

            <div role="tabpanel">

                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs nav-pills nav-stacked col-sm-3"
                        role="tablist">
                        <li role="presentation" class="active">
                            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">What is ABC Funding?</a>
                        </li>
                        <li role="presentation">
                            <a href="#investGuide" aria-controls="profile" role="tab" data-toggle="tab">Investment Guide</a>
                        </li>
                        <li role="presentation">
                            <a href="#loanGuide" aria-controls="messages" role="tab" data-toggle="tab">Loan Guide</a>
                        </li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content col-sm-9">
                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                            <div class="page-header">
                                <h1>What is ABC Funding?</h1>
                            </div>
                            <p>
                                <img src="${pageContext.request.contextPath}/img/intro_bridge.jpg" class="img-rounded" alt="Bridge image">
                            </p>
                            <p>ABC Funding is a P2P financial company that links financing between angel investors and individuals and corporations in need of funds.</p>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="investGuide">
                            <div class="page-header">
                                <h1>Investment Guide</h1>
                            </div>
                            <p>
                                <img src="${pageContext.request.contextPath}/img/investFlow_1.jpg" class="img-rounded" alt="Bridge image">
                            </p>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="loanGuide">
                            <div class="page-header">
                                <h1>Loan Guide</h1>
                            </div>
                            <p>
                                <img src="${pageContext.request.contextPath}/img/loanFlow_1.jpg" class="img-rounded" alt="Bridge image">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Scrolling Nav JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>

</body>
</html>