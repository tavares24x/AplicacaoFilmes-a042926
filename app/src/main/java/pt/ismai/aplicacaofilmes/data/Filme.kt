package pt.ismai.aplicacaofilmes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class Filme(
    @PrimaryKey val id: String,
    val title: String,
    val year: Int?,
    val imageUrl: String?,
    val category: String? = null,
    val rating: Float? = null,
    val isFavorito: Boolean = false,
    val isVisto: Boolean = false,
    val isPorVer: Boolean = false
)
