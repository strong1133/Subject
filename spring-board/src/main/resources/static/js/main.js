

$(document).ready(function () {
    showHide()
    $('.card__container').empty()
    getArticle(1);
    logout();
    goHome();
    navBtn();

})



function getPageNum(num){
    // console.log("pageNum"+num)
    getArticle(num)
}

function goHome(){
    $('.main-title').on('click',function (){
        window.location.href="/"
    })
}

function showHide() {
    $('#posting-pen').on('click', function () {
        if ($('.link-signup').text() == "회원 가입하러 가기" ){
            alert("게시물 작성을 위해선 로그인을 해야합니다.")
            window.location.href="/signin"
            return;
        }
        $('.contents__container').hide();
        $('.posting__contianer').show();
    })

    $('#cancel-btn').on('click', function () {
        $('.posting__contianer').hide();
        $('.contents__container').show();

        $('.input-name').val('')
        $('.input-title').val('')
        $('.posting-textarea').val('')
    })
}


function postArticle() {
    let username = $('.cur_username').text()
    let title = $('.input-title').val()
    let contents = $('.posting-textarea').val()
    if (username === '') {
        alert('이름을 적어주세요!')
        return;
    } else if (title === '') {
        alert('제목을 적어주세요!')
        return
    } else if (contents === '') {
        alert('내용을 적어주세요!')
        return
    }
    let data = {'title': title, 'content': contents}
    let token = "Bearer "+getCookie('jwt');
    $.ajax({
        type:'POST',
        url:'/boards',
        headers:{
            "Authorization":token
        },
        contentType:'application/json',
        data: JSON.stringify(data),
        success: function (response){
            alert("게시물 작성이 완료 되었습니다!")
            window.location.reload()
        }
    })
}

function goPrev(){
    let idx = $('.page-num.active').text();
    getArticle(parseInt(idx)-1)
}
function goNext(){
    let idx = $('.page-num.active').text();

    getArticle(parseInt(idx)+1)

}

function getArticle(pageNum){
    
    $.ajax({
        type:'GET',
        url:`/boards?page=${pageNum}`,
        success: function (response){
            $('.card__container').empty();
            $('.page__index').empty();
            let boardsList = response.postList;
            let maxPageNum = response.maxPageNum;
            let starPageNum = response.starPageNum;
            let nextBtn = response.nextBtn;
            let prevBtn = response.prevBtn;
            for(let i=0; i<boardsList.length; i++){
                let board = boardsList[i]
                addArticle(board['id'], board['writer'], board['title'], board['content'], board['createdAt'])
            }
            for(let i=starPageNum; i<=starPageNum+9 && i<= maxPageNum; i++){
                addPageIndex(i, pageNum)
            }
            if(pageNum === 1){
                $('.prev').hide()
            }else{
                $('.prev').show()
            }
            if(pageNum === maxPageNum){
                $('.next').hide()
            }else{
                $('.next').show()
            }

            
        }


    })
}
function addArticle(id, username, title, contents, modifiedAt){
    let tempHtml = `<div class="card">
                    <div class="card-header">
                        <a href="/detail?id=${JSON.stringify(id)}" class="article-title"><h2>${title}</h2></a>
                    </div>
                    <div class="card-body">
                        <p>
                            ${contents}
                        </p>
                    </div>
                    <div class="card-footer">
                        <p class="post-author"> ${username} <span class="post-date">|  ${modifiedAt}</span></p>
                    </div>
                </div>`
    $('.card__container').append(tempHtml)
}
function addPageIndex(num, pageNum){
    let tempHtml
    if (num === pageNum){
        tempHtml=`
        <a href="#/boards?page=${num}" onclick="getPageNum(${num})" id="page-${num}" class="page-num active">${num}</a>
        `
    }else{
        tempHtml=`<a href="#/boards?page=${num}" onclick="getPageNum(${num})" id="page-${num}" class="page-num">${num}</a>`
    }
    $('.page__index').append(tempHtml)
}