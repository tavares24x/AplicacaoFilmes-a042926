package pt.ismai.aplicacaofilmes.data

import kotlinx.coroutines.flow.Flow

class FilmeRepository(private val filmeDao: FilmeDao) {
    
    val allFilmes: Flow<List<Filme>> = filmeDao.getAllFilmes()
    val filmesFavoritos: Flow<List<Filme>> = filmeDao.getFilmesFavoritos()
    val filmesVistos: Flow<List<Filme>> = filmeDao.getFilmesVistos()
    val filmesPorVer: Flow<List<Filme>> = filmeDao.getFilmesPorVer()
    
    suspend fun getFilmeById(id: String): Filme? {
        return filmeDao.getFilmeById(id)
    }
    
    suspend fun insertFilme(filme: Filme) {
        filmeDao.insertFilme(filme)
    }
    
    suspend fun insertFilmes(filmes: List<Filme>) {
        filmeDao.insertFilmes(filmes)
    }
    
    suspend fun updateFilme(filme: Filme) {
        filmeDao.updateFilme(filme)
    }
    
    suspend fun deleteFilme(filme: Filme) {
        filmeDao.deleteFilme(filme)
    }
    
    suspend fun deleteFilmeById(filmeId: String) {
        filmeDao.deleteFilmeById(filmeId)
    }
    
    suspend fun toggleFavorito(filmeId: String, isFavorito: Boolean) {
        filmeDao.updateFavorito(filmeId, isFavorito)
    }
    
    suspend fun toggleVisto(filmeId: String, isVisto: Boolean) {
        filmeDao.updateVisto(filmeId, isVisto)
    }
    
    suspend fun togglePorVer(filmeId: String, isPorVer: Boolean) {
        filmeDao.updatePorVer(filmeId, isPorVer)
    }
}
