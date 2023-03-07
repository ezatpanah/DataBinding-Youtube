package com.ezatpanah.databinding_youtube.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ezatpanah.databinding_youtube.response.ResponseTopHeadline

class NewsDiffUtil(
    private val oldList: List<ResponseTopHeadline.Article>,
    private val newList: List<ResponseTopHeadline.Article>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}