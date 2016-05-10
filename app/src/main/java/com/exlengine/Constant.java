package com.exlengine;

public class Constant {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HIGHT;

    public static int SYSTEM_WIDTH;
    public static int SYSTEM_HIGHT;

    public static boolean landScape;
    //橫屏
    public static int DEFULT_WIDTH=1280;
    public static int DEFULT_HIGHT=720;

    public static void setDefultScale(){
        if (landScape) {
            DEFULT_WIDTH=1280;
            DEFULT_HIGHT=720;
        }else {
            DEFULT_WIDTH=720;
            DEFULT_HIGHT=1280;
        }
    }
    static float SCREEN_WIDTH_UNIT;
    static float SCREEN_HEIGHT_UNIT;

    public static boolean Flag;
    public static void setFlag(boolean set){
        Constant.Flag=set;
    }
}


