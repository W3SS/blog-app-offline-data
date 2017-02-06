package br.com.thiengo.blogapp.presenter;

import android.content.Context;

import br.com.thiengo.blogapp.view.ViewImpl;


public interface PresenterImpl {
    public void setView( ViewImpl view );
    public Context getContext();
    public void updateEhFavoritoPost(Post post);
    public void showToast(String mensagem);
    public void showProgressBar(boolean status);
    public void updateItemRecycler(Post p);
    public void updateListaRecycler( Object object );
    public void enviarComentario( Post post );
}
