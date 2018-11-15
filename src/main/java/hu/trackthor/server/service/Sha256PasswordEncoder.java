package hu.trackthor.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Sha256PasswordEncoder implements PasswordEncoder {

    @Autowired
    HashingService hashing;
    
    @Override
    public String encode(CharSequence arg0) {
        return hashing.hash(arg0.toString());
    }

    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        return encode(arg0).equals(arg1);
    }

}
