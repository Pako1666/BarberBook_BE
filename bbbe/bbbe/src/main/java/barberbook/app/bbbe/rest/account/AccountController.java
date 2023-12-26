package barberbook.app.bbbe.rest.account;

import barberbook.app.bbbe.exception.ValidationException;
import barberbook.app.bbbe.rest.account.dto.*;
import barberbook.app.bbbe.rest.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account-api/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "login into the account", operationId = "login")
    @ApiResponse(responseCode = "200", description = "login into the account")
    @PostMapping("login")
    ResponseEntity<AccountLoginResponseDto> login(@RequestBody AccountLoginRequestDto req)throws Exception{
        return ResponseEntity.ok().body(accountService.login(req));
    }

    @Operation(summary = "add new account to the existing user", operationId = "addNewUserAccount")
    @ApiResponse(responseCode = "200", description = "add new account to the existing user")
    @PostMapping("add_new_account")
    ResponseEntity<AccountResponseDto> addToUserAccount(@RequestBody AddAccountUserRequestDto req) throws Exception {
        return  ResponseEntity.ok().body(accountService.addAccountToEmail(req));
    }

    @Operation(summary = "register a new account", operationId = "signup")
    @ApiResponse(responseCode = "200", description = "register for the first time")
    @PostMapping("signup")
    ResponseEntity<AccountResponseDto> signup(@RequestBody NewAccountSignupRequestDto req) throws Exception {
        return ResponseEntity.ok().body(accountService.signup(req));
    }
}
