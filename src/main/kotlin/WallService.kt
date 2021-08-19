class WallService {
    var posts = emptyArray<Post>()
    private var id = 0

    fun add(post: Post): Post {
        id++
        post.id = id
        posts += post
        return posts.last()
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