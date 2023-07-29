<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/7/13
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function DelUser(id){
            if(confirm("确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/DelUserServlet?id="+id;
            }
        }
        $(function(){
            $("#chkAll").click(function (){
                $("input[name='chkUser']:checkbox").prop("checked",this.checked);
            })

            $("#delSelected").click(function (){
                if(confirm("是否确定删除？")) {
                    var flag = false;
                    //记录有没有选中
                    $("input[name='chkUser']:checkbox").each(function () {
                        //当前复选框被选中
                        if (this.checked) {
                            flag = true;
                        }
                    })
                    //如果有选中的记录就提交表单
                    if (flag) {
                        $("#form1").submit();
                    }
                }
            })
        })
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/UserListServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" value="${condition.address[0]}" class="form-control" id="exampleInputName3" >
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" value="${condition.email[0]}" class="form-control" id="exampleInputEmail2"  >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>
    <form action="${pageContext.request.contextPath}/DelSelectedUserServlet" id="form1">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="chkAll"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="user">
        <tr>
            <td><input type="checkbox" name="chkUser" value="${user.id}"></td>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.gender}</td>
            <td>${user.age}</td>
            <td>${user.address}</td>
            <td>${user.qq}</td>
            <td>${user.email}</td>
            <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/FindUserServlet?uid=${user.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:DelUser(${user.id})">删除</a></td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a></td>
        </tr>
    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage==1}">
                <li class="disabled">
                    </c:if>
                        <c:if test="${pb.currentPage!=1}">
                    <li>
                        </c:if>
                    <a href="${pageContext.request.contextPath}/UserListServlet?currentPage=${pb.currentPage-1}&rows=3&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                    <c:forEach begin="1" end="${pb.pageCount}" var="i">
                        <c:if test="${pb.currentPage==i}">
                <li class="active"><a href="${pageContext.request.contextPath}/UserListServlet?currentPage=${i}&rows=3&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                    ${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage!=i}">
                <li>                    <a href="${pageContext.request.contextPath}/UserListServlet?currentPage=${i}&rows=3&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        ${i}</a></li>
                        </c:if>
                    </c:forEach>

                <c:if test="${pb.currentPage==pb.pageCount}">
                    <li class="disabled">
                        </c:if>
                            <c:if test="${pb.currentPage!=pb.pageCount}">
                    <li>
                        </c:if>
                    <a href="${pageContext.request.contextPath}/UserListServlet?currentPage=${pb.currentPage+1}&rows=3&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous"
                    aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.recordCount}条记录，共${pb.pageCount}页
                </span>

            </ul>
        </nav>


    </div>
</div>
</body>
</html>

