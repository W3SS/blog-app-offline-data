package br.com.thiengo.blogapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.Comentario;


public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {

    private Context context;
    private List<Comentario> comentarios;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView civPerfil;
        TextView tvNome;
        TextView tvComentario;

        ViewHolder(View itemView) {
            super(itemView);

            civPerfil = (CircularImageView) itemView.findViewById(R.id.civ_perfil);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            tvComentario = (TextView) itemView.findViewById(R.id.tv_comentario);
        }

        private void setData( Comentario comentario ){
            Picasso.with( context )
                    .load( comentario.getUser().getUriImagem() )
                    .into( civPerfil );

            tvNome.setText( comentario.getUser().getNome() );
            tvComentario.setText( comentario.getMensagem() );
        }
    }

    public ComentariosAdapter(Context context, List<Comentario> comentarios){
        this.context = context;
        this.comentarios = comentarios;
    }

    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from( context )
                    .inflate(R.layout.item_comentario, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( comentarios.get( position ) );
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}
