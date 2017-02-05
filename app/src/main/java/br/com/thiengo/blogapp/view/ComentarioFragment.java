package br.com.thiengo.blogapp.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import br.com.thiengo.blogapp.R;
import br.com.thiengo.blogapp.presenter.Post;
import br.com.thiengo.blogapp.presenter.PresenterPost;


public class ComentarioFragment extends DialogFragment
        implements View.OnClickListener {

    public static final String KEY = "comentario_fragment";

    private Post post;
    private EditText etNome;
    private EditText etComentario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = getArguments().getParcelable( Post.KEY);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog( savedInstanceState );
        dialog.getWindow().requestFeature( Window.FEATURE_NO_TITLE );
        setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_comentario, null, false);

        TextView tvCabecalho = (TextView) view.findViewById( R.id.tv_cabecalho );
        tvCabecalho.setText( Html.fromHtml("Comemtário em <b>"+post.getTitulo()+"</b>:") );

        etNome = (EditText) view.findViewById( R.id.et_nome );
        etComentario = (EditText) view.findViewById( R.id.et_comentario );

        View tvEnviar = view.findViewById(R.id.tv_enviar);
        tvEnviar.setOnClickListener( this );

        View tvCancelar = view.findViewById(R.id.tv_cancelar);
        tvCancelar.setOnClickListener( this );

        return view;
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.tv_enviar ){
            PresenterPost presenter = PresenterPost.getInstance();
            presenter.insertComentario(post,
                    etNome.getText().toString(),
                    etComentario.getText().toString());
        }
        dismiss();
    }
}
