import java.time.Instant
import java.util.*

data class Post(
    var id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Any = 0,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: Any? = 0,
    val copyright: Any? = 0,
    val likes: Any = 0,
    val reposts: Any? = 0,
    val views: Any = 0,
    val postType: String = "post",
    val postSource: Any? = 0,
    val geo: Any? = 0,
    val signerId: Int = 0,
    val copyHistory: Any? = 0,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Any = false,
    val postponedId: Int = 0,
    val original: Post?
)