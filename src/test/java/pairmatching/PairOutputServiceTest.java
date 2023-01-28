//package pairmatching;
//
//import camp.nextstep.edu.missionutils.Randoms;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.Collectors;
//
//import static camp.nextstep.edu.missionutils.Randoms.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class PairOutputServiceTest {
//
//    PairInputService pairInputService = new PairInputService();
//    final HashMap<Integer, Match> matches = new HashMap<>();
//
//    @Test
//    void 최초_페어매칭() throws IOException {
//        String input = "프론트엔드, 레벨1, 자동차경주";
//        String[] options = input.split(", ");
//
//        Course course = Course.findCourseByName(options[0]);
//        Level level = Course.findLevelByName(options[1]);
//        String mission = options[2];
//
//        List<String> crews = pairInputService.crewFileRead(course);
//
//        Match newMatch = new Match(course, level, mission);
//
//        List<Match> findMatchByLevel = matches.values().stream()
//                .filter(match -> match.getLevel() == level).collect(Collectors.toList());
//
//        List<String> crewList = shuffle(crews);
//
//        for(int i = 0; i < crewList.size()-2; i+=2){
//            Pair newPair = new Pair();
//            newPair.addCrew(crewList.get(i));
//            newPair.addCrew(crewList.get(i+1));
//            if(i+2 == crewList.size()-1){
//                newPair.addCrew(crewList.get(crewList.size() - 1));
//            }
//            if(check(newPair, findMatchByLevel)){
//                newMatch.addPair(newPair);
//            }
//        }
//
//        matches.put(input.hashCode(), newMatch);
//
//    }
//
//
//    @Test
//    void 같은레벨_다른미션_페어() throws IOException {
//        최초_페어매칭();
//
//        String input = "프론트엔드, 레벨1, 로또";
//        String[] options = input.split(", ");
//
//        Course course = Course.findCourseByName(options[0]);
//        Level level = Course.findLevelByName(options[1]);
//        String mission = options[2];
//
//        List<String> crews = pairInputService.crewFileRead(course);
//
//        List<String> crewList = shuffle(crews);
//
//        Match newMatch = new Match(course, level, mission);
//
//        List<Match> findMatchByLevel = matches.values().stream()
//                .filter(match -> match.getLevel() == level).collect(Collectors.toList());
//
//
//        for(int i = 0; i < crewList.size()-2; i+=2){
//            Pair newPair = new Pair();
//            newPair.addCrew(crewList.get(i));
//            newPair.addCrew(crewList.get(i+1));
//            if(i+2 == crewList.size()-1){
//                newPair.addCrew(crewList.get(crewList.size() - 1));
//            }
//            if(check(newPair, findMatchByLevel)){
//                newMatch.addPair(newPair);
//            }
//        }
//
//        matches.put(input.hashCode(), newMatch);
//    }
//
//    private Boolean check(Pair newPair, List<Match> findMatchByLevel) {
//        for (Match match : findMatchByLevel) {
//            if(match.getPairs().stream()
//                    .anyMatch(pair -> pair.equalPair(newPair))) {
//                throw new IllegalStateException();
//            }
//        }
//        return true;
//    }
//
//}