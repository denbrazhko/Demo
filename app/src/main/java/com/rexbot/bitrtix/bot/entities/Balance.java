package com.rexbot.bitrtix.bot.entities;

public class Balance {

    private static Balance balance;

    private double ada;
    private double ark;
    private double btc;
    private double btxcrd;
    private double dash;
    private double eth;
    private double grs;
    private double met;
    private double neo;
    private double ocean;
    private double omg;
    private double qtum;
    private double usdt;
    private double vtc;
    private double xem;
    private double xmr;
    private double zec;

    public double getAda() {
        return ada;
    }

    public void setAda(double ada) {
        this.ada = ada;
    }

    public double getArk() {
        return ark;
    }

    public void setArk(double ark) {
        this.ark = ark;
    }

    public double getBtc() {
        return btc;
    }

    public void setBtc(double btc) {
        this.btc = btc;
    }

    public double getBtxcrd() {
        return btxcrd;
    }

    public void setBtxcrd(double btxcrd) {
        this.btxcrd = btxcrd;
    }

    public double getDash() {
        return dash;
    }

    public void setDash(double dash) {
        this.dash = dash;
    }

    public double getEth() {
        return eth;
    }

    public void setEth(double eth) {
        this.eth = eth;
    }

    public double getGrs() {
        return grs;
    }

    public void setGrs(double grs) {
        this.grs = grs;
    }

    public double getMet() {
        return met;
    }

    public void setMet(double met) {
        this.met = met;
    }

    public double getNeo() {
        return neo;
    }

    public void setNeo(double neo) {
        this.neo = neo;
    }

    public double getOcean() {
        return ocean;
    }

    public void setOcean(double ocean) {
        this.ocean = ocean;
    }

    public double getOmg() {
        return omg;
    }

    public void setOmg(double omg) {
        this.omg = omg;
    }

    public double getQtum() {
        return qtum;
    }

    public void setQtum(double qtum) {
        this.qtum = qtum;
    }

    public double getUsdt() {
        return usdt;
    }

    public void setUsdt(double usdt) {
        this.usdt = usdt;
    }

    public double getVtc() {
        return vtc;
    }

    public void setVtc(double vtc) {
        this.vtc = vtc;
    }

    public double getXem() {
        return xem;
    }

    public void setXem(double xem) {
        this.xem = xem;
    }

    public double getXmr() {
        return xmr;
    }

    public void setXmr(double xmr) {
        this.xmr = xmr;
    }

    public double getZec() {
        return zec;
    }

    public void setZec(double zec) {
        this.zec = zec;
    }

    public static Balance balance(){
        if(balance == null) balance = new Balance();
        return balance;
    }
}
