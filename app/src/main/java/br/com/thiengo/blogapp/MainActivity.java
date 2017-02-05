package br.com.thiengo.blogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.thiengo.blogapp.domain.User;
import br.com.thiengo.blogapp.domain.User_;
import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {
    private Box<User> boxUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boxUser = ((App) getApplication()).getBox(User.class);

        /*User user = new User();
        user.setId(1);
        user.setName("Carceres");
        user.setIdade(26);

        boxUser.put(user);*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*List<User> users = boxUser.query().startsWith(User_.name, "carceres").build().find();
        for( User u : users ){
            Log.i("log", "ID: "+u.getId());
            Log.i("log", "Name: "+u.getName());
            Log.i("log", "Idade: "+u.getIdade());
            u.setName( u.getName()+" Silva" );
            boxUser.put( u );
        }*/
    }
}
