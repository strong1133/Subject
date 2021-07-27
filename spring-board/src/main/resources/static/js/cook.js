$(document).ready(function () {
    getName()

})

function getName(){
    let tokenken ="Bearer "+ getCookie('jwt')
    let username;
    $.ajax({
        type: 'GET',
        url: '/user',
        headers:{
            "Authorization": tokenken
        },
        success: function (response){
            username= response;
            console.log(username)
            showUserName(username)
        }
    })
    return username;
}

function setCookie(cName, cValue, cDay){
    let exp = new Date();
    exp.setDate(exp.getDate() + cDay);
    cValue = cValue.split(' ')[1];
    cookies = cName + '=' + escape(cValue) + '; path=/ ' ;
    if(typeof cDay != 'undefined') cookies += '; expires=' + exp.toGMTString() + ';';
    document.cookie = cookies;
}

const deleteCookie = function(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}

const getCookie = function(name){
    let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;
}

function showUserName(username){
    let token = getCookie('jwt')
    let tempHtml;
    if (token === null){
         tempHtml = `<div>
         아직 회원이 아니시군요? <br />
         <a href="/signup" class="link-signup">회원 가입하러 가기</a>
       </div>`
    }
    else{
        $('.show_username').empty();
        tempHtml = `<div><div class="usernameon fas fa-sign-out-alt"> ${username}</div></div>`
    }
    $('.show_username').append(tempHtml);
}


function navBtn(){
    $('.fa-sign-out-alt').on('click',function (){
        alert('로그아웃 되었습니다. 감사합니다.')
        deleteCookie('jwt')
        window.location.href="/signin"
    })
    
    $('.nav-git').on('click',function (){
        window.location.href="https://github.com/strong1133"
    })
    $('.nav-pen').on('click',function (){
        if (getCookie('jwt') === null){
            console.log("!")
            alert("게시물 작성을 위해선 로그인을 해야합니다.")
            window.location.href="/signup"
            return;
        }
        $('.contents__container').hide();
        $('.posting__contianer').show();
    })
    $('.nav-key').on('click',function (){
        if (getCookie('jwt') === null ){
            window.location.href="/signin"
            return;
        }
        alert('로그아웃 되었습니다. 감사합니다.')
        deleteCookie('jwt')
        window.location.href="/signin"
    })
}
function logout(){
    $('.usernameon').on('click',function(){
        alert('로그아웃 되었습니다. 감사합니다.')
        deleteCookie('jwt')
        window.location.href="/signin"
    })
}