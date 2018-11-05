package pl.com.tt.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.MalformedURLException;
import java.net.URL;

import pl.com.tt.myapplication.model.APIConnector;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;

    private Spinner protocolSpinner;
    private TextView inputUrl;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        protocolSpinner = findViewById(R.id.protocol_spinner);
        inputUrl = findViewById(R.id.url_input);
        button = findViewById(R.id.requests_button);

        configureSpinner();
        configureButton();
        configureRecycler();

    }

    private void configureSpinner(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.protocols, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        protocolSpinner.setAdapter(adapter);

    }

    private void configureButton(){
        final RequestQueue queue =  Core.getInstance(this).getRequestQueue();

        button.setOnClickListener(onClick ->
                {
                    String url = buildURL();
                    if(isProperUrl(url)){
                        queue.add(APIConnector.getRequest(url, adapter));
                    } else {
                        Toast.makeText(this, getText(R.string.not_proper_url), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void configureRecycler(){
        recycler = findViewById(R.id.requests_recycler);
        recycler.setHasFixedSize(true);

        adapter = new RequestsAdapter(Core.getRequestsList());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean isProperUrl(String inputUrl){
        try{
            URL url = new URL(inputUrl);
            return true;
        } catch (MalformedURLException e)
        {
            return false;
        }
    }

    private String buildURL(){
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(protocolSpinner.getSelectedItem().toString());
        urlBuilder.append(inputUrl.getText());
        return urlBuilder.toString();
    }

}
