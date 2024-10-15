package com.ben.benhigginsnpr.data.headline.classes

data class Links(
    val audio: List<Audio>,
    val image: List<Image>,
    val profile: List<Profile>,
    val program: List<Program>,
    val up: List<Up>,
    val web: List<Web>
)