package fr.polyflix.search.infrastructure.repository.elasticsearch.document

import fr.polyflix.search.domain.entity.User
import fr.polyflix.search.domain.mapper.DomainMapper
import fr.polyflix.search.infrastructure.repository.elasticsearch.mapper.DocumentMapper
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

data class NestedRole(
    val role: String
)

@Document(indexName = Indices.USER)
data class UserDocument (
    @Id val id: String,
    @Field(type= FieldType.Text) val firstName: String,
    @Field(type= FieldType.Text) val lastName: String,
    @Field(type= FieldType.Text) val username: String,
    @Field(type= FieldType.Text) val avatar: String,
    @Field(type= FieldType.Text) val email: String,
    @Field(type= FieldType.Nested) val roles: List<NestedRole>
): DomainMapper<User> {
    /**
     * Map the UserDocument to the User domain entity
     */
    override fun toDomain(): User {
        return User(id, email, username, firstName, lastName, avatar, roles.map { it.role })
    }

    companion object: DocumentMapper<User, UserDocument> {
        /**
         * Create a UserDocument from a User domain entity
         */
        override fun fromDomain(domain: User): UserDocument {
            return UserDocument(
                domain.id!!,
                domain.firstName,
                domain.lastName,
                domain.username,
                domain.avatar,
                domain.email,
                domain.roles.map { NestedRole(it) }
            )
        }
    }
}