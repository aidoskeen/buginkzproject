package com.daryn.buginkzproject.models

import com.google.gson.annotations.SerializedName

data class Datum (
  @SerializedName("news_id"    ) var newsId    : Int?    = null,
  @SerializedName("news_name"  ) var newsName  : String? = null,
  @SerializedName("news_desc"  ) var newsDesc  : String? = null,
  @SerializedName("news_date"  ) var newsDate  : String? = null,
  @SerializedName("news_image" ) var newsImage : String? = null,
  @SerializedName("view_count" ) var viewCount : String? = null,
  @SerializedName("bugin"      ) var bugin     : String? = null,
  @SerializedName("author"     ) var author    : Author? = Author(),
  @SerializedName("rubric"     ) var rubric    : Rubric? = Rubric()

)