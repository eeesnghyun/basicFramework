<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}" />
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
    Basic Framework

    <div id="menuList"></div>

    <script>
        const csrfToken  = $("meta[name='_csrf']").attr("content");
        const csrfHeader = $("meta[name='_csrf_header']").attr("content");

        function getMenuList() {
            $.ajax({
                url         : "/getMenuList",
                type        : "POST",
                dataType    : "json",
                data 	    : {},
                contentType : 'application/json;charset=UTF-8',
                cache       : false,
                async		: true,
                beforeSend  : function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                }
            })
            .done(function(data) {
                console.log(data);
            })
            .fail(function(data, status, error) {
            	console.log(data);
            })
            .always(function() {
            });
        }

        getMenuList();
    </script>
</body>
</html>