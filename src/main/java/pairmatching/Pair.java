package pairmatching;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Pair {

    private final LinkedHashSet<String> crews = new LinkedHashSet<>();

    public Boolean equalPair(Pair pair){
        return crews.containsAll(pair.getCrews());
    }

    public Pair(String crew1, String crew2) {
        addCrew(crew1);
        addCrew(crew2);
    }

    public void addCrew(String crew){
        crews.add(crew);
    }

    public LinkedHashSet<String> getCrews() {
        return crews;
    }

    public String pairToString(){
        return String.join(" : ", crews);
    }
}
