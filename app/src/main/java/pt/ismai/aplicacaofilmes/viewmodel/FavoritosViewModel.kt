package pt.ismai.aplicacaofilmes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.ismai.aplicacaofilmes.data.Filme
import pt.ismai.aplicacaofilmes.data.FilmeDatabase
import pt.ismai.aplicacaofilmes.data.FilmeRepository

class FavoritosViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: FilmeRepository
    
    val filmesFavoritos: StateFlow<List<Filme>>
    
    init {
        val filmeDao = FilmeDatabase.getDatabase(application).filmeDao()
        repository = FilmeRepository(filmeDao)
        
        filmesFavoritos = repository.filmesFavoritos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    
    fun removerFavorito(filme: Filme) {
        viewModelScope.launch {
            repository.toggleFavorito(filme.id, false)
        }
    }
    
    fun removerFilme(filme: Filme) {
        viewModelScope.launch {
            if (!filme.isVisto && !filme.isPorVer) {
                repository.deleteFilme(filme)
            } else {
                repository.toggleFavorito(filme.id, false)
            }
        }
    }
}
