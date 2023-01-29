package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.dto.PairDto;
import pairmatching.exceptions.NotFoundPairs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.Randoms.*;

public class PairOutputService {

    private final HashMap<Integer, Match> matches = new HashMap<>();
    private final PairInputService pairInputService = new PairInputService();

    /* 기능 선택 */
    public void start() throws IOException {
        while(true){
            String input = pairInputService.selectServicePrint();
            try{
                if(input.equals("1")) pairMatching();
                if(input.equals("2")) findPair();
                if(input.equals("3")) pairInitialization();
                if(input.equals("Q")) return;
            }catch (NotFoundPairs e){
                System.out.println("[ERROR] 찾으시는 페어 정보가 없습니다.");
            }
            catch (IllegalStateException e){
                System.out.println("[ERROR] 매칭 실패.");
            }
        }
    }

    /* 1. 페어 매칭 */
    public void pairMatching() throws IOException {
        PairDto pairDto = pairInputService.selectOptionPrint();
        List<String> crews = pairInputService.crewFileRead(pairDto.getCourse());
        if (checkRematching(pairDto)) return;
        Match match = createMatch(pairDto, shuffle(crews));
        matches.put(pairDto.getInput().hashCode(), match);
        match.printPair();
    }

    /* 페어 매칭 생성 */
    private Match createMatch(PairDto pairDto, List<String> crewList) {
        Match match = pairDto.toMatch();
        int count = 0;
        for(int i = 0; i <= limitIterator(crewList.size()); i+=2){
            if(count == 3) throw new IllegalStateException();
            Pair pair = createPair(i, crewList);
            if(checkPair(pair, pairDto.getLevel())){
                i = 0;
                crewList = shuffle(crewList);
                count++;
                continue;
            }
            match.addPair(pair);
        }
        return match;
    }

    /* 페어 생성 */
    private Pair createPair(int i, List<String> crewList) {
        Pair pair = new Pair(crewList.get(i), crewList.get(i +1));
        if(i +2 == crewList.size()-1) {
            pair.addCrew(crewList.get(crewList.size()-1));
        }
        return pair;
    }

    /* 리매칭 여부 확인 */
    private boolean checkRematching(PairDto pairDto) {
        if(matches.get(pairDto.getInput().hashCode()) != null){
            String answer = pairInputService.selectRematchPrint();
            if(answer.equals("아니오")) return true;
        }
        return false;
    }

    /* 2. 페어 조회 */
    private void findPair() {
        PairDto pairDto = pairInputService.selectOptionPrint();
        if(matches.get(pairDto.getInput().hashCode()) == null) throw new NotFoundPairs();
        matches.get(pairDto.getInput().hashCode()).printPair();
    }

    /* 3. 페어 초기화 */
    public void pairInitialization(){
        matches.clear();
    }

    /* 같은 레벨에서 만난 적 있는 페어 인지 */
    public Boolean checkPair(Pair pair, Level level) {
        List<Match> sameLevelMatch = findSameLevelMatch(level);
        for (Match match : sameLevelMatch) {
            if(match.getPairs().stream()
                    .anyMatch(i -> i.equalPair(pair))) {
                return true;
            }
        }
        return false;
    }

    /* 같은 레벨의 매칭 정보가 있는지 확인 */
    public List<Match> findSameLevelMatch(Level level){
        return matches.values().stream()
                .filter(match -> match.getLevel() == level).collect(Collectors.toList());
    }

    public int limitIterator(int size){
        if(size % 2 == 1) return size - 3;
        if(size % 2 == 0) return size - 2;
        throw new IllegalStateException();
    }


}
