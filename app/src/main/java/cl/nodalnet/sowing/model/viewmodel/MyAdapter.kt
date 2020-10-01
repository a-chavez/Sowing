package cl.nodalnet.sowing.model.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.nodalnet.sowing.R
import cl.nodalnet.sowing.model.retrofit.SowingItem
import cl.nodalnet.sowing.model.retrofit.SowingList
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_seed.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.mViewHolder>() {

    private var dataList = emptyList<SowingItem>()

    fun updateListSeed(mDataList: List<SowingItem>){

        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class mViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val mSeedName = itemView.tvSeedNameItem
        val mSeedUrli = itemView.imageSeedHome
        val itemView = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            // click XD
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_seed,parent,false)
        return mViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val mSowingItem: SowingItem = dataList[position]

        holder.mSeedName.text = mSowingItem.name.capitalize()
        Glide.with(holder.itemView.context).load(mSowingItem.urlImage).into(holder.mSeedUrli)
    }

    override fun getItemCount() = dataList.size



}