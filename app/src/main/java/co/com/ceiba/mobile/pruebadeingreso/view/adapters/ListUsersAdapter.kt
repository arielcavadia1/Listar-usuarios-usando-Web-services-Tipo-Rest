package co.com.ceiba.mobile.pruebadeingreso.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity
import kotlinx.android.synthetic.main.user_list_item.view.*

class ListUsersAdapter : RecyclerView.Adapter<ListUsersAdapter.MyviewHolder>(){

    var list = ArrayList<UserEntity>()
    var clickListener : OnClick?= null

    inner class MyviewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_list_item,
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.itemView.apply {
            list[position].apply {
                holder.itemView.txtName.text = this.username
                holder.itemView.txtPhone.text = this.phone
                holder.itemView.txtEmail.text = this.email
            }

            holder.itemView.btn_view_post.setOnClickListener {
                clickListener?.onClick(list[position])
            }
        }
    }

    fun update(data : ArrayList<UserEntity>){
        list = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size


    /**
     *  conecta los items del adapter con el mainactivity
     *  cuando se hace click en publicaciones
     */
    interface OnClick{
        fun onClick(user: UserEntity){

        }
    }
}