package com.nasirov.library.services;

import org.junit.Assert;
import org.junit.Test;


public class TestAuthorizationService {

    private AuthorizationService authorizationService=AuthorizationService.getInstance();
    private String validName="David";
    private String nameWithNumber="David1";
    private String nameWithSpace="Davi d";
    private String validEmail="Test@mail.com";
    private String invalidEmail="Test.com";
    private String validPassword="Password1";
    private String shortPassword="Pas1";
    private String passwordWithoutNumber="Password";
    private String passwordInLowerCase="password1";

    @Test
    public void checkValidNameTest(){
        Assert.assertTrue(authorizationService.checkName(validName));
    }

    @Test
    public void checkNameWithSpaceTest(){
        Assert.assertFalse(authorizationService.checkName(nameWithSpace));
    }

    @Test
    public void checkNameWithNumberTest(){
        Assert.assertFalse(authorizationService.checkName(nameWithNumber));
    }

    @Test
    public void checkValidEmail(){
        Assert.assertTrue(authorizationService.checkEmail(validEmail));
    }

    @Test
    public void checkInvalidEmail(){
        Assert.assertFalse(authorizationService.checkName(invalidEmail));
    }


    @Test
    public void checkValidPassword(){
        Assert.assertTrue(authorizationService.checkPassword(validPassword));
    }

    @Test
    public void checkPasswordWithoutNumber(){
        Assert.assertFalse(authorizationService.checkPassword(passwordWithoutNumber));
    }

    @Test
    public void checkShortPassword(){
        Assert.assertFalse(authorizationService.checkPassword(shortPassword));
    }

    @Test
    public void checkPasswordInLowerCase(){
        Assert.assertFalse(authorizationService.checkPassword(passwordInLowerCase));
    }




}
