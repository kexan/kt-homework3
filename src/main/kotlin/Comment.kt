import attachments.Attachment

data class Comment(
    var id: Int,
    val postId: Int,
    val fromId: Int,
    val date: Int = 0,
    val text: String,
    val donut: Any = false,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Attachment? = null,
    val parentsStack: Any = 0,
    val thread: Any = 0

)