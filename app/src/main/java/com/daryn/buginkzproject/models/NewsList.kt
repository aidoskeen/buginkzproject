package com.daryn.buginkzproject.models

import com.google.gson.annotations.SerializedName


data class NewsList(
    @SerializedName("data"       ) var data      : ArrayList<Datum> = arrayListOf() ,
    @SerializedName("total_item" ) var totalItem : Int?            = null,
    @SerializedName("total_page" ) var totalPage : Int?            = null,
    @SerializedName("status"     ) var status    : Boolean?        = null

)