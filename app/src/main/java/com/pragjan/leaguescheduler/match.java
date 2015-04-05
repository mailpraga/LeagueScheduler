package com.pragjan.leaguescheduler;

public class match {

    private int _id;
    private int _matchNo;
    private String _home1;
    private String _home2;
    private int _homeGoal;
    private String _guest1;
    private String _guest2;
    private int _guestGoal;

    public match(int matchNo, String home1, String home2, int homeGoal,
                 String guest1, String guest2, int guestGoal) {
        this._matchNo = matchNo;
        this._home1 = home1;
        this._home2 = home2;
        this._homeGoal = homeGoal;

        this._guest1 = guest1;
        this._guest2 = guest2;
        this._guestGoal = guestGoal;
    }

    public int get_matchNo() {
        return _matchNo;
    }

    public void set_matchNo(int _matchNo) {
        this._matchNo = _matchNo;
    }

    public String get_home1() {
        return _home1;
    }

    public String get_home2() {
        return _home2;
    }

    public String get_guest1() {
        return _guest1;
    }

    public String get_guest2() {
        return _guest2;
    }

    public int get_homeGoal() {
        return _homeGoal;
    }

    public void set_homeGoal(int _homeGoal) {
        this._homeGoal = _homeGoal;
    }

    public int get_guestGoal() {
        return _guestGoal;
    }

    public void set_guestGoal(int _guestGoal) {
        this._guestGoal = _guestGoal;
    }
}

