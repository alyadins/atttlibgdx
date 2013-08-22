package ru.petrsu.attt;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/10/13
 * Time: 3:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useWakelock = true;
        configuration.useGL20 = true;
        configuration.useAccelerometer = false;
        configuration.useCompass = false;

        initialize(new Attt(), configuration);
    }
}
