package br.com.thiengo.blogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class User extends RealmObject implements Parcelable {
    public static final String NOME_KEY = "nome";

    @PrimaryKey
    private long id;
    private String nome;
    private String uriImagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public void setUriImagem(String uriImagem) {
        this.uriImagem = uriImagem;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.uriImagem);
    }

    public User() {}

    protected User(Parcel in) {
        this.id = in.readLong();
        this.nome = in.readString();
        this.uriImagem = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public User gerarCopia(){
        User u = new User();
        u.setId( id );
        u.setNome( nome );
        u.setUriImagem( uriImagem );
        return u;
    }
}
