package com.paolotti.my.smart.home.enums;

public enum DeviceInstallationStatusEnum {
    TO_ACTIVATE, // the device was discovered but not activated yet
    DEACTIVATED, // the device was discovered and activated then was deactivated
    BLACKLISTED // the device was discovered and blacklisted (so it will not discover anymore)
}
