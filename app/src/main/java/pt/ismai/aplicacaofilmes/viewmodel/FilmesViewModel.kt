package pt.ismai.aplicacaofilmes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pt.ismai.aplicacaofilmes.data.Filme
import pt.ismai.aplicacaofilmes.data.FilmeDatabase
import pt.ismai.aplicacaofilmes.data.FilmeRepository
import pt.ismai.aplicacaofilmes.network.RetrofitInstance

class FilmesViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: FilmeRepository
    private val API_KEY = "df3b41b214msh33df031633dfe8ap1ad9d2jsn450c7cbeb7b5"
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _recomendacoes = MutableStateFlow<List<Filme>>(emptyList())
    val recomendacoes: StateFlow<List<Filme>> = _recomendacoes.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<Filme>>(emptyList())
    val searchResults: StateFlow<List<Filme>> = _searchResults.asStateFlow()
    
    init {
        val filmeDao = FilmeDatabase.getDatabase(application).filmeDao()
        repository = FilmeRepository(filmeDao)
        loadRecomendacoes()
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _searchResults.value = emptyList()
        }
    }
    
    fun searchFilmes() {
        if (_searchQuery.value.isBlank()) return
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Simular pesquisa de filmes
                val query = _searchQuery.value.lowercase()
                val todosFilmes = listOf(
                    Filme(
                        id = "tt0111161",
                        title = "The Shawshank Redemption",
                        year = 1994,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg",
                        rating = 9.3f
                    ),
                    Filme(
                        id = "tt0068646",
                        title = "The Godfather",
                        year = 1972,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                        rating = 9.2f
                    ),
                    Filme(
                        id = "tt0468569",
                        title = "The Dark Knight",
                        year = 2008,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg",
                        rating = 9.0f
                    ),
                    Filme(
                        id = "tt0109830",
                        title = "Forrest Gump",
                        year = 1994,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg",
                        rating = 8.8f
                    ),
                    Filme(
                        id = "tt0137523",
                        title = "Fight Club",
                        year = 1999,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_.jpg",
                        rating = 8.8f
                    ),
                    Filme(
                        id = "tt0110912",
                        title = "Pulp Fiction",
                        year = 1994,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                        rating = 8.9f
                    ),
                    Filme(
                        id = "tt0120737",
                        title = "The Lord of the Rings: The Fellowship of the Ring",
                        year = 2001,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_.jpg",
                        rating = 8.8f
                    ),
                    Filme(
                        id = "tt0167260",
                        title = "The Lord of the Rings: The Return of the King",
                        year = 2003,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                        rating = 9.0f
                    ),
                    Filme(
                        id = "tt0816692",
                        title = "Interstellar",
                        year = 2014,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg",
                        rating = 8.7f
                    ),
                    Filme(
                        id = "tt0133093",
                        title = "The Matrix",
                        year = 1999,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg",
                        rating = 8.7f
                    )
                )
                
                // Filtrar filmes que correspondem à pesquisa
                val filmes = todosFilmes.filter { 
                    it.title.lowercase().contains(query)
                }
                
                _searchResults.value = filmes
                
                if (filmes.isEmpty()) {
                    _errorMessage.value = "Nenhum filme encontrado para '${_searchQuery.value}'"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao pesquisar filmes: ${e.message}"
                android.util.Log.e("FilmesViewModel", "Erro na pesquisa: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun loadRecomendacoes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Criar lista de filmes de exemplo em vez de chamar a API
                val filmes = listOf(
                    Filme(
                        id = "tt0111161",
                        title = "The Shawshank Redemption",
                        year = 1994,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg",
                        rating = 9.3f
                    ),
                    Filme(
                        id = "tt0068646",
                        title = "The Godfather",
                        year = 1972,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                        rating = 9.2f
                    ),
                    Filme(
                        id = "tt0468569",
                        title = "The Dark Knight",
                        year = 2008,
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg",
                        rating = 9.0f
                    )
                )
                
                _recomendacoes.value = filmes
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao carregar recomendações: ${e.message}"
                android.util.Log.e("FilmesViewModel", "Erro: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun adicionarFavorito(filme: Filme) {
        viewModelScope.launch {
            val filmeExistente = repository.getFilmeById(filme.id)
            if (filmeExistente != null) {
                repository.toggleFavorito(filme.id, !filmeExistente.isFavorito)
            } else {
                repository.insertFilme(filme.copy(isFavorito = true))
            }
        }
    }
    
    fun adicionarVisto(filme: Filme) {
        viewModelScope.launch {
            val filmeExistente = repository.getFilmeById(filme.id)
            if (filmeExistente != null) {
                repository.toggleVisto(filme.id, !filmeExistente.isVisto)
            } else {
                repository.insertFilme(filme.copy(isVisto = true))
            }
        }
    }
    
    fun adicionarPorVer(filme: Filme) {
        viewModelScope.launch {
            val filmeExistente = repository.getFilmeById(filme.id)
            if (filmeExistente != null) {
                repository.togglePorVer(filme.id, !filmeExistente.isPorVer)
            } else {
                repository.insertFilme(filme.copy(isPorVer = true))
            }
        }
    }
}
