package fr.polyflix.search.domain.entity

class User(
    id: String,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val roles: List<String>
): Searchable(id, User::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "User { id = $id, email = $email, username = $username, firstName = $firstName, lastName = $lastName, avatar = $avatar,  roles = $roles }"
    }
}