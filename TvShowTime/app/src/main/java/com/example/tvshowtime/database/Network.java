package com.example.tvshowtime.database;

import com.google.gson.annotations.SerializedName;

public class Network {
    @SerializedName("id")
    private int networkId;
    @SerializedName("name")
    private String networkName;

    public Network(int networkId, String networkName) {
        this.networkId = networkId;
        this.networkName = networkName;
    }

    public int getNetworkId() {
        return networkId;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }
}
