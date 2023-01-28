package pairmatching;

import pairmatching.dto.PairDto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.Console.*;
import static java.util.regex.Pattern.*;

public class PairInputService {

    private final String selectServiceRegex = "[1-3|Q]";
    private final String selectOptionRegex = "[가-힣]+, [가-힣]+[1-5], [가-힣]+";
    private final String selectRematchRegex = "[네|아니오]";

    /* 크루 파일 읽기 */
    public List<String> crewFileRead(Course course) throws IOException {
        List<String> crew = new ArrayList<>();
        File file = new File(course.getFilePath());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = "";

        while( (line = bufferedReader.readLine()) != null ){
            crew.add(line);
        }

        return crew;
    }

    public String selectServicePrint(){
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
        return selectService();
    }

    /* 기능 선택 */
    private String selectService(){
        try{
            String input = readLine();
            inputException(selectServiceRegex, input);
            return input;
        }catch (IllegalArgumentException e){
            System.out.println("[ERROR] 입력하신 기능이 존재하지 않습니다.");
            return selectService();
        }
    }

    public PairDto selectOptionPrint(){
        System.out.println("#############################################\n" +
                "과정: 백엔드 | 프론트엔드\n" +
                "미션:\n" +
                "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
                "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
                "  - 레벨3: \n" +
                "  - 레벨4: 성능개선 | 배포\n" +
                "  - 레벨5: \n" +
                "############################################\n" +
                "과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        return selectOptions();
    }

    /* 과정, 레벨, 미션 선택 */
    private PairDto selectOptions(){
        try{
            String input = readLine();
            inputException(selectOptionRegex, input);
            return new PairDto(input);
        }catch (IllegalArgumentException e){
            System.out.println("[ERROR] 입력이 옳지 않습니다.");
            return selectOptions();
        }
    }

    public String selectRematchPrint(){
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" +
                "네 | 아니오");
        return selectRematch();
    }

    /* 페어 재매칭 여부 입력 */
    private String selectRematch(){
        String input = readLine();
        try{
            inputException(selectRematchRegex, input);
            return input;
        } catch (IllegalArgumentException e){
            System.out.println("[ERROR] 입력이 옳지 않습니다.");
            return selectRematch();
        }
    }

    /* 입력 예외 처리 */
    public void inputException(String re, String input){
        if(!matches(re, input)) throw new IllegalArgumentException();
    }
}
