package com.jacky.wework;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeworkConfigTest {

    @Test
    public void load() {
        WeworkConfig.load("");
    }

    @Test
    public void getInstance(){
        WeworkConfig.getInstance();
    }
}
