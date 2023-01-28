package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Course course;
    private Level level;
    private String mission;
    private List<Pair> pairs = new ArrayList<>();

    public Match(Course course, Level level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    /* 페어 출력 */
    public void printPair(){
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairs) {
            System.out.println(pair.pairToString());
        }
        System.out.println();
    }

    public void addPair(Pair pair){
        pairs.add(pair);
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
