package com.choubao.www.sqlite_gameplayer.db;

import android.provider.BaseColumns;

/**
 * Created by choubao on 17/4/28.
 */

public final class GameMetaData {
    private GameMetaData(){}
    public static abstract class GamePlayer implements BaseColumns {
        public static final String TABLE_NAME="player_table";
        public static final String PLAYER="player";
        public static final String SCORE="score";
        public static final String LEVEL="level";
    }
}
