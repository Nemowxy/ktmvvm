package com.nemo.ktmvvm.net.entity

data class BannerEntity(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String


) {
    override fun toString(): String {
        return "BannerEntity(desc='$desc', id=$id, imagePath='$imagePath', isVisible=$isVisible, order=$order, title='$title', type=$type, url='$url')"
    }
}