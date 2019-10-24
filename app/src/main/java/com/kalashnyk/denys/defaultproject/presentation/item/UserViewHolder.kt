package com.kalashnyk.denys.defaultproject.presentation.item

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.kalashnyk.denys.defaultproject.usecases.repository.database.entity.UserEntity
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var userEntity: UserEntity? = null
    private var listener: IUserItemClickListener<UserEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this.userEntity!!) }

    fun bind(user: UserEntity, listener: IUserItemClickListener<UserEntity>) {
        userEntity = user
        this.listener = listener
        setupItem()
    }

    private fun setupItem() {
//        view.txtRvId.text = userEntity?.id.toString()
//        view.txtName.text = userEntity?.name
//        view.txtSurname.text = userEntity?.surname
//        view.txtFathername.text = userEntity?.fathername
        view.setOnClickListener(itemDetail)
    }
}
