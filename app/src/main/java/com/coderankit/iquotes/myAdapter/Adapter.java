package com.coderankit.iquotes.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.coderankit.iquotes.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final JSONObject data;


    public Adapter(Context context, JSONObject object){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = object;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, quote, author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.api_id);
            quote = itemView.findViewById(R.id.api_quote);
            author = itemView.findViewById(R.id.api_auther);

        }


    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        try {
            String id = data.getJSONArray("quotes").getJSONObject(position).getString("id");
            String quote = data.getJSONArray("quotes").getJSONObject(position).getString("quote");
            String author = data.getJSONArray("quotes").getJSONObject(position).getString("author");

            holder.id.setText(String.valueOf(id));
            holder.quote.setText(quote);
            holder.author.setText(author);
        }
        catch (JSONException e) {
            Toast.makeText(layoutInflater.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return data.getJSONArray("quotes").length();
        } catch (JSONException e) {
            Toast.makeText(layoutInflater.getContext(), "Something Went Wrong, while getting Length", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

}
