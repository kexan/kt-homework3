package services

import Comment
import CommentReport
import Post
import attachments.*
import exceptions.CommentNotFoundException
import exceptions.NoSuchReasonException
import exceptions.PostNotFoundException

class WallService {
    var posts = mutableListOf<Post>()
    var attachments = mutableListOf<Attachment>()
    var comments = mutableListOf<Comment>()
    var commentReports = mutableListOf<CommentReport>()
    private var postId = 0
    private var commentId = 0

    fun add(post: Post): Post {
        postId++
        post.id = postId
        posts += post
        return posts.last()
    }

    fun createComment(comment: Comment) {
        for (postInArray in posts) {
            if (comment.postId == postInArray.id) {
                commentId++
                comment.id = commentId
                comments += comment
                return
            }
        }
        throw PostNotFoundException()
    }

    fun reportComment(reportedComment: Comment, reason: Int) {

        if (reason < 0 || reason > 8) {
            throw NoSuchReasonException()
        }

        for (commentInArray in comments) {
            if (reportedComment.id == commentInArray.id) {
                val report = CommentReport(reportedComment.fromId, reportedComment.id, reason)
                commentReports += report
                return
            }
        }
        throw CommentNotFoundException()
    }

    fun addAttachment(attachment: Attachment): Attachment {

        val newAttachment: Attachment = when (attachment) {
            is Photo -> PhotoAttachment(attachment.type, attachment)
            is Video -> VideoAttachment(attachment.type, attachment)
            is Audio -> AudioAttachment(attachment.type, attachment)
            is Document -> DocumentAttachment(attachment.type, attachment)
            is Link -> LinkAttachment(attachment.type, attachment)
            else -> throw Exception()
        }

        attachments += newAttachment
        return attachments.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postInArray) in posts.withIndex()) {
            if (post.id == postInArray.id) {
                posts[index] = post.copy(ownerId = postInArray.ownerId, date = postInArray.date)
                return true
            }
        }
        return false
    }
}