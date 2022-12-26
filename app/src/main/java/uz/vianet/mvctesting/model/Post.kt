package uz.vianet.mvctesting.model

 class Post {
    var id: Int = 0
    var userId: Int = 0
    var title: String
    var body: String

    constructor(userId: Int, title: String, body: String) {
        this.userId = userId
        this.title = title
        this.body = body
    }
     constructor(title: String, body: String,userId: Int) {
         this.title = title
         this.body = body
         this.userId = userId
     }
    constructor(id: Int, userId: Int, title: String, body: String) {
        this.id = id
        this.userId = userId
        this.title = title
        this.body = body
    }
}