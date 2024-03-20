<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/common.css">
    <link rel="stylesheet" href="/resources/styles/login.css">
</head>
<body>
    <main class="form-signin w-100 m-auto">
        <form method="post" action="/authenticate">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <h1 class="h3 mb-3 fw-normal text-center">로그인</h1>

            <div class="form-floating">
              <input type="text" class="form-control" id="floatingInput" name="username" placeholder="아이디">
              <label for="floatingInput">아이디</label>
            </div>
            <div class="form-floating">
              <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="패스워드">
              <label for="floatingPassword">패스워드</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>

            <p class="mt-5 mb-3 text-center">
              <a href="/join">회원가입</a>
            </p>
        </form>
    </main>
</body>
</html>