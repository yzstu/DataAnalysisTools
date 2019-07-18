package com.dikey.dao;

import java.util.ArrayList;

public class testDao {
    public static void main(String[] args) {
        ArrayList<String> packetNoList = new ArrayList<String>();
        packetNoList.add("CMC-MZ-miku");
        packetNoList.add("CMC-MZ-onepunch");
        packetNoList.add("CMC-CCL-boke");
        new testM().test(packetNoList);
    }
}
