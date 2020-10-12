<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
    <!-- Regi Section -->
    <section class="container" style="max-width: 800px;">
        <h1 class="pt-5 mb-5 text-center">Sign Up</h1>
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="addMember.do" method="post">
                    <div class="mb-3">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="pwd" required="required" pattern="[0-9a-zA-Z!@#$]{6,20}"
                        placeholder="Number / Upper and lower case / !@#$ / 6~20 " />
                    </div>
                    <div class="mb-3">
                        <label for="birthDate">Birth Date</label>
                        <input type="date" class="form-control" id="birthDate" required="required" />
                    </div>
                    <div class="mb-2">
                        <span>Gender</span>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 mb-3">
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="male" name="gender" value="m" checked="checked" />
                                <label class="form-check-label" for="male">Male</label>
                            </div>
                        </div>
                        <div class="col-lg-6 mb-3">
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="female" name="gender" value="f" />
                                <label class="form-check-label" for="female">Female</label>
                            </div>
                        </div>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="Sign Up" />
                    </div>
                </form>
            </div>
        </div>
    </section>
    <section id="addmember" class="addmember-section" style="display: none;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>회원가입</h1>
                    <br>
                    <br>
                    <form action="addMember.do" method="post">
                    <table class="addmember-table">
                      <tr>
                        <td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
                        <td><input type="text" name="name" required="required"/></td>
                      </tr>
                      <tr>
                        <td>이&nbsp;&nbsp;메&nbsp;&nbsp;일</td>
                        <td><input type="text" name="email1" required="required"/>
                        	@
                        	<input type="text" name="email2" required="required"/></td>
                      </tr>
                      <tr>
                        <td>비밀번호</td>
                        <td><input type="password" name="pwd" required="required"/></td>
                      </tr>
                      <tr>
                        <td>생년월일</td>
                        <td>
                           <select name="year">
                               <c:forEach var="idx" begin="1964" end="2004">
                                   <option value="${idx}">${idx}</option>
                                </c:forEach>
                           </select>년
                           <select name="month">
                        <c:forEach var="idx" begin="1" end="12">
                           <c:set var="cnt" value="0${idx}" />
                           <option value="${idx<10 ? cnt : idx}">
                           ${idx<10 ? cnt : idx}</option>
                        </c:forEach>
                           </select>월
                           <select name="day">
                                <c:forEach var="idx" begin="1" end="31">
                           <c:set var="cnt" value="0${idx}" />
                           <option value="${idx<10 ? cnt : idx}">
                           ${idx<10 ? cnt : idx}</option>
                        </c:forEach>
                           </select>일
                        </td>
                      </tr>
                      <tr>
                        <td>성&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;별
                          <td>
                            <input type="radio" checked="checked" name="gender" value="남자" />
                               남자&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" name="gender" value="여자" />여자
                          </td>
                      </tr>
                  <tr>
                  <td></td>
                  <td><input type="submit" class="btn btn-primary" name="addmember" value="회원가입하기" />&nbsp;&nbsp;&nbsp;
                  <input type="reset" class="btn btn-primary" name="reset" value="리셋" /></td>
                  </tr>
                  </table>
                  </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>