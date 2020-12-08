package com.example.cyvid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyvid.model.Node;

import java.util.List;

public class NodesAdapter extends RecyclerView.Adapter<NodesAdapter.ViewHolder>{

    Context context;
    List<Node> nodeList;

    public NodesAdapter(Context c, List<Node> nodeList) {
        this.context = c;
        this.nodeList = nodeList;
    }

    @NonNull
    @Override
    public NodesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_node, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Node node = nodeList.get(position);

        holder.tvHostName.setText(nodeList.get(position).getHostName());
        holder.tvHostIP.setText(nodeList.get(position).getHostIP());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

                String hostName = nodeList.get(position).getHostName();
                String hostIP = nodeList.get(position).getHostIP();
                String hostGateway = nodeList.get(position).getHostGateway();
                String hostOS = nodeList.get(position).getHostOS();

                String id = nodeList.get(position).getId();
                String rev = nodeList.get(position).getRev();

                Intent intent = new Intent(context, NodeDetail.class);
                intent.putExtra("hostName", hostName);
                intent.putExtra("hostIP", hostIP);
                intent.putExtra("hostGateway", hostGateway);
                intent.putExtra("hostOS", hostOS);

                intent.putExtra("iID", id);
                intent.putExtra("iRev", rev);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nodeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvHostName, tvHostIP, tvHostGateway, tvNodeOS;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvHostName = itemView.findViewById(R.id.tvNodeName);
            this.tvHostIP = itemView.findViewById(R.id.tvDescription);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
}
