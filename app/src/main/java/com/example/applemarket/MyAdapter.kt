package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemRecyclerviewBinding
import java.text.DecimalFormat


// data 클래스
class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }

        val dec = DecimalFormat("#,### 원")

        holder.iconImageView.setImageResource(mItems[position].aIcon)
        holder.name.text = mItems[position].aName
        holder.price.text = dec.format(mItems[position].aPrice.toInt())
        holder.adress.text = mItems[position].aAdress
        holder.number.text = mItems[position].aGood.toString()
        // 좋아요 수 증가
        holder.good.setOnClickListener {
            when(mItems[position].aTrue) {
                false -> {
                    mItems[position].aGood += 1
                    mItems[position].aTrue = true
                    holder.number.text = mItems[position].aGood.toString()
                    holder.good.setImageResource(R.drawable.favorite_red)
                }
                true -> {
                    mItems[position].aGood -= 1
                    mItems[position].aTrue = false
                    holder.number.text = mItems[position].aGood.toString()
                    holder.good.setImageResource(R.drawable.favorite_24px)
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textName
        val price = binding.textPrice
        val adress = binding.textAdress
        val good = binding.heartImageView2
        val number = binding.numberTextView
    }
}