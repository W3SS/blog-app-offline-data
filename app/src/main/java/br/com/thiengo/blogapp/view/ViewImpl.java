package br.com.thiengo.blogapp.view;

import br.com.thiengo.blogapp.presenter.Post;


public interface ViewImpl {
    public void showToast( String mensagem );
    public void showProgressBar( int visibilidade );
    public void updateListaRecycler();
    public void updateItemRecycler( int posicao );
    public void updateEhFavoritoPost( Post post );
}
