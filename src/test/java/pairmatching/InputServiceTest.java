package pairmatching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;
import static org.assertj.core.api.Assertions.*;

public class InputServiceTest {

    @Test
    void 파일_읽기() throws IOException {
        List<String> crew = new ArrayList<>();
        String path = "src/main/resources/backend-crew.md";

        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        String line = "";
        while( (line = bufferedReader.readLine()) != null ){
            crew.add(line);
        }
    }

    @Test
    void 미션_선택_정규화(){
        String successTest = "프론트엔드, 레벨1, 자동차경주";
        String fileTest = "aaa, 레벨5555, 자동차경주";
        String re = "[가-힣]+, [가-힣]+[1-5], [가-힣]+";
        assertThat(matches(re, successTest)).isEqualTo(true);
        assertThat(matches(re, fileTest)).isEqualTo(false);
    }

    @Test
    void 서비스_선택_정규화(){
        String successTest = "1";
        String failTest = "1.";
        String re = "[1-3|Q]";
        assertThat(matches(re, successTest)).isEqualTo(true);
        assertThat(matches(re, failTest)).isEqualTo(false);
    }

    @Test
    void 재매칭_여부_확인_정규화(){
        String successTest = "네";
        String failTest = "yes";
        String re = "[네|아니오]";
        assertThat(matches(re, successTest)).isEqualTo(true);
        assertThat(matches(re, failTest)).isEqualTo(false);
    }

}
