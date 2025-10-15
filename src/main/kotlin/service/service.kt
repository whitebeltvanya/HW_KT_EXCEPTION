package ru.netology.service

import ru.netology.data.Attachment
import ru.netology.data.Comment
import ru.netology.data.Post
import ru.netology.exception.PostNotFoundException

object WallService {

    private var newId = 0
    private var posts = emptyArray<Post>()
    private var commentId = 0
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        val newPost = post.copy(id = getNewId())
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, postChecked) in posts.withIndex()) {
            if (postChecked.id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun findById(postId: Int): Post? {
        for (post in posts) {
            if (post.id == postId) {
                return post
            }
        }
        return null
    }

    fun addAttachment(post: Post, attachment: Attachment): Boolean {
        for (postChecked in posts) {
            if (postChecked.id == post.id) {
                post.attachments += attachment
                return true
            }
        }
        return false
    }

    fun addComment(postId: Int, comment: Comment): Comment {
        val post = findById(postId) ?: throw PostNotFoundException("Пост $postId не найден")
        val newComment = comment.copy(id = getNewCommentId(), replyToPost = post.id)
        comments += newComment
        return comment
    }

    fun getNewId(): Int = ++newId //thanks to jomarzka
    fun getNewCommentId(): Int = ++commentId
    fun getPostsCounts() = posts.size
    fun clear() {
        posts = emptyArray<Post>()
        newId = 0
        commentId = 0
    }
}