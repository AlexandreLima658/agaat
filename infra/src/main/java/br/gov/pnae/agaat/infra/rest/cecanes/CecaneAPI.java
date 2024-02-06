package br.gov.pnae.agaat.infra.rest.cecanes;

import br.gov.pnae.agaat.application.cecanes.create.Input;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "cecanes")
@Tag(name = "Cecanes")
public interface CecaneAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar uma nova Cecane")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "A validação falhou"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    ResponseEntity<?> create(@RequestBody Input input);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obter uma Cecane pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cecane recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cecane não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    CecaneResponse getById(@PathVariable(name = "id") Long id);
}
