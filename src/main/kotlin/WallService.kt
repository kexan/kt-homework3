class WallService {
    var posts = emptyArray<Post>()
    private var id: Int = 0

    fun add(post: Post): Post {
        id += 1
        post.id = id
        posts += post
        return posts.last()
    }

    fun update(id: Int): Boolean {
        for (post in posts) {
            if (post.id == id) {
                return true
            }
        }
        return false
    }
}