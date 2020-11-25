package co.com.ceiba.mobile.pruebadeingreso.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.model.PostEntity
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostUserAdapter  : RecyclerView.Adapter<PostUserAdapter.MyviewHolder>() {

    var list = ArrayList<PostEntity>()

    inner class MyviewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_list_item,
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.itemView.apply {
            list[position].apply {
                holder.itemView.title.text = this.title
                holder.itemView.body.text = this.body
            }
        }
    }

    /**
     * Actualiza el adapter con una lista nueva de elementos
     * @param data :  nueva lista de elementos Post
     */
    fun update(data : ArrayList<PostEntity>){
        list = data
        notifyDataSetChanged()
    }


    override fun getItemCount() = list.size


}

