import attachments.Photo
import exceptions.CommentNotFoundException
import exceptions.NoSuchReasonException
import exceptions.PostNotFoundException
import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService()

        val testPost = service.add(Post(id = 10, text = "Тестовый пост", original = null))
        val expected = Post(id = 1, text = "Тестовый пост", original = null)


        assertEquals(expected, testPost)
    }

    @Test(expected = PostNotFoundException::class)
    fun addCommentShouldThrow() {
        val service = WallService()
        val post = Post(id = 1, text = "Тестовый пост", original = null)
        val comment = Comment(1, 30, 1, text = "Тестовый коммент")

        service.add(post)
        service.createComment(comment)
    }

    @Test(expected = Test.None::class /* no exception expected */)
    fun addCommentShouldNotThrow() {
        val service = WallService()
        val post = Post(id = 1, text = "Тестовый пост", original = null)
        val comment = Comment(1, 1, 1, text = "Тестовый коммент")

        service.add(post)
        service.createComment(comment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportCommentThrowCommentNotFound() {
        val service = WallService()

        val post = Post(text = "Тестовый пост", original = null)
        val comment = Comment(1, 1, 1, text = "Тест1")
        val comment2 = Comment(2, 1, 2, text = "Тест1")

        service.add(post)
        service.createComment(comment)
        service.reportComment(comment2,0)
    }

    @Test(expected = Test.None::class /* no exception expected */)
    fun reportCommentShouldNotThrow() {
        val service = WallService()

        val post = Post(text = "Тестовый пост", original = null)
        val comment = Comment(1, 1, 1, text = "Тест1")

        service.add(post)
        service.createComment(comment)
        service.reportComment(comment,5)
    }

    @Test(expected = NoSuchReasonException::class)
    fun reportCommentThrowNoSuchReason() {
        val service = WallService()

        val post = Post(text = "Тестовый пост", original = null)
        val comment = Comment(1, 1, 1, text = "Тест1")

        service.add(post)
        service.createComment(comment)
        service.reportComment(comment,10)
    }

    @Test
    fun addAttachment() {
        val service = WallService()

        val photo = Photo(1, 1, 1)
        val photo2 = Photo(2, 2, 2)

        val testAttachment = service.addAttachment(photo)
        val testAttachment2 = service.addAttachment(photo2)

        assertNotEquals(testAttachment, testAttachment2)

    }

    @Test
    fun updateExisting() {
        val service = WallService()

        service.add(Post(text = "Тестовый пост 1", original = null))
        service.add(Post(text = "Тестовый пост 2", original = null))
        service.add(Post(text = "Тестовый пост 3", original = null))

        val update = service.add(Post(text = "Тестовый пост 4", original = null))

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNonExisting() {
        val service = WallService()

        val test = Post(id = 10, text = "Тестовый пост, без воллсервиса", original = null)

        val result = service.update(test)

        assertFalse(result)
    }
}