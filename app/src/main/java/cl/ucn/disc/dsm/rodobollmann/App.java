/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * The Main App
 */
public final class App extends Application {

    /**
     * Initialize the application.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
