package services

import Comment
import Note
import exceptions.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NoteServiceTest {

    @Test
    fun add() {
        val service = NoteService()

        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        val expected = Note(id = 1001, ownerId = 1, text = "Тестовая заметка", title = "Заголовок")


        assertEquals(expected, testNote)
    }

    @Test(expected = NoteAlreadyDeletedException::class)
    fun createCommentShouldThrowNoteDeleted() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))

        service.delete(testNote.id)
        service.createComment(testNote, "Сообщение комментария", 0)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentShouldThrowNoteNotFound() {
        val service = NoteService()

        service.delete(2)
    }

    @Test
    fun createComment() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        assertTrue(service.createComment(testNote, "Сообщение", 0))
    }

    @Test(expected = NoteAlreadyDeletedException::class)
    fun deleteShouldThrowNoteDeleted() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))

        service.add(testNote)
        service.delete(testNote.id)
        service.delete(testNote.id)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteShouldThrowNoteNotFound() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))

        service.add(testNote)
        service.delete(2)
    }

    @Test
    fun delete() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.add(testNote)
        assertTrue(service.delete(testNote.id))
    }

    @Test(expected = CommentAlreadyDeletedException::class)
    fun deleteCommentShouldThrowCommentDeleted() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.add(testNote)
        service.createComment(testNote, "123", 0)
        service.deleteComment(1001)
        service.deleteComment(1001)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentShouldThrowCommentNotFound() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.add(testNote)
        service.createComment(testNote, "123", 0)
        service.deleteComment(2)
    }

    @Test
    fun deleteComment() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.add(testNote)
        service.createComment(testNote, "123", 0)
        assertTrue(service.deleteComment(1001))
    }

    @Test(expected = NoteAlreadyDeletedException::class)
    fun editShouldThrowNoteDeleted() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.delete(testNote.id)
        service.edit(testNote, "Новый текст", "Новый заголовок")
    }

    @Test(expected = NoteNotFoundException::class)
    fun editShouldThrowNoteNotFound() {
        val service = NoteService()
        val testNote = Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок")
        service.edit(testNote, "Новый текст", "Новый заголовок")
    }

    @Test
    fun edit() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))

        assertTrue(service.edit(testNote, "Новый текст", "Новый заголовок"))
    }

    @Test(expected = CommentAlreadyDeletedException::class)
    fun editCommentThrowCommentDeleted() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.createComment(testNote, "123", 0)
        service.deleteComment(1001)
        service.editComment(1001, 1, "Тест")
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrowCommentNotFound() {
        val service = NoteService()
        service.editComment(1, 1, "Тест")
    }

    @Test
    fun editComment() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.createComment(testNote, "123", 0)
        assertTrue(service.editComment(1001, 1, "Новый комментарий"))
    }

    @Test
    fun get() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        val testNote2 = service.add(Note(id = 2, ownerId = 2, text = "Тестовая заметка 2", title = "Заголовок 2"))
        val expected = mutableListOf<Note>()
        expected.add(testNote)
        expected.add(testNote2)
        val result = service.get()

        assertEquals(expected, result)
    }

    @Test (expected = NoteAlreadyDeletedException::class)
    fun getByIdShouldThrowNoteDeleted() {
        val service = NoteService()
        val expected = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.delete(1001)
        service.getById(1001, 1, false)
    }

    @Test (expected = NoteNotFoundException::class)
    fun getByIdShouldThrowNoteNotFound() {
        val service = NoteService()
        service.getById(1001, 1, false)
    }

    @Test
    fun getById() {
        val service = NoteService()
        val expected = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        val result = service.getById(1001, 1, false)

        assertEquals(expected, result)
    }

    @Test
    fun getComments() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        val expected = mutableListOf<Comment>()
        val comment = Comment(1001, 1001, 1, text = "Тестовый комментарий")
        expected.add(comment)
        service.createComment(testNote, "Тестовый комментарий", 0)
        val result = service.getComments()
        assertEquals(expected, result)
    }

    @Test (expected = CommentNotDeletedException::class)
    fun restoreCommentShouldThrowNotDeleted() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.createComment(testNote, "123", 0)
        service.restoreComment(1001)
    }

    @Test (expected = CommentNotFoundException::class)
    fun restoreCommentShouldThrowNotFound() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.createComment(testNote, "123", 0)
        service.restoreComment(1)
    }

    @Test
    fun restoreComment() {
        val service = NoteService()
        val testNote = service.add(Note(id = 1, ownerId = 1, text = "Тестовая заметка", title = "Заголовок"))
        service.createComment(testNote, "123", 0)
        service.deleteComment(1001)
        service.restoreComment(1001)
    }
}