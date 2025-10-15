package ru.netology

import ru.netology.data.Comment
import ru.netology.data.Post
import ru.netology.exception.PostNotFoundException
import ru.netology.service.WallService

fun main() {
    val post = Post(text = "New post text")
    val service = WallService

    println(service.add(post))
    println("All posts counts: ${service.getPostsCounts()}")

    val comment = Comment(text = "New Comment")
    try {
        service.addComment(3, comment)
    } catch (e: PostNotFoundException) {
        println(e.message + ": " + "Невозможно добавить комментарий \"${comment.text}\"")
    }

}