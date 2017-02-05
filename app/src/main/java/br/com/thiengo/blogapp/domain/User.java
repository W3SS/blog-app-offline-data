package br.com.thiengo.blogapp.domain;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Generated;

/**
 * Created by viniciusthiengo on 04/02/17.
 */

@Entity
public class User {
    @Id(assignable = true)
    private long id;
    private String name;
    private int idade;
    @Generated(hash = 103426632)
    public User(long id, String name, int idade) {
        this.id = id;
        this.name = name;
        this.idade = idade;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

}
