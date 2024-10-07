package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Bejegyzes {
    private String _szerzo;
    private String _tartalom;
    private int _likeok;
    private LocalDateTime _letrejott;
    private LocalDateTime _szerkesztve;

    Bejegyzes(String szerzo, String tartalom) {
        this._szerzo = szerzo;
        this._tartalom = tartalom;
        this._likeok = 0;
        this._letrejott = LocalDateTime.now();
        this._szerkesztve = LocalDateTime.now();
    }

    public String getSzerzo(){
        return this._szerzo;
    }

    public String getTartalom(){
        return this._tartalom;
    }

    public void setTartalom(String tartalom){
        this._tartalom = tartalom;
        this._szerkesztve = LocalDateTime.now();
    }

    public int getLikeok(){
        return this._likeok;
    }

    public LocalDateTime getLetrejott(){
        return this._letrejott;
    }

    public LocalDateTime getSzerkesztve(){
        return this._szerkesztve;
    }

    public void like(){
        this._likeok++;
    }

    @Override
    public String toString() {
        return this._szerzo + "-" + this._likeok + "-" + this._letrejott + "\n" + ((this._szerkesztve.isEqual(this._letrejott)) ? "" : "Szerkesztve: " + this._szerkesztve) + "\n" + this._tartalom;
    }
}