package com.example.android.marvelcomicreader.model

import com.google.gson.annotations.SerializedName

data class Images (

	@SerializedName("path") val path : String,
	@SerializedName("extension") val extension : String
)