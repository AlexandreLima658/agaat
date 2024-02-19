package br.gov.pnae.agaat.infra.rest.cecanes;

import br.gov.pnae.agaat.application.cecanes.command.create.CreateCecaneInput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import br.gov.pnae.agaat.infra.rest.cecanes.models.UpdateCecaneHttpRequest;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create.CreateCecaneHttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "cecanes")
@Tag(name = "Cecanes")
public interface CecaneAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar uma nova Cecane")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso", content = @Content(schema = @Schema(implementation = CreateCecaneHttpResponse.class))),
            @ApiResponse(responseCode = "422", description = "A validação falhou", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    Object create(@RequestBody CreateCecaneInput request);

    @GetMapping(value = "{cecaneId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obter uma Cecane pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cecane recuperada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Cecane não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    ResponseEntity<?> retrieveById(@PathVariable(name = "cecaneId") UUID cecaneId);

    @GetMapping
    @Operation(summary = "Recuperar uma lista de Cecanes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um parâmetro inválido foi recebido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    ResponseEntity<?> retrieveByFilter(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "per_page", required = false, defaultValue = "5") final int perPage,
            @RequestParam(name = "terms", required = false, defaultValue = "") final String terms,
            @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Atualizar uma Cecane pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cecane atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "A validação falhou"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    Object update(@PathVariable(name = "id") UUID id, @RequestBody UpdateCecaneHttpRequest request);

    @DeleteMapping(
            value = "{cecaneId}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar uma Cecane pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cecane deletada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    void delete(@PathVariable(name = "cecaneId") UUID cecaneId);
}
