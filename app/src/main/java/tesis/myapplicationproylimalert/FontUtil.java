package tesis.myapplicationproylimalert;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by doapps on 5/21/15.
 */
public class FontUtil {

    public static Typeface setBebas(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/bebeas.ttf");
    }

    public static Typeface setQuenn(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/queen_camelot_regular.ttf");
    }

    public static Typeface setNeuropol(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/neuropol_x_rg.ttf");
    }
    public static Typeface setGratis(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/gratis.ttf");
    }
    public static Typeface setJoystick(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/joystick.otf");
    }
}
