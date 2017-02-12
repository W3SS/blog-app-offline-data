package br.com.thiengo.blogapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.blogapp.presenter.Comentario;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterImpl;
import br.com.thiengo.blogapp.presenter.User;


public class Model {
    private AsyncHttpClient asyncHttpClient;
    private PresenterImpl presenter;

    public Model(PresenterImpl presenter ){
        asyncHttpClient = new AsyncHttpClient();
        //asyncHttpClient.setTimeout(5000);
        asyncHttpClient.setMaxRetriesAndTimeout(0, 5000);

        this.presenter = presenter;
    }

    public void retrievePosts() {
        RequestParams requestParams = new RequestParams(
                JsonHttpRequest.METODO_KEY,
                JsonHttpRequest.METODO_POSTS);

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }

    public void updateEhFavoritoPost(Post post) {
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, JsonHttpRequest.METODO_UPDATE_FAVORITO );
        requestParams.put( Post.ID_KEY, post.getId() );
        requestParams.put( Post.EH_FAVORITO_KEY, post.isEhFavorito() );

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }

    public void retrieveComentarios( Post post ) {
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, JsonHttpRequest.METODO_COMENTARIOS );
        requestParams.put( Post.ID_KEY, post.getId() );

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter, post ));
    }

    public void insertComentario(Post post, Comentario comentario) {
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, JsonHttpRequest.METODO_NOVO_COMENTARIO );
        requestParams.put( Post.ID_KEY, post.getId() );
        requestParams.put( Comentario.MENSAGEM_KEY, comentario.getMensagem() );
        requestParams.put( User.NOME_KEY, comentario.getUser().getNome() );

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }
}
