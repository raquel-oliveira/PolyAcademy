package polytech.unice.fr.polynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import polytech.unice.fr.polynews.R;
import polytech.unice.fr.polynews.database.EventsDBHelper;
import polytech.unice.fr.polynews.model.Event;

/**
 * @version 03/04/16.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> implements View.OnClickListener{
    //define interface
    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, String data);
    }

    private List<Event> dataSet;
    private Context context;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView description;
        public TextView date_time;
        public TextView local;
        public TextView org;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title_event);
            this.description = (TextView) itemView.findViewById(R.id.description_event);
            this.date_time = (TextView) itemView.findViewById(R.id.date_time);
            this.local = (TextView) itemView.findViewById(R.id.local);
            this.org = (TextView) itemView.findViewById(R.id.org);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public EventsAdapter(Context context) {
        this.context = context;
        this.dataSet = setDataSet();
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(dataSet.get(position).getTitle());
        holder.description.setText(dataSet.get(position).getDescription());
        holder.date_time.setText(dataSet.get(position).getDateTime());
        holder.local.setText(dataSet.get(position).getLocal());
        holder.org.setText(dataSet.get(position).getOrganization());
    }

    @Override
    public void onClick(View v){
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private List<Event> setDataSet() {
        EventsDBHelper dbHelper = new EventsDBHelper(context);
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return dbHelper.selectRecords();
    }

    public List<Event> getDataSet(){
        return dataSet;
    }
}