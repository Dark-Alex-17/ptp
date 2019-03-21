package edu.msudenver.tsp.services;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.msudenver.tsp.services.dto.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final RestService restService;
    @Value("${persistence.api.connection.timeout.milliseconds}") private int connectionTimeoutMilliseconds;
    @Value("${persistence.api.socket.timeout.milliseconds}") private int socketTimeoutMilliseconds;
    @Value("${persistence.api.base.url}") private String persistenceApiBaseUrl;

    @Autowired
    public UserService(final RestService restService) {
        this.restService = restService;
    }

    public Optional<Account> createNewAccount(final Account account) {
        if (account == null) {
            LOG.error("Given null account, returning {}");
            return Optional.empty();
        }
        final Instant start = Instant.now();

        try {
            final TypeToken<Account> typeToken = new TypeToken<Account>() {};
            final Optional<Account> persistenceApiResponse = restService.post(persistenceApiBaseUrl + "accounts/",
                    new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create().toJson(account),
                    typeToken,
                    connectionTimeoutMilliseconds,
                    socketTimeoutMilliseconds);

            if (persistenceApiResponse.isPresent()) {
                LOG.info("Returning {}", persistenceApiResponse.get());
            } else {
                LOG.info("Unable to create new account {}", account.toString());
            }

            return persistenceApiResponse;
        } catch (final Exception e) {
            LOG.error("Error creating new account {}", e);
            return Optional.empty();
        } finally {
            LOG.info("Create new account request took {} ms", Duration.between(start, Instant.now()).toMillis());
        }
    }

    public Optional<Account> updatePassword(final Account account , final String  password){

        if(account ==null){
            LOG.error("user not exist, returning{}");
            return Optional.empty();
        }

        final Integer id = account.getId();
        account.setPassword(password);
        final Instant start = Instant.now();

        try{
            final String auth = "";
            final TypeToken<Account> typeToken = new TypeToken<Account>(){};
            final Optional<Account> persistenceApiResponse = restService.patch(persistenceApiBaseUrl + "accounts/" + id,
                    new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create().toJson(account),
                    typeToken,
                    connectionTimeoutMilliseconds,
                    socketTimeoutMilliseconds);

            if (persistenceApiResponse.isPresent()) {
                LOG.info("Returning {}", persistenceApiResponse.get());
            } else {
                LOG.info("Unable to update password for account {}",account.toString());
            }

            return persistenceApiResponse;
        } catch (final Exception e) {
            LOG.error("Error updating account {}", e);
            return Optional.empty();
        } finally {
            LOG.info("Update account request took {} ms", Duration.between(start, Instant.now()).toMillis());
        }
    }

    public Optional<Account> updateUsername(final Account account , final String  username){

        if(account ==null){
            LOG.error("user not exist, returning{}");
            return Optional.empty();
        }

        final Integer id = account.getId();
        account.setUsername(username);
        final Instant start = Instant.now();

        try{
            final String auth = "";
            final TypeToken<Account> typeToken = new TypeToken<Account>(){};
            final Optional<Account> persistenceApiResponse = restService.patch(persistenceApiBaseUrl + "accounts/"+id,
                    new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create().toJson(account),
                    typeToken,
                    connectionTimeoutMilliseconds,
                    socketTimeoutMilliseconds);

            if (persistenceApiResponse.isPresent()) {
                LOG.info("Returning {}", persistenceApiResponse.get());
            } else {
                LOG.info("Unable to update username for account {}",account.toString());
            }

            return persistenceApiResponse;
        } catch (final Exception e) {
            LOG.error("Error updating account {}", e);
            return Optional.empty();
        } finally {
            LOG.info("Update account request took {} ms", Duration.between(start, Instant.now()).toMillis());
        }
    }

    public boolean deleteAccount(final Account account) {
        if(account == null){
            LOG.error("Username does not exist, returning {}");
            return false;
        }
        final Integer id = account.getId();
        final Instant start = Instant.now();

        try{

            final boolean persistenceApiResponse = restService.delete(persistenceApiBaseUrl + "/accounts/" + id,
                    connectionTimeoutMilliseconds,
                    socketTimeoutMilliseconds, HttpStatus.NO_CONTENT );
            if(persistenceApiResponse){
                LOG.info("return {}", persistenceApiResponse);
            }
            else {
                LOG.info("Unable to delete user {}", account.toString());
            }

            return persistenceApiResponse;
        }catch (final Exception e) {
            LOG.error("Error deleting user {}", e);
            return false;
        } finally {
            LOG.info("delete user request took {} ms", Duration.between(start, Instant.now()).toMillis());
        }
    }
}
