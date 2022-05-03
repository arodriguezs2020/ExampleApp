package com.example.exampleapp.model

data class Cards (

	val name : String,
	val manaCost : String,
	val cmc : Double,
	val colors : List<String>,
	val colorIdentity : List<String>,
	val type : String,
	val types : List<String>,
	val subtypes : List<String>,
	val rarity : String,
	val set : String,
	val setName : String,
	val text : String,
	val artist : String,
	val number : String,
	val power : String,
	val toughness : String,
	val layout : String,
	val multiverseid : Int,
	val imageUrl : String,
	val variations : List<String>,
	val foreignNames : List<ForeignNames>,
	val printings : List<String>,
	val originalText : String,
	val originalType : String,
	val legalities : List<Legalities>,
	val id : String
)