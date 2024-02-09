package br.gov.pnae.agaat.infra.rest.cecanes.controllers;

import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneOutput;
import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdOutput;
import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneOutput;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.infra.database.in.memory.CecaneRepositoryInMemory;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneListResponse;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CreateCecaneRequest;
import br.gov.pnae.agaat.infra.rest.cecanes.models.UpdateCecaneRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class CecaneControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CecaneRepositoryInMemory repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void ShouldCreateCecane() throws Exception {

        //given
        final var input = new CreateCecaneRequest("UFC Russas");

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.post("/cecanes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, CreateCecaneOutput.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.id());

    }

    @Test
    public void ShouldCreateCecaneWithInvalidName() throws Exception {

        //given
        final var input = new CreateCecaneRequest("");
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
    public void ShouldGetCecaneById() throws Exception {

        // given
        final var cecane = CecaneFactory.create(new CecaneNome("UFC Russas"));
        repository.persist(cecane);

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes/{id}", cecane.id().value())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, GetCecaneByIdOutput.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(cecane.id().value(), actualResponse.id().value());
        Assertions.assertEquals(cecane.nome(), actualResponse.nome());

    }

    @Test
    public void ShouldGetPaginatedCecanes() throws Exception {

        // given
        final var nomes = new String[]{"UFC Russas", "UFC Quixadá", "UFC Fortaleza", "UFC Sobral", "UFC Crateús"};

        for (String nome : nomes) {
            final var cecane = CecaneFactory.create(new CecaneNome(nome));
            repository.persist(cecane);
        }

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.get("/cecanes?page=0&perPage=2&sort=nome")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, new TypeReference<Pagination<CecaneListResponse>>() {
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
    public void ShouldUpdateCecaneById() throws Exception {

        // given
        final var cecane = CecaneFactory.create(new CecaneNome("UFC Russas"));
        repository.persist(cecane);

        //given
        final var input = new UpdateCecaneRequest("UFC Russas - Campus Russas");

        // when
        final var result = this.mvc.perform(
                        MockMvcRequestBuilders.put("/cecanes/{id}", cecane.id().value())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(input))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andReturn().getResponse().getContentAsByteArray();

        // then
        final var actualResponse = mapper.readValue(result, UpdateCecaneOutput.class);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.id());
        Assertions.assertEquals(cecane.nome(), actualResponse.nome());

    }

    @Test
    public void ShouldDeleteCecaneById() throws Exception {

        // given
        final var cecane = CecaneFactory.create(new CecaneNome("UFC Russas"));
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

}