/**
 * Created by akos on 2017.05.10..
 */

$(document).ready(function () {
    {
        var state;
        var $todos = $("#todos");
        $todos.empty();
        var htmlstringstart = "<div class=\"todo\">" +
            "<p id = ";
        var closetag = "</p>"+
            "</div>";
        var changeButton;
        localStorage.setItem("state", "geci");
        console.log(localStorage.getItem("state"));
        if (localStorage.getItem("state") == "geci")
        {
            state = "all";
            localStorage.setItem("state", "all");
        }
        else
        {
                state = localStorage.getItem("state")
        }

        $.ajax
        ({
            method: 'GET',
            url: '/gettasks',
            data:{selection:state},
            success: function(response)
            {
                console.log(response);
                for(var i=0; i<response.length; i++)
                {
                    changeButton = generateChangeButton(response[i].state);

                    $todos.append(htmlstringstart + response[i].id +  ">" + "Todo label: " +response[i].text + " Todo State: " + response[i].state + changeButton + closetag);
                }
            }
        })
    }

});
//Accordion management script

function   toggleAccordion() {
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].onclick = function(){
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
        }
    }

}

// to-do adding script.
function addTodo()
    {
        var todoText = $(".todoText").val();
        console.log(todoText);
        $.post(
            "/addtask",
            {type:"add", taskText: todoText},
            function () {
                loadTodos("all")
            }
            );
    }

//to-do loading script.

//generating some good overloaded method to get filters done. :)
function loadTodos(type)
{
    console.log(type);
    localStorage.setItem("state", type);
    var $todos = $("#todos");
    $todos.empty();
    var htmlstringstart = "<div class=\"todo\">" +
        "<p id = ";
    var closetag = "</p>"+
        "</div>";
    var changeButton;

    $.ajax
    ({
        method: 'GET',
        url: '/gettasks',
        data:{selection:type},
        success: function(response)
        {
            console.log(response);
            for(var i=0; i<response.length; i++)
            {
                changeButton = generateChangeButton(response[i].state);

                $todos.append(htmlstringstart + response[i].id +  ">" + "Todo label: " +response[i].text + " Todo State: " + response[i].state + changeButton + closetag);
            }
        }
    })
}


//generating proper buttons for to-dos according to state, hopefully. And it does, best day ever.
function generateChangeButton(state) {
    var buttonTag;
    if (state == "BACKLOG")
    {
        buttonTag = "<button class = \"changeState\" onclick=\"changeToActive()\">change to active</button> <button class='editText' onclick='editTodo()'> edit your todo </button>";

    }
    else if(state == "ACTIVE")
    {
        buttonTag = "<button class = \"changeState\" onclick=\"changeToDone()\">change to done</button> <button class = \"changeState\" onclick=\"changeToBacklog()\">change to backlog</button> <button class='editText' onclick='editTodo()'> edit your todo </button>";
    }

    else if(state == "DONE")
    {
        buttonTag = "<button class = \"changeState\" onclick=\"changeToActive()\">change to active</button> <button class='editText' onclick='editTodo()'> edit your todo </button>";
    }
    return buttonTag;
}

//onclick methods to change to-do state.
function changeToActive()
{
    var newState = "ACTIVE";
    console.log(newState);
    console.log($(event.target));
    console.log($(event.target).parent());
    console.log($(event.target).parent().attr("id"));
    $.post
    (
        "/addtask",
        {type:"state", newstate: newState, ID:$(event.target).parent().attr("id")}
    );
    loadTodos(localStorage.getItem("state"));
}
function changeToDone()
{
    var newState = "DONE";
    $.post
    (
        "/addtask",
        {type:"state", newstate: newState, ID:$(event.target).parent().attr("id")}
    );
    loadTodos(localStorage.getItem("state"));}

function changeToBacklog()
{
    var newState = "BACKLOG";
    $.post
    (
        "/addtask",
        {type:"state", newstate: newState, ID:$(event.target).parent().attr("id")}
    );
    loadTodos(localStorage.getItem("state"));
}

function editTodo()
{
    do
    {
        var newText = prompt("Enter the text");
    } while (newText.length == 0 && newText != null);

    console.log(newText);
    console.log($(event.target));
    console.log($(event.target).parent());
    console.log($(event.target).parent().attr("id"));
    $.post
    (
        "/addtask",
        {type:"edit", ID:$(event.target).parent().attr("id"), newtext:newText}
    );
    loadTodos(localStorage.getItem("state"));
}


