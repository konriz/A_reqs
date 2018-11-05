package pl.com.tt.myapplication.model;

import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;


import pl.com.tt.myapplication.Core;

public class APIConnector {

    public static StringRequest getRequest(CharSequence inputUrl, RecyclerView.Adapter adapter) {

        final String url = inputUrl.toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    Core.addToRequestList(new RequestInfo(url, 400));
                    adapter.notifyDataSetChanged();
                },
                error -> {
                if(error.networkResponse != null){
                    Core.addToRequestList(new RequestInfo(url, error.networkResponse.statusCode));
                    adapter.notifyDataSetChanged();
                }

        });

        return stringRequest;
    }

}
