package edu.jlu.fuliang.domain;

import java.util.HashSet;
import java.util.Set;

public class CompositeSinger extends Singer {
    private Set<SingleSinger> singleSingers = new HashSet<SingleSinger>();

    public Set<SingleSinger> getSingleSingers() {
        return singleSingers;
    }

    public void setSingleSingers(Set<SingleSinger> singleSingers) {
        this.singleSingers = singleSingers;
    }

    public void addSinger(SingleSinger singleSinger) {
        singleSingers.add(singleSinger);
    }
}
