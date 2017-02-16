package org.agoncal.application.petstore.security;

import org.agoncal.application.petstore.util.ConfigProperty;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author blep
 *         Date: 16/02/12
 *         Time: 07:28
 */
public class LoginContextProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private SimpleCallbackHandler callbackHandler;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Produces
    public LoginContext produceLoginContext(@ConfigProperty("loginConfigFile") String loginConfigFileName,
                                            @ConfigProperty("loginModuleName") String loginModuleName) throws LoginException, URISyntaxException {

		 URL loginConfigURL = LoginContextProducer.class.getResource(loginConfigFileName);
		 URI loginConfigURI = loginConfigURL.toURI();
		 File loginConfigFile = new File("C:\\devel\\src\\agoncal-petstore-ee6\\src\\main\\resources\\petstore-test.login");
		 String strPath = loginConfigFile.toPath().toString();
		 
        System.setProperty("java.security.auth.login.config", strPath);

        try {
            return new LoginContext(loginModuleName, callbackHandler);
        } catch (Exception e) {
            System.out.println("ouch!!!");
            return null;
        }
    }

}
