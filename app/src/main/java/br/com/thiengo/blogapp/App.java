package br.com.thiengo.blogapp;

import android.app.Application;

import br.com.thiengo.blogapp.domain.MyObjectBox;
import br.com.thiengo.blogapp.domain.User;
import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by viniciusthiengo on 04/02/17.
 */

public class App extends Application {

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        boxStore = MyObjectBox.builder().androidContext(App.this).build();
    }

    public Box getBox(Class classe ) {
        return boxStore.boxFor(classe);
    }
}
