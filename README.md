# Aplicação de Filmes - Android

Aplicação Android desenvolvida em Jetpack Compose para gestão de filmes com integração à API IMDB.

## 🎯 Funcionalidades

### 4 Ecrãs Principais:

1. **Ecra Filmes**
   - Barra de pesquisa para procurar filmes
   - 3 filmes de recomendações
   - Opções para adicionar filmes a Favoritos, Vistos ou Por Ver

2. **Ecra Vistos**
   - Lista de filmes marcados como vistos
   - Opção para remover filmes da lista

3. **Ecra Favoritos**
   - Lista de filmes marcados como favoritos
   - Opção para remover filmes da lista

4. **Ecra Por Ver**
   - Lista de filmes marcados para ver mais tarde
   - Opção para remover filmes da lista

## 🔧 Tecnologias Utilizadas

- **Jetpack Compose** - UI moderna e declarativa
- **Room Database** - Armazenamento local de filmes
- **Retrofit** - Chamadas à API REST
- **Navigation Compose** - Navegação entre ecrãs
- **ViewModel** - Gestão de estado
- **Coroutines & Flow** - Programação assíncrona
- **Coil** - Carregamento de imagens
- **Material Design 3** - Design system

## 📦 Estrutura do Projeto

```
app/src/main/java/pt/ismai/aplicacaofilmes/
├── data/
│   ├── Filme.kt              # Entidade Room
│   ├── FilmeDao.kt           # Interface DAO
│   ├── FilmeDatabase.kt      # Database Room
│   └── FilmeRepository.kt    # Repositório de dados
├── network/
│   ├── ImdbModels.kt         # Modelos da API
│   ├── ImdbApiService.kt     # Interface Retrofit
│   └── RetrofitInstance.kt   # Configuração Retrofit
├── viewmodel/
│   ├── FilmesViewModel.kt    # ViewModel do ecra de filmes
│   ├── FavoritosViewModel.kt # ViewModel do ecra de favoritos
│   ├── VistosViewModel.kt    # ViewModel do ecra de vistos
│   └── PorVerViewModel.kt    # ViewModel do ecra por ver
├── ui/
│   ├── EcraFilmes.kt         # UI do ecra de filmes
│   ├── EcraFavoritos.kt      # UI do ecra de favoritos
│   ├── EcraVistos.kt         # UI do ecra de vistos
│   └── EcraPorVer.kt         # UI do ecra por ver
├── Destinos.kt               # Navegação entre ecrãs
└── MainActivity.kt           # Activity principal
```

## 🚀 Como Executar

1. **Pré-requisitos:**
   - Android Studio (versão Giraffe ou superior)
   - JDK 11 ou superior
   - Android SDK API 24 ou superior

2. **Passos:**
   - Abrir o projeto no Android Studio
   - Aguardar o sync do Gradle
   - Executar a aplicação num emulador ou dispositivo físico

## 🔑 API Configuration

A aplicação utiliza a API IMDB236 da RapidAPI. A chave API está configurada em:
- `FilmesViewModel.kt` - linha com `API_KEY`

**Nota:** Para uso em produção, mova a API key para um ficheiro de configuração seguro.

## 📱 Como Usar a Aplicação

### Pesquisar Filmes:
1. Aceder ao ecrã "Filmes"
2. Escrever o nome do filme na barra de pesquisa
3. Premir enter ou clicar no ícone de pesquisa

### Adicionar aos Favoritos:
1. Pesquisar ou ver as recomendações
2. Clicar no botão "Favorito" (ícone de coração)
3. O filme fica guardado localmente

### Marcar como Visto:
1. Pesquisar ou ver as recomendações
2. Clicar no botão "Visto" (ícone de estrela)
3. O filme aparece no ecrã "Vistos"

### Adicionar a Por Ver:
1. Pesquisar ou ver as recomendações
2. Clicar no botão "Adicionar a Por Ver"
3. O filme aparece no ecrã "Por Ver"

### Remover Filmes:
1. Aceder ao ecrã correspondente (Favoritos/Vistos/Por Ver)
2. Clicar no ícone de lixeira (Delete) ao lado do filme
3. O filme é removido da lista

## 💾 Armazenamento Local

- Os filmes são guardados localmente usando **Room Database**
- Não é necessário estar sempre ligado à internet
- As listas (Favoritos, Vistos, Por Ver) persistem mesmo após fechar a app
- Ao rodar o ecrã, os dados não são perdidos

## 🎨 Design

A aplicação segue as diretrizes do **Material Design 3**:
- Bottom Navigation Bar para navegação entre ecrãs
- Cards com elevação para cada filme
- Cores consistentes do tema
- Ícones intuitivos
- Layout responsivo

## ⚠️ Notas Importantes

1. **Permissões:** A aplicação requer permissão de Internet (já configurada no AndroidManifest)

2. **API Limits:** A RapidAPI tem limites de chamadas. Em caso de erro, verificar:
   - Conexão à internet
   - Limite de requests da API
   - Validade da API key

3. **Primeira Execução:** 
   - As recomendações são carregadas automaticamente
   - Pode demorar alguns segundos na primeira vez

4. **Imagens:** 
   - Utiliza a biblioteca Coil para carregamento assíncrono
   - Imagens são cached automaticamente

## 🐛 Troubleshooting

**App não compila:**
- Fazer Sync do Gradle: File > Sync Project with Gradle Files
- Limpar build: Build > Clean Project
- Rebuild: Build > Rebuild Project

**Erro de rede:**
- Verificar permissões no AndroidManifest
- Verificar conexão à internet do emulador/dispositivo
- Testar a API key

**Imagens não aparecem:**
- Verificar conexão à internet
- A API pode não ter imagem para todos os filmes

## 📝 Licença

Projeto académico desenvolvido para a disciplina de Computação Móvel.

---

Desenvolvido com ❤️ usando Jetpack Compose
