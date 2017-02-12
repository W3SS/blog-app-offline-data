package br.com.thiengo.blogapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.Comentario;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterPost;

public class PostActivity extends AppCompatActivity
        implements View.OnClickListener, ViewImpl {

    private PresenterPost presenter;
    private Post post;
    private FloatingActionButton fab;
    private ComentariosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        post = getIntent().getParcelableExtra( Post.KEY);
        presenter = PresenterPost.getInstance();
        presenter.setView( this );
        presenter.retrieveComentarios( post, savedInstanceState);

        intiViews();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(
                Comentario.COMENTARIOS_KEY,
                presenter.getComentarios() );
        super.onSaveInstanceState(outState);
    }

    private void intiViews(){
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle( post.getTitulo() );

        ImageView ivCabecalho = (ImageView) findViewById(R.id.iv_cabecalho);
        Picasso.with( this )
                .load( post.getUriImagemBanner() )
                .into( ivCabecalho );

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setImageResource( post.getEhFavoritoIconeBig() );

        TextView tvDescricao = (TextView) findViewById(R.id.tv_descricao);
        tvDescricao.setText( post.getConteudo() );

        initComentarios();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        updateEhFavoritoPost(post);
    }

    private void initComentarios(){
        RecyclerView rvComentarios = (RecyclerView) findViewById(R.id.rv_comentarios);
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        adapter = new ComentariosAdapter( this, presenter.getComentarios() );

        layoutManager.setAutoMeasureEnabled(true);
        rvComentarios.setNestedScrollingEnabled(false);
        rvComentarios.setHasFixedSize(false);
        rvComentarios.setLayoutManager( layoutManager );
        rvComentarios.setAdapter(adapter);
    }

    public void enviarComentario( View view ){
        presenter.enviarComentario( post );
    }

    @Override
    public void updateEhFavoritoPost(Post post) {
        presenter.updateEhFavoritoPost(post);
    }

    @Override
    public void updateListaRecycler() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateItemRecycler(int posicao) {
        fab.setImageResource( post.getEhFavoritoIconeBig() );
    }

    @Override
    public void showToast(String mensagem) {}

    @Override
    public void showProgressBar(int visibilidade) {}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra( Post.KEY, post );
        setResult(Post.POST_CODE, intent);
        super.onBackPressed();
    }
}
