package com.ihr.ihr.common.interf;

public interface PasswordProvider {
    String convertToSha256(String password);
}
