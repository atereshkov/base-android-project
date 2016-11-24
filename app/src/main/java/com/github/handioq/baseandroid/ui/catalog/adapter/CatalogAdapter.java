package com.github.handioq.baseandroid.ui.catalog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.handioq.baseandroid.R;
import com.github.handioq.baseandroid.model.dto.FaqItem;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FaqItem> data;

    public CatalogAdapter(List<FaqItem> data) {
        setData(data);
    }

    public void setData(List<FaqItem> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog_view, parent, false);
        return new FAQItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FaqItem faqItem = getItem(position);
        if (faqItem != null) {
            FAQItemHolder itemHolder = (FAQItemHolder) holder;
            itemHolder.bind(faqItem);
        }
    }

    private FaqItem getItem(int position) {
        if (data != null && data.size() > 0 && position < data.size()) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void update(List<FaqItem> data) {
        setData(data);
        notifyDataSetChanged();
    }

    private class FAQItemHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        TextView tvAnswer;

        public FAQItemHolder(View itemView) {
            super(itemView);
            tvQuestion = (TextView) itemView.findViewById(R.id.tv_question);
            tvAnswer = (TextView) itemView.findViewById(R.id.tv_answer);
        }

        public void bind(FaqItem item) {
            tvQuestion.setText(item.getQuestion());
            tvAnswer.setText(item.getAnswer());
        }
    }
}
