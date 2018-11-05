package pl.com.tt.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.com.tt.myapplication.model.RequestInfo;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder> {

    private List<RequestInfo> requests;

    public static class RequestsViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public RequestsViewHolder(View requestsView){
            super(requestsView);
            textView = requestsView.findViewById(R.id.request_data);
        }
    }

    public RequestsAdapter(List<RequestInfo> requestsList){
        requests = requestsList;
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View requestsView = inflater.inflate(R.layout.requests_layout, parent, false);

        return new RequestsViewHolder(requestsView);

    }

    @Override
    public void onBindViewHolder(@NonNull RequestsViewHolder requestsViewHolder, int position) {

        RequestInfo requestInfo = requests.get(position);

        TextView textView = requestsViewHolder.textView;
        textView.setText(requestInfo.getInfo());

    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
