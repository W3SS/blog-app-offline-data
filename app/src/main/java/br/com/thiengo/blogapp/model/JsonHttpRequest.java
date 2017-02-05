package br.com.thiengo.blogapp.model;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.thiengo.blogapp.presenter.Comentario;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterImpl;
import cz.msebera.android.httpclient.Header;


public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://192.168.25.221:8888/blog-app/ctrl/CtrlPost.php";
    public static final String METODO_KEY = "metodo";

    private PresenterImpl presenter;


    public JsonHttpRequest( PresenterImpl presenter ){
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
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
                    && h.getName().equalsIgnoreCase("update-favorito-post") ){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();
        ArrayList<Post> posts = new ArrayList<>();
        Post p;

        for( int i = 0; i < response.length(); i++ ){
            try{
                p = gson.fromJson( response.getJSONObject( i ).toString(), Post.class );
                posts.add( p );
            }
            catch(JSONException e){}
        }
        presenter.updateListaRecycler( posts );
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        presenter.showToast( responseString );
    }

    @Override
    public void onFinish() {
        presenter.showProgressBar( false );
    }
}
