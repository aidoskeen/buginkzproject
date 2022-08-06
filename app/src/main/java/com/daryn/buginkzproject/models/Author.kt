package com.daryn.buginkzproject.models

import com.google.gson.annotations.SerializedName

data class Author (
    @SerializedName("user_id"   ) var userId   : String? = null,
    @SerializedName("user_name" ) var userName : String? = null,
    @SerializedName("bugin"     ) var bugin    : String? = null,
    @SerializedName("avatar"    ) var avatar   : String? = null
        )
