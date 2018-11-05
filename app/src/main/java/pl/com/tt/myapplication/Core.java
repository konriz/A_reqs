package pl.com.tt.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import pl.com.tt.myapplication.model.RequestInfo;

public class Core {

    private static Core instance;
    private RequestQueue requestQueue;
    private static Context coreContext;

    private static List<RequestInfo> requestsList;

    private Core(Context context){
        coreContext = context;
        requestQueue = getRequestQueue();
        requestsList = new ArrayList<>();
    }

    public static synchronized Core getInstance(Context context){
        if(instance == null){
            instance = new Core(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(coreContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public static void addToRequestList(RequestInfo request){
        requestsList.add(request);
    }

    public static List<RequestInfo> getRequestsList(){
        return requestsList;
    }
}
