package service

import org.jetbrains.annotations.Nullable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.data.*
import ru.netology.exception.PostNotFoundException
import ru.netology.service.WallService
import java.util.Objects

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addTest() {
        val post = Post(text = "Test Post")
        val expected = 1
        val actual = WallService.add(post).id
        assertEquals(expected, actual)
    }


    @Test
    fun updateTestFall() {
        val newPost = Post(text = "New post")
        val postUpdated = Post(id = 3, text = "Post with updated text")
        WallService.add(newPost)
        assertFalse(WallService.update(postUpdated))
    }

    @Test
    fun updateTestRight() {
        val newPost = Post(text = "New post")
        val postUpdated = Post(id = 1, text = "Post with updated text")
        WallService.add(newPost)
        assertTrue(WallService.update(postUpdated))
    }

    @Test(expected = PostNotFoundException::class)
    fun addCommentTestException() {
        val comment = Comment(text = "New Comment")
        WallService.addComment(3, comment)
    }

    @Test
    fun addCommentTestRight() {
        val newPost = Post(text = "New post")
        WallService.add(newPost)
        val comment = Comment(text = "New Comment")
        WallService.addComment(1, comment)
        assertEquals(comment, comment)
    }
}