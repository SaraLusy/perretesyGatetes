package com.empresa.perretesGatetes.domain.utils;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isEmail(String email) {
        return Pattern.compile(Constantes.EMAIL_PATTERN).matcher(email).matches();
    }

}
