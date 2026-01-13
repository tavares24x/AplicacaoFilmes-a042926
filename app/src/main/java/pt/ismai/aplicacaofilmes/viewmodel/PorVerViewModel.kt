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

class PorVerViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: FilmeRepository
    
    val filmesPorVer: StateFlow<List<Filme>>
    
    init {
        val filmeDao = FilmeDatabase.getDatabase(application).filmeDao()
        repository = FilmeRepository(filmeDao)
        
        filmesPorVer = repository.filmesPorVer.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    
    fun removerPorVer(filme: Filme) {
        viewModelScope.launch {
            repository.togglePorVer(filme.id, false)
        }
    }
    
    fun removerFilme(filme: Filme) {
        viewModelScope.launch {
            if (!filme.isFavorito && !filme.isVisto) {
                repository.deleteFilme(filme)
            } else {
                repository.togglePorVer(filme.id, false)
            }
        }
    }
    
    fun marcarComoVisto(filme: Filme) {
        viewModelScope.launch {
            // Remove de Por Ver
            repository.togglePorVer(filme.id, false)
            // Adiciona a Vistos
            repository.toggleVisto(filme.id, true)
        }
    }
}
