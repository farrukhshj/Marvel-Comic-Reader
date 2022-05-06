package com.example.android.marvelcomicreader.model

import com.google.gson.annotations.SerializedName

data class Results (

	@SerializedName("id") val id : String,
	@SerializedName("digitalId") val digitalId : String,
	@SerializedName("title") val title : String,
	@SerializedName("issueNumber") val issueNumber : String,
	@SerializedName("variantDescription") val variantDescription : String,
	@SerializedName("description") val description : String,
	@SerializedName("modified") val modified : String,
	@SerializedName("isbn") val isbn : String,
	@SerializedName("upc") val upc : String,
	@SerializedName("diamondCode") val diamondCode : String,
	@SerializedName("ean") val ean : String,
	@SerializedName("issn") val issn : String,
	@SerializedName("format") val format : String,
	@SerializedName("pageCount") val pageCount : String,
	@SerializedName("textObjects") val textObjects : List<TextObjects>,
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("urls") val urls : List<Urls>,
	@SerializedName("series") val series : Series,
	@SerializedName("variants") val variants : List<Variants>,
	@SerializedName("collections") val collections : List<Collections>,
	@SerializedName("collectedIssues") val collectedIssues : List<CollectedIssues>,
	@SerializedName("dates") val dates : List<Dates>,
	@SerializedName("prices") val prices : List<Prices>,
	@SerializedName("thumbnail") val thumbnail : Thumbnail,
	@SerializedName("images") val images : List<Images>,
	@SerializedName("creators") val creators : Creators,
	@SerializedName("characters") val characters : Characters,
	@SerializedName("stories") val stories : Stories,
	@SerializedName("events") val events : Events
)