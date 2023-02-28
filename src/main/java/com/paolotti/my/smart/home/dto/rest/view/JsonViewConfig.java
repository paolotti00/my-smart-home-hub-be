package com.paolotti.my.smart.home.dto.rest.view;

public class JsonViewConfig {

    public static class LowDetail {}
    public static class MediumDetail extends LowDetail {}
    public static class HighDetail extends  MediumDetail{}
}
