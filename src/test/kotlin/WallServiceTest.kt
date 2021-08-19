import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService()

        val testPost = service.add(Post(text = "Тестовый пост"))

        val result = service.posts[0]

        assertEquals(testPost,result)
    }

    @Test
    fun updateExisting() {
        val service = WallService()

        service.add(Post(text = "Тестовый пост 1"))
        service.add(Post(text = "Тестовый пост 2"))
        service.add(Post(text = "Тестовый пост 3"))

        val update = service.add(Post(text = "Тестовый пост 4"))

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNonExisting() {
        val service = WallService()

        val test = Post(id = 10, text = "Тестовый пост, без воллсервиса")

        val result = service.update(test)

        assertFalse(result)
    }
}