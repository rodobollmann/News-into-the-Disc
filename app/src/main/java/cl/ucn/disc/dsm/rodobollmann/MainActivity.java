/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.dsm.rodobollmann.databinding.ActivityMainBinding;

/**
 *  The Main class.
 *
 * @author Rodolfo Bollmann Checura.
 */
public final class MainActivity extends AppCompatActivity {

    /**
     *  The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     * The Binding
     */
    private ActivityMainBinding binding;

    /**
     * @param savedInstanceState the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflate the xml
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        // Set the toolbar
        this.setSupportActionBar(this.binding.toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.debug("onStart ..");
    }

}