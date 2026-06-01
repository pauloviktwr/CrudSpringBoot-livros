package com.portfolio.livros.service;

import com.portfolio.livros.model.Livro;
import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.repository.LivroRepository;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários - LivroService (CRUD Completo)")
class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;

    private DadosCadastraLivro dadosCadastra;
    private DadosEditarLivro dadosEditar;
    private Livro livroExistente;

    @BeforeEach
    void setUp() {
        dadosCadastra = new DadosCadastraLivro("Spring Boot em Ação", "Craig Walls");
        dadosEditar = new DadosEditarLivro(1L, "Spring Boot Avançado", "Craig Walls Atualizado");
        livroExistente = new Livro();
        livroExistente.setId(1L);
        livroExistente.setTitulo("Livro Original");
        livroExistente.setAutor("Autor Original");
    }

    // === CREATE ===
    @Test
    @DisplayName("Deve salvar livro novo com sucesso")
    void save_Sucesso() {
        // Arrange
        Livro livroSalvo = new Livro(dadosCadastra); 
        when(repository.save(any(Livro.class))).thenReturn(livroSalvo);

        // Act
        Livro resultado = service.save(dadosCadastra);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getTitulo()).isEqualTo(dadosCadastra.titulo());
        verify(repository, times(1)).save(any(Livro.class));
    }
   
    // === READ ===
    @Test
    @DisplayName("Deve carregar lista de livros")
    void carregaLivros_RetornaLista() {
        // Arrange
        Pageable paginacao = PageRequest.of(0, 10);
        List<Livro> listaLivrosMock = List.of(livroExistente);
        Page<Livro> paginaMock = new PageImpl<>(listaLivrosMock, paginacao, listaLivrosMock.size());
        
        // Configura o mock para aceitar qualquer Pageable e retornar a página mockada
        when(repository.findAll(any(Pageable.class))).thenReturn(paginaMock);

        // Act
        Page<Livro> resultado = service.carregaLivros(paginacao);

        // Assert

        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).hasSize(1);
        assertThat(resultado.getTotalElements()).isEqualTo(1);
        verify(repository, times(1)).findAll(paginacao);
    }

    @Test
    @DisplayName("Deve buscar livro por ID existente")
    void findById_Existe() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(livroExistente));

        // Act
        Livro resultado = service.findById(1L);

        // Assert
        assertThat(resultado.getId()).isEqualTo(1L);
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve falhar ao buscar livro inexistente")
    void findById_NaoExiste() {
        // Arrange
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.findById(999L))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessageContaining("Livro não encontrado");
    }

    // === UPDATE ===
    @Test
    @DisplayName("Deve atualizar dados do livro com sucesso")
    void update_Sucesso() {
        // Arrange
        when(repository.findById(dadosEditar.id())).thenReturn(Optional.of(livroExistente));
        when(repository.save(any(Livro.class))).thenReturn(livroExistente);

        // Act
        service.update(dadosEditar);

        // Assert
        assertThat(livroExistente.getTitulo()).isEqualTo(dadosEditar.titulo());
        verify(repository, times(1)).save(livroExistente);
    }

    // NOVO: Teste de erro no Update
    @Test
    @DisplayName("Deve falhar ao atualizar livro inexistente")
    void update_NaoExiste() {
        // Arrange
        when(repository.findById(dadosEditar.id())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.update(dadosEditar))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessageContaining("Livro não encontrado");
    }

    // === DELETE ===
    @Test
    @DisplayName("Deve deletar livro por ID")
    void deleteById_Sucesso() {
        // Arrange
        Long id = 1L;

        // Act
        service.deleteById(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }
}