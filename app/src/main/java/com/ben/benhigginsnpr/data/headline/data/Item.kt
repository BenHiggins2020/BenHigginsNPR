package com.ben.benhigginsnpr.data.headline.data

data class Item(
    val attributes: AttributesX,
    val errors: List<Any>,
    val href: String,
    val items: List<Any>,
    val links: Links,
    val version: String
)