package pairmatching;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Pair {

    private final LinkedHashSet<String> crews = new LinkedHashSet<>();

    public Boolean equalPair(Pair pair){
        return crews.containsAll(pair.getCrews());
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
