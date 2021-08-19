import attachments.Photo
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

    @Test
    fun addAttachment() {
        val service = WallService()

        val photo = Photo(1, 1, 1)
        val photo2 = Photo(2,2,2)

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