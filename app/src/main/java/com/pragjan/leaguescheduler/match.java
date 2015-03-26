package com.pragjan.leaguescheduler;

public class match {

    private int _id;
    private String _home1;
    private String _home2;
    private String _guest1;
    private String _guest2;

    public match(String home1, String home2,
                String guest1, String guest2) {
        this._home1 = home1;
        this._home2 = home2;

        this._guest1 = guest1;
        this._guest2 = guest2;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_home1() {
        return _home1;
    }

    public void set_home1(String _home1) {
        this._home1 = _home1;
    }

    public String get_home2() {
        return _home2;
    }

    public void set_home2(String _home2) {
        this._home2 = _home2;
    }

    public String get_guest1() {
        return _guest1;
    }

    public void set_guest1(String _guest1) {
        this._guest1 = _guest1;
    }

    public String get_guest2() {
        return _guest2;
    }

    public void set_guest2(String _guest2) {
        this._guest2 = _guest2;
    }
}

