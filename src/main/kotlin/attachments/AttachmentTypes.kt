package attachments

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    override val type: String = "photo"
) : Attachment

data class Video(
    val id: Int,
    val albumId: Int,
    val title: String,
    override val type: String = "video"
) : Attachment

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    override val type: String = "audio"
) : Attachment

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    override val type: String = "doc"
) : Attachment

data class Link(
    val url: String,
    val title: String,
    override val type: String = "link"
) : Attachment

data class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo
) : Attachment

data class VideoAttachment(
    override val type: String = "video",
    val video: Video
) : Attachment

data class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio
) : Attachment

data class DocumentAttachment(
    override val type: String = "doc",
    val document: Document
) : Attachment

data class LinkAttachment(
    override val type: String = "link",
    val link: Link
) : Attachment