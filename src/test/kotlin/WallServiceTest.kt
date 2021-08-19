import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService()

        val testPost = service.add(Post(text = "Тестовый пост", original = null))

        val result = service.posts[0]

        assertEquals(testPost,result)
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