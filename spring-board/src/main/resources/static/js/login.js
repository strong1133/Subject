$(document).ready(function(){
    home()
})

function home(){
    $('.login__title').on('click',function (){
        window.location.href="/"
    })
}

function blank_check(username, password, name){
    return !(username === "" || password === "" || name === "");

}



function doRegister() {
    let username = $('#signup__id').val();
    let password = $('#signup__pw').val();
    let name =  $('#signup__name').val();

    if (!blank_check(username, password, name)){
        alert("필수 입력값을 모두 입력해 주세요!")
        return;
    }
    let data ={'username':username, 'password':password, 'name':name}
    $.ajax({
        type:'POST',
        url:'/signup',
        contentType:'application/json',
        data:JSON.stringify(data),

        success: function (response){
            console.log(response)
            location.href='/signin'
        },
        error: function (request, thrownError){
            let err = JSON.parse(request.responseText);
            alert(err.msg);
        }
    })
}

function login(){
    let id = $('#id').val();
    let pw = $('#pw').val();

    let data ={'username':id, 'password':pw}

    $.ajax({
        type:'POST',
        url:'/login',
        contentType:'application/json',
        data:JSON.stringify(data),
        success: function(data, request, xhr){
            let token = xhr.getResponseHeader("Authorization");
            console.log(token)
            setCookie('jwt',token,1);
            location.href ="/"
        },
        error: function(){
            $('.alert').show();
        }
    })
}