<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head></head>
<body>
	<!-- Loan Section -->
    <section class="container" style="max-width: 800px;">
        <h1 class="pt-5 mb-5 text-center">Loan application</h1>
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="loanAf.do" method="post" enctype="multipart/form-data">
                    <div class="mb-5">
                        <h3>Personal Information</h3>
                        <div class="mb-3">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" name="name" id="name" value="${login.name}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="birth">Birth day</label>
                            <input type="date" class="form-control" name="birth" id="birth" value="${login.birth}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="gender">Gender</label>
                            <input type="text" class="form-control" name="gender" id="gender" value="${login.gender}" readonly>
                        </div>
                    </div>
                    <div class="mb-5">
                        <h3>Work Information</h3>
                        <div class="mb-3">
                            <label for="employType">Type of employment</label>
                            <select class="custom-select" id="employType" name="employType">
                                <option value="regular">Regular</option>
                                <option value="nonRegular">Non-regular</option>
                                <option value="partTime">Part time job</option>
                                <option value="none">Not employed</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="scale">Enterprise size</label>
                            <select class="custom-select" id="scale" name="scale">
                                <option value="large">Large enterprise</option>
                                <option value="medium">Medium-sized enterprise</option>
                                <option value="small">Small-sized enterprise</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="serve">Term of employment</label>
                            <div class="form-inline">
                                <select class="custom-select mr-sm-2" id="serve" name="serve">
                                    <c:forEach var="idx" begin="1" end="50" step="1">
                                        <option value="${idx}">${idx}</option>
                                    </c:forEach>
                                </select>
                                <span>Year</span>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="salary">Salary</label>
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">$</div>
                                </div>
                                <input type="number" class="form-control" name="salary" id="salary" value="0" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="mb-5">
                        <h3>Loan application</h3>
                        <div class="mb-3">
                            <label for="interestRate">Interest rate</label>
                            <div class="input-group mb-2">
                                <input type="text" class="form-control" name="interestRate" id="interestRate" value="8" readonly>
                                <div class="input-group-append">
                                    <div class="input-group-text">%</div>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="repayType">Repayment method</label>
                            <input type="text" class="form-control" name="repayType" id="repayType" value="Equivalence of principal and interest" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="loanType">Loan classification</label>
                            <input type="text" class="form-control" name="loanType" id="loanType" value="Personal credit loan" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="loanMoney">Loan application amount</label>
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">$</div>
                                </div>
                                <input type="number" class="form-control" name="loanMoney" id="loanMoney" value="Personal credit loan" max="50000000" required="required">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="loanDate">Loan period</label>
                            <div class="form-inline">
                                <select class="custom-select mr-sm-2" name="loanDate" id="loanType">
                                    <c:forEach var="idx" begin="1" end="24">
                                        <option value="${idx}">${idx}</option>
                                    </c:forEach>
                                </select>
                                <span>months</span>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="repay">Desired repayment date</label>
                            <select class="custom-select mr-sm-2" name="repay" id="repay">
                                <option value="1">1</option>
                                <c:forEach var="idx" begin="5" end="25" step="5">
                                    <option value="${idx}">${idx}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-5">
                        <h3>Introduction</h3>
                        <div class="mb-3">
                            <label for="title">Title</label>
                            <input type="text" class="form-control" name="title" id="title" size="50" required="required">
                        </div>
                        <div class="mb-3">
                            <label for="intro">content</label>
                            <textarea class="form-control" name="intro" id="intro" cols="52" rows="10" required="required"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="uploadfile">Image file</label>
                            <input type="file" class="form-control-file" name="uploadfile" id="uploadfile" required="required">
                        </div>
                        <div class="mb-3">
                            <label for="expiryDate">Funding period</label>
                            <select class="custom-select mr-sm-2" name="expiryDate" id="expiryDate">
                                <c:forEach var="idx" begin="1" end="7" step="1">
                                    <option value="${idx}">${idx}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="email" value="${login.email}">
                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Apply a loan" />
                </form>
            </div>
        </div>
    </section>
</body>
</html>