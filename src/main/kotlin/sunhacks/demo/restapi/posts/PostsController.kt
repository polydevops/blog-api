package sunhacks.demo.restapi.posts

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sunhacks.demo.restapi.posts.data.PostsRepository
import sunhacks.demo.restapi.posts.models.Post

@RestController
@RequestMapping("/posts")
class PostsController {

    @Autowired
    lateinit var postsRepository: PostsRepository

    @PostMapping
    fun createPosts(post: Post): ResponseEntity<Long> {
        val id = postsRepository.createPost(post)
        return ResponseEntity.status(201).body(id)
    }
}