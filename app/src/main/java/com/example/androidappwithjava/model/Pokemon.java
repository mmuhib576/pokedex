package com.example.androidappwithjava.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemons")
public class Pokemon {

    private String img;

    @ColumnInfo(name = "pokemonNumber")
    private String num;

    @ColumnInfo(name = "pokemonName")
    private String name;

    @PrimaryKey
    @ColumnInfo(name = "pokemonId")
    private Integer id;

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
