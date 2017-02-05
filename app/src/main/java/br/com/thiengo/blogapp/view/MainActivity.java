package br.com.thiengo.blogapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterMain;

public class MainActivity extends AppCompatActivity
        implements ViewImpl {

    private PresenterMain presenter;
    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = PresenterMain.getInstance();
        presenter.setView( this );
        intiViews();

        presenter.retrievePosts( savedInstanceState );
    }

    private void intiViews() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_posts);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration( divider );

        adapter = new PostsAdapter( this, presenter.getPosts() );
        recyclerView.setAdapter( adapter );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.updateItemRecycler( (Post) data.getParcelableExtra(Post.KEY) );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(POSTS_KEY, presenter.getPosts());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateEhFavoritoPost( Post post ){
        presenter.updateEhFavoritoPost( post );
    }

    public void updateListaRecycler(){
        adapter.notifyDataSetChanged();
    }

    public void updateItemRecycler( int posicao ){
        adapter.notifyItemChanged( posicao );
    }

    public void showToast( String mensagem ){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar( int visibilidade ){
        findViewById(R.id.pb_loading).setVisibility( visibilidade );
    }

    public void itemClicado( int id, Post post ){
        presenter.itemClicado( id, post );
    }
}
