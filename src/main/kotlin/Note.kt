data class Note(
    var id: Int,
    val ownerId: Int,
    val text: String,
    val title: String,
    var deleted: Boolean = false
)
