package com.kalashnyk.denys.defaultproject.presentation.adapter.paginglist

import android.support.v7.util.DiffUtil

class DiffCallbackBaseCardModel : DiffUtil.ItemCallback<BaseCardModel>() {

    companion object {
        val CONTENT = Any()
        val REACTION = Any()
    }

    override fun areItemsTheSame(oldCard: BaseCardModel, newCard: BaseCardModel): Boolean {
        return oldCard.getCardId() == newCard.getCardId()
    }

    override fun areContentsTheSame(oldCard: BaseCardModel, newCard: BaseCardModel): Boolean {
        return oldCard.getCardId() == newCard.getCardId()
    }
}