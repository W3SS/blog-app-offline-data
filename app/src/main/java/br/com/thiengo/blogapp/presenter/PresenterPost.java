package br.com.thiengo.blogapp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.blogapp.model.Model;
import br.com.thiengo.blogapp.view.ComentarioFragment;
import br.com.thiengo.blogapp.view.ViewImpl;


public class PresenterPost implements PresenterImpl {

    private static PresenterPost instance;
    private Model model;
    private ViewImpl view;
    private List<Comentario> comentarios = new ArrayList<>();


    private PresenterPost(){
        model = new Model( this );
    }

    public static PresenterPost getInstance(){
        if( instance == null ){
            instance = new PresenterPost();
        }
        return instance;
    }

    @Override
    public void setView( ViewImpl view ){
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void updateEhFavoritoPost(Post post) {
        post.setEhFavorito( !post.isEhFavorito() );
        model.updateEhFavoritoPost( post );
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast( mensagem );
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar( visibilidade );
    }

    @Override
    public void updateItemRecycler(Post p) {
        view.updateItemRecycler(0);
    }

    @Override
    public void updateListaRecycler(Object object) {
        if( object instanceof Comentario ){
            Comentario c = (Comentario) object;
            comentarios.add( 0, c );
        }
        else{
            List<Comentario> comentsCarregados = (List<Comentario>) object;
            comentarios.clear();
            comentarios.addAll( comentsCarregados );
        }
        view.updateListaRecycler();
    }

    @Override
    public void enviarComentario( Post post ){
        FragmentManager fragManager = ((AppCompatActivity) getContext())
                .getSupportFragmentManager();
        FragmentTransaction ft = fragManager.beginTransaction();
        Fragment fragAnterior = fragManager.findFragmentByTag( ComentarioFragment.KEY );

        if (fragAnterior != null) {
            ft.remove(fragAnterior);
        }
        ft.addToBackStack(null);

        Bundle dados = new Bundle();
        dados.putParcelable( Post.KEY, post );

        DialogFragment dialog = new ComentarioFragment();
        dialog.setArguments(dados);
        dialog.show(ft, ComentarioFragment.KEY);
    }

    public void retrieveComentarios( Post post, Bundle savedInstanceState) {
        if( savedInstanceState != null ){
            comentarios = savedInstanceState.getParcelableArrayList( Comentario.COMENTARIOS_KEY );
            return;
        }
        model.retrieveComentarios( post );
    }

    public ArrayList<Comentario> getComentarios() {
        return (ArrayList<Comentario>) comentarios;
    }

    public void insertComentario(Post post, String nome, String mensagem){
        User user = new User();
        user.setNome( nome );

        Comentario comentario = new Comentario();
        comentario.setMensagem( mensagem );
        comentario.setUser( user );

        model.insertComentario( post, comentario );
    }
}
