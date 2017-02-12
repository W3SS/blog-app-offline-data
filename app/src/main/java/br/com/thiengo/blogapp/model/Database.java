package br.com.thiengo.blogapp.model;


import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.blogapp.presenter.Comentario;
import br.com.thiengo.blogapp.presenter.Post;
import io.realm.Realm;
import io.realm.RealmResults;

public class Database {
    private Realm realm;

    public Database(){
        realm = Realm.getDefaultInstance();
    }


    public void savePosts(List<Post> posts){
        realm.beginTransaction();
        for( Post post : posts ){
            realm.copyToRealmOrUpdate(post);
        }
        realm.commitTransaction();
    }
    public List<Post> getPosts(){
        RealmResults<Post> postsRealm = realm.where(Post.class).findAll();
        List<Post> posts = new ArrayList<>();

        for( Post post : postsRealm ){
            posts.add( post.gerarCopia() );
        }
        return posts;
    }


    public void saveComentarios(List<Comentario> comentarios){
        realm.beginTransaction();
        for( Comentario comentario : comentarios ){
            realm.copyToRealmOrUpdate( comentario );
        }
        realm.commitTransaction();
    }
    public List<Comentario> getComentarios( Post post ){
        RealmResults<Comentario> comentariosRealm = realm
                .where(Comentario.class)
                .equalTo("post.id", post.getId())
                .findAll();
        List<Comentario> comentarios = new ArrayList<>();

        for( Comentario comentario : comentariosRealm ){
            comentarios.add( comentario.gerarCopia() );
        }
        return comentarios;
    }
}
