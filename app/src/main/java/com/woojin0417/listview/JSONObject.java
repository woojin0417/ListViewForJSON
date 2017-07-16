package com.woojin0417.listview;


public class JSONObject {
    private String url;
    private String rank;
    private String Nm;

    public JSONObject(String url, String rank, String Nm) {
        this.url = url;
        this.rank = rank;
        this.Nm = Nm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNm() {return Nm;}

    public void setNm(String Nm) {
        this.Nm = Nm;
    }


}
