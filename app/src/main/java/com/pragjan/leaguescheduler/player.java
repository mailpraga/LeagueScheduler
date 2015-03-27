package com.pragjan.leaguescheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class player {

    private int _id;
    private String _name;
    private List<String> _matchPartner;
    private int _NumEnemy;
    private int _point;
    private int _matchPlayed;
    private int _win;
    private int _loss;
    private int _draw;

    public player(String name, int matchPlayed, int win, int loss, int draw, int point){
        this._name = name;
        this._matchPartner = new ArrayList<>();
        this._NumEnemy = matchPlayed;
        this._point = point;
        this._matchPlayed = matchPlayed;
        this._win = win;
        this._loss = loss;
        this._draw = draw;
    }

    public player(String name, int NumPlayer/*, int Round*/){
        this._name = name;
        this._matchPartner = new ArrayList<>();
        this._NumEnemy = NumPlayer-1;
        this._point = 0;
        this._matchPlayed = 0;
        this._win = 0;
        this._loss = 0;
        this._draw = 0;
    }

    public List<String> get_matchPartner() {
        return _matchPartner;
    }

    public void set_matchPartner(List<String> _matchPartner) {
        this._matchPartner = _matchPartner;
    }
    public void appendMatchPartner(String matchPartner) {
        List<String> alist = this.get_matchPartner();
        alist.add(matchPartner);
        this.set_matchPartner(alist);
    }

    public void matchDraw(){
        set_draw(get_draw() + 1);
        set_matchPlayed(get_matchPlayed() + 1);
        set_point(get_point() + 2);
    }

    public void matchWin(){
        set_win(get_win() + 1);
        set_matchPlayed(get_matchPlayed() + 1);
        set_point(get_point() + 3);
    }

    public void matchLoss(){
        set_loss(get_loss() + 1);
        set_matchPlayed(get_matchPlayed() + 1);
        set_point(get_point() + 1);
    }

    public int get_point() {
        return _point;
    }

    public void set_point(int _point) {
        this._point = _point;
    }

    public int get_NumEnemy() {
        return _NumEnemy;
    }
    public int get_matchPlayed() {
        return _matchPlayed;
    }

    public void set_matchPlayed(int _matchPlayed) {
        this._matchPlayed = _matchPlayed;
    }

    public int get_win() {
        return _win;
    }

    public void set_win(int _win) {
        this._win = _win;
    }

    public int get_loss() {
        return _loss;
    }

    public void set_loss(int _loss) {
        this._loss = _loss;
    }

    public int get_draw() {
        return _draw;
    }

    public void set_draw(int _draw) {
        this._draw = _draw;
    }

    public String get_name() {
        return _name;
    }

    public boolean findMatchPartner(player thePlayer) {
        if (this.get_name().equals(thePlayer.get_name())) {
            return true;
        }
        if (this.get_matchPartner().size() == 0) {
            return false;
        }
        List<String> partner = this.get_matchPartner();
        if (partner.size() < this.get_NumEnemy()) {
            return partner.contains(thePlayer.get_name());
        } else {
            return Collections.frequency(partner, thePlayer.get_name()) != 1;
        }
    }

    public void reset(){
        this.set_matchPlayed(0);
        this.set_win(0);
        this.set_loss(0);
        this.set_draw(0);
        this.set_point(0);
    }

}
