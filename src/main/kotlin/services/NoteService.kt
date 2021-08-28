package services

import Comment
import Note
import exceptions.*

class NoteService {
    var notes = mutableListOf<Note>()
    var notesComments = mutableListOf<Comment>()
    private var noteId: Int = 1000
    private var guid: Int = 1000

    fun add(note: Note): Note {
        noteId++
        note.id = noteId
        notes.add(note)
        return notes.last()
    }

    fun createComment(note: Note, message: String, replyTo: Int): Boolean {
        for (noteInList in notes) {
            if (note.id == noteInList.id) {
                if (noteInList.deleted) {
                    throw NoteAlreadyDeletedException()
                }
                guid++
                val comment =
                    Comment(id = guid, fromId = 1, postId = noteInList.id, text = message, replyToComment = replyTo)
                notesComments.add(comment)
                return true
            }
        }
        throw NoteNotFoundException()
    }

    fun delete(noteId: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id) {
                if (note.deleted) {
                    throw NoteAlreadyDeletedException()
                }

                val deletedNote = note.copy(deleted = true)
                notes[index] = deletedNote
                return true
            }
        }
        throw NoteNotFoundException()
    }

    fun deleteComment(commentId: Int, ownerId: Int = 0): Boolean {
        for ((index, comment) in notesComments.withIndex()) {
            if (commentId == comment.id) {
                if (comment.deleted) {
                    throw CommentAlreadyDeletedException()
                }

                val deletedComment = comment.copy(deleted = true)
                notesComments[index] = deletedComment
                return true
            }
        }
        throw CommentNotFoundException()
    }

    fun edit(note: Note, editedText: String, editedTitle: String): Boolean {
        for ((index, noteInList) in notes.withIndex()) {
            if (noteInList.id == note.id) {
                if (noteInList.deleted) {
                    throw NoteAlreadyDeletedException()
                }
                val editedNote = noteInList.copy(text = editedText, title = editedTitle)
                notes[index] = editedNote
                return true
            }
        }
        throw NoteNotFoundException()
    }

    fun editComment(id: Int, ownerId: Int, message: String): Boolean {
        for ((index, commentInList) in notesComments.withIndex()) {
            if (commentInList.id == id && commentInList.fromId == ownerId) {
                if (commentInList.deleted) {
                    throw CommentAlreadyDeletedException()
                }
                val editedComment = commentInList.copy(text = message)
                notesComments[index] = editedComment
                return true
            }
        }
        throw CommentNotFoundException()
    }

    fun get(): List<Note> {
        val noteList = mutableListOf<Note>()
        for (note in notes) {
            if (!note.deleted) {
                noteList.add(note)
            }
        }
        return noteList
    }

    fun getById(id: Int, ownerId: Int, needWiki: Boolean = false): Note {
        for (noteInList in notes) {
            if (noteInList.id == id && noteInList.ownerId == ownerId) {
                if (noteInList.deleted) {
                    throw NoteAlreadyDeletedException()
                }
                return noteInList
            }
        }
        throw NoteNotFoundException()
    }

    fun getComments(): List<Comment> {
        val commentsList = mutableListOf<Comment>()
        for (comment in notesComments) {
            if (!comment.deleted) {
                commentsList.add(comment)
            }
        }
        return commentsList
    }

    fun restoreComment(id: Int): Boolean {
        for ((index, commentInList) in notesComments.withIndex()) {
            if (commentInList.id == id) {
                if (!commentInList.deleted) {
                    throw CommentNotDeletedException()
                }
                val restoredComment = commentInList.copy(deleted = false)
                notesComments[index] = restoredComment
                return true
            }
        }
        throw CommentNotFoundException()
    }

}