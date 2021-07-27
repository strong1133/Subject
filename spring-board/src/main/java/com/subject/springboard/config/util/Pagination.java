package com.subject.spring_board.config.util;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class Pagination {

    private final PostRepository postRepository;
    // 총 페이지 수 반환
    public Long maxPageNum() {
        int nums = (int) postRepository.count();
        int maxPageNum =1;
        if (nums % 10 == 0){
            maxPageNum = nums/10;
        }else{
            maxPageNum = (nums/10) +1;
        }
        return (long) maxPageNum;
    }

    public Long starPageNum(int page){
        int starPageNum= 0;
        if (page%10 == 0){
            starPageNum = (page/10)*10+1-10;
        }else{
            starPageNum = (page/10)*10+1 ;
        }

        System.out.println(starPageNum);
        return (long) starPageNum;
    }

    public boolean prevBtn(int page){
        return page > 10;
    }

    public boolean nextBtn(int page){
        Long maxNum = maxPageNum();
        Long starNum = starPageNum(page);
        long cha= maxNum - starNum;
        return cha >= 10;
    }
}
