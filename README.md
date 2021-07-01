</br>

<h1 align="center" style="color:red"> 다이브 개발 과제</h1>

</br></br>

</br>

## 🤠 요구사항

<hr/>

1. layered architecture 구현
2. 모든 api는 Restful하게 구성
3. 게시글(post)과 댓글(comment)의 CRUD를 모두 구현을 하여야 합니다.
   - 네이버 뉴스와 같은 일반적인 게시글과 댓글의 구조(일대다 관계)로 이해하시면 됩니다.
   - 게시글 삭제시 게시글의 내용(content)만 삭제되도록 하고, 댓글은 삭제되지 않도록 처리
   - 댓글 삭제시 댓글 내용만 삭제되도록 처리
   - 전체 데이터를 가져오는 api의 페이징 처리(default 10개)
4. 적절한 로그 처리
5. development / production 용 환경 설정 파일 분리
6. README 작성
7. ERD 작성(README에 포함)

</br>
</br>
</br>

> layered architecture 구현

        - daib
            |_ config
            |_ controller
            |_ domain
            |_ dto
            |_ service
            |_ repository

</br>
</br>

> 모든 api는 Restful하게 구성

1. Post
   - GET - /boards : Post Read
   - GET - /boards/{id} : Post Details
   - POST - /boards : Post Create
   - PUT - /boards/{id} : Post Update
   - DELETE - /boards/{id} : Post Delete

</br>

2. Comment
   - GET - /comment : Comment Read
   - POST - /comment : Comment Create
   - PUT - /comment/{id} : Comment Update
   - DELETE - /comment/{id} : Comment Delete

</br>
</br>

> 게시글 삭제시 게시글의 내용(content)만 삭제되도록 하고,</br> 댓글은 삭제되지 않도록 처리
> 댓글 삭제시 댓글 내용만 삭제되도록 처리

- post 테이블에 삭제여부를 판단할 수 있는 boolean 컬럼을 추가함.
- delete요청이 들어오면 delete 매서드를 이용하여 내용을 `삭제된 게시물입니다.` 로 바꾸고 삭제상태를 바꿔줌
- post 전체 조회 시에 삭제 상태가 false인 게시물만 가져옴으로써 삭제 처리 된 게시물이 보이지 않음
- 게시물은 보이지 않을 뿐 실제 삭제 된것이 아니므로 조인된 댓글 역시 삭제되지 않음.

</br>

- 댓글은 삭제 시에 내용만 `삭제된 댓글 입니다.`로 바꾸고 다른 로직은 처리 하지 않음.

</br>
</br>

> 전체 데이터를 가져오는 api의 페이징 처리(default 10개)

- post, comment 전체 조회시 쿼리 스트링 값으로 페이지 넘버를 넘기면 해당 페이지에 해당하는 데이터가 반환됨.
- 한 페이지에 데이터는 10개로 제한함.
- 현재 DB에 있는 데이터의 갯수를 카운팅 하여 갯수에 따른 최대 페이지 넘버 수를 산출 하는 알고리즘을 만들어 이를 get요청시에 같이 넘김으로써 view에서 1페이지 부터 ~maxPageNum 만큼의 페이징 인덱스를 만들어 냄.
- JS를 사용하여 각태그에 id값을 먹여 해당 id태그가 클릭되면 해당하는 페이지 반환 처리

</br>
</br>

> development / production 용 환경 설정 파일 분리

- 크게 Dev, Operation 으로 나눔
- jvm설정에서 profiles설정으로 바꿔주면 됨.

       <JAR실행시>
       java -jar 파일명.jar --spring.profiles.active= profiles값
       # dev 는 8081포트에서 동작합니다.
       # 로컬(디폴트)은 h2DB 사용


</br>
</br>

> DataBase & ERD

<p align="center">
<img src="https://github.com/strong1133/Subject/blob/main/img/erd.png?raw=true" width="90%"></img>
</p>

- 개발용 DB는 테스트에 용이 하도록 H2 DB를 사용.
- 운영용 DB는 MySql를 사용 하였으면 DB의 안정(환경의 구애를 막기)을 위해 aws cloud Rds를 사용하였음.

</br>

</br>
</br>

> View

- thymeleaf 를 사용하였고
- 적절히 Jquery를 곁들여 사용.

> Security

- 게시판 및 댓글 CRUD의 특성상 로그인 시스템이 있어야 한다고 판단하여 Spring Security 를 사용하여 구현.
- 개인적으로 백과 프론트를 완전히 따로 구현하는 경우 JWT같이 토큰만 넘기면 되는 편이 훤씬 편한것 같습니다.

<hr/>

</br>
</br>

## 추가 사항

<hr/>

> Swagger 적용

    로컬 실행후 해당 링크로 들어가면 됨.
    http://localhost:8080/swagger-ui.html

> 에러처리

    GlobalExceptionHandler를 만들어 throw하면 @ControllerAdvice가 케치하여 일괄 처리

> validator

    SignupValidator 를 만들어

    //Id - 정규식
    public static boolean usernameValid(String usename){
        return Pattern.matches("^[A-za-z0-9]{5,15}", usename);
    }

    //Id - 중복검사

    public  boolean usernameDupCheck(String username){
        Optional<User> foundUsername = userRepository.findByUsername(username);
        return foundUsername.isEmpty();
    }

    //PW - 정규식
    public static boolean passwordValid(String  username,String password){
        if(password.contains(username)){
            return false;
        }
        return Pattern.matches("^[A-za-z0-9]{5,15}", password);
    }

</br>
</br>

## 화면

<hr/>

<p align="center">
<img src="https://github.com/strong1133/Subject/blob/main/img/%EB%A9%94%EC%9D%B8.png?raw=true" width="60%"></img>
</p>

<p align="center">
<img src="https://github.com/strong1133/Subject/blob/main/img/%EA%B8%80%EC%9E%91%EC%84%B1.png?raw=true" width="60%"></img>
</p>

<p align="center">
<img src="https://github.com/strong1133/Subject/blob/main/img/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.png?raw=true" width="60%"></img>
</p>

<p align="center">
<img src="https://github.com/strong1133/Subject/blob/main/img/%EA%B8%80%EC%83%81%EC%84%B8.png?raw=true" width="60%"></img>
</p>

<p align="center">
<img src="https://github.com/strong1133/Subject/blob/main/img/%EB%8C%93%EA%B8%80.png?raw=true" width="60%"></img>
</p>
