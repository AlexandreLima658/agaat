package br.gov.pnae.agaat.infra.rest.cecanes.controllers;

import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneInput;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.infra.rest.cecanes.models.UpdateCecaneHttpRequest;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create.CreateCecaneHttpResponse;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.filter.RetrieveCecaneByFilterHttpResponse;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.id.RetrieveCecaneByIdHttpResponse;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update.UpdateCecaneHttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Tag("e2eTest")
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles(profiles = "test")
class CecaneControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CecaneRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar um Cecane a partir de uma requisição válida")
    public void ShouldCreateCecane() throws Exception {

        //given
        final var input = new CreateCecaneInput("UFC Russas");

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.post("/cecanes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cecane_id").isString())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, CreateCecaneHttpResponse.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.cecaneId());

    }

    @Test
    @DisplayName("Deve retornar erro ao tentar criar um Cecane com nome inválido")
    public void ShouldCreateCecaneWithInvalidName() throws Exception {

        //given
        final var input = new CreateCecaneInput("");
        final var expectedErrorMessage = "Nome do Cecane não pode ser nulo ou vazio";

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.post("/cecanes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, ErrorInfo.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedErrorMessage, actualResponse.message());

    }

    @Test
    @DisplayName("Deve retornar um Cecane a partir de um id válido")
    public void ShouldGetCecaneById() throws Exception {

        // given
        final var cecane = CecaneFactory.create("UFC Russas");
        repository.persist(cecane);

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes/{id}", cecane.id().value())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, RetrieveCecaneByIdHttpResponse.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(cecane.id().value(), actualResponse.cecaneId());
        Assertions.assertEquals(cecane.nome(), actualResponse.nome());

    }

    @Test
    public void shouldGetPaginatedCecanesSortByNome() throws Exception {

        // given
        final var nomes = new String[]{"UFC Russas", "UFC Quixadá", "UFC Fortaleza", "UFC Sobral", "UFC Crateús"};

        for (String nome : nomes) {
            final var cecane = CecaneFactory.create(nome);
            repository.persist(cecane);
        }

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes?page=0&per_page=2&sort=nome")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, new TypeReference<Pagination<RetrieveCecaneByFilterHttpResponse>>() {
        });
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(2, actualResponse.items().size());
        Assertions.assertEquals(5, actualResponse.total());
        Assertions.assertEquals(0, actualResponse.currentPage());
        Assertions.assertEquals(2, actualResponse.perPage());

        Assertions.assertEquals("UFC Crateús", actualResponse.items().getFirst().nome());
        Assertions.assertEquals("UFC Fortaleza", actualResponse.items().get(1).nome());

    }

    @Test
    @DisplayName("Deve filtrar Cecanes por termo")
    public void shouldFilterCecanesByTerm() throws Exception {

        // given
        final var nomes = new String[]{"IFCE Morada Nova", "IFCE Fortaleza", "IFCE Cedro", "IFCE Aracati", "UFC Russas", "UFC Quixadá", "UFC Fortaleza", "UFC Sobral", "UFC Crateús"};

        for (String nome : nomes) {
            final var cecane = CecaneFactory.create(nome);
            repository.persist(cecane);
        }

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes?terms=ifce")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, new TypeReference<Pagination<RetrieveCecaneByFilterHttpResponse>>() {
        });
        Assertions.assertNotNull(actualResponse);

        for (final var response : actualResponse.items()) {
            Assertions.assertTrue(response.nome().toLowerCase().contains("ifce"));
        }

    }



    @Test
    public void ShouldGetPaginatedCecanesByTerm() throws Exception {

        // given
        final var nomes = new String[]{"IFCE Morada Nova", "IFCE Fortaleza", "IFCE Cedro", "IFCE Aracati", "UFC Russas", "UFC Quixadá", "UFC Fortaleza", "UFC Sobral", "UFC Crateús"};

        for (String nome : nomes) {
            final var cecane = CecaneFactory.create(nome);
            repository.persist(cecane);
        }

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes?page=0&per_page=2&term=IFCE&sort=nome")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, new TypeReference<Pagination<RetrieveCecaneByFilterHttpResponse>>() {
        });
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(2, actualResponse.items().size());
        Assertions.assertEquals(9, actualResponse.total());
        Assertions.assertEquals(0, actualResponse.currentPage());
        Assertions.assertEquals(2, actualResponse.perPage());

        Assertions.assertEquals("IFCE Aracati", actualResponse.items().getFirst().nome());
        Assertions.assertEquals("IFCE Cedro", actualResponse.items().get(1).nome());

    }


    @Test
    public void ShouldGetPaginatedCecanesSortByEmptyTerm() throws Exception {

        // given
        final var nomes = new String[]{"UFC Russas", "UFC Quixadá", "UFC Fortaleza", "UFC Sobral", "UFC Crateús"};

        for (String nome : nomes) {
            final var cecane = CecaneFactory.create(nome);
            repository.persist(cecane);
        }

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, new TypeReference<Pagination<RetrieveCecaneByFilterHttpResponse>>() {
        });
        Assertions.assertNotNull(actualResponse);
    }


    @Test
    public void ShouldGetPaginatedCecanesSortByInvalidTerm() throws Exception {

        // given
        final var nomes = new String[]{"UFC Russas", "UFC Quixadá", "UFC Fortaleza", "UFC Sobral", "UFC Crateús"};

        for (String nome : nomes) {
            final var cecane = CecaneFactory.create(nome);
            repository.persist(cecane);
        }

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes?page=0&per_page=2&term=1&sort=nome")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, new TypeReference<Pagination<RetrieveCecaneByFilterHttpResponse>>() {
        });
        Assertions.assertNotNull(actualResponse);
    }

    @Test
    @DisplayName("Deve lançar um erro 500 se o caso de uso lançar uma exceção")
    public void ShouldReturnInternalServerError() throws Exception {

        // given

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes?sort=null")
                )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, ErrorInfo.class);
        Assertions.assertNotNull(actualResponse);
    }

    @Test
    public void ShouldUpdateCecaneById() throws Exception {

        // given
        final var cecane = CecaneFactory.create("UFC Russas");
        repository.persist(cecane);

        //given
        final var input = new UpdateCecaneHttpRequest("UFC Russas - Campus Russas");

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.put("/cecanes/{id}", cecane.id().value())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cecane_id").isString())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, UpdateCecaneHttpResponse.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.cecaneId());
        Assertions.assertEquals(cecane.id().value(), actualResponse.cecaneId());
        Assertions.assertEquals(input.nome(), actualResponse.nome());
    }

    @Test
    // TODO O que esse método está testando?
    public void test1() throws Exception {

        // given
        final var cecaneId = CecaneId.generate();
        final var expectedErrorMessage = "Cecane com ID %s não encontrado".formatted(cecaneId.value());

        final var input = new UpdateCecaneHttpRequest("UFC Russas - Campus Russas");

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.put("/cecanes/{id}", cecaneId.value())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, ErrorInfo.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedErrorMessage, actualResponse.message());
    }

    @Test
    // TODO O que esse método está testando?
    public void test1Get() throws Exception {

        // given
        final var cecaneId = CecaneId.generate();
        final var expectedErrorMessage = "Cecane com ID %s não encontrado".formatted(cecaneId.value());

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes/{id}", cecaneId.value())
                )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, ErrorInfo.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedErrorMessage, actualResponse.message());
    }

    @Test
    public void ShouldDeleteCecaneById() throws Exception {

        // given
        final var cecane = CecaneFactory.create("UFC Russas");
        repository.persist(cecane);

        // when
        this.mvc.perform(
                        MockMvcRequestBuilders.delete("/cecanes/{id}", cecane.id().value())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        // then
        final var actualResponse = repository.findById(cecane.id());
        Assertions.assertTrue(actualResponse.isEmpty());

    }

    @Test
    public void ShouldDeleteCecaneInvalidId() throws Exception {

        // given
        final var cecaneId = CecaneId.generate();

        // when
        this.mvc.perform(
                        MockMvcRequestBuilders.delete("/cecanes/{id}", cecaneId.value())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

    }
}