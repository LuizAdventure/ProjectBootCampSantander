package com.example.projectbootcampsantander.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectbootcampsantander.databinding.MatchItemBinding;
import com.example.projectbootcampsantander.domain.Match;
import com.example.projectbootcampsantander.ui.DetailActivity;

import java.util.List;


/**
 * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br">Criar listas dinâmicas com o RecyclerView</a>
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private final List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Context context = viewHolder.itemView.getContext();
        Match match = matches.get(position);

        Glide.with(context).load(match.getHomeTeam().getImage()).into(viewHolder.binding.ivHomeTeam);
        viewHolder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        if (match.getHomeTeam().getScore() != null) {
            viewHolder.binding.tvHomeTeamScore.setText(String.valueOf(match.getHomeTeam().getScore()));
        }

        Glide.with(context).load(match.getAwayTeam().getImage()).into(viewHolder.binding.ivAwayTeam);
        viewHolder.binding.tvAwayTeamName.setText(match.getAwayTeam().getName());
        if (match.getAwayTeam().getScore() != null) {
            viewHolder.binding.tvAwayTeamScore.setText(String.valueOf(match.getAwayTeam().getScore()));
        }

        viewHolder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.Extras.MATCH, match);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final MatchItemBinding binding;

        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
