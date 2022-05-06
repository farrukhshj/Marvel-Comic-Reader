package com.example.android.marvelcomicreader.model

import com.google.gson.annotations.SerializedName

data class Events (

	@SerializedName("available") val available : String,
	@SerializedName("returned") val returned : String,
	@SerializedName("collectionURI") val collectionURI : String,
	@SerializedName("items") val items : List<Items>
)