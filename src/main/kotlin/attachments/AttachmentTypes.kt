package attachments

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
) : PhotoAttachment()

data class Video(
    val id: Int,
    val albumId: Int,
    val title: String
) : VideoAttachment()

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String
) : AudioAttachment()

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String
) : DocumentAttachment()

data class Link(
    val url: String,
    val title: String,
) : LinkAttachment()

open class PhotoAttachment(
    override val type: String = "photo"
) : Attachment

open class VideoAttachment(
    override val type: String = "video"
) : Attachment

open class AudioAttachment(
    override val type: String = "audio"
) : Attachment

open class DocumentAttachment(
    override val type: String = "doc"
) : Attachment

open class LinkAttachment(
    override val type: String = "link"
) : Attachment