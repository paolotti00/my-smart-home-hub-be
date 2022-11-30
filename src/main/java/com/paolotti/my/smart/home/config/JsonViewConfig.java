package com.paolotti.my.smart.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

public class JsonViewConfig {
    public static class AsInput{}
    public static class AsOutput extends AsInput {}
}
