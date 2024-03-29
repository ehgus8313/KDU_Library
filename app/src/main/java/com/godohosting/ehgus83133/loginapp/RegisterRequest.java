package com.godohosting.ehgus83133.loginapp;

import android.content.Intent;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ehgus on 2018-03-14.
 */

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://ehgus83133.godohosting.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, String userMajor, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userMajor", userMajor);
    }


    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
