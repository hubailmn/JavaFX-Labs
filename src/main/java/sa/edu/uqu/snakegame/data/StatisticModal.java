package sa.edu.uqu.snakegame.data;

import java.io.Serializable;

public class StatisticModal implements Serializable {

    long Score;
    long time;

    public StatisticModal(long score, long time) {
        this.Score = score;
        this.time = time;
    }

    public long getScore() {
        return Score;
    }

    public long getTime() {
        return time;
    }
}
