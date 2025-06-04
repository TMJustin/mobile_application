package com.zybooks.capstone_application.UI;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.capstone_application.R;
import com.zybooks.capstone_application.database.Repository;
import com.zybooks.capstone_application.entities.Vacation;

import org.w3c.dom.Text;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Vacation> mVacations;
    private final Context context;
    private final LayoutInflater mInflator;
    private Repository repository;


    public SearchAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private final TextView searchItemView;
        private final TextView searchItemView2;
        private final TextView searchItemView3;
        private final TextView searchItemView4;
        private final TextView searchItemView5;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchItemView = itemView.findViewById(R.id.searchreport);
            searchItemView2 = itemView.findViewById(R.id.searchreport2);
            searchItemView3 = itemView.findViewById(R.id.searchreport3);
            searchItemView4 = itemView.findViewById(R.id.searchreport4);
            searchItemView5 = itemView.findViewById(R.id.searchreport5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Vacation current = mVacations.get(position);
                    Intent intent = new Intent(context, VacationDetails.class);
                    intent.putExtra("id", current.getVacationID());
                    intent.putExtra("title", current.getVacationTitle());
                    intent.putExtra("accommodation", current.getAccommodation());
                    intent.putExtra("startdate", current.getStartDate());
                    intent.putExtra("enddate", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.vacation_search_report, parent, false);
        return new SearchAdapter.SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        if (mVacations != null) {
            Vacation current = mVacations.get(position);
            String title = current.getVacationTitle();
            String accommodation = current.getAccommodation();
            String startdate = current.getStartDate();
            String enddate = current.getEndDate();
            int id = current.getVacationID();

            holder.searchItemView.setText(title);
            holder.searchItemView2.setText(accommodation);
            holder.searchItemView3.setText(startdate);
            holder.searchItemView4.setText(enddate);
            holder.searchItemView5.setText(id);
        } else {
            holder.searchItemView.setText("No destination title");
            holder.searchItemView2.setText("No accommodation selected");
            holder.searchItemView3.setText("No start date selected");
            holder.searchItemView4.setText("No end date selected");
            holder.searchItemView5.setText("");
        }

    }

    @Override
    public int getItemCount() {
        if (mVacations != null) {
            return mVacations.size();
        } else return 0;
    }

    public void setVacations(List<Vacation> vacations) {
        mVacations = vacations;
        notifyDataSetChanged();
    }
}
