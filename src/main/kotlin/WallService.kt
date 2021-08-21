import attachments.*

class WallService {
    var posts = emptyArray<Post>()
    var attachments = emptyArray<Attachment>()
    private var id = 0

    fun add(post: Post): Post {
        id++
        post.id = id
        posts += post
        return posts.last()
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