package barberbook.app.bbbe.rest.account.service;

import barberbook.app.bbbe.rest.account.dto.*;

public interface AccountService {

    AccountLoginResponseDto login(AccountLoginRequestDto req) throws Exception;
    AccountResponseDto addAccountToEmail(AddAccountUserRequestDto req) throws Exception;
    AccountResponseDto signup(NewAccountSignupRequestDto req) throws Exception;

}
