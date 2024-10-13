package com.ben.benhigginsnpr.data.headline.data

data class NPRHeadlineItem(
    val attributes: Attributes,
    val errors: List<Any>,
    val href: String,
    val items: List<Item>,
    val links: LinksX,
    val version: String
)