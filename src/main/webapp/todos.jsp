<%--
  Created by IntelliJ IDEA.
  User: akos
  Date: 2017.05.10.
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="./scripts.js"></script>
    <title>My todos</title>
</head>
<%
    String username = (String) session.getAttribute("username");
%>
<body>
<div>
    <h1>welcome, <%=username%></h1>
</div>
<div>
    <button class="accordion" onclick="toggleAccordion()">add a todo!</button>
        <div class="panel"><input class="todoText" type="type text" required maxlength="128" placeholder="Write your todo here">
        <button class="addTodo" onclick="addTodo()">Add todo</button>
        </div>
    <button class="filterButton" onclick="loadTodos('all')">See All tasks</button>
    <button class="filterButton" onclick="loadTodos('active')">See Active tasks</button>
    <button class="filterButton" onclick="loadTodos('done')">See Done tasks</button>
    <button class="filterButton" onclick="loadTodos('backlog')">See Backlog tasks</button>
    <br>
    <div id="todos">
        <div class="todo">
            <p id = taskID> name, state <button class = "changeState">change state</button> <button class="changeText">Edit todo</button></p>
        </div>
    </div>
</div>
<p style="visibility: hidden" class="currentSelection"></p>


</body>
</html>
