package com.sanqing.bean;

import java.util.List;

public class VoteResult {
    private Vote vote;
    private List<VoteOption> voteOptions;

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }
}
