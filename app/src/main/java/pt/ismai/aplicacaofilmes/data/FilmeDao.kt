package pt.ismai.aplicacaofilmes.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmeDao {
    
    @Query("SELECT * FROM filmes")
    fun getAllFilmes(): Flow<List<Filme>>
    
    @Query("SELECT * FROM filmes WHERE isFavorito = 1")
    fun getFilmesFavoritos(): Flow<List<Filme>>
    
    @Query("SELECT * FROM filmes WHERE isVisto = 1")
    fun getFilmesVistos(): Flow<List<Filme>>
    
    @Query("SELECT * FROM filmes WHERE isPorVer = 1")
    fun getFilmesPorVer(): Flow<List<Filme>>
    
    @Query("SELECT * FROM filmes WHERE id = :id")
    suspend fun getFilmeById(id: String): Filme?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilme(filme: Filme)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmes(filmes: List<Filme>)
    
    @Update
    suspend fun updateFilme(filme: Filme)
    
    @Delete
    suspend fun deleteFilme(filme: Filme)
    
    @Query("DELETE FROM filmes WHERE id = :filmeId")
    suspend fun deleteFilmeById(filmeId: String)
    
    @Query("UPDATE filmes SET isFavorito = :isFavorito WHERE id = :filmeId")
    suspend fun updateFavorito(filmeId: String, isFavorito: Boolean)
    
    @Query("UPDATE filmes SET isVisto = :isVisto WHERE id = :filmeId")
    suspend fun updateVisto(filmeId: String, isVisto: Boolean)
    
    @Query("UPDATE filmes SET isPorVer = :isPorVer WHERE id = :filmeId")
    suspend fun updatePorVer(filmeId: String, isPorVer: Boolean)
}
