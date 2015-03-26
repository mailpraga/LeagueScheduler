package com.pragjan.leaguescheduler;

import java.util.ArrayList;
import java.util.List;

public class player {

    private int _id;
    private String _name;
    private List<String> _matchPartner;
    private List<String> _matchEnemy;
    private int _matchPartnerInd;
    private int _matchEnemyInd;
    private int _point;
    private int _matchPlayed;
    private int _win;
    private int _loss;
    private int _draw;

    public player(){
    }

    public player(String name, int NumEnemy/*, int Round*/){
        this._name = name;
        this._matchPartner = new ArrayList<String>(NumEnemy);
        this._matchEnemy = new ArrayList<String>(NumEnemy);
        this._matchPartnerInd = 0;
        this._matchEnemyInd = 0;
        this._point = 0;
        this._matchPlayed = 0;
        this._win = 0;
        this._loss = 0;
        this._draw = 0;

    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public List<String> get_matchPartner() {
        return _matchPartner;
    }

    public void set_matchPartner(List<String> _matchPartner) {
        this._matchPartner = _matchPartner;
    }

    public List<String> get_matchEnemy() {
        return _matchEnemy;
    }

    public void set_matchEnemy(List<String> _matchEnemy) {
        this._matchEnemy = _matchEnemy;
    }

    public int get_matchPartnerInd() {
        return _matchPartnerInd;
    }

    public void set_matchPartnerInd(int _matchPartnerInd) {
        this._matchPartnerInd = _matchPartnerInd;
    }

    public int get_matchEnemyInd() {
        return _matchEnemyInd;
    }

    public void set_matchEnemyInd(int _matchEnemyInd) {
        this._matchEnemyInd = _matchEnemyInd;
    }

    public int get_point() {
        return _point;
    }

    public void set_point(int _point) {
        this._point = _point;
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

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

}
