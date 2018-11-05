package pl.com.tt.myapplication.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestInfo {

    private Date date;
    private String url;
    private int response;

    public RequestInfo(String url, int response){
        this.date = new Date();
        this.url = url;
        this.response = response;
    }

    public String getInfo(){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder info = new StringBuilder();
        info.append(dateFormat.format(date));
        info.append(" : ");
        info.append(url);
        return info.toString();
    }
}
