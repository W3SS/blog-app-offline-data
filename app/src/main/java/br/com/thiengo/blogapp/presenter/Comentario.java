package br.com.thiengo.blogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Comentario extends RealmObject implements Parcelable {
    public static final String MENSAGEM_KEY = "comentario";
    public static final String COMENTARIOS_KEY = "comentarios";

    @PrimaryKey
    private long id;
    private String mensagem;
    private User user;
    private Post post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comentario() {}


    public Comentario gerarCopia(){
        Comentario c = new Comentario();
        c.setId( id );
        c.setMensagem( mensagem );
        c.setUser( user.gerarCopia() );
        return c;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.mensagem);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.post, flags);
    }

    protected Comentario(Parcel in) {
        this.id = in.readLong();
        this.mensagem = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.post = in.readParcelable(Post.class.getClassLoader());
    }

    public static final Creator<Comentario> CREATOR = new Creator<Comentario>() {
        @Override
        public Comentario createFromParcel(Parcel source) {
            return new Comentario(source);
        }

        @Override
        public Comentario[] newArray(int size) {
            return new Comentario[size];
        }
    };
}
