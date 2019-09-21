package sunhacks.demo.restapi.posts.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import sunhacks.demo.restapi.posts.models.Post

interface PostsRepository {
    fun createPost(post: Post): Long
}

@Repository
class PostsJdbcRepository : PostsRepository {

    @Autowired
    lateinit var parameterizedJdbcTemplate: NamedParameterJdbcTemplate

    override fun createPost(post: Post): Long {
        val parameters = mapOf("title" to post.title,
                "content" to post.content,
                "author" to post.author)

        return SimpleJdbcInsert(parameterizedJdbcTemplate.jdbcTemplate)
                .withTableName("post")
                .usingColumns("title", "content", "author")
                .apply { setGeneratedKeyName("id") }
                .executeAndReturnKey(parameters)
                .toLong()
    }

}