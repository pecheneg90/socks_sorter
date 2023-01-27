package skypro.socks_sorter.Controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.socks_sorter.model.Socks;
import skypro.socks_sorter.service.SocksService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {
    SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    /**
     * Registers the arrival of socks to the warehouse this application<br>
     * Use method of service {@link SocksService#income(Socks)}
     *
     * @return OK if socks added in database
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "socks income has been added"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Query parameters are missing " +
                            "or are not in the correct format"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "an error occurred that " +
                            "is not dependent on the caller"
            )
    })
    @PostMapping("/income")
    public ResponseEntity<?> income(@RequestBody Socks socks) {
        socksService.income(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Registers the disposal of socks from the warehouse of the application<br>
     * Use method of service {@link SocksService#outcome(Socks)}
     *
     * @return OK if socks outcome
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "request completed, socks left the warehouse"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Query parameters are missing " +
                            "or are not in the correct format"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "an error occurred that " +
                            "is not dependent on the caller"
            )
    })
    @PostMapping("/outcome")
    public ResponseEntity<?> outcome(@RequestBody Socks socks) {
        try {
            socksService.outcome(socks);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Returns the total number of socks in stock that match
     * the query criteria passed in the parameters of the application<br>
     * Use method of service {@link SocksService#getAllSocks(String, String, Integer)}
     *
     * @return OK if request completed, result in response body
     * as a string representation of an integer
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "request completed, result in response body " +
                            "as a string representation of an integer"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Query parameters are missing " +
                            "or are not in the correct format"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "an error occurred that " +
                            "is not dependent on the caller"
            )
    })
    @GetMapping
    public ResponseEntity<String> getSocks(@RequestParam(value = "color") String color,
                                           @RequestParam(value = "operation") String operation,
                                           @RequestParam(value = "cottonPart") Integer cottonPart) {
        String count = String.valueOf(socksService.getAllSocks(color, operation, cottonPart));
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}