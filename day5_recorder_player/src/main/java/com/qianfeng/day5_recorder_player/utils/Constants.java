package com.qianfeng.day5_recorder_player.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by xray on 16/9/30.
 */

public class Constants {

    public static final String FILE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "record_test.3gp";
}
