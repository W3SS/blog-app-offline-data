package br.com.thiengo.blogapp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.thiengo.blogapp.presenter.Comentario;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterImpl;
import br.com.thiengo.blogapp.presenter.PresenterMain;
import cz.msebera.android.httpclient.Header;


public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://192.168.25.221:8888/blog-app/ctrl/CtrlPost.php";
    public static final String METODO_KEY = "metodo";
    public static final String METODO_POSTS = "get-posts";
    public static final String METODO_COMENTARIOS = "get-comentarios";
    public static final String METODO_UPDATE_FAVORITO = "update-favorito-post";
    public static final String METODO_NOVO_COMENTARIO = "novo-comentario";

    private PresenterImpl presenter;
    private Database database;
    private Post post;


    public JsonHttpRequest( PresenterImpl presenter ){
        this.presenter = presenter;
        database = new Database();
    }
    public JsonHttpRequest( PresenterImpl presenter, Post post ){
        this.presenter = presenter;
        database = new Database();
        this.post = post;
    }

    @Override
    public void onStart() {


        if( presenter instanceof PresenterMain){
            presenter.updateListaRecycler( database.getPosts() );
        }
        else{
            presenter.updateListaRecycler( database.getComentarios(post) );
        }
        presenter.showProgressBar( true );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Gson gson = new Gson();

        if( ehPostFavoritoUpdate( headers ) ){
            Post p = gson.fromJson( response.toString(), Post.class );
            presenter.updateItemRecycler( p );
        }
        else {
            Comentario c = gson.fromJson( response.toString(), Comentario.class );
            presenter.updateListaRecycler( c );
        }
    }

    private boolean ehPostFavoritoUpdate(Header[] headers){
        for( Header h : headers ){
            if( h.getName().equalsIgnoreCase("X-Blog-App")
                    && (h.getValue().equalsIgnoreCase(METODO_UPDATE_FAVORITO)
                        || h.getValue().equalsIgnoreCase(METODO_POSTS)) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();

        if( ehPostFavoritoUpdate( headers ) ){
            successPosts( gson, response );
        }
        else {
            successComentarios( gson, response );
        }
    }

    private void successPosts( Gson gson, JSONArray response ){
        ArrayList<Post> posts = new ArrayList<>();
        Post p;

        for( int i = 0; i < response.length(); i++ ){
            try{
                p = gson.fromJson( response.getJSONObject( i ).toString(), Post.class );
                posts.add( p );
            }
            catch(JSONException e){}
        }
        database.savePosts( posts );
        presenter.updateListaRecycler( posts );
    }

    private void successComentarios( Gson gson, JSONArray response ){
        ArrayList<Comentario> comentarios = new ArrayList<>();
        Comentario c;

        for( int i = 0; i < response.length(); i++ ){
            try{
                c = gson.fromJson( response.getJSONObject( i ).toString(), Comentario.class );
                comentarios.add( c );
            }
            catch(JSONException e){}
        }
        database.saveComentarios( comentarios );
        presenter.updateListaRecycler( comentarios );
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    @Override
    public void onFinish() {
        presenter.showProgressBar( false );
    }
}
