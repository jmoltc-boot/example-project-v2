package cl.mobdev.rickandmorty.api;

import cl.mobdev.rickandmorty.api.model.ApiErrorResponse;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;

@Api(value = "characters", description = "the characters API")
public interface CharactersApi {

    @ApiOperation(value = "", notes = "Returns all characters", response = GetCharactersResponse.class, tags={ "characters", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of characters.", response = GetCharactersResponse.class),
        @ApiResponse(code = 500, message = "Response with error.", response = GetCharactersResponse.class) })
    @RequestMapping(value = "/characters",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<GetCharactersResponse> getCharacters();

}
