package com.example.testjetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testjetpack.data.Student
import com.example.testjetpack.databinding.ActivityRecyclerViewBinding
import com.example.testjetpack.databinding.RecyclerItemBinding


class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityRecyclerViewBinding>(this,R.layout.activity_recycler_view)
        var list = ArrayList<Student>()
        var item1 = Student()
        item1.age = 20
        item1.name = "白敬亭"
        list.add(item1)
        var item2 = Student()
        item2.age = 21
        item2.name = "鬼鬼"
        list.add(item2)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MyAdapter(list)

    }
    inner class MyAdapter : RecyclerView.Adapter<ViewHolder> {
        var list:List<Student> = ArrayList()
        constructor(list:List<Student>){
            this.list = list
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item: Student = list[position]
            holder.bind(item)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         private var mBinding: RecyclerItemBinding? = null
        fun bind(item: Student) {
             mBinding?.item = item
        }

        init {
            mBinding = DataBindingUtil.bind(itemView)
        }
    }
}