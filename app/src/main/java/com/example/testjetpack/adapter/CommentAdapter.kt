package com.example.testjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testjetpack.R
import com.example.testjetpack.databinding.CommentItemBinding
import com.example.testjetpack.model.Comment
import com.example.testjetpack.ui.CommentClickCallback

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.ComentViewHolder> {
    lateinit var mCommentList:List<Comment?>
    lateinit var mCommentClickListener: CommentClickCallback
    constructor(commentClickCallback: CommentClickCallback):super(){
        mCommentClickListener = commentClickCallback
    }

    fun setCommenttList(commentList: List<Comment?>){
         if (mCommentList == null) {
            mCommentList = commentList
            notifyItemRangeInserted(0, commentList.size)}
         else {
             val  result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                 override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                     return mCommentList[oldItemPosition]?.id ===
                             commentList[newItemPosition]!!.id
                 }

                 override fun getOldListSize(): Int {
                     return mCommentList.size
                 }

                 override fun getNewListSize(): Int {
                     return commentList.size
                 }

                 override fun areContentsTheSame(
                     oldItemPosition: Int,
                     newItemPosition: Int
                 ): Boolean {
                     val newProduct = commentList[newItemPosition]
                     val oldProduct = mCommentList[oldItemPosition]
                     return (newProduct!!.id === oldProduct?.id && newProduct!!.text == oldProduct.text
                             && newProduct.productId == oldProduct.productId
                             && newProduct.postedAt.time === oldProduct.postedAt.time)

                 }
             })
             mCommentList = commentList
             result.dispatchUpdatesTo(this)


         }
    }

    companion object internal
        class ComentViewHolder(binding: CommentItemBinding) :
            RecyclerView.ViewHolder(binding.getRoot()) {
            val binding: CommentItemBinding

            init {
                this.binding = binding
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentViewHolder {
        val binding: CommentItemBinding =DataBindingUtil.inflate<CommentItemBinding>(LayoutInflater.from(parent.context),R.layout.comment_item,parent,false);
        binding.setCallback(mCommentClickListener)
        return ComentViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if(mCommentList == null) 0 else mCommentList.size
    }

    override fun onBindViewHolder(holder: ComentViewHolder, position: Int) {
        holder.binding.comment = mCommentList[position]
        holder.binding.executePendingBindings()

    }

    fun setCommentList(comments: List<Comment?>) {
        if (mCommentList == null) {
            mCommentList = comments
            notifyItemRangeInserted(0, comments.size)
        } else {
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {


                override fun areItemsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val old = mCommentList[oldItemPosition]!!
                    val comment = comments[newItemPosition]
                    return old.id === comment!!.id
                }

                override fun getOldListSize(): Int {
                    return mCommentList.size                }

                override fun getNewListSize(): Int {
                    return comments.size
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val old = mCommentList[oldItemPosition]!!
                    val comment = comments[newItemPosition]
                    return old.id === comment!!.id && old.postedAt === comment!!.postedAt && old.productId === comment!!.productId && old.text == comment!!.text
                }
            })
            mCommentList = comments
            diffResult.dispatchUpdatesTo(this)
        }
    }


}