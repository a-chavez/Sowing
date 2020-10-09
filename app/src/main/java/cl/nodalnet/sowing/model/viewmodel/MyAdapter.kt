package cl.nodalnet.sowing.model.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.nodalnet.sowing.R
import cl.nodalnet.sowing.model.room.SowingItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_seed.view.*

class MyAdapter (var mSeedNameTxt: SeedNameTxt): RecyclerView.Adapter<MyAdapter.mViewHolder>() {

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
            mSeedNameTxt.passData(dataList[adapterPosition].name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_seed,parent,false)
        return mViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val mSowingItem: SowingItem = dataList[position]

        holder.mSeedName.text = mSowingItem.name.capitalize()
        Glide.with(holder.itemView.context)
            .load(mSowingItem.urlImage)
            .transform(CenterCrop(), RoundedCorners(40))
            .into(holder.mSeedUrli)
    }

    override fun getItemCount() = dataList.size

    interface SeedNameTxt{
        fun passData(mSeedNameTxt: String )
    }

}