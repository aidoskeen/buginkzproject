package com.daryn.buginkzproject.models

import com.google.gson.annotations.SerializedName

data class Rubric(
    @SerializedName("rubric_id"   ) var rubricId   : String? = null,
    @SerializedName("rubric_name" ) var rubricName : String? = null,
    @SerializedName("rubric_color") var rubricColor  : String? = null,
    @SerializedName("rubric_color_second") var rubricColorSecond : String? = null

)
