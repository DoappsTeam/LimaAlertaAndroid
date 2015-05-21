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
}
