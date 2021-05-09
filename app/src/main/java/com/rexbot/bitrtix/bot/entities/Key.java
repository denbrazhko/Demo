package com.rexbot.bitrtix.bot.entities;

public class Key {

    private static Key key;

    private String publicKey;
    private String privateKey;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public static Key key(){
        if(key == null) key = new Key();
        return key;
    }
}
